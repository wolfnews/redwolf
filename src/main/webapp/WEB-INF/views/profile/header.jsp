<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String account = (String)session.getAttribute("USER_NAME");
if(null == account){
	account= "";
}
%>
<div id="navbar" class="navbar navbar-skin-3">
	<div class="navbar-container" id="navbar-container">
		<button type="button" class="navbar-toggle menu-toggler pull-left"
			id="menu-toggler" data-target="#sidebar">
			<span class="sr-only"></span>
		</button>
		<div class="navbar-header pull-left">
			<a href="<%=basePath%>index.html" class="navbar-brand"> 
				<small>
					<i class="fa fa-cloud"></i>
					<strong>欢迎来到牛股会个人中心</strong>
				</small>
			</a>
		</div>
		<div class="navbar-buttons navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li>
					<a style="background: #AB1A08" data-toggle="dropdown" class="dropdown-toggle" href="#">
						<i class="ace-icon fa fa-inbox"></i>
						<span class="white"><b> 牛股宝盒  </b></span>
					</a>
					<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-content">
							<ul class="dropdown-menu dropdown-navbar">
								<li>
									<a href="<%=basePath%>profile/box.html?type=all">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-danger fa fa-inbox"></i>
												<b>所有宝盒</b>
											</span>
										</div>
									</a>
								</li>
								<li>
									<a href="<%=basePath%>profile/box.html?type=subs">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover  btn-danger fa fa-inbox"></i>
												<b>已订阅宝盒</b>
											</span>
										</div>
									</a>
								</li>
								<li>
									<a href="<%=basePath%>profile/box.html?type=rss">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover  btn-danger fa fa-inbox"></i>
												<b>已关注宝盒</b>
											</span>
										</div>
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				<li>
					<a style="background: #AB1A08" data-toggle="dropdown" class="dropdown-toggle" href="#">
						<i class="ace-icon fa fa-users"></i>
						<span class="white"><b>牛股讲师</b></span>
					</a>
					<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-content">
							<ul class="dropdown-menu dropdown-navbar">
								<li>
									<a href="<%=basePath%>profile/professor.html?type=all">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-danger fa fa-users"></i>
												<b>所有讲师</b>
											</span>
										</div>
									</a>
								</li>
								<li>
									<a href="<%=basePath%>profile/professor.html?type=subs">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-danger fa fa-users"></i>
												<b>已订阅讲师</b>
											</span>
										</div>
									</a>
								</li>
								<li>
									<a href="<%=basePath%>profile/professor.html?type=rss">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-danger fa fa-users"></i>
												<b>已关注讲师</b>
											</span>
										</div>
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				<li >
					<a style="background: #AB1A08" data-toggle="dropdown" class="dropdown-toggle" href="#">
						<i class="ace-icon fa fa-credit-card"></i>
						<span class="white"><b>充值订单</b></span>
					</a>
					<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-content">
							<ul class="dropdown-menu dropdown-navbar">
								<li>
									<a href="<%=basePath%>profile/payOrder.html?type=all">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-danger fa fa-credit-card"></i>
												<b>所有订单</b>
											</span>
										</div>
									</a>
								</li>
								<li>
									<a href="<%=basePath%>profile/payOrder.html?type=unfinish">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-danger fa fa-credit-card"></i>
												<b>未完成订单</b>
											</span>
										</div>
									</a>
								</li>
								<li>
									<a href="<%=basePath%>profile/payOrder.html?type=finish">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-danger fa fa-credit-card"></i>
												<b>已完成订单</b>
											</span>
										</div>
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>	
				<li>
					<a style="background: #AB1A08" href="<%=basePath%>profile/sr.html">
						<i class="ace-icon fa fa-envelope"></i>
						<span class="white"><b>订阅记录</b></span>
					</a>
				</li>
				<li >
					<a style="background: #AB1A08"  href="<%=basePath%>profile/index.html">
						<i class="ace-icon fa fa-user"></i>
						<span><strong> <%=account %> </strong></span>
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