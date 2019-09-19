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
	<script src="${pageContext.request.contextPath}/resources/js/pages/menu_form/form_document.js" type="text/javascript"></script>
</head>
<body>
		<div class="panel panel-vtn">
			<div class="panel-heading text-center">
				<b>Form Document</b>
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
					<div class="col-xs-6 col-sm-3">
						<select id="dwlReport" class="form-control">
							<option value="01">หนังสือรับรองรายได้รายเดือน</option>
							<option value="02">หนังสือรับรองรายได้เฉลี่ย (TH)</option>
							<option value="03">หนังสือรับรองรายได้เฉลี่ย (EN)</option>
							<option value="04">หนังสือรับรองรายได้ สำหรับขอวีซ่า(EN)</option>
							<option value="06">หนังสือรับรองรายได้ สำหรับประชุม (1)(EN)</option>
							<option value="07">หนังสือรับรองรายได้ สำหรับประชุม (2)(EN)</option>
						</select>
					</div>
					<!-- <div class="col-xs-6 col-sm-6 form-check form-check-inline">
					  <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1">
					  <label class="form-check-label" for="inlineCheckbox1">Signature & Rubber Stamp</label>
					  <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="option1">
					  <label class="form-check-label" for="inlineCheckbox1">Digital Signature</label>
					</div> -->
				</div>
				<div class="row">
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="text-right">
								<b id="lblPrintDate">Payment Date From</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3" id="">
							<div class="input-group">
								<input type="text" id="txtStartDate" name="txtStartDate" class="form-control datePicker" placeholder="dd/mm/yyyy" /> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="text-right">
								<b id="lblPrintDate">Payment Date To</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3" id="">
							<div class="input-group">
								<input type="text" id="txtEndDate" name="txtEndDate" class="form-control datePicker" placeholder="dd/mm/yyyy" /> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
					</div>
				<div id="reason" >	
				<div class="row" id="location">
					<div class="col-xs-6 col-sm-3 control-label">
						<p class="text-right">
							<b id="lblReport">Location</b>
						</p>
					</div>
					<div class="col-xs-6 col-sm-9">
						<input id="txtLocation" name="txtLocation" type="text"class="form-control ui-autocomplete-input " placeholder="Example: United States of America">
					</div>
				</div>
				<div class="row" id="period">
					<div class="col-xs-6 col-sm-3 control-label">
						<p class="text-right">
							<b id="lblReport">Period Of</b>
						</p>
					</div>
					<div class="col-xs-6 col-sm-9">
						<input id="txtLocation" name="txtLocation" type="text"class="form-control ui-autocomplete-input" placeholder="Example: 6-23 Agust, 2019">
					</div>
				</div>
			</div><!-- End reason -->
			<!-- 	<div class="row">
					<div class="col-xs-6 col-sm-3 control-label">
						<p class="text-right">
							<b id="lblReport"></b>
						</p>
					</div>
					<div class="col-xs-6 col-sm-6 form-check ">
						<input class="form-check-input" type="checkbox"
							id="inlineCheckbox1" value="option1"> <label
							class="form-check-label" for="inlineCheckbox1">Signature
							& Rubber Stamp</label> <input class="form-check-input" type="checkbox"
							id="inlineCheckbox1" value="option1"> <label
							class="form-check-label" for="inlineCheckbox1">Digital
							Signature</label>
					</div>

				</div> -->
				<div class="row">
					<div class="col-xs-6 col-sm-3 control-label">
						<p class="text-right">
							<b id="lblReport"></b>
						</p>
					</div>
					<div class="col-xs-6 col-sm-3 ">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="ST">
							<label class="form-check-label" for="gridCheck1"> Signature & Rubber Stamp</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="DS">
							<label class="form-check-label" for="gridCheck1"> Digital Signature </label>
						</div>
					</div>
				</div>


			</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12 text-right">
						<!-- <button type="button" id="btnView" class="btn btn-default" onclick="getDoctor()">View</button> -->
						<button type="button" id="btnFormDocument" class="btn btn-default" onclick="getForm()">View</button>
					</div>
				</div>
			
			</div>
			<hr>
			</div>
			
		<input type="hidden" id="hidUserCode" name="hidUserCode" value="<%= session.getAttribute("userid") %>">
		<input type="hidden" id="hidRole" name="hidRole" value="<%= session.getAttribute("role") %>">
		<input type="hidden" id="hidHospitalCode" name="hidHospitalCode" value="<%= session.getAttribute("hospitalcode")%>">
		<form id="frmPaymentAll" action="/vtnreport/getPaymentContentAllSrvl" method="post"> </form>
		<form id="frmPayment" action="/vtnreport/getPaymentContentSrvl" method="post"> </form>
		<form id="frmTax" action="/vtnreport/getTaxContentSrvl" method="post"> </form>
		<form id="frmEmailSchedule" action="/vtnreport/setMailScheduleSrvl" method="post"> </form>
		<form id="frmFormDocument" action="/vtnreport/getFormContentSrvl" method="post"> </form>
		<jsp:include page="../../../resources/template/modalMessage.jsp"></jsp:include>
		
</body>
</html>