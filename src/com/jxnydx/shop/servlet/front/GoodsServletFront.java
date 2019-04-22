package com.jxnydx.shop.servlet.front;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxnydx.shop.factory.ServiceBackFactory;
import com.jxnydx.shop.factory.ServiceFrontFactory;
import com.jxnydx.util.validate.ValidateUtil;

import java.io.IOException;
import java.util.Map;

@WebServlet(name="GoodsServletFront",urlPatterns = "/pages/front/goods/GoodsServletFront/*")
public class GoodsServletFront extends HttpServlet {
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
    	String iid = "0";
    	int currentPage = 1;
    	int lineSize = 15;
    	String column = null;
    	String keyWord = null;
    	String columeData = "商品名称:title|发布管理员:aid";
    	try {
    		currentPage = Integer.parseInt(request.getParameter("cp"));
    	} catch(Exception e) {}
    	try {
    		lineSize = Integer.parseInt(request.getParameter("ls"));
    	} catch(Exception e) {}
    	iid = request.getParameter("iid");
    	keyWord = request.getParameter("kw");
    	column = request.getParameter("col");
    	if(keyWord == null) {
    		keyWord = "";
    	}
    	if(column == null) {
    		column = "title";
    	}
    	if(iid == null) {
    		iid = "0";
    	}
    	try {
			Map<String,Object> map = null;
	    	if(iid == null || "0".equals(iid)) {	//属于查询全部
	    		map = ServiceFrontFactory.getIGoodsServiceFrontInstance().list(currentPage, lineSize, column, keyWord);
	    	} else {	//要根据商品类型分类查询
	    		map = ServiceFrontFactory.getIGoodsServiceFrontInstance().listByItem(Integer.parseInt(iid), currentPage, lineSize, column, keyWord);
	    	}
	    	request.setAttribute("allItems", map.get("allItems"));
			request.setAttribute("allGoods", map.get("allGoods"));
	    	request.setAttribute("allRecorders", map.get("goodsCount"));
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	request.setAttribute("currentPage", currentPage);
    	request.setAttribute("lineSize", lineSize);
    	request.setAttribute("column", column);
    	request.setAttribute("keyWord", keyWord);
    	request.setAttribute("columeData", columeData);
    	request.setAttribute("url", "/MShop/pages/front/goods/GoodsServletFront/list");
    	request.setAttribute("paramName", "iid");
    	request.setAttribute("paramValue", iid);
    	return "/pages/front/goods/goods_list.jsp";
    }
    
    public String show(HttpServletRequest request) {
    	String msg = null;
    	String url = null;
    	String gid = request.getParameter("gid");	
    	//System.out.println(gid);
    	String referer = request.getHeader("referer");	//取得之前的路径
    	if(ValidateUtil.validateEmpty(gid)) {
    		try {
				request.setAttribute("goods", ServiceFrontFactory.getIGoodsServiceFrontInstance().show(Integer.parseInt(gid)));
			} catch (Exception e) {
				e.printStackTrace();
			}
    		return "/pages/front/goods/goods_show.jsp"; 
    	} else {
    		msg = "你选择的商品信息有问题，请重新选择";
    		url = "/pages/front/goods/GoodsServletFront" + referer.substring(referer.lastIndexOf("/")); 
    	}
    	request.setAttribute("msg", msg);
    	request.setAttribute("url", url);
    	return "/pages/forward.jsp";
    }
}
