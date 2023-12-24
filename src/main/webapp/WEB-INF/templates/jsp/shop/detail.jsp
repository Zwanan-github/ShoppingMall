<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hu'hao
  Date: 2023/12/23
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>详情</title>
</head>
<body>
<center>
    <jsp:include page="../common/logo.jsp"/>
    <jsp:include page="../common/navigator.jsp"/>
    <h3>${product.productName}</h3>
    <c:if test="${null == sessionScope.curUser}">
        <span style="color: red">尚未登录，登录可购买商品和加入购物车，</span><a
            href="${pageContext.request.contextPath}/user/login">去登录</a>
    </c:if>
    <span style="color: red">${productStatus}</span>
    <form action="${pageContext.request.contextPath}/shop/product" class="content"
          style="width: 970px; height: 630px; border: black solid 1px">
        <%--左边商品图片， 右边是详情和两个按钮--%>
        <div style="width: 400px; height: 600px; float: left">
            <img src="${pageContext.request.contextPath}${product.pic}" width="500px" height="600px"/>
        </div>
        <div style="padding-top: 50px; width: 450px; height: 600px; float: left">
            <table>
                <tr>
                    <td style="font-weight: bolder; font-size: larger">商品名称：</td>
                    <td>${product.productName}</td>
                </tr>
                <hr/>
                <tr>
                    <td style="font-weight: bolder; font-size: larger">商品价格：</td>
                    <td>${product.price}</td>
                </tr>
                <tr>
                    <td style="font-weight: bolder; font-size: larger">商品库存：</td>
                    <td>${product.number}</td>
                </tr>
                <tr>
                    <td style="font-weight: bolder; font-size: larger">商品描述：</td>
                    <td>${product.memo}</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="hidden" name="view" value="detail"/>
                        <button type="submit" name="buy" value="${product.id}" style="margin-left: 3px">购买</button>
                        <button type="submit" name="cart" value="${product.id}" style="margin-left: 3px">加购物车</button>
                    </td>
                </tr>
            </table>
        </div>
    </form>
    <jsp:include page="../common/bottom.jsp"/>
</center>
</body>
</html>
