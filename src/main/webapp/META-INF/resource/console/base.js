$(document).ready(function(){
	bootbox.setDefaults("locale","zh_CN"); 
});

function showMessage(message,callbackFn){
	bootbox.dialog({
		message: "<b>温馨提示</b><hr/><center><b>"+message+"</b></center>", 
		buttons: {
			"success" : {
				"label" : "确定",
				"className" : "btn-sm btn-danger",
				callback: callbackFn
			}
		}
	});
}
function confirm(message,callbackOk,callbackCancel){
	bootbox.confirm("<b>操作提示</b><hr><center><b>"+message+"</b></center>", function(result) {
		if(result) {
			callbackOk();
		}else{
			if(null == callbackCancel || undefined == callbackCancel){
				return;
			}
			callbackCancel();
		}
	});
}
function successNotice(title,content){
	notify(title,content,'success');
}
function errorNotice(title,content){
	notify(title,content,'error');
}
function warnNotice(title,content){
	notify(title,content,'warn');
}
function notify(title,content,type){
	switch(type){
	case 'success':
		type = 'error';
		break;
	case 'warn':
		type='warning';
		break;
	case 'error':
		type='error';
		break;
	default:
		type='info';
		break;
	}
	$.gritter.add({
		title: title,
		text: '<div class="center"><b>'+content+'</b></center>',
		class_name: 'bottom-right gritter-'+type+' gritter-light'
	});
}
function stringFilter(s){
	var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	var rs = "";   
    for (var i = 0; i < s.length; i++) {   
        rs = rs+s.substr(i, 1).replace(pattern, '');   
    } 
    return rs;
}