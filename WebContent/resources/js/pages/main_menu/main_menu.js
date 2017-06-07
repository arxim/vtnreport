$(document).ready(function() {
	getPaymentContent();
});

function getPaymentContent(){
	 $('#panelContent').empty(); 
	  $.ajax({
			type : "POST",
			url : "/vtnreport/getPaymentContentSrvl",
			success : function(data) {
				$('#panelContent').append(data);
			}
	 }); 
}
