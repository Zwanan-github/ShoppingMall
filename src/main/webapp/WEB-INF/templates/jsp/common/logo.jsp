<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
    <body>
        <c:if test="${sessionScope.curUser.role == 1}">
            <h1>商城后台管理系统</h1>
        </c:if>
        <c:if test="${null == sessionScope.curUser || sessionScope.curUser.role == 0}">
            <h1>商城系统</h1>
        </c:if>
    </body>
</html>
