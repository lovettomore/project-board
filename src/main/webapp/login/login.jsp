<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>LOGIN</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/js.cookie.js"></script>
	
	<script>
		$(document).ready(function(){
			$("#userId").val(Cookies.get("userId"));
			
			$("#btnLogin").on("click", function(){
				$("#loginForm").submit();
			});
		});
	</script>
</head>
<body>
	<div id="wrap">
		<div class="loginWrap">
			<form action="${pageContext.request.contextPath}/login" method="post" id="loginForm">
				<div class="inputField">
					<h2>Login<span>Board Login Page</span></h2>
					<ul>
						<li>
							<label for="userId">User ID</label>
							<input type="text" id="userId" name="userId" placeholder="Please enter your ID." value="${param.userId}"></li>
						<li>
							<label for="userId">Password</label>
							<input type="password" id="pass" name="pass" placeholder="Please enter your Password." value="brown1234">
						</li>
						<li><button type="submit" id="btnLogin">Login</button></li>
					</ul>
				</div>
			</form>
		</div>
	</div>
</body>
</html>