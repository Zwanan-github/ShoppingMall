<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hu'hao
  Date: 2023/12/18
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的订单</title>
    <style type="text/css">
        .content {
            margin-top: 20px;
        }
        .content table {
            text-align: center;
        }
        .content table tr th {
            background-color: #e8e8e8;
        }
        .content table tr td {
            background-color: #f8f8f8;
        }
    </style>
</head>
<body>
<center>
    <jsp:include page="../common/logo.jsp"/>

    <jsp:include page="../common/navigator.jsp"/>
    <h3>我的订单</h3>
    <c:if test="${null == sessionScope.curUser}">
        <span style="color: red">尚未登录，登录可查看和管理订单，</span><a
            href="${pageContext.request.contextPath}/user/login">去登录</a><br>
    </c:if>
    <span style="color: red">${orderStatus}</span>
    <form action="${pageContext.request.contextPath}/shop/order/clear" class="content" style="width: 970px; height: 630px; border: black solid 1px">
        <!-- 订单号，商品名，价格，时间 -->
        <table border="1" style="width: 100%; height: 100%">
            <tr>
                <th>订单号</th>
                <th>商品名</th>
                <th>价格</th>
                <th>时间</th>
                <th colspan="1">操作</th>
            </tr>
            <!-- 填充8行，不论够不够 -->
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.product.productName}</td>
                    <td>${order.product.price}</td>
                    <td>${order.submitTime}</td>
                    <td>
                        <button type="submit" name="id" value="${order.id}" style="margin-left: 3px">删除</button>
                    </td>
                </tr>
            </c:forEach>
            <c:forEach begin="${orders.size() + 1}" end="8" varStatus="status">
                <tr>
                    <td colspan="5">-</td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <%--分页组件--%>
    <a href="${pageContext.request.contextPath}/shop/order/${sessionScope.orderPageNumber > 1 ? sessionScope.orderPageNumber - 1 : 1}">上一页</a>
    <span>第${sessionScope.orderPageNumber}页, 共${sessionScope.totalOrderPage}页</span>
    <a href="${pageContext.request.contextPath}/shop/order/${sessionScope.orderPageNumber < sessionScope.totalProductPage ? sessionScope.orderPageNumber + 1 : sessionScope.totalOrderPage}">下一页</a>
    <jsp:include page="../common/bottom.jsp"/>
</center>
</body>
</html>
