<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>牛股会用户注册</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<jsp:include page="../site_base.jsp"></jsp:include>
		<script src="${base}static/console/site/register.js"></script>
		<script type="text/javascript">
			var base='${base}';
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
											<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>用户昵称</strong> </label>
											<div class="col-sm-7">
												<input type="text" id="profile_username" maxlength="30" placeholder="请输入用户名称（3-30个字符）..." class="form-control" />
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
											<label class="col-sm-2 control-label no-padding-right" for="form-field-1-1"><strong>短信验证码</strong> </label>
											<div class="col-sm-4">
												<input type="text" id="profile_code" placeholder="请输入短信验证码..." class="form-control" />
											</div>
											<div class="col-sm-3">
												<label class="btn btn-sm btn-danger control" onclick="javascript:sendCode()"><b>获取验证码</b></label>
											</div>
											<label class="col-sm-3 control-label red no-padding-left" id="code_error"></label>
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
										<button class="btn" type="reset">
											<i class="ace-icon fa fa-undo bigger-110"></i>
											<strong>重新填写</strong>
										</button>
										&nbsp; &nbsp; &nbsp;
										<button class="btn btn-danger" onclick="register()">
											<i class="ace-icon fa fa-check bigger-110"></i>
											<strong>提交注册</strong>
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
