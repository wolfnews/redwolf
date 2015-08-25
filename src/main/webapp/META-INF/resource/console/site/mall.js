var grid_list = '#item_list';
var grid_page = '#item_pager';
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
			data={page:page,rows:6};
			url = base+'item/list';
			$.post(url,data,function(response){
				$(grid_list).empty();
				items = response.rows;
				html = inflateView(items);
				$(grid_list).append(html);
			});
       	 }
	});
	$('#my_carts').on('click', function (event) {
		location.href=base+'profile/cart.html';
	});
});

function inflateView(items){
	var html ="";
	for(var i=0;i<items.length;i++){
		var item = items[i];
		 html += "<div class='col-xs-4 pricing-box'>"+
					"<div class='widget-box widget-color-orange'>"+
						"<div class='widget-header'>"+
							"<h5 class='widget-title sm lighter'><strong>"+item.name+"</strong></h5>"+
						"</div>"+
						"<div class='widget-body'>"+
							"<div class='widget-main'>"+
								"<h5><strong>"+item.desp+"</strong></h5>"+
								"<h5><strong>商品数量："+item.sku+" </strong></h5>"+
								"<hr />"+
								"<div class='price'>价格："+
									item.price+
									"<small>.00 （元）</small>"+
								"</div>"+
							"</div>"+
							"<div>"+
								"<a href='#' onclick=\"add2Cart('"+item.id+"','"+item.name+"')\" class='btn btn-sm btn-block btn-warning'>"+
									"<i class='ace-icon fa fa-shopping-cart bigger-110'></i>"+
									"<span>购买</span>"+
								"</a>"+
							"</div>"+
						"</div>"+
					"</div>"+
				"</div>";
	}
	return html;
}

function add2Cart(id,name){
	data={num:1,name:name};
	url = base+'profile/cart/item/add/'+id;
	$.post(url,data,function(response){
		if(response.success){
			showMessage("商品已添加到购物车！");
		}else{
			showMessage("商品添加到购物车失败！");
		}
	})
}
