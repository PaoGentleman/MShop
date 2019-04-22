package com.jxnydx.shop.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jxnydx.shop.factory.ServiceFrontFactory;
import com.jxnydx.shop.vo.Member;
import com.jxnydx.util.CookieUtil;
@WebFilter(filterName = "AutoLoginFilter",urlPatterns = {"/index.jsp","/pages/front/*"})
public class AutoLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpSession ses = request.getSession();
		if(ses.getAttribute("mid") == null) {
			Map<String,String> map = CookieUtil.load(request);
			if(map.containsKey("mid") && map.containsKey("password")) {
				Member vo = new Member();
				vo.setMid(map.get("mid"));
				vo.setPassword(map.get("password"));
				try {
					if(ServiceFrontFactory.getIMemberServiceFrontInstance().login(vo)) {
						ses.setAttribute("mid", vo.getMid());
						ses.setAttribute("photo", vo.getPhoto());
					} 
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
