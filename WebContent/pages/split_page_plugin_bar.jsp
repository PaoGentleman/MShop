<%@ page import="java.util.*" pageEncoding="UTF-8"%>
<%--
	代码的引用过程
	<div id="splitBarDiv">
		<jsp:include page="split_page_plugin_bar.jsp">
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
		var eleLs = document.getElementById("lsSel").value;
		try {
			var eleKw = document.getElementById("kw").value;
			var eleCol = document.getElementById("colSel").value;
			window.location = "<%=url%>" + "?cp=" + vcp + "&ls=" + eleLs + "&kw=" + eleKw + "&col=" + eleCol + "&<%=paramName%>=<%=paramValue%>";
		} catch(Exception) {
			window.location = "<%=url%>" + "?cp=" + vcp + "&ls=" + eleLs + "&<%=paramName%>=<%=paramValue%>";
		}
	}
</script>

<input type="button" value="首页" onclick="goSplit(1)" <%=currentPage == 1 ? "disabled" : "" %>>
<input type="button" value="上一页" onclick="goSplit(<%=currentPage - 1%>)" <%=currentPage == 1 ? "disabled" : "" %>>
<input type="button" value="下一页" onclick="goSplit(<%=currentPage + 1%>)" <%=currentPage == pageSize ? "disabled" : "" %>>
<input type="button" value="尾页" onclick="goSplit(<%=pageSize%>)" <%=currentPage == pageSize ? "disabled" : "" %>>
	跳转到:<select id="cpSel" onchange="goSplit(this.value)">
		<%
			for(int x = 1; x <= pageSize; x ++) {
		%>
				<option value="<%=x%>" <%=currentPage == x ? "selected" : "" %>><%=x%></option>
		<%
			}
		%>
	</select>页&nbsp;
	每页显示:<select id="lsSel" onchange="goSplit(1)">
		<%
			for(int x = 0; x <lsData.length; x ++) {
		%>
				<option value="<%=lsData[x]%>" <%=lineSize == lsData[x] ? "selected" : "" %>><%=lsData[x]%></option>
		<%		
			}
		%>
	</select>
	行记录
