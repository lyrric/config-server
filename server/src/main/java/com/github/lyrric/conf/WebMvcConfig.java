package com.github.lyrric.conf;

import com.github.lyrric.plugin.ParamInterceptor;
import com.github.lyrric.plugin.LoginInterceptor;
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
    @Resource
    private ParamInterceptor paramInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/swagger-ui.html**",
                "/swagger-resources/**",
                "/webjars/**",
                "/images/**",
                "/v2/**",
                "/api/user/login#**",
                "/api/remote/**");

        registration = registry.addInterceptor(paramInterceptor);
        registration.addPathPatterns("/api/remote/**");
    }
}
