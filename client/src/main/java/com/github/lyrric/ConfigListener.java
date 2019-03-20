package com.github.lyrric;

import com.github.lyrric.manager.ConfigManager;
import com.github.lyrric.manager.DefaultConfigManager;
import com.github.lyrric.model.Config;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * Created on 2019/3/14.
 *
 * @author wangxiaodong
 */
@SuppressWarnings("all")
@CommonsLog
public class ConfigListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final String CONFIG_DATA_ID  = "conf.data-ids";
    private static final String CONFIG_GROUP_ID = "conf.group-ids";
    private static final String CONFIG_APP_KEY = "conf.app-keys";
    private static final String CONFIG_SERVER_HOST = "conf.server-host";
    private static final String CONFIG_REQ_TIMEOUT = "conf.req-timeout";
    private static final String CONFIG_AUTO_REFRESH= "conf.auto-refresh";

    private static final String CONFIG_NAME = "default";

    /**
     * 属性配置
     */
    private volatile Properties properties = new Properties();
    /**
     * 环境
     */
    private volatile Environment environment = null;

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        if(environment == null){
            environment = event.getEnvironment();
        }
        String confGroupId = environment.getProperty(CONFIG_GROUP_ID);
        String confDataId = environment.getProperty(CONFIG_DATA_ID);
        String confServerHost = environment.getProperty(CONFIG_SERVER_HOST);
        String confAppKey = environment.getProperty(CONFIG_APP_KEY);
        Boolean confAutoRefresh = Boolean.valueOf(environment.getProperty(CONFIG_AUTO_REFRESH));

        Integer confReqTimeout = Integer.parseInt(environment.getProperty(CONFIG_REQ_TIMEOUT));
        if(confGroupId != null && confDataId != null && confServerHost != null && confAppKey != null){
            init(confGroupId, confDataId, confServerHost, confAppKey, confReqTimeout, confAutoRefresh);
        }

    }

    /**
     * 初始化配置
     * @param confGroupId
     * @param confDataId
     */
    private void init(String confGroupId, String confDataId, String confServerHost, String confAppKey, Integer confReqTimeout, Boolean confAutoRefresh){
        ConfigManager configManager = new DefaultConfigManager(confGroupId, confDataId, confServerHost, confAppKey, confReqTimeout);
        //第一次进行初始化配置
        try {
            log.info("初始化获取配置");
            String content = configManager.getConfig();
            refreshConfig(content);
        }catch (Exception e){
            log.error("初始化获取配置失败");
            e.printStackTrace();
        }

        //是否定时刷新
        if(confAutoRefresh != null && confAutoRefresh){
            configManager.start((content)->{
                refreshConfig(content);
            });
        }
    }

    private void refreshConfig(String content){
        try {
            properties.load(new StringReader(content));
        } catch (IOException e) {
            log.error("更新配置错误");
            e.printStackTrace();
        }
        MutablePropertySources sources = ((AbstractEnvironment)environment).getPropertySources();
        sources.remove(CONFIG_NAME);
        sources.addLast(new PropertiesPropertySource(CONFIG_NAME, properties));
    }
}
