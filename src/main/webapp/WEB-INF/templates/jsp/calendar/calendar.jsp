<%-- 
    Document   : calendar
    Created on : 2009-9-24, 19:42:18
    Author     : e
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>日历</title>
    </head>
    <body>
        <center>
            <jsp:include page="../common/logo.jsp"/>

            <jsp:include page="../common/navigator.jsp"/>
            
            <h3>今天是${calendarBean.year}年${calendarBean.month}月${calendarBean.day}日</h3>
            ${calendarBean.calendar}
            <jsp:include page="../common/bottom.jsp"/>
        </center>
    </body>
</html>
