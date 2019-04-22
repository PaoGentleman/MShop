<%@ page pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	String url = null;
	String columnData = null;
	String keyWord = null;
	String column = null;
	int allRecorders = 0;
	String title;
%>
<%
	try {
		allRecorders = Integer.parseInt(request.getParameter("allRecorders"));
	} catch(Exception e) {}
	columnData = request.getParameter("columnData");
	keyWord = request.getParameter("keyWord");
	column = request.getParameter("column");
	url = request.getParameter("url");
	title = request.getParameter("title");
%>
<script type="text/javascript">
	function goSplit(vcp) {
		try {
			var eleKw = document.getElementById("kw").value;
			var eleCol = document.getElementById("colSel").value;
			window.location = "<%=url%>" + "?cp=" + vcp + "&kw=" + eleKw + "&col=" + eleCol;
		} catch(Exception) {
			window.location = "<%=url%>" + "?cp=" + vcp;
		}
	}
</script>
请输入查询<%=title%>关键字:
<%
	if(columnData != null) {
%>
		<select id="colSel">
			<% 
				String[] result = columnData.split("\\|");
				for(int x = 0;x < result.length;x ++) {
					String[] temp = result[x].split(":");
			%>
					<option value="<%=temp[1]%>" <%=column.equals(temp[1]) ? "selected" : ""%>><%=temp[0]%>
			<%
				} 
			%>
		</select>
<%
	}
%>
<input type="text" name="kw" id="kw" value="<%=keyWord%>">
<input type="button" value="检索" onclick="goSplit(1)">
<span>一共查询到<%=allRecorders%>条数据。
</span><br>