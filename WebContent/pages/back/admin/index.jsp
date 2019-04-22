<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String logoutUrl = basePath + "pages/AdminLoginServletBack/logout";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>微商城项目</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css">
    <script type="text/javascript" src="js/mldn.js"></script>
	<script type="text/javascript" src="js/admin.js"></script>    
</head>
<body>
    <jsp:include page="/pages/back/admin/header.jsp"/>
    <div id="mainDiv">
	<h1>管理员:${aid}您好，感谢登陆使用!</h1>
	<h1>上次登陆时间${lastdate}</h1>
	<h1>欢迎来到后台管理程序</h1>
    </div>
    <jsp:include page="/pages/back/admin/footer.jsp"/>
</body>
</html>
