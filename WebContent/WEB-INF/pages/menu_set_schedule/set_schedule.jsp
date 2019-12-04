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
	<link href="${pageContext.request.contextPath}/resources/libraries/bootstrap-datepicker-1.6.4/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
	<script src="${pageContext.request.contextPath}/resources/libraries/bootstrap-datepicker-1.6.4/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/pages/main_menu/main_menu.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/pages/menu_set_schedule/set_schedule.js" type="text/javascript"></script>
</head>
<body>
		<div class="panel panel-vtn">
			<div class="panel-heading text-center">
				<b>Set Schedule Email</b>
			</div>			
		</div>
		<div class="container">
			<div class="panel-body">
				<div class="form-horizontal">
				<div class="row">
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="text-right">
								<b id="lblYear">Year</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3">
							<input class="form-control" id="txtYYYY" name="txtYYYY" readonly="readonly" />
						</div>
						<div class="col-xs-6 col-sm-3 control-label paymentDF">
							<p class="text-right">
								<b id="lblMonth">Month</b>
							</p>
						</div>
						<div class="col-xs-6 col-sm-3 paymentDF" >
							<input  class="form-control" id="txtMM" name="txtMM" readonly="readonly"/>
						</div>	
						
					</div>
					<div class="row">
						<div class="col-xs-6 col-sm-3 control-label">
							<p class="text-right">
								<b id="lblPrintDate">Date Time</b>
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
					
				<div class="row">
					<div class="col-xs-12 col-sm-3 text-right">
						<!-- <button type="button" id="btnView" class="btn btn-default" onclick="getDoctor()">View</button> -->
						<!-- <button type="button" id="btnSetEmailSchedule" class="btn btn-default" onclick="setScheduleEmail()">Set Schedule</button> -->
					<button type="button" id="btnSetEmailSchedule" class="btn btn-default text-left" onclick="checkrunning('c')">Check Schedule</button>
					</div>
					<div class="col-xs-12 col-sm-9 text-right">	
						
						<button type="button" id="btnSetEmailSchedule" class="btn btn-default " onclick="checkmodal('s')">Set Schedule</button>
						<button type="button" id="btnCancelEmailSchedule" class="btn btn-default " onclick="cancelScheduler()">Reset Schedule</button>
					</div>
				</div>
				
				<div class="modal fade" id="mymodal" style="display: none;">
				  <div class="modal-dialog">
				 
				 	<div class="modal-content">
				 	  <div class="modal-header">
				 	  	<button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title" id="modaltitle">Confirm change</h4>
				        </div>
				        <div class="modal-body">
				          <p id="text"></p>
				        </div>
				        <div class="modal-footer">
				          <button type="button" id="confirm" class="btn btn-default" data-dismiss="modal" onclick="setScheduleEmail()">Confirm</button>
				          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> 	  
				 	  </div>
				 	</div>
				 </div>
				</div>	
				
			<!-- <div class="modal fade" id="popupmodal">
				 <div class="modal-dialog modal-sm">
				 <div class="modal-content">
				  <div class="modal-header">
				 	  	<button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Information</h4>
				  </div>
				  <div class="modal-body">
				  	<button type="button" class="close" data-dismiss="modal">&times;</button>
				  	<p align="center" id="success"></p>
				  </div>
				  </div>
				 </div>
				</div> -->

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
			<!--  <div class="alert alert-success alert-dismissible fade out" id="alertsuccess" >
			    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			    <p id="success"></p>
			  </div> -->	
			
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
		
		<jsp:include page="../../../resources/template/modalMessage.jsp"></jsp:include>
		
</body>
</html>