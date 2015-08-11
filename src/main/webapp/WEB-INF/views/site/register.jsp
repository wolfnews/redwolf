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
		<title>牛股会用户注册</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="<%=basePath%>static/css/main.css" />
		<script src="<%=basePath%>static/ace/assets/js/jquery.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootstrap.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/ace.js"></script>
		<script src="<%=basePath%>static/js/nav.js"></script>
		<script src="<%=basePath%>static/js/index.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootbox.js"></script>
		<script src="<%=basePath%>static/console/site/register.js"></script>
		<script type="text/javascript">
			var base='<%=basePath%>';
		</script>
	</head>
	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<div class="main-content">
				<div class="main-content-inner">
					<div class="page-content">
						<div class="row">
							<div class="col-xs-1"></div>
							<div class="col-xs-10">
								<h3 class="header red lighter bigger">
									<i class="ace-icon fa fa-pencil-square-o red"></i>
									<strong>请填写个人信息</strong>
								</h3>
							</div>
							<div class="col-xs-1"></div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<form class="form-horizontal" role="form">
									<div class="col-xs-1"></div>
									<div class="col-xs-5">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>用户名称</strong> </label>
											<div class="col-sm-7">
												<input type="text" id="profile_username" placeholder="请输入用户名称（6-30个字符）..." class="form-control" />
											</div>
											<label class="col-sm-3 control-label red no-padding-left" id="username_error"></label>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>登录密码</strong> </label>
											<div class="col-sm-7">
												<input type="password" id="profile_password" placeholder="请输入登录密码 （6-30个字符）..." class="form-control" />
											</div>
											<label class="col-sm-3 control-label red no-padding-left" id="password_error"></label>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>个人职业</strong> </label>
											<div class="col-sm-7">
												<input type="text" id="profile_occupation" placeholder="请输入个人职业..." class="form-control" />
											</div>
											<label class="col-sm-3 control-label red no-padding-left" id="occup_error"></label>
										</div>
									</div>
									<div class="col-xs-5">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>手机号码</strong> </label>
											<div class="col-sm-7">
												<input type="text" id="profile_mobile" placeholder="请输入手机号码..." class="form-control" />
											</div>
											<label class="col-sm-3 control-label red no-padding-left" id="mobile_error"></label>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>密码确认</strong> </label>
											<div class="col-sm-7">
												<input type="password" id="profile_repassword" placeholder="请再次输入登录密码（6-30个字符）..." class="form-control" />
											</div>
											<label class="col-sm-3 control-label red no-padding-left" id="repassword_error"></label>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>个人邮箱</strong> </label>
											<div class="col-sm-7">
												<input type="text" id="profile_email" placeholder="请输入个人邮箱地址..." class="form-control" />
											</div>
											<label class="col-sm-3 control-label red no-padding-left" id="email_error"></label>
										</div>
									</div>
								</form>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-1"></div>
							<div class="col-xs-10">
								<div class="clearfix form-actions">
									<div class="col-md-offset-5 col-md-7">
										<button class="btn btn-danger" type="button" onclick="register()">
											<i class="ace-icon fa fa-check bigger-110"></i>
											<strong>提交注册</strong>
										</button>
										&nbsp; &nbsp; &nbsp;
										<button class="btn" type="reset">
											<i class="ace-icon fa fa-undo bigger-110"></i>
											<strong>重新填写</strong>
										</button>
									</div>
								</div>
							</div>
							<div class="col-xs-1"></div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="../foot.jsp"></jsp:include>
		</div>
	</body>
</html>
