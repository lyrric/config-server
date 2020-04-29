package com.github.lyrric.config.server.service.impl;

import com.github.lyrric.common.model.req.ReqConfigParam;
import com.github.lyrric.common.model.req.ResConfig;
import com.github.lyrric.config.server.entity.Config;
import com.github.lyrric.config.server.mapper.ConfigMapper;
import com.github.lyrric.config.server.mapper.manual.ConfigExtMapper;
import com.github.lyrric.config.server.model.BusinessException;
import com.github.lyrric.config.server.service.ConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.weekend.Weekend;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
    @Resource
    private ConfigExtMapper configExtMapper;

    @Override
    public List<ResConfig> get(ReqConfigParam param) throws BusinessException {
        Weekend<Config> weekend = new Weekend<>(Config.class);
        weekend.weekendCriteria()
                .andEqualTo(Config::getGroupId, param.getGroupId())
                .andIn(Config::getDataId, param.getDataIds());
        List<Config> configs = configMapper.selectByExample(weekend);
        if(configs.size() == 0){
            throw new BusinessException("没有符合条件的配置");
        }
        List<ResConfig> result = new ArrayList<>(configs.size());
        configs.forEach(item->{
            ResConfig resConfig = new ResConfig();
            BeanUtils.copyProperties(item, resConfig);
            result.add(resConfig);
        });
        return result;
    }

    @Override
    public PageInfo<Config> page(String groupId, String dataId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(configExtMapper.search(groupId, dataId));
    }

    @Override
    public Config findById(Integer id) throws BusinessException {
        if(id == null){
            throw new BusinessException("ID错误");
        }
        return configMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(Config config) throws BusinessException {
        config.setModifiedTime(new Date());
        if(StringUtils.isEmpty(config.getGroupId()) ||
                StringUtils.isEmpty(config.getDataId()) ||
                StringUtils.isEmpty(config.getContent())){
            throw new BusinessException("参数缺失");
        }
        if(config.getId() == null){
            config.setCreatedTime(new Date());
            configMapper.insert(config);
        }else {
            configMapper.updateByPrimaryKeySelective(config);
        }

    }

    @Override
    public void delete(int id) {
        configMapper.deleteByPrimaryKey(id);
    }

}
