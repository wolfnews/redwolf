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
		<script src="${base}static/ace/jquery.twbsPagination.js"></script>
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
							total = ${totalPages};
							type = '${type}';
							author = '${author}';
							vpage = total>10 ? 10:total;
							$(document).ready(function(){
								label = '所有宝盒';
								if('rss' == type){
									label = '查看关注宝盒';
								}else if('subs' == type){
									label = '查看订阅宝盒';
								}
								$('#active_label').text(label);
	   						    $('#box_pager').twbsPagination({
	   						    	startPage:1,
								    totalPages: total,
								    visiblePages: vpage,
								    version: '1.1',
								    onPageClick: function (event, page) {
										$('#profile_lastest_box').empty();
										data={page:page,rows:10,type:type,author:author};
										url = base+'profile/box/pagination';
										
										$.post(url,data,function(response){
											if(response.success){
												boxs = response.data.rows;
												for(var i=0;i<boxs.length;i++){
													 var box = boxs[i];
													 html = "<div class=\"profile-activity clearfix\">"+
																"<div>"+
																	"<span style='font-family:tahoma,Arial,\"Microsoft YaHei\",SimSun;font-size:16px'><strong>【"+box.authorName+"】："+box.title+"</strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
																	"<i class=\"ace-icon fa fa-clock-o bigger-110\">"+box.gmtCreate+"</i>"+
																"</div><hr>"+
																"<div>"+
																	"<span style='font-family:tahoma,Arial,\"Microsoft YaHei\",SimSun;font-size:14px'>"+box.publicContent+"</span>"+
																	"<div class=\"text-right\"><button class=\"btn btn-danger btn-sm btn-round\" onclick=\"boxDetail("+box.id+")\">查看宝盒</button></div>"+
																"</div>"+
															"</div>";
													 $('#profile_lastest_box').append(html);
												}
											}
										});
  							       	 }
								});
							});
							function boxDetail(id){
								location.href='box/detail.html?id='+id;
							}
						</script>
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="../profile/index.html"><strong>首页</strong></a>
							</li>
							<li>
								<i class="ace-icon fa fa-inbox inbox-icon"></i>
								<a href="box.html"><strong>宝盒浏览</strong></a>
							</li>
							<li class="active" id="active_label"><b></b></li>
							
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-10 col-xs-offset-1">
								<div id="profile_lastest_box"></div>
								<div class="text-right">
					                <ul id="box_pager"></ul>
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
