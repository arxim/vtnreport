$(document).ready(function() {
	getYYYY();
});

function getYYYY();{
	 $('#dwlYear').empty(); 
	  $.ajax({
			type : "POST",
			url : "/vtnreport/DropDownListGeneratorSrvl",
			data :{
				
				url : "getYYYY"
			}, 
			success : function(data) {
//				alert(data);
				$("#dwlYear").append(data);
			}
	 }); 
}

function getTax(){
	$('#frmTax').submit();
}

function getPayment(){
	$('#frmPayment').submit();
}
function getEmail(){
	$('#frmEmail').submit();
}