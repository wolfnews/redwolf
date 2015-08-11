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
		<title>支付宝支付返回接口</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="<%=basePath%>static/css/alipay.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<script src="<%=basePath%>static/ace/assets/js/jquery.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace-extra.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootbox.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootstrap.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/ace.js"></script>
		<script src="<%=basePath%>static/console/base.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='<%=basePath%>';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div id="sidebar" class="sidebar responsive"></div>
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
								<a href="../profile/index.html"><strong>个人中心</strong></a>
							</li>
							<li class="active">
								<i class="ace-icon fa fa-cash"></i>
								<span><strong>支付确认</strong></span>
							</li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12" >
								<div class="row">
									<h3 class="widget-title red lighter">
										<i class="ace-icon fa fa-leaf red"></i>
										<b>用户充值</b>
									</h3>
									<hr>
									<div class="space-2"></div>
									<div class="col-sm-3">
										<div class="row">
											<div id="step-1" class="col-xs-11 label label-xlg label-danger arrowed-right">
												<b>填写充值信息</b>
											</div>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="row">
											<div id="step-2" class="col-xs-11 label label-xlg label-danger arrowed-in arrowed-right">
												<b>充值订单确认</b>
											</div>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="row">
											<div id="step-3" class="col-xs-11 label label-xlg label-danger arrowed-in arrowed-right">
												<b>订单支付确认</b>
											</div>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="row">
											<div id="step-4" class="col-xs-11 label label-xlg arrowed-in">
												<b>自动充值</b>
											</div>
										</div>
									</div>
								</div>
								<div class="space-10"></div>
							</div>
							<div class="col-xs-12">
								<div class="row"  id="step-2-div" style="display: block;">
									<div class="col-sm-10 col-sm-offset-1">
										<div id="head">
								            <div class="alipay_link">
								                <a target="_blank" href="http://www.alipay.com/"><span>支付宝首页</span></a>|
								                <a target="_blank" href="https://b.alipay.com/home.htm"><span>商家服务</span></a>|
								                <a target="_blank" href="http://help.alipay.com/support/index_sh.htm"><span>帮助中心</span></a>
									            <span class="title">支付宝即时到账交易接口快速通道</span>
								            </div>
											<div class="hr hr12 dotted"></div>
										</div>
									<div class="space-10"></div>
									<div class="col-sm-10 col-sm-offset-1">
										<div class="text-center" id="waiting_return_div">
											<i class="ace-icon fa fa-spinner fa-spin red bigger-275"></i>
											<span class="bigger-175">支付确认中...</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->
	</body>
</html>
