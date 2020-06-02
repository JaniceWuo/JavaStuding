<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<h3>传统文件上传</h3>

<form action="user/fileUpload1" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload">  <br/>
    <input type="submit" value="上传">
</form>

<h3>springMVC文件上传</h3>

<form action="user/fileUpload2" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload">  <br/>
    <input type="submit" value="上传">
</form>

<h3>跨服务器上传</h3>
<form action="user/fileUpload3" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload">  <br/>
    <input type="submit" value="上传">
</form>

</body>


</html>
