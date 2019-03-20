package com.github.lyrric.manager;

import com.github.lyrric.model.Config;
import com.github.lyrric.model.HttpResult;
import com.github.lyrric.util.ConfigUtil;
import com.github.lyrric.util.HttpClient;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * Created on 2019/3/14.
 *
 * @author wangxiaodong
 */
@CommonsLog
public class DefaultConfigManager implements ConfigManager {

    private HttpClient httpClient;

    /**
     * 配置上次修改时间
     */
    private Map<String, Date> modifyTimeMap;

    public DefaultConfigManager(String confGroupId, String confDataId, String confServerHost, String confAppKey, Integer reqTimeout) {
        httpClient = new HttpClient(confGroupId, confDataId, confServerHost, confAppKey, reqTimeout);
        modifyTimeMap = new HashMap<>();
    }

    @Override
    public String getConfig() throws Exception {
        List<Config> configList = httpClient.getConfig();
        modifyTimeMap = configList.stream().collect(Collectors.toMap(Config::getDataId, Config::getModifiedTime));
        return ConfigUtil.parseConfigContent(configList);
    }

    @Override
    public void start(ConfigChangeEvent event) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    if(checkConfigModify()){
                        log.info("检测到配置发生改变，更新配置");
                        List<Config> configList =  httpClient.getConfig();
                        String content = ConfigUtil.parseConfigContent(configList);
                        event.onchange(content);
                        modifyTimeMap = configList.stream().collect(Collectors.toMap(Config::getDataId, Config::getModifiedTime));

                    }
                } catch (IOException e) {
                    log.error("定时获取任务失败");
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 1000 * 30, 1000 * 10);
    }

    /**
     * 判断配置是否发生改变
     * @return true=发生改变
     */
    private boolean checkConfigModify(){
        try {
            Map<String, Date> remoteModifyTime = httpClient.getModifiedTime();
            for(Map.Entry<String, Date> entry:remoteModifyTime.entrySet()){
                Date date = entry.getValue();
                if(date.equals(modifyTimeMap.get(entry.getKey()))){
                    continue;
                }
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
