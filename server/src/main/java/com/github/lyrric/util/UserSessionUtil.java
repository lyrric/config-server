package com.github.lyrric.util;

import com.github.lyrric.entity.User;
import org.springframework.http.HttpRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created on 2019/3/12.
 *
 * @author wangxiaodong
 */
public class UserSessionUtil {

    public static final String USER_SESSION_ATTRIBUTE = "user";
    /**
     * 获取当前用户session
     * @return
     */
    public static User getSession(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return (User)request.getSession().getAttribute(USER_SESSION_ATTRIBUTE);
    }
    /**
     * 设置当前用户session
     * @return
     */
    public static void setSession(User user){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute(USER_SESSION_ATTRIBUTE, user);
    }
}
