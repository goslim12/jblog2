package com.cafe24.security;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog2.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

//		System.out.println("============");
//		System.out.println("============");
//		System.out.println("============");
//		System.out.println("============");
//		System.out.println("============");
//		System.out.println(handler);
		// 1.핸들러 종류 확인
		if (handler instanceof HandlerMethod == false) {
			return true;
		}

		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. @Auth 받아오기.
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		// 4. Method에 @Auth가 없는 경우
		if (auth == null) {
			return true;
		}
		// 5. @Auth 가 붙어 있는 경우, 인증 여부 체크
		HttpSession session = request.getSession();
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		
		if(auth.role()==Auth.Role.ADMIN)
		{ 
			Map pathVariables = (LinkedHashMap)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			
			if(!authUser.getId().equals(pathVariables.get("id"))) { //번호가 다르면
				response.sendRedirect(request.getContextPath());
				return false;	
			}
		}
		
		//6. 접근 허가
		return true;
	}

}
