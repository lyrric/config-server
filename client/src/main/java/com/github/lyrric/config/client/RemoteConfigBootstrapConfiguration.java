package com.github.lyrric.config.client;

import com.github.lyrric.config.client.core.RemotePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2020-04-28.
 *
 * @author wangxiaodong
 */
@Configuration
public class RemoteConfigBootstrapConfiguration {

    @Bean
    public RemotePropertySourceLocator remotePropertySourceLocator(){
        return new RemotePropertySourceLocator();
    }
}
