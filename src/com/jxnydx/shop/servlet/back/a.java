package com.jxnydx.shop.servlet.back;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxnydx.shop.factory.ServiceBackFactory;

/**
 * Servlet implementation class a
 */
@WebServlet({ "/a", "/a/*" })
public class a extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public a() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if(status != null) {
            if("insertPre".equals(status)) {
            	path=this.insertPre(request);
            }
         }
        request.getRequestDispatcher(path).forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public String insertPre(HttpServletRequest request) {
    	try {
    		Map<String,Object> map = ServiceBackFactory.getIGoodsServiceBackInstance().insertPre();
			request.setAttribute("allItems", map.get("allItems"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "/pages/back/admin/goods/goods_insert.jsp";
    }
    

}
