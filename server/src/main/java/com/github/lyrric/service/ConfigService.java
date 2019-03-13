package com.github.lyrric.service;

import com.github.lyrric.entity.Config;

/**
 * Created on 2019/3/13.
 *
 * @author wangxiaodong
 */
public interface ConfigService {

    /**
     * 获取配置
     * @param confGroupId
     * @param confDataId
     * @return
     */
    Config get(String confGroupId, String confDataId);
}
