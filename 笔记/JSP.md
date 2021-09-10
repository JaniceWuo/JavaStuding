### 什么是JSP

Java Server Pages 是java服务器页面。

每个jsp文件的开头：

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
```

如果要在页面直接显示HelloWorld:

```jsp
<%
    out.println("HelloWorld！");
%>
```

在html中，<form action="xx.jsp">的意思是表单提交到xx.jsp文件中。



#### 表达式

`<%=表达式%>`

例如：<%= (new java.util.Date()).toLocaleString()%>



引用java的一些包：

`<%@ page import="java.util.*" %>`



如果想获取某个页面被访问的次数，需要添加一个页面统计器。

```jsp
<body>
<%
    Integer count = (Integer) application.getAttribute("count");
    if(count==null || count==0){
        out.println("欢迎第一次访问本页面");
        count = 1;
    }else{
        out.println("欢迎再次访问本页面");
        count+=1;
    }
    application.setAttribute("count",count);
%>
<p>本页面访问统计量：<%=count%></p>
</body>
```

如果是要知道每个页面的访问量，就要把以上代码添加到所有页面中。

但是有个问题，一旦web服务器重启，以上数据会清零。如果想保存，可以建一个表，将count写入表中，然后每次访问都读取表的这个字段，然后自增。

但是！个贷系统是多台服务器，多线程？ 会造成访问冲突？

是给每个jsp页面所在的表里面增加一个count字段吧？（好像不可行 数据表都是设计好了的 不容改变 那增加一个数据表 name值存这个页面的功能名或者按钮名）

访问数据肯定要实时存储，所以用消息队列存着？

乐观锁？通过版本号来解决。线程一对字段++后，线程二就要重新获取版本号才能++。

通过`document.getElementById('...').innerText`

获取按钮的名字，然后存到数据库里，对应的count也存进去，每次都+1，利用乐观锁实现多线程。



















