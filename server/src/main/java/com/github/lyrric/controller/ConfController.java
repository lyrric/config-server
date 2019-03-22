package com.github.lyrric.controller;

import com.github.lyrric.constant.ApiConstant;
import com.github.lyrric.entity.Config;
import com.github.lyrric.model.BusinessException;
import com.github.lyrric.service.ConfigService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created on 2019/3/20.
 *
 * @author wangxiaodong
 */
@RestController
@RequestMapping(value = ApiConstant.API_PREFIX + "/conf")
public class ConfController {

    @Resource
    private ConfigService configService;

    /**
     * 配置列表
     * @param groupId
     * @param dataId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/list")
    public PageInfo<Config> list(@RequestParam(required = false) String groupId,
                                 @RequestParam(required = false) String dataId,
                                 @RequestParam(defaultValue = "1", required = false) int pageNum,
                                 @RequestParam(defaultValue = "10", required = false)  int pageSize){
        return configService.page(groupId, dataId, pageNum, pageSize);
    }

    /**
     * 获取配置详情
     * @param id
     * @return
     * @throws BusinessException
     */
    @GetMapping(value = "/{id}")
    public Config detail(@PathVariable Integer id) throws BusinessException {
        return configService.findById(id);
    }

    /**
     * 重新生成appKey
     * @param id
     * @return
     */
    @GetMapping(value = "/key/{id}")
    public String randKey(@PathVariable Integer id) throws BusinessException {
        return configService.randKey(id);
    }

    /**
     * 保存
     * @param config
     */
    @PostMapping
    public void save(@RequestBody Config config) throws BusinessException {
        configService.save(config);
    }
}
