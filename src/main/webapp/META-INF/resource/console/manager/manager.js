var grid_selector = "#manage_manager_list";
var page_selector = "#manage_manager_page";
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
		url : base+'manage/manager/pagination',
		datatype : "json",
		height : '100%',
		autowidth: true,
		colNames : ['账号', '手机号码', '简介', '级别','添加日期','快捷操作'],
		colModel : [ 
			{name : 'username',index : 'username',width : 30},
			{name : 'mobile',index : 'mobile',width : 20},
			{name : 'summary',index : 'summary',width : 20},
			{name : 'level',index : 'level',width : 10,
				formatter : function(cellvalue, options,rowObject) {
					return cellvalue;
				}
			},
			{name : 'gmtCreate',index : 'gmtCreate',width : 15},
			{
		    	name : '',
				index : '',
				width : 120,
				fixed : true,
				sortable : false,
				resize : false,
				formatter : function(cellvalue, options,rowObject) {
					return "<button class=\"btn btn-xs btn-danger\" onclick=\"showDetail('"+rowObject.id+ "')\"><b>详细</b></button> &nbsp;"+
						   "<button class=\"btn btn-xs btn-danger\" onclick=\"removeManager('"+rowObject.id+ "')\"><b>删除</b></button>";
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
			{ // navbar options
				edit : false,
				add : false,
				del : false,
				search : false,
				refresh : true,
				refreshstate :'current',
				refreshicon : 'ace-icon fa fa-refresh red',
				view : false
			},{},{},{},	{},{}
	);

	function styleCheckbox(table) {
	}
	function updateActionIcons(table) {
	}
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
function removeManager(id){
	url = base+"manage/manager/remove";
	data = {managerId:id};
	bootbox.confirm("<b>你确定要删除此员工账号?</b>", function(result) {
		if(result) {
			$.post(url,data,function(response){
				if(response.success){
					$(grid_selector).trigger("reloadGrid");
				}else{
					showMessage("删除失败！");
				}
			});
		}
	});
}
function showMessage(message,callbackFn){
	bootbox.dialog({
		message: '<b>'+message+'</b>', 
		buttons: {
			"success" : {
				"label" : "OK",
				"className" : "btn-sm btn-primary",
				callback: callbackFn
			}
		}
	});
}
function showDetail(id){
	url = base+"manage/manager/detail";
	data = {managerId:id};
	$.post(url,data,function(response){
		title="<b>员工详细信息</b>";
		if(response.success){
			message = '<div class="row">  ' +
						 '<div><b>员工账号：'+response.data.username+'</b></div><hr/>'+
			             '<div><b>手机号码：</b>'+
							response.data.mobile+
						 '<hr><b>员工级别：</b>'+
							response.data.level+
						 '<hr><b>员工简介：<b>'+	
						 	response.data.summary+
						 '</div>'+
			    	  '</div>';
			bootbox.dialog({
				size:'large',
				title : title,
				message: message,
				buttons: {
					main: {
						label: "<b>关闭</b>",
						className: "btn-primary",
						callback: function() {
							bootbox.hideAll();
						}
					}
				}
			});
        }else{
        	bootbox.dialog({
				size:'normal',
                title: title,
                message: '<div class="row">  ' +
                             '<div>获取员工详细失败！</div>'+
                    	'</div>',
                buttons: {
                	main: {
				         label: "关闭",
				         className: "btn-primary",
				         callback: function() {
				        	 bootbox.hideAll();
				         }
				     }
                }
            });
        }
	});
}
