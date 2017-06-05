<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <jsp:include page="../../../resources/template/script-header.jsp"></jsp:include>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>Login Success!!</p> 
	<p><span><label>status : </label></label><%= request.getAttribute( "vaMessage" ) %></span></p>
	<p><span><label>hospital session : </label></label><%= session.getAttribute( "hospitalcode" ) %></span></p>
	<input type="hidden"  id="hidHospitalcode" value="${hospitalcode}" />
	<p><a href="${pageContext.request.contextPath}/LogoutSrvl">Logout</a></p>
</body>
</html>