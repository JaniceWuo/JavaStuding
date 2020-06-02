<%--
  Created by IntelliJ IDEA.
  User: deii
  Date: 2020/5/31
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--常用的注解--%>
    <a href="anno/testRequestParam?name=haha">RequestParam</a>
    <br/>

    <a href="anno/testPathVariable/10">PathVariable</a>

    <br/>
    <a href="anno/testCookieValue">CookieValue</a>

    <br/>
    <form action="anno/testModelAttribute" method="post">
        用户姓名：<input type="text" name="uname"><br/>
        用户年龄：<input type="text" name="age"><br/>
        <input type="submit" value="提交">
    </form>
    <br/>

    <a href="anno/testSessionAttributes">SessionAttributes</a>
<br/>
    <a href="anno/getSessionAttributes">getSessionAttributes</a>
<br/>
    <a href="anno/delSessionAttributes">delSessionAttributes</a>



</body>
</html>
