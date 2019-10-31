$(document).ready(function() {
	checkRole();
	$("#reason").hide();
	$("#reason2").hide();
	
	$('#dwlReport').change(function() {
		var dw_val =$('#'+$(this).attr("id")).val();
		getRunningNum = 0;
		if(dw_val=="02"||dw_val=="01"||dw_val=="03"){
			$("#reason").hide();
			$("#reason2").hide();
		}else if(dw_val=="04"||dw_val=="06"){
			$("#reason").show();
			$("#reason2").hide();
		}else{
			$("#reason").show();
			$("#reason2").show();
		}
	});
	
	$('#txtDepartDate').datepicker({
		format : "dd/MM/yyyy",
		autoclose : true,
		todayHighlight : true
	});
	$('#txtArrivedDate').datepicker({
		format : "dd/MM/yyyy",
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
	            }
	        });
	    },
	 select: function(event, ui) {
	   event.preventDefault();
	   $("#txtCounty").val(ui.item.value); // Code
	   $("#hidCounty").val(ui.item.id);
	 },
     change: function( event, ui ) {
    	 if($("#hidCounty").val() == ""){
 			$("#txtCounty").val("");
 			$("#hidCounty").val("");
 		}
     }
	});
	
	$("#btnSendMail").hide();
	$("#sent_mail").hide();
	
	$('input:radio').change(function(){
			getRunningNum = 0;
	       var id= $(this).attr("id"); 
	        if(id=="DS"){
	        	$("#btnSendMail").hide();
	        	$("#btnView").show();
	        	$("#sent_mail").show();
	        }else if(id=="DSS"){
	        	$("#btnSendMail").hide();
	        	$("#btnView").show();
	        	$("#sent_mail").show();
	        }else{
	        	$("#btnSendMail").show();
	        	$("#sent_mail").hide();
	        	$("#btnView").hide();
	        }	
	 });   
	
});

var getRunningNum = 0;
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

function setForm(){
	 StartForm("1","false");
//	var reportCode = $('#dwlReport').val();
//	var startDate = $('#txtStartDate').val();
//	var startMM = startDate.substring(3,5);
//	var startYYYY = startDate.substring(6,10);
//	var endDate = $('#txtEndDate').val();
//	var endMM = endDate.substring(3,5);
//	var endYYYY = endDate.substring(6,10);
//	var doctorCode = $('#txtDoctorCode').val();
//	var doctorName = $('#txtDoctorName').val();
//	var meetingName = $('#txtMeetingName').val();
//	var location = $('#txtCounty').val();
//	var meetingDate = $('#txtMeetingDate').val();
//	var type = "1";
//	
////	alert(meetingDate+location);
//		
//	$("#hidtype").val(type);
//	$("#hidReport").val(reportCode);
//	$("#hidStartMM").val(startMM);
//	$("#hidStartYYYY").val(startYYYY);
//	$("#hidEndMM").val(endMM);
//	$("#hidEndYYYY").val(endYYYY);
//	$("#hidDoctorCode").val(doctorCode);
//	$("#hidMeetingName").val(meetingName);
//	$("#hidMeetingDate").val(meetingDate);
//	$("#hidtxtCounty").val(location);
//
//
//	$("#frmReport").submit();
//	alert("re:"+reportCode+"stDate:"+startDate+"stM:"+startMM+"stY:"+startYYYY+"endM:"+endMM+"endY:"+endYYYY);
	
	
//	$.ajax({
//		url : '/vtnreport/GenerateReportSrvl',
//		type : 'post',
//		data : {
//			startYYYY : startYYYY ,
//			startMM : startMM ,
//			endYYYY : endYYYY ,
//			endMM : endMM ,
//			doctorCode : doctorCode ,
//			reportCode : reportCode ,
//			meetingName : meetingName ,
//			location : location ,
//			meetingDate : meetingDate ,
//			type : type
//		},
//		success : function(response) {
//
//		}
//	});
	
	
}

function setSendMail(){
	StartForm("2","false");
//	var reportCode = $('#dwlReport').val();
//	var startDate = $('#txtStartDate').val();
//	var startMM = startDate.substring(3,5);
//	var startYYYY = startDate.substring(6,10);
//	var endDate = $('#txtEndDate').val();
//	var endMM = endDate.substring(3,5);
//	var endYYYY = endDate.substring(6,10);
//	var doctorCode = $('#txtDoctorCode').val();
//	var doctorName = $('#txtDoctorName').val();
//	var meetingName = $('#txtMeetingName').val();
//	var location = $('#txtCounty').val();
//	var meetingDate = $('#txtMeetingDate').val();
//	var type = "2";
//	
////	alert("re:"+reportCode+"stDate:"+startDate+"stM:"+startMM+"stY:"+startYYYY);
//	$("#hidtype").val(type);
//	$("#hidReport").val(reportCode);
//	$("#hidStartMM").val(startMM);
//	$("#hidStartYYYY").val(startYYYY);
//	$("#hidEndMM").val(endMM);
//	$("#hidEndYYYY").val(endYYYY);
//	$("#hidDoctorCode").val(doctorCode);
//	$("#hidMeetingName").val(meetingName);
//	$("#hidMeetingDate").val(meetingDate);
//	$("#hidtxtCounty").val(location);
//
//
//	$("#frmReport").submit();
//	
//	$.ajax({
//		url : '/vtnreport/GenerateReportSrvl',
//		type : 'post',
//		data : {
//			startYYYY : startYYYY ,
//			startMM : startMM,
//			endYYYY : endYYYY,
//			endMM : endMM,
//			doctorCode : doctorCode ,
//			reportCode : reportCode ,
//			meetingName : meetingName ,
//			location : location ,
//			meetingDate : meetingDate ,
//			type : type
//		},
//		success : function(response) {
//
//		}
//	});
	
}

function preview(){
	StartForm("1","true");
}	


function SendSelfEmail(){
	StratForm("3","false");
}

function StartForm(reporttype,preview){
	
	var reportCode = $('#dwlReport').val();
	var startDate = $('#datePeriod').val();
//	var startMM = startDate.substring(3,5);
//	var startYYYY = startDate.substring(6,10);
//	var endDate = $('#txtEndDate').val();
//	var endMM = endDate.substring(3,5);
//	var endYYYY = endDate.substring(6,10);
	var doctorCode = $('#txtDoctorCode').val();
	var doctorName = $('#txtDoctorName').val();
	var meetingName = $('#txtMeetingName').val();
	var location = $('#txtCounty').val();
	var meetingDate = $('#txtMeetingDate').val();
	var DepartDate = $('#txtDepartDate').val();
	var ArrivedDate = $('#txtArrivedDate').val();
	var Email = $('#txtEmail');
	var type = reporttype;
	
//	alert("re:"+reportCode+"stDate:"+startDate+"stM:"+startMM+"stY:"+startYYYY);
	$("#hidtype").val(type);
	$("#hidReport").val(reportCode);
	$("#hidDate").val(startDate);
//	$("#hidStartYYYY").val(startYYYY);
//	$("#hidEndMM").val(endMM);
//	$("#hidEndYYYY").val(endYYYY);
	$("#hidDoctorCode").val(doctorCode);
	$("#hidMeetingName").val(meetingName);
	$("#hidMeetingDate").val(meetingDate);
	$("#hidtxtCounty").val(location);
	$("#hidDepartDate").val(DepartDate);
	$("#hidArrivedDate").val(ArrivedDate);
	$("#hidEmail").val(Email);
	alert(preview);
	alert(getRunningNum);
	$.ajax({
		url : '/vtnreport/GetRunningNumberSrvl',
		type : 'post',
		data : {
		},
		success : function(running_batch) {
			$("#hidPreview").val(preview);
			if(getRunningNum != 0){
			$("#hidPreview").val("true");
			getRunningNum = running_batch[0]["RUNNING_NUMBER"];
			}
//			alert(getRunningNum);
			$("#hidrunningNumber").val((running_batch[0]["RUNNING_NUMBER"]));
			alert((running_batch[0]["RUNNING_NUMBER"]));
			$("#hidbatch").val((running_batch[0]["batch_date"]));
			$("#frmReport").submit();
		}
	});
}

