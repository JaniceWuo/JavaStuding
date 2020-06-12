#### 课程：尚硅谷SpringBoot顶尖教程  (雷丰阳)

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



#### 6/11 

#### 日志

市面上的日志框架有很多。

日志门面：选SLF4J

日志实现：选logback

SpringBoot也是选用SLF4j和logback

##### SLF4j使用

1.如何在系统中使用SLF4j

开发的时候，不应该直接调用日志的实现类，而是调用日志抽象层里面的方法。

##### 如何让系统中所有日志都统一到slf4j?

**将系统中其他日志框架先排除出去，再用中间包替换原有日志框架，导入slf4j其他的实现。**



SpringBoot的日志

底层也是使用slf4j+logback的方式进行日志记录。

SpringBoot也把其他的日志替换成了slf4j



日志级别：trace<debug<info<warn<error

SpringBoot默认使用的是info级别的



#### Web开发

使用SpringBoot:

创建SpringBoot应用，选中我们需要的模块。SpringBoot已经默认将这些场景配置好了，只需要在配置文件中指定少量配置就可以了。然后自己编写业务代码。

要去webjars网站找相关maven依赖。

SpringBoot对静态资源的映射规则：

1.所有/webjars/**，都去classpath:/META-INF/resources/webjars/找资源。

比如http://localhost:8081/webjars/jquery/3.5.1/jquery.js就可以访问到js资源

2.如果是自己写的静态资源，则可以放在"classpath:/META-INF/resources/"、"classpath:/resources/"、"classpath:/static/"、"classpath:/public/"这些静态资源文件夹下以及当前项目的根路径下。

3.欢迎页：静态资源文件夹下所有的index.html页面  被/**映射。

4.所有的**/favicon.ico都是在静态资源文件夹下找（但是我只能放在resources/static下才能找到）



#### 模板引擎

Jsp  Thymeleaf等

#### Thymeleaf使用&语法

只要把html放在classpath:/templates/  就能自动渲染

使用：

1.导入thymeleaf的名称空间：xmlns:th="http://www.thymeleaf.org"

语法规则：

th:text  th:if  th:href等等

表达式：

${} 获取对象的属性、调用方法

@{...}定义url

~{...}片段引用表达式

还支持各种运算：bool  数学运算等



#### SpringMVC

SpringBoot自动配置了ViewResolver

如何修改SpringBoot的默认配置？

SpringBoot在自动配置很多组件的时候，先看容器中有没有用户自己配置的（@Bean  @Component) 如果有就用用户配置的 如果没有才自动配置。

##### 扩展SpringMVC  

```java
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/janice").setViewName("success");
    }
}
```

##### 全面接管SpringMVC:

SpringBoot对SpringMVC自动配置不需要了，所有都是我们自己配

我们需要在配置类中添加@EnableWebMvc,这样就意味着所有的自动配置都失效。(不推荐这样做)

为什么@EnableWebMvc会让自动配置失效？：

因为@EnableWebMvc将WebMvcConfigurationSupport组件导入进来，导入的WebMvcConfigurationSupport只是SpringMVC最基本的功能。



#### RestfulCRUD

登录页面的国际化（点中文显示中文，点英文显示英文）：

1.编写国际化配置文件

2.使用ResourceBundleMessageSource管理国际化资源文件

（指定下自己的国际化文件路径，让SpringBoot管理：spring.messages.basename=i18n.login）

3.在页面使用fmt:message取出国际化消息

login.properties是没有配置语言时显示的；login_en.properties是英文，login_zh_CN.properties是中文。（IDEA有bug，自己写Text吧）

这样就可以根据浏览器设置的语言优先级来改变中英文。

##### 想要实现根据按钮点击来切换语言：

需要自己实现localResolver  在连接上实现区域信息

要在切换按钮那儿加个链接：th:href="@{/index.html(l='en_US')}"   然后在自己写的MyLocaleResolver里面获取l参数。

```java
@RequestMapping(value = "/user/login",method = RequestMethod.POST)和@PostMapping(value = "/user/login")一样，所以之后都用后者。
```

登录要提交用户名和密码，会写在一个form表单中，在form标签体内一定要加th:action=“@{路径}” 指明要提交的路径。然后再写个controller，路径和表单的一样，点击按钮后才会执行这个controller方法，在controller中判断用户名和密码是否符合，再return其他页面。

```html
<p style="color: red" th:text="${msg}" th:if="${not #strings.isEmpty(msg)}"></p>
```

判断msg是否为空，如果不为空才显示错误消息。

因为登录后再刷新会弹出重复提交表单的选项，所以要使用重定向。







