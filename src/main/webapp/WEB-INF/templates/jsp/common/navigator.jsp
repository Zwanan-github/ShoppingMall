<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <table border="1" width="970" cellpadding="4" bordercolor="black"  Style="border-collapse:collapse">
        <tr height="30"bgcolor="#EFEFEF">
            <td width="200" align="center" >
                <c:if test="${sessionScope.curUser == null}">
                    [<font size="2"><a href="${pageContext.request.contextPath}/user/login">请登录</a></font>]&nbsp&nbsp[<font size="2"><a href="${pageContext.request.contextPath}/user/register"> 点此注册</a></font>]
                </c:if>
                <c:if test="${sessionScope.curUser != null}">
                    <font size="2"> 您好，</font><font color="orange">${sessionScope.curUser.username}</font>!  [<font size="2"><a href="${pageContext.request.contextPath}/user/logout">退出</a></font>]
                </c:if>
            </td>
            <%--当前是管理员用户--%>
            <c:if test="${sessionScope.curUser != null && sessionScope.curUser.role == 1}">
                <td align="left" >&nbsp&nbsp
                <font size="2">
                    <a href="${pageContext.request.contextPath}/admin/user/1">用户管理</a>·
                    <a href="${pageContext.request.contextPath}/admin/product/all/1">商品管理</a>·
                </font>
                </td>
            </c:if>
            <%--当前是普通或者未登录--%>
            <c:if test="${sessionScope.curUser == null || sessionScope.curUser.role == 0}">
                <td align="left" >&nbsp&nbsp
                <font size="2">
                <a href="${pageContext.request.contextPath}/">首页</a>·
                <a href="${pageContext.request.contextPath}/user/show">用户信息</a>·
                <a href="${pageContext.request.contextPath}/calendar">查看日历</a>·
                <a href="${pageContext.request.contextPath}/shop/all/1">商城</a>·
                <a href="${pageContext.request.contextPath}/shop/cart/1">购物车</a>·
                <a href="${pageContext.request.contextPath}/shop/order/1">订单</a>·
                <a href="${pageContext.request.contextPath}/#">栏目</a>·
                <a href="${pageContext.request.contextPath}/#">栏目</a>·
                </font>
                </td>
            </c:if>
        </tr>
    </table>
</html>
