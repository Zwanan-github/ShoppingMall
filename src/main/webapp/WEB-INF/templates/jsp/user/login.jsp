<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>登录窗口</title>
    </head>

    <body>
        <div align="center">
            <jsp:include page="../common/logo.jsp"/>
            <jsp:include page="../common/navigator.jsp"/>
            <h3>用户登录</h3>
            <form action="${pageContext.request.contextPath}/user/doLogin" method="get">
                <table width="200" border="1" cellpadding="5" bordercolor="green"  Style="border-collapse:collapse">
                    <tr>
                        <td colspan="2" align="center"><a href="${pageContext.request.contextPath}/user/register">注册新用户</a></td>
                    <tr>
                        <td>用户名</td>
                        <td><input type="text" name="username" value="${username}" size="50"></td>
                    </tr>
                    <tr>
                        <td>密码</td>
                        <td><input type="password" name="password" value="${password}" size="50"></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" name="submit" value="登录"> </td>
                    </tr>
                </table>
            </form>
            <c:if test="${null != loginStatus}"><p style="color: red">${loginStatus}</p></c:if>
          <jsp:include page="../common/bottom.jsp"/>
        </div>
    </body>
</html>
