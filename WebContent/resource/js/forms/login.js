 $(document).ready(function(){
	 loadHospitalDropdown();
 });
 
 


function  loadHospitalDropdown() {
	 
	 $('#dwlHospital').empty(); 
	  $.ajax({
			type : "POST",
			url : "/vtnreport/DropDownListGeneratorSrvl",
			data : {
				tb : "HOSPITAL_LOGIN"
			},
			success : function(data) {
				if($.trim(data) !=""){
					$('#dwlHospital').append(data);
				} 
				 
			}
	 });  
}

 
 