$(document).ready(function() {
	checkRole();
	$("#reason").hide();
	$("#reason2").hide();
	
	$('#dwlReport').change(function() {
		var dw_val =$('#'+$(this).attr("id")).val();
		
		if(dw_val=="02"||dw_val=="01"||dw_val=="03"){
			$("#reason").hide();
			$("#reason2").hide();
		}else if(dw_val=="04"){
			$("#reason").show();
			$("#reason2").hide();
		}else{
			$("#reason").show();
			$("#reason2").show();
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
	            	type:"DOCTOR",
	            	doctorSearch : request.term.replace(" ", "%"),
					hospitalCode : $('#hidHospitalCode').val(),
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
	
	$("#txtCounty").autocomplete({
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
	            	type:"COUNTY",
	            	countySearch : request.term.replace(" ", "%"),
					hospitalCode : $('#hidHospitalCode').val()
	            },
	            success: function(data) {
	                response(data);
	                $("#txtCounty").val("");
	                $("#hidCounty").val("");
	            }
	        });
	    },
	 select: function(event, ui) {
	   event.preventDefault();
	   $("#txtCounty").val(ui.item.value); // Code
	   $("#hidCounty").val(ui.item.id);
	 },
     change: function( event, ui ) {
    	 
    	/* if($("#hidDoctor").val() == ""){
    		 
 			$("#txtCounty").val("");
 			$("#hidCounty").val("");
 			
 		}*/
     }
	});
	
	$("#btnSendMail").hide();
	 $('input:radio').change(function(){
	       var id= $(this).attr("id"); 
	        if(id=="RS"|| id=="RT"){
	        	$("#btnSendMail").show();
	        	$("#btnView").hide();
	        }else{
	        	if($("#RS").is(':checked') || $("#RT").is(':checked') ){
	        		$("#btnSendMail").show();
		        	$("#btnView").hide();
	        	}else{
	        		$("#btnSendMail").hide();
		        	$("#btnView").show();
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

//Check Role DR Or Account
function checkRole(){
	
	var role = $("#hidRole").val();
	var userCode = $("#hidUserCode").val();
	var userName = $("#hidUserName").val();
	
	if(role == "5"){
		$("#txtDoctorCode").prop('readonly', true);
		$("#txtDoctorCode").val(userCode);
		$("#txtDoctorName").val(userName)
}
}
