var grid_selector = "#news_list";
var page_selector = "#news_page";
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
		url : base+'manage/news/list',
		datatype : "json",
		height : '100%',
		autowidth: true,
		colNames : ['新闻标题', '新闻类型', '关键字','浏览量','添加日期','快捷操作'],
		colModel : [ 
			{name : 'title',index : 'title',width : 30},
			{name : 'category',index : 'category',width : 20,
				formatter:function(cellvalue, options,rowObject){
					switch(cellvalue){
					case 'POLICY':
						return '政策';
					case 'INVEST':
						return '投资';
					case 'STOCKS':
						return '股票';
					default :
						return '未知类型';
					}
				}
			},
			{name : 'tag',index : 'tag',width : 10},
			{name : 'browseTimes',index : 'browseTimes',width : 15},
			{name : 'gmtCreate',index : 'gmtCreate',width : 15},
			{
		    	name : '',
				index : '',
				width : 120,
				fixed : true,
				sortable : false,
				resize : false,
				formatter : function(cellvalue, options,rowObject) {
					return "<button class=\"btn btn-xs btn-danger\" onclick=\"showNotice('"+rowObject.id+ "','"+rowObject.status+"')\"><b>编辑</b></button> &nbsp;"
							+"<button class=\"btn btn-xs btn-danger\" onclick=\"removeNews('"+rowObject.id+ "')\"><b>删除</b></button> &nbsp;";
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
			},{},{},{},{},{}
	);

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
function showMessage(message,callbackFn){
	bootbox.dialog({
		message: '<b>'+message+'</b>', 
		buttons: {
			"success" : {
				"label" : "<b>确定</b>",
				"className" : "btn-sm btn-primary",
				callback: callbackFn
			}
		}
	});
}
function removeNews(id){
	url = base+"manage/news/remove";
	data = {id:id};
	bootbox.confirm("<b>你确定要删除此新闻?</b>", function(result) {
		if(result) {
			$.post(url,data,function(response){
				if(response.success){
					$(grid_selector).trigger("reloadGrid");
				}else{
					showMessage("删除新闻失败！");
				}
			});
		}
	});
}
function showNotice(id,status){
	url = base+"manage/notice/detail";
	data = {noticeId:id};
	$.post(url,data,function(response){
		console.log(response);
		if(response.success){
			message = '<div class="row" style=\"padding:10 10 10 10\">  ' +
						 '<div><b>标题：'+response.data.title+'</b></div><hr/>'+
						 '<div><b>作者：'+response.data.authorName+'</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>发布时间：'+response.data.gmtCreate+'</span></div><hr/>'+
			             '<div><b>免费内容：</b>'+
							response.data.publicContent+
						 '<hr><b>收费内容：</b>'+	
						 	response.data.privateContent+
						 '</div>'+
			    	  '</div>';
			if("NEW_CREATED" == status){
				title="<b>宝盒审核</b>";
				verifyUrl = base+"manage/notice/verify";
				bootbox.dialog({
					size:'large',
					title : title,
	                message: message,
	                buttons: {
	    				success: {
	  				        label: "<b>通过</b>",
	  				        className: "btn-success",
	  				        callback: function() {
	  				        	bootbox.confirm("<b>你确定通过该宝盒?</b>", function(result) {
		  				      		if(result) {
		  				      			verifyData = {noticeId:id,passed:result,reason:""};
		  				      			$.post(verifyUrl,verifyData,function(response){
		  				      				tempmessage="操作失败！";
		  				      				if(response.success){
		  				      					tempmessage="操作成功！";
		  				      				}
			  				      			showMessage(tempmessage,function(){
	  				      						$(grid_selector).trigger("reloadGrid");
	  				      					});
		  				      			});
		  				      		}
	  				      		});
	  				        }
	  				     },
	  				     danger: {
	  				         label: "<b>否决</b>",
	  				         className: "btn-danger",
	  				         callback: function() {
	  				        	bootbox.prompt({
	  				        	  title: "请输入否决原因",
	  				        	  callback: function(result) {
	  				        	    if (result === null ||""==result.trim()) {
	  				        	    	showMessage("请输入否决原因！");
	  				        	    } else {
	  				        	    	verifyData = {noticeId:id,passed:false,reason:result};
	  				        	    	$.post(verifyUrl,verifyData,function(response){
		  				      				tempmessage="操作失败！";
		  				      				if(response.success){
		  				      					tempmessage="操作成功！";
		  				      				}
			  				      			showMessage(tempmessage,function(){
	  				      						$(grid_selector).trigger("reloadGrid");
	  				      					});
		  				      			});
	  				        	    }
	  				        	  }
	  				        	});
	  				         }
	  				     },
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
				title="<b>宝盒详情</b>";
				bootbox.dialog({
					size:'large',
					title : title,
	                message: message,
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
			
        }else{
        	bootbox.dialog({
				size:'normal',
                title: title,
                message: '<div class="row">  ' +
                             '<div>获取宝盒详情失败！</div>'+
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