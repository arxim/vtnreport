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
$(document).on('submit','#frmLogin',function(e) {
	
	var username = $('#username').val();
	var password = $('#password').val();
	var hospital = $('#dwlHospital').val();
    var passphrase = $('#hidPassphrase').val(); 
    if (!username || !password || !hospital){
    	var msg = '<div id="msg" class="error">' + messages['login.invalid'] + '</div>';
    	$('#msg-container').html(msg);
    	$('#frmLogin').addClass('msg-active');
    	e.preventDefault(); // Stop the submit
    }else if (!passphrase) {
    	var msg = '<div id="msg" class="error">' + messages['login.unsecure'] + '</div>';
    	$('#msg-container').html(msg);
    	$('#frmLogin').addClass('msg-active');
    	e.preventDefault(); // Stop the submit
    }else {
    	var iv = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
	    var salt = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
	    var aesUtil = new AesUtil(keySize, iterationCount);
	    var passwordCipher = aesUtil.encrypt(salt, iv, passphrase, password);
	    
	    $('#hidIv').val(iv);
	    $('#hidSalt').val(salt);
	    $('#hidIterationCount').val(iterationCount);
	    $('#hidKeySize').val(keySize);
	    
	    // clear
	    $('#pwdPassword').val(passwordCipher);
    }
});

 
 