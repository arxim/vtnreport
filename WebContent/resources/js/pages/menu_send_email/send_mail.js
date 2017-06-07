$(document).ready(function() {
	getYYYY();
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
function sendEmail(){
	var yyyy =  $('#dwlYear').val();
	var mm = $('#dwlMonth').val()
	var hospitalCode = "VTN01";
	var doctorCode = "70001"
		
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