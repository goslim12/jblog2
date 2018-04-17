<%@page import="com.cafe24.jblog2.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h1>${blogVo.title}</h1>
<ul>
	<c:choose>
		<c:when test="${empty authUser }">
			<li><a href="${pageContext.servletContext.contextPath}/user/login">로그인</a></li>
		</c:when>
		<c:otherwise>
			<li><a
				href="${pageContext.servletContext.contextPath}/user/logout">로그아웃</a></li>
			<li><a
				href="${pageContext.servletContext.contextPath}/${authUser.id}">블로그메인</a></li>
		</c:otherwise>
	</c:choose>
</ul>