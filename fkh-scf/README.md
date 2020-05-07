# 简介
架构学习笔记
# 目录说明

fkh-stack
├── fkh-center
│   ├── fkh-center-third                    # http://192.168.2.18/center/fkh-center-third
├── fkh-framework
│   ├── fkh-dashboard                       # http://192.168.2.18/framework/fkh-dashboard
│   ├── fkh-dns                             # http://192.168.2.18/framework/fkh-dns
│   ├── fkh-gateway                         # http://192.168.2.18/framework/fkh-gateway
│   ├── fkh-kernel
│   │   ├── fkh-company-build               # http://192.168.2.18/framework/fkh-company-build
│   │   ├── fkh-company-primary             # http://192.168.2.18/framework/fkh-company-primary
│   │   ├── fkh-starter                     # http://192.168.2.18/framework/fkh-starter
│   │   ├── fkh-starter-example             # http://192.168.2.18/framework/fkh-starter-example
│   │   └── fkh-starter-processor           # http://192.168.2.18/framework/fkh-starter-processor
│   ├── fkh-moss                            # http://192.168.2.18/framework/fkh-moss
│   ├── fkh-nacos                           # http://192.168.2.18/framework/fkh-nacos
│   ├── fkh-nacos-dashboard                 # http://192.168.2.18/framework/fkh-nacos-dashboard
│   ├── fkh-seata                           # http://192.168.2.18/framework/fkh-seata
│   └── fkh-sentinel                        # http://192.168.2.18/framework/fkh-sentinel
│   └── fkh-pangu                           # http://192.168.2.18/framework/fkh-pangu
│   └── fkh-template                        # http://192.168.2.18/framework/fkh-template
└── fkh-mservice
    ├── fkh-mservice-auth                   # http://192.168.2.18/mservice/fkh-mservice-auth
    ├── fkh-mservice-system                 # http://192.168.2.18/mservice/fkh-mservice-system
└── fkh-mservice-third                  # http://192.168.2.18/mservice/fkh-mservice-third

fkh-scf-storage 数据层模块,包括 dao,entity,service。
数据层工具 mybatis-plus  3.1.2版本 api文档 https://mybatis.plus/guide/
 1.entity实体类型上加@TableName 映射表名  （更新lombok为新版本，不然@SuperBuilder注解无效）
 2.dao接口继承BaseDao(继承mp的BaseMapper,包含简单的增删改查方法，新增了两个方法)
 3.service接口继承BaseService(继承mp的IService,包含简单的增删改查方法，新增了两个方法)
fkh-scf-service 接口提供模块 启动之后可以查看swagger文档 http://localhost:63708/swagger-ui.html
    TemplateUserController feign接口  提供swagger文档。
    UserDubboProvider  dubbo接口 
fkh-scf-common VO等实体，业务所需枚举类，异常类 
fkh-scf-client-feign feigh接口调用客户端
fkh-scf-client-dubbo dubbo接口调用客户端
AutoCodeTest 自动生产测试工具。


## 构建

## 使用

## 更新日志

### 3.0.0-SNAPSHOT (2019-09-27)

#### Bug Fixes

#### Features
