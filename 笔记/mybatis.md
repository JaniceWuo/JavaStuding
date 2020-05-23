### day01  入门

框架封装了很多细节

Ioc  反转控制   AOP 面向切面编程

##### mybatis概述

是一个持久层框架 用java编写的  它封装了jdbc操作的很多细节，使我们只需要关注sql语句本身  它使用了ORM思想实现了结果集的封装。

ORM：Object Relation Mapping 对象关系映射

##### mybatis入门：

​       环境搭建：创建maven工程；创建实体类和dao的接口；创建mybatis主配置文件；创建映射配置文件。

​       在resources中创建directory时要一级一级创建才是多级目录。

​       mybatis的映射配置文件位置必须和dao接口的包结构相同

​       映射配置文件的操作配置，id属性的取值必须是dao接口的方法名

​       遵从上述法则后，在开发中无需再写dao的实现类，只需定义个接口







