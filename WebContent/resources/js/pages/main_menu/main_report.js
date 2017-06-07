$(document).ready(function() {
//	getYYYY();
});

function getYYYY(){
	 $('#tbReport').empty(); 
	  $.ajax({
			type : "POST",
			url : "/vtnreport/SentEmailSrv",
			success : function(data) {
				$("#tbReport").append(data);
			}
	 }); 
}

function removeEmt(){
	$('#tbReport').empty(); 
}