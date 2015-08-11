<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String account = (String)session.getAttribute("USER_NAME");
if(null == account){account= "";}%>
<div id="navbar" class="navbar navbar-skin-3">
	<div class="navbar-container" id="navbar-container">
		<button type="button" class="navbar-toggle menu-toggler pull-left"
			id="menu-toggler" data-target="#sidebar">
			<span class="sr-only"></span>
		</button>
		<div class="navbar-header pull-left">
			<a href="<%=basePath%>profile/index.html" class="navbar-brand"> 
				<small>
					<i class="fa fa-cloud"></i>
					<strong>牛股会个人中心-用户充值</strong>
				</small>
			</a>
		</div>
		<div class="navbar-buttons navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li >
					<a style="background: #AB1A08"  href="#">
						<i class="ace-icon fa fa-user"></i>
						<span><strong>欢迎你！ <%=account %> </strong></span>
					</a>
				</li>
				<li>
					<a style="background: #AB1A08" href="<%=basePath%>profile/user/logout.html">
						<i class="ace-icon fa fa-key"></i>
						<span><strong>退出</strong></span>
					</a>
				</li>
			</ul>
		</div>
	</div>
</div>