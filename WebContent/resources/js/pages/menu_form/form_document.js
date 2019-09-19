$(document).ready(function() {
	$("#reason").hide();
	$('#dwlReport').change(function() {
		var dw_val =$('#'+$(this).attr("id")).val();
		
		if(dw_val=="02"||dw_val=="01"||dw_val=="03"){
			$("#reason").hide();
		}else{
			$("#reason").show();
		}
	});
	
	$('#txtStartDate').datepicker({
		format : "dd/mm/yyyy",
		autoclose : true,
		todayHighlight : true
	});
	$('#txtEndDate').datepicker({
		format : "dd/mm/yyyy",
		autoclose : true,
		todayHighlight : true
	});
});

function getTax(){
	$('#frmTax').submit();
}

function getPayment(){
	$('#frmPayment').submit();
}
function getEmail(){
	$('#frmEmail').submit();
}
function getManual(){
	window.open('SCAP-VTN-DFS_PRESENT_V1.0.pdf',"_blank");
}

function getMailSchedule(){
	$('#frmEmailSchedule').submit();
}

function getForm(){
	$('#frmFormDocument').submit();
}

