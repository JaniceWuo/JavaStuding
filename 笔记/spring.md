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

JavaBean  用java语言编写的可重用组件。  需要一个配置文件来配置service和dao，通过读取文件中配置的内容，反射创建对象。

**不要使用相对路径和绝对路径，通过BeanFactory.class.getClassLoader().getResourceAsStream(properties文件)来获取properties文件流对象。**

通过反射机制创建对象。







