package com.github.lyrric.controller;

import com.github.lyrric.entity.Config;
import com.github.lyrric.service.ConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created on 2019/3/13.
 *
 * @author wangxiaodong
 */
@Api(value = "配置服务")
@RestController
@RequestMapping(value = "/api/remote/conf")
public class RemoteController {

    @Resource
    private ConfigService configService;

    /**
     * 获取指定的配置
     * @param confGroupIds
     * @param confDataIds
     * @return
     */
    @GetMapping(value = "/get")
    public List<Config> get(@RequestParam String confGroupIds,
                           @RequestParam String confDataIds,
                           @RequestParam String confAppKeys){
        return configService.get(confGroupIds, confDataIds);
    }

    /**
     * 获取配置最后一次修改的时间
     * @param confGroupId
     * @param confDataId
     * @return
     */
    @GetMapping(value = "/modified-time")
    public List<Date> getModifiedTime(@RequestParam String confGroupId,
                                @RequestParam String confDataId,
                                @RequestParam String confAppKey){
        List<Config> confList = configService.get(confGroupId, confDataId);
        return confList.stream().map(Config::getModifiedTime).collect(Collectors.toList());
    }
}
