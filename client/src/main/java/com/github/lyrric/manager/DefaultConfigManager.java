package com.github.lyrric.manager;

import com.github.lyrric.model.Config;
import com.github.lyrric.model.HttpResult;
import com.github.lyrric.util.HttpClient;
import lombok.extern.apachecommons.CommonsLog;

/**
 * Created on 2019/3/14.
 *
 * @author wangxiaodong
 */
@CommonsLog
public class DefaultConfigManager implements ConfigManager {

    private HttpClient httpClient;

    public DefaultConfigManager(String confGroupId, String confDataId, String confServerHost) {
        httpClient = new HttpClient(confGroupId, confDataId, confServerHost);
    }

    @Override
    public Config getConfig() throws Exception {
        HttpResult result = httpClient.getConfig();
        if(!result.getSuccess()){
            throw new Exception(result.getErrMsg());
        }
        return (Config)result.getData();
    }

    @Override
    public void start() {

    }
}
