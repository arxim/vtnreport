<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	 
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>Main Report</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="../../../resources/template/script-header.jsp"></jsp:include>
	<jsp:include page="../../../resources/template/navbar-header.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/resources/js/pages/main_menu/main_menu.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/pages/menu_send_email/send_mail.js" type="text/javascript"></script>
</head>
<body>
		<div class="panel panel-vtn">
			<div class="panel-heading text-center">
				<b>Send Email</b>
			</div>			
		</div>
		<div class="container">
			<div class="panel-body">
				<div class="form-horizontal">
					<div class="row">
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="text-right">
								<b id="lblReport">Report</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3 coltrol-label">
							<select class="form-control" id="dwlReport">
								<option value="03">Payment Summary</option>
<!-- 								<option value="02">Payment DF Split File</option> -->
								<option value="01">Tax Certificate</option>
							</select>
						</div>
					</div>
					<div class="row">
						<!-- Payment DF -->
						<div class="col-xs-6 col-sm-3 control-label paymentDF">
							<p class="text-right">
								<b id="lblMonth">Month</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3 paymentDF" >
							<input  class="form-control" id="txtMM" name="txtMM" readonly="readonly"/>
						</div>
						<!-- End Of Payment DF -->
						<div class="col-xs-6 col-sm-3 control-label taxt406" id="divLabelTerm">
							<p class="text-right">
								<b id="lblTerm">Term</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3 coltrol-label taxt406" id="divValueTerm">
							<select class="form-control" id="dwlTerm">
								<option value="01">First Term</option>
								<option value="06">Second Term</option>
								<option value="12">Yearly</option>
							</select>
						</div>
									
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="text-right">
								<b id="lblYear">Year</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3">
							<input class="form-control" id="txtYYYY" name="txtYYYY" readonly="readonly" />
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="text-right">
								<b id="lblPrintDate">Print Date</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3" id="divPrintDate">
							<div class="input-group">
								<input type="text" id="txtPrintDate" name="txtPrintDate" class="form-control datePicker" placeholder="dd/mm/yyyy" /> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
					</div>
					</div>
					<div class="form-group"></div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 text-right">
						<button type="button" id="btnView" class="btn btn-default" onclick="getDoctor()">View</button>
						<button type="button" id="btnSendEmail" class="btn btn-default" onclick="sendEmail()"disabled="disabled">Send Email</button>
					</div>
				</div>
			
			</div>
			<hr>
			<div class="form-group text-left">
				<h4>The table has <span id="record-mail-count">0</span> / <span id="all-mail-count">0</span> records</h4>
			</div>
			<div class="row">
					<div class="col-sm-12 col-xs-12">
						<div class="table-responsive dt-responsive ">
							<table id="tblDoctor"
								class="table table-striped table-responsive table-bordered dt-responsive nowrap table-hover"
								style="width: 100%">
								<thead>
									<tr>
										<th>Doctor Code</th>
										<th>Doctor Name</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody id="tblDoctor"></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			
		<input type="hidden" id="hidUserCode" name="hidUserCode" value="<%= session.getAttribute("userid") %>">
		<input type="hidden" id="hidRole" name="hidRole" value="<%= session.getAttribute("role") %>">
		<input type="hidden" id="hidHospitalCode" name="hidHospitalCode" value="<%= session.getAttribute("hospitalcode")%>">
		<form id="frmPaymentAll" action="/vtnreport/getPaymentContentAllSrvl" method="post"> </form>
		<form id="frmPayment" action="/vtnreport/getPaymentContentSrvl" method="post"> </form>
		<form id="frmTax" action="/vtnreport/getTaxContentSrvl" method="post"> </form>
		<form id="frmEmail" action="/vtnreport/getEmailContentSrvl" method="post"> </form>
		<form id="frmEmailSchedule" action="/vtnreport/setMailScheduleSrvl" method="post"> </form>
		
		<jsp:include page="../../../resources/template/modalMessage.jsp"></jsp:include>
		
</body>
</html>