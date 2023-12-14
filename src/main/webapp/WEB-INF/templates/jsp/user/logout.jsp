<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
    <head>
        <title>退出登录</title>
    </head>

    <body>
        <div align="center">
            <h3>退出登录</h3>
            <font color="blue"></font>${logout_name} 已经退出登录<br><br>
            <a href="${pageContext.request.contextPath}/">返回首页</a>
        </div>
    </body>

</html>

