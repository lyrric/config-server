package com.github.lyrric.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2019/3/12.
 * 用户模块
 * @author wangxiaodong
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @PostMapping(value = "/login")
    public void login(String username, String password){

    }
}
