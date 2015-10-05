var grid_selector = "#item_list";
jQuery(function($) {

	var parent_column = $(grid_selector).closest('[class*="col-"]');
	$(window).on('resize.jqGrid', function() {
		$(grid_selector).jqGrid('setGridWidth', parent_column.width());
		$(grid_selector).closest(".ui-jqgrid-bdiv").css({ 'overflow-x': 'hidden' });
	})
	console.log(parent_column.width());
	$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
		if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
			setTimeout(function() {
				$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
			}, 0);
		}
	})
	jQuery(grid_selector).jqGrid({
		datatype : "local",
		data : items,
		height : '100%',
		autowidth: true,
		colNames : ['商品名称','商品价格', '商品数量'],
		colModel : [ 
		            {name : 'name', index : 'name',width : 4},
		            {name : 'price', index : 'price',width : 3},
		            {name : 'amount',index : 'amount',width : 2}
		            ]
	});
	
	$(window).triggerHandler('resize.jqGrid');// 窗口resize时重新resize表格，使其变成合适的大小
	function updateActionIcons(table) {}
	if("CONFIRMED" == state || "UNPAID" == state||"CREATED" == state){
		$('#pay_way_div').removeClass("hidden");
		$('#pay_order_btn').removeClass("hidden");
	}
});
