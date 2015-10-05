function showMessage(message,callbackFn){
	bootbox.dialog({
		message: message, 
		buttons: {
			"success" : {
				"label" : "<b>确定</b>",
				"className" : "btn-sm btn-danger",
				callback: callbackFn
			}
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
