<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
boolean success = (boolean)request.getAttribute("success");
String message = (String)request.getAttribute("message");
if(success){
	System.out.print("SUCCESS");
	out.println(message);
}else{
	System.out.print("FAILED");
	out.println("<script language='javascript'>");   
    out.println("alert('"+message+"！');");  
    out.println("location.href="+basePath+"profile/recharge.html");   
    out.print("</script>"); 
}
%>
