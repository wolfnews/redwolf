var grid_list = '#professor_list';
var grid_page = '#professor_pager';
jQuery(function($) {
	total = total == 0 ? 1:total;
	vpage = total>10 ? 10:total;
	$(grid_page).twbsPagination({
	    	startPage:1,
	    totalPages: total,
	    visiblePages: vpage,
	    version: '1.1',
	    onPageClick: function (event, page) {
			$(grid_list).empty();
			data={page:page,rows:20};
			url = base+'prof/list';
			$.post(url,data,function(response){
				$(grid_list).empty();
				professors = response.rows;
				html = inflateView(professors);
				$(grid_list).append(html);
			});
       	 }
	});
});

function inflateView(professors){
	var html ="";
	for(var i=0;i<professors.length;i++){
		var professor = professors[i];
		 html += "<div class=\"col-xs-3 pricing-box\">"+
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
								"<hr />"+
							"</div>"+
							"<div class=\"btn-group text-center\">"+
								"<button class=\"btn btn-sm btn-danger\" onclick=\"browseNotice("+professor.id+")\">"+
									"<i class=\"ace-icon fa fa-inbox bigger-110\"></i>"+
									"<span> <strong>宝盒</strong> </span>"+
								"</button><span>&nbsp;</span>"+
								"<button class=\"btn btn-sm btn-danger\" onclick=\"rssProfessor("+professor.id+")\">"+
									"<i class=\"ace-icon fa fa-asterisk bigger-110\"></i>"+
									"<span> <strong>关注</strong> </span>"+
								"</button>"+
								"<button class=\"btn btn-sm btn-danger\" onclick=\"subProfessor("+professor.id+")\">"+
									"<i class=\"ace-icon fa fa-shopping-cart bigger-110\"></i>"+
									"<span> <strong>订阅 </strong> </span>"+
								"</button><span>&nbsp;</span>"+
							"</div>"+
						"</div>"+
					"</div>"+
				"</div>";
	}
	return html;
}
//查看讲师宝盒
function browseNotice(professor){
	location.href='box.html?author='+professor;
}
//订阅讲师
function subProfessor(professor){
	location.href='profile/subscribe.html?notice=0&author='+professor;
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
