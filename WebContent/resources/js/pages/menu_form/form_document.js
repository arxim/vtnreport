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
	
	var hospitalCode = $('#hidhospitalCode').val();
	$("#txtDoctorCode").autocomplete({
		autoFocus: true,
	    cacheLength: 1,
	    scroll: true,
	    highlight: false,
	    source: function(request, response) {
	        $.ajax({
	         type: "POST",
	            url: '/vtnreport/AutoCompleteSrvl',
	            dataType: "json",
	            data: {
	            	doctorSearch : request.term.replace(" ", "%"),
					hospitalCode : hospitalCode,
	            },
	            success: function(data) {
	                response(data);
	                $("#txtDoctorName").val("");
	                $("#hidDoctor").val("");
	            }
	        });
	    },
	 select: function(event, ui) {
	   event.preventDefault();
	   $("#txtDoctorName").val(ui.item.value); // Code : Description
	   $("#txtDoctorCode").val(ui.item.id); // Code
	   $("#hidDoctor").val(ui.item.id);
	 },
     change: function( event, ui ) {
    	 
    	 if($("#hidDoctor").val() == ""){
    		 
 			$("#txtDoctorCode").val("");
 			$("#hidDoctor").val("");
 			$("#txtDoctorName").val("");
 			
 		}
     }
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

