package com.github.demo.config.demo;

import com.github.lyrric.config.client.ConfigListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * Created on 2019/3/14.
 *
 * @author wangxiaodong
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@RefreshScope
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoApplication.class);
        application.addListeners(new ConfigListener());
        application.run(args);
    }
}
