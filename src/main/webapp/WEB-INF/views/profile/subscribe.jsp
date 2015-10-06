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
		<script src="${base}static/ace/assets/js/ace/elements.spinner.js"></script>
		<script src="${base}static/ace/assets/js/fuelux/fuelux.spinner.js"></script>
		<script src="${base}static/ace/assets/js/ace-extra.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='${base}';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div id="sidebar" class="sidebar responsive"></div>
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
							var box = ${box};
							$(document).ready(function(){
								var groups = ${groups};
								$("#subscribe_group").empty();
								for(var i=0;i<groups.length;i++){
									group = groups[i];
									$("#subscribe_group").append("<option value='"+group.id+"'>"+group.name+"："+group.price+"积分/天</option>");
								}
								$("#subscribe_group").change(function(){
									group = $('#subscribe_group').val();
									price = getPrice(group);
									time = $('#subscribe_time').val();
									cost = time*price;
									$('#cost_grade').val(cost);
								});
								$('#subscribe_time').ace_spinner({value:1,min:1,max:300,step:1, on_sides: true, icon_up:'ace-icon fa fa-plus bigger-120', icon_down:'ace-icon fa fa-minus bigger-120', btn_up_class:'btn-danger' , btn_down_class:'btn-danger'})
								.on('changed.fu.spinbox', function(){
									alert($('#subscribe_time').val());
								});
							});
							function getPrice(id){
								for(var i=0;i<groups.length;i++){
									group = groups[i];
									if(id ==group.id){
										return group.price;
									}
								}
								return null;
							}
						</script>
						<ul class="breadcrumb">
							<li>
								<strong>当前位置：</strong>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="index.html"><strong>个人中心</strong></a>
							</li>
							<li class="active">
								<i class="ace-icon fa fa-user"></i>
								<span><strong>订阅讲师</strong></span>
							</li>
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
													<img id="avatar" class="editable img-responsive" alt="${professor.username }" src="${base}static/ace/assets/avatars/profile-pic.jpg" />
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
											<!-- #section:custom/extra.grid -->
											<div class="clearfix">
												<div class="grid1">
													<span class="bigger-175 blue">${professor.level }</span>
													<br />
													<strong>讲师等级</strong>
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
													<span class="line-height-1 bigger-170 white"> ${professor.subCount }人 </span>
													<br />
													<span class="line-height-1 smaller-90"><strong>用户订阅</strong></span>
												</span>
												<!-- 查看关注的讲师信息-->
												<span class="btn btn-app btn-sm btn-warning no-hover">
													<span class="line-height-1 bigger-170"> ${professor.rssCount }人 </span>
													<br />
													<span class="line-height-1 smaller-90"> <strong>用户关注</strong> </span>
												</span>
												<!-- 锦囊总数 -->
												<span class="btn btn-app btn-sm btn-grey no-hover">
													<span class="line-height-1 bigger-170"> ${professor.boxCount }个 </span>

													<br />
													<span class="line-height-1 smaller-90"> <strong>宝盒总数</strong> </span>
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
														<strong>讲师简介</strong>
													</h4>
												</div>
												<div class="widget-body">
													<div class="widget-main padding-8">
														<div id="profile_lastest_box" class="profile-feed">
														${professor.summary }
														</div>
													</div>
												</div>
											</div>
											<div class="hr hr2 hr-double"></div>
											<div class="col-xs-12">
												<div class="well well">
													<div style="text-align: justify;">
														<span style="font-size: 14px; line-height: 29.1666679382324px;">
														&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</span>
														<font face="Verdana"><b style="font-size: 14px; line-height: 29.1666679382324px;">
														<font color="#f83a22">温馨提示：股市有风险，投资需谨慎！</font></b>
														<font color="#f83a22" style="font-size: 14px; line-height: 29.1666679382324px;">
														<b>牛股会讲师在平台中发表的宝盒仅代表其个人观点，不代表牛股会平台对股市的观点。广大牛股会会友在阅读讲师发表的宝盒时，不要盲从，理性判断，理性投资。</b>
														<br/><span>&nbsp; &nbsp; &nbsp; </span>
														<b>特别提示：您在投资过程中因参考牛股会平台讲师的观点而造成的任何经济损失都属于个人行为，和牛股会平台无关！请在订阅讲师时慎重考虑！</b>
														</font></font>
													</div>
													<hr>
													<div class="col-sm-12" align="right">
														<input id="accept_risk" name="accept_risk" type="radio" value="accept"  onclick="changeButton()" class="ace" />
														<span class="lbl"> <strong>我了解风险，仍然愿意订阅该讲师</strong></span>
														<input id="accept_risk" name="accept_risk" type="radio" value="reject"  onclick="changeButton()" class="ace" checked="checked"/>
														<span class="lbl"> <strong>我害怕风险，放弃订阅该讲师</strong></span>
													</div>
													<br>
													<script type="text/javascript">
														function changeButton(){
															accept = $('input[name="accept_risk"]:checked').val();
															if('accept'==accept){
																$('#subscribe_btn').removeClass("disabled");
															}else if('reject' == accept){
																$('#subscribe_btn').addClass("disabled");
															}
															
														}
													</script>
												</div>
												<div class="well well-sm" align="right">
													<input type="hidden" id="professor_id" value="${professor.id }">
													<label><strong>选择讲师分组</strong></label>
													<select id="subscribe_group" name="subscribe_group" style="width: 200px">
													</select>&nbsp;&nbsp;
													<label><strong>订阅时长：</strong></label>
													<input type="text" class="input" id="subscribe_time" />&nbsp;&nbsp;<b>天</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<label><strong>消耗积分：</strong></label><span id="cost_grade"></span>
													<button id="subscribe_btn" onclick="subsProf()" class="btn btn-danger disabled"><strong>订阅</strong></button>
												</div>
												<script type="text/javascript">
													function subsProf(){
														url ='user/subsProf';
														data = {
																professor:$('#professor_id').val(),
																group : $('#subscribe_group').val(),
																time  : $('#subscribe_time').val()};
														$.post(url,data,function(response){
															if(response.success){
																showMessage("订阅成功",function(){
																	if(null != box){
																		location.href='box/detail.html?id='+box;
																	}
																})
															}else{
																showMessage("订阅失败："+response.message,function(){
																	location.href='index.html'
																});
															}
														});
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
