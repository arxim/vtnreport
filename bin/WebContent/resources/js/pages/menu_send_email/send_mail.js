$(document).ready(function() {
	getYYYY();
	
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
			}
	 }); 
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

function sendEmail(){
	var yyyy =  $('#dwlYear').val();
	var mm = $('#dwlMonth').val()
<<<<<<< HEAD
<<<<<<< HEAD
=======
	var hospitalCode = "VTN01";
	var doctorCode = "70001"
>>>>>>> stash
=======
	var hospitalCode = "VTN01";
	var doctorCode = "70001"
>>>>>>> branch 'master' of https://github.com/arxim/vtnreport.git
	var hospitalCode = $('#hidHospitalCode').val();
	var doctorCode = $('#txtDoctorCode').val();
	
		$.ajax({
			type : "POST",
			url : "/vtnreport/SentEmailSrv",
			data :{
				yyyy : yyyy,
				mm : mm,
				hospitalCode : hospitalCode,
				doctorCode : doctorCode
			}, 
			success : function(data) {
				$("#dwlYear").append(data);
			}
	 });
}