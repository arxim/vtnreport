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


function checkrunning(type){
	$.ajax({
		url : '/vtnreport/CheckSchedulerSrvl',
		type : 'get',
		 dataType: 'json',
		 success : function(data) {
			 if(type=='c'){
				 if((data[0]["Datetime"]) != 0){
					 $('#success').html("ขณะนี้ระบบถูกกำหนดวันและเวลาในการส่ง  Mail เป็นวัน   "+(data[0]["Datetime"]+" "));
					 $("#popupmodal").modal();
					 $("#btnCancelEmailSchedule").prop( "disabled", false );
				 }else{
					 $('#success').html("ขณะนี้ระบบยังไม่ถูกกำหนดวันและเวลาในการส่ง  Mail");
					 $("#popupmodal").modal();
				 }
					
			 }else{
				 if((data[0]["Datetime"]) != 0){
					 $('#text').html("ขณะนี้ระบบถูกกำหนดวันและเวลาในการส่ง  Mail เป็นวัน   "+(data[0]["Datetime"]+"  ต้องการเปลี่ยนวันและเวลาส่ง  Mail หรือไม่ ?"));
					 $("#mymodal").modal();
				 }else{
					 setScheduleEmail();
					 $("#btnCancelEmailSchedule").prop( "disabled", true );
				 }
				 
			 }
			 
			 
			 
				/*if((data[0]["Datetime"]) != 0){
					alert("eiei1"+data);
					if(type=='c'){
						$('#success').html("ขณะนี้ระบบถูกกำหนดวันและเวลาในการส่ง  Mail เป็นวัน   "+(data[0]["Datetime"]+" "));
						$("#popupmodal").modal();
						$("#btnCancelEmailSchedule").prop( "disabled", false );
					}else{
						$('#text').html("ขณะนี้ระบบถูกกำหนดวันและเวลาในการส่ง  Mail เป็นวัน   "+(data[0]["Datetime"]+"  ต้องการเปลี่ยนวันและเวลาส่ง  Mail หรือไม่ ?"));
						$("#mymodal").modal();
					}
					
				}else{
					alert("eiei2"+data);
					if(type='s'){
						if($("#txtPrintDate").val()==""){
							$('#success').html(" กรุณาเลือก Date Time ก่อน  set schedule ");
							$("#popupmodal").modal();
						}else{
							setScheduleEmail();
						}
					}else{
						$('#success').html("ขณะนี้ระบบยังไม่ถูกกำหนดวันและเวลาในการส่ง  Mail");
						$("#popupmodal").modal();
						$("#btnCancelEmailSchedule").prop( "disabled", true );
					}
					
				}*/
			
			
}
	});
	
}

function checkmodal(){
	/*if(dateTime==""){
		$('#success').html(" กรุณาเลือก Date Time ก่อน  set schedule ");
		$("#popupmodal").modal();
	}else{*/
/*		if (checkrun == 1) {
			$("#mymodal").modal();
		} else {
			$("#mymodal").modal('hide');
			setScheduleEmail();
		}
*/
	if($("#txtPrintDate").val()==""){
		$('#success').html(" กรุณาเลือก Date Time ก่อน  set schedule ");
		$("#popupmodal").modal();
	}else{
		checkrunning('s');
	}
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
//	var modal = document.getElementById("mymodal");
//	
//	alert(cron_schedule);
	$("#dwlReport").prop( "readonly", true );
	$("#txtPrintDate").prop( "readonly", true );
	$("#dwlTerm").prop( "readonly", true );
	$("#btnView").prop( "disabled", true );
	$("#btnSendEmail").prop( "disabled", true );
	
	$.ajax({
		url : '/vtnreport/SendEmailNewSrvl',
		type : 'post',
		data : {
			yyyy : yyyy ,
			mm : mm,
			hospitalCode : hospitalCode ,
			report : report,
			term : term,
			printDate : cron_schedule,
			set_reset : "set"
		},
		success : function(response) {
			$('#success').html("กำหนดเวลาเสร็จสิ้น");
			$("#popupmodal").modal();
			$("#btnCancelEmailSchedule").prop( "disabled", false );
//			$("#alertsuccess").alert("show");
//			$("#alertsuccess").toggleClass('in out'); 
		}
	});

	
	//waitIcon(current_row);
	//if(current_row < total_table.fnGetData().length){
//	$.ajax({
//		url : '/vtnreport/CheckSchedulerSrvl',
//		type : 'get',
//		 dataType: 'json',
//		 success : function(data) {
////			alert("1:"+(data[0]["Datetime"])); 
//			if((data[0]["Datetime"]) != 0){
////				modal.style.display = "none";
//			document.getElementById("text").innerHTML = "ระบบกำลังทำงาน  จะทำการส่ง Mail ตามช่วงเวลา "+(data[0]["Datetime"]+"น. ต้องการเปลี่ยนแปลงเวลาเดิมหรือไม่");
////			 var r = confirm("ระบบกำลังทำงาน  จะทำการส่ง Mail ตามช่วงเวลา "+(data[0]["Datetime"]+"น. ต้องการปลี่ยนแปลงเวลาเดิมหรือไม่"));
////			  if (r == true) {
////				  $.ajax({
////						url : '/vtnreport/SendEmailNewSrvl',
////						type : 'post',
////						data : {
////							yyyy : yyyy ,
////							mm : mm,
////							hospitalCode : hospitalCode ,
////							report : report,
////							term : term,
////							printDate : cron_schedule
////						},
////						success : function(response) {
////				
////						}
////					});
////			  } else {
////				  modal.style.display = "none";
////			  }
//			}
//			else{
////				modal[0].style.display("none");
////				modal.style.display = "block";
//				$("#mymodal").modal('hide');
//				console.log('eiei');
//				 $.ajax({
//						url : '/vtnreport/SendEmailNewSrvl',
//						type : 'post',
//						data : {
//							yyyy : yyyy ,
//							mm : mm,
//							hospitalCode : hospitalCode ,
//							report : report,
//							term : term,
//							printDate : cron_schedule
//						},
//						success : function(response) {
//				
//						}
//					});
//			}
//			
//		 $("#confirm").click(function () {
//			 $.ajax({
//					url : '/vtnreport/SendEmailNewSrvl',
//					type : 'post',
//					data : {
//						yyyy : yyyy ,
//						mm : mm,
//						hospitalCode : hospitalCode ,
//						report : report,
//						term : term,
//						printDate : cron_schedule
//					},
//					success : function(response) {
//			
//					}
//				});
//			  });
//			  
//		 }	  
//	});
	

	
//	$.ajax({
//		url : '/vtnreport/SendEmailNewSrvl',
//		type : 'post',
//		data : {
//			yyyy : yyyy ,
//			mm : mm,
//			hospitalCode : hospitalCode ,
//			report : report,
//			term : term,
//			printDate : cron_schedule
//		},
//		success : function(response) {
//
//		}
//	});
}

function cancelScheduler(){
	//if(checkrun == 1){
		$('#text').html("ต้องการยกเลิกการส่ง  Mail หรือไม่ ");
		$('#modaltitle').html("Reset Schedule ");
		$("#mymodal").modal();
	//	}		
		
	 $("#confirm").click(function () {
		 $.ajax({
			 	url : '/vtnreport/SendEmailNewSrvl',
				type : 'post',
				data : {
					yyyy : "0000" ,
					mm : "0000",
					report : "04",
					set_reset : "reset"
				},
				success : function(response) {
					$("#txtPrintDate").prop('readonly', false);
					$("#txtPrintDate").val("");
					$('#success').html("ยกเลิกการตั้งเวลาสำเร็จ");
					$("#popupmodal").modal();
					
//					$("#alertsuccess").alert("show");
//					$("#alertsuccess").toggleClass('in out'); 
				}
			});
		  });
//	$.ajax({
//		url : '/vtnreport/CheckSchedulerSrvl',
//		type : 'get',
//		 dataType: 'json',
//		 success : function(data) {
////			alert("1:"+(data[0]["Datetime"]));
//			if((data[0]["Datetime"]) != 0){
//			 var r = confirm("ต้องการยกเลิกการส่ง  Mail หรือไม่ ");
//			  if (r == true) {
//				  $.ajax({
//						url : '/vtnreport/',
//						type : 'post',
//						data : {
//							yyyy : yyyy ,
//							mm : mm,
//							hospitalCode : hospitalCode ,
//							report : report,
//							term : term,
//							printDate : cron_schedule
//						},
//						success : function(response) {
//				
//						}
//					});
//			  } else {
//
//			  }
//			}
//			else{
//			alert("ไม่มีงานที่กำลังทำงานในระบบ");
//			}
//			
//		}
//	});
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


