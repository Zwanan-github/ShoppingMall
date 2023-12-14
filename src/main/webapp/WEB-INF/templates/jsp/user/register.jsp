<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>注册窗口</title>
    </head>

    <body>
    <div align="center">
        <jsp:include page="../common/logo.jsp"/>
        <jsp:include page="../common/navigator.jsp"/>
        <h3>注册</h3>
        <form action="${pageContext.request.contextPath}/user/doRegister" method="post">
            <table width="200" border="1" cellpadding="5" bordercolor="green"  Style="border-collapse:collapse">
                <tr>
                    <td colspan="2" align="center"><a href="${pageContext.request.contextPath}/user/login">返回登录窗口</a></td>
                <tr>
                    <td>用户名</td>
                    <td><input type="text" name="username" value="${username}" size="50"></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input type="password" name="password1" value="${password1}" size="50"></td>
                </tr>
                <tr>
                    <td>确认密码</td>
                    <td><input type="password" name="password2" value="${password2}" size="10"></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="text" name="email" value="${email}" size="10"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" name="submit" value="提交注册信息"> </td>
                </tr>
            </table>
        </form>
        <p style="color: red">${registerStatus}</p>
    </div>
</body>
</html>
