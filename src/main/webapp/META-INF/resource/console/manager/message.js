jQuery(function($) {
	var grid_selector = "#manage_notice_list";
	var page_selector = "#manage_notice_page";
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
		url : base+'manage/notice/pagination',
		datatype : "json",
		height : '100%',
		autowidth: true,
		colNames : ['标题', '作者', '关键字', '状态','点赞数量','生成日期','快捷操作'],
		colModel : [ 
			{name : 'title',index : 'title',width : 30},
			{name : 'author',index : 'author',width : 20},
			{name : 'keyword',index : 'keyword',width : 20},
			{name : 'status',index : 'status',width : 10,
				formatter : function(cellvalue, options,rowObject) {
					return cellvalue;
				}
			},
			{name : 'favorNum',index : 'favorNum',width : 15},
			{name : 'gmtCreate',index : 'gmtCreate',width : 15},
			{
		    	name : '',
				index : '',
				width : 120,
				fixed : true,
				sortable : false,
				resize : false,
				formatter : function(cellvalue, options,rowObject) {
					return "<button class=\"btn btn-xs btn-danger\" onclick=\"jumpToDetail('"+rowObject.id+"','"+ rowObject.channelId+ "')\"><b>详细</b></button> &nbsp;"+"<button class=\"btn btn-xs btn-danger\" onclick=\"refresh('"+rowObject.id+ "')\"><b>刷新</b></button> &nbsp;";
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
			},{},{},{},
			{
				recreateForm : true,
				afterShowSearch : function(e) {
					var form = $(e[0]);
					form.closest('.ui-jqdialog').find('.ui-jqdialog-title')
							.wrap('<div class="widget-header" />')
					style_search_form(form);
				},
				afterRedraw : function() {
					style_search_filters($(this));
				},
				multipleSearch : false
			},
			{}
	);

	function style_search_filters(form) {
		form.find('.delete-rule').val('X');
	}
	function style_search_form(form) {
		var dialog = form.closest('.ui-jqdialog');
		var buttons = dialog.find('.EditTable')
		buttons.find('.EditButton a[id*="_query"]').addClass(
				'btn btn-sm btn-inverse').find('.ui-icon').attr('class',
				'ace-icon fa fa-comment-o');
		buttons.find('.EditButton a[id*="_search"]').addClass(
				'btn btn-sm btn-purple').find('.ui-icon').attr('class',
				'ace-icon fa fa-search');
	}
	function styleCheckbox(table) {
	}

	// unlike navButtons icons, action icons in rows seem to be hard-coded
	// you can change them like this in here if you want
	function updateActionIcons(table) {
	}
	// replace icons with FontAwesome icons like above
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

	$(document).one('ajaxloadstart.page', function(e) {
		$(grid_selector).jqGrid('GridUnload');
		$('.ui-jqdialog').remove();
	});
});
