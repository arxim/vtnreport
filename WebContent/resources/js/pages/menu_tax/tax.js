$(document).ready(function() {
	getYYYY();
	checkRole();   
	$("#btn-tax").addClass("active").css("background-color","#87b2e0");
	
	$('#txtPrintDate').datepicker({
		format : "dd/mm/yyyy",
		autoclose : true,
		todayHighlight : true
	});
	
	// Set Current Date
	$('#txtPrintDate').datepicker('setDate', 'now');
	
	var hospitalCode = $('#hidhospitalCode').val();
	//Autocomplete
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
	            }
	        });
	    },
	 select: function(event, ui) {
	   event.preventDefault();
	   $("#txtDoctorName").val(ui.item.value); // Code : Description
	   $("#txtDoctorCode").val(ui.item.id); // Code
	 }
	});
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
				getLastBatchTax406OnClose();
			}
	 }); 
}

// View Report
function getReport(){
	
	var yyyy = $('#dwlYear').val();
	var mm = $('#dwlMonth').val();
	var hospitalCode = $('#hidHospitalCode').val();
	var	report = "tax";
	var doctorCode = $('#txtDoctorCode').val().trim();
	var tempPrintDate = $("#txtPrintDate").val();
	var printDate = tempPrintDate.substring(6,10)+tempPrintDate.substring(3,5)+tempPrintDate.substring(0,2);
	var term = $("#dwlTerm").val();
	
	$("#hidReport").val(report);
	$("#hidMM").val(mm);
	$("#hidYYYY").val(yyyy);
	$("#hidDoctorCode").val(doctorCode);
	$("#hidPrintDate").val(printDate);
	$("#hidTerm").val(term);

	$("#frmReport").submit();

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
function getManual(){
//	window.location.href = 'SCAP-VTN-DFS_PRESENT_V1.0.pdf',"_blank";
	window.open('SCAP-VTN-DFS_PRESENT_V1.0.pdf',"_blank");
}

//Check Role DR Or Account
function checkRole(){
	
	var role = $("#hidRole").val();
	var userCode = $("#hidUserCode").val();
	var userName = $("#hidUserName").val();
	
	if(role == "5"){
		$("#txtDoctorCode").prop('disabled', true);
		$("#txtDoctorCode").val(userCode);
		$("#txtDoctorName").val(userName)
	}
}


function getLastBatchTax406OnClose(){
	$.ajax({
		type : "POST",
		url : "/vtnreport/GetBatch",
		dataType: "json",
		data: {
			method : "03",
        },
		success : function(data) {
//			$('#dwlMonth').val(data[0]["mm"]).attr("selected", "selected");
			$('#dwlYear').val(data[0]["yyyy"]).attr("selected", "selected");
		}
 }); 
}