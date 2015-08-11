<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta name="360-site-verification"
	content="16fef83fb2c88e83913adfb5e8d308c0" />
<title>欢迎光临牛股会</title>
<meta name="Keywords" content="牛股会">
<meta name="Description" content="牛股会">
<link rel="stylesheet" href="<%=basePath%>static/css/main.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/nav.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/index.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div style="background-color: #ea3f2c">
		<img alt="" src="static/images/bg_img.png">
	</div>
	<jsp:include page="../foot.jsp" />
</body>
</html>
