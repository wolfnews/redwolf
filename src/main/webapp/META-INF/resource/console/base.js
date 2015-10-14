$(document).ready(function(){
	bootbox.setDefaults("locale","zh_CN");
});
function showMessage(message,callbackFn){
	bootbox.dialog({
		message: "<b>温馨提示</b><hr/><center><b>"+message+"</b></center>", 
		buttons: {
			"success" : {
				"label" : "确定",
				"className" : "btn-sm btn-danger btn-round",
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
function stringFilter(s){
	var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
	var rs = "";   
    for (var i = 0; i < s.length; i++) {   
        rs = rs+s.substr(i, 1).replace(pattern, '');   
    } 
    return rs;
}