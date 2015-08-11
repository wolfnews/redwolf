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
		<title>牛股会讲师平台</title>
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
				$(document).ready(function(){
					$('#professor_lastest_notice').empty();
					notices = ${notices};
					for(var i=0;i<notices.length;i++){
						 var notice = notices[i];
						 html = "<div class=\"profile-activity clearfix\">"+
									"<div>"+
										"<i class=\"ace-icon fa fa-briefcase bigger-110\"></i>&nbsp;"+
										"<span><strong>锦囊标题：</strong>"+notice.title+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
										"<strong>发表时间：</strong><i class=\"ace-icon fa fa-clock-o bigger-110\">"+notice.gmtCreate+"</i>"+
									"</div>"+
								"</div>";
						 $('#professor_lastest_notice').append(html);
					}
				});
			</script>
			<jsp:include page="nav.jsp">
				<jsp:param value="professor_dashboard" name="page_index"/>
			</jsp:include>			
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="<%=basePath%>manage/index.html"><strong>首页</strong></a>
							</li>
							<li class="active"><b>数据纵览</b></li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<div class="hr dotted"></div>
								<div>
									<div id="user-profile-1" class="user-profile row">
										<div class="col-xs-12 col-sm-3 center">
											<div>
												<span class="profile-picture">
													<img id="avatar" class="editable img-responsive" alt="Alex's Avatar" src="<%=basePath %>static/ace/assets/avatars/profile-pic.jpg" />
												</span>
												<div class="space-4"></div>
												<div class="width-80 label label-danger label-xlg arrowed-in arrowed-in-right">
													<div class="inline position-relative">
														<a href="#" class="user-title-label dropdown-toggle" data-toggle="dropdown">
															<i class="ace-icon fa fa-circle light-red"></i>
															&nbsp;
															<span class="white"><strong>${professor.username }</strong></span>
														</a>
													</div>
												</div>
											</div>
											<div class="space-6"></div>
											<!-- /section:pages/profile.contact -->
											<div class="hr hr12 dotted"></div>
										</div>
										<div class="col-xs-12 col-sm-9">
										<!-- #统计信息展示 -->
											<div class="left">
												<!-- 关注人数 -->
												<span class="btn btn-app btn-sm btn-yellow no-hover">
													<span class="line-height-1 bigger-170"> ${accounts.userRssAccount } </span>
													<br />
													<span class="line-height-1 smaller-90"> <strong>关注人数</strong> </span>
												</span>
												<!-- 订阅人数 -->
												<span class="btn btn-app btn-sm btn-pink no-hover">
													<span class="line-height-1 bigger-170"> ${accounts.userSubscribeAccount } </span>
													<br />
													<span class="line-height-1 smaller-90"> <strong>订阅人数</strong> </span>
												</span>
												<!-- 锦囊总数 -->
												<span class="btn btn-app btn-sm btn-grey no-hover">
													<span class="line-height-1 bigger-170"> ${accounts.noticeAccount } </span>

													<br />
													<span class="line-height-1 smaller-90"> <strong>宝盒总数</strong> </span>
												</span>
												<!-- 锦囊浏览量 -->
												<span class="btn btn-app btn-sm btn-light no-hover">
													<span class="line-height-1 bigger-170 blue"> ${accounts.noticeBrowseAccount } </span>
													<br />
													<span class="line-height-1 smaller-90"><strong>浏览次数</strong></span>
												</span>
												<!-- 撰写锦囊连接 -->
												<span class="btn btn-app btn-sm btn-primary no-hover" onclick="gotoWriterNotice()">
													<i class="ace-icon fa fa-pencil-square-o"></i>
													<span class="line-height-1 smaller-90"><strong>撰写锦囊</strong></span>
												</span>
												<script type="text/javascript">
													function gotoWriterNotice(){
														location.href = base+'professor/notice/add.html';
													}
												</script>
											</div>
											<div class="space-12"></div>
											<!-- #section:个人信息展示 -->
											<div class="profile-user-info profile-user-info-striped">
												<div class="profile-info-row">
													<div class="profile-info-name"> 登录账号 </div>
													<div class="profile-info-value">
														<span>${professor.username }</span>
													</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name"> 真实姓名 </div>
													<div class="profile-info-value">
														<span>${professor.truename }</span>
													</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name"> 个人职业 </div>
													<div class="profile-info-value">
														<span >${professor.occupation }</span>
													</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name"> 当前级别 </div>
													<div class="profile-info-value">
														<span >${professor.level }</span>
													</div>
												</div>
												<div class="profile-info-row">
													<div class="profile-info-name"> 加入时间 </div>
													<div class="profile-info-value">
														<span >${professor.gmtCreate }</span>
													</div>
												</div>
											</div>
											<!-- /section:pages/profile.info -->
											<div class="space-20"></div>
											<div class="widget-box transparent">
												<div class="widget-header widget-header-small">
													<h4 class="widget-title blue smaller">
														<i class="ace-icon fa fa-rss orange"></i>
														<strong>近期锦囊</strong>
													</h4>
													<div class="widget-toolbar action-buttons">
														<a href="javascript:void(0)" onclick="refresh()" data-action="reload">
															<i class="ace-icon fa fa-refresh blue"></i>
														</a>
													</div>
													<script type="text/javascript">
														function refresh(){
															$.get(base+'professor/profile/refresh',function(response){
																if(response.success){
																	$('#professor_lastest_notice').empty();
																	notices = response.data;
																	for(var i=0;i<notices.length;i++){
																		 var notice = notices[i];
																		 html = "<div class=\"profile-activity clearfix\">"+
																					"<div>"+
																						"<i class=\"ace-icon fa fa-briefcase bigger-110\"></i>&nbsp;"+
																						"<span><strong>锦囊标题：</strong>"+notice.title+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
																						"<strong>发表时间：</strong><i class=\"ace-icon fa fa-clock-o bigger-110\">"+notice.gmtCreate+"</i>"+
																					"</div>"+
																				"</div>";
																		 $('#professor_lastest_notice').append(html);
																	}
																}
															});
														}
													</script>
												</div>
												<div class="widget-body">
													<div class="widget-main padding-8">
														<!-- #section:pages/profile.feed -->
														<div id="professor_lastest_notice" class="profile-feed">
														</div>
													</div>
												</div>
											</div>
											<div class="hr hr2 hr-double"></div>
											<div class="space-6"></div>
											<div class="center">
												<button type="button" class="btn btn-sm btn-primary btn-white btn-round" onclick="jump2AllNotice()">
													<i class="ace-icon fa fa-rss bigger-150 middle orange2"></i>
													<span class="bigger-110"><strong>查看所有锦囊</strong></span>
													<i class="icon-on-right ace-icon fa fa-arrow-right"></i>
												</button>
												<script type="text/javascript">
													function jump2AllNotice(){
														location.href="notice.html"
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
