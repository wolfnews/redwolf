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
function isMobile(s){ 
	var reg=/^1[3|4|5|7|8][0-9]\d{4,8}$/;
	return reg.exec(s)
} 


function isEmail (s){
	var reg =/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	return reg.exec(s);
}

function validateForm(){
	$("#username_error").text("");
	$("#password_error").text("");
	$("#repassword_error").text("");
	$("#email_error").text("");
	$("#mobile_error").text("");
	username=$('#profile_username').val().trim();
	password=$('#profile_password').val().trim();
	repassword=$('#profile_repassword').val().trim();
	occupation=$('#profile_occupation').val().trim();
	email=$('#profile_email').val().trim();
	mobile=$('#profile_mobile').val().trim();
	var passed = true;
	if(null == username || "" == username){
		$("#username_error").text("用户名为空！");
		passed = false;
	}else if(username.length<6){
		$("#username_error").text("名称少于6个字符");
		passed = false;
	}else if(username.length>30){
		$("#username_error").text("名称超过30个字符");
		passed = false;
	}
	if(null == password || "" == password){
		$("#password_error").text("密码为空！")
		passed = false;
	}else if(password.length<6){
		$("#password_error").text("密码少于6个字符");
		passed = false;
	}else if(password.length>30){
		$("#password_error").text("密码超过30个字符");
		passed = false;
	}else {
		if(null == repassword || "" == repassword){
			$("#repassword_error").text("确认密码为空！");
			passed = false;
		}else if(repassword.length<6){
			$("#repassword_error").text("确认密码少于6个字符");
			passed = false;
		}else if(repassword.length>30){
			$("#repassword_error").text("确认密码超过30个字符");
			passed = false;
		}else if(password != repassword){
			$("#repassword_error").text("两次密码不一致");
			passed = false;
		}
	}
	if(null == mobile || "" == mobile){
		$("#mobile_error").text("手机号为空！");
		passed = false;
	}else if(!isMobile(mobile)){
		$("#mobile_error").text("手机号码格式错误");
		passed = false;
	}
	if(null == email || "" == email){
		$("#email_error").text("邮箱为空！");
		passed = false;
	}else if(!isEmail(email)){
		$("#email_error").text("请输入正确的邮箱");
		passed = false;
	}
	return passed;
}
function register(){
	var passed = validateForm();
	if(passed){
		username=$('#profile_username').val().trim();
		password=$('#profile_password').val().trim();
		repassword=$('#profile_repassword').val().trim();
		occupation=$('#profile_occupation').val().trim();
		email=$('#profile_email').val().trim();
		mobile=$('#profile_mobile').val().trim();
		
		url = base+"profile/user/register";
		data = {username:username,password:password,occupation:occupation,email:email,mobile:mobile};
		$.post(url,data,function(response){
			if(response.success){
				showMessage("注册成功！我们给您发送了一封确认邮件，请登录邮箱进行注册确认。",function(){
					location.href=base+'profile/login.html';
				})
			}else{
				showMessage("注册失败！失败原因："+response.message);
			}
		});
	}
}
