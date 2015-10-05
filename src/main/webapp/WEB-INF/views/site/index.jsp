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
<!-- 		<meta http-equiv="refresh" content="0;url=http://www.baidu.com"> -->
		<title>欢迎来到牛股会</title>
		<meta name="Keywords" content="牛股会">
		<meta name="Description" content="牛股会">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="<%=basePath%>static/css/main.css" />
		<script src="<%=basePath%>static/ace/assets/js/jquery.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootstrap.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/ace.js"></script>
		<script type="text/javascript" src="<%=basePath%>static/js/nav.js"></script>
		<script type="text/javascript" src="<%=basePath%>static/js/index.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='<%=basePath%>';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
				$(document).ready(function(){
				});
				
			</script>
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>
						<ul class="breadcrumb">
							<li class="active">
<!-- 								<strong>当前位置：</strong> -->
<!-- 								<i class="ace-icon fa fa-home home-icon"></i> -->
<!-- 								<a href="index.html"><strong>首页</strong></a> -->
							</li>
						</ul>
					</div>
					<div class="page-content" style="background-color: #ea3f2c">
						<div class="row center">
							<div class="col-xs-6">
								 <img alt="" width="640" height="500" src="static/images/bg_img.jpg">
							</div>
							<div class="col-xs-6">
								<div class="col-xs-12 white" id="notice_board" style="height: 320px;">
									<div class="space-15"></div>
									<h2 class="left white"><b>热烈庆祝红狼军团旗下首款app综合资讯平台<br/>(牛股会) 震撼上市！</b></h2>	
									<h4  class="left white" style="text-align: left;">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									随着《红狼军团》近几年的快速发展，旗下粉丝也呈几何数字增长！应广大朋友的建议，为了能够及时方便的让投资者接收相关信息，
									本公司首款app综合资讯平台（牛股会）震撼上市！专业的投资团队，成熟的操作系统，优越的战绩为您的投资保驾护航，
									让股神做您的私人助理！&nbsp;<b>818不止有苏宁!牛股会将会以更大的力度回馈新老用户：</b>&nbsp;即日起凡注册登录即送188元现金大礼，
									5000万红包大礼等你来拿！扫描下方二维码即可下载牛股宝盒客户端。小伙伴们赶紧行动吧! 
									</h4>
									<h3 class="center white"><b>活动期间：2015-08-13 15:12:00至2015-09-17 15:12:00</b></h3>	
								</div>
								<div class="col-xs-12" style="height: 180px;">
									<div class="col-sm-5">
										<img alt="Android版本下载" width="178px" height="178px" src="<%=basePath%>static/images/android.jpg">
										<div class="space-4"></div>
										<label class="center white"><b>Android版本</b></label>								
									</div>
									<div class="col-sm-1"></div>
									<div class="col-sm-5">
<%-- 										<img alt="IOS版本下载" width="178px" height="178px" src="<%=basePath%>static/images/ios.jpg"> --%>
<!-- 										<div class="space-4"></div> -->
<!-- 										<label class="center white"><b>IOS版本</b></label> -->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="../foot.jsp"></jsp:include>
		</div>
	</body>
</html>
