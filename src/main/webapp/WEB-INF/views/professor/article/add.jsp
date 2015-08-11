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
		<title>牛股会讲师平台-制作宝盒</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<script src="<%=basePath%>static/ace/assets/js/jquery.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/jquery.hotkeys.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace-extra.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootbox.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootstrap.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootstrap-wysiwyg.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/ace.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/elements.wysiwyg.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/elements.colorpicker.js"></script>
		<script	src="<%=basePath%>static/console/professor/add_notice.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="../header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='<%=basePath%>';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
				function showMessage(message,callbackFn){
					bootbox.dialog({
						message: '<b>'+message+'</b>', 
						buttons: {
							"success" : {
								"label" : "OK",
								"className" : "btn-sm btn-primary",
								callback: callbackFn
							}
						}
					});
				}
			</script>
			<jsp:include page="../nav.jsp">
				<jsp:param value="professor_article" name="page_index"/>
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
								<a href="<%=basePath%>professor/index.html"><strong>首页</strong></a>
							</li>
							<li>
								<i class="ace-icon fa fa-briefcase"></i>
								<a href="<%=basePath%>professor/notice.html"><strong>我的锦囊</strong></a>
							</li>
							<li class="active">
								<i class="ace-icon fa fa-pencil-square-o bigger-125"></i>
								<strong>撰写文章</strong>
							</li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>锦囊标题</strong> </label>
										<div class="col-sm-9">
											<input type="text" id="professor_notice_title" placeholder="请输入锦囊标题" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>锦囊关键词</strong> </label>
										<div class="col-sm-9">
											<input type="text" id="professor_notice_keyword" placeholder="请输入锦囊关键词" class="form-control" />
										</div>
									</div>
									<div class="form-group" >
										<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>锦囊类型</strong> </label>
										<div class="col-sm-9">
											<input id="professor_notice_category" name="professor_notice_category" type="radio" value="FREE"   onclick="changeSelect()" class="ace" checked="checked" />
											<span class="lbl"> <b>免费</b></span>
											<input id="professor_notice_category" name="professor_notice_category" type="radio" value="CHARGE" onclick="changeSelect()" class="ace" />
											<span class="lbl"> <b>收费</b></span>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>免费内容</strong> </label>
										<div class="col-sm-9">
											<div class="wysiwyg-editor" id="professor_addnotice_free_editor"></div>
										</div>
									</div>
									<div class="form-group" id="professor_addnotice_charge_editor_div" style="display: none;">
										<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>收费内容</strong> </label>
										<div class="col-sm-9">
											<div class="wysiwyg-editor" id="professor_addnotice_charge_editor"></div>
										</div>
									</div>
									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info" type="button" onclick="addNotice()">
												<i class="ace-icon fa fa-check bigger-110"></i>
												<strong>保存锦囊</strong>
											</button>

											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset">
												<i class="ace-icon fa fa-undo bigger-110"></i>
												<strong>放弃锦囊</strong>
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
