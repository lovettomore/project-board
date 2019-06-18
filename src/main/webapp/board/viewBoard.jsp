<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="/common/header.jsp"%>

<script>
	
	$(document).ready(function(){
		
		//수정하기 버튼 클릭시
		$("#btnUpdate").on("click", function(){
			$("#recycleForm").submit();
		});
		
		//삭제하기 버튼 클릭시
		$("#btnDelete").on("click", function(){
			if(confirm("정말 삭제하시겠습니까?") == true){
				$("#recycleForm").attr("action", "${pageContext.request.contextPath}/deleteBoard");
				$("#recycleForm").attr("method", "post");
				$("#recycleForm").submit();
			}else{
				return false;
			}
		});
		
		//답글달기 버튼 클릭시
		$("#btnReply").on("click", function(){
			$("#recycleForm").attr("action", "${pageContext.request.contextPath}/reply");
			$("#recycleForm").submit();
		});
		
		//댓글 등록 버튼 클릭시
		$("#btnComments").on("click", function(){
			if($("#btnComments").attr("id") == "btnComments"){
				if(confirm("댓글을 등록 하시겠습니까?") == true){
					$("#recycleForm").attr("action", "${pageContext.request.contextPath}/comment");
				}else{
					return false;
				}
			}else{
				if(confirm("댓글을 수정 하시겠습니까?") == true){
					$("#recycleForm").attr("action", "${pageContext.request.contextPath}/updateComment");
				}else{
					return false;
				}
			}
			$("#recycleForm").attr("method", "post");
			$("#recycleForm").submit();
		});
		
		//댓글 수정 버튼 클릭시
		$(".commentsBox").find("#btnCommUpdate").on("click", function(){
			var txt = $(this).closest("ul").prev("p").text();
			document.getElementById("comment").value=txt;
			
			var commId = $(this).closest(".commentsItems").children("p").children(".blind").text();
			$("#commId").val(commId);
			
			$("#btnComments").attr("value", "댓글 수정");
			$("#btnComments").attr("id", "btnCommentUpdate");
		});
		
		//댓글 삭제 버튼 클릭시
		$(".commentsBox").find("#btnCommdelete").on("click", function(){
			if(confirm("삭제하시겠습니까?") == true){
				var commId = $(this).closest(".commentsItems").children("p").children(".blind").text();
				$("#commId").val(commId);
				
				$("#recycleForm").attr("action", "${pageContext.request.contextPath}/deleteComment");
				$("#recycleForm").attr("method", "post");
				$("#recycleForm").submit();
			}else{
				return false;
			}
			
		});
		
		var textCountLimit = 500;

		$('textarea[name=comment]').keyup(function() {
	        var textLength = $(this).val().length;
	        $('#textCount').text(textLength+"/500");
	        if (textLength > textCountLimit) {
	        	alert("500자 까지 입력이 가능 합니다. ");
	            $(this).val($(this).val().substr(0, textCountLimit));
	        }
	    });

		
	});
	
</script>

<div class="contents">

	<form id="recycleForm" action="${pageContext.request.contextPath}/updateWriteBoard" method="get">
		<input type="hidden" name="writeId" id="writeId" value="${param.writeId}">
		<input type="hidden" name="commId" id="commId">
		
		<div class="viewWrap">
			<div class="viewHeader">
				<h2>${boardWriteVO.subject}</h2>	
				<p>${boardWriteVO.userId} / ${boardWriteVO.writeDateStr}</p>
				<ul class="attachment">
					<c:forEach items="${boardFileList}" var="fileList">
					<li>
						<span>첨부파일</span>
						<a href="${pageContext.request.contextPath}/downloadFile?fileId=${fileList.fileId}">
							${fileList.original_file_name}
							<span class="downFile">다운로드</span>
						</a>
					</li>
					</c:forEach>
				</ul>
			</div>
			<div class="viewContent">
				${boardWriteVO.content}
			</div>
			<div class="btnWrap">
				<c:choose>
					<c:when test="${boardWriteVO.userId == userId}">
						<input type="button" id="btnUpdate" value="수정하기">
						<input type="button" id="btnDelete" value="삭제하기">
					</c:when>
				</c:choose>
				<input type="button" id="btnReply" value="답글달기">
				<input type="button" onclick="location.href='${pageContext.request.contextPath}/listBoard?boardId=${boardWriteVO.boardId}'" value="목록으로">
			</div>
			
			<div class="comments">
				<div class="commentsList">
				
					<c:forEach items="${boardCommentList}" var="comment">
					<div class="commentsItems">
						<p>
							<span class="commUserId">${comment.userId}</span>
							<span>${comment.writeDateStr}</span>
							<span class="blind">${comment.commId}</span>
						</p>
						<div class="commentsBox">
							<c:choose>
								<c:when test="${comment.use_yn == 'Y'}">
									<p>${comment.content}</p>
									<ul>
										<li>
											<input type="button" id="btnCommUpdate" value="수정">
											<input type="button" id="btnCommdelete" value="삭제">
										</li>
									</ul>
								</c:when>
								<c:otherwise>
									<p class="commentDelete">작성자에 의해 삭제된 댓글 입니다.</p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					</c:forEach>
					
				</div>
				<div class="commentsWrite">
					<p id="textCount">0/500</p>
					<textarea name="comment" id="comment" placeholder="댓글 내용을 입력하세요."></textarea>
					<input type="button" id="btnComments" value="댓글 등록">
				</div>
			</div>
		</div>
	</form>
</div>

<%@include file="/common/tail.jsp"%>