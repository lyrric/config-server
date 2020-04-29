package com.github.lyrric.config.server.service;


import com.github.lyrric.common.model.req.ReqConfigParam;
import com.github.lyrric.common.model.req.ResConfig;
import com.github.lyrric.config.server.entity.Config;
import com.github.lyrric.config.server.model.BusinessException;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created on 2019/3/13.
 *
 * @author wangxiaodong
 */
public interface ConfigService {

    /**
     * 获取配置
     * @return
     */
    List<ResConfig> get(ReqConfigParam dtoList) throws BusinessException;

    /**
     * 列表
     * @param groupId
     * @param dataId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Config> page(String groupId, String dataId, int pageNum, int pageSize);
    /**
     * 获取配置
     * @param id
     * @return
     */
    Config findById(Integer id) throws BusinessException;

    /**
     * 保存
     * @param config
     */
    void save(Config config) throws BusinessException;

    /**
     * 刪除
     * @param id
     */
    void delete(int id);
}
