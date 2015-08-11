<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="wraper">
	<div class="logo">
		<a href="<%=basePath %>index.html" target="_parent"></a>
	</div>
	<div class="nav">
		<ul>
			<li class="nav-item cur"><a href="<%=basePath %>index.html" target="_parent">首页</a></li>
			<li class="nav-item"><a href="<%=basePath %>news.html" target="_parent">行业资讯</a></li>
			<li class="nav-item"><a href="<%=basePath %>box.html" target="_parent">牛股宝盒</a></li>
			<li class="nav-item"><a href="<%=basePath %>professor.html" target="_parent">牛股讲师</a></li>
			<li class="nav-item"><a href="<%=basePath %>profile/index.html" target="_parent">会员中心</a></li>
			<li class="nav-item"><a href="<%=basePath %>register.html" target="_parent">会员注册</a></li>
		</ul>
		<div class="move-bg"></div>
	</div>
</div>
