package com.github.lyrric.config.server.core;

import tk.mybatis.mapper.common.Mapper;

/**
 * Created on 2019/3/12.
 * 这个mapper不能被扫描到
 * @author wangxiaodong
 */
public interface BaseMapper<T> extends Mapper<T> {
}
