<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: deii
  Date: 2020/5/9
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<%--  <%=new Date().toString()%>--%>
<%
    List<String> words = new ArrayList<String>();
    words.add("today");
    words.add("is");
    words.add("a");
    words.add("great");
    words.add("day");
%>

<table width="200px" align="center" border="1" cellspacing="0">

    <%for (String word : words) {%>

    <tr>
        <td><%=word%></td>
    </tr>

    <%}%>

</table>
<jsp:include page="footer.jsp">
    <jsp:param  name="year" value="2017" />
</jsp:include>
  </body>
</html>
