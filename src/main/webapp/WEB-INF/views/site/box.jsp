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
		<script src="${base}static/console/site/box.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='${base}';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
				total = ${total};
				author = ${author};
			</script>
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>
						<ul class="breadcrumb">
							<li class="active">
								<strong>当前位置：</strong>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="${base}box.html"><strong>牛股宝盒</strong></a>
							</li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-10 col-xs-offset-1 ">
								<div id="box_list"></div>
								<div class="text-right">
					                <ul id="box_pager"></ul>
					            </div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="../foot.jsp"></jsp:include>
		</div>
	</body>
</html>
