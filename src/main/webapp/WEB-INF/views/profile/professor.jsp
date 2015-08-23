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
		<title>牛股会-查看讲师信息</title>
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
		<script src="<%=basePath%>static/console/base.js"></script>
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
							var type='${type}';
							//查看讲师宝盒
							function browseBox(professor){
								location.href='box.html?type=all&author='+professor;
							}
							//订阅讲师
							function subProfessor(professor){
								location.href='subscribe.html?box=0&author='+professor;
							}
							
							//关注讲师
							function rssProfessor(professor){
								$.post(base+'profile/user/rssProf',{professorId:professor},function(response){
									if(response.success){
										showMessage("关注成功！",function(){
											location.href=base+'profile/professor.html';
										});
									}else{
										showMessage("关注失败！");
									}
								});
							}

							$(document).ready(function(){
								function isExist(id,array){
									for(var i=0;i<array.length;i++){
										if(id==array[i]){
											return true;
										}
									}
									return false;
								}
								subArray = ${subList};
								rssArray = ${rssList};
								function isRssed(id){
									return isExist(id,rssArray);
								}
								function isSubscribed(id){
									return isExist(id, subArray);
								}
								$('#professor_show_div').empty();
								currentPage = ${currentPage};
								if(null == currentPage){
									currentPage=1;
								}
								$('#currentPage').val(currentPage);
								professors = ${professors};
								for(var i=0;i<professors.length;i++){
									 var professor = professors[i];
									 var rssbutton = null;
									 var subbutton = null;
// 									 var subsHtml = '<h5><strong>暂未订阅！</strong></h5><hr>';
									var subsHtml = '';
									 if(isSubscribed(professor.id)){
										 subbutton = "续订";
										 if(type=='subs'){
											 subsHtml ='<h5 class=\"text-center\"><strong>到期时间：'+professor.groups[0].expiredTime+'</strong></h5><hr>';
										 }
									 }else{
										 subbutton = "订阅";
									 }
									 
									 if(isRssed(professor.id)){
										 rssbutton = "<button class=\"btn btn-sm disabled btn-danger\">";
									 }else{
										 rssbutton = "<button class=\"btn btn-sm btn-danger\" onclick=\"rssProfessor("+professor.id+")\">"
									 }
									 
									 html = "<div class=\"col-xs-3 pricing-box\">"+
												"<div class=\"widget-box widget-color-red\">"+
													"<div class=\"widget-header\">"+
														"<h5 class=\"widget-title bigger lighter\"><strong>"+professor.username+"</strong></h5>"+
													"</div>"+
													"<div class=\"widget-body\">"+
														"<div class=\"widget-main\">"+
															"<h5><strong>当前职业："+professor.occupation+"</strong></h5>"+
															"<h5><strong>锦囊总数："+professor.boxCount+" 篇</strong></h5>"+
															"<h5><strong>用户订阅："+professor.subCount+" 人</strong></h5>"+
															"<h5><strong>用户关注："+professor.rssCount+" 人</strong></h5>"+
															"<hr />"+subsHtml+
// 															"<div class=\"price\">"+
// 																"<small>"+professor.prices+"积分\/天</small>"+
// 															"</div>"+
														"</div>"+
														"<div class=\"btn-group text-center\">"+
															"<button class=\"btn btn-sm btn-danger\" onclick=\"browseBox("+professor.id+")\">"+
																"<i class=\"ace-icon fa fa-inbox bigger-110\"></i>"+
																"<span> <strong>宝盒</strong> </span>"+
															"</button><span>&nbsp;</span>"+
																rssbutton+
																"<i class=\"ace-icon fa fa-asterisk bigger-110\"></i>"+
																"<span> <strong>关注</strong> </span>"+
															"</button>"+
															"<button class=\"btn btn-sm btn-danger\" onclick=\"subProfessor("+professor.id+")\">"+
																"<i class=\"ace-icon fa fa-shopping-cart bigger-110\"></i>"+
																"<span> <strong>"+subbutton+" </strong> </span>"+
															"</button>"+
															"<button class=\"btn btn-sm btn-danger\" onclick=\"leaveMessage("+professor.id+",'"+professor.username+"')\">"+
																"<span> <strong>留言</strong> </span>"+
															"</button><span>&nbsp;</span>"+
														"</div>"+
													"</div>"+
												"</div>"+
											"</div>";
									 $('#professor_show_div').append(html);
								}
							});
							function leaveMessage(id,receiver){
								bootbox.dialog({
									title : "<b>给【"+receiver+"】留言</b>",
									message : "<div class='well ' style='margin-top:1px;'>"+
													"<form class='form-horizontal' role='form'>"+
											  			"<div class='form-group'>"+
											  				"<div class='col-sm-12'>"+
								    	      					"<textarea id=\"message_content\" class=\"form-control\" rows=\"5\"></textarea>"+
								    	      				"</div>"+
								    	      			"</div>"+
								    	      		"</form>"+
								    	      	"</div>",
									buttons : {
										"success" : {
											"label" : "<i class='icon-ok'></i> 提交",
											"className" : "btn-sm btn-success",
											"callback" : function() {
												content=$("#message_content").val();
												data={receiverId:id,receiver:receiver,content:content,last:null};
												url=base+"profile/message/add";
												$.post(url,data,function(response){
													showMessage(response.message);
												});
											}
										},
										"cancel" : {
											"label" : "<i class='icon-info'></i> 取消",
											"className" : "btn-sm btn-danger",
											"callback" : function() { }
										}
									}
								});
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
								<span><strong>查看讲师</strong></span>
							</li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12" id="professor_show_div"></div>
						</div>
					</div>
					<jsp:include page="../foot.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</body>
</html>
