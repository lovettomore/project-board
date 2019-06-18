<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>BOARD</title>
	
	<%-- basic Library --%>
	<%@include file="/common/basicLib.jsp"%>
</head>
<body>
	<header id="header">
		<nav id="nav">
			<h2><a href="${pageContext.request.contextPath}">board.</a></h2>
			<div class="boardList">
				<h2>BOARD</h2>
				<ul>
					<c:forEach items="${useBoardList}" var="useBoard">
					<li><a href="${pageContext.request.contextPath}/listBoard?boardId=${useBoard.boardId}">${useBoard.name}</a></li>
					</c:forEach>
				</ul>
				<h2>ADMIN</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/regBoard">add board</a></li>
				</ul>
			</div>
			
			<div class="userInfo">
				<p><img src="${pageContext.request.contextPath}/profile?userId=${USER_INFO.userId}" alt="brown"></p>
				<dl>
					<dt>${USER_INFO.userId}</dt>
					<dd>${USER_INFO.name}</dd>
				</dl>
				<div class="logout"><a href="${pageContext.request.contextPath}/logout">Logout</a></div>
			</div>
		</nav>
	</header>
	
	<div id="wrap">
		<div class="container">