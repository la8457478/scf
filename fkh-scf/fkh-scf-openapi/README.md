# 项目说明 
1. 该项目基于SpringBoot+SpringMVC+Swagger搭建
2. 集成Dubbo对系统服务进行调用，另外集成阿里的Sentinel，用于对系统接口服务的调用进行监控


## Swagger 使用说明
Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。总体目标是使客户端和文件系统作为服务器以同样的速度来更新。文件的方法，参数和模型紧密集成到服务器端的代码，允许API来始终保持同步。
### 使用文档
1. 官网：https://github.com/swagger-api/swagger-core/wiki/Annotations-1.5.X#quick-annotation-overview
2. 中文参考：https://www.cnblogs.com/softidea/p/6251249.html
3. 访问地址： http://localhost:8081/openapi/swagger-ui.html

### Sentinel 使用说明
Sentinel 具有以下特征:

丰富的应用场景： Sentinel 承接了阿里巴巴近 10 年的双十一大促流量的核心场景，例如秒杀，即突发流量控制在系统容量可以承受的范围；消息削峰填谷；实时熔断下游不可用应用，等等。
完备的监控功能： Sentinel 同时提供最实时的监控功能，您可以在控制台中看到接入应用的单台机器秒级数据，甚至 500 台以下规模的集群的汇总运行情况。
简单易用的扩展点： Sentinel 提供简单易用的扩展点，您可以通过实现扩展点，快速的定制逻辑。例如定制规则管理，适配数据源等。
Sentinel 分为两个部分:

核心库（Java 客户端）不依赖任何框架/库，能够运行于所有 Java 运行时环境，同时对 Dubbo / Spring Cloud 等框架也有较好的支持。
控制台（Dashboard）基于 Spring Boot 开发，打包后可以直接运行，不需要额外的 Tomcat 等应用容器。

### 配置OpenAPIApplication启动参数
1. Git：https://github.com/alibaba/Sentinel

启动参数说明
``` 
-Dcsp.sentinel.dashboard.server=192.168.8.100:8080 
-Dcsp.sentinel.api.port=8719 
-Dproject.name=openapi
```

dashboard.server 为控制台的连接地址及端口
api.port 本服务启动后，和服务器端建立连接的端口，默认端口为8719
project.name 在控制台中，显示的应用名称
启动后，可访问 [链接](http://localhost:8719/tree?type=root)进行查看

### 单独运行控制台程序 sentinel-dashboard 项目
1. 下载地址：
https://github.com/alibaba/Sentinel/tree/master/sentinel-dashboard
2. 启动参数：
```
java -Dserver.port=8080 \
-Dcsp.sentinel.dashboard.server=localhost:8080 \
-Dproject.name=sentinel-dashboard \
-jar target/sentinel-dashboard.jar
```
3. 访问地址： http://localhost:8080/#/dashboard/home ，其中8080为启动时配置的端口号
