#### 课程：尚硅谷SpringBoot顶尖教程

Spring全家桶：Spring Boot—>J2EE一站式解决方案

​                           Spring Cloud—>分布式整体解决方案

#### 微服务

是一种架构风格。一个应用应该是一组小型服务。

#### 注解：

@SpringBootApplication 说明这是一个springboot应用。里面的源码有：

```java
@EnableAutoConfiguration：开启自动配置功能。
@AutoConfigurationPackage:自动配置包  将主配置类的所在包及下面所有子包里面的所有组件扫描到Spring容器。
```



IDEA直接创建spring项目(使用Spring Initializer)，而不是创建maven。

```java
@ResponseBody
@Controller
```

和

`@RestController `作用相同。

主程序已经生成好了，只需要我们自己的逻辑。默认不支持jsp。application.properties：Spring Boot应用的配置文件，可以修改一些默认配置。

YAML：以数据为中心，比json、xml等更适合做配置文件



#### 6/10  注解

##### 1.@ConfigurationProperties和@Value

@ConfigurationProperties(prefix = "")  是告诉SpringBoot将本类中的所有属性和配置文件中的相关配置进行绑定。prefix是要告诉说哪个下面的所有属性进行一一映射。  在这个注解前要加上@Component才能生效。

@ConfigurationProperties支持松散绑定 就是last-name和lastName都对

但是@Value不支持，如果写lastName就错。

@Value不支持复杂类型封装

如果说只是需要获取一下配置文件中的某项值，则用@Value

如果说专门编写了一个javaBean来和配置文件进行映射，就使用@ConfigurationProperties

##### 2.@PropertySource和@ImportResource

```java
@PropertySource可以加载指定配置文件
@ImportResource是让Spring的配置文件生效，加载进来（不推荐使用）
```

SpringBoot推荐使用@Bean给容器添加组件，且组件的默认id就是方法名。

##### 3.配置文件占位符

1.随机数   ${random.int}等

2.占位符获取之前的配置的值，如果没有配置可以用:指定默认值

```java
person.last-name=张三${random.uuid}
person.age=${random.int}
person.birth=1998/02/29
person.lists=a,b,c
person.dog.name=${person.hello:hello}_xiaogou
person.dog.age = 3
```

##### 4.Profile多环境支持

就是可能真正开发的时候，开发、生产、测试都不是同一个环境，要用不同端口。

第一个方式：写多个Profile文件  使用spring-{名字}.properties    但是默认使用application.properties文件  想要激活某个profile：在application.properties中指定spring.profiles.active=dev/prod

第二个方式：yml  在application.yml中用多个文档块定义不同属性  就不用写那么多个文件了

第三个方式：命令行 --spring.profiles.active=dev   命令行优先级最高







