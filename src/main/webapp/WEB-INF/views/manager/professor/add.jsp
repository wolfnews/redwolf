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
		<title>牛股会后台管理-新增讲师</title>
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
	</head>
	<body class="no-skin">
		<jsp:include page="../header.jsp"/>	
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='<%=basePath%>';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
				jQuery(function($) {
					function showErrorAlert(reason, detail) {
						var msg = '';
						if (reason === 'unsupported-file-type') {
							msg = "Unsupported format " + detail;
						} else {
							// console.log("error uploading file", reason, detail);
						}
						$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'
										+ '<strong>File upload error</strong> '
										+ msg
										+ ' </div>').prependTo('#alerts');
					}
					$('#manage_professor_summary').ace_wysiwyg({
						toolbar : [ 'font', null, 'fontSize', null, {
							name : 'bold',
							className : 'btn-info'
						}, {
							name : 'italic',
							className : 'btn-info'
						}, {
							name : 'strikethrough',
							className : 'btn-info'
						}, {
							name : 'underline',
							className : 'btn-info'
						}, null, {
							name : 'insertunorderedlist',
							className : 'btn-success'
						}, {
							name : 'insertorderedlist',
							className : 'btn-success'
						}, {
							name : 'outdent',
							className : 'btn-purple'
						}, {
							name : 'indent',
							className : 'btn-purple'
						}, null, {
							name : 'justifyleft',
							className : 'btn-primary'
						}, {
							name : 'justifycenter',
							className : 'btn-primary'
						}, {
							name : 'justifyright',
							className : 'btn-primary'
						}, {
							name : 'justifyfull',
							className : 'btn-inverse'
						}, null, {
							name : 'createLink',
							className : 'btn-pink'
						}, {
							name : 'unlink',
							className : 'btn-pink'
						}, null, {
							name : 'insertImage',
							className : 'btn-success'
						}, null, 'foreColor', null, {
							name : 'undo',
							className : 'btn-grey'
						}, {
							name : 'redo',
							className : 'btn-grey'
						} ],
						'wysiwyg' : {
							fileUploadError : showErrorAlert
						}
					}).prev().addClass('wysiwyg-style2');
					$(window).on('resize.editor', function() {
						var offset = $('#manage_professor_summary').parent().offset();
						var winHeight = $(this).height();

						$('#manage_professor_summary').css({
							'height' : winHeight - offset - 10,
							'max-height' : 'none'
						});
					}).triggerHandler('resize.editor');
					
					$('[data-toggle="buttons"] .btn').on(
							'click',
							function(e) {
								var target = $(this).find('input[type=radio]');
								var which = parseInt(target.val());
								var toolbar = $('#editor1').prev().get(0);
								if (which >= 1 && which <= 4) {
									toolbar.className = toolbar.className.replace(/wysiwyg\-style(1|2)/g, '');
									if (which == 1)
										$(toolbar).addClass('wysiwyg-style1');
									else if (which == 2)
										$(toolbar).addClass('wysiwyg-style2');
									if (which == 4) {
										$(toolbar).find('.btn-group > .btn').addClass(
												'btn-white btn-round');
									} else
										$(toolbar).find('.btn-group > .btn-white').removeClass(
												'btn-white btn-round');
								}
							});
					var enableImageResize = function() {
						$('.wysiwyg-editor').on('mousedown', function(e) {
							var target = $(e.target);
							if (e.target instanceof HTMLImageElement) {
								if (!target.data('resizable')) {
									target.resizable({
										aspectRatio : e.target.width / e.target.height,
									});
									target.data('resizable', true);

									if (lastResizableImg != null) {
										// disable previous resizable image
										lastResizableImg.resizable("destroy");
										lastResizableImg.removeData('resizable');
									}
									lastResizableImg = target;
								}
							}
						}).on('click',function(e) {
									if (lastResizableImg != null
											&& !(e.target instanceof HTMLImageElement)) {
										destroyResizable();
									}
								}).on('keydown', function() {
							destroyResizable();
						});
					}
				});
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
				function addProfessor(){
					username  = $('#manage_professor_username').val();
					truename  = $('#manage_professor_truename').val();
					password  = $('#manage_professor_password').val();
					occupation= $('#manage_professor_occupation').val();
					mobile    = $('#manage_professor_mobile').val();
					price     = $('#manage_professor_price').val();
					level     = $('#manage_professor_level').val();
					summary   = $('#manage_professor_summary').html();
					url       = base+'manage/professor/add';
					data = {
							username:username,
							truename:truename,
							password:password,
							occupation:occupation,
							mobile:mobile,
							summary:summary,
							level:level,
							price:price
					}
					
					$.post(url,data,function(response){
						if(response.success){
							showMessage("添加成功！",function(){
								location.href=base+'manage/professor.html';
							});
						}else{
							showMessage("添加失败");
						}
					});
				}
			</script>
			<jsp:include page="../nav.jsp">
				<jsp:param value="manage_professor" name="page_index"/>
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
								<i class="ace-icon fa fa-user"></i>
								<a href="<%=basePath%>manage/professor.html"><strong>讲师管理</strong></a>
							</li>
							<li class="active">
								<i class="ace-icon fa fa-pencil-square-o bigger-125"></i>
								<strong>新增讲师</strong>
							</li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
							<form class="form-horizontal" role="form">
								<div class="col-xs-5">
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right" for="form-field-1-1"><strong>讲师账号</strong> </label>
										<div class="col-sm-8">
											<input type="text" id="manage_professor_username" placeholder="请输入讲师账号" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right" for="form-field-1-1"><strong>登录密码</strong> </label>
										<div class="col-sm-8">
											<input type="password" id="manage_professor_password" placeholder="请输入登录密码" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right" for="form-field-1-1"><strong>讲师职业</strong> </label>
										<div class="col-sm-8">
											<input type="text" id="manage_professor_occupation" placeholder="请输入讲师职业" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right" for="form-field-1-1"><strong>订阅价格</strong> </label>
										<div class="col-sm-8">
											<input type="text" id="manage_professor_price" placeholder="订阅价格...(积分/天)" class="form-control" />
										</div>
									</div>
								</div>
								<div class="col-xs-5">
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right" for="form-field-1-1"><strong>真实姓名</strong> </label>
										<div class="col-sm-8">
											<input type="text" id="manage_professor_truename" placeholder="请输入讲师真实姓名" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right" for="form-field-1-1"><strong>密码确认</strong> </label>
										<div class="col-sm-8">
											<input type="password" id="manage_professor_repassword" placeholder="请再次输入登录密码" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right" for="form-field-1-1"><strong>手机号码</strong> </label>
										<div class="col-sm-8">
											<input type="text" id="manage_professor_mobile" placeholder="请输入讲师手机号码" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label no-padding-right" for="form-field-1-1"><strong>讲师级别</strong> </label>
										<div class="col-sm-8">
											<input type="text" id="manage_professor_level" placeholder="请输入讲师级别(请输入1-10内的数字)" class="form-control" />
										</div>
									</div>
								</div>
								<div class="col-xs-10">
									<div class="form-group">
										<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>讲师简介</strong> </label>
										<div class="col-sm-10">
											<div class="wysiwyg-editor" id="manage_professor_summary"></div>
										</div>
									</div>
									<div class="clearfix form-actions">
										<div class="col-md-offset-5 col-md-9">
											<button class="btn btn-info" type="button" onclick="addProfessor()">
												<i class="ace-icon fa fa-check bigger-110"></i>
												<strong>保存信息</strong>
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
