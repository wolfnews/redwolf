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
		<title>牛股会讲师平台-系统登录</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace.css"/>
		<script src="<%=basePath%>static/ace/assets/js/jquery.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootbox.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootstrap.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/ace.js"></script>
		<script src="<%=basePath%>static/console/professor/login.js"></script>
	</head>
	<body class="login-layout blur-login">
		<script type="text/javascript">
			var base='<%=basePath%>';
		</script>
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<br><br><br><br><br><br><br>
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-leaf red"></i>
									<span class="white" id="id-text2"><strong>牛股会讲师平台</strong></span>
								</h1>
							</div>
							<div class="space-6"></div>
							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="ace-icon fa fa-lock red"></i>
												<strong>请输入账号信息</strong>
											</h4>
											<div class="space-6"></div>
											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" id="professor_login_username" placeholder="请输入账号..." />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" id="professor_login_password" placeholder="请输入登录密码..." />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>
													<div class="space"></div>
													<div class="clearfix">
														<button type="button" id="login_button" class="width-100 pull-right btn btn-sm btn-danger" 
															onclick="login()">
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-110"><strong>登录</strong></span>
														</button>
													</div>
													<div class="space-4"></div>
												</fieldset>
											</form>
										</div>
										<div class="toolbar clearfix">
											<div>
												<a href="#" data-target="#forgot-box" class="forgot-password-link">
													<strong>忘记密码？</strong>
													<i class="ace-icon fa fa-arrow-right"></i>
												</a>
											</div>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->
								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="ace-icon fa fa-key"></i>
												<strong>密码找回</strong>
											</h4>
											<div class="space-6"></div>
											<p>
												<strong>请输入登录账号和邮箱地址</strong>
											</p>
											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="登录账号" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="邮箱地址..." />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
													</label>

													<div class="clearfix">
														<button type="button" class="width-45 pull-right btn btn-sm btn-danger">
															<span class="bigger-110"><strong>发送</strong></span>
															<i class="ace-icon fa fa-envelope-o"></i>
														</button>
													</div>
												</fieldset>
											</form>
										</div>
										<div class="toolbar center">
											<a href="#" data-target="#login-box" class="back-to-login-link">
												返回登录
												<i class="ace-icon fa fa-arrow-right"></i>
											</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->
		<script type="text/javascript">
			jQuery(function($) {
			 $(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			 });
			});
		</script>
	</body>
</html>
