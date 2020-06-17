### Dubbo

#### 分布式基础理论

分布式系统是若干独立计算机的集合，这些计算机对于用户来说就像单个相关系统。

##### RPC：远程过程调用

dubbo:一款高性能的Java RPC框架。（最初是阿里开发的，后面卖给了apache）

在编写dubbo应用的时候，先来写一个Provider，将其提供的服务注册到注册中心，然后编写一个Consumer，其到注册中心订阅提供者提供的服务。

先下载并安装zookeeper，我下载的是3.4.14版本。然后下载dubbo-admin这个服务监控，也可以不安装，我是直接下载别人打包好的jar包。

要先启动zookeeper的zkServer.cmd，dubbo的jar包才能正常启动。











