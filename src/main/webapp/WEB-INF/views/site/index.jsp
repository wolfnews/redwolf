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
							</li>
						</ul>
					</div>
					<div class="page-content">
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
							      <li data-target="#main_carousel" data-slide-to="5"></li>
							   </ol>   
							   <!-- 轮播（Carousel）项目 -->
							   <div class="carousel-inner" style="height: 500px;text-align: center;">
							      <div class="item active" align="center">
							         <img src="${base}static/images/android.jpg" class="img-responsive;" alt=""  >
							      </div>
							      <div class="item" align="center">
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
