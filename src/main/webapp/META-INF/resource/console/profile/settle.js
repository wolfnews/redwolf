jQuery(function($) {

	if(continues =='true'){
		var grid_selector = "#item_list";
		$(window).on('resize.jqGrid', function() {
			$(grid_selector).jqGrid('setGridWidth', $(".page-content").width());
			$(grid_selector).closest(".ui-jqgrid-bdiv").css({ 'overflow-x': 'hidden' });
		})
		var parent_column = $(grid_selector).closest('[class*="col-"]');
		$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
			if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
				setTimeout(function() {
					$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
				}, 0);
			}
		})
		jQuery(grid_selector).jqGrid({
			datatype : "local",
			data : order.items,
			height : '100%',
			autowidth: true,
			colNames : ['商品名称','数量', '操作'],
			colModel : [ 
			            {name : 'name', index : 'name',width : 4},
			            {name : 'num',  index : 'num',width : 3},
			            {name : 'itemId',	index : 'itemId',	width : 60,fixed : true, align:'center',
			            	formatter : function(cell, options,rowObject) {
			            		return "<button class=\"btn btn-xs btn-danger\" onclick=\"removeItem('"+rowObject.id+ "')\"><b>删除</b></button>";
			            	}
			            }
			            ],
			            jsonReader: {
			            	root: "rows",
			            	total: "total",
			            	page: "page",
			            	records: "records",
			            	repeatitems: false
			            }
		});
		
		$(window).triggerHandler('resize.jqGrid');// 窗口resize时重新resize表格，使其变成合适的大小
	}else{
		$("#step-2-div").empty();
		errorHtml = "<div class='center'> "+reason+"！请点击<a href='"+base+"profile/cart.html' >返回购物车</a></div>"
		$("#step-2-div").append(errorHtml);
	}

		function updateActionIcons(table) {}
});
