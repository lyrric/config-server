package com.github.lyrric.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created on 2019/3/12.
 *
 * @author wangxiaodong
 */
@SpringBootApplication
@MapperScan(basePackages = "com.github.lyrric.config.server.mapper")
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
