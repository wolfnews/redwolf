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
				<a href="#" class="dropdown-toggle">
					<i class="menu-icon fa fa-users"></i>
					<span class="menu-text"> <b>用户管理</b> </span>
					<b class="arrow fa fa-angle-down"></b>
				</a>
				<b class="arrow"></b>
				<ul class="submenu">
					<li class="">
						<a href="<%=basePath%>manage/user.html">
							<i class="menu-icon fa fa-users"></i>
							<b>会员管理</b>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="<%=basePath%>manage/subscribe.html">
							<i class="menu-icon fa fa-cogs"></i>
							<b>会员订阅</b>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="<%=basePath%>manage/rss.html">
							<i class="menu-icon fa fa-bell"></i>
							<b>会员关注</b>
						</a>
						<b class="arrow"></b>
					</li>
				</ul>
			</li>
			<li id="manage_professor" class="">
				<a href="#" class="dropdown-toggle"> 
					<i class="menu-icon fa fa-user"></i> 
					<span class="menu-text"><b>讲师管理</b> </span>
					<b class="arrow fa fa-angle-down"></b>
				</a>
				<b class="arrow"></b>
				<ul class="submenu">
					<li class="">
						<a href="<%=basePath%>manage/professor.html">
							<i class="menu-icon fa fa-user"></i>
							<b>讲师管理</b>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="<%=basePath%>manage/box.html">
							<i class="menu-icon fa fa-inbox"></i>
							<b>宝盒管理</b>
						</a>
						<b class="arrow"></b>
					</li>
				</ul>
			</li>
			<li id="manage_content" class="">
				<a href="#" class="dropdown-toggle"> 
					<i class="menu-icon fa fa-book"></i> 
					<span class="menu-text"><b>内容管理</b> </span>
					<b class="arrow fa fa-angle-down"></b>
				</a>
				<b class="arrow"></b>
				<ul class="submenu">
					<li class="">
						<a href="<%=basePath%>manage/activity.html"> 
							<i class="menu-icon fa fa-gift"></i> 
							<span class="menu-text"><strong>活动管理</strong> </span>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href='<%=basePath%>manage/news.html'> 
							<i class="menu-icon fa fa-newspaper-o"></i> 
							<span class="menu-text"><b>新闻管理</b> </span>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href="<%=basePath%>manage/message.html"> 
							<i class="menu-icon fa fa-comment"></i> 
							<span class="menu-text"><b>留言管理</b> </span>
						</a>
						<b class="arrow"></b>
					</li>
				</ul>
			</li>
			<li id="manage_store" class="">
				<a href="#" class="dropdown-toggle"> 
					<i class="menu-icon fa fa-home"></i> 
					<span class="menu-text"><b>库存管理</b> </span>
					<b class="arrow fa fa-angle-down"></b>
				</a>
				<b class="arrow"></b>
				<ul class="submenu">
					<li class="">
						<a href='<%=basePath%>manage/item.html'> 
							<i class="menu-icon fa fa-tag"></i> 
							<span class="menu-text"><b>商品管理</b> </span>
						</a>
						<b class="arrow"></b>
					</li>
				</ul>
			</li>
			<li id="manage_sale" class="">
				<a href="#" class="dropdown-toggle"> 
					<i class="menu-icon fa fa-credit-card"></i> 
					<span class="menu-text"><b>销售管理</b> </span>
					<b class="arrow fa fa-angle-down"></b>
				</a>
				<b class="arrow"></b>
				<ul class="submenu">
					<li class="">
						<a href='<%=basePath%>manage/order.html'> 
							<i class="menu-icon fa fa-file"></i> 
							<span class="menu-text"><b>订单管理</b> </span>
						</a>
						<b class="arrow"></b>
					</li>
				</ul>
			</li>
			<li id="manage_power" class="">
				<a href="#" class="dropdown-toggle"> 
					<i class="menu-icon fa fa-lock"></i> 
					<span class="menu-text"><b>权限管理</b> </span>
					<b class="arrow fa fa-angle-down"></b>
				</a>
				<b class="arrow"></b>
				<ul class="submenu">
					<li class="">
						<a href='<%=basePath%>manage/manager.html'> 
							<i class="menu-icon fa fa-users"></i> 
							<span class="menu-text"><b>员工管理</b> </span>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href='<%=basePath%>manage/manager.html'> 
							<i class="menu-icon fa fa-flag"></i> 
							<span class="menu-text"><b>角色管理</b> </span>
						</a>
						<b class="arrow"></b>
					</li>
					<li class="">
						<a href='<%=basePath%>manage/manager.html'> 
							<i class="menu-icon fa fa-key"></i> 
							<span class="menu-text"><b>权限管理</b> </span>
						</a>
						<b class="arrow"></b>
					</li>
				</ul>
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
