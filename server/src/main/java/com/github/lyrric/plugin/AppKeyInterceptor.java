package com.github.lyrric.plugin;

import com.alibaba.fastjson.JSONObject;
import com.github.lyrric.constant.ConfConstant;
import com.github.lyrric.entity.Config;
import com.github.lyrric.mapper.ConfigMapper;
import com.github.lyrric.model.HttpResult;
import com.github.lyrric.util.UserSessionUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import tk.mybatis.mapper.weekend.Weekend;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 2019/3/12.
 * 远程调用， AppKey校验
 * @author wangxiaodong
 */
@SuppressWarnings("all")
@Component
public class AppKeyInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private ConfigMapper configMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String confGroupId = request.getParameter(ConfConstant.CONF_GROUP_ID_ATTRIBUTE);
        String confDataId = request.getParameter(ConfConstant.CONF_DATA_ID_ATTRIBUTE);
        String confAppKey = request.getParameter(ConfConstant.CONF_APP_KEY_ATTRIBUTE);
        if(StringUtils.isEmpty(confGroupId) || StringUtils.isEmpty(confDataId) || StringUtils.isEmpty(confAppKey)){
            write(response, "参数错误");
            return false;
        }
        Weekend<Config> weekend = new Weekend<>(Config.class);
        weekend.weekendCriteria()
                .andEqualTo(Config::getGroupId, confGroupId)
                .andEqualTo(Config::getDataId, confDataId);
        Config config = configMapper.selectOneByExample(weekend);
        if(config == null){
            write(response, "配置不存在");
            return false;
        }
        if(!config.getAppKey().equals(confAppKey)){
            write(response, "conf_app_key无效");
            return false;
        }
        return super.preHandle(request, response, handler);
    }
    private void write(HttpServletResponse response, String errMsg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(HttpResult.failure(errMsg)));
        writer.flush();
        writer.close();
    }
}
