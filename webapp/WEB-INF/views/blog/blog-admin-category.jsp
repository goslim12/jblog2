<%@page import="com.cafe24.jblog2.vo.BlogVo"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<% List<BlogVo> list = (List<BlogVo>)request.getAttribute("list"); 
	pageContext.setAttribute("newline","\n");
%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script
	src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>
<script>
	$(function() {
		$("#btn-category-add")
				.click(
						function() {
							var categoryName = $("#category-name").val();
							var categoryDesc = $("#category-desc").val();
								 if (categoryName == "") {
										return;
									}
									if (categoryDesc == "") {
										return;
									} 
							
									$.ajax({
										url : "${pageContext.servletContext.contextPath}/${authUser.id}/api/admin/category", //요청할 URL
										dataType : "json", //응답받을 데이터타입
										type : "post", //요청 방식
										data : {
											"categoryName" : categoryName,
											"categoryDesc" : categoryDesc
										}, //서버에 요청시 보낼 파라미터 ex) {name:홍길동}
										success : function(response) { //요청 및 응답에 성공했을 경우
										    $('#cate-table tr:first').after(
										    		"<tr>"+
													"<td>"+$('#cate-table tr').length+"</td>"+
													"<td>"+categoryName+"</td>"+
													"<td>"+0+"</td>"+
													"<td>"+categoryDesc+"</td>"+
													"<td><img class='cate-delete' data-no="+response.no +" src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>"+
													"</tr>"
										    		);
										},
										error : function(xhr, status, e) { //요청 및 응답에 실패 한 경우
											console.error(status + ":" + e);

										}
									});
						});
	});
	
	
	$(document).on("click", ".cate-delete", function(){
		var tmp = $(this); 
		var no = tmp.attr("data-no")
		console.log(no);
		$
				.ajax({
					url : "${pageContext.servletContext.contextPath}/${authUser.id}/api/admin/category-rmv", //요청할 URL
					dataType : "json", //응답받을 데이터타입
					type : "post", //요청 방식
					data : {"no" : no}, //서버에 요청시 보낼 파라미터 ex) {name:홍길동}
					success : function(response) { //요청 및 응답에 성공했을 경우
						tmp.closest("tr").remove();
					},
					error : function(xhr, status, e) { //요청 및 응답에 실패 한 경우
						console.error(status + ":" + e);

					}
				});
	})
	
	/*
	$(function() {
		$(".cate-delete")
				.click(
						function() {
							var tmp = $(this); 
							var no = tmp.attr("data-no")
							console.log(no);
							$
									.ajax({
										url : "${pageContext.servletContext.contextPath}/${authUser.id}/api/admin/category-rmv", //요청할 URL
										dataType : "json", //응답받을 데이터타입
										type : "post", //요청 방식
										data : {"no" : no}, //서버에 요청시 보낼 파라미터 ex) {name:홍길동}
										success : function(response) { //요청 및 응답에 성공했을 경우
											tmp.closest("tr").remove();
										},
										error : function(xhr, status, e) { //요청 및 응답에 실패 한 경우
											console.error(status + ":" + e);

										}
									});
						});
	});
	*/
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/includes/blog/blogAdminHeader.jsp" />
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blog/adminMenu.jsp">
					<c:param name="menu" value="category" />
				</c:import>
				<table class="admin-cat" id="cate-table">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					<c:set var="count" value="${fn:length(list) }"/>
					<c:forEach items="${list}" var ="vo" varStatus="status">
					<tr>
						<td>${count-status.index}</td>
						<td>${vo.name}</td>
						<td>${vo.postCount}</td>
						<td>${vo.description}</td>
						<td><img class="cate-delete" data-no="${vo.no}"
							src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
					</tr>
					</c:forEach>
				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<table id="admin-cat-add">
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" id="category-name" name="name"></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" id="category-desc" name="desc"></td>
					</tr>
					<tr>
						<td class="s">&nbsp;</td>
						<td><input type="submit" value="카테고리 추가"
							id="btn-category-add"></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>