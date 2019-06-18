<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="/common/header.jsp"%>

<script>
	$(document).ready(function(){
		
		var msg = "${param.updateCnt}";
		
		if(msg == 1){
			alert("수정이 완료 되었습니다.");
		}
		
		
		$("#updateBoardFrm").find(".btnStyle01").on("click", function(){
			var subject = $(this).closest("ul").children("li:nth-child(1)").children("input").val();
			var useBoard = $(this).closest("ul").children("li:nth-child(2)").children("select").val();
			var boardId = $(this).closest("ul").children("li:nth-child(4)").children("input").val();
			
			$("#subject").val(subject);
			$("#useBoard").val(useBoard);
			$("#boardId").val(boardId);
			
			console.log(boardId);
			console.log(useBoard);
			console.log(subject);
			
			$("#updateBoardFrm").submit();
		
		});
		
		$("#btnInsert").click(function(){
			
	 		var con_test = confirm("등록 하시겠습니까?");
	 		if(con_test == true){
	 			$("#insertForm").submit();
	 		}else{
	 			return false;
	 		}
			
		});
		
	});
	
	
	
</script>

<div class="contents">
	<h2 class="subTitle">Board Management</h2>
	
	<div class="addBoardWrap">
		<form id="insertForm" action="${pageContext.request.contextPath}/regBoard" method="post">
			<h2>게시판 생성</h2>
			<ul>
				<li><input type="text" name="subject" placeholder="게시판 이름을 입력해 주세요."></li>
				<li>
					<select name="useBoard">
						<option value="Y">사용</option>
						<option value="N">사용안함</option>
					</select>
				</li>
				<li><input type="button" id="btnInsert" value="게시판 생성"></li>
			</ul>
		</form>
	</div>
	
	<div class="addBoardWrap">
		<form action="${pageContext.request.contextPath}/regBoard" method="post" id="updateBoardFrm">
			<input type="hidden" name="subject" id="subject">
			<input type="hidden" name="useBoard" id="useBoard">
			<input type="hidden" name="boardId" id="boardId">
			
			<h2>게시판 리스트</h2>
			<c:forEach items="${boardList}" var="board">
			<ul>			
				<li><input type="text" id="boardName" name="boardName" value="${board.name}"></li>
				<li>
					<select name="useBoard">
						<c:if test="${board.use_yn == 'Y'}">
							<option value="Y">사용</option>
							<option value="N">사용안함</option>
						</c:if>
						<c:if test="${board.use_yn == 'N'}">
							<option value="N">사용안함</option>
							<option value="Y">사용</option>
						</c:if>
					</select>
				</li>
				<li><input type="button" class="btnStyle01" value="게시판 수정"></li>
				<li class="blind"><input type="hidden" name="boardId" id="boardId" value="${board.boardId}"></li>
			</ul>
			</c:forEach>
		</form>
	</div>
	
</div>

<%@include file="/common/tail.jsp"%>