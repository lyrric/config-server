package com.github.lyrric.controller;

import com.github.lyrric.entity.Config;
import com.github.lyrric.service.ConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created on 2019/3/13.
 *
 * @author wangxiaodong
 */
@RestController
@RequestMapping(value = "/api/remote")
public class RemoteController {

    @Resource
    private ConfigService configService;

    /**
     * 获取指定的配置
     * @param confGroupId
     * @param confDataId
     * @param confAppKey
     * @return
     */
    @GetMapping(value = "/conf/get")
    public Config get(@RequestParam String confGroupId,
                      @RequestParam String confDataId,
                      @RequestParam String confAppKey){
        return configService.get(confGroupId, confDataId);
    }

    /**
     * 获取配置最后一次修改的时间
     * @param confGroupId
     * @param confDataId
     * @return
     */
    @GetMapping(value = "/conf/modified-time")
    public Date getModifiedTime(@RequestParam String confGroupId,
                                @RequestParam String confDataId){
        Config conf = configService.get(confGroupId, confDataId);
        return conf.getModifiedTime();
    }
}
