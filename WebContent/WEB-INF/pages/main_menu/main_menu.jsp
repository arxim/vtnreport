<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta charset="UTF-8">
	<title>Main Report</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="../../../resources/template/script-header.jsp"></jsp:include>
	<jsp:include page="../../../resources/template/navbar-header.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/resources/js/pages/main_menu/main_menu.js" type="text/javascript"></script>
</head>
<body>
	<div id="page-content-wrapper">
		<form id="frmPaymentAll" action="/vtnreport/getPaymentAllContentSrvl" method="post">
		</form>
		<form id="frmPayment" action="/vtnreport/getPaymentContentSrvl" method="post">
		</form>
		<form id="frmTax" action="/vtnreport/getTaxContentSrvl" method="post">
		</form>
		<form id="frmEmail" action="/vtnreport/getEmailContentSrvl" method="post">
		</form>
		<form id="frmFormDocument" action="/vtnreport/getFormContentSrvl" method="post"> </form>
	</div>
</body>
</html>
