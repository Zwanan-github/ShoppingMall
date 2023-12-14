<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>显示用户资料</title>
    </head>
    <body>
        <center>
            <jsp:include page="../common/logo.jsp"/>

            <jsp:include page="../common/navigator.jsp"/>

            <h3>我的用户信息</h3>

            <table border="1" width="970" cellpadding="5" bordercolor="green"  Style="border-collapse:collapse">

                <tr>
                    <td  height="20" bgcolor="#EFEFEF" colspan="4">-</td>

                </tr>

                <tr>
                    <td width="200"rowspan="8">-</td>
                    <td width="20%">用户名</td>
                    <td><font color="orange">${sessionScope.curUser.username}</font></td>
                     <td width="200"rowspan="8">-</td>
                </tr>
                <tr>
                    <td>性别</td>
                    <td><font color="orange">${sessionScope.curUser.gender}</font></td>
                </tr>
                  <tr>
                    <td>生日</td>
                    <td><font color="orange">${sessionScope.curUser.birthday}</font></td>
                </tr>
                <tr>
                    <td>电子邮箱</td>
                    <td><font color="orange">${sessionScope.curUser.email}</font></td>
                </tr>
                <tr>
                    <td>电话号码</td>
                    <td><font color="orange">${sessionScope.curUser.phone}</font></td>
                </tr>
                <tr>
                    <td>地址</td>
                    <td><font color="orange">${sessionScope.curUser.address}</font></td>
                </tr>
                <tr>
                    <td>个人说明</td>
                    <td><font color="orange">${sessionScope.curUser.personal}</font></td>
                </tr>
                <tr>
                    <td  height="25" align="center" bgcolor="#EFEFEF" colspan="2"><font size="2"><a href="${pageContext.request.contextPath}/user/edit">更新用户资料</a></font></td>

                </tr>

            </table>
            <jsp:include page="../common/bottom.jsp"/>
        </center>

    </body>
</html>
