$(document).ready(function() {
	getYYYY();
});

function getYYYY(){
	 $('#dwlYear').empty(); 
	  $.ajax({
			type : "POST",
			url : "/vtnreport/DropDownListGeneratorSrvl",
			data :{
				
				url : "getYYYY"
			}, 
			success : function(data) {
				$("#dwlYear").append(data);
			}
	 }); 
}
function getTax(){
	$('#frmTax').submit();
}

function getPayment(){
	$('#frmPayment').submit();
}
function getEmail(){
	$('#frmEmail').submit();
}

function getReport(){
	
	var yyyy = $('#dwlYear').val();
	var mm = $('#dwlMonth').val();
	var hospitalCode = $('#hidHospitalCode').val();
	var	report = $('#dwlReport').val();
	var doctorCode = $('#txtDoctorCode').val();
	
	$("#hidReport").val(report);
	$("#hidMM").val(mm);
	$("#hidYYYY").val(yyyy);
	$("#hidDoctorCode").val(doctorCode);
	
	$("#frmReport").submit();
	

}