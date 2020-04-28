package com.github.lyrric.config.server.conf;

import com.github.lyrric.config.server.constant.ApiConstant;
import com.github.lyrric.config.server.plugin.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Created on 2019/3/12.
 *
 * @author wangxiaodong
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);
        registration.addPathPatterns(ApiConstant.API_PREFIX + "/**");
        registration.excludePathPatterns(ApiConstant.API_PREFIX  + "/user/login",
                "/api/remote/conf/**");
    }
}
