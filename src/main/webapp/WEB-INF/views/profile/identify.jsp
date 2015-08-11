<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String authUser = (String)request.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>牛股会用户注册</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<script src="<%=basePath%>static/ace/assets/js/jquery.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace-extra.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootbox.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootstrap.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/ace.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<div class="main-content">
				<div class="main-content-inner">
					<div class="page-content">
						<div class="row" id="tips_content">
							<label> 感谢您注册牛股汇!您的账号:<span>  
							<font color=red><strong>${username}</strong></font></span>已经注册成功!  
							系统将会在<strong id="endtime" class="red"></strong>秒后跳转到登录页！  
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
							<a class="red" href="login.html">直接登录</a></label>
						</div>
						<script type="text/javascript">  
							var base='<%=basePath%>';
							var i = 5;  
							function remainTime(){  
							    if(i==0){  
							        location.href=base+'profile/login.html';  
							    }  
							    document.getElementById('endtime').innerHTML=i--;  
							    setTimeout("remainTime()",1000);  
							}  
							remainTime();  
						</script>  
					</div>
				</div>
			</div>
			<jsp:include page="../foot.jsp"></jsp:include>
		</div>
	</body>
</html>
