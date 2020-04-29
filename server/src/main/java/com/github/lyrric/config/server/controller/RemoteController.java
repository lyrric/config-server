package com.github.lyrric.config.server.controller;

import com.github.lyrric.common.model.req.ReqConfigParam;
import com.github.lyrric.common.model.req.ResConfig;
import com.github.lyrric.config.server.entity.Config;
import com.github.lyrric.config.server.model.BusinessException;
import com.github.lyrric.config.server.service.ConfigService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
     * @return
     */
    @PostMapping(value = "/get")
    public List<ResConfig> get(@RequestBody ReqConfigParam param) throws BusinessException {
        return configService.get(param);
    }

    /**
     * 获取配置最后一次修改的时间
     * @param confGroupId
     * @param confDataId
     * @return
     */
/*    @GetMapping(value = "/modified-time")
    public Map<String, Date> getModifiedTime(@RequestParam String confGroupId,
                                     @RequestParam String confDataId,
                                     @RequestParam String confAppKey){
        List<Config> confList = configService.get(confGroupId, confDataId);
        return confList.stream().collect(Collectors.toMap(Config::getDataId, Config::getModifiedTime));
    }*/
}
