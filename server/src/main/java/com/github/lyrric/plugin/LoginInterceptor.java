package com.github.lyrric.plugin;

import com.alibaba.fastjson.JSONObject;
import com.github.lyrric.model.HttpResult;
import com.github.lyrric.util.UserSessionUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created on 2019/3/12.
 *
 * @author wangxiaodong
 */
@SuppressWarnings("all")
public class LoginInterceptor extends HandlerInterceptorAdapter {
    /**
     * 登陆uri不做拦截
     */
    private final String LOGIN_URI = "/user/login";
    /**
     * 获取接口业务写api_key校验
     */
    private final String API_URI = "/api/get";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if(!uri.equals(LOGIN_URI) && !uri.equals(API_URI) && UserSessionUtil.getSession() == null ){
            write(response);
            return false;
        }else{
            return super.preHandle(request, response, handler);
        }

    }
    private void write(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONObject.toJSONString(HttpResult.failure("你还没有登陆")));
        writer.flush();
        writer.close();
    }
}
