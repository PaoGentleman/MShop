package com.jxnydx.shop.servlet.front;

import com.jxnydx.shop.factory.ServiceFrontFactory;
import com.jxnydx.shop.vo.Member;
import com.jxnydx.util.BasePath;
import com.jxnydx.util.CookieUtil;
import com.jxnydx.util.MD5;
import com.jxnydx.util.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

@WebServlet(name="MemberServletFront",urlPatterns="/pages/MemberServletFront/*")
public class MemberServletFront extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if(status != null) {
            if(status.equals("regist")) {
                path = this.regist(request);
            } else if(status.equals("active")) {
            	path = this.active(request);
            } else if(status.equals("login")) {
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
    public String regist(HttpServletRequest request) {
        String msg = null;
        String url = null;  //执行forward.jsp的使用
        String active = null;
        String mid = request.getParameter("mid");
        String password = request.getParameter("password");
        //必须验证通过，才可以进行后续的功能操作
        if(ValidateUtil.validateEmpty(mid) && ValidateUtil.validateEmpty(password)) {
            Member vo = new Member();
            vo.setMid(mid);
            vo.setPassword(new MD5().getkeyBeanofStr(password));    //MD5加密
            vo.setRegdate(new Date());  //今天为注册日期
            vo.setPhoto("nophoto.jpg");
            vo.setCode(UUID.randomUUID().toString());   //生成一个随机的Code码
            vo.setStatus(2);    //现在处于待激活状态
            try {
                if (ServiceFrontFactory.getIMemberServiceFrontInstance().regist(vo)) {
                    msg = "用户注册成功，请进行账户激活!";
                    active = BasePath.getBasePath(request) + "/pages/MemberServletFront/active?mid=" + mid + "&code=" + vo.getCode();
                    request.setAttribute("active", active);
                    //模拟发送邮件
                    System.out.println("【发激活邮件】" + BasePath.getBasePath(request) + "/pages/MemberServletFront/active?mid=" + mid + "&code=" + vo.getCode());
                    return "/pages/member_active.jsp";
                } else {
                    msg = "用户注册失败，请填写新的注册信息!";
                    url = "/pages/member_regist.jsp";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "输入的用户信息不正确，请重新注册！";
            url = "/pages/member_regist.jsp";

        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    
    public String active(HttpServletRequest request) {
    	String msg = null;
        String url = null;  //执行forward.jsp的使用
    	String mid = request.getParameter("mid");
        String code = request.getParameter("code");
        if(ValidateUtil.validateEmpty(mid) && ValidateUtil.validateEmpty(code)) {
        	Member vo = new Member();
        	vo.setMid(mid);
        	vo.setCode(code);
        	try {
	        	if(ServiceFrontFactory.getIMemberServiceFrontInstance().active(vo)) {
	        		msg="用户激活成功，请登录!";
	        		url="/pages/member_login.jsp";
	        	} else {
	        		msg="用户激活失败，请与管理员联系!";
	        		url="/index.jsp";
	        	} 
        	} catch(Exception e) {
        		e.printStackTrace();
        	}
        } else {
        	msg = "错误的激活操作，请与管理员联系！";
            url = "/index.jsp";
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }

    public String login(HttpServletRequest request,HttpServletResponse response) {
    	String msg = null;
        String url = null;  //执行forward.jsp的使用
        String mid = request.getParameter("mid");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String rand = (String) request.getSession().getAttribute("rand");
        if(ValidateUtil.validateEmpty(mid) && ValidateUtil.validateEmpty(password) && ValidateUtil.validateEmpty(code) && ValidateUtil.validateEmpty(rand)) {
        	if(ValidateUtil.validateSame(code, rand)) {
        		Member vo = new Member();
        		vo.setMid(mid);
        		vo.setPassword(new MD5().getkeyBeanofStr(password));
        		try {
	        		if(ServiceFrontFactory.getIMemberServiceFrontInstance().login(vo)) {
	        			request.getSession().setAttribute("mid", mid);
	        			request.getSession().setAttribute("photo", vo.getPhoto());
	        			msg = "登录成功，欢迎光临!";
	                	url = "/index.jsp";
	                	if(request.getParameter("reme") != null) {
	                		int expiry = Integer.parseInt(request.getParameter("reme"));
	                		CookieUtil.save(response, request, "mid",mid,expiry);
	                		CookieUtil.save(response, request, "password",vo.getPassword(),expiry);
	                	}
	        		} else {
	        			msg = "登录失败，错误的用户名或密码!";
	                	url = "/pages/member_login.jsp";
	        		}
        		} catch(Exception e) {
        			e.printStackTrace();
        		}
        	} else {
        		msg = "验证码输入错误，请重新登录!";
            	url = "/pages/member_login.jsp";
        	}
        } else {
        	msg = "信息输入错误，请重新登录!";
        	url = "/pages/member_login.jsp";
        }
    	request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    
    public String logout(HttpServletRequest request,HttpServletResponse response) { 
    	CookieUtil.clear(response, request);
    	request.getSession().invalidate();
    	request.setAttribute("msg","您已安全退出");
        request.setAttribute("url","/index.jsp");
    	return "/pages/forward.jsp";
    }
}
