<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../../../resources/template/script-header.jsp"></jsp:include>
<jsp:include page="../../../resources/template/navbar-header.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/js/pages/report/doctor_report.js" type="text/javascript"></script>
<title>Doctor Report</title>
</head>
<body>
	<div id="page-content-wrapper">
		<div class="container-fluid">
			<div class="panel panel-vtn">
				<div class="panel-heading text-center">
					<b>Doctor Report</b>
				</div>
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
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>