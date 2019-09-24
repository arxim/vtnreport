$(document).ready(function() {
	getBatch();
	
	$('#txtPrintDate').datetimepicker({
		    format: "dd-mm-yyyy hh:ii",
		    weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
	        showMeridian: 1
	});
	//$('#txtPrintDate').datepicker('setDate', 'now');
	
	var hospitalCode = $('#hidhospitalCode').val();
	
	
	
});

function getPaymnetAll(){
	$('#frmPaymentAll').submit();
}
function getPaymnet(){
	$('#frmPayment').submit();
}
function getTax(){
	$('#frmTax').submit();
}
function getEmail(){
	$('#frmEmail').submit();
}

function getMailSchedule(){
	$('#frmEmailSchedule').submit();
}
function getForm(){
	$('#frmFormDocument').submit();
}
var current_row = 0;


function waitIcon(current_row_wait){
	var table = $('#tblDoctor').DataTable();
	table.cell(current_row_wait, 2 ).data( '<div class="text-center"><img src="resources/pics/process.gif" class="img-responsive" style="display:block; margin:auto;" alt="icon process" width="20" height="20"></div>' );
	table.draw();
}


// Send Email
function setScheduleEmail(){
	var total_table = $('#tblDoctor').dataTable();
	var table_getDoctor = $('#tblDoctor').DataTable();
	
	var hospitalCode = $('#hidHospitalCode').val();
	var	report = "04";
	var term = $('#dwlTerm').val();
	var tempPrintDate = $("#txtPrintDate").val();
	var dateTime = tempPrintDate.split(' ');
	var hh=dateTime[1].substring(0,2);
	var mm=dateTime[1].substring(3,5);
	var date=dateTime[0].substring(0,2);
	var month=dateTime[0].substring(3,5);
	var cron_schedule ="0 "+mm+" "+hh+" "+date+" "+month+" ?";
	var yyyy = $('#txtYYYY').val();
	var mm = $('#txtMM').val();
	
	alert(cron_schedule);
	$("#dwlReport").prop( "readonly", true );
	$("#txtPrintDate").prop( "readonly", true );
	$("#dwlTerm").prop( "readonly", true );
	$("#btnView").prop( "disabled", true );
	$("#btnSendEmail").prop( "disabled", true );
	
	//waitIcon(current_row);
	//if(current_row < total_table.fnGetData().length){
	$.ajax({
		url : '/vtnreport/SendEmailNewSrvl',
		type : 'post',
		data : {
			yyyy : yyyy ,
			mm : mm,
			hospitalCode : hospitalCode ,
			report : report,
			term : term,
			printDate : cron_schedule
		},
		success : function(response) {

		}
	});
}

function getBatch(){
	$.ajax({
		type : "POST",
		url : "/vtnreport/GetBatch",
		dataType: "json",
		data: {
			method : "02",
        },
		success : function(data) {
			$("#txtMM").val(data[0]["mm"]);
			$("#txtYYYY").val(data[0]["yyyy"]);
		}
 }); 
}

