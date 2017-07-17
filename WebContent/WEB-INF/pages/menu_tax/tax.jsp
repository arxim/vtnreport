<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	 
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>Main Report</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="../../../resources/template/script-header.jsp"></jsp:include>
	<jsp:include page="../../../resources/template/navbar-header.jsp"></jsp:include>
	<script src="${pageContext.request.contextPath}/resources/js/pages/main_menu/main_menu.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/pages/menu_tax/tax.js" type="text/javascript"></script>
</head>
<body>		
	<div class="panel panel-vtn">
			<div class="panel-heading text-center">
				<b>Report Tax</b>
			</div>			
		</div>
		<div class="container">
			<div class="panel-body">
				<div class="form-horizontal">
					<div class="row">
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="text-right">
								<b id="lblDoctorCode">Doctor Code</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3">
							<input id="hidDoctor" name="hidDoctor" type="hidden" />
							<input id="txtDoctorCode" name="txtDoctorCode" type="text" class="form-control" />
						</div>
						<div class="col-xs-6">
							
						</div>
						<div class="col-xs-6 col-sm-6">
							<input id="txtDoctorName" name="txtDoctorName" type="text" class="form-control" disabled="disabled"/>
						</div>
					</div>
					<div class="row">
						<!-- <div class="col-xs-6 col-sm-3 control-label">
							<p class="text-right">
								<b id="lblMonth">Month</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3">
							<div class="form-group-xs-6 form-group-sm-3 ">
							     <select id="dwlMonth" class="form-control">
							    	  <option value="01">January</option>
									  <option value="02">February</option>
									  <option value="03">March</option>
									  <option value="04">April</option>
									  <option value="05">May</option>
									  <option value="06">June</option>
									  <option value="07">July</option>
									  <option value="08">August</option>
									  <option value="09">September</option>
									  <option value="10">October</option>
									  <option value="11">November</option>
									  <option value="12">December</option>
							     </select>
							</div>			
						</div> -->
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="text-right">
								<b id="lblTerm">Term</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3 coltrol-label" id="divValueTerm">
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
							<select class="form-control" id="dwlYear">
								
							</select>
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
					<div class="form-group"></div>
					<div class="row">
						<div class="col-xs-12 col-sm-12 text-right">
							<button type="button" class="btn btn-default" id="btnView" onclick="getReport()">view</button>
						</div>
					</div>
					<div class="row">
						<br/>
						<br/>
						<div class="col-xs-6 col-sm-3 control-label text-danger">
							<p class="text-right">
								<b id="lblNote">หมายเหตุ</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-9 control-label text-danger">
							<p class="text-left">
								<b id="NoteContent">ยังไม่ระบุ</b>
							</p>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="hidUserCode" name="hidUserCode" value="<%= session.getAttribute("userid") %>">
		    <input type="hidden" id="hidRole" name="hidRole" value="<%= session.getAttribute("role") %>">
		    <input type="hidden" id="hidHospitalCode" name="hidHospitalCode" value="<%= session.getAttribute("hospitalcode")%>">
		    <input type="hidden" id="hidUserName" name="hidUserName" value="<%= session.getAttribute("name")%>">
			
			<form id="frmPaymentAll" action="/vtnreport/getPaymentContentAllSrvl" method="post"> </form>
			<form id="frmPayment" action="/vtnreport/getPaymentContentSrvl" method="post"> </form>
			<form id="frmTax" action="/vtnreport/getTaxContentSrvl" method="post"> </form>
			<form id="frmEmail" action="/vtnreport/getEmailContentSrvl" method="post"> </form>
			
			<form id="frmReport" name="frmReport" action="/vtnreport/DoctorReportSrv" method="post" target="_blank">
				<input type="hidden" id="hidMM" name="hidMM" />
				<input type="hidden" id="hidYYYY" name="hidYYYY" />
				<input type="hidden" id="hidHospitalCode" name="hidHospitalCode" value="<%= session.getAttribute("hospitalcode")%>" />
				<input type="hidden" id="hidDoctorCode" name="hidDoctorCode" />
				<input type="hidden" id="hidReport" name="hidReport" />
				<input type="hidden" id="hidPrintDate" name="hidPrintDate" />
				<input type="hidden" id="hidTerm" name="hidTerm" />
			</form>
			
		</div>
	<body>
</html>