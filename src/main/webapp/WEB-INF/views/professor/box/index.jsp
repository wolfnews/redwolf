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
		<title>牛股会讲师平台-我的宝盒</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ui.jqgrid.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<script src="<%=basePath%>static/ace/assets/js/jquery.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace-extra.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootstrap.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootbox.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/ace.js"></script>
		<script	src="<%=basePath%>static/ace/assets/js/jqGrid/jquery.jqGrid.src.js"></script>
		<script	src="<%=basePath%>static/ace/assets/js/jqGrid/i18n/grid.locale-en.js"></script>
		<script	src="<%=basePath%>static/console/professor/box.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="../header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='<%=basePath%>';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
				function jump2addBox(){
					location.href=base+'professor/box/add.html';
				}
			</script>
			<jsp:include page="../nav.jsp">
				<jsp:param value="professor_box" name="page_index"/>
			</jsp:include>			
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="<%=basePath%>professor/index.html"><strong>首页</strong></a>
							</li>
							<li class="active"><b>我的宝盒</b></li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<div class="well well-sm">
									<button class="btn btn-sm btn-danger" onclick="jump2addBox()"> 
										<i class="ace-icon fa fa-pencil-square-o bigger-125"></i>
										<b>制作宝盒</b>
									</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div>
									<table id="professor_box_list"></table>
									<div id="professor_box_page"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="../../foot.jsp"></jsp:include>
		</div>
	</body>
</html>
