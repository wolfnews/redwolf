jQuery(function($) {
	$("body").keyup(function(event){
		if(event.which == 13){
			$("#login_button").trigger("click");
		}
	})
});
function login(){
	url = base+"professor/profile/login";
	username=$('#professor_login_username').val().trim();
	password=$('#professor_login_password').val().trim();
	data = {username:username,password:password};
	$.post(url,data,function(response){
		if(response.success){
			location.href=base+'professor/index.html';
		}else{
        	showMessage(response.message,function(){
        		$('#professor_login_username').val("");
        		$('#professor_login_password').val("");
        	});
        }
	});
}
