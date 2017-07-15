$(document).ready(function() {
	
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
function getManual(){
//	window.location.href = 'SCAP-VTN-DFS_PRESENT_V1.0.pdf',"_blank";
	window.open('SCAP-VTN-DFS_PRESENT_V1.0.pdf',"_blank");
}