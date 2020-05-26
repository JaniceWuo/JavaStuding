#### day1   5.18

##### spring概述

​       spring是全栈轻量级开源框架。

##### spring优势

​       方便解耦  简化开发

​       AOP编程的支持

​        声明式事物的支持

spring是一个基于IOC(反转控制)和AOP的结构J2EE系统的框架。

构建项目遇到问题：maven导入的mysql总是下不下来javdoc文件导致mysql缺失文件而出错。最后不得不手动导入依赖。

耦合：程序间的依赖关系。包括：类之间的依赖，方法间的依赖。

解耦：降低程序间的依赖关系。

应该要做到，编译期不依赖，运行时才依赖。

解耦思路：1.使用反射创建对象，而避免使用new关键字。2.通过配置文件来获取要创建的对象类名。

JavaBean  用java语言编写的可重用组件。  需要一个配置文件来配置service和dao，通过读取文件中配置的内容，反射创建对象。配置文件可以是xml和properties。

**不要使用相对路径和绝对路径，要通过BeanFactory.class.getClassLoader().getResourceAsStream(properties文件)来获取properties文件流对象。**

resources文件夹下的文件最后会成为每个文件夹下的文件。

通过反射机制创建对象。



### 5.21

使用spring的IOC解决程序耦合

问题：maven还是没有成功  就算导入了完整jar包  还是用不了

5.22解决该问题，将maven5.6.3版本换为5.5.3就好了(orz....这是什么鬼bug  耽误我整整两天 而且在网上也找不到解决方案)



### 5.23

ApplicationContext：在构建核心容器时，创建对象采取的策略是采用立即加载的方式。只要一读完配置文件马上就创建配置文件中配置的对象。（单例对象使用）

BeanFactory: 创建对象采取的是延迟加载方式。什么时候根据id获取对象了，什么时候才真正创建对象。（多例对象使用）

##### spring对bean的管理细节

创建bean的三种方式：

​        1.使用默认构造函数创建

​        2.使用普通工厂中的方法创建对象

​        3.使用工厂中的静态方法创建对象

##### bean标签的scope属性：

​         singleton:单例的

​         prototype:多例的

​         request:作用于web应用的请求范围

​         session:作用于web应用的会话范围

​         global session:作用于集群环境的会话范围（就是有多台服务器，想让他们在一个session下）



### 5.25

##### ApplicationContext的三个常用实现类：

ClassPathXmlApplicationContext:它可以加载类路径下的配置文件，要求配置文件必须在类路径下，不在的话，加载不了。

FileSystemXmlApplicationContext,加载磁盘任意路径下的配置文件。（不推荐，文件可能被别人删掉）

AnnotationConfigApplicationContext 用于读取注释创建容器。



##### bean对象的生命周期

单例对象：

​        出生：当容器创建时，对象出生

​        活着：只要容器还在，对象一直活着

​        死亡：容器销毁，对象向往

所以单例对象的生命周期和容器相同

多例对象：

​         出生：当我们使用对象时，spring框架为我们创建

​         活着：对象在使用过程中就一直活着

​         死亡：当对象长时间不用且没有别的对象引用时，由java的垃圾回收机制回收



##### 依赖注入：

有三种数据：

​        基本类型和String

​        其他bean类型（在配置文件中或者注解配置过的bean，在构造函数方法中通过ref使用)

​        复杂类型/集合类型 

有三种注入方式：

​         使用构造函数提供

​         使用set方法提供（更常用）

​         使用注解提供



#### Spring第二天

##### spring注解：

注入对象的：

​      Component:用于把当前对象存入spring容器中，如果没有设置value那就是默认为`类名的首字母改小写`

​       Controller:一般用在表现层

​       Service:一般用在业务层

​       Repository:一般用于持久层

注入数据的：

​        Autowired:自动按类型注入







