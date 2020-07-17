package com.github.lyrric.config.client.manager;

/**
 * Created on 2019/3/15.
 *
 * @author wangxiaodong
 */
public interface ConfigChangeEvent {

    /**
     * 当配置发生改变时会触发此事件
     * @param content
     */
    void onchange(String content);
}
