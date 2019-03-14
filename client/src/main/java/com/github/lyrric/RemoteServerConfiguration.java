package com.github.lyrric;

import com.github.lyrric.properties.ConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2019/3/14.
 *
 * @author wangxiaodong
 */
@Configuration
@EnableConfigurationProperties(ConfigProperties.class)
public class RemoteServerConfiguration {
}
