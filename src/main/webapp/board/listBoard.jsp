<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="/common/header.jsp"%>

<script>
	
	$(document).ready(function(){
		//리스트 클릭시
		$(".listClick").on("click", function(){
			var writeId = $(this).children(".blind").text();
			
			var a = $(this).children("td:nth-child(3)").text();
			if(a == '작성자에 의해 삭제된 게시글 입니다.'){
				return false;
			}
			
			$("#writeId").val(writeId);
			$("#getListFrm").submit();
		});
	});
</script>

<div class="contents">
	<h2 class="subTitle">${boardName}</h2>
	
	<!-- 게시판 상세 조회 : boardId, writeId 필요 -->
	<form action="${pageContext.request.contextPath}/viewBoard" method="get" id="getListFrm">
		<input type="hidden" id="boardId" name="boardId" value="${param.boardId}">
		<input type="hidden" id="writeId" name="writeId">
	</form>
	
	<!-- 게시판 리스트 조회 -->
	<div class="boardWrap">
		<h2>게시판 리스트</h2>
		<table>
			<colgroup>
				<col width="10%">
				<col width="70%">
				<col width="10%">
				<col width="15%">
			</colgroup>
			<tr>
				<th>게시글 번호</th>
				<th>제목</th>
				<th>작성자 아이디</th>
				<th>작성 일시</th>
			</tr>
			<c:forEach items="${boardWriteList}" var="boardWriteList">
			<tr class="listClick">
				<td class="blind">${boardWriteList.writeId}</td>
				<td>${boardWriteList.rn}</td>
				<c:choose>
					<c:when test="${boardWriteList.use_yn == 'Y'}">
						<c:choose>
							<c:when test="${boardWriteList.parent_seq > 0}">
								<td style="padding-left:${boardWriteList.lv}9px">
									<span class="level"></span>${boardWriteList.subject}
								</td>
							</c:when>
							<c:otherwise>
								<td>${boardWriteList.subject}</td>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<td class="color01">작성자에 의해 삭제된 게시글 입니다.</td>
					</c:otherwise>
				</c:choose>
				<td>${boardWriteList.userId}</td>
				<td>${boardWriteList.writeDateStr}</td>
			</tr>
			</c:forEach>
		</table>
		
		<div class="btnWrap">
			<a href="${pageContext.request.contextPath}/writeBoard?boardId=${param.boardId}">글쓰기</a>
		</div>
		
		<div class="pagination">
			<ul>
				<c:choose>
					<c:when test="${pageVO.page == 1}">
						<li class="disabled"><span><i class="fa fa-angle-double-left"></i></span></li>
						<li class="disabled"><span><i class="fa fa-angle-left"></i></span></li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="${pageContext.request.contextPath}/listBoard?boardId=${pageVO.boardId}&page=1&pageSize=10">
								<i class="fa fa-angle-double-left"></i>
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/listBoard?boardId=${pageVO.boardId}&page=${pageVO.page-1}&pageSize=${pageVO.pageSize}">
								<i class="fa fa-angle-left"></i>
							</a>
						</li>
					</c:otherwise>
				</c:choose>
				
				<c:forEach begin="1" end="${paginationSize}" step="1" var="i">
					<c:choose>
						<c:when test="${pageVO.page == i}">
							<li class="active"><span>${i}</span></li>
						</c:when>
						<c:otherwise>
							<li>
								<a href="${pageContext.request.contextPath}/listBoard?boardId=${pageVO.boardId}&page=${i}&pageSize=${pageVO.pageSize}">${i}</a>
							</li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${pageVO.page >= paginationSize}">
						<li class="disabled"><span><i class="fa fa-angle-right"></i></span></li>
						<li class="disabled"><span><i class="fa fa-angle-double-right"></i></span></li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="${pageContext.request.contextPath}/listBoard?boardId=${pageVO.boardId}&page=${pageVO.page+1}&pageSize=${pageVO.pageSize}">
								<i class="fa fa-angle-right"></i>
							</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/listBoard?boardId=${pageVO.boardId}&page=${paginationSize}&pageSize=${pageVO.pageSize}">
								<i class="fa fa-angle-double-right"></i>
							</a>
						</li>
					</c:otherwise>
				</c:choose>
				
			</ul>
		</div>

	</div>
</div>

<%@include file="/common/tail.jsp"%>