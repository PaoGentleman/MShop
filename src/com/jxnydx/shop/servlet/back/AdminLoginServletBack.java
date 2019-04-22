package com.jxnydx.shop.servlet.back;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jxnydx.shop.factory.ServiceBackFactory;
import com.jxnydx.shop.vo.Admin;
import com.jxnydx.util.MD5;
import com.jxnydx.util.validate.ValidateUtil;

@WebServlet(name="AdminLoginServletBack",urlPatterns="/pages/AdminLoginServletBack/*")
public class AdminLoginServletBack extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if(status != null) {
            if(status.equals("login")) {
            	path = this.login(request,response);
            } else if(status.equals("logout")) {
            	path = this.logout(request, response);
            }
         }
        request.getRequestDispatcher(path).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
    
    public String login(HttpServletRequest request, HttpServletResponse response) {
    	String msg = null;
        String url = null;  //执行forward.jsp的使用
        String aid = request.getParameter("aid");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String rand = (String) request.getSession().getAttribute("rand");
        if(ValidateUtil.validateEmpty(aid) && ValidateUtil.validateEmpty(password) && ValidateUtil.validateEmpty(code) && ValidateUtil.validateEmpty(rand)) {
        	if(ValidateUtil.validateSame(code, rand)) {
	        	Admin vo = new Admin();
	        	vo.setAid(aid);
	        	vo.setPassword(new MD5().getkeyBeanofStr(password));
	        	try {
		        	if(ServiceBackFactory.getIAdminServiceBackInstance().login(vo)) {
		        		request.getSession().setAttribute("aid", aid);
		        		request.getSession().setAttribute("lastdate", vo.getLastdate());
		        		msg = "管理员登录成功，欢迎您的归来!";
		        		url = "/pages/back/admin/index.jsp";
		        	} else {
		        		msg = "管理员登录失败，错误的用户名或密码，请重新登录!";
		        		url = "/pages/back/login.jsp";
		        	}
	        	} catch(Exception e) {
	        		e.printStackTrace();
	        	}
	        } else {
	        	msg = "验证码输入错误，请重新登录!";
	        	url = "/pages/back/login.jsp";
	        }
        } else {
        	msg = "管理员登录信息填写错误，请重新登录!";
        	url = "/pages/back/login.jsp";
        }
    	request.setAttribute("msg",msg);
        request.setAttribute("url",url);
    	return "/pages/forward.jsp";
    }
    
    public String logout(HttpServletRequest request, HttpServletResponse response) {
    	request.getSession().invalidate();
    	request.setAttribute("msg","管理员注销成功，再见!");
        request.setAttribute("url","/pages/back/login.jsp");
    	return "/pages/forward.jsp";
    }
}
