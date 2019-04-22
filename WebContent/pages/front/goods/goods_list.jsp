<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String showUrl = basePath + "pages/front/goods/GoodsServletFront/show";
    String listUrl = basePath + "pages/front/goods/GoodsServletFront/list";
    String addCarUrl = basePath + "pages/front/shopcar/ShopcarServletFront/insert";
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
	<h1>商品列表</h1>
	<select onchange="goList('<%=listUrl%>',this.value)">
		<option value="0">====== 查看全部商品 ======</option>
		<c:forEach items="${allItems}" var="item">
			<option value="${item.iid}" ${item.iid==paramValue ? "selected" : ""}>${item.title}</option>
		</c:forEach>
	</select>
	<c:if test="${allGoods != null}" var="res">
		<div id="splitSearchDiv">
			<jsp:include page="/pages/split_page_plugin_search.jsp">
				<jsp:param name="currentPage" value="${currentPage}"/>
				<jsp:param name="lineSize" value="${lineSize}"/>
				<jsp:param name="column" value="${column}"/>
				<jsp:param name="keyWord" value="${keyWord}"/>
				<jsp:param name="columnDate" value="${columnData}"/>
				<jsp:param name="allRecorders" value="${allRecorders}"/>
				<jsp:param name="url" value="${url}"/>
				<jsp:param name="paramName" value="iid"/>
				<jsp:param name="paramValue" value="${iid}}"/>
				<jsp:param name="title" value="商品名称"/>
			</jsp:include>
		</div>
        <c:forEach items="${allGoods}" var="goods">
            <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="100%">
            	<tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
	            	<td rowspan="4" width="20%">
	            		<a href="<%=showUrl%>?gid=${goods.gid}">
	            			<img src="upload/goods/${goods.photo}" style="width:50px;height:50px">
	            		</a>
	            	</td>
	            </tr>
	            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
	            	<td width="20%"><strong>商品名称:</strong></td>
	            	<td width="20%"><a href="<%=showUrl%>?gid=${goods.gid}">${goods.title}</a></td>
	            	<td width="20%"><strong>上架信息</strong></td>
	            	<td width="20%">${goods.pubdate}</td>
	            </tr>
	            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
	            	<td><strong>商品价格:</strong></td>
	            	<td>${goods.price}</td>
	            	<td><strong>浏览次数</strong></td>
	            	<td>${goods.bow}</td>
	            </tr>
				<tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
	            	<td colspan="3">
	            		${goods.note}
	            	</td>
	            	<td><a href="<%=addCarUrl%>?gid=${goods.gid}">加入购物车</a></td>
	            </tr>
	    	</table>
        </c:forEach>
		<div id="splitBarDiv">
			<jsp:include page="/pages/split_page_plugin_bars.jsp">
				<jsp:param name="currentPage" value="${currentPage}"/>
				<jsp:param name="lineSize" value="${lineSize}"/>
				<jsp:param name="column" value="${column}"/>
				<jsp:param name="keyWord" value="${keyWord}"/>
				<jsp:param name="columnDate" value="${columnData}"/>
				<jsp:param name="allRecorders" value="${allRecorders}"/>
				<jsp:param name="url" value="${url}"/>
			</jsp:include>
			<br>
		</div>
	</c:if>
    </div>
    <jsp:include page="/pages/footer.jsp"/>
</body>
</html>
