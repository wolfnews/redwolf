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
		<title>牛股会-行业资讯</title>
		<meta name="Keywords" content="牛股会">
		<meta name="Description" content="牛股会">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="<%=basePath%>static/ace/assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
		<link rel="stylesheet" href="<%=basePath%>static/css/main.css" />
		<script src="<%=basePath%>static/ace/assets/js/jquery.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/bootstrap.js"></script>
		<script src="<%=basePath%>static/ace/assets/js/ace/ace.js"></script>
		<script type="text/javascript" src="<%=basePath%>static/js/nav.js"></script>
		<script type="text/javascript" src="<%=basePath%>static/js/index.js"></script>
	</head>
	<body class="no-skin">
		<jsp:include page="header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				var base='<%=basePath%>';
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
				$(document).ready(function(){
					var exist = ${exist};
					if(!exist){
						$('#box_detail_div').empty();
						$('#box_detail_div').html("<span class=\"red h4 strong\"><strong>很抱歉！您查看的宝盒可能已被删除...</strong></span>")
					}else{
						sub = ${sub};
						if(!sub){
							$('#box_private').empty();
							id=${box.id};
							author=${box.author};
							html = "<span>更多内容请订阅后阅读...</span>"+
								   "<div class=\"text-right\"><button class=\"btn btn-danger\""+
								   " onclick=\"jumpToSubscribe("+author+","+id+")\"><strong>订阅宝盒</strong></button></div>";
							$('#box_private').html(html);
						}
					}
				});
				function jumpToSubscribe(author,box){
					location.href=base+'profile/subscribe.html?author='+author+"&box="+box;
				}
			</script>
			<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
					try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
// 					box = ${box};
// 					console.log(box);
					</script>
						<ul class="breadcrumb">
							<li>
								<strong>当前位置：</strong>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="<%=basePath%>box.html"><strong>牛股宝盒</strong></a>
							</li>
							<li class="active">
								<i class="ace-icon fa fa-user"></i>
								<span><strong>查看宝盒</strong></span>
							</li>
						</ul>
					</div>
					<div class="page-content">
						<div class="row" id="box_detail_div">
							<div class="col-xs-10 col-xs-offset-1">
							<br>
								<div class="col-xs-5">
								<span class="red h4 strong">${box.title}</span>
								</div>
								<div class="col-xs-5">
									<span class="red h4 strong">&nbsp;&nbsp;&nbsp;&nbsp;</span>
									<span class="red h4 strong">讲师：${box.authorName},&nbsp;&nbsp;${box.gmtCreate}</span>
								</div>
								<div class="col-xs-2">
								<span class="red h4 strong">浏览：${box.browseNum}次</span>
								</div>
							</div>
							<div class="col-xs-10 col-xs-offset-1">
								<div class="hr"></div>
								<div class="col-xs-12">${box.publicContent}</div>
								<div class="col-xs-12" id="box_private">${box.privateContent }</div>
								<div class="col-xs-12"><hr></div>
								<div class="col-xs-12 text-center"><span style="color: rgb(128, 130, 133); font-family: 宋体, Helvetica, Arial, sans-serif; font-size: 14px; line-height: 21px;">风险提示：以上内容仅供参考，不作为投资决策依据，投资者据此操作，风险自担。股市有风险，投资需谨慎。</span></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="../foot.jsp"/>
		</div>
	</body>
</html>