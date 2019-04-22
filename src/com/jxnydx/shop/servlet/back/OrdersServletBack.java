package com.jxnydx.shop.servlet.back;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxnydx.shop.factory.ServiceBackFactory;
import com.jxnydx.shop.factory.ServiceFrontFactory;

import java.io.IOException;
import java.util.Map;

@WebServlet(name="OrdersServletBack",urlPatterns = "/pages/back/admin/orders/OrdersServletBack/*")
public class OrdersServletBack extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if(status != null) {
            if("list".equals(status)) {
            	path = this.list(request);
            } else if("show".equals(status)) {
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
    	String columeData = "购买用户:mid";
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
    		String mid = (String) request.getSession().getAttribute("mid");
			Map<String,Object> map = ServiceBackFactory.getIOrdersServiceBackInstance().list(currentPage, lineSize, column, keyWord);
	    	
	    	request.setAttribute("allOrders", map.get("allOrders"));
			request.setAttribute("allRecorder", map.get("ordersCount"));
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	request.setAttribute("currentPage", currentPage);
    	request.setAttribute("lineSize", lineSize);
    	request.setAttribute("column", column);
    	request.setAttribute("keyWord", keyWord);
    	request.setAttribute("columeData", columeData);
    	request.setAttribute("url", "/MShop/pages/back/admin/orders/OrdersServletBack/list");
    	return "/pages/back/admin/orders/orders_list.jsp";
    }
    
    public String show(HttpServletRequest request) {
    	int oid = Integer.parseInt(request.getParameter("oid"));
    	try {
			request.setAttribute("orders", ServiceBackFactory.getIOrdersServiceBackInstance().show(oid));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "/pages/back/admin/orders/orders_show.jsp";
    }
}
