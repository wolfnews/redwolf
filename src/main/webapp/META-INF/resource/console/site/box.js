var grid_list = '#box_list';
var grid_page = '#box_pager';
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
			data={page:page,rows:20,author:author};
			url = base+'box/list';
			$.post(url,data,function(response){
				boxes = response.rows;
				for(var i=0;i<boxes.length;i++){
					var box = boxes[i];
					html = "<div class=\"profile-activity clearfix\">"+
								"<div class=\"col-sm-10\">"+
								"<span class=\"bigger-110 left\" style=\"width=20%\"><strong>"+box.authorName+"&nbsp;:&nbsp;</strong></span>"+
								"<span class=\"bigger-110 left\" style=\"width=60%\"><strong>"+box.title+"</strong></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
								"<i class=\"ace-icon fa fa-clock-o bigger-110\">"+box.gmtCreate+"</i>"+
								"<span>&nbsp;&nbsp;|&nbsp;&nbsp;浏览："+box.browseNum+"次</span>"+
								"</div><div class=\"col-sm-2 text-center\">"+
								"<button class=\"btn btn-sm btn-danger\" onclick=\"box_detail("+box.id+")\">查看宝盒</button>"
								"</div><hr>"+
							"</div>";
					$(grid_list).append(html);
				}
			});
	       	 }
	});
});

function box_detail(id){
	location.href=base+'box/detail.html?id='+id;
}