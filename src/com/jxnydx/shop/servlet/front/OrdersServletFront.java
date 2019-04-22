package com.jxnydx.shop.servlet.front;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxnydx.shop.exception.EmptyShopcarException;
import com.jxnydx.shop.exception.UnCompleteMemberInformationException;
import com.jxnydx.shop.exception.UnEnoughAmountException;
import com.jxnydx.shop.factory.DAOFactory;
import com.jxnydx.shop.factory.ServiceFrontFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name="OrdersServletFront",urlPatterns = "/pages/front/orders/OrdersServletFront/*")
public class OrdersServletFront extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if(status != null) {
            if("insert".equals(status)) {
            	path = this.insert(request);
            } else if("list".equals(status)) {
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
    
    public String insert(HttpServletRequest request) {
    	String msg = null;
    	String url = null;
    	String mid = (String)request.getSession().getAttribute("mid");
    	try {
			if(ServiceFrontFactory.getIOrdersServiceFrontInstance().insert(mid)) {
				msg = "订单创建成功";
				url = "/index.jsp";
			}
		} catch (UnCompleteMemberInformationException e) {
			msg = "个人信息不完整，无法进行订单的创建";
			url = "/pages/front/member/MemberInfoServletFront/updatePre";
			e.printStackTrace();
		} catch (UnEnoughAmountException e) {
			msg = "供货商品不足,无法创建订单";
			url = "/pages/front/shopcar/ShopcarServletFront/list";
			e.printStackTrace();
		} catch (EmptyShopcarException e) {
			msg = "购物车没有商品，请选择商品";
			url = "/pages/front/goods/GoodsServletFront/list";
			e.printStackTrace();
		} catch (SQLException e) {
			msg = "订单创建失败";
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	request.setAttribute("msg", msg);
    	request.setAttribute("url", url);
    	return "/pages/forward.jsp";
    }
    
    public String list(HttpServletRequest request) {
    	int currentPage = 1;
    	int lineSize = 15;
    	String column = null;
    	String keyWord = null;
    	try {
    		currentPage = Integer.parseInt(request.getParameter("cp"));
    	} catch(Exception e) {}
    	try {
    		lineSize = Integer.parseInt(request.getParameter("ls"));
    	} catch(Exception e) {}
    	keyWord = request.getParameter("kw");
    	column = request.getParameter("col");
    	if(keyWord == null) {
    		keyWord = "mid";
    	}
    	if(column == null) {
    		column = "";
    	}
    	try {
    		String mid = (String) request.getSession().getAttribute("mid");
			Map<String,Object> map = ServiceFrontFactory.getIOrdersServiceFrontInstance().listByMember(mid, currentPage, lineSize);
	    	
	    	request.setAttribute("allOrders", map.get("allOrders"));
			request.setAttribute("allRecorder", map.get("ordersCount"));
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	request.setAttribute("currentPage", currentPage);
    	request.setAttribute("lineSize", lineSize);
    	request.setAttribute("column", column);
    	request.setAttribute("keyWord", keyWord);
    	request.setAttribute("url", "/MShop/pages/front/orders/OrdersServletFront/list");
    	return "/pages/front/orders/orders_list.jsp";
    }
    public String show(HttpServletRequest request) {
    	int oid = Integer.parseInt(request.getParameter("oid"));
    	String mid = (String) request.getSession().getAttribute("mid");
    	try {
			request.setAttribute("orders", ServiceFrontFactory.getIOrdersServiceFrontInstance().show(mid, oid));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "/pages/front/orders/orders_show.jsp";
    }
}
