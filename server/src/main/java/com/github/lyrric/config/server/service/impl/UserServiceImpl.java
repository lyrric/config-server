package com.github.lyrric.config.server.service.impl;

import com.github.lyrric.config.server.entity.User;
import com.github.lyrric.config.server.mapper.UserMapper;
import com.github.lyrric.config.server.model.BusinessException;
import com.github.lyrric.config.server.service.UserService;
import com.github.lyrric.config.server.util.UserSessionUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.weekend.Weekend;

import javax.annotation.Resource;

/**
 * Created on 2019/3/12.
 *
 * @author wangxiaodong
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void login(String username, String password) throws BusinessException {
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new BusinessException("用户名或密码错误");
        }
        Weekend<User> weekend = new Weekend<>(User.class);
        weekend.weekendCriteria().andEqualTo(User::getUsername, username);
        User user = userMapper.selectOneByExample(weekend);
        if(null == user || !user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
            throw new BusinessException("用户名或密码错误");
        }
        user.setPassword(null);
        UserSessionUtil.setSession(user);
    }

}
