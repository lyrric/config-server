package com.github.lyrric;

import com.github.lyrric.manager.ConfigManager;
import com.github.lyrric.manager.DefaultConfigManager;
import com.github.lyrric.model.Config;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.io.StringReader;
import java.util.Properties;

/**
 * Created on 2019/3/14.
 *
 * @author wangxiaodong
 */
@SuppressWarnings("all")
public class ConfigListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final String CONFIG_DATA_ID  = "conf.data-id";
    private static final String CONFIG_GROUP_ID = "conf.group-id";
    private static final String CONFIG_SERVER_HOST = "conf.server-host";
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
        if(confGroupId != null && confDataId != null && confServerHost != null){
            init(confGroupId, confDataId, confServerHost);
        }

    }

    /**
     * 初始化配置
     * @param confGroupId
     * @param confDataId
     */
    private void init(String confGroupId, String confDataId, String confServerHost){
        ConfigManager configManager = new DefaultConfigManager(confGroupId, confDataId, confServerHost);
        //第一次进行初始化配置
        try {
            Config config = configManager.getConfig();
            properties.load(new StringReader(config.getContent()));
            MutablePropertySources sources = ((AbstractEnvironment)environment).getPropertySources();
            sources.remove(CONFIG_NAME);
            sources.addLast(new PropertiesPropertySource(CONFIG_NAME, properties));
        }catch (Exception e){
            throw new RuntimeException("获取配置错误");
        }
    }
}
