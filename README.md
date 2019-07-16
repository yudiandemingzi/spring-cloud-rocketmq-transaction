# <center>RocketMQ实现分布式事务</center>

有关RocketMQ实现分布式事务前面写了一篇博客

1、[RocketMQ实现分布式事务原理](https://www.cnblogs.com/qdhxhz/p/11191399.html)

下面就这个项目做个整体简单介绍。

## <font color=#FFD700>一、项目概述</font>

#### 1、技术架构

项目总体技术选型

```
SpringCloud(Finchley.RELEASE) + SpringBoot2.0.4 + Maven3.5.4 + RocketMQ4.3 +MySQL + lombok(插件)
```

有关SpringCloud主要用到以下四个组建

```
Eureka Server +config-server(配置中心)+ Eureka Client + Feign(服务间调用) 
```

配置中心是用MySQL存储数据。

#### 2、项目整体结构

```makefile
config-service  # 配置中心
eureka          # 注册中心
service-order   #订单微服务
service-produce #商品微服务
```

各服务的启动顺序就安装上面的顺序启动。

`大致流程`

启动后，配置中心、订单微服务、商品微服务都会将信息注册到注册中心。

如果访问：`localhost:7001`(注册中心地址）,以上服务都出现说明启动成功。

![](https://img2018.cnblogs.com/blog/1090617/201907/1090617-20190717002438786-605382528.png)



#### 3、分布式服务流程

用户在订单微服务下单后，会去回调商品微服务去减库存。这个过程需要事务的一致性。

![](https://img2018.cnblogs.com/blog/1090617/201907/1090617-20190717002448277-331707552.png)



#### 4、测试流程

页面输入：

```
http://localhost:9001/api/v1/order/save?userId=1&productId=1&total=4	
```

订单微服务执行情况（订单服务事务执行成功）

![](https://img2018.cnblogs.com/blog/1090617/201907/1090617-20190717002934582-471936462.png)





商品微服务执行情况（商品服务事务执行成功）

![](https://img2018.cnblogs.com/blog/1090617/201907/1090617-20190717002506834-1271705975.png)



当然你也可以通过修改参数来模拟分布式事务出现的各种情况。

<br>