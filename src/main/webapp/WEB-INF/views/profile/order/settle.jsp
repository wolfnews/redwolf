<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>牛股会-订单结算</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="${base}static/ace/assets/css/ui.jqgrid.css" />
		<link rel="stylesheet" href="${base}static/css/alipay.css" />
		<jsp:include page="../../prof_base.jsp"></jsp:include>
		<script	src="${base}static/ace/assets/js/jqGrid/jquery.jqGrid.src.js"></script>
		<script	src="${base}static/ace/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
		<script src="${base}static/console/profile/settle.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="../header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='${base}';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
				var continues = '${continues}';
				var reason = '${reason}';
				var order = '${order}';
				var items = ${items};
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
								<a href="${base}profile/index.html"><strong>个人中心</strong></a>
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
									<form id="user_pay_form" action="${base}profile/order/pay.html" method="get">
									<div class="col-sm-10 col-sm-offset-1">
										<div id="head">
											<div class="hr hr12 dotted"></div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="form-input-readonly"><b>订单编号：</b> </label>
											<div class="col-sm-9">
												<input type="hidden" name="id" value="${order.id }">
												<input readonly="readonly" type="text" name="sn" class="col-xs-10 col-sm-5" id="order_sn" value="${order.sn }" />
											</div>
											<br><br>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="form-input-readonly"><b>订单名称：</b> </label>
											<div class="col-sm-9">
												<input readonly="readonly" type="text" name="subject" class="col-xs-10 col-sm-5" id="order_subject" value="${order.name }" />
											</div>
											<br><br>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="form-input-readonly"><b>付款金额： </b></label>
											<div class="col-sm-9">
												<input readonly="readonly" type="text" name="money" class="col-xs-10 col-sm-5" id="order_fee" value="${order.total }" />
											</div>
										<br><br>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="form-input-readonly"><b>订单描述：</b> </label>
											<div class="col-sm-9">
												<input readonly="readonly" type="text" name="body" class="col-xs-10 col-sm-5" id="order_desc" value="${order.desp}" />
											</div>
										</div><br>
										<div class="space-10"></div>
										<div>
											<table id="item_list"></table>
										</div>
										<div class="space-10"></div>
										<div >
											<label class="red" style="font-size: 17px"><strong>请选择支付方式 :</strong></label>
											<input id="pay_category" name="pay_category" type="radio" value="TENPAY" class="ace" checked="checked" />
											<span class="lbl"> <b>&nbsp;</b></span>
											<img alt="财付通" src="${base}static/images/tenpay.png">
<!-- 											<input id="pay_category" name="pay_category" type="radio" value="ALIPAY" class="ace" /> -->
<!-- 											<span class="lbl"> <b>&nbsp;</b></span> -->
<%-- 											<img alt="支付宝" src="${base}static/images/alipay.png"> --%>
										</div>
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
