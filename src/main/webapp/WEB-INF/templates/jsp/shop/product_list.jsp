<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hu'hao
  Date: 2023/12/14
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>商城模块</title>
    <style type="text/css">
        .product_line {
            width: 100%;
            height: 48%;
            margin: 6px 3px 6px 3px;
        }
        .product_line > .col {
            display: inline-block;
            text-align: left;
            width: 24%;
            height: 100%;
            border: rgb(128, 128, 128) solid 1px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<center>
    <jsp:include page="../common/logo.jsp"/>
    <jsp:include page="../common/navigator.jsp"/>
    <h3>商城模块</h3>
    <c:if test="${null == sessionScope.curUser}">
        <span style="color: red">尚未登录，登录可购买商品和加入购物车，</span><a
            href="${pageContext.request.contextPath}/user/login">去登录</a>
    </c:if>
    <span style="color: red">${productStatus}</span>
    <br>
    <a href="${pageContext.request.contextPath}/shop/all/1">全部</a>
    <%--category下拉框--%>
    <c:forEach var="category" items="${categories}">
        <a href="${pageContext.request.contextPath}/shop/${category}/1">${category}</a>
    </c:forEach>
    <form action="${pageContext.request.contextPath}/shop/product" class="content"
          style="width: 970px; height: 650px; border: black solid 1px">
        <div class="product_line">
            <c:forEach begin="0" end="3" var="product" items="${products}">
                <div class="col">
                    <div class="product_img"
                         style="width: 100%;height: 70%;border-bottom: rgb(128, 128, 128) solid 1px;
                                 background: url('${product.pic}') no-repeat center;background-size: contain;">
                    </div>
                    <span style="margin-left: 5px; font-weight: bold">商品: </span>${product.productName}
                    <br>
                    <span style="margin-left: 5px; font-weight: bold">价格: </span>${product.price}<br>
                    <span style="margin-left: 5px; font-weight: bold">剩余: </span>${product.number}<br>
                    <a href="/shop/detail/${product.id}" style="float: right">详情>></a>
                    <button type="submit" name="buy" value="${product.id}" style="margin-left: 3px">购买</button>
                    <button type="submit" name="cart" value="${product.id}" style="margin-left: 3px">加购物车</button>
                </div>
            </c:forEach>
        </div>
        <div class="product_line">
            <c:forEach begin="4" end="8" var="product" items="${products}">
                <div class="col">
                    <div class="product_img"
                         style="width: 100%;height: 70%;border-bottom: rgb(128, 128, 128) solid 1px;
                                 background: url('${product.pic}') no-repeat center;background-size: contain;">
                    </div>
                    <span style="margin-left: 5px; font-weight: bold">商品: </span>${product.productName}<br>
                    <span style="margin-left: 5px; font-weight: bold">价格: </span>${product.price}<br>
                    <span style="margin-left: 5px; font-weight: bold">剩余: </span>${product.number}<br>
                    <a href="/shop/detail/${product.id}" style="float: right">详情>></a>
                    <button type="submit" name="buy" value="${product.id}" style="margin-left: 3px">购买</button>
                    <button type="submit" name="cart" value="${product.id}" style="margin-left: 3px">加购物车</button>
                </div>
            </c:forEach>
        </div>
    </form>
    <%--分页组件--%>
    <a href="${pageContext.request.contextPath}/shop/${sessionScope.productKeyword}/${sessionScope.productPageNumber > 1 ? sessionScope.productPageNumber - 1 : 1}">上一页</a>
    <span>第${sessionScope.productPageNumber}页, 共${sessionScope.totalProductPage}页</span>
    <a href="${pageContext.request.contextPath}/shop/${sessionScope.productKeyword}/${sessionScope.productPageNumber < sessionScope.totalProductPage ? sessionScope.productPageNumber + 1 : sessionScope.totalProductPage}">下一页</a>
    <jsp:include page="../common/bottom.jsp"/>
</center>
</body>
</html>
