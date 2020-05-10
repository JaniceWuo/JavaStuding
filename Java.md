# Java

##### 1.字符串对象

```java
String s1 = "Java"  #新建一个对象

String s2 = "Java"  #并没有新建，而是指向了上一步创建的那个对象

String s3 = s2;     #也是指向已经创建好的"Java"对象

String s4 = new String("Java")  #新建了
```

##### 2.equals()和"=="的区别：equals()是判断character是否相等，而“==”是判断是不是同一个对象。

##### 3.toCharArray()返回的是array的copy

##### 4.FileInputStream 和 FileOutputStream用法例如:

```java
File file = new File("test.txt");
FileInputStream fs = new FileInputStream(file);
byte[] fileContent = new byte[(int)file.length()];  //这里只是定义了一个大小为file.length()的数组，但是没传东西
fs.read(fileContent);//到这一步才是真正将文件内容读取到了数组中  之后直接用这个数组就行了
```

##### 5.第4介绍的方法都是以数字读取的，例如文件里是'a'，读取后输出为'97'，下面这种方法是以字符串读取：

```java
File file = new File("test.txt");
FileReader fr = new FileReader(file);
char[] data = new char[(int)file.length()];
fr.read(data);
fr.close();
```

##### 6.按object删除List中元素，要用迭代器：`Iterator iter`，还要判断list中是否还有元素：`iter.hasNext()`,`if(iter.next().equals(要删除的元素))`就执行`iter.remove()`。

##### 7.Queue  :使用q.offer()添加元素。

##### 8.ArrayList和LinkedList比较：LinkedList直接插入数据更快(比如在数组最前面或者最后面插入元素)；但是如果要定位到第i个位置取出元素或者插入元素，那ArrayList快很多。

##### 9.对于HashMap定义的对象，插入元素用.put()；对于HashSet定义的对象，插入元素用.add();List定义的对象也是用add()。

##### 10.HashSet怎样判断值是否重复：

​       HashSet没有自身的实现，而是里面封装了一个HashMap，所以本质上就是判断HashMap的key是否重复。key是否重复，是由两个步骤判断的：

-  如果hashcode不一样，就是在**不同的坑里**，一定是不重复的
- 如果hashcode一样，就是在**同一个坑里**，还需要进行equals比较（如果equals一样，则是重复数据，不一样就是不同数据）。

##### 11.泛型` <? extends Hero>`是只能取不能加，因为加的话不确定到底是Hero类还是它的子类；

##### ` <? super Hero>`是只能加不能取，因为取的话如果取的是Object，就会强转成Hero失败。

##### 子类泛型不能转换为父类泛型，父类泛型也不能转换为子类泛型。

##### 12.运行时异常和非运行时异常的区别。

运行时错误是RunTimeException类或其子类，一般是由程序逻辑问题引起的，如果不进行异常处理，线程或程序将在问题处中断（或main函数处中断）;  非运行错误是RuntimeException以外的异常，类型上都属于Exception类及其子类，java编辑器要求必须对这类异常进行处理。



------

### 2020.5.6

##### 1.String 、StringBuilder、StringBuffer

String是不可变序列，因为源码里面用来存储字符数组的对象前面用了`final`。

```java
String str1 = "hello"+"java";
String str2 = "hellojava";  //str1==str2
String str3 = "hello";
String str4 = "java";
String str5 = str3 + str4;//str2!=str5
```

`StringBuilder`和`StringBuffer`都可变。

`StringBuilder`线程不安全，但是效率高；`StringBuffer`线程安全但是效率较低。

如果有如下代码：

```java
String str = "";
for(int i = 0 ;i<100;i++){
    str = str + i;
}
```

会占用大量内存，因为`String`不可变，每次循环都会产生一个新的无用对象。所以这种代码最好用`StringBuilder`。

##### 2. Servlet

Servlet就是一个接口，定义了java类的规则让其被tomcat识别。servlet要运行在tomcat或者web服务器中。

servlet的生命周期:

​      **被创建**：执行init方法   可以通过<load-on-startup>来修改init的调用时刻，如果是负数则在第一次被访问时才被调用，如果为0或者正整数，则服务器一被创建就启动。注意：init方法只会执行一次，说明一个Servlet在内存中只有一个init，导致多个用户访问时，可能存在线程安全问题。所以尽量不要在Servlet中定义成员变量，即使定义了成员变量，也不要对其修改值。

​      **提供服务**：执行service方法，执行多次。

​      **被销毁**：执行destroy方法，只执行一次。只有服务器正常关闭时才执行。是在服务器被销毁前执行，一般用于释放资源。

Servlet3.0好处：在类上使用@WebServlet注解，进行配置，就不需要创建web.xml。

Servlet有两个抽象类：GenericServlet、HttpServlet。

Servlet相关配置：

​        urlpatterns:访问路径  一个Servlet可以定义多个访问路径。这个路径有很多个写的方法，如果为("*.io")，则通过/demo.io可以访问，io也可以换成其他任意。



------

### 2020.5.7

##### 1.HTTP  

   Hyper Text Transfer Protocol 超文本传输协议

   特点：基于TCP/IP的高级协议  默认端口为80  基于请求/响应模型：一次请求对应一次响应  每次请求之间相互独立，不能交互数据。

   请求消息数据格式：请求行、请求头、请求空行、请求体（正文）

   常见的请求头：User-Agent

​                  Referer:告诉服务器，当前请求从哪里来？（可以做统计工作和防盗链）

   HTTP协议有7种请求方式，常用的有两种：GET、POST

​            GET：请求参数在请求行中，在url后面；请求的url长度是有限制的；不太安全。

​            POST：请求参数在请求体中；请求的url长度没有限制；相对安全。

​    

##### 2.数据库连接池

其实就是一个存放数据库连接的容器

好处：节约资源、用户访问高效

如果不用连接池的话，使用connection.close()就是直接丢弃了连接对象，但如果使用了连接池，就是归还而不是舍弃。

##### 3.用户登录案例

使用`Druid+Tomcat+HttpServlet+MySQL`实现用户登录，项目位于j2ee/day14_test。



------

### 2020.5.8

#### Request对象

##### 1.响应行的组成：协议/版本，响应状态码，状态码描述

​           响应状态码：服务器告诉客户端浏览器本次请求和响应的一个状态。

​                   状态码都是3位数字：

​                    1xx：服务器接收客户端消息，但没有接收完成，等待一段时间后，发送1xx码，意思是问客户端你还要不要发消息。

​                    2xx：成功  例如200

​                    3xx：重定向    代表：302（重定向）  304（访问缓存）

​                    4xx：客户端错误  404(请求的路径没有对应的资源)   405（请求方式没有对应的doxx方法）

​                    5xx: 服务器端错误   500（服务器内部出现异常）

##### 2.响应头

​           Content-Type:服务器告诉客户端本次响应体数据的格式以及编码格式。比如说字符编码是utf-8，那浏览器解析就是utf-8，如果是gbk浏览器也是gbk。

##### 3.响应体：真实传输的数据



#### Response对象  :hugs:

##### 功能：设置响应消息

​           设置响应行

​           设置响应头：`setHeader(String name,String value)`

​           设置响应体​ :获取输出流（字符和字节输出流）  使用输出流

##### 重定向(redirect)特点

​           重定向是两次请求，所以`不能使用request对象来共享数据`

​           重定向的地址栏会发生变化，且可以访问其他站点资源

##### 转发（forward）的特点   

​           转发的地址栏路径不变

​           转发只能访问当前服务器下的资源

​           转发是一次请求，可以使用request对象来共享数据

路径写法

​       1.相对路径：以.开头   使用相对路径要找到当前资源和目标资源之间的相对位置关系（./是当前目录  ../是后退一级目录）

​        2.绝对路径： （简单路径）：/day15/responseDemo2

​                            给客户端使用：需要加虚拟目录  建议使用request.getContextPath()动态获取虚拟目录

​                            给服务器使用，比如转发路径  不需要加虚拟目录



##### 服务器输出字符数据到浏览器，要注意乱码问题，用`response.setContentType("text/html;charset=utf-8");`

​                                         

------

### 5.9  ​ ​ :blue_heart::accept:

#### ServletContext对象  

​        代表整个web应用，可以和程序的容器（服务器）来通信。

​        获取：1.通过request对象来获取        2.通过HttpServlet获取

​        功能：1.获取MIME类型  MIME是互联网通信过程中定义的一种文件数据类型

​                    2.域对象：共享数据   对象范围：所有用户所有请求的数据（所以需谨慎使用）

​                    3.获取文件的真实（服务器）路径

###### 案例：文件下载 ——点击超链接完成图片下载

​          分析：超链接指向的资源如果能被浏览器解析，则在浏览器中展示，如果不能解析，则弹出下载提示框。但是我们的需求是任何资源都必须弹出下载提示框。

​          方法：

​          1.编辑超链接href属性指向servlet，传递资源名称filename

​          2.定义servlet: 获取文件名称，加载文件进内存（涉及到上面说的文件真实路径），指定response的响应头，将数据写出到response输出流

​          问题：中文.jpg 有问题  不同的浏览器对中文解析不一样

​          完美解决中文问题：要加上`filename = new String(filename.getBytes("ISO8859-1"),"UTF-8");`    这句。

​          项目位于`day15_response/src/cn.itcast.web/download/DownloadServlet.java`

#### 会话技术

##### 会话：一次会话包含多次请求和响应。

注意：HTTP是无状态的，无状态就是说浏览器和服务器的一次请求和响应与其他的请求响应无关。

会话的功能：在一次会话的范围内的多次请求间，共享数据。

会话的方式：客户端会话数据：cookie       服务器会话数据：session

##### Cookie的特点和作用：

特点： Cookie存储数据在客户端浏览器

​             对于单个Cookie存储大小有限制

作用：一般用于存储少量的不太敏感的数据

​            在不登录的情况下完成服务器对客户端身份的识别

###### 案例：记住上一次访问时间——是否第一次访问Servlet

​           位于day16_cookie&session下的CookieTest.java



#### JSP入门 ​ ​ :hugs:

概念：java server pages:java服务器端页面

​            可以理解为一个特殊的页面，既可以定义html标签，又可以定义java代码

<%= %>是写java代码

#### Session

 概念：服务端会话技术，在`一次会话`的多次请求间共享数据，将数据保存在服务端的对象中。

服务器是通过cookie的方式确保在一次会话范围内，多次获取的session对象是同一个：第一次获取session时会创建一个新的session对象，并且有一个id号，响应头会存JSESSIONID，下一次会话时会去检查有没有JSESSIONID。

##### 原理：Session的实现是依赖于cookie的。

细节：

#####         1.客户端关闭，服务器不关闭，两次获取的session是否是同一个？

​         默认情况下不是。如果希望相同，则创建Cookie，键为JSESSIONID，设置最大存活时间。

#####         2.客户端不关闭，服务器关闭，两次session是否为同一个？

​         不是，但是要确保数据不丢失，不然服务器重新启动的话，用户的数据就会丢失。

​          session的钝化：在服务器正常关闭之前，将session对象序列化到硬盘上

​          session的活化：在服务器启动后，将session文件转化为内存中的session对象。

​           tomcat可以完成上述。

###### 案例：验证码

​          页面有用户名、密码、验证码，`要先判断验证码`，因为如果先判断用户名和密码是否正确的话，要去数据库查询进行判断，增加了数据库的开销。

​          判断验证码：要从session中获取程序生成的验证码。

​          若验证码不正确，给用户提示信息：验证码错误 request   跳转登录页面(转发)

代码位于day16_cookie&session/servlet



### 5.10  :jack_o_lantern:

#### JSP:

​        指令  ：用于配置JSP页面，导入资源文件 <%@>

​        注释

​       内置对象





























































