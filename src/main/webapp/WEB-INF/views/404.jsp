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
<title>红狼军团中国财经在线实战交流第一平台</title>
<meta name="Keywords" content="红狼军团">
<meta name="Description" content="红狼军团">
<link rel="stylesheet" href="<%=basePath%>static/css/base.css" />
<link rel="stylesheet" href="<%=basePath%>static/css/main.css" />
<link rel="stylesheet" href="<%=basePath%>static/css/rotaion.css" />
<link rel="stylesheet" href="<%=basePath%>static/css/ue_grid.css" />
<link rel="stylesheet" href="<%=basePath%>static/css/login.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/base.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/nav.js"></script>
<script type="text/javascript" src="<%=basePath%>static/thirdparty/rotaion.js"></script>
<script type="text/javascript" src="<%=basePath%>static/thirdparty/jquery.easing.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/thirdparty/jquery.tabso_yeso.js"></script>
<script type="text/javascript" src="<%=basePath%>static/thirdparty/jquery.leanModal.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/js/index.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />

	<jsp:include page="foot.jsp" />
</body>
</html>
