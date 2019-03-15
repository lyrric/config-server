package com.github.lyrric.manager;

import com.github.lyrric.model.Config;

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
    Config getConfig() throws Exception;

    /**
     * 开始定时刷新配置
     * @param event 监听器
     */
    void start(ConfigChangeEvent event);

}
