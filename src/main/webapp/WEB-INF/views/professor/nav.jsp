<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String pageIndex = request.getParameter("page_index");
pageIndex = null == pageIndex? "" : pageIndex;
%>
<script src="<%=basePath%>static/ace/assets/js/ace/ace.sidebar.js"></script>
	<div id="sidebar" class="sidebar responsive">
	<script type="text/javascript">
		$(document).ready(function(){
			var page_index = '<%=pageIndex %>';
			if("" != page_index){
				$("#"+page_index).addClass('active');
			}
		});
	</script>
		<ul class="nav nav-list">
			<li id="professor_dashboard" class="">
				<a href="<%=basePath%>professor/index.html"> 
					<i class="menu-icon fa fa-tachometer"></i> 
					<span class="menu-text"><strong>数据纵览</strong> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li id="professor_notice" class="">
				<a href="<%=basePath%>professor/box.html"> 
					<i class="menu-icon fa fa-briefcase"></i> 
					<span class="menu-text"><strong>我的宝盒</strong> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li id="professor_article" class="">
				<a href='<%=basePath%>professor/article.html'> 
					<i class="menu-icon fa fa-book"></i> 
					<span class="menu-text"><b>我的文章</b> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li id="professor_rss" class="">
				<a href='<%=basePath%>professor/rss.html'> 
					<i class="menu-icon fa fa-bell"></i> 
					<span class="menu-text"><b>关注管理</b> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li id="professor_subscribe" class="">
				<a href='<%=basePath%>professor/subscribe.html'> 
					<i class="menu-icon fa fa-cog"></i> 
					<span class="menu-text"><b>订阅管理</b> </span>
				</a>
				<b class="arrow"></b>
			</li>
		</ul>
		<!-- 菜单伸缩按钮 -->
		<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
			<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
		</div>
		<script type="text/javascript">
			try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
		</script>
	</div>
