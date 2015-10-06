<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String account = (String)session.getAttribute("USER_NAME");
if(null == account){
	account= "";
}
String base = (String)session.getAttribute("base");
if(null == base || base.isEmpty()){
	session.setAttribute("base", basePath);
}
%>
<div id="navbar" class="navbar navbar-skin-3">
	<div class="navbar-container" id="navbar-container">
		<button type="button" class="navbar-toggle menu-toggler pull-left"
			id="menu-toggler" data-target="#sidebar">
			<span class="sr-only"></span>
		</button>
		<div class="navbar-header pull-left">
			<a href="${base}index.html" class="navbar-brand"> 
				<small>
					<i class="fa fa-cloud"></i>
					牛股会个人中心
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
									<a href="${base}profile/box.html?type=all">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-danger fa fa-inbox"></i>
												<b>所有宝盒</b>
											</span>
										</div>
									</a>
								</li>
								<li>
									<a href="${base}profile/box.html?type=subs">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover  btn-danger fa fa-inbox"></i>
												<b>已订阅宝盒</b>
											</span>
										</div>
									</a>
								</li>
								<li>
									<a href="${base}profile/box.html?type=rss">
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
									<a href="${base}profile/professor.html?type=all">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-danger fa fa-users"></i>
												<b>所有讲师</b>
											</span>
										</div>
									</a>
								</li>
								<li>
									<a href="${base}profile/professor.html?type=subs">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-danger fa fa-users"></i>
												<b>已订阅讲师</b>
											</span>
										</div>
									</a>
								</li>
								<li>
									<a href="${base}profile/professor.html?type=rss">
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
						<span class="white"><b>我的订单</b></span>
					</a>
					<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-content">
							<ul class="dropdown-menu dropdown-navbar">
								<li>
									<a href="${base}profile/order.html?type=all">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-danger fa fa-credit-card"></i>
												<b>所有订单</b>
											</span>
										</div>
									</a>
								</li>
								<li>
									<a href="${base}profile/order.html?type=unfinish">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-danger fa fa-credit-card"></i>
												<b>未完成订单</b>
											</span>
										</div>
									</a>
								</li>
								<li>
									<a href="${base}profile/order.html?type=finish">
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
				<li >
					<a style="background: #AB1A08" data-toggle="dropdown" class="dropdown-toggle" href="#">
						<i class="ace-icon fa fa-credit-card"></i>
						<span class="white"><b>我的留言</b></span>
					</a>
					<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-content">
							<ul class="dropdown-menu dropdown-navbar">
								<li>
									<a href="${base}profile/message.html?category=send">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-danger fa fa-credit-card"></i>
												<b>我发出的</b>
											</span>
										</div>
									</a>
								</li>
								<li>
									<a href="${base}profile/message.html?category=receive">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-danger fa fa-credit-card"></i>
												<b>我收到的</b>
											</span>
										</div>
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>	
				<li>
					<a style="background: #AB1A08" href="${base}profile/subRecord.html">
						<i class="ace-icon fa fa-envelope"></i>
						<span class="white"><b>订阅记录</b></span>
					</a>
				</li>
				<li >
					<a style="background: #AB1A08" data-toggle="dropdown" class="dropdown-toggle" href="#">
						<i class="ace-icon fa fa-user"></i>
						<span class="white"><b><%=account %></b></span>
					</a>
					<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-content">
							<ul class="dropdown-menu dropdown-navbar">
								<li>
									<a href="${base}profile/index.html">
										<div class="clearfix">
											<span class="pull-left">
												<i class="ace-icon fa fa-power-home"></i>
												<b>返回首页</b>
											</span>
										</div>
									</a>
								</li>
								<li>
									<a href="${base}profile/user/logout.html">
										<div class="clearfix">
											<span class="pull-left">
												<i class="ace-icon fa fa-power-off"></i>
												<b>退出登录</b>
											</span>
										</div>
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
<!-- 				<li > -->
<!-- 					<a style="background: #AB1A08"  href=""> -->
<!-- 						<i class="ace-icon fa fa-user"></i> -->
<%-- 						<span><strong> <%=account %> </strong></span> --%>
<!-- 					</a> -->
<!-- 				</li> -->
<!-- 				<li> -->
<!-- 					<a style="background: #AB1A08" href=""> -->
<!-- 						<i class="ace-icon fa fa-share"></i> -->
<!-- 						<span><strong>退出</strong></span> -->
<!-- 					</a> -->
<!-- 				</li> -->
			</ul>
		</div>
	</div>
</div>