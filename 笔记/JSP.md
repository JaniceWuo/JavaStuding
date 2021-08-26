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