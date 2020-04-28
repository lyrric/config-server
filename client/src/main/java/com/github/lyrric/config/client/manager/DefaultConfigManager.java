package com.github.lyrric.config.client.manager;

import com.github.lyrric.config.client.model.Config;
import com.github.lyrric.config.client.properties.ConfigProperties;
import com.github.lyrric.config.client.util.ConfigUtil;
import com.github.lyrric.config.client.util.HttpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 2019/3/14.
 *
 * @author wangxiaodong
 */
public class DefaultConfigManager implements ConfigManager {

    private HttpClient httpClient;

    private Log log = LogFactory.getLog(DefaultConfigManager.class);
    /**
     * 配置上次修改时间
     */
    private Map<String, Date> modifyTimeMap;

    public DefaultConfigManager(ConfigProperties properties) {
        httpClient = new HttpClient(properties);
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
                        List<Config> configList =  httpClient.getConfig();
                        String content = ConfigUtil.parseConfigContent(configList);
                        event.onchange(content);
                        modifyTimeMap = configList.stream().collect(Collectors.toMap(Config::getDataId, Config::getModifiedTime));
                        log.info("配置更新完成");
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
        /*try {
            Map<String, Date> remoteModifyTime = httpClient.getModifiedTime();
            for(Map.Entry<String, Date> entry:remoteModifyTime.entrySet()){
                Date date = entry.getValue();
                if(date.equals(modifyTimeMap.get(entry.getKey()))){
                    continue;
                }
                log.info("检测到" + entry.getKey() + "发生改变");
                return true;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return false;
    }
}
