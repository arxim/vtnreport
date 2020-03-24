$(document).ready(function() {
	checkRole();
	$("#reason").hide();
	$("#reason2").hide();
	$("#self_mail").hide();
	$("#SS").attr("checked","checked");
	
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
	   getEmailDoctor();
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
	
	$("#btnSendMail").show();
	$("#sent_mail").hide();
	$("#btnView").hide();
	
	$('input:radio').change(function(){
		$("#hid_sign").val($("input:radio[name=SS]:checked").attr("id"));
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

function getEmailDoctor(){
	$.ajax({
        type: "POST",
           url: '/vtnreport/GetDoctorToSendEmailSrv',
           dataType: "json",
           data: {
        	   report:"02",
        	   doctorCode:$("#txtDoctorCode").val(),
        	   hospitalCode:$('#hidHospitalCode').val(),
           },
           success: function(data) {
               $("#txtEmail").val(data[0].value);
           }
       });
}

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
}

function setSendMail(){
	StartForm("2","false");
}

function preview(){
	StartForm("1","true");
}	


function SendSelfEmail(){
	StartForm("3","false");
}

function StartForm(reporttype,preview){
	var reportCode = $('#dwlReport').val();
	var startDate = $('#datePeriod').val();
	var doctorCode = $('#txtDoctorCode').val();
	var doctorName = $('#txtDoctorName').val();
	var meetingName = $('#txtMeetingName').val();
	var location = $('#txtCounty').val();
	var meetingDate = $('#txtMeetingDate').val();
	var DepartDate = $('#txtDepartDate').val();
	var ArrivedDate = $('#txtArrivedDate').val();
	var Email = $('#txtEmail');
	var type = reporttype;
	
	
	$("#hidtype").val(type);
	$("#hidReport").val(reportCode);
	$("#hidDate").val(startDate);
	$("#hidDoctorCode").val(doctorCode);
	$("#hidMeetingName").val(meetingName);
	$("#hidMeetingDate").val(meetingDate);
	$("#hidtxtCounty").val(location);
	$("#hidDepartDate").val(DepartDate);
	$("#hidArrivedDate").val(ArrivedDate);
	$("#hidEmail").val(Email);
	$("#subj_mail").val($("#dwlReport option:selected").text());
	
	
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
			/*alert((running_batch[0]["RUNNING_NUMBER"]));*/
			$("#hidbatch").val((running_batch[0]["batch_date"]));
			
			if(type=="3" || type=="2"){
				$("#sent-loading-modal").modal();
				$.ajax({
					url : '/vtnreport/GenerateReportSrvl',
					type : 'post',
					data : {
					hidReport  :$("#hidReport").val(),
					hidDate    :$("#hidDate").val(),
					hidDoctorCode:$("#hidDoctorCode").val(),
					hidMeetingName:$("#hidMeetingName").val(),
					hidMeetingDate:$("#hidMeetingDate").val(),
					hidtxtCounty:$("#hidtxtCounty").val(),
					hidDepartDate:$("#hidDepartDate").val(),
					hidArrivedDate:$("#hidArrivedDate").val(),
					hidEmail:$("#hidEmail").val(),
					hidtype:$("#hidtype").val(),
					hidPreview:$("#hidPreview").val(),
					subj_mail:$("#dwlReport option:selected").text(),
					hid_sign:$("#hid_sign").val(),
					},
					success : function() {
						$("#sent-loading-modal").modal('hide');
						$("#sent-success-modal").modal();
					}
					
				});
			}else{
				$("#frmReport").submit();
			}
			
		}
	});
}


function backGroundRefresh(){
	alert("backGroundRefresh");
	
	$.ajax({
        type: "POST",
           url: '/vtnreport/getFormContentSrvl',
           data: {data : JSON.stringify(data)
           },
           success: function(data) {
           }
       });
}

