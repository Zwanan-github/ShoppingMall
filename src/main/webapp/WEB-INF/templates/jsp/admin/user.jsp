<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hu'hao
  Date: 2023/12/23
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
</head>
<body>
<div align="center">
    <jsp:include page="../common/logo.jsp"/>
    <jsp:include page="../common/navigator.jsp"/>
    <h3>用户管理</h3>
    <p style="color: red">${userStatus}</p>
    <table border="1" width="80%">
        <tr>
            <th>id</th>
            <th>用户名</th>
            <th>密码</th>
            <th>性别</th>
            <th>邮箱</th>
            <th>手机号</th>
            <th>地址</th>
            <th>生日</th>
            <th>简介</th>
            <th colspan="3">操作</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.gender}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <td>${user.address}</td>
                <td>${user.birthday}</td>
                <td>${user.personal}</td>
            </tr>
        </c:forEach>
        <form action="${pageContext.request.contextPath}/admin/user/operation" method="post">
            <%--用户修改删除表格--%>
            <tr>
                <td><input name="id" size="2"/></td>
                <td><input name="username" size="15"/></td>
                <td><input name="password" size="15"/></td>
                <td><input name="gender" size="2" maxlength="1"/></td>
                <td><input name="email" size="15"/></td>
                <td><input name="phone" size="15"/></td>
                <td><input name="address" size="15"/></td>
                <td><input name="birthday" size="15"/></td>
                <td><input name="personal" size="15"/></td>
                <td>
                    <button type="submit" name="method" value="delete">删除</button>
                </td>
                <td>
                    <button type="submit" name="method" value="modify">修改</button>
                </td>
                <td>
                    <button type="submit" name="method" value="add">添加</button>
                </td>
            </tr>
        </form>
    </table>
    <a href="${pageContext.request.contextPath}/admin/user/${sessionScope.userPageNumber > 1 ? sessionScope.userPageNumber - 1 : 1}">上一页</a>
    <span>第${sessionScope.userPageNumber}页, 共${sessionScope.totalUserPage}页</span>
    <a href="${pageContext.request.contextPath}/admin/user/${sessionScope.userPageNumber < sessionScope.totalProductPage ? sessionScope.userPageNumber + 1 : sessionScope.totalUserPage}">下一页</a>
    <jsp:include page="../common/bottom.jsp"/>
</div>
</body>
</html>
