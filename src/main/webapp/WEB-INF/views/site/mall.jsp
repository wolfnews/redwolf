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
		<title>牛股会-牛股商城</title>
		<meta name="Keywords" content="牛股会">
		<meta name="Description" content="牛股会">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="<%=basePath%>static/css/main.css" />
		<script src="<%=basePath%>static/ace/assets/js/jquery.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootbox.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootstrap.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/ace.js"></script>
		<script src="<%=basePath%>static/ace/jquery.twbsPagination.js"></script>
		<script src="<%=basePath%>static/console/base.js"></script>
		<script src="<%=basePath%>static/console/site/mall.js"></script>
		<script type="text/javascript" src="<%=basePath%>static/js/nav.js"></script>
		<script type="text/javascript" src="<%=basePath%>static/js/index.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='<%=basePath%>';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
				total = ${total};
			</script>
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>
						<ul class="breadcrumb">
							<li class="active">
								<strong>当前位置：</strong>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="<%=basePath%>professor.html"><strong>牛股商城</strong></a>
							</li>
						</ul>
						<div class="nav-search">
							<button class="btn btn-danger" id="my_carts">
								<i class='ace-icon fa fa-shopping-cart bigger-128'></i>
								我的购物车
							</button>
						</div>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-10 col-xs-offset-1">
								<div id="item_list"></div>
							</div>
							<div class="col-xs-10 col-xs-offset-1">
								<div class="text-right">
					                <ul id="item_pager"></ul>
					            </div>
							</div>
							
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="../foot.jsp"/>
		</div>
	</body>
</html>
