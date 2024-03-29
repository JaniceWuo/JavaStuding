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

配置一个User对象：

`<bean id="user" class="com...."></bean>`

其中id属性是唯一标识，class属性是类全路径。



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

注入对象的：（以下四个功能是一样的，都是代表将类注册到Spring容器中装配）

​      ①Component:用于把当前对象存入spring容器中，如果没有设置value那就是默认为`类名的首字母改小写`

​       ②Controller:一般用在表现层

​       ③Service:一般用在业务层(Service)

​       ④Repository:一般用于持久层（Dao）

注入数据的：

​        Autowired:自动按类型注入  如果Ioc容器中有多个类型匹配就会出错。

​        Qualifier:在按照类中注入的基础之上再按照名称注入。在给类成员注入时不能单独使用，但是在给方法参数注入时可以。

​        Resource:直接按照bean的id注入，可以单独使用

​     以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现。另外，集合类型的注入只能通过XML来实现。

​         Value:用于注入基本类型和String类型的数据。可以使用SpEL(spring中的el表达式)



##### 基于xml的ioc案例

<bean id = "随便取" class = "一般是一个impl">

<property name = "" ref = "要引用的bean的id"></property>

</bean>

用到了QueryRunner,在bean.xml中也要对其进行配置，并且设置成prototype。

代码位于Spring_study/spring_day02anno_xmlioc



如果采用的是xml的方式，那么set方法不能省略。如果是注解的方式，那么可以省略。



##### 基于注解的案例

别忘了在bean.xml中告知spring在创建容器时要扫描的包：

```java
<context:component-scan base-package="com"></context:component-scan>
```

而且使用注解在bean.xml开头要导入的和基于xml导入的不一样。

代码位于Spring_study/spring_day02account_annoIoc



##### spring的新注解

Configuration  指定当前类是Spring的核心配置类  

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

##### 动态代理：不改变源代码的基础上对已有方法增强（代理的是接口）

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

aop:before  表示配置前置通知

​        `method`属性:用于指定你要切入的方法

​        `pointcut`属性:用于指定切入点表达式，该表达式的含义指的是对业务层中哪些方法增强。

 切入点表达式写法：`execution([修饰符] 返回值类型 包名.类名.方法名(参数))`

​        可以使用通配符*代表任意

​        包名与类名之间一个点，表示当前包下的类，两个点表示当前包及其子包下的类。

​        参数列表可以使用两个点..表示任意个数、任意类型的参数列表。

​         

spring的环绕通知:

​          它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。

写aop代码，要有目标对象、切面对象（其实就是一个类），然后配置织入。



### 基于XML的AOP开发：

通知的类型：

前置通知 `<aop:before>` 指定增强的方法在切入点方法之前执行。

后置通知 `<aop:after-returning> ` 指定增强的方法在切入点方法之后执行。

环绕通知 `<aop:around>` 指定增强的方法在切入点方法之前和之后都执行。

异常抛出通知 `<aop:throwing>` 指定增强的方法在出现异常时执行。

最终通知 `<aop:after>`  无论是否有异常都会执行。

![](img\image-20210810110909520.png)

### 基于注解的AOP开发:

##### spring基于注解的AOP配置：最好使用环绕通知，不然可能调用顺序有误。

代码位于`day03_05annotationAOP `  可以看到bean.xml中内容很少。

基于注解的AOP开发，要使用@Aspect来标注切面类；一定要在配置文件中配置aop自动代理`<aop:aspectj-autoproxy/>`



### 5_28 day_4  :jack_o_lantern:

1.spring中的JdbcTemplate：

​      JdbcTemplate作用：和数据库进行交互

2.作业：

###### 将account项目改为用spring基于xml的AOP的事务控制

代码位于`day04/02account_aoptx_xml`

（注意bean标签要改为aop的）

```java
<aop:config>
        <!--配置通用切入点表达式-->
        <aop:pointcut id="pt1" expression="execution(* com.service.impl.*.*(..))"></aop:pointcut>

        <aop:aspect id="tsAdvice" ref="tsManager">
            <!--配置前置通知  开启事物-->
            <aop:before method="beginTransaction" pointcut-ref="pt1"></aop:before>
            <!--配置后置通知  提交事物-->
            <aop:after-returning method="commit" pointcut-ref="pt1"></aop:after-returning>
            <!--配置异常通知  回滚事物-->
            <aop:after-throwing method="rollback" pointcut-ref="pt1"></aop:after-throwing>
            <!--配置最终通知  释放连接-->
            <aop:after method="release" pointcut-ref="pt1"></aop:after>
        </aop:aspect>
</aop:config>
```



###### 将account改为基于注解的spring AOP:

bean.xml中内容少很多。而且类中定义的变量都用@Autowired进行赋值，不需要set方法。

本来是要在TransactionManager.java中的每个方法前面加上对应的切面注解，比如@Before，@After等等，对应上面配置的bean.xml。但是spring的基于注解调用顺序有问题，会先调用最终通知导致线程被释放，所以这里改为使用环绕通知。

```java
@Around("pt1()")
public Object aroundAdvice(ProceedingJoinPoint pjp){
        Object returnValue = null;
        try{
            Object[] args = pjp.getArgs();
            this.beginTransaction();
            returnValue = pjp.proceed(args);
            this.commit();
            return returnValue;
        }catch (Throwable e){
            this.rollback();
            throw new RuntimeException(e);
        }finally {
            this.release();
        }
    }
```

还要在bean.xml里开启注解AOP的支持：

```java
<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
```

代码见`day04/03account_aoptx_anno`



### 5.29

spring中有JdbcDaoSupport类，用extends即可调用。但是这样的话就不能用基于注解。

用了@Autowired之后，就不需要set方法了。使用@Autowired后会去bean.xml里面找有没有配置相应的<bean>标签，如果没有的话也会报错。

再次提醒<property name="">中的name属性是set方法后面首字母变小写。







#### 事务控制

事务控制也是基于AOP的  所以需要导aspectjweaver。

spring基于xml的声明式事务控制：`05tx_xml`

spring基于注解的声明式事务控制: `06tx_anno`

(如果使用注解，一个类中有多个方法的话可能会比较麻烦，但是xml可以一劳永逸)

spring基于纯注解：test.java中的@ContextConfiguration内容要改成classes=另一个java文件。代码位于:`07anno_tx_withoutxml`

（因为把@ComponentScan写成了@Component导致找不到IAccountService类的bean对象....又找错找了半个小时！！！）

<<<<<<< Updated upstream




#### 8/5  

开始学习Spring源码  :fist_oncoming:

##### 注解方式：

@Bean == 之前xml里面写的bean标签

需要有个配置类config来代替之前的xml。

@ComponentScan 指定要扫描的包



在Ioc中默认是单例。无论获取多少次，都是同一个对象。

prototype是多实例的，singleton是单实例（默认）

@Scope("prototype")就是指定为多实例。

singleton:ioc容器启动时会调用方法创建对象放到ioc容器中，以后每次获取就是直接从容器(map.get())中拿。

prototype:ioc容器启动时并不会调用方法创建对象放在容器中，而是每次获取的时候才会调用方法创建对象，所以每次获取的对象都不同。

##### @Conditional

按照一定的条件进行判断 满足条件给容器中注册bean

要写几个java类实现Condition接口  来作为不同的条件

然后在方法或者类上加上@Condition({条件.class})   就可以满足此条件的才注册组件

##### @Import

快速给容器中导入一个组件



##### 懒加载

是针对单实例bean的。

懒加载，容器启动不创建对象，第一次使用时才创建对象并初始化。

在config配置类上面加上@Lazy。



@Conditional 按照条件注册bean

@FactoryBean  默认获取的是工厂bean调用getObject创建的对象，如果要获取工厂bean本身，需要给id前面加一个&。



##### Bean的生命周期

指的是bean创建—>初始化—>销毁的过程

也可以自定义初始化和销毁方法：

   1. 通过配置文件。 <bean ..... init-method = " "  destroy-method="">

   2. 通过注解。@Bean(init-method=自己定义的init方法，destroy-method=自己定义的销毁方法)

      （注意 如果是多实例  那就算容器关闭，bean也不会销毁）

​       可以使用JSR250: @PostConstruct   @PreDestroy

​       让Bean实现InitializationBean    DisposableBean

​       实现BeanPostProcessor（后置处理器）接口的postProcessBeforeInitialization和postProcessAfterInitialization



##### @Value

赋值：基本数值  可以写SpEL#{}  可以写${}

如果要获取配置文件中的值 还要额外通过指定@PropertySource来



##### 自动装配

Spring利用依赖注入 完成对IOC容器中各个组件的依赖关系赋值

@Autowired 注解是优先按照类型来找 如果找到多个 那就按照属性名去找 比如说BookDao bookDao 就相当于调用了applicationContext.getBean("bookDao")

但是如果就想指定id 就使用@Qualifire("bookDao2")来指定找bookDao2这个id的bean。

如果找不到一个bean的话，会报错。为了不报错，可以指定@Autowired(required=false)表示非必须找到。

还可以用@Resource来注入，它是默认按照名称来注入的。比如说：

```java
@Resource
private BookDao bookDao;
```

那么就会注入@Bean(bookDao)，就算bookDao2设置了@Primary也无济于事。但是可以@Resource(name = "bookDao2")。



@Autowired可以标注在构造器、参数、方法、属性上。

接口上不加注解，因为不需要将接口加在容器中。

要记住Ioc中保存的是代理对象。



#### AOP

指在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程模式。

涉及的设计模式就是**代理模式**。

通知：前置、后置、返回@AfterReturning、异常@AfterThrowing、环绕通知@Around

抽取公共的切入点表达式：

```java
@Pointcut("execution(目标方法的全类名)")
public void pointcut(){}
```

给配置类加上@EnableAspectJAutoProxy【开启基于注解的aop模式】

要给切面类加上@Aspect注解 告诉Spring当前类是一个切面类  就是里面写了前置通知后置通知等方法的类。



##### AOP原理

先研究@EnableAspectJAutoProxy这个注解





##### 事务细节

isolation  是Isolation类型   ： 事务的隔离级别

propagation 是Propagation类型 ： 事务的传播行为

noRollbackFor  Class[]   哪些异常事务可以不回滚

noRollbackForClassName  String[]

rollbackFor  Class[]       哪些异常事务需要回滚

rollbackForClassName  String[ ]

































































=======
### 声明式事务控制

在配置文件中声明，用在Spring配置文件中声明式的处理事务来代替代码式的处理事务。

在不需要事务管理的时候，只要在设定文件上修改一下，即可移去事务管理服务，无需改变代码重新编译，维护更方便。

其底层原理就是AOP



### SSM框架整合

controller层

service层

dao层

每一层各司其职

整合MyBatis时，要将SqlSessionFactory配置到Spring容器中，在applicationcontext.xml中添加：

```xml
<bean id = "sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"></property>
</bean>
```

pom文件中要有mybatis-spring包。

还要配置接口（也就是有sql语句的）所在位置:

```xml
<bean id = "mapperScanner" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="接口所在路径"></property>
</bean>
```

>>>>>>> Stashed changes


