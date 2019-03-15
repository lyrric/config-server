package com.github.lyrric.manager;

/**
 * Created on 2019/3/15.
 *
 * @author wangxiaodong
 */
public interface ConfigChangeEvent {

    /**
     * 当配置发生改变时会处罚此时间
     * @param content
     */
    void onchange(String content);
}
