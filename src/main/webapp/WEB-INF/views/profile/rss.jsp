<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>牛股会-个人中心</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<jsp:include page="../prof_base.jsp"></jsp:include>
	</head>
	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='${base}';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
							$(document).ready(function(){
								$('#profile_lastest_notice').empty();
								currentPage = ${currentPage};
								if(null == currentPage){
									currentPage=1;
								}
								$('#currentPage').val(currentPage);
								notices = ${notices};
								for(var i=0;i<notices.length;i++){
									 var notice = notices[i];
									 html = "<div class=\"profile-activity clearfix\">"+
												"<div>"+
													"<i class=\"ace-icon fa fa-user bigger-110\"></i>&nbsp;"+
													"<span><strong>"+notice.authorName+"：</strong>"+notice.title+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
													"<i class=\"ace-icon fa fa-clock-o bigger-110\">"+notice.gmtCreate+"</i>"+
												"</div>"+
												"<div>"+
													"<span>"+notice.publicContent.substring(0,20)+"...</span>"+
													"<button class=\"btn btn-minier btn-yellow\">查看锦囊</button>"+
												"</div>"+
											"</div>";
									 $('#profile_lastest_notice').append(html);
								}
							});
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
													<img id="avatar" class="editable img-responsive" alt="${profile.username }" src="${base}static/ace/assets/avatars/profile-pic.jpg" />
												</span>
												<div class="space-4"></div>
												<div class="width-80 label label-danger label-xlg arrowed-in arrowed-in-right">
													<div class="inline position-relative">
														<a href="#" class="user-title-label dropdown-toggle" data-toggle="dropdown">
															<i class="ace-icon fa fa-circle light-red"></i>
															&nbsp;
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
													<span class="bigger-175 blue">${profile.grade }</span>
													<br />
													<strong>积分余额</strong>
												</div>
												<!-- 充值链接 -->
												<div class="grid3">
													<span class="btn btn-app btn-sm btn-danger no-hover" onclick="toRecharge()">
														<span class="line-height-1 smaller-90"> <strong>充值</strong> </span>
													</span>
												</div>
											</div>
											<!-- /section:custom/extra.grid -->
											<div class="hr hr16 dotted"></div>
										</div>
										<div class="col-xs-12 col-sm-9">
										<!-- #统计信息展示 -->
											<div class="left">
												<!-- 查看订阅的讲师信息 -->
												<span class="btn btn-app btn-sm btn-danger no-hover">
													<span class="line-height-1 bigger-170 white"> ${profile.subAccount } </span>
													<br />
													<span class="line-height-1 smaller-90"><strong>我的订阅</strong></span>
												</span>
												<!-- 查看关注的讲师信息-->
												<span class="btn btn-app btn-sm btn-warning no-hover">
													<span class="line-height-1 bigger-170"> ${profile.rssAccount } </span>
													<br />
													<span class="line-height-1 smaller-90"> <strong>我的关注</strong> </span>
												</span>
												<!-- 查看自己的留言信息 -->
												<span class="btn btn-app btn-sm btn-primary no-hover">
													<i class="ace-icon fa fa-envelope light-white"></i>
													<span class="label label-inverse arrowed-in">6+</span>
													<span class="line-height-1 smaller-90"> <strong>我的留言</strong> </span>
												</span>
												<!-- 查看所有的讲师信息 -->
												<span class="btn btn-app btn-sm btn-pink no-hover">
													<i class="ace-icon fa fa-user light-white"></i>
													<span class="line-height-1 smaller-90"> <strong>所有讲师</strong> </span>
												</span>
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
														<strong>最新锦囊</strong>
													</h4>
													<div class="widget-toolbar action-buttons">
														<a href="javascript:void(0)" onclick="refresh()" data-action="reload">
															<i class="ace-icon fa fa-refresh blue"></i>
														</a>
													</div>
													<script type="text/javascript">
														function refresh(){
															$('#profile_lastest_notice').empty();
															$.get(base+'profile/notice/refresh',function(response){
																if(response.success){
																	notices = response.data;
																	for(var i=0;i<notices.length;i++){
																		 var notice = notices[i];
																		 html = "<div class=\"profile-activity clearfix\">"+
																					"<div>"+
																						"<i class=\"ace-icon fa fa-user bigger-110\"></i>&nbsp;"+
																						"<span><strong>"+notice.authorName+"：</strong>"+notice.title+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
																						"<i class=\"ace-icon fa fa-clock-o bigger-110\">"+notice.gmtCreate+"</i>"+
																					"</div>"+
																					"<div>"+
																						"<span>"+notice.publicContent.substring(0,10)+"...</span>"+
																						"<button class=\"btn btn-minier btn-yellow\">查看锦囊</button>"+
																					"</div>"+
																				"</div>";
																		 $('#profile_lastest_notice').append(html);
																	}
																}
															});
														}
													</script>
												</div>
												<div class="widget-body">
													<div class="widget-main padding-8">
														<!-- #section:pages/profile.feed -->
														<div id="profile_lastest_notice" class="profile-feed">
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
		</div><!-- /.main-container -->
	</body>
</html>
