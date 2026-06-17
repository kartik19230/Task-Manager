package com.kk.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("user") != null) {
			return true;
		}
		response.sendRedirect("/auth/loginIfRegister");

		return false;
	}
}

//HttpRequest client -> web filter -> dispatcher Servlet -> interceptor preHandle() -> controller -> Interceptor(postHandle()) -> view -> Handler Interceptor(afterCompletion) -> view