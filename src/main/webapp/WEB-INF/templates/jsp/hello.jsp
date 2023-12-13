<%--
  Created by IntelliJ IDEA.
  User: hu'hao
  Date: 2023/12/13
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
hello<br>
<c:forEach var="user" items="${users}">
    <li>${user}</li>
</c:forEach>
</body>
</html>
