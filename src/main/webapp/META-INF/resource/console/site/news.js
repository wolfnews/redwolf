var grid_list = '#news_list';
var grid_page = '#news_pager';
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
			url = base+'news/list';
			$.post(url,data,function(response){
				news = response.rows;
				for(var i=0;i<news.length;i++){
					var newObj = news[i];
					html = "<div class=\"profile-activity clearfix\">"+
								"<div class=\"col-sm-9\">"+
								"<span class=\"bigger-110 left\" style=\"width=60%\"><strong>"+newObj.title+"</strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
								"<i class=\"ace-icon fa fa-clock-o bigger-110\">"+newObj.gmtCreate+"</i>"+
								"<span>&nbsp;&nbsp;|&nbsp;&nbsp;浏览次数："+newObj.browseTimes+"</span>"+
								"</div><div class=\"col-sm-3\">"+
								"<button class=\"btn btn-sm btn-danger\" onclick=\"news_detail("+newObj.id+")\">查看详细</button>"
								"</div><hr>"+
							"</div>";
					$(grid_list).append(html);
				}
			});
	       	 }
	});
});

function news_detail(id){
	location.href=base+'news/detail.html?id='+id;
}