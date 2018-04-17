<%@page import="com.cafe24.jblog2.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  


 <!-- <div id="header">  -->
			<h1 class="logo"><a href="${pageContext.servletContext.contextPath}/main">JBlog</a></h1>
		<!-- 	<h1 ><a href="${pageContext.servletContext.contextPath}/main">JBlog</a></h1> -->
			<ul class="menu">
				<c:choose>
					<c:when test="${empty authUser }">
						<li><a href="${pageContext.servletContext.contextPath}/user/login">로그인</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/user/join">회원가입</a></li>
						<!-- <li><a href="${pageContext.servletContext.contextPath}/blog/myblog">내블로그</a></li> -->
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.servletContext.contextPath}/user/logout">로그아웃</a></li>
						<li><a href="${pageContext.servletContext.contextPath}/${authUser.id}">내블로그</a></li>
						<br/><li>${authUser.name }님 안녕하세요 >_<</li>
				</c:otherwise>
				</c:choose>
	
			</ul>
	<!-- </div> -->