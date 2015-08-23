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
		<title>牛股会-个人中心</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<script src="<%=basePath%>static/ace/assets/js/jquery.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace-extra.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootbox.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootstrap.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/ace.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='<%=basePath%>';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
							$(document).ready(function(){
								$('#profile_lastest_box').empty();
								currentPage = ${currentPage};
								if(null == currentPage){
									currentPage=1;
								}
								$('#currentPage').val(currentPage);
								boxs = ${boxs};
								for(var i=0;i<boxs.length;i++){
									 var box = boxs[i];
									 html = "<div class=\"profile-activity clearfix\">"+
												"<div>"+
													"<span><strong>【"+box.authorName+"】："+box.title+"</strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
													"<i class=\"ace-icon fa fa-clock-o bigger-110\">"+box.gmtCreate+"</i>"+
												"</div><hr>"+
												"<div>"+
													"<span>"+box.publicContent+"...</span>"+
												"</div>"+
												"<div class=\"text-right\"><button class=\"btn btn-danger\" onclick=\"boxDetail("+box.id+")\">查看宝盒</button></div>"+
											"</div>";
									 $('#profile_lastest_box').append(html);
								}
							});
							function boxDetail(id){
								location.href='box/detail.html?id='+id;
							}
						</script>
						<ul class="breadcrumb">
							<li class="active">
								<i class="ace-icon fa fa-home home-icon"></i>
								<strong>当前位置：</strong>
								<span><strong>个人中心</strong></span>
							</li>
							
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-10 col-xs-offset-1">
								<div class="hr dotted"></div>
								<div>
									<div id="user-profile-1" class="user-profile row">
										<div class="col-xs-12 col-sm-3 center">
											<div>
												<span class="profile-picture">
													<img id="avatar" class="editable img-responsive" alt="${profile.username }" src="<%=basePath %>static/ace/assets/avatars/profile-pic.jpg" />
												</span>
												<div class="space-4"></div>
												<div class="width-80 label label-danger label-xlg arrowed-in arrowed-in-right">
													<div class="inline position-relative">
														<a href="#" class="user-title-label dropdown-toggle" data-toggle="dropdown">
															<span class="white"><strong>${profile.username }</strong></span>
														</a>
													</div>
												</div>
											</div>
											<div class="space-6"></div>
											<!-- /section:pages/profile.contact -->
											<div class="hr hr12 dotted"></div>
											<!-- #section:custom/extra.grid -->
											<div class="clearfix">
												<div class="grid3">
													<span class="bigger-175 blue">${profile.level }</span>
													<br />
													<strong>当前等级</strong>
												</div>
												<div class="grid3">
													<span class="bigger-175 blue">${profile.coin }</span>
													<br />
													<strong>牛币余额</strong>
												</div>
												<!-- 充值链接 -->
												<div class="grid3">
													<span class="btn btn-app btn-sm btn-danger no-hover" onclick="toRecharge()">
														<span class="line-height-1 smaller-90"> <strong>充值</strong> </span>
													</span>
												</div>
												
												<script type="text/javascript">
												function toRecharge(){
													location.href='recharge.html';
												}
												function toMyRss(){
													location.href='professor.html?type=rss';
												}
												function toMySub(){
													location.href='professor.html?type=subs';
												}
												function toMyOrder(){
													location.href='payOrder.html?type=all';
												}
												</script>
											</div>
											<!-- /section:custom/extra.grid -->
											<div class="hr hr16 dotted"></div>
										</div>
										<div class="col-xs-12 col-sm-9">
										<!-- #统计信息展示 -->
											<div class="left">
												<!-- 查看重置订单信息 -->
												<span class="btn btn-app btn-sm btn-danger no-hover" onclick="toMyOrder()">
													<span class="line-height-1 bigger-170 white"> ${profile.subAccount } </span>
													<br />
													<span class="line-height-1 smaller-90"><strong>我的订单</strong></span>
												</span>
												<!-- 查看订阅的讲师信息 -->
												<span class="btn btn-app btn-sm btn-danger no-hover" onclick="toMySub()">
													<span class="line-height-1 bigger-170 white"> ${profile.subAccount } </span>
													<br />
													<span class="line-height-1 smaller-90"><strong>我的订阅</strong></span>
												</span>
												<!-- 查看关注的讲师信息-->
												<span class="btn btn-app btn-sm btn-warning no-hover" onclick="toMyRss()">
													<span class="line-height-1 bigger-170"> ${profile.rssAccount } </span>
													<br />
													<span class="line-height-1 smaller-90"> <strong>我的关注</strong> </span>
												</span>
												<!-- 查看自己的留言信息 -->
												<span class="btn btn-app btn-sm btn-primary no-hover">
													<i class="ace-icon fa fa-envelope light-white"></i>
													<span class="label label-inverse arrowed-in" id="profile_message_num"></span>
													<span class="line-height-1 smaller-90"> <strong>我的留言</strong> </span>
												</span>
												<!-- 查看所有的讲师信息 -->
												<span class="btn btn-app btn-sm btn-pink no-hover" onclick="gotoProfessors()">
													<i class="ace-icon fa fa-user light-white"></i>
													<span class="line-height-1 smaller-90"> <strong>所有讲师</strong> </span>
												</span>
												<script type="text/javascript">
													function gotoProfessors(){
														location.href=base+'profile/professor.html';
													}
												</script>
											</div>
											<div class="space-12"></div>
										</div>
										<div class="col-xs-9">
											<!-- /section:pages/profile.info -->
											<div class="space-20"></div>
											<div class="widget-box transparent">
												<div class="widget-header widget-header-small">
													<h4 class="widget-title red smaller">
														<i class="ace-icon fa fa-rss orange"></i>
														<strong>最新宝盒</strong>
													</h4>
													<div class="widget-toolbar action-buttons">
														<a href="javascript:void(0)" onclick="refresh()" data-action="reload">
															<i class="ace-icon fa fa-refresh blue"></i>
														</a>
													</div>
													<script type="text/javascript">
														function refresh(){
															$('#profile_lastest_box').empty();
															$.get(base+'profile/box/refresh',function(response){
																if(response.success){
																	boxs = response.data;
																	for(var i=0;i<boxs.length;i++){
																		 var box = boxs[i];
																		 html = "<div class=\"profile-activity clearfix\">"+
																			"<div>"+
																				"<span><strong>【"+box.authorName+"】："+box.title+"</strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
																				"<i class=\"ace-icon fa fa-clock-o bigger-110\">"+box.gmtCreate+"</i>"+
																			"</div><hr>"+
																			"<div>"+
																				"<span>"+box.publicContent+"...</span>"+
																			"</div>"+
																			"<div class=\"text-right\"><button class=\"btn btn-danger\" onclick=\"boxDetail("+box.id+")\">查看宝盒</button></div>"+
																		"</div>";
																		 $('#profile_lastest_box').append(html);
																	}
																}
															});
														}
													</script>
												</div>
												<div class="widget-body">
													<div class="widget-main padding-8">
														<!-- #section:pages/profile.feed -->
														<div id="profile_lastest_box" class="profile-feed">
														</div>
													</div>
												</div>
											</div>
											<div class="hr hr2 hr-double"></div>
											<div class="space-6"></div>
											<div align="right">
												<input type="hidden" id="currentPage"/>
												<button type="button" class="btn btn-sm btn-primary btn-white btn-round" onclick="browseMore()">
													<i class="ace-icon fa fa-rss bigger-150 middle orange2"></i>
													<span class="bigger-110"><strong>查看更多</strong></span>
													<i class="icon-on-right ace-icon fa fa-arrow-right"></i>
												</button>
												<script type="text/javascript">
													function browseMore(){
													}
												</script>
											</div>
										</div>
									</div>
								</div>
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->
			<jsp:include page="../foot.jsp"></jsp:include>
		</div><!-- /.main-container -->
	</body>
</html>
