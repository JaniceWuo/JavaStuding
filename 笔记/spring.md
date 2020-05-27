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

​        死亡：容器销毁，对象消亡

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

​        Autowired:自动按类型注入  如果Ioc容器中有多个类型匹配就会出错。

​        Qualifier:在按照类中注入的基础之上再按照名称注入。在给类成员注入时不能单独使用，但是在给方法参数注入时可以。

​        Resource:直接按照bean的id注入，可以单独使用

​     以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现。另外，集合类型的注入只能通过XML来实现。

​         Value:用于注入基本类型和String类型的数据。可以使用SpEL(spring中的el表达式)



##### 基于xml的ioc案例

<bean id = "随便取" class = "一般是一个impl">

<property name = "这个是类里面的方法去掉set后的首字母改小写" ref = "定义的另外一个bean的id"></property>

</bean>

用到了QueryRunner,在bean.xml中也要对其进行配置，并且设置成prototype。

代码位于Spring_study/spring_day02anno_xmlioc



##### 基于注解的案例

别忘了在bean.xml中告知spring在创建容器时要扫描的包：

```java
<context:component-scan base-package="com"></context:component-scan>
```

而且使用注解在bean.xml开头要导入的和基于xml导入的不一样。

代码位于Spring_study/spring_day02account_annoIoc



##### spring的新注解

Configuration  指定当前类是一个配置类  

ComponentScan  用于通过注解指定spring在创建容器时要扫描的包

Bean  用于把当前方法的返回值作为bean对象存入spring的ioc容器中。  如果方法有参数，那spring框架会去容器中查找有没有可用的bean对象。查找的方式和Autowired一样。

Import  用于导入其他的配置类

PropertySource  用于指定properties文件的位置  

​                属性：value 指定文件的名字和路径

使用JUnit单元测试 测试我们的配置

代码位于：Spring_study/day_02account_annoIoc_withoutxml



### 5.27   day_3

事物问题：在转账业务中，如果转出账户和转入账户的操作没有同步的话，影响很大。

用了`ThreadLocal`

代码位于day03_01account

##### 动态代理：

特点：字节码随用随创建，随用随加载

###### 基于接口的动态代理：Proxy.newProxyInstance

​       如何创建代理对象：

​        使用Proxy类中的newProxyInstance()方法。要求被代理类最少实现一个接口。

​         newProxyInstance方法的参数：

​         ClassLoader:类加载器  用于加载代理对象字节码的。固定写法

​         Class[]:字节码数组。固定写法。

​         InvocationHandler:用于提供增强的代码。 此接口的实现类都是谁用谁写。

###### 基于子类的动态代理：Enhancer.create

​        由第三方cglib库提供

​        要求被代理类不能是最终类。

##### AOP

作用：在程序运行期间，不修改源码对已有方法进行增强。

实现方式：实现动态代理



#### spring中的AOP