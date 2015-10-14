<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>牛股会-个人中心</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="${base}static/ace/assets/css/ui.jqgrid.css" />
		<jsp:include page="../../prof_base.jsp"></jsp:include>
		<script	src="${base}static/ace/assets/js/jqGrid/jquery.jqGrid.src.js"></script>
		<script	src="${base}static/ace/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
		<script src="${base}static/console/profile/order.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="../header.jsp"/>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='${base}';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
				var type='${type}';
				$(document).ready(function(){
					if("unfinish" == type){
						$('#current_label').text("已完成订单");
					}else if("finish" == type){
						$('#current_label').text("未完成订单");
					}else{
						$('#current_label').text("全部订单");
					}
				})
			</script>
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
								<a href="index.html"><strong>个人中心</strong></a>
							</li>
							<li class="active bold">
								<i class="ace-icon fa fa-list"></i>
								<span id="current_label"></span>
							</li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<div>
									<table id="profile_order_list"></table>
									<div id="profile_order_page"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
				<jsp:include page="../../foot.jsp"/>
		</div>
	</body>
</html>
