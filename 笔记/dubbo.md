### Dubbo

#### 分布式基础理论

分布式系统是若干独立计算机的集合，这些计算机对于用户来说就像单个相关系统。

##### RPC：远程过程调用

dubbo:一款高性能的Java RPC框架。（最初是阿里开发的，后面卖给了apache）

在编写dubbo应用的时候，先来写一个Provider，将其提供的服务注册到注册中心，然后编写一个Consumer，其到注册中心订阅提供者提供的服务。

先下载并安装zookeeper，我下载的是3.4.14版本。然后下载dubbo-admin这个服务监控，也可以不安装，我是直接下载别人打包好的jar包。

要先启动zookeeper的zkServer.cmd，dubbo的jar包才能正常启动。



#### dubbo-helloworld

需求：订单服务需要调用用户服务获取某个用户的所有地址。需要创建两个服务模块进行测试：

订单服务web模块：创建订单

用户服务service模块：查询用户地址

运行时候要先运行提供者，然后运行消费者。因为提供者得把服务注册到zookeeper中，消费者才能订阅。



配置dubbo-monitor：

dubbo-monitor主要用来统计服务的调用次数和调用时间，配置好了可以结合admin管理后台使用，可以清晰的看到服务的访问记录、成功次数、失败次数等

也是要打包成jar  我这里用的是2.6.0版本。要双击start.bat运行。



dubbo有个注解：`@Reference`可以注入。

无论是provider还是consumer的pom.xml中一定要加上：

```xml
<dependency>
   <groupId>org.apache.curator</groupId>
   <artifactId>curator-framework</artifactId>
   <version>2.13.0</version>
</dependency>
```

否则会报错找不到curator并且无法通过注解去注册中心找到已经注册的provider，以及provider无法注册到注册中心。

如果是通过springboot整合dubbo，pom文件要加上：

```x
<dependency>
   <groupId>com.alibaba.boot</groupId>
   <artifactId>dubbo-spring-boot-starter</artifactId>
   <version>0.2.1.RELEASE</version>
</dependency>
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>dubbo</artifactId>
    <version>2.6.5</version>
</dependency>
```

有个习惯就是把所有的接口和javaBean都抽取出来，然后消费者和提供者在pom文件中引入它的依赖即可使用这些接口。（建的需是module而不是project）

还要注意使用@Service暴露服务，这里用的是import com.alibaba.dubbo.config.annotation.Service;

**面试经常问：zookeeper宕机与dubbo直连**

zookeeper宕机了不影响使用，仍能通过缓存提供服务列表查询，但不能注册新服务。注册中心全部宕掉后，服务提供者和服务消费者仍能通过本地缓存通讯。没有注册中心也能调用服务：dubbo直连，直接告诉消费者提供者所在的机器地址。























