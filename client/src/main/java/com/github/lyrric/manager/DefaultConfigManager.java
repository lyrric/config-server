package com.github.lyrric.manager;

import com.github.lyrric.model.Config;
import com.github.lyrric.model.HttpResult;
import com.github.lyrric.util.HttpClient;
import lombok.extern.apachecommons.CommonsLog;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadPoolExecutor;

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
    private Date modifyTime;

    public DefaultConfigManager(String confGroupId, String confDataId, String confServerHost, String confAppKey, Integer reqTimeout) {
        httpClient = new HttpClient(confGroupId, confDataId, confServerHost, confAppKey, reqTimeout);
    }

    @Override
    public Config getConfig() throws Exception {
        Config config = httpClient.getConfig();
        modifyTime = config.getModifiedTime();
        return config;
    }

    @Override
    public void start(ConfigChangeEvent event) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    Date date = httpClient.getModifiedTime();
                    if(!date.equals(modifyTime)){
                        log.info("检测到配置发生改变，更新配置");
                        Config config =  httpClient.getConfig();
                        event.onchange(config.getContent());
                        modifyTime = config.getModifiedTime();
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


}
