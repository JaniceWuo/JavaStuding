<%--
  Created by IntelliJ IDEA.
  User: deii
  Date: 2020/5/31
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.5.1.min.js"></script>
    <script>
        //绑定单击事件
        $(function () {
            $("#btn").click(function () {
                // alert("hello btn");
                $.ajax({
                    url:"user/testAjax",
                    contentType:"application/json;charset=utf-8",
                    data:'{"username":"hehe","password":"123","age":30}',
                    dataType:"json",
                    type:"post",
                    success:function(data){
                        alert(data);
                        alert(data.username);
                        alert(data.password);
                        alert(data.age);
                    }

                });
            });
        });
    </script>
</head>
<body>
    <a href="user/testString">testString</a>
    <br/>
    <a href="user/testVoid">testVoid</a>
    <br/>
    <a href="user/testModelAndView">ModelAndView</a>
    <br/>
    <a href="user/testForwardOrRedirect">ForwardOrRedirect</a>
    <br/>
    <button id = "btn">发送ajax请求</button>
</body>
</html>
