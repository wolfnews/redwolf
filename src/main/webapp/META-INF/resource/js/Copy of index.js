$(function() {
	$(".nav").movebg({
		width : 120/*滑块的大小*/,
		extra : 40/*额外反弹的距离*/,
		speed : 300/*滑块移动的速度*/,
		rebound_speed : 400
	/*滑块反弹的速度*/});
	$(".yx-rotaion").yx_rotaion({
		auto : true
	});
	$("#real_stock").yx_rotaion({
		auto : true
	});
	$('#golden_stock').BreakingNews({
		title : '红狼金股',
		titlebgcolor : '#BB0000',
		linkhovercolor : '#BB0000',
		border : '1px solid #BB0000',
		timer : 4000,
		effect : 'slide'
	});

	$('#month_click_top').BreakingNews({
		title : '月度排行',
		titlebgcolor : '#BB0000',
		linkhovercolor : '#BB0000',
		border : '1px solid #BB0000',
		timer : 4000,
		effect : 'slide'
	});

	// 淡隐淡现选项卡切换
	$("#real_stock").tabso({
		cntSelect : "#fadecon",
		tabEvent : "mouseover",
		tabStyle : "fade"
	});

	$('#user_login').leanModal({
		top : 110,
		overlay : 0.45,
		closeButton : ".hidemodal"
	});
	
	function userLogin(){
		var username = $("#username").val();
		var password = $("#password").val();
		alert(username+"_"+password);
		var errorMsg="";
		var isValid = true;
		if(null == username || ""==username.trim()){
			errorMsg = "用户名不能为空！"
		}
		if(null == password || "" == password.trim()){
			errorMsg += "密码不能为空！" 
		}
		if("" == errorMsg){
			//提交
		}else{
			alert(errorMsg);
		}
	}
	
});

