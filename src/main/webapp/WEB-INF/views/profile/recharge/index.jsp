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
		<link rel="stylesheet" href="${base}static/css/alipay.css" />
		<jsp:include page="../../prof_base.jsp"></jsp:include>
		<script src="${base}static/ace/assets/js/fuelux/fuelux.spinner.js"></script>
		<script src="${base}static/ace/assets/js/ace/elements.spinner.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="../header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='${base}';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div id="sidebar" class="sidebar responsive"></div>
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
							function fastRecharge(money){
								$('#charge_account').val(money)
							}
							function nextStep(){
								location.href=base+'profile/recharge/confirm.html?amount='+$('#charge_account').val();
							}
						</script>
						<ul class="breadcrumb">
							<li>
								<strong>当前位置：</strong>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="index.html"><strong>个人中心</strong></a>
							</li>
							<li class="active">
								<i class="ace-icon fa fa-cash"></i>
								<span><strong>充值</strong></span>
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
											<div id="step-2" class="col-xs-11 label label-xlg  arrowed-in arrowed-right">
												<b>充值订单确认</b>
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
												<b>自动充值</b>
											</div>
										</div>
									</div>
								</div>
								<div class="space-10"></div>
								<div class="hr hr12 dotted"></div>
							</div>
							<div class="col-xs-12">
								<div class="row"  id="step-1-div" style="display: block;">
									<div class="col-sm-10 col-sm-offset-1 well">
										<label class="red" style="font-size: 17px"><strong>请输入充值金额 :</strong></label>
										<input type="text" class="input" id="charge_account" value="1"/>&nbsp;&nbsp;<b>.00(元)</b>
										&nbsp;&nbsp;<label class="black" style="font-size: 17px"><strong>快速充值：</strong></label>
										<button class="btn btn-danger" onclick="fastRecharge(100)"><strong> 100元 </strong></button>&nbsp;&nbsp;
										<button class="btn btn-danger" onclick="fastRecharge(200)"><strong> 200元 </strong></button>&nbsp;&nbsp;
										<button class="btn btn-danger" onclick="fastRecharge(500)"><strong> 500元 </strong></button>&nbsp;&nbsp;
										<button class="btn btn-danger" onclick="fastRecharge(1000)"><strong> 1000元 </strong></button>
										<div class="space-10"></div>
									</div>
									<br><br>
<!-- 									<div class="col-sm-10 col-sm-offset-1 well"> -->
<!-- 										<label class="red" style="font-size: 17px"><strong>请选择支付方式 :</strong></label> -->
<!-- 										<input id="pay_category" name="pay_category" type="radio" value="ALIPAY" class="ace" checked="checked" /> -->
<!-- 										<span class="lbl"> <b>&nbsp;</b></span> -->
<%-- 										<img alt="支付宝" src="${base}static/images/alipay.png"> --%>
<!-- 										<input id="pay_category" name="pay_category" type="radio" value="TENPAY" class="ace" /> -->
<!-- 										<span class="lbl"> <b>&nbsp;</b></span> -->
<%-- 										<img alt="财付通" src="${base}static/images/tenpay.png"> --%>
<!-- 									</div> -->
									<div class="col-sm-10 col-sm-offset-1">
										<div class="hr hr12 dotted"></div>
										<div align="right">
											<button class="btn btn-danger" onclick="nextStep()"><strong> 下一步 </strong></button>
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
