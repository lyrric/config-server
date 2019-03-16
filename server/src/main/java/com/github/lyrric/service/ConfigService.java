package com.github.lyrric.service;

import com.github.lyrric.entity.Config;

import java.util.List;

/**
 * Created on 2019/3/13.
 *
 * @author wangxiaodong
 */
public interface ConfigService {

    /**
     * 获取配置
     * @param confGroupIds
     * @param confDataIds
     * @return
     */
    List<Config> get(String confGroupIds, String confDataIds);
}
