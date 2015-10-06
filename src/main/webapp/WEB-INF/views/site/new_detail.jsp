<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>牛股会-行业资讯</title>
		<meta name="Keywords" content="牛股会">
		<meta name="Description" content="牛股会">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<jsp:include page="../site_base.jsp"></jsp:include>
		<script src="${base}static/ace/jquery.twbsPagination.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='${base}';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
				$(document).ready(function(){
					var exist = ${exist};
					if(!exist){
						$('#news_detail_div').empty();
						$('#news_detail_div').html("<span class=\"red h4 strong\"><strong>很抱歉！您查看的新闻可能已被删除...</strong></span>")
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
								<a href="${base}news.html"><strong>行业资讯</strong></a>
							</li>
							<li class="active">
								<i class="ace-icon fa fa-user"></i>
								<span><strong>查看新闻</strong></span>
							</li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row" id="news_detail_div">
							<div class="col-xs-10 col-xs-offset-1">
							<br>
								<div class="col-xs-6">
								<span class="red h4 strong" id="news_title">${news.title}</span>
								</div>
								<div class="col-xs-3">
									<span class="red h4 strong">&nbsp;&nbsp;&nbsp;&nbsp;</span>
									<span id="news_date" class="red h4 strong">${news.gmtCreate}</span>
								</div>
								<div class="col-xs-3">
								<span class="red h4 strong">浏览次数：${news.browseTimes}</span>
								</div>
							</div>
							<div class="col-xs-10 col-xs-offset-1">
								<div class="hr"></div>
								<div class="col-xs-12" id="news_content">${news.content}</div>
								<div class="col-xs-12"><hr></div>
								<div class="col-xs-12 text-center"><span style="color: rgb(128, 130, 133); font-family: 宋体, Helvetica, Arial, sans-serif; font-size: 14px; line-height: 21px;">风险提示：以上内容仅供参考，不作为投资决策依据，投资者据此操作，风险自担。股市有风险，投资需谨慎。</span></div>
							</div>
						</div>
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->
			<jsp:include page="../foot.jsp"></jsp:include>
		</div><!-- /.main-container -->
	</body>
</html>