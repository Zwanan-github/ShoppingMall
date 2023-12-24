<%--
  Created by IntelliJ IDEA.
  User: hu'hao
  Date: 2023/12/23
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商城系统后台页面</title>
</head>
<body>
<div align="center">
    <jsp:include page="../common/logo.jsp"/>
    <h3>管理员登录</h3>
    <form action="${pageContext.request.contextPath}/admin/doLogin" method="get">
        <table width="200" border="1" cellpadding="5" bordercolor="green"  Style="border-collapse:collapse">
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
    <p style="color: red">${loginStatus}</p>
    <jsp:include page="../common/bottom.jsp"/>
</div>
</body>
</html>
