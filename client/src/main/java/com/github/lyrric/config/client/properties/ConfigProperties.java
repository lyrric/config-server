package com.github.lyrric.config.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 2019/3/14.
 *
 * @author wangxiaodong
 */
@ConfigurationProperties(prefix = "conf")
@Component
public class ConfigProperties {

    /**
     * config server host
     */
    private String serverHost;
    /**
     * request timeout,millisecond,default 2000
     */
    private Integer reqTimeout;
    /**
     * config group id
     */
    private String groupId;
    /**
     * config data id list
     */
    private String[] dataIds;

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public Integer getReqTimeout() {
        return reqTimeout;
    }

    public void setReqTimeout(Integer reqTimeout) {
        this.reqTimeout = reqTimeout;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String[] getDataIds() {
        return dataIds;
    }

    public void setDataIds(String[] dataIds) {
        this.dataIds = dataIds;
    }
}
