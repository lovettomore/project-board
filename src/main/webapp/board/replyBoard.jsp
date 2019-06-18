<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="/common/header.jsp"%>

<script src="${pageContext.request.contextPath}/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

$(document).ready(function() {
	// Editor Setting
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors, // 전역변수 명과 동일해야 함.
		elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
		sSkinURI : "${pageContext.request.contextPath}/SE2/SmartEditor2Skin.html", // Editor HTML
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, 
		}
	});

	// 전송버튼 클릭이벤트
	$("#btnReply").click(function(){
		if(confirm("등록하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증
			if(validation()) {
				$("#replyBoardFrm").submit();
			}
		}else{
			return false;
		}
		
	});
	
	
	var inputList = "";
	var fileCnt = "";
	
	
	$("#plus").on("click", function(){
		if($(".fileWrap p").length < 5){
			fileCnt = $(".fileWrap input[type=file]").length + 1; 
			inputList = '<p id="fileList'+ fileCnt +'"><input type="file" id="attFile" name="attFile[]"> <input type="button" onclick="minus('+fileCnt+')" value="삭제"></p>';
			$(".fileWrap").append(inputList);
		}else{
			alert("파일은 5개 까지 추가가 가능합니다.");
		}
	});
	
});

// file 리스트 제거
function minus(minusNum){
	$("#fileList"+minusNum).remove();
}

// 필수값 Check
function validation(){
	var contents = $.trim(oEditors[0].getContents());
	if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
		alert("내용을 입력하세요.");
		oEditors.getById['smarteditor'].exec('FOCUS');
		return false;
	}

	return true;
}

</script>

<div class="contents">
	<h2 class="subTitle">Board Write</h2>
	
	<div class="writeBoardWrap">
		<form action="${pageContext.request.contextPath}/reply" method="post" id="replyBoardFrm" enctype="multipart/form-data">
			<input type="hidden" name="writeId" value="${boardWriteVO.writeId}">
			<ul>			
				<li><input type="text" id="subject" name="subject" placeholder="제목을 입력하세요."></li>
				<li><textarea name="smarteditor" id="smarteditor"></textarea></li>
				<li>첨부파일<input type="button" id="plus" value="파일추가"></li>
				<li>
					<div class="fileWrap">
						<p id="fileList1">
							<input type="file" id="attFile" name="attFile">
							<input type="button" onclick="minus(0)" value="삭제">
						</p>
					</div>
				</li>
			</ul>
			
			<div class="btnWrap">
				<input type="button" id="btnReply" value="답글등록"/>
			</div>
		</form>
	</div>		
</div>

<%@include file="/common/tail.jsp"%>