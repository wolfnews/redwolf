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
		url : base+'manage/item/list',
		datatype : "json",
		height : '100%',
		autowidth: true,
		colNames : ['商品名称', '商品类型', '价格（￥）','价值（金币，天）','商品库存','上架日期','快捷操作'],
		colModel : [ 
			{name : 'name',index : 'name',width : 30},
			{name : 'category',index : 'category',width : 10,
				formatter:function(cell,option,object){
					if('SERVICEBAG' == cell){
						return '<b>服务包</b>';
					}else{
						return '<b>充值卡</b>';
					}
				}
			},
			{name : 'price',index : 'price',width : 10},
			{name : 'value',index : 'value',width : 15,
				formatter:function(cell,option,object){
					category = object.category;
					if('SERVICEBAG' == category){
						return '<b>'+cell+'天</b>';
					}else if('CREDITCARD' == category){
						return '<b>'+cell+'金币</b>';
					}else{
						return '<b>'+cell+'</b>';
					}
				}	
			},
			{name : 'sku',index : 'sku',width : 15},
			{name : 'gmtCreate',index : 'gmtCreate',width : 15},
			{
		    	name : '',
				index : '',
				width : 110,
				fixed : true,
				sortable : false,
				resize : false,
				formatter : function(cellvalue, options,rowObject) {
					return "<button class=\"btn btn-xs btn-danger\" onclick=\"showItem('"+rowObject.id+"')\"><b>编辑</b></button> &nbsp;"
							+"<button class=\"btn btn-xs btn-danger\" onclick=\"removeItem('"+rowObject.id+ "')\"><b>删除</b></button> &nbsp;";
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

function showAddItemWin(){
	bootbox.dialog({
		title : "<div class=\"danger\"><b>添加商品</b></div>",
		message : "<div class='well ' style='margin-top:1px;'>"+
						"<form class='form-horizontal' role='form' id='add_item_frm'>"+
				  			"<div class='form-group'>"+
				  				"<label class='col-sm-3'><b>商品名称：</b></label>"+
				  				"<div class='col-sm-9'>"+
	    	      					"<input id=\"item_name\" name='item_name' type='text' class=\"form-control\" />"+
	    	      				"</div>"+
	    	      			"</div>"+
	    	      			"<div class='form-group'>"+
				  				"<label class='col-sm-3'><b>商品类型：</b></label>"+
				  				"<div class='col-sm-9'>"+
				  					"<select id='item_category' class='form-control' onchange='showExtend()'>"+
 				  						"<option value='CREDITCARD'>充值卡</option>"+
 				  						"<option value='SERVICEBAG'>服务包</option>"+
 				  					"</select>"+
				  				"</div>"+
	    	      			"</div>"+
	    	      			"<div class='form-group'>"+
				  				"<label class='col-sm-3'><b>商品价格：</b></label>"+
				  				"<div class='col-sm-9'>"+
			      					"<input id=\"item_price\" name='item_price' type='text' class=\"form-control\" />"+
			      				"</div>"+
			      			"</div>"+
			      			"<div class='form-group'>"+
				  				"<label class='col-sm-3'><b>商品价值：</b></label>"+
				  				"<div class='col-sm-9'>"+
			      					"<input id=\"item_value\" name='item_value' type='text' class=\"form-control\" />"+
			      				"</div>"+
			      			"</div>"+
			      			"<div class='form-group'>"+
				  				"<label class='col-sm-3'><b>商品数量：</b></label>"+
				  				"<div class='col-sm-9'>"+
			      					"<input id=\"item_sku\" name='item_sku' type='text' class=\"form-control\" />"+
			      				"</div>"+
			      			"</div>"+
			      			"<div class='form-group'>"+
			      				"<label class='col-sm-3'><b>商品描述：</b></label>"+
				  				"<div class='col-sm-9'>"+
	    	      					"<textarea id=\"item_content\" class=\"form-control\" rows=\"3\"></textarea>"+
	    	      				"</div>"+
	    	      			"</div>"+
	    	      			"<div id='service_prof_div' class='hide form-group'>"+
	    	      				"<label class='col-sm-3'><b>服务讲师：</b></label>"+
	    	      				"<div class='col-sm-9'>"+
	    	      					"<select id='item_extend' class='form-control'>"+
	    	      				"</div>"+
	    	      			"</div>"+
	    	      		"</form>"+
	    	      	"</div>",
		buttons : {
			"success" : {
				"label" : "<i class='icon-ok'></i> <b>保存</b>",
				"className" : "btn-sm btn-danger",
				"callback" : function() {
					name=$('#item_name').val();
					category = $('#item_category').val();
					price = $('#item_price').val();
					sku = $('#item_sku').val();
					desc = $('#item_content').val();
					extend=$('#item_extend').val();
					value=$('#item_value').val();
					data={name:name,category:category,price:price,sku:sku,desc:desc,value:value,extend:extend};
					console.log(data);
					url=base+"manage/item/add";
					$.post(url,data,function(response){
						showMessage(response.message,function(){
							$(grid_selector).trigger("reloadGrid");
						});
					});
				}
			},
			"cancel" : {
				"label" : "<i class='icon-info'></i> <b>取消</b>",
				"className" : "btn-sm btn-warning",
				"callback" : function() {}
			}
		}
	});
}

function showExtend(id){
	var category = $('#item_category').val();
	if("SERVICEBAG"==category){
		$('#service_prof_div').removeClass("hide");
		data = {};
		url=base+'manage/professor/loadAll';
		$.post(url,data,function(response){
			if(response.success){
				profs = response.data;
				$('#item_extend').empty();
				for(var i=0;i<profs.length;i++){
					prof = profs[i];
					if(null != id && id == prof.id){
						$('#item_extend').append("<option value='"+prof.id+"' selected='true'>"+prof.username+"</option>");
 					}else{
						$('#item_extend').append("<option value='"+prof.id+"'>"+prof.username+"</option>");
					}
				}
			}else{
				showMessage(response.message);
			}
		});
	}else{
		$('#item_extend').empty();
		$('#service_prof_div').addClass("hide");                  
	}
}
function removeItem(id){
	url = base+"manage/item/remove/"+id;
	data = {};
	bootbox.confirm("<b>你确定要删除此商品?</b>", function(result) {
		if(result) {
			$.post(url,data,function(response){
				showMessage("删除商品失败！",function(){
					$(grid_selector).trigger("reloadGrid");
				});
			});
		}
	});
}
function showItem(id){
	url = base+'manage/item/detail/'+id;
	data = {};
	$.post(url,data,function(response){
		if(response.success){
			item = response.data;
			bootbox.dialog({
				title : "<div class=\"danger\"><b>编辑商品</b></div>",
				message : "<div class='well ' style='margin-top:1px;'>"+
								"<form class='form-horizontal' role='form' id='add_item_frm'>"+
						  			"<div class='form-group'>"+
						  				"<label class='col-sm-3'><b>商品名称：</b></label>"+
						  				"<div class='col-sm-9'>"+
						  					"<input id='item_id' value='"+item.id+"' type='hidden' class='form-control' />"+
			    	      					"<input id=\"item_name\" value='"+item.name+"' name='item_name' type='text' class=\"form-control\" />"+
			    	      				"</div>"+
			    	      			"</div>"+
			    	      			"<div class='form-group'>"+
						  				"<label class='col-sm-3'><b>商品类型：</b></label>"+
						  				"<div class='col-sm-9'>"+
						  					"<select id='item_category' class='form-control' onchange='showExtend()'>"+
		 				  						"<option value='CREDITCARD'>充值卡</option>"+
		 				  						"<option value='SERVICEBAG'>服务包</option>"+
		 				  					"</select>"+
						  				"</div>"+
			    	      			"</div>"+
			    	      			"<div class='form-group'>"+
						  				"<label class='col-sm-3'><b>商品价格：</b></label>"+
						  				"<div class='col-sm-9'>"+
					      					"<input id=\"item_price\" value='"+item.price+"' name='item_price' type='text' class=\"form-control\" />"+
					      				"</div>"+
					      			"</div>"+
					      			"<div class='form-group'>"+
						  				"<label class='col-sm-3'><b>商品价值：</b></label>"+
						  				"<div class='col-sm-9'>"+
					      					"<input id=\"item_value\" value='"+item.value+"' name='item_value' type='text' class=\"form-control\" />"+
					      				"</div>"+
					      			"</div>"+
					      			"<div class='form-group'>"+
						  				"<label class='col-sm-3'><b>商品数量：</b></label>"+
						  				"<div class='col-sm-9'>"+
					      					"<input id=\"item_sku\" value='"+item.sku+"' name='item_sku' type='text' class=\"form-control\" />"+
					      				"</div>"+
					      			"</div>"+
					      			"<div class='form-group'>"+
					      				"<label class='col-sm-3'><b>商品描述：</b></label>"+
						  				"<div class='col-sm-9'>"+
			    	      					"<textarea id=\"item_content\" value='"+item.desp+"' class=\"form-control\" rows=\"3\"></textarea>"+
			    	      				"</div>"+
			    	      			"</div>"+
			    	      			"<div id='service_prof_div' class='hide form-group'>"+
						  				"<label class='col-sm-3'><b>服务讲师：</b></label>"+
						  				"<div class='col-sm-9'>"+
						  					"<select id='item_extend' class='form-control'>"+
						  				"</div>"+
						  			"</div>"+
			    	      		"</form>"+
			    	      	"</div>",
				buttons : {
					"success" : {
						"label" : "<i class='icon-ok'></i> <b>保存</b>",
						"className" : "btn-sm btn-danger",
						"callback" : function() {
							name=$('#item_name').val();
							category = $('#item_category').val();
							price = $('#item_price').val();
							sku = $('#item_sku').val();
							desc = $('#item_content').val();
							extend=$('#item_extend').val();
							value=$('#item_value').val();
							id=$('#item_id').val();
							data={id:id,name:name,category:category,price:price,sku:sku,desc:desc,value:value,extend:extend};
							console.log(data);
							url=base+"manage/item/update";
							$.post(url,data,function(response){
								showMessage(response.message,function(){
									$(grid_selector).trigger("reloadGrid");
								});
							});
						}
					},
					"cancel" : {
						"label" : "<i class='icon-info'></i> <b>取消</b>",
						"className" : "btn-sm btn-warning",
						"callback" : function() {}
					}
				}
			});
			$('#item_content').text(item.desp);
			$('#item_category').val(item.category);
			showExtend(item.extend);
		}
	});
}