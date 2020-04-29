# config-center轻量级的远程配置中心

**config-center实现的是将配置文件放在配置中心里面，修改配置无需修改代码的功能**

config-server可以将写在配置中心里面，然后在服务端只需要简单的配置好配置中心的地址，group-id，data-id，程序在启动的时候，将会从配置中心获取配置，加入到本地配置中。


## 基于Spring Boot的config-center
配置中心主要包括两个部分，客户端和服务端。

服务端是基于Spring Boot开发的，主要是管理配置，提供接口给客户端访问，需要单独启动，并部署为一个服务。

客户端也是基于Spring Boot开发的，主要是在程序启动的时候，从服务端获取配置，加入到本地配置中，用户只需要依赖相关maven，然后配置好配置中心的地址，group-id，data-id就可以使用（只能在spring boot项目中使用）。



# 使用步骤

## 服务端(server项目)

1. 创建数据库config-server，然后导入server项目下的src->resources->sql->config-server.sql

2. 在application.yml中配置好数据库连接信息

3. 启动项目，访问http://localhost:8088/login.html，默认用户名密码admin，admin，添加配置即可

## 客户端

1. 依赖maven
```
  <dependency>
        <groupId>com.github.lyrric</groupId>
        <artifactId>config-client</artifactId>
        <version>1.0-SNAPSHOT</version>
  </dependency>
```
2. 在yml中配置好信息
```
conf:
  server-host: http://localhost:8088  #配置中心地址
  req-timeout: 2000  #请求超时时间
  group-id: test_group  #group-id
  data-ids:  #data-ids，支持同group-id下的多个data-id
    - test_data1
    - test_data2
```
### 愉快的使用吧
