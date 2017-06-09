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
			{ "width": "50%" },
			{ "width": "30%" },
		]
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

function sendEmail(){
	var total_table = $('#tblDoctor').dataTable();
//	alert(current_row);
//	alert(total_table.fnGetData().length);
	if(current_row < total_table.fnGetData().length){
		$.ajax({
		url : '/vtnreport/SentEmailSrv',
		type : 'post',
		data : {
			yyyy : '2010',
			mm : '03',
			hospitalCode : 'VTN01',
			doctorCode : '70033'
		},
		success : function(response) {
			if (response == 'PASS') {
				var table = $('#tblDoctor').DataTable();
				table.cell(current_row, 2 ).data( '/' );
				table.draw();
//				alert(response);
			} else if (response == 'FAIL') {
				var table = $('#tblDoctor').DataTable();
				table.cell( current_row, 2 ).data( 'X' );
				table.draw();
			}
			current_row++;
			if (current_row == total_table.fnGetData().length) {
				alert("Finish");
			}
			
			// continue do_cal
			sendEmail();
		}
	});
	}
	
//	var table = $('#tblDoctor').DataTable();
//    table.cell( $(this).closest('tr'), 2 ).data( 'Re-subscribed Successfully' )
//    table.cell( 0, 2 ).data( '/' );
//	table.draw();
	
	/*var yyyy =  $('#dwlYear').val();
	var mm = $('#dwlMonth').val()
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
	 });*/
}

function getDoctor(){
	
	var yyyy =  $('#txtYYYY').val();
	var mm = $('#txtMM').val()
	var hospitalCode = $('#hidHospitalCode').val();
	
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
				hospitalCode : hospitalCode,
			}
		},
		"fnInitComplete": function( oSettings ) {
			$('#btnSendEmail').prop( "disabled", false );
		 }
	});
}
