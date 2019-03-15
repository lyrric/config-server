package com.github.lyrric.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created on 2019/3/14.
 *
 * @author wangxiaodong
 */
@ConfigurationProperties(prefix = "conf")
@Data
public class ConfigProperties {

    /**
     * config server host
     */
    private String serverHost;
    /**
     * conf_group_id
     */
    private String groupId;
    /**
     * conf_data_id
     */
    private String dataId;
    /**
     * conf_app_key
     */
    private String appKey;
    /**
     * request timeout,millisecond,default 2000
     */
    private Integer reqTimeout;
}
