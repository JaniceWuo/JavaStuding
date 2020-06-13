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

#### 登录功能实现

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

**这里还有一个问题，就是直接访问那个main页面不进行登录的话，也是可以访问成功，所以要用拦截器进行登录检查。**

```java
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if(user == null){
            //表示未登录  返回登录页面
            request.setAttribute("msg","没有权限，请先登录！");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else {
            return true;
        }
    }
}
```

还要在config里面配置才能生效。(springboot2.*也要过滤掉静态资源，否则就被拦截而没有样式)

```java
@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
            .excludePathPatterns("/index.html","/","/user/login","/asserts/**","/webjars/**");
}
```

整个逻辑应该是在controller中验证用户名和密码是否正确，如果正确，要在session中存储用户名然后重定向到登录后的页面：

```java
@Controller
public class LoginController {
//    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名和密码错误");
            return "login";
        }

    }
}
```

在LoginHandlerInterceptor中用request.getSession().getAttribute（）去获取是否存的有用户名，如果有，说明登录了则返回true，如果没有说明没登录就设置一个"msg"并且转发到登录页面。

在登录后的页面上要显示已登录的用户名的话，要用到thymeleaf取出session中存储的用户名：[[${session.loginUser}]]

#### CRUD-员工列表

CRUD要满足Rest风格:

查询：emp--GET

添加：emp--POST

修改：emp/{id}--PUT

删除：emp/{id}--DELETE

实验的请求架构：

| 实验功能                             | 请求URI | 请求方式 |
| ------------------------------------ | ------- | -------- |
| 查询所有员工                         | emps    | GET      |
| 查询某个员工(来到修改页面)           | emp/1   | GET      |
| 来到添加页面                         | emp     | GET      |
| 添加员工                             | emp     | POST     |
| 来到修改页面（查出员工进行信息回显） | emp/1   | GET      |
| 修改员工                             | emp     | PUT      |
| 删除员工                             | emp/1   | DELETE   |

所以查询所有员工数据是emps发GET请求，则在html对应标签体内写th:href="@{/emps}"表示路径是要拼接上"/emps"。

##### thymeleaf公共页面元素抽取：

（当几个页面有公共元素时，共用一个就好，将它抽取出来  可以把代码都重新放在一个新的html中）

用th:fragment="取个名字" 抽取   

有三种方式引用所抽取的公共元素：

​        th:insert=“” 将公共片段整个插入到声明引入的元素中

​        th:replace=“”  替换

​        th:include=“”  包含

也可以直接给个id比如“sidebar” 然后用选择器<div th:replace="~{dashboard::#sidebar}"></div>

##### 添加员工：

要先到添加员工页面，然后进行添加。

springMVC会自动将请求参数和入参的属性进行一一绑定  所以要求请求参数的名字和javaBean入参的属性名一致（意思是表单提交的会自动封装到参数对象）

日期提交的格式有很多种，涉及格式化。默认按照/的方式。

##### 修改员工：

需要先到修改页面  修改页面路径为@GetMapping("/emp/{id}")

要获取所修改的员工的id   将路径的参数"id"封装：@PathVariable("id") Integer id

选中部门id：th:selected="${dept.id == emp.department.id}" 就是在遍历每个部门id的时候，要是它和当前编辑员工的id相同就显示它。

由于添加员工和修改员工都是到add页面，所以add.html中要判断是哪种。

（因为添加员工时emp是空的，而修改员工才有emp，所以表单th:value里面要判断一下当前emp是否为空）

还要注意表单只有get和post，而修改员工是put请求，所以要加上：

```java
<input type="hidden" name="_method" value="put" th:if="${emp!=null}"/>
```

意思是只有是修改请求时才会创建该标签。

还有就是在save方法中当没有获取员工的id时，就会选择自增的方式。而在修改员工信息时如果不获取他的id，就会自增id然后当成一个新的用户存入。所以修改员工时要获取id：

```java
<input type="hidden" name="id" th:if="${emp!=null}" th:value="${emp.id}"> 
```

##### 删除员工：

在application.properties中要加上：

```java
spring.mvc.hiddenmethod.filter.enabled=true
```

否则会报post错误。

#### 错误处理机制













