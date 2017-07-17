$(document).ready(function() {
	getYYYY();
	getBatch();
	
	$("#lblPrintDate").hide();
	$("#divPrintDate").hide();
	$("#divLabelTerm").hide();
	$("#divValueTerm").hide();
	
	$("#btn-email").addClass("active").css("background-color","#87b2e0");
	
	$('#txtPrintDate').datepicker({
		format : "dd/mm/yyyy",
		autoclose : true,
		todayHighlight : true
	});
	
	$('#txtPrintDate').datepicker('setDate', 'now');
	
	var hospitalCode = $('#hidhospitalCode').val();
	
	//Data Table
	$('#tblDoctor').dataTable({
//		"iDisplayLength": 1000000,
		paging: false,
        "iDisplayStart": 0,
        "sPaginationType": "full_numbers",
        "bLengthChange": false,
		"bProcessing": true,
        "bDestroy": true,
		"columns": [
			{ "width": "20%" },
			{ "width": "70%" },
			{ "width": "10%"},
		],
		"columnDefs": [
	        {"className": "dt-center", "targets": -1}
	      ],
	});
	
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
	

	// On Change Select Dropdown
	$("#dwlReport").change(function() {
		
		if ($("#dwlReport").val() == '02' || $("#dwlReport").val() == '03') {
			
			$('#btnSendEmail').prop( "disabled", true );
			$("#lblPrintDate").hide();
			$("#divPrintDate").hide();
			$("#divLabelTerm").hide();
			$("#divValueTerm").hide();
			
			$(".paymentDF").show();
			$(".taxt406").hide();
			
			$('#record-mail-count').text("0");
			$('#all-mail-count').text("0");
			
			$('#tblDoctor').DataTable().clear()
			$('#tblDoctor').DataTable().draw();
			
		}else{
			
			$('#btnSendEmail').prop( "disabled", true );
			$("#lblPrintDate").show();
			$("#divPrintDate").show();
			$("#divLabelTerm").show();
			$("#divValueTerm").show();
			
			$(".paymentDF").hide();
			$(".taxt406").show();

			$('#record-mail-count').text("0");
			$('#all-mail-count').text("0");
			
			$('#tblDoctor').DataTable().clear()
			$('#tblDoctor').DataTable().draw();
			
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

var current_row = 0;


function waitIcon(current_row_wait){
	var table = $('#tblDoctor').DataTable();
	table.cell(current_row_wait, 2 ).data( '<div class="text-center"><img src="resources/pics/process.gif" class="img-responsive" style="display:block; margin:auto;" alt="icon process" width="20" height="20"></div>' );
	table.draw();
}


// Send Email
function sendEmail(){
	var total_table = $('#tblDoctor').dataTable();
	var table_getDoctor = $('#tblDoctor').DataTable();
	var doctorCode = table_getDoctor.cell(current_row,0).data();
	
	var yyyy = $('#txtYYYY').val();
	var mm = $('#txtMM').val();
	var hospitalCode = $('#hidHospitalCode').val();
	var	report = $('#dwlReport').val();
	var term = $('#dwlTerm').val();
	var tempPrintDate = $("#txtPrintDate").val();
	var printDate = tempPrintDate.substring(6,10)+tempPrintDate.substring(3,5)+tempPrintDate.substring(0,2);

	$("#dwlReport").prop( "disabled", true );
	$("#txtPrintDate").prop( "disabled", true );
	$("#dwlTerm").prop( "disabled", true );
	$("#btnView").prop( "disabled", true );
	$("#btnSendEmail").prop( "disabled", true );
	
	waitIcon(current_row);
	if(current_row < total_table.fnGetData().length){
		$.ajax({
		url : '/vtnreport/SentEmailSrv',
		type : 'post',
		data : {
			yyyy : yyyy ,
			mm : mm,
			hospitalCode : hospitalCode ,
			doctorCode : doctorCode,
			report : report,
			term : term,
			printDate : printDate
		},
		success : function(response) {
			if (response == 'PASS') {
				var table = $('#tblDoctor').DataTable();
				table.cell(current_row, 2 ).data( '<div class="text-center"><span class="glyphicon glyphicon-ok" style="color:#4CAF50"><span style="visibility: hidden">1</span></span></div>' );
				table.draw();
			} else if (response == 'FAIL') {
				var table = $('#tblDoctor').DataTable();
				table.cell( current_row, 2 ).data( '<div class="text-center"><span class="glyphicon glyphicon-remove" style="color:#FF0000"><span style="visibility: hidden">2</span></span></div>' );
				table.draw();
			}
			current_row++;
			$('#record-mail-count').text(current_row);
			if (current_row == total_table.fnGetData().length) {
				processMmodalSuccess();
				current_row = 0;
				
				$("#dwlReport").prop( "disabled", false );
				$("#txtPrintDate").prop( "disabled", false );
				$("#dwlTerm").prop( "disabled", false );
				$("#btnView").prop( "disabled", false );
				$("#btnSendEmail").prop( "disabled", true );
				
			}else{
				sendEmail();
			}

		}
	});
	}
}

// Get Datatable Doctor
function getDoctor(){
	
	var yyyy =  $('#txtYYYY').val();
	var mm = $('#txtMM').val()
	var hospitalCode = $('#hidHospitalCode').val();
	var	report = $('#dwlReport').val();
	var term = $('#dwlTerm').val();
	
	$('#tblDoctor').dataTable({
//		"iDisplayLength": 10000000,
		paging: false,
        "iDisplayStart": 0,
        "sPaginationType": "full_numbers",
        "bLengthChange": false,
		"bProcessing": true,
        "bDestroy": true,
		"ajax" : {
			type : "POST",
			url : "/vtnreport/GetDoctorToSendEmailSrv",
			dataSrc : "data",
			data :{
				yyyy : "2014",
				mm : mm,
				term : term,
				hospitalCode : hospitalCode,
				report : report
			}
		},
		"fnInitComplete": function( oSettings ) {
			countDataTable = $('#tblDoctor').dataTable().fnGetData().length;
			if(countDataTable > 0){
				$('#btnSendEmail').prop( "disabled", false );
			}
			
			$('#all-mail-count').text($('#tblDoctor').dataTable().fnGetData().length);
		 }
	});
}
