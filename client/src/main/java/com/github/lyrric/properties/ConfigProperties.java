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
    private String groupIds;
    /**
     * conf_data_id
     */
    private String dataIds;
    /**
     * conf_app_key
     */
    private String appKeys;
    /**
     * request timeout,millisecond,default 2000
     */
    private Integer reqTimeout;
}
