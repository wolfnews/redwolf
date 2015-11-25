<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String authUser = (String) request.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta name="360-site-verification"
	content="16fef83fb2c88e83913adfb5e8d308c0" />
<title>牛股会</title>
<meta name="Keywords" content="牛股会">
<meta name="Description" content="牛股会">
</head>
<body>
	<p>我们找了好多地方也没有找到这个页面</p>
	<jsp:include page="foot.jsp" />
</body>
</html>
