package com.github.lyrric.controller;

import com.github.lyrric.constant.ApiConstant;
import com.github.lyrric.model.BusinessException;
import com.github.lyrric.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created on 2019/3/12.
 * 用户模块
 * @author wangxiaodong
 */
@RestController
@RequestMapping(value = ApiConstant.API_PREFIX + "/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 登陆接口
     * @param username
     * @param password
     * @throws BusinessException
     */
    @PostMapping(value = "/login")
    public void login(@RequestParam String username, @RequestParam String password) throws BusinessException {
        userService.login(username, password);
    }

}
