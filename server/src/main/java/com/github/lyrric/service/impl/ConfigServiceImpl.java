package com.github.lyrric.service.impl;

import com.github.lyrric.entity.Config;
import com.github.lyrric.mapper.ConfigMapper;
import com.github.lyrric.service.ConfigService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.weekend.Weekend;

import javax.annotation.Resource;

/**
 * Created on 2019/3/13.
 *
 * @author wangxiaodong
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Resource
    private ConfigMapper configMapper;

    @Override
    public Config get(String confGroupId, String confDataId) {
        Weekend<Config> weekend = new Weekend<>(Config.class);
        weekend.weekendCriteria()
                .andEqualTo(Config::getGroupId, confGroupId)
                .andEqualTo(Config::getDataId, confDataId);
        return configMapper.selectOneByExample(weekend);
    }
}
