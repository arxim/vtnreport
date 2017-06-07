$(document).ready(function() {
	getYYYY();
});

function getYYYY(){
	 $('#dwlHospital').empty(); 
	  $.ajax({
			type : "POST",
			url : "/vtnreport/DropDownListGeneratorSrvl",
			data : {
				url : "getYYYY"
			},
			success : function(data) {
				$("#dwlYYYY").append(data);
			}
	 }); 
}
