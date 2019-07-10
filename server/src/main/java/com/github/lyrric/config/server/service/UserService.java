package com.github.lyrric.config.server.service;

import com.github.lyrric.config.server.model.BusinessException;

/**
 * Created on 2019/3/12.
 *
 * @author wangxiaodong
 */
public interface UserService {

    /**
     * 登陆
     * @param username
     * @param password
     */
    void login(String username, String password) throws BusinessException;
}
