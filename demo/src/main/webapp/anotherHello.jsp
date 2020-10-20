<%--
  Created by IntelliJ IDEA.
  User: iulia
  Date: 18.10.2020
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Another Hello</title>
    </head>
    <body>
        <h1>Hello <%= request.getParameter("name")%></h1>
    </body>
</html>
