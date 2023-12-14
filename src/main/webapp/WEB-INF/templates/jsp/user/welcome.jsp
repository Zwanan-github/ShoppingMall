
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>登录成功</title>
    </head>

    <body>
        <div align="center">
            <jsp:include page="../common/logo.jsp"/>
            <jsp:include page="../common/navigator.jsp"/>
            <h2>登录成功</h2>
            欢迎你，<font color="blue">${sessionScope.curUser.username}</font>，你已经登录成功，可以进行其他的操作了。
        </div>

    </body>
</html>
