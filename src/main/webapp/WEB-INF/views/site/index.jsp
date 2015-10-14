<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>欢迎来到牛股会</title>
		<meta name="Keywords" content="牛股会">
		<meta name="Description" content="牛股会">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<jsp:include page="../site_base.jsp"></jsp:include>
	</head>
	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='${base}';
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
					<div class="page-content">
<!-- 						<div class="row center"> -->
<!-- 							<div class="col-xs-6"> -->
<!-- 								 <img alt="" width="100%" height="100%" src="static/images/bg_img.jpg"> -->
<!-- 							</div> -->
<!-- 							<div class="col-xs-6"> -->
<!-- 								<div class="col-xs-12 white" id="notice_board" style="height: 320px;"> -->
<!-- 									<div class="space-15"></div> -->
<!-- 									<h2 class="left white"><b>热烈庆祝红狼军团旗下综合资讯平台上线！<br/></b></h2>	 -->
<!-- 									<h4  class="left white" style="text-align: left;"> -->
<!-- 									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 									随着《红狼军团》近几年的快速发展，旗下粉丝也呈几何数字增长！应广大朋友的建议，为了能够及时方便的让投资者接收相关信息， -->
<!-- 									 本公司现推出牛股会资讯平台！专业的投资团队，成熟的操作系统，优越的战绩为您的投资保驾护航， 让股神做您的私人助理！为回馈新老用户 -->
<!-- 									 ：<br/><br/><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 									 即日起至国庆期间，凡在线注册登录即送188元现金大礼， 5000万红包大礼等你来拿！小伙伴们赶紧行动吧!</b>  -->
<!-- 									</h4> -->
<!-- 									<h3 class="center white"><b>活动期间：2015-10-01至2015-10-10</b></h3>	 -->
<!-- 								</div> -->
<!-- 								<div class="col-xs-12" style="height: 180px;"> -->
<!-- 									<div class="col-sm-5"> -->
<%-- 										<img alt="Android版本下载" width="178px" height="178px" src="${base}static/images/android.jpg"> --%>
<!-- 										<div class="space-4"></div> -->
<!-- 										<label class="center white"><b>Android版本</b></label>								 -->
<!-- 									</div> -->
<!-- 									<div class="col-sm-1"></div> -->
<!-- 									<div class="col-sm-5"> -->
<%-- 										<img alt="IOS版本下载" width="178px" height="178px" src="${base}static/images/ios.jpg"> --%>
<!-- 										<div class="space-4"></div> -->
<!-- 										<label class="center white"><b>IOS版本</b></label> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="row">
							<div class="col-xs-1"></div>
							<div id="main_carousel" class="col-xs-10 carousel slide center">
								<!-- 轮播（Carousel）指标 -->
							   <ol class="carousel-indicators">
							      <li data-target="#main_carousel" data-slide-to="0" 
							         class="active"></li>
							      <li data-target="#main_carousel" data-slide-to="1"></li>
							      <li data-target="#main_carousel" data-slide-to="2"></li>
							      <li data-target="#main_carousel" data-slide-to="3"></li>
							      <li data-target="#main_carousel" data-slide-to="4"></li>
							   </ol>   
							   <!-- 轮播（Carousel）项目 -->
							   <div class="carousel-inner" style="height: 500px;text-align: center;">
							      <div class="item active" align="center">
							         <img src="${base}static/images/bg-1.jpg" class="img-responsive;" alt=""  >
							      </div>
							      <div class="item" align="center">
							         <img src="${base}static/images/bg-2.jpg" class="img-responsive" alt="">
							      </div>
							      <div class="item" align="center">
							         <img src="${base}static/images/bg-3.jpg" class="img-responsive" alt="">
							      </div>
							      <div class="item" align="center">
							         <img src="${base}static/images/bg-4.jpg" class="img-responsive" alt="">
							      </div>
							   </div>
							   <!-- 轮播（Carousel）导航 -->
							   <a class="carousel-control left" href="#main_carousel" 
							      data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
							   <a class="carousel-control right" href="#main_carousel" 
							      data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="../foot.jsp"></jsp:include>
		</div>
	</body>
</html>
