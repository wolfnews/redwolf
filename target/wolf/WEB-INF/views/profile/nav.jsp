<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	<div id="sidebar" class="sidebar responsive">
		<ul class="nav nav-list">
			<li class="active">
				<a href="<%=basePath%>index.html"> 
					<i class="menu-icon fa fa-home"></i> 
					<span class="menu-text"><strong>返回首页</strong> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li class="active">
				<a href="<%=basePath%>profile/notice.html"> 
					<i class="menu-icon fa fa-briefcase"></i> 
					<span class="menu-text"><strong>红狼宝盒</strong> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li class="active">
				<a href="<%=basePath%>profile/professor.html"> 
					<i class="menu-icon fa fa-users"></i> 
					<span class="menu-text"><strong>红狼讲师</strong> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li class="active">
				<a href="<%=basePath%>profile/message.html"> 
					<i class="menu-icon fa fa-book"></i> 
					<span class="menu-text"><b>我的留言</b> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li class="">
				<a href='<%=basePath%>profile/rss.html'> 
					<i class="menu-icon fa fa-comment"></i> 
					<span class="menu-text"><b>我的关注</b> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li class="">
				<a href='<%=basePath%>profile/subscribe.html'> 
					<i class="menu-icon fa fa-comment"></i> 
					<span class="menu-text"><b>我的订阅</b> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li class="">
				<a href='<%=basePath%>profile/pay.html'> 
					<i class="menu-icon fa fa-comment"></i> 
					<span class="menu-text"><b>支付记录</b> </span>
				</a>
				<b class="arrow"></b>
			</li>
		</ul>
	</div>
