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
		<jsp:include page="../prof_base.jsp"></jsp:include>
	</head>
	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
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
							$(document).ready(function(){
								var result = ${result};
								if(result.success){
									$('#box_title').text(result.data.title);
									$('#box_date').text(result.data.gmtCreate);
									$('#box_author').text(result.data.authorName);
									$('#box_public').html(result.data.publicContent);
									if(result.message=='NSOSO'){
										html = "<span>更多内容请订阅后阅读...</span><div class=\"text-right\"><button class=\"btn btn-danger\" onclick=\"jumpToSubscribe("+result.data.author+","+result.data.id+")\"><strong>订阅宝盒</strong></button></div>";
										$('#box_private').html(html);
									}else{
										$('#box_private').html(result.data.privateContent);
									}
								}								
							});
							function jumpToSubscribe(author,box){
								location.href='../subscribe.html?author='+author+"&box="+box;
							}
						</script>
						<ul class="breadcrumb">
							<li>
								<strong>当前位置：</strong>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="../index.html"><strong>个人中心</strong></a>
							</li>
							<li class="active">
								<i class="ace-icon fa fa-user"></i>
								<span><strong>查看宝盒</strong></span>
							</li>
						</ul>
						<a href="#" onclick="javascript:window.history.back(-1);">返回</a>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
							<br>
								<div class="col-xs-6">
								<span class="red h4 strong" id="box_title"></span>
								</div>
								<div class="col-xs-2"></div>
								<div class="col-xs-4">
									<span id="box_author" class="red h4 strong"></span>&nbsp;&nbsp;|&nbsp;&nbsp;
									<span id="box_date" class="red h4 strong"></span>
								</div>
							</div>
							<div class="col-xs-12">
								<div class="hr"></div>
								<div class="col-xs-11" id="box_public"></div>
								<div class="col-xs-11" id="box_private"></div>
								<div class="col-xs-11"><hr></div>
								<div class="col-xs-11 text-center"><span style="color: rgb(128, 130, 133); font-family: 宋体, Helvetica, Arial, sans-serif; font-size: 14px; line-height: 21px;">风险提示：以上内容仅供参考，不作为投资决策依据，投资者据此操作，风险自担。股市有风险，投资需谨慎。</span></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
