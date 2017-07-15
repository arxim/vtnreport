$(document).ready(function() {
	getYYYY();
	checkRole();
 	$("#btn-sum-payment").addClass("active").css("background-color","#87b2e0");
	
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
				getLastBatchOnClose();
			}
	 }); 
}
function getPaymnetAll(){
	$('#frmPaymentAll').submit();
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

function getReport(){
	
	var yyyy = $('#dwlYear').val();
	var mm = $('#dwlMonth').val();
	var hospitalCode = $('#hidHospitalCode').val();
	var	report = $('#dwlReport').val();
	var doctorCode = $('#txtDoctorCode').val().trim();
	
	$("#hidReport").val("05");
	$("#hidMM").val(mm);
	$("#hidYYYY").val(yyyy);
	$("#hidDoctorCode").val(doctorCode);

	$("#frmReport").submit();
	

}

function getLastBatchOnClose(){
	$.ajax({
		type : "POST",
		url : "/vtnreport/GetBatch",
		dataType: "json",
		data: {
			method : "02",
        },
		success : function(data) {
			$('#dwlMonth').val(data[0]["mm"]).attr("selected", "selected");
			$('#dwlYear').val(data[0]["yyyy"]).attr("selected", "selected");
		}
 }); 
}

// Check Role DR Or Account
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