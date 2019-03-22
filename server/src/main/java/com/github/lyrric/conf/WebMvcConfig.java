package com.github.lyrric.conf;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lyrric.constant.ApiConstant;
import com.github.lyrric.plugin.AppKeyInterceptor;
import com.github.lyrric.plugin.LoginInterceptor;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    private AppKeyInterceptor appKeyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);
        registration.addPathPatterns(ApiConstant.API_PREFIX + "/**");
        registration.excludePathPatterns(ApiConstant.API_PREFIX  + "/user/login",
                "/api/remote/conf/**");

        registration = registry.addInterceptor(appKeyInterceptor);
        registration.addPathPatterns("/api/remote/conf/**");
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(list);
        converters.add(fastConverter);

        //处理Indicator响应序列化
        MappingJackson2HttpMessageConverter indicatorConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        indicatorConverter.setObjectMapper(objectMapper);
        List<MediaType> indicatorMediaTypes = new ArrayList<MediaType>();
        indicatorMediaTypes.add(new MediaType("application", "vnd.spring-boot.actuator.v1+json"));
        indicatorConverter.setSupportedMediaTypes(indicatorMediaTypes);
        converters.add(indicatorConverter);
    }
}
