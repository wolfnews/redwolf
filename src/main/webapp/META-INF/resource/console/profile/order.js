var grid_selector = "#profile_order_list";
var page_selector = "#profile_order_page";
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
		url : base+'profile/order/list?category='+type,
		datatype : "json",
		height : '100%',
		autowidth: true,
		colNames : ['订单编号','订单描述', '金额金额（￥、元）', '订单状态','订单日期','快捷操作'],
		colModel : [ 
			{name : 'sn',index : 'sn',width : 15},
			{name : 'name',index : 'name',width : 25},
			{name : 'total',index : 'total',width : 10,
				formatter:function(cellvalue, options,rowObject){
					return cellvalue+".00";
				}
			},
			{name : 'state',index : 'state',width : 10, 
				formatter : function(cellvalue, options,rowObject) {
					switch (cellvalue) {
					case "CREATED":
						return "<b>等待支付</b>";
					case "PAID":
						return "<b>支付完成</b>";
					case "DONE":
						return "<b>订单完成</b>";
					default:
						return "<b>等待支付</b>";
					}
				}
			},
			{name : 'gmtCreate',index : 'gmtCreate',width : 10},
			{name : 'state',	index : 'state',	width : 140,fixed : true,align:'right',
				formatter : function(cell, options,row) {
					var paidBtn="";
					if("PAID" != cell &"DONE" != cell){
						paidBtn = "<label class=\"btn btn-xs btn-danger btn-round\" onclick=\"toSettle('"+row.id+ "')\"><i class=\"ace-icon fa fa-credit-card\"/>支付</label>&nbsp;&nbsp;&nbsp;";
					}
					return paidBtn +"<label class=\"btn btn-xs btn-danger btn-round\" onclick=\"removeOrder('"+row.id+ "')\"><i class=\"ace-icon fa fa-trash\"/>删除</label>";
				}
		    }
		],
		viewrecords : true,
		rowNum : 10,
		rowList : [ 10,20,50,100,1000 ],
		pager : page_selector,
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
	jQuery(grid_selector).jqGrid(//分页栏按钮
			'navGrid',
			page_selector,
			{ 
				edit : false,
				add : false,
				del : false,
				search : false,
				refresh : true,
				refreshstate :'current',
				refreshicon : 'ace-icon fa fa-refresh red',
				view : false
			},{},{},{},{},{}
	);

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
});
function toSettle(id){
	location.href=base+'profile/order/detail/'+id+'.html';
}

function removeOrder(id){
	url = base+"profile/order/remove/"+id;
	data = {};
	bootbox.confirm("你确定要取消此订单？", function(result) {
		if(result) {
			$.post(url,data,function(response){
				if(response.success){
					$(grid_selector).trigger("reloadGrid");
				}else{
					showMessage(response.message);
				}
			});
		}
	});
}
