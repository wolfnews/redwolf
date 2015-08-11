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
	url = base+"profile/user/login";
	username=$('#profile_login_username').val().trim();
	password=$('#profile_login_password').val().trim();
	data = {username:username,password:password};
	$.post(url,data,function(response){
		if(response.success){
			location.href=base+'profile/index.html';
		}else{
        	showMessage(response.message,function(){
        		$('#profile_login_username').val("");
        		$('#profile_login_password').val("");
        	});
        }
	});
}
function isEmail (s){
	var reg =/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	return reg.exec(s);
}

function passwordGetback(){
	email = $('#user_email').val().trim();
	if(null == email || "" == email){
		showMessage("请输入邮箱！");
	}else{
		if(!isEmail(email)){
			showMessage("请输入格式正确的邮箱地址！",function(){
				$("#user_email").val("");
			})
		}else{
			url = base+"profile/user/retrieve";
			data = {email:email};
			$.post(url,data,function(response){
				if(response.success){
					showMessage("密码找回成功！"+response.message,function(){
						window.location.href=base+'profile/login.html';
					})
				}else{
					showMessage("密码找回失败！"+response.message);
				}
			});
		}
	}
}
