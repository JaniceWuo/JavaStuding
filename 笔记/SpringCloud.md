#### eureka注册中心

springcloud习惯采用eureka注册中心。

eureka是Netflix开源的一款提供`服务注册和发现`的产品。

步骤：

引入组件启动器，覆盖默认配置，在引导类上添加注解

启用Eureka Server:

@EnableEurekaServer注解

启用Service Provider非常简单，只需要加上@EnableDiscoveryClient即可

在pom中引入Spring Cloud Eureka Server的依赖：

```java
<dependency>    
<groupId>org.springframework.cloud</groupId>    
<artifactId>spring-cloud-starter-eureka-server</artifactId><version>1.2.0.RELEASE</version>
</dependency>
```



Renew（服务续约）操作由Service Provider定期调用，类似于心跳。主要是用来告诉Eureka Server Service Provider还活着，避免服务被剔除掉。



#### 远程调用技术：rpc  http

rpc协议：自定义数据格式 限定技术  传输速度快 效率高  tcp

http协议：统一的数据格式 不限定技术 rest接口 tcp



#### 什么是SpringCloud

是在springboot的基础上构建的微服务架构的解决方案，是很多组件的集合。

zuul:网关组件，路由请求，过滤器

ribbon:负载均衡组件

hystrix:熔断组件

feign:远程调用组件

eureka



#### Ribbon

在实际开发时，往往会开启很多个service-provider集群，那到底该访问哪个呢？

这时需要ribbon负载均衡器。它可以基于某种负载均衡算法，自动的帮助服务者去请求，我们也可以自定义算法。

#### hystrix

是一种保护机制

hystrix解决雪崩问题的手段：

1.线程隔离

2.服务熔断

hystrix为每个依赖服务调用分配一个小的线程池，如果线程池已满，调用将被立即拒绝，默认不采用排队，加速判定时间。

用户的请求将不再直接访问服务，而是通过线程池中的空闲线程来访问服务，如果线程池已满，或者请求超时，则会进行降级处理。

使用@EnableCircuitBreaker 开启熔断

#### Feign

可以把rest请求进行隐藏，伪装成类似SpringMVC的Controller一样。

feign.hystrix.enable=true 开启feign的熔断功能

在引导类上加上@EnableFeignClients  启用feign

创建一个接口，在接口上添加@FeignClient

#### Zuul网关

@EnableZuulProxy

过滤器：

​        创建一个类继承ZuulFilter基类

​        重写四个方法 filterType  filterOrder  shouldFilter  run



集群可以保证高可用  一台服务器挂掉了也不影响用户的使用

















