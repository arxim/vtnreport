<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>Session Time Out</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="../../../resources/template/script-header.jsp"></jsp:include>
	<jsp:include page="../../../resources/template/navbar-header.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/resources/js/pages/main_menu/main_menu.js" type="text/javascript"></script>
</head>
<body>

	<div id="page-content-wrapper">
		<div class="container-fluid">
			<div class="panel panel-vtn"></div>
			<div class="container">
			<h2 class="msgSessionTimeOut text-center" > <span class="glyphicon   glyphicon-tags" aria-hidden="true"></span> <b> Your session has expired. Please login again! &nbsp; &nbsp;    <span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span>  <a class="linkBackHome" href="${pageContext.request.contextPath}/LogoutSrvl">     Login</a> <span class="glyphicon glyphicon-triangle-left" aria-hidden="true"></span></b> </h2>
				

			</div>

		</div>
	</div>
	<%--<p>Login Success!!</p> 
	 <p><span><label>status : </label></label><%= session.getAttribute( "vaMessage" ) %></span></p>
	<p><span><label>hospital session : </label></label><%= session.getAttribute( "hospitalcode" ) %></span></p>
	<input type="hidden"  id="hidHospitalcode" value="${hospitalcode}" />  
	<span><p>Your session has expired. Please log in again ...</p> <p><a href="${pageContext.request.contextPath}/LogoutSrvl">Logout</a></p></span>--%>
</body>
</html>