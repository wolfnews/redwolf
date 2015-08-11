<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="alipaysubmit" name="alipaysubmit"
		action="https://mapi.alipay.com/gateway.do?_input_charset=utf-8"
		method="get">
		<input type="hidden" name="payment_type" value="1" /><input
			type="hidden" name="out_trade_no" value="20150716170026898" /><input
			type="hidden" name="_input_charset" value="utf-8" /><input
			type="hidden" name="subject" value="???????????????" /><input
			type="hidden" name="service" value="create_direct_pay_by_user" /><input
			type="hidden" name="total_fee" value="1" /><input type="hidden"
			name="sign" value="255b92ec28359cf24acc0e182d6cceb9" /><input
			type="hidden" name="return_url"
			value="http://商户网关地址/create_direct_pay_by_user-JAVA-UTF-8/return_url.jsp" /><input
			type="hidden" name="notify_url"
			value="http://商户网关地址/create_direct_pay_by_user-JAVA-UTF-8/notify_url.jsp" /><input
			type="hidden" name="body" value="?????????" /><input type="hidden"
			name="sign_type" value="MD5" /><input type="submit" value="确认"
			style="display: none;">
	</form>
	<script>
		document.forms['alipaysubmit'].submit();
	</script>
	<form id="alipaysubmit" name="alipaysubmit"
		action="https://mapi.alipay.com/gateway.do?_input_charset=utf-8"
		method="get">
		<input type="hidden" name="payment_type" value="1" /><input
			type="hidden" name="out_trade_no" value="2015071617003868" /><input
			type="hidden" name="_input_charset" value="utf-8" /><input
			type="hidden" name="subject" value="???????????????" /><input
			type="hidden" name="service" value="create_direct_pay_by_user" /><input
			type="hidden" name="total_fee" value="1" /><input type="hidden"
			name="sign" value="f72a23fab7495e9ecb954eb1be216016" /><input
			type="hidden" name="return_url"
			value="http://商户网关地址/create_direct_pay_by_user-JAVA-UTF-8/return_url.jsp" /><input
			type="hidden" name="notify_url"
			value="http://商户网关地址/create_direct_pay_by_user-JAVA-UTF-8/notify_url.jsp" /><input
			type="hidden" name="body" value="?????????" /><input type="hidden"
			name="sign_type" value="MD5" /><input type="submit" value="确认"
			style="display: none;">
	</form>
	<script>
		document.forms['alipaysubmit'].submit();
	</script>
</body>
</html>