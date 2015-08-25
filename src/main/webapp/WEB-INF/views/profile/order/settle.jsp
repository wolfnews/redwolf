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
		<title>牛股会-订单结算</title>
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
		<jsp:include page="../header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='<%=basePath%>';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
							function fastRecharge(money){
								$('#charge_account').val(money)
							}
							function gotoRecharge(){
								location.href=base+'alipay/index.html?amount='+$('#charge_account').val();
							}
						</script>
						<ul class="breadcrumb">
							<li>
								<strong>当前位置：</strong>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="../profile/index.html"><strong>个人中心</strong></a>
							</li>
							<li class="active">
								<i class="ace-icon fa fa-cash"></i>
								<span><strong>订单结算</strong></span>
							</li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-10 col-xs-offset-1" >
								<div class="row">
									<h3 class="widget-title red lighter">
										<i class="ace-icon fa fa-leaf red"></i>
										<b>订单结算</b>
									</h3>
									<hr>
									<div class="space-2"></div>
									<div class="col-sm-3">
										<div class="row">
											<div id="step-1" class="col-xs-11 label label-xlg label-danger arrowed-right">
												<b>选择商品</b>
											</div>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="row">
											<div id="step-2" class="col-xs-11 label label-xlg label-danger arrowed-in arrowed-right">
												<b>订单结算</b>
											</div>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="row">
											<div id="step-3" class="col-xs-11 label label-xlg arrowed-in arrowed-right">
												<b>订单支付</b>
											</div>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="row">
											<div id="step-4" class="col-xs-11 label label-xlg arrowed-in">
												<b>订单处理</b>
											</div>
										</div>
									</div>
								</div>
								<div class="space-10"></div>
							</div>
							<div class="col-xs-12">
								<div class="row"  id="step-2-div" style="display: block;">
									<form id="user_pay_form" action="<%=basePath%>alipay/pay.html" method="get">
									<div class="col-sm-10 col-sm-offset-1">
										<div id="head">
											<div class="hr hr12 dotted"></div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="form-input-readonly"><b>充值订单号：</b> </label>
											<div class="col-sm-9">
												<input readonly="readonly" type="text" name="sn" class="col-xs-10 col-sm-5" id="order_sn" value="${orderSn }" />
											</div>
											<br><br>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="form-input-readonly"><b>订单名称：</b> </label>
											<div class="col-sm-9">
												<input readonly="readonly" type="text" name="subject" class="col-xs-10 col-sm-5" id="order_subject" value="${orderName }" />
											</div>
											<br><br>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="form-input-readonly"><b>付款金额： </b></label>
											<div class="col-sm-9">
												<input readonly="readonly" type="text" name="money" class="col-xs-10 col-sm-5" id="order_fee" value="${orderMoney }" />
											</div>
										<br><br>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="form-input-readonly"><b>订单描述：</b> </label>
											<div class="col-sm-9">
												<input readonly="readonly" type="text" name="body" class="col-xs-10 col-sm-5" id="order_desc" value="牛股会用户充值订单" />
											</div>
										</div>
										<div class="space-10"></div>
									</div>
									</form>
									<div class="space-10"></div>
									<div class="col-sm-10 col-sm-offset-1">
										<div class="hr hr12 dotted"></div>
										<div align="right">
											<font class="note-help"><b>如果您点击“确认支付”按钮，即表示您同意该次的执行操作。 </b></font>
											<button class="btn btn-danger" onclick="submitOrder()"><strong> 确认并支付 </strong></button>
										</div>
										<script type="text/javascript">
											function submitOrder(){
												$('#user_pay_form').submit();
											}
										</script>
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
