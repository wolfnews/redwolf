var grid_selector = "#item_list";
var page_selector = "#item_page";
jQuery(function($) {
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
		url : base+'profile/cart/list',
		datatype : "json",
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
		altRows : true,
		jsonReader: {
			root: "rows",
			total: "total",
			page: "page",
			records: "records",
			repeatitems: false
		},
		loadComplete : function() {
			var table = this;
			setTimeout(function() {
				updateActionIcons(table);
				updatePagerIcons(table);
				enableTooltips(table);
			}, 0);
		}
	});
	$(window).triggerHandler('resize.jqGrid');// 窗口resize时重新resize表格，使其变成合适的大小

	function updateActionIcons(table) {}
	function updatePagerIcons(table) {
		var replacement = {
			'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
			'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
			'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
			'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
		};
		$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
			.each(function() {
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				if ($class in replacement)
					icon.attr('class', 'ui-icon '+ replacement[$class]);
			})
	}

	function enableTooltips(table) {
		$('.navtable .ui-pg-button').tooltip({
			container : 'body'
		});
		$(table).find('.ui-pg-div').tooltip({
			container : 'body'
		});
	}
	$('#visit_more_btn').on('click', function (event) {
		location.href=base+'mall.html';
	});
	$('#goto_settle_btn').on('click', function (event) {
		url = base+'profile/cart/list';
		data = {};
		$.post(url,data,function(response){
			if(response.rows.length>0){
				location.href = base+'profile/settle.html'
			}else{
				showMessage("您的购物车是空的，再逛逛吧？",function(){
					location.href=base+'mall.html';
				});
			}
		});
	});
});

function removeItem(id){
	url = base+'profile/cart/item/remove/'+id;
	data={};
	$.post(url,data,function(response){
		$(grid_selector).trigger("reloadGrid");
	});
}
