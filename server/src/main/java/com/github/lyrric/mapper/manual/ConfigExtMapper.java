package com.github.lyrric.mapper.manual;

import com.github.lyrric.core.BaseMapper;
import com.github.lyrric.entity.Config;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

/**
 * Created on 2019/3/20.
 *
 * @author wangxiaodong
 */
public interface ConfigExtMapper extends BaseMapper<Config> {

    /**
     * 分页
     * @param groupId
     * @param dataId
     * @return
     */
    Page<Config> page(@Param("groupId") String groupId,
                      @Param("dataId") String dataId);
}
