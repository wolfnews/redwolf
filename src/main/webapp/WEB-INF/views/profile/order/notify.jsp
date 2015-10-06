<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>牛股会-支付结果</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="${base}static/css/alipay.css" />
		<jsp:include page="../../prof_base.jsp"></jsp:include>
	</head>
	<body class="no-skin">
		<jsp:include page="../header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='${base}';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
				$(document).ready(function(){
					var success = ${success};
					$('#wait_notify_div').empty();
					if(success){
						$('#wait_notify_div').text('<label><b>订单处理成功!</b></label>');
					}else{
						$('#wait_notify_div').text('<label><b>订单处理异常!</b></label>');
					}
				});
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
								<a href="../profile/index.html"><strong>个人中心</strong></a>
							</li>
							<li class="active">
								<i class="ace-icon fa fa-cash"></i>
								<span><strong>支付结果</strong></span>
							</li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<div class="hr hr12 dotted"></div>
								<div class="space-10"></div>
								<div class="col-sm-10 col-sm-offset-1 well">
									<div class="text-center" id="wait_notify_div">
										<div id="pay_result_div"></div>
										<i class="ace-icon fa fa-spinner fa-spin red bigger-275"></i>
										<span class="bigger-175">订单处理中...</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>