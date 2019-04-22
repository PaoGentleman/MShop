<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String loginUrl = basePath + "pages/AdminLoginServletBack/login";
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
    <jsp:include page="/pages/header.jsp"/>
    <div id="mainDiv">
		<form action="<%=loginUrl%>" method="post" onsubmit="return validateLogin()">
        	<table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="100%">
	            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
	                <td colspan="3">管理员登录</td>
	            </tr>
	            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
	                <td width="15%">管理员ID:</td>
	                <td width="40%"><input type="text" name="aid" id="aid" class="init" onblur="validateAid()"></td>
	                <td width="45%"><span id="aidMsg"></span></td>
	            </tr>
	            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
	                <td>密&nbsp;&nbsp;码:</td>
	                <td><input type="password" name="password" id="password" class="init" onblur="validatePassword()"></td>
	                <td><span id="passwordMsg"></span></td>
	            </tr>
	            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
	                <td>验证码:</td>
	                <td>
	                	<input type="text" name="code" id="code" class="init" maxlength="4" size="4" onblur="validateCode()">
	                	<img src="pages/image.jsp"></img>	
	                </td>
	                <td><span id="codeMsg"></span></td>
	            </tr>
	            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
	                <td colspan="3">
	                    <input type="submit" value="登录">
	                    <input type="reset" value="重置">
	                </td>
	            </tr>
	        </table>
    	</form>
    </div>
    <jsp:include page="/pages/footer.jsp"/>
</body>
</html>
