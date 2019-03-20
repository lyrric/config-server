package com.github.lyrric.controller;

import com.github.lyrric.constant.ApiConstant;
import com.github.lyrric.entity.Config;
import com.github.lyrric.service.ConfigService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
