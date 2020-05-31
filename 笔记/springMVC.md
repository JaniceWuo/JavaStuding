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











