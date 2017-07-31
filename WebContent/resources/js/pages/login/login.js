 $(document).ready(function(){
//	 loadHospitalDropdown();
 });
 
 


function  loadHospitalDropdown() {
	 
	 $('#dwlHospital').empty(); 
	  $.ajax({
			type : "POST",
			url : "/vtnreport/DropDownListGeneratorSrvl",
			data : {
				url : "getHospital"
			},
			success : function(data) {
				if($.trim(data) !=""){
					$('#dwlHospital').val(data);
				} 
				 
			}
	 });  
}
$(document).on('submit','#frmLogin',function(e) {
	var username = $('#username').val();
	var password = $('#password').val();
	var hospital = $('#dwlHospital').val();
	
    if (!username || !password || !hospital){
    	$('#hidIsLoginNull').val('isEmpty');
    	var title = '<div id="msg"  > please insert your username and password</div>'
    	var footer = '<button type="button" class="btn btn-default" data-dismiss="modal"  onclick=\"remomeModalBackDrop('+modalID+')\">Close</button>'
    	modalInfo(title, footer);
    }
});

 
 