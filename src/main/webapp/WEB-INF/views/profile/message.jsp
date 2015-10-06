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
							total = ${total};
							total = total >0 ? total:1;
							type = '${type}';
							vpage = total>10 ? 10:total;
							$(document).ready(function(){
								label = '所有留言';
								if('send' == type){
									label = '我发出的留言';
								}else if('receive' == type){
									label = '我收到的留言';
								}
								$('#active_label').text(label);
	   						    $('#message_pager').twbsPagination({
	   						    	startPage:1,
								    totalPages: total,
								    visiblePages: vpage,
								    version: '1.1',
								    onPageClick: function (event, page) {
										$('#profile_message_div').empty();
										data={page:page,rows:10};
										url = base+'profile/message/list/'+type;
										$.post(url,data,function(response){
												messages = response.rows;
												for(var i=0;i<messages.length;i++){
													 var message = messages[i];
													 html = "<div class=\"profile-activity clearfix\">"+
																"<div>"+
																	"<span><strong>"+message.sender+"</strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
																	"<i class=\"ace-icon fa fa-clock-o bigger-110\">"+message.gmtCreate+"</i>"+
																"</div><hr>"+
																"<div>"+
																	"<span>"+message.content+"</span>"+
																	"<div class=\"text-right\"><button class=\"btn btn-danger\" onclick=\"replyMessage("+message.id+",'"+message.senderId+"','"+message.sender+"')\">回复</button></div>"+
																"</div>"+
															"</div>";
													 $('#profile_message_div').append(html);
												}
										});
  							       	 }
								});
							});
							function replyMessage(id,receiverId,receiver){
								bootbox.dialog({
									title : "<b>回复【"+receiver+"】</b>",
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
												data={receiver:receiver,receiverId:receiverId,content:content,last:id};
												url=base+"profile/message/add";
												$.post(url,data,function(response){
													alert(response.message);
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
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="../profile/index.html"><strong>首页</strong></a>
							</li>
							<li>
								<i class="ace-icon fa fa-inbox inbox-icon"></i>
								<a href="message.html"><strong>我的留言</strong></a>
							</li>
							<li class="active" id="active_label"><b></b></li>
							
						</ul>
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-10 col-xs-offset-1">
								<div id="profile_message_div"></div>
								<div class="text-right">
					                <ul id="message_pager"></ul>
					            </div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="../foot.jsp"/>
		</div>
	</body>
</html>
