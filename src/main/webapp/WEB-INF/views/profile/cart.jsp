<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>牛股商城-我的购物车</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="${base}static/ace/assets/css/ui.jqgrid.css" />
		<jsp:include page="../prof_base.jsp"></jsp:include>
		<script	src="${base}static/ace/assets/js/jqGrid/jquery.jqGrid.src.js"></script>
		<script	src="${base}static/ace/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
		<script src="${base}static/console/profile/cart.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='${base}';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
<!-- 			<div id="sidebar" class="sidebar responsive"></div> -->
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
						<ul class="breadcrumb">
							<li>
								<strong>当前位置：</strong>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="${base}profile/index.html"><strong>个人中心</strong></a>
							</li>
							<li class="active bold">
								<i class='ace-icon fa fa-shopping-cart bigger-128'></i>
								<span id="current_label"><b>我的购物车</b></span>
							</li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<table id="item_list"></table>
								<div id="item_page"></div>	
							</div>
						</div>
						<div class="hr hr12 dotted"></div>
						<div align="right">
							<button class="btn btn-danger" id="visit_more_btn">
							<i class='ace-icon fa fa-shopping-cart bigger-128'></i>
							<strong> 再逛逛 </strong></button>
							<button class="btn btn-danger" id="goto_settle_btn">
							<i class='ace-icon fa fa-credit-card bigger-128'></i>
							<strong> 去结算 </strong></button>
						</div>
					</div>
					<jsp:include page="../foot.jsp"/>
				</div>
			</div>
		</div>
	</body>
</html>
