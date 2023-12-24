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
    <title>商品管理</title>
</head>
<body>
<center>
    <jsp:include page="../common/logo.jsp"/>
    <jsp:include page="../common/navigator.jsp"/>
    <h3>商品管理</h3>
    <p style="color: red">${productStatus}</p>
    <a href="${pageContext.request.contextPath}/admin/product/all/1">全部</a>
    <%--category下拉框--%>
    <c:forEach var="category" items="${categories}">
        <a href="${pageContext.request.contextPath}/admin/product/${category}/1">${category}</a>
    </c:forEach>
    <table border="1" width="80%">
        <tr>
            <th>id</th>
            <th>商品名称</th>
            <th>商品价格</th>
            <th>商品描述</th>
            <th>商品图片</th>
            <th>商品类型</th>
            <th>商品库存</th>
            <th colspan="3">操作</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.id}</td>
                <td>${product.productName}</td>
                <td>${product.price}</td>
                <td>${product.memo}</td>
                <td>${product.pic}</td>
                <td>${product.category}</td>
                <td>${product.number}</td>
            </tr>
        </c:forEach>
        <form action="${pageContext.request.contextPath}/admin/product/operation" method="post">
            <%--用户修改删除表格--%>
            <tr>
                <td><input name="id" size="2"></td>
                <td><input name="productName" size="15"></td>
                <td><input name="price" size="15"></td>
                <td><input name="memo" size="15"></td>
                <td><input name="pic" size="15"></td>
                <td><input name="category" size="15"></td>
                <td><input name="number" size="15"></td>
                <td colspan="3">
                    <button type="submit" name="method" value="delete">删除</button>
                    <button type="submit" name="method" value="modify">修改</button>
                    <button type="submit" name="method" value="add">添加</button>
                </td>
            </tr>
        </form>
    </table>
    <%--分页组件--%>
    <a href="${pageContext.request.contextPath}/admin/product/${sessionScope.productKeyword}/${sessionScope.productPageNumber > 1 ? sessionScope.productPageNumber - 1 : 1}">上一页</a>
    <span>第${sessionScope.productPageNumber}页, 共${sessionScope.totalProductPage}页</span>
    <a href="${pageContext.request.contextPath}/admin/product/${sessionScope.productKeyword}/${sessionScope.productPageNumber < sessionScope.totalProductPage ? sessionScope.productPageNumber + 1 : sessionScope.totalProductPage}">下一页</a>
    <jsp:include page="../common/bottom.jsp"/>
</center>
</body>
</html>
