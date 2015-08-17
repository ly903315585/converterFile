<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>在Form表单中使用验证码</title>
<script type="text/javascript">
	//刷新验证码
	function changeImg() {
		document.getElementById("validateCodeImg").src = "${pageContext.request.contextPath}/servlet/DrawImage?createTypeFlag="
				+ createTypeFlag + "&" + Math.random();
	}
</script>
</head>

<body>
	<form action="${pageContext.request.contextPath}/servlet/CheckServlet" method="post">
		数字字母混合验证码：
		<input type="text" name="validateCode" /> 
		<img alt="验证码看不清，换一张" id="validateCodeImg1" onclick="changeImg()"
			src="${pageContext.request.contextPath}/servlet/DrawImage" > 
		<a href="javascript:void(0)" onclick="changeImg(this,'nl')">看不清，换一张</a> 
		<br /> 
		
		中文验证码：
		<input type="text" name="validateCode" /> 
		<img alt="验证码看不清，换一张" id="validateCodeImg2" onclick="changeImg()"
			src="${pageContext.request.contextPath}/servlet/DrawImage?createTypeFlag=ch" > 
		<a href="javascript:void(0)" onclick="changeImg(this,'cn')">看不清，换一张</a> 
		<br /> 
		
		英文验证码：
		<input type="text" name="validateCode"/>
		<img alt="验证码看不清，换一张" id="validateCodeImg3" onclick="changeImg()"
			src="${pageContext.request.contextPath}/servlet/DrawImage?createTypeFlag=l" > 
		<a href="javascript:void(0)" onclick="changeImg(this,'l')">看不清，换一张</a> 
		<br />
		
		数字验证码：
		<input type="text" name="validateCode"/>
		<img alt="验证码看不清，换一张" id="validateCodeImg4" onclick="changeImg()"
			src="${pageContext.request.contextPath}/servlet/DrawImage?createTypeFlag=n" > 
		<a href="javascript:void(0)" onclick="changeImg(this,'n')">看不清，换一张</a> 
		<br />
		
		<input type="submit" value="提交">
	</form>
</body>
</html>