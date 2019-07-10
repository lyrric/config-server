package com.github.lyrric.config.server.plugin;

import com.alibaba.fastjson.JSONObject;
import com.github.lyrric.config.server.constant.ConfConstant;
import com.github.lyrric.config.server.entity.Config;
import com.github.lyrric.config.server.mapper.ConfigMapper;
import com.github.lyrric.config.server.model.HttpResult;
import com.github.lyrric.config.server.service.ConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private ConfigService configService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String confGroupIds = request.getParameter(ConfConstant.CONF_GROUP_ID_ATTRIBUTE);
        String confDataIds = request.getParameter(ConfConstant.CONF_DATA_ID_ATTRIBUTE);
        String confAppKeys = request.getParameter(ConfConstant.CONF_APP_KEY_ATTRIBUTE);
        if(StringUtils.isEmpty(confGroupIds) || StringUtils.isEmpty(confDataIds) || StringUtils.isEmpty(confAppKeys)){
            write(response, "参数错误");
            return false;
        }
        List<String> confDataIdList = Arrays.asList(confDataIds.split(","));
        List<String> confAppKeyList = Arrays.asList(confAppKeys.split(","));
        if(confDataIdList.size() == 0 || confDataIdList.size() != confAppKeyList.size()){
            write(response, "参数data_ids与app_keys无法对应");
            return false;
        }
        List<Config> configs = configService.get(confGroupIds, confDataIds);
        if(configs.size() == 0){
            write(response, "没有找到对应的配置");
            return false;
        }
        //从数据库里查出的数量与配置的数量不一致
        if(configs.size() != confAppKeyList.size()){
            //循环查找出没有在数据库里面找到的data_id
            List<String> dataIdList =  confDataIdList.stream()
                    .filter(dataid->!configs.stream().filter(config -> config.getDataId().equals(dataid)).findFirst().isPresent())
                    .collect(Collectors.toList());
            String dataIds = org.apache.commons.lang3.StringUtils.join(dataIdList, ",");
            write(response, dataIds + "没有找到对应的配置");
            return false;
        }
        //校验appkey
        for(Config config:configs){
            for(int i=0;i<confDataIdList.size();i++){
                if(config.getDataId().equals(confDataIdList.get(i)) && !config.getAppKey().equals(confAppKeyList.get(i))){
                    //appkey有误
                    write(response, confDataIdList.get(i) + "对应的App Key配置错误");
                    return false;
                }
            }
        }
        return super.preHandle(request, response, handler);
    }
    private void write(HttpServletResponse response, String errMsg)  {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(JSONObject.toJSONString(HttpResult.failure(errMsg)));
        writer.flush();
        writer.close();
    }
}
