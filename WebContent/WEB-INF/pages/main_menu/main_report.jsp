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
	<script src="${pageContext.request.contextPath}/resources/js/pages/main_menu/main_report.js" type="text/javascript"></script>
	
</head>
<body>
	<div id="page-content-wrapper">
		<div class="container-fluid">
			<div class="panel panel-vtn">
				<div class="panel-heading text-center">
					<b>Doctor Report</b>
				</div>			
			</div>
			<div class="container">
				<div class="panel-body">
					<div class="form-horizontal">
						<div class="row">
							<div class="col-xs-6 col-sm-3 control-label">
								<p class="text-right">
									<b id="lblFrmDoctorCode">From Doctor Code</b>
								</p>
							</div>
							<div class="col-xs-6 col-sm-3">
								<input id="txtFrmDoctorCode" name="txtFrmDoctorCode" type="text" class="form-control input-sm" />
							</div>
							<div class="col-xs-6 col-sm-3 control-label">
								<p class="text-right">
									<b id="lblToDoctorCode">To Doctor Code</b>
								</p>
							</div>
							<div class="col-xs-6 col-sm-3">
								<input id="txtToDoctorCode" name="txtToDoctorCode" type="text" class="form-control input-sm" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-6 col-sm-3 control-label">
								<p class="text-right">
									<b id="lblDoctorCategory">Doctor Category</b>
								</p>
							</div>
							<div class="col-xs-6 col-sm-3">
								<input id="txtDoctorCategory" name="txtDoctorCategory" type="text" class="form-control input-sm" />
							</div>
							<div class="col-xs-6 col-sm-3 control-label">
								<p class="text-right">
									<b id="lblDoctorDepartment">Doctor Department</b>
								</p>
							</div>
							<div class="col-xs-6 col-sm-3">
								<input id="txtDoctorDepartment" name="txtDoctorDepartment" type="text" class="form-control input-sm" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-6 col-sm-3 control-label">
								<p class="text-right">
									<b id="lblExpenseCode">Expense Code</b>
								</p>
							</div>
							<div class="col-xs-6 col-sm-3">
								<input id="txtExpenseCode" name="txtExpenseCode" type="text" class="form-control input-sm" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-6 col-sm-3 control-label">
								<p class="text-right">
									<b id="lblExpenseSign">Expense Sign</b>
								</p>
							</div>
							<div class="col-xs-6 col-sm-3">
								<input id="txtExpenseSign" name="txtExpenseSign" type="text" class="form-control input-sm" />
							</div>
							<div class="col-xs-6 col-sm-3 control-label">
								<p class="text-right">
									<b id="lblExpenseAccountCode">Expense Account Code</b>
								</p>
							</div>
							<div class="col-xs-6 col-sm-3">
								<input id="txtExpenseAccountCode" name="txtExpenseAccountCode" type="text" class="form-control input-sm" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-6 col-sm-3 control-label">
								<p class="text-right">
									<b id="lblMonth">Month</b>
								</p>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group-xs-6 form-group-sm-3 ">
								     <select class="form-control">
								    	  <option value="">----select month-----</option>
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
							</div>
							<div class="col-xs-6 col-sm-3 control-label">
									<p class="text-right">
										<b id="lblYear">Year</b>
									</p>
							</div>
							<div class="col-xs-6 col-sm-3">
								<input id="txtYear" name="txtYear" type="text" class="form-control input-sm" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-6 col-sm-6">
								<button type="button" class="btn btn-default" onclick="removeEmt()">reset</button>
							</div>
							<div class="col-xs-6 col-sm-6 text-right">
								<button type="button" class="btn btn-default" onclick="getYYYY()">view</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="containner" id="tbReport"></div>
		</div>
	</div>
</body>
</html>
