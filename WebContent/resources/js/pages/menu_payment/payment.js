$(document).ready(function() {
//	getPaymentContent();
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
//				alert(data);
				$("#dwlYear").append(data);
			}
	 }); 
}