package com.github.lyrric.config.server.plugin;

import com.alibaba.fastjson.JSONObject;
import com.github.lyrric.config.server.model.HttpResult;
import com.github.lyrric.config.server.util.UserSessionUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created on 2019/3/12.
 * 登陆权限验证
 * @author wangxiaodong
 */
@SuppressWarnings("all")
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(UserSessionUtil.getSession() == null){
            write(response, "你还没有登陆");
            return false;
        }
        return super.preHandle(request, response, handler);
    }
    private void write(HttpServletResponse response, String errMsg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(HttpResult.failure(false, 5000, errMsg, null)));
        writer.flush();
        writer.close();
    }
}
