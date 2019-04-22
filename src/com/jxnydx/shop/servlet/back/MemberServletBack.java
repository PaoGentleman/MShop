package com.jxnydx.shop.servlet.back;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxnydx.shop.factory.ServiceBackFactory;
import com.jxnydx.util.validate.ValidateUtil;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@WebServlet(name="MemberServletBack",urlPatterns = "/pages/back/admin/member/MemberServletBack/*")
public class MemberServletBack extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if(status != null) {
            if(status.equals("list")) {
            	path = this.list(request);
            } else if(status.equals("listStatus")) {
            	path = this.listStatus(request);
            } else if(status.equals("updateStatus")) {
            	path = this.updateStatus(request);
            } else if(status.equals("show")) {
            	path = this.show(request);
            }
         }
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
    
    public String list(HttpServletRequest request) {
    	int currentPage = 1;
    	int lineSize = 15;
    	String column = null;
    	String keyWord = null;
    	String columeData = "用户名:mid|真实姓名:name|联系电话:phone|地址:address";
    	try {
    		currentPage = Integer.parseInt(request.getParameter("cp"));
    	} catch(Exception e) {}
    	try {
    		lineSize = Integer.parseInt(request.getParameter("ls"));
    	} catch(Exception e) {}
    	keyWord = request.getParameter("kw");
    	column = request.getParameter("col");
    	if(keyWord == null) {
    		keyWord = "";
    	}
    	if(column == null) {
    		column = "mid";
    	}
    	try {
    			Map<String,Object> map = ServiceBackFactory.getIMemberServiceBackInstance().list(currentPage, lineSize, column, keyWord);
    			request.setAttribute("allMembers", map.get("allMembers"));
    	    	request.setAttribute("allRecorders", map.get("memberCount"));
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	request.setAttribute("currentPage", currentPage);
    	request.setAttribute("lineSize", lineSize);
    	request.setAttribute("column", column);
    	request.setAttribute("keyWord", keyWord);
    	request.setAttribute("columeData", columeData);
    	request.setAttribute("url", "/MShop/pages/back/admin/member/MemberServletBack/list");
    	return "/pages/back/admin/member/member_list.jsp";
    }
    
    public String listStatus(HttpServletRequest request) {
    	int  status = 0;
    	int currentPage = 1;
    	int lineSize = 1000;	//目前先显示所有，因为分页组件还未处理好			
    	String column = null;
    	String keyWord = null;
    	String columeData = "用户名:mid|真实姓名:name|联系电话:phone|地址:address";
    	try {
    		status = Integer.parseInt(request.getParameter("status"));
    	} catch(Exception e) {}
    	try {
    		currentPage = Integer.parseInt(request.getParameter("cp"));
    	} catch(Exception e) {}
    	try {
    		lineSize = Integer.parseInt(request.getParameter("ls"));
    	} catch(Exception e) {}
    	keyWord = request.getParameter("kw");
    	column = request.getParameter("col");
    	if(keyWord == null) {
    		keyWord = "";
    	}
    	if(column == null) {
    		column = "mid";
    	}
    	try {
    			Map<String,Object> map = ServiceBackFactory.getIMemberServiceBackInstance().listByStatus(status, currentPage, lineSize, column, keyWord);
    			request.setAttribute("allMembers", map.get("allMembers"));
    	    	request.setAttribute("allRecorders", map.get("memberCount"));
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	request.setAttribute("currentPage", currentPage);
    	request.setAttribute("lineSize", lineSize);
    	request.setAttribute("column", column);
    	request.setAttribute("keyWord", keyWord);
    	request.setAttribute("columeData", columeData);
    	request.setAttribute("url", "/MShop/pages/back/admin/member/MemberServletBack/list");
    	request.setAttribute("paramName", "status");
    	request.setAttribute("paramValue", String.valueOf(status));
    	return "/pages/back/admin/member/member_list.jsp";
    }
    
    public String updateStatus(HttpServletRequest request) {
    	//Enumeration<String> enu = request.getHeaderNames();
    	/*while(enu.hasMoreElements()) {
    		String name = enu.nextElement();
    		System.out.println("*****" + name + ", value = " + request.getHeaderNames());
    	}*/
    	String referer = request.getHeader("referer");	//取得之前的路径
    	String type = request.getParameter("type");
    	String msg = null;
    	String url = null;
    	String ids = request.getParameter("ids");
    	if(ValidateUtil.validateEmpty(ids)) {
    		String[] reslut = ids.split("--");
    		Set<String> mid = new HashSet<String>();
    		for(int x = 0; x < reslut.length ; x ++) {
    			mid.add(reslut[x]);
    		}
    		try {
    			
    			boolean flag = false;
    			if("active".equalsIgnoreCase(type)) {
	    			flag = ServiceBackFactory.getIMemberServiceBackInstance().updateActive(mid); 
    			}
    			if("lock".equalsIgnoreCase(type)) {
	    			flag = ServiceBackFactory.getIMemberServiceBackInstance().updateLock(mid); 
    			}
    			if(flag == true) {
    				msg = "用户状态更新成功！";
    			} else {
    				msg = "用户状态更新失败！";
    			}
    			url = "/pages/back/admin/member/MemberServletBack" + referer.substring(referer.lastIndexOf("/"));
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else {
    		msg = "您还未选择更新数据，请重新操作！";
    		url = "/pages/back/admin/member/MemberServletBack" + referer.substring(referer.lastIndexOf("/"));
    	}
    	request.setAttribute("msg", msg);
    	request.setAttribute("url", url);
    	return "/pages/forward.jsp";
    }
    public String show(HttpServletRequest request) {
    	String msg = null;
    	String url = null;
    	String referer = request.getHeader("referer");	//取得之前的路径
    	String mid = request.getParameter("mid");
    	if(ValidateUtil.validateEmpty(mid)) {
    		try {
				request.setAttribute("member", ServiceBackFactory.getIMemberServiceBackInstance().show(mid));
			} catch (Exception e) {
				e.printStackTrace();
			}
    		request.setAttribute("msg", msg);
        	request.setAttribute("url", url);
        	return "/pages/back/admin/member/member_show.jsp";
    	} else {
    		msg = "您要浏览的数据不存在，或者是还未选择任何数据，请重新选择";
    		url = "/pages/back/admin/member/MemberServletBack" + referer.substring(referer.lastIndexOf("/"));
    		request.setAttribute("msg", msg);
        	request.setAttribute("url", url);
        	return "/pages/forward.jsp";
    	}
    }
}
