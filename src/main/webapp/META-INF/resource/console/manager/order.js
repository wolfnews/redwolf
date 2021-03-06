var grid_selector = "#manage_order_list";
var page_selector = "#manage_order_page";
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
		url : base+'manage/order/list',
		datatype : "json",
		height : '100%',
		autowidth: true,
		colNames : ['订单编号','订单描述', '金额金额（￥、元）', '订单状态','支付方式','订单日期','操作'],
		colModel : [ 
			{name : 'sn',index : 'sn',width : 15},
			{name : 'desp',index : 'desp',width : 25},
			{name : 'total',index : 'total',width : 10},
			{name : 'state',index : 'state',width : 10, 
				formatter : function(cellvalue, options,rowObject) {
					switch (cellvalue) {
					case "PAID":
						return "<b>已支付</b>";
					case "DONE":
						return "<b>已完成</b>";
					default:
						return "未支付";
					}
				}
			},
			{name : 'payWay',index : 'payWay',width : 10,
				formatter:function(cellvalue, options,rowObject){
					switch (cellvalue) {
					case 'ALIPAY':
						return '<b>支付宝</b>';
					case 'TENPAY':
						return '<b>财付通</b>';
					default:
						return '';
					}
				}},
			{name : 'gmtCreate',index : 'gmtCreate',width : 10},
			{name : 'paid',	index : 'paid',	width : 60,fixed : true,sortable:false,
				formatter : function(cellvalue, options,rowObject) {
					return "<button class=\"btn btn-xs btn-danger\" onclick=\"removeOrder('"+rowObject.id+ "')\"><b>删除</b></button>";
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
function removeOrder(id){
	url = base+"manage/order/remove";
	data = {id:id};
	bootbox.confirm("<b>你确定要删除该订单?</b>", function(result) {
		if(result) {
			$.post(url,data,function(response){
				if(response.success){
					showMessage("操作成功！",function(){
						$(grid_selector).trigger("reloadGrid");
					});
				}else{
					showMessage("操作成功！");
				}
			});
		}
	});
}
