$(document).ready(function() {
	alert("test");
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
				if($.trim(data) !=""){
					//$('#dwlHospital').append(data);
				} 
			}
	 }); 
}
