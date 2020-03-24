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
							<option value="06">หนังสือรับรองรายได้ สำหรับประชุมไม่ระบุสถานที่ (EN)</option>
							<option value="07">หนังสือรับรองรายได้ สำหรับประชุมระบุสถานที่ (EN)</option>
						</select>
					</div>
					<div class="col-xs-6 col-sm-3 control-label">
						<p class="text-right">
							<b id="lblReport">Previous Period</b>
						</p>
					</div>
					<div class="col-xs-6 col-sm-3">
						<select id="datePeriod" class="form-control">
							<option value="3">3 Month</option>
							<option value="6">6 Month</option>
							<option value="12">12 Month</option>
						</select>
					</div>
					
				</div>
				<!-- <div class="row">
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="text-right">
								<b id="lblPrintDate">Revenue Date From</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3" id="">
							<div class="input-group">
								<input id="hidStartDate" name="hidStartDate" type="hidden" /> 
								<input type="text" id="txtStartDate" name="txtStartDate" class="form-control datePicker" placeholder="dd/mm/yyyy" /> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="text-right">
								<b id="lblPrintDate">Revenue Date To</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3" id="">
							<div class="input-group">
								<input id="hidEndDate" name="hidEndDate" type="hidden" /> 
								<input type="text" id="txtEndDate" name="txtEndDate" class="form-control datePicker" placeholder="dd/mm/yyyy" /> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
					</div> -->
					<div class="row">
					<div class="col-xs-6 col-sm-3 control-label">
						<p class="text-right">
							<b id="lblDoctorCode">Doctor Code</b>
						</p>
					</div>
					<div class="col-xs-6 col-sm-3">
					
						<input id="txtDoctorCode" name="txtDoctorCode" type="text" class="form-control" />
					</div>
					<div class="col-xs-6"></div>
					<div class="col-xs-6 col-sm-6">
						<input id="hidDoctorName" name="hidDoctorName" type="hidden" /> 
						<input id="txtDoctorName" name="txtDoctorName" type="text" class="form-control" readonly="readonly" />
					</div>
				</div>
				<div id="reason2" >	
				<div class="row" id="MeetingName">
					<div class="col-xs-6 col-sm-3 control-label">
						<p class="text-right">
							<b id="lblMeetingName">Meeting Name</b>
						</p>
					</div>
					<div class="col-xs-6 col-sm-9">
						<input id="txtMeetingName" name="txtMeetingName" type="text"class="form-control ui-autocomplete-input " placeholder="Example: ESHRE Annual Meeting">
					</div>
				</div>
				</div>
				<!-- End reason2 -->
				<div id="reason" >	
				<div class="row" id="location">
					<div class="col-xs-6 col-sm-3 control-label">
						<p class="text-right">
							<b id="lblReport">County</b>
						</p>
					</div>
					<div class="col-xs-6 col-sm-9">
						<input id="hidCounty" name="hidCounty" type="hidden" /> 
						<input id="txtCounty" name="txtCounty" type="text"class="form-control ui-autocomplete-input ">
					</div>
				</div>
				<div class="row" id="period">
					<div class="col-xs-6 col-sm-3 control-label">
						<p class="text-right">
							<b id="lblReport">Depart Date</b>
						</p>
					</div>
					<div class="col-xs-6 col-sm-3" id="">
							<div class="input-group">
								<input type="text" id="txtDepartDate" name="txtDepartDate" class="form-control datePicker" placeholder="dd/mm/yyyy" /> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
					</div>
					<div class="col-xs-6 col-sm-3 control-label">
						<p class="text-right">
							<b id="lblReport">Arrived Date</b>
						</p>
					</div>
					<div class="col-xs-6 col-sm-3" id="">
							<div class="input-group">
								<input type="text" id="txtArrivedDate" name="txtArrivedDate" class="form-control datePicker" placeholder="dd/mm/yyyy" /> 
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
					</div>
					
					
					<!-- <div class="col-xs-6 col-sm-9">
						<input id="txtMeetingDate" name="txtMeetingDate" type="text"class="form-control ui-autocomplete-input" placeholder="Example: 6-23 Agust, 2019">
					</div> -->
				</div>
			</div><!-- End reason -->
			
			 <div class="row" id="sent_mail">
					<div class="col-xs-6 col-sm-3">
						<p class="text-right">
							<b id="lblReport">E-mail</b>
						</p>
					</div>
					<div class="col-xs-6 col-sm-3 ">
						<input id="txtEmail" name="txtEmail" type="text" class="form-control">
					</div>
					<div class="col-xs-3 col-sm-3 ">
						<button type="button" id="btnSendSelfEmail" class="btn btn-default" onclick="SendSelfEmail()">Sent E-Mail</button>
					</div>
				</div>
				
			<div class="row">
					<div class="col-xs-6 col-sm-3 ">
						<p class="text-right">
							<b id="lblReport">Sign</b>
						</p>
					</div>
					<div class="col-xs-6 col-sm-6 ">
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="SS" id="DS" value="">
	 						<label class="form-check-label" for="inlineRadio1">Digital Sign </label>
 						
							<input class="form-check-input" type="radio" name="SS" id="DSS" value="">
	 						<label class="form-check-label" for="inlineRadio1">Digital Sign & Seal </label>
	 						
	 						<input class="form-check-input" type="radio" name="SS" id="SS" value="">
	 						<label class="form-check-label" for="inlineRadio1">Sign & Seal </label>
 						</div>
 						 
					</div>
				</div>
				<!-- <div class="row">
					<div class="col-xs-6 col-sm-3">
						<p class="text-right">
							<b id="lblReport">Company seal</b>
						</p>
					</div>
					<div class="col-xs-6 col-sm-3 ">
						<div class="form-check form-check-inline ">
							<input class="form-check-input" type="radio" name="TT" id="S" value="option1">
	 						<label class="form-check-label" for="inlineRadio1">Digital Seal</label>
 						
							<input class="form-check-input" type="radio" name="TT" id="RT" value="option1">
	 						<label class="form-check-label" for="inlineRadio1">Seal</label>
 						</div>
					</div>
				</div> -->
				
				<div id="btnView"  class="row ">
					<div class="col-xs-12 col-sm-3 text-left"></div>
					<div class="col-xs-12 col-sm-9 text-left">
						<button type="button" id="btnFormDocument" class="btn btn-default" onclick="setForm()">View</button>
					</div>
				</div>
				<div id="btnSendMail"  class="row">
					<div class="col-xs-12 col-sm-3 text-left"></div>
					<div class="col-xs-12 col-sm-4 text-left">
						<button type="button" id="btnPreviewForm" class="btn btn-default" onclick="preview()">PreView</button>
					</div>
					<div class="col-xs-12 col-sm-5 text-right">
						<button type="button" id="btnSetSendMail" class="btn btn-default" onclick="setSendMail()">Send E-Mail</button>
					</div>
				</div>
				
			</div><!-- Form-Horizontal -->
				
			<div class="modal fade" id="popupmodal">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h3 class="modal-title">Informations</h3>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						</div>
						<div class="modal-body">
							<p align="left" id="success"></p>
						</div>
					</div>
				</div>
			</div>
			
			
			</div>
			<hr>
			</div>
			
		<input type="hidden" id="hidUserCode" name="hidUserCode" value="<%= session.getAttribute("userid") %>">
		<input type="hidden" id="hidUserName" name="hidUserName" value="<%= session.getAttribute("name")%>">	
		<input type="hidden" id="hidRole" name="hidRole" value="<%= session.getAttribute("role") %>">
		<input type="hidden" id="hidHospitalCode" name="hidHospitalCode" value="<%= session.getAttribute("hospitalcode")%>">
			
		<form id="frmPaymentAll" action="/vtnreport/getPaymentContentAllSrvl" method="post"> </form>
		<form id="frmPayment" action="/vtnreport/getPaymentContentSrvl" method="post"> </form>
		<form id="frmTax" action="/vtnreport/getTaxContentSrvl" method="post"> </form>
		<form id="frmEmail" action="/vtnreport/getEmailContentSrvl" method="post"> </form>
		<form id="frmEmailSchedule" action="/vtnreport/setMailScheduleSrvl" method="post"> </form>
		<form id="frmFormDocument" action="/vtnreport/getFormContentSrvl" method="post"> </form>
		<form id="frmReport" name="frmReport" action="/vtnreport/GenerateReportSrvl" method="post" target="_blank">

				<input type="hidden" id="hidtype" name="hidtype" />
				<input type="hidden" id="hidDate" name="hidDate" />
				<!-- <input type="hidden" id="hidEndMM" name="hidEndMM" />
				<input type="hidden" id="hidStartYYYY" name="hidStartYYYY" />
				<input type="hidden" id="hidEndYYYY" name="hidEndYYYY" /> -->
				<input type="hidden" id="hidHospitalCode" name="hidHospitalCode" value="<%= session.getAttribute("hospitalcode")%>" />
				<input type="hidden" id="hidDoctorCode" name="hidDoctorCode" />
				<input type="hidden" id="hidReport" name="hidReport" />
				<input type="hidden" id="hidMeetingName" name="hidMeetingName" />
				<input type="hidden" id="hidMeetingDate" name="hidMeetingDate" />
				<input type="hidden" id="hidtxtCounty" name="hidtxtCounty" />
				<input type="hidden" id="hidPreview" name="hidPreview" />
				<input id="hidDepartDate" name="hidDepartDate" type="hidden" /> 
				<input id="hidArrivedDate" name="hidArrivedDate" type="hidden" /> 
				<input id="hidEmail" name="hidEmail" type="hidden" /> 
				<input id="subj_mail" name="subj_mail" type="hidden" /> 
				<input id="hid_sign" name="hid_sign" type="hidden" value="SS"/> 

			</form>
			
		<jsp:include page="../../../resources/template/modalMessage.jsp"></jsp:include>
		
</body>
</html>