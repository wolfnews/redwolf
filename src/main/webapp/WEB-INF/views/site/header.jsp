<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String base = (String)session.getAttribute("base");
if(null == base || base.isEmpty()){
	session.setAttribute("base", basePath);
}
%>
<div class="wraper navbar-fixed-top" style="background-color: white;">
<!-- 	<div class="logo"> -->
<%-- 		<a href="${base}index.html" target="_parent"></a> --%>
<!-- 	</div> -->
	<div class="nav">
		<ul>
			<li class="nav-item"><a href="${base}index.html" target="_parent"><b>首页</b></a></li>
			<li class="nav-item"><a href="${base}news.html" target="_parent"><b>牛股快讯</b></a></li>
			<li class="nav-item"><a href="${base}box.html" target="_parent"><b>牛股宝盒</b></a></li>
			<li class="nav-item"><a href="${base}professor.html" target="_parent"><b>牛股讲师</b></a></li>
			<li class="nav-item"><a href="${base}mall.html" target="_parent"><b>牛股商城</b></a></li>
			<li class="nav-item"><a href="${base}profile/index.html" target="_parent"><b>会员中心</b></a></li>
			<li class="nav-item"><a href="${base}register.html" target="_parent"><b>会员注册</b></a></li>
		</ul>
		<div class="move-bg"></div>
	</div>
</div>
