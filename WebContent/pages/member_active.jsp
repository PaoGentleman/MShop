<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>微商城项目</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css">
    <script type="text/javascript" src="js/mldn.js"></script>
</head>
<body>
    <jsp:include page="/pages/header.jsp"/>
    <div id="mainDiv">
	<h1>【发激活邮件】${active}</h1>
	<h2><a href="${active}">点击这里进行激活</a></h2>
    </div>
    <jsp:include page="/pages/footer.jsp"/>
</body>
</html>
