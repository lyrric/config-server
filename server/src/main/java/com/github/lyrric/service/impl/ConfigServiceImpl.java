package com.github.lyrric.service.impl;

import com.github.lyrric.entity.Config;
import com.github.lyrric.mapper.ConfigMapper;
import com.github.lyrric.service.ConfigService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.weekend.Weekend;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public List<Config> get(String confGroupIds, String confDataIds) {
        List<String> confDataIdList = Arrays.asList(confDataIds.split(","));
        Weekend<Config> weekend = new Weekend<>(Config.class);
        weekend.weekendCriteria()
                .andEqualTo(Config::getGroupId, confGroupIds)
                .andIn(Config::getDataId, confDataIdList);
        return configMapper.selectByExample(weekend);
    }

}
