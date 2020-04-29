package com.github.lyrric.config.client.manager;

import com.github.lyrric.common.model.req.ResConfig;
import com.github.lyrric.config.client.core.RemotePropertySource;
import com.github.lyrric.config.client.model.RemoteProperty;

import java.util.List;

/**
 * Created on 2019/3/14.
 * 配置管理中心
 * @author wangxiaodong
 */
public interface ConfigManager {
    /**
     * 获取配置
     * @return
     * @throws Exception
     */
    RemoteProperty getConfig() throws Exception;

    /**
     * 开始定时刷新配置
     * @param event 监听器
     */
    void start(ConfigChangeEvent event);

}
