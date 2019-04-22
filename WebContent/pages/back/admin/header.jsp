<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="headerDiv">
    <img src="images/logo.png">
    <a href="">商城首页</a>
   	<a href="pages/AdminLoginServletBack/logout">安全退出</a><br>
   	<ul value="用户管理">
	   	<li><a href="pages/back/admin/member/MemberServletBack/list">用户列表</a></li>
	   	<li><a href="pages/back/admin/member/MemberServletBack/listStatus?status=0">锁定用户</a></li>
	   	<li><a href="pages/back/admin/member/MemberServletBack/listStatus?status=1">激活用户</a></li>
	   	<li><a href="pages/back/admin/member/MemberServletBack/listStatus?status=2">待激活用户</a></li>
   	</ul>
   	<ul value="商品管理">
	   	<li><a href="pages/back/admin/item/item_insert.jsp">增加商品分类</a></li>
	   	<li><a href="pages/back/admin/item/ItemServletBack/list">商品分类列表</a></li>
	   	<li><a href="pages/back/admin/goods/GoodsServletBack/insertPre">增加商品</a></li>
	   	<li><a href="pages/back/admin/goods/GoodsServletBack/list">商品列表</a></li>
	   	<li><a href="pages/back/admin/goods/GoodsServletBack/listStatus?status=1">上架商品</a></li>
	   	<li><a href="pages/back/admin/goods/GoodsServletBack/listStatus?status=0">下架商品</a></li>
	   	<li><a href="pages/back/admin/goods/GoodsServletBack/listStatus?status=2">删除商品</a></li>
   	</ul>
   	<ul value="订单管理">
   		<li><a href="pages/back/admin/orders/OrdersServletBack/list">全部订单</a></li>
   	</ul>
</div>