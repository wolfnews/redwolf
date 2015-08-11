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
		<title>牛股会后台管理-添加新闻</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/select2.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<script src="<%=basePath%>static/ace/assets/js/jquery.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/jquery.hotkeys.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace-extra.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootbox.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/select2.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootstrap.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootstrap-wysiwyg.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/ace.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/elements.wysiwyg.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/elements.colorpicker.js"></script>
		<script	src="<%=basePath%>static/console/manager/add_news.js"></script>
		<script src="<%=basePath%>static/console/base.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="../header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='<%=basePath%>';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<jsp:include page="../nav.jsp">
				<jsp:param value="professor_notice" name="page_index"/>
			</jsp:include>			
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="<%=basePath%>manage/index.html"><strong>首页</strong></a>
							</li>
							<li>
								<i class="ace-icon fa fa-briefcase"></i>
								<a href="<%=basePath%>manage/news.html"><strong>新闻管理</strong></a>
							</li>
							<li class="active">
								<i class="ace-icon fa fa-pencil-square-o bigger-125"></i>
								<strong>添加新闻</strong>
							</li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>标题</strong> </label>
										<div class="col-sm-9">
											<input type="text" id="news_title" placeholder="请输入新闻标题" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>关键词</strong> </label>
										<div class="col-sm-9">
											<input type="text" id="news_keyword" placeholder="请输入新闻关键词" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>类型</strong> </label>
										<div class="col-sm-9">
											<select id="news_category" name="news_category" placeholder="请选择新闻类型" style="width: 200px">
												<option value="POLICY">政策</option>
												<option value="INVEST">投资</option>
												<option value="STOCKS">股票</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>内容</strong> </label>
										<div class="col-sm-9">
											<div class="wysiwyg-editor" id="news_content"></div>
										</div>
									</div>
									<div class="clearfix form-actions">
										<div class="col-md-offset-5 col-md-9">
											<button class="btn btn-info" type="button" onclick="addNews()">
												<i class="ace-icon fa fa-check bigger-110"></i>
												<strong>保存新闻</strong>
											</button>

											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset">
												<i class="ace-icon fa fa-undo bigger-110"></i>
												<strong>放弃新闻</strong>
											</button>
										</div>
									</div>
								</div>
							</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="../../foot.jsp"></jsp:include>
		</div>
	</body>
</html>
