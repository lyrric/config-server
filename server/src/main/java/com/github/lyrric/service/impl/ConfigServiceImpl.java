package com.github.lyrric.service.impl;

import com.github.lyrric.entity.Config;
import com.github.lyrric.mapper.ConfigMapper;
import com.github.lyrric.mapper.manual.ConfigExtMapper;
import com.github.lyrric.model.BusinessException;
import com.github.lyrric.service.ConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.weekend.Weekend;

import javax.annotation.Resource;
import java.util.*;

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
    public List<Config> get(String confGroupIds, String confDataIds) {
        List<String> confDataIdList = Arrays.asList(confDataIds.split(","));
        Weekend<Config> weekend = new Weekend<>(Config.class);
        weekend.weekendCriteria()
                .andEqualTo(Config::getGroupId, confGroupIds)
                .andIn(Config::getDataId, confDataIdList);
        return configMapper.selectByExample(weekend);
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
    public String randKey(Integer id) throws BusinessException {
        if(id == null){
            throw new BusinessException("ID错误");
        }
        String key = randKey();
        Config config = new Config();
        config.setAppKey(key);
        config.setId(id);
        configMapper.updateByPrimaryKeySelective(config);
        return key;
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
            config.setAppKey(randKey());
            configMapper.insert(config);
        }else {
            configMapper.updateByPrimaryKeySelective(config);
        }

    }

    @Override
    public void delete(int id) {
        configMapper.deleteByPrimaryKey(id);
    }


    private String randKey(){
       return UUID.randomUUID().toString().replace("-","");
    }
}
