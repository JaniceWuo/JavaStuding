## 5.30

服务端分成三层框架：

表现层(springMVC)  业务层(spring框架)  持久层(MyBatis)

(面试可能会问到Struts2和SpringMVC的比较：SpringMVC的入口是Servlet，而Struts2是Filter。SpringMVC是基于方法设计的，而Struts2是基于类。)



案例：入门程序   

web.xml: 配置servlet  

​                 DispatcherServlet对象创建

​                 springmvc.xml加载、 写的类被创建为对象

 

springMVC框架基于组件方式执行流程。

SpringMVC的三大组件：处理器映射器、处理器适配器、视图解析器       

DispatcherServlet：前端控制器   是整个流程控制的中心，由它调用其他组件处理用户的请求

HandlerMapping：处理器映射器   负责根据用户请求找到Handler即处理器

Handler：处理器   对具体用户请求进行处理

HandlerAdapter：处理器适配器

View Resolver：视图解析器

View：视图   显示数据 渲染样式



@RequestMapping注解既可以写在类上也可以写在方法上，写在类上就会多一个父路径。里面有很多属性：value,path,params,header

其中value和path作用一样，用于指定请求的URL,

method用于指定请求方式。

如果加了params，那就要在超链接地址后面拼接"?参数="

注意a href后面的地址最前面不要加斜杠。



### 5.31

##### 请求参数的绑定

如果参数较多，就用Javabean封装。注意自定义类、List、Map的调用。

表单数据要封装成功的话，表单的name值必须和定义的类里面的属性名称一样。

还要处理中文乱码问题。（在web.xml中配置过滤器）



注解讲解

1.RequestParam注解

2.RequestBody  获取请求体

3.PathVariable

4.RequestHeader  获取请求头的值 比如Accept

5.CookieValue

6.ModelAttribute  可以让某个方法先执行（比如说User类中有三个属性，但是form表单只有两个）

7.SessionAttributes  用于多次执行控制器方法间的参数共享

以上为第一天内容  代码位于`springmvc_day01_01Start`



### day_2

以后都是在写的Controller.java中将数据存储到request域中，在jsp文件中就可以获取。

使用Model model,model.addAttribute()可以把对象存储到request域中;ModelAndView mv = new ModelAndView(),mv.addObject()也可以。

使用关键字方式进行重定向都不需要加request.getContextPath()，框架直接就能找到index.jsp。但是重定向不能找到WEB-INF目录下的页面。

#### springMVC实现文件上传

前提：form表单的enctype取值必须是：multipart/form-data

要配置文件解析器

**MultipartFile  名字 要跟form表单里面的name一样。**



#### 跨服务器文件上传

应用服务器   图片服务器

代码位于fileUploadServer和springmvc_day02_02_fileupload

有个问题就是配置两个tomcat不行，只能在原有的基础上多部署一个项目。

#### SpringMVC异常处理

web会抛异常给前端控制器  如果前端控制器没有异常处理器则会抛给浏览器，浏览器就会显示错误提示页面   这是很不友好的。应该要跳到一个写好的友好界面。

异常处理器要实现一个接口：HandlerExceptionResolver

代码位于springmvc_day02_03_exception

#### SpringMVC拦截器

过滤器是servlet规范中的一部分  任何Java web工程都能用

拦截器是SpringMVC框架自己的  只有使用了SpringMVC框架的工程才能用

拦截器不能对jsp/html/css/js进行拦截，只会拦截访问的控制器方法。

编写拦截器类，实现HandlerInterceptor接口   如果实现了里面的preHandle方法，则会先执行它，最后返回true表示放行，false表示不放行。



### day_3  :blonde_woman:

SSM

搭建整合环境：SSM整合可以使用多种方式

表现层：SpringMVC框架

业务层：Spring框架

持久层：MyBatis框架

整合思路：

​        1.先搭整合环境

​        2.先把spring配置搭建完成

​        3.用Spring整合SpringMVC和MyBatis















