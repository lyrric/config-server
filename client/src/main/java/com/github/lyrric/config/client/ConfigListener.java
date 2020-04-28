package com.github.lyrric.config.client;

import com.github.lyrric.config.client.manager.ConfigManager;
import com.github.lyrric.config.client.manager.DefaultConfigManager;
import com.github.lyrric.config.client.properties.ConfigProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created on 2019/3/14.
 *
 * @author wangxiaodong
 */
@SuppressWarnings("all")
public class ConfigListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final String CONFIG_CONFIGS = "conf";

    private static final String CONFIG_NAME = "default";

    private Log log = LogFactory.getLog(DefaultConfigManager.class);

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
        ConfigProperties property = getProperty();
        init(property);
    }

    /**
     * 初始化配置
     * @param confGroupId
     * @param confDataId
     */
    private void init(ConfigProperties properties){
        ConfigManager configManager = new DefaultConfigManager(properties);
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
        /*if(confAutoRefresh != null && confAutoRefresh){
            configManager.start((content)->{
                refreshConfig(content);
            });
        }*/
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

    private ConfigProperties getProperty(){
        ConfigProperties properties = new ConfigProperties();
        String serverHost = environment.getProperty("conf.server-host");
        if(StringUtils.isEmpty(serverHost)){
            throw new NullPointerException("Missing Property conf.server-host");
        }
        properties.setServerHost(serverHost);
        Integer reqTimeout = environment.getProperty("conf.req-timeout", Integer.class);
        reqTimeout = reqTimeout==null?2000:reqTimeout;
        properties.setReqTimeout(reqTimeout);
        String groupId = environment.getProperty("conf.group-id");
        if(StringUtils.isEmpty(groupId)){
            throw new NullPointerException("Missing Property conf.group-id");
        }
        properties.setGroupId(groupId);
        List<String> dataIds  = new ArrayList<>();
        for (Integer i = 0; ; i++) {
            String dataId = environment.getProperty("conf".concat(".data-ids[").concat(i.toString()).concat("]"));
            if(StringUtils.isEmpty(dataId)){
                break;
            }
            dataIds.add(dataId);
        }
        if(dataIds.size() == 0){
            throw new NullPointerException("Missing Property conf.data-ids");
        }
        properties.setDataIds(dataIds.toArray(new String[dataIds.size()]));
        return properties;
    }
}
