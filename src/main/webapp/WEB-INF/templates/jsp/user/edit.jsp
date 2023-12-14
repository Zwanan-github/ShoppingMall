<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>修改个人信息</title>
    </head>
    <body>
        <center>
            <jsp:include page="../common/logo.jsp"/>
            <jsp:include page="../common/navigator.jsp"/>
            <h3>我的用户信息</h3>
            <form action="${pageContext.request.contextPath}/user/doEdit" method="post">
                <table border="1" width="970" cellpadding="5" bordercolor="green"  Style="border-collapse:collapse">
                    <tr>
                        <td align="center" height="25" bgcolor="#EFEFEF" colspan="4" style="color: red">${editStatus}</td>
                    </tr>
                    <tr>
                        <td width="200"rowspan="9">-</td>
                        <td width="25%">用户ID</td>
                        <td align="left">${sessionScope.curUser.id}</td>
                        <td width="200"rowspan="9">-</td>
                    </tr>
                    <tr>
                        <td>用户名</td>
                        <td align="left">${sessionScope.curUser.username}</td>
                    </tr>
                    <tr>
                        <td>用户密码</td>
                        <td align="left"> <input name="password" type="text" value="${sessionScope.curUser.password}"size="35" maxlength="35" />
                            *必填</td>
                    </tr>
                    <tr>
                        <td>性别</td>
                        <td align="left">
                            <input name="gender" type="radio" value="男"
                                    <c:if test="${'男'.equals(sessionScope.curUser.gender)}">
                                        checked
                                    </c:if>
                            />男
                            <input name="gender" type="radio" value="女"
                                    <c:if test="${'女'.equals(sessionScope.curUser.gender)}">
                                        checked
                                    </c:if>
                            />女
                        </td>
                    </tr>
                    <tr>
                        <td>出生日期</td>
                        <td align="left"><input id="birthday" name="birthday" type="text" value="${sessionScope.curUser.birthday}" size="25" maxlength="12" />例：19890127</td>
                    </tr>
                    <tr>
                        <td>电子邮箱</td>
                        <td align="left"><input name="email" type="text" value="${sessionScope.curUser.email}" size="35" maxlength="20" /></td>
                    </tr>
                    <tr>
                        <td>电话号码</td>
                        <td align="left"><input name="phone" type="text" value="${sessionScope.curUser.phone}" size="35" maxlength="15" /></td>
                    </tr>
                    <tr>
                        <td>地址</td>
                        <td align="left"><input name="address" type="text" value="${sessionScope.curUser.address}"size="35" maxlength="35" /></td>
                    </tr>
                    <tr>
                        <td>个人说明</td>
                        <td align="left"><textarea name="personal" cols="50" rows="5">${sessionScope.curUser.personal}</textarea></td>
                    </tr>
                    <tr>
                        <td  height="25" align="center" bgcolor="#EFEFEF" colspan="4"><input type="submit" name="Submit" value="提交修改"></td>
                    </tr>
                </table>
            </form>
            <jsp:include page="../common/bottom.jsp"/>
        </center>
    </body>
</html>

