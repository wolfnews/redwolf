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
			try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
		</script>
		<ul class="nav nav-list">
			<li id="manage_dashboard" class="">
				<a href="<%=basePath%>manage/index.html"> 
					<i class="menu-icon fa fa-tachometer"></i> 
					<span class="menu-text"><strong>数据纵览</strong> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li id="manage_user" class="">
				<a href="<%=basePath%>manage/user.html"> 
					<i class="menu-icon fa fa-users"></i> 
					<span class="menu-text"><strong>用户管理</strong> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li id="manage_professor" class="">
				<a href="<%=basePath%>manage/professor.html"> 
					<i class="menu-icon fa fa-user"></i> 
					<span class="menu-text"><b>讲师管理</b> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li id="manage_manager" class="">
				<a href='<%=basePath%>manage/manager.html'> 
					<i class="menu-icon fa fa-user"></i> 
					<span class="menu-text"><b>人员管理</b> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li id="manage_box" class="">
				<a href="<%=basePath%>manage/box.html"> 
					<i class="menu-icon fa fa-inbox"></i> 
					<span class="menu-text"><strong>宝盒管理</strong> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li id="manage_activity" class="">
				<a href="<%=basePath%>manage/activity.html"> 
					<i class="menu-icon fa fa-gift"></i> 
					<span class="menu-text"><strong>活动管理</strong> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li id="manage_article" class="">
				<a href='<%=basePath%>manage/news.html'> 
					<i class="menu-icon fa fa-newspaper-o"></i> 
					<span class="menu-text"><b>新闻管理</b> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li id="manage_pay" class="">
				<a href='<%=basePath%>manage/pay.html'> 
					<i class="menu-icon fa fa-credit-card"></i> 
					<span class="menu-text"><b>账单管理</b> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li id="manage_rss" class="">
				<a href='<%=basePath%>manage/rss.html'> 
					<i class="menu-icon fa fa-bell"></i> 
					<span class="menu-text"><b>用户关注</b> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li id="manage_subscribe" class="">
				<a href='<%=basePath%>manage/subscribe.html'> 
					<i class="menu-icon fa fa-cogs"></i> 
					<span class="menu-text"><b>用户订阅</b> </span>
				</a>
				<b class="arrow"></b>
			</li>
			<li id="manage_message" class="">
				<a href="<%=basePath%>manage/message.html"> 
					<i class="menu-icon fa fa-comment"></i> 
					<span class="menu-text"><b>留言管理</b> </span>
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
			$(document).ready(function(){
				var page_index = "<%=pageIndex %>";
				$("#manage_message").show();
				if("" != page_index){
					$("#"+page_index).addClass('active');
				}
			});
		</script>
	</div>
