$(document).ready(function() {
	getYYYY();
	getBatch();
	
	var hospitalCode = $('#hidhospitalCode').val();
	
	//Data Table
	$('#tblDoctor').dataTable({
		"iDisplayLength": 10,
        "iDisplayStart": 0,
        "sPaginationType": "full_numbers",
        "bLengthChange": true,
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
		
		if ($("#dwlReport").val() == '02') {
			$("#divLabelTerm").hide();
			$("#divValueTerm").hide();
			
			$('#tblDoctor').DataTable().clear()
			$('#tblDoctor').DataTable().draw();
		}else{
			$("#divLabelTerm").show();
			$("#divValueTerm").show();
			
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
		success : function(data) {
			$("#txtMM").val(data[0]["mm"]);
			$("#txtYYYY").val(data[0]["yyyy"]);
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

var current_row = 0;


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
				alert(current_row);
				current_row = 0;
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
		"iDisplayLength": 10,
        "iDisplayStart": 0,
        "sPaginationType": "full_numbers",
        "bLengthChange": true,
		"bProcessing": true,
        "bDestroy": true,
		"ajax" : {
			type : "POST",
			url : "/vtnreport/GetDoctorToSendEmailSrv",
			dataSrc : "data",
			data :{
				yyyy : yyyy,
				mm : mm,
				term : term,
				hospitalCode : hospitalCode,
				report : report
			}
		},
		"fnInitComplete": function( oSettings ) {
			$('#btnSendEmail').prop( "disabled", false );
			$('#all-mail-count').text($('#tblDoctor').dataTable().fnGetData().length);
		 }
	});
}
