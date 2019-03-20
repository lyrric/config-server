package com.github.lyrric.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2019/3/15.
 *
 * @author wangxiaodong
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${test.value}")
    private String value;
    @Value("${test.name}")
    private String name;

    @GetMapping(value = "/test")
    public String test(){
       return name+"-----"+value;
    }
}
