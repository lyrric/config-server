package com.github.lyrric.config.client.model;

import com.github.lyrric.common.model.req.ResConfig;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created on 2020-04-28.
 *
 * @author wangxiaodong
 */
public class RemoteProperty {

    private String groupId;

    private List<String> dataIds;

    private Map<String, Object> config;

    public RemoteProperty(List<ResConfig> resConfigs) {
        groupId = resConfigs.get(0).getGroupId();
        dataIds = resConfigs.stream().map(ResConfig::getDataId).collect(Collectors.toList());
        config = new HashMap<>();
        resConfigs.forEach(resConfig -> {
            try {
                addConfigToMap(resConfig.getContent());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 获取配置
     * @param name
     * @return
     */
    public Object getProperty(String name){
        return config.get(name);
    }

    /**
     * 解析配置文本到config里面
     * @param content
     */
    public void addConfigToMap(String content) throws ParseException {
       String[] lines = content.split(System.getProperty("line.separator"));
        for (String line : lines) {
            String[] arr = line.split("=");
            if(arr.length != 2){
                throw new ParseException("error when parse "+line, 0);
            }
            config.put(arr[0], arr[1]);
        }
    }

    public String getGroupId() {
        return groupId;
    }

    public List<String> getDataIds() {
        return dataIds;
    }

}
