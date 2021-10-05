package com.example.iThomeIronMan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.iThomeIronMan.model.Member;

@Component
@WebFilter(urlPatterns = {"/*"})
@Order(value = 1)
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("MemberSession");

		String uri = new String(req.getRequestURI());

		// 放行所有靜態檔案
		if(uri.contains("/css") || uri.contains("/images") || uri.contains("/js")) {
			chain.doFilter(request, response);
			return ;
		}

		// 已登入
		if(member != null) {
			if(uri.contains("/login") || uri.contains("/register")) {
				res.sendRedirect("/information");
			}
			else {
				chain.doFilter(request, response);
			}
		}
		// 未登入
		else {
			if(uri.contains("/login") || uri.contains("/register")) {
				chain.doFilter(request, response);
			}
			else {
				res.sendRedirect("/login");
			}
		}
	}

}
