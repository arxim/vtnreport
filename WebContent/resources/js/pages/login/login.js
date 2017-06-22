 $(document).ready(function(){
	 loadHospitalDropdown();
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
					$('#dwlHospital').append(data);
				} 
				 
			}
	 });  
}
$(document).on('submit','#frmLogin',function(e) {
	// global variable
	var iterationCount = 1000;
	var keySize = 128;
	
	var username = $('#username').val();
	var password = $('#password').val();
	var hospital = $('#dwlHospital').val();
    var passphrase = $('#hidPassphrase').val(); 
    if (!username || !password || !hospital){
    	$('#hidIsLoginNull').val('isEmpty');
    	var title = '<div id="msg"  > please insert your username and password</div>'
    	var footer = '<button type="button" class="btn btn-default" data-dismiss="modal"  onclick=\"remomeModalBackDrop('+modalID+')\">Close</button>'
    	modalInfo(title, footer);
       }else if (!passphrase) {
    	/*var msg = '<div id="msg" class="error">' + messages['login.unsecure'] + '</div>';
    	$('#msg-container').html(msg);
    	$('#frmLogin').addClass('msg-active');
    	e.preventDefault(); // Stop the submit
*/    }else {
    	var iv = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
	    var salt = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
	    var aesUtil = new  AesUtil(keySize, iterationCount);
	    var passwordCipher = aesUtil.encrypt(salt, iv, passphrase, password);
	    $('#hidIsLoginNull').val('notEmpty');
	    $('#hidIv').val(iv);
	    $('#hidSalt').val(salt);
	    $('#hidIterationCount').val(iterationCount);
	    $('#hidKeySize').val(keySize);
	    
	    // clear
	    $('#password').val(passwordCipher);
    }
});

 
 