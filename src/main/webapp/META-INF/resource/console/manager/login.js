jQuery(function($) {
	$("body").keyup(function(event){
		if(event.which == 13){
			$("#login_button").trigger("click");
		}
	})
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
function login(){
	url = base+"manage/manager/login";
	username=$('#manager_login_username').val().trim();
	password=$('#manager_login_password').val().trim();
	data = {username:username,password:password};
	$.post(url,data,function(response){
		if(response.success){
			location.href=base+'manage/index.html';
		}else{
        	showMessage(response.message,function(){
        		$('#manager_login_username').val("");
        		$('#manager_login_password').val("");
        	});
        }
	});
}
