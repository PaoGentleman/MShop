package com.jxnydx.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@WebFilter(filterName = "AdminLoginFilter",urlPatterns = "/pages/back/admin/*")
public class AdminLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpSession ses = request.getSession();
		if(ses.getAttribute("aid") != null) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			request.setAttribute("msg", "您还未登录，请进行登录后操作");
			request.setAttribute("url", "/pages/back/login.jsp");
			request.getRequestDispatcher("/pages/forward.jsp").forward(servletRequest, servletResponse);
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
