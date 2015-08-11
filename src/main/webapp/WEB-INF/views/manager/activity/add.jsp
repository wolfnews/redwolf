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
		<title>牛股会后台管理-添加活动</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/select2.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/bootstrap-datetimepicker.css" />
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
		<script src="<%=basePath%>static/ace/assets/js/date-time/moment.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/jquery-ui.custom.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/date-time/bootstrap-datetimepicker.js"></script>
		<script	src="<%=basePath%>static/console/manager/add_activity.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="../header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='<%=basePath%>';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<jsp:include page="../nav.jsp">
				<jsp:param value="manage_activity" name="page_index"/>
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
								<i class="ace-icon fa fa-credit-card"></i>
								<a href="<%=basePath%>manage/activity.html"><strong>活动管理</strong></a>
							</li>
							<li class="active">
								<i class="ace-icon fa fa-pencil-square-o bigger-125"></i>
								<strong>发布活动</strong>
							</li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>活动标题</strong> </label>
										<div class="col-sm-9">
											<input type="text" id="activity_name" placeholder="请输入活动标题" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>活动类型</strong> </label>
										<div class="col-sm-4">
											<select id="activity_category" name="activity_category" placeholder="请选择活动类型" style="width: 100%">
												<option value="REGISTER">注册送积分活动</option>
												<option value="RECHARGE">充值送积分活动</option>
											</select>
										</div>
										<label class="col-sm-1 control-label no-padding-right" for="form-field-1-1"><strong>优惠比率</strong> </label>
										<div class="col-sm-4">
											<input type="text" id="activity_rate" placeholder="请输入活动优惠比率" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>开始时间</strong> </label>
										<div class="col-sm-4">
											<div class="input-group">
												<input id="start_time" type="text" class="form-control" />
												<span class="input-group-addon">
													<i class="fa fa-clock-o bigger-110"></i>
												</span>
											</div>
										</div>
										<label class="col-sm-1 control-label no-padding-right" for="form-field-1-1"><strong>结束时间</strong> </label>
										<div class="col-sm-4">
											<div class="input-group">
												<input id="end_time" type="text" class="form-control" />
												<span class="input-group-addon">
													<i class="fa fa-clock-o bigger-110"></i>
												</span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>活动介绍</strong> </label>
										<div class="col-sm-9">
											<div class="wysiwyg-editor" id="activity_desc"></div>
										</div>
									</div>
									<div class="clearfix form-actions">
										<div class="col-md-offset-5 col-md-7">
											<button class="btn btn-danger" type="button" onclick="addActivity()">
												<i class="ace-icon fa fa-check bigger-110"></i>
												<strong>提交保存</strong>
											</button>
											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset">
												<i class="ace-icon fa fa-undo bigger-110"></i>
												<strong>重置信息</strong>
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
