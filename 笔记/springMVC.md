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



@RequestMapping注解既可以写在类上也可以写在方法上，写在类上就会多一个父路径。











