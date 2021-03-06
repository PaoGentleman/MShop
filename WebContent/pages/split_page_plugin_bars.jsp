<%@ page import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/mldn.css">
<%--
	代码的引用过程
	<div id="splitBarDiv">
		<jsp:include page="/pages/split_page_plugin_bars.jsp">
			<jsp:param name="currentPage" value="<%=currentPage%>"/>
			<jsp:param name="lineSize" value="<%=lineSize%>"/>
			<jsp:param name="column" value="<%=column%>"/>
			<jsp:param name="keyWord" value="<%=keyWord%>"/>
			<jsp:param name="allRecorders" value="<%=allRecorders%>"/>
			<jsp:param name="url" value="<%=url%>"/>
			<jsp:param name="paramName" value="	参数名称"/>
			<jsp:param name="paramValue" value="<%=参数内容%>"/>
		</jsp:include>
	</div>
	<jsp:include page="/pages/split_page_plugin_bars.jsp">
		<jsp:param name="currentPage" value="${currentPage}"/>
		<jsp:param name="lineSize" value="${lineSize}"/>
		<jsp:param name="column" value="${column}"/>
		<jsp:param name="keyWord" value="${keyWord}"/>
		<jsp:param name="columnDate" value="${columnData}"/>
		<jsp:param name="allRecorders" value="${allRecorders}"/>
		<jsp:param name="url" value="${url}"/>
		<jsp:param name="paramName" value="	参数名称"/>
		<jsp:param name="paramValue" value="<%=参数内容%>"/>
	</jsp:include>
--%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	String url = null;
	int currentPage = 1;
	int lineSize = 5;
	String column = null;
	String keyWord = null;
	int allRecorders = 0;
	int pageSize = 0;
	int[] lsData = new int[] {1,5,10,15,20,30,50,100}; 
	int seed = 3;
	String paramName = (String)request.getAttribute("paramName");
	String paramValue = (String)request.getAttribute("paramValue");
%>

<%
	try {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	} catch(Exception e) {}
	try {
		lineSize = Integer.parseInt(request.getParameter("lineSize"));
	} catch(Exception e) {}
	try {
		allRecorders = Integer.parseInt(request.getParameter("allRecorders"));
	} catch(Exception e) {}
	column = request.getParameter("column");
	keyWord = request.getParameter("keyWord");
	url = request.getParameter("url");
%>
<%	//防止用户手动输入出错
	if(allRecorders > 0) {	//记录总页数
		pageSize = (allRecorders + lineSize - 1) / lineSize;
	} else {
		pageSize = 1;
	}
%>

<script type="text/javascript">
	function goSplit(vcp) {
		try {
			var eleKw = document.getElementById("kw").value;
			var eleCol = document.getElementById("colSel").value;
			window.location = "<%=url%>" + "?cp=" + vcp + "&ls=" + <%=lineSize%> + "&kw=" + eleKw + "&col=" + eleCol + "&<%=paramName%>=<%=paramValue%>";
		} catch(Exception) {
			window.location = "<%=url%>" + "?cp=" + vcp + "&ls=" + <%=lineSize%> + "&<%=paramName%>=<%=paramValue%>";
		}
	}
</script>
<%
if(pageSize > seed * 3) {
%>
<ul class="pagination"> 
	<li class="<%=currentPage == 1 ? "active" : "" %>"><a onclick="goSplit(<%=1%>)"><%=1%></a></li>
<%
	if(currentPage > seed * 2){
%>
		<li><span>...</span></li>
<%
		int startPage = currentPage - seed;
		int endPage = currentPage + seed;
		for(int x = startPage;x <= endPage; x ++) {
			if(x < pageSize) {				
%>
				<li class="<%=currentPage == x ? "active" : "" %>"><a onclick="goSplit(<%=x%>)"><%=x%></a></li>
<%			
			}
		}
		if((currentPage + seed * 2) <= pageSize) {
%>
			<li><span>...</span></li>
<%
		}
	} else {
		for(int x = 2 ;x <= currentPage + seed ; x ++) {
%>
			<li class="<%=currentPage == x ? "active" : "" %>"><a onclick="goSplit(<%=x%>)"><%=x%></a></li>
<%
		}
		if((currentPage + seed * 2) <= pageSize) {
%>
			<li><span>...</span></li>
<%
		}
	}
%>
	<li class="<%=currentPage == pageSize ? "active" : "" %>"><a onclick="goSplit(<%=pageSize%>)"><%=pageSize%></a></li>
<%
} else {
%>
	<ul class="pagination"> 
<%
	for(int x = 1 ;x <= pageSize ; x ++) {
%>
		<li class="<%=currentPage == x ? "active" : "" %>"><a onclick="goSplit(<%=x%>)"><%=x%></a></li>
<%
	}
%>
	</ul>
<%
}
%>
</ul>




