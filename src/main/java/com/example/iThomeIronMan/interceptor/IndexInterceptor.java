package com.example.iThomeIronMan.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.iThomeIronMan.controller.LoginRegisterController;
import com.example.iThomeIronMan.model.Member;

@Component
public class IndexInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(LoginRegisterController.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		String uri = new String(request.getRequestURI());
				
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("MemberSession");

		// 已登入
		if(member != null) {
			logger.info(member.getName() + " 訪問 " + uri);
			return true;
		}
		// 未登入
		else {
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
