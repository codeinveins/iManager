package com.supra.imanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.supra.imanager.repository.RestTokenRepository;

public class RequestInterceptor implements HandlerInterceptor {

	@Autowired
	private RestTokenRepository restTokenRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		if(!url.contains("login") && !url.contains("forgetPasswordActivationLink")
				&& !url.contains("images") && !url.contains("profileImages")) {
			String accessToken = request.getHeader("token");
			if(StringUtils.isNotBlank(accessToken)) {
				if(null != restTokenRepository.findByToken(accessToken)){
					return true;
				}
				else {
					response.setStatus(HttpStatus.BAD_REQUEST.value());
					response.setHeader("message", "Unauthorized. Provide a valid access token.");
					return false;
				}
			}
			else {
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				response.setHeader("message", "Unauthorized. Provide a valid access token.");
				return false;
			}
		}
		else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
