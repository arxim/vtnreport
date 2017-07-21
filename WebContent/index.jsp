<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>:: Vejthani Report ::</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" type="text/css" href="resources/libraries/bootstrap-3.3.7/css/bootstrap.min.css">
	<script src="resources/libraries/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="resources/libraries/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	
	<!-- encrypt -->
	<script type="text/javascript" src="resources/js/utils/aes.js"></script>
	<script type="text/javascript" src="resources/js/utils/pbkdf2.js"></script>
	<script type="text/javascript" src="resources/js/utils/AesUtil.js"></script>
	<script type="text/javascript" src="resources/js/pages/login/login.js"></script>
	
  <style>
  .modal-header, h4, .close {
      background-color: #337ab7;
      color:white !important;
      text-align: center;
      font-size: 30px;
  }
  .modal-footer {
      background-color: #f9f9f9;
  }
  </style>
</head>
<body>

<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:35px 50px;">
          <h4><span class="glyphicon glyphicon-lock"></span> Payment Report </h4>
        </div>
        <div class="modal-body" style="padding:40px 50px;">
          <form id="frmLogin" action="/vtnreport/LoginSrvl" method="post">
            <div class="form-group">
              <label for="usrname"><span class="glyphicon glyphicon-user"></span> Username</label>
              <input type="text" class="form-control" id="username" name="username" placeholder="Enter username">
            </div>
            <div class="form-group">
              <label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
              <input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
            </div>
              <button type="submit" class="btn btn-primary btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
              
              <c:choose>
				<c:when test="${sessionScope.message=='FAIL'}">
					<div class="row loginFail" > 
					<span><img src="resources/pics/Warning-icon2.png" alt="Mountain View" style="width:25px;height:25px;float: left;"></img></span>
					<label class="warningLogin"> <!-- <input type="checkbox" name="remember_me" id="remember_me"> -->
							wrong password or user. try again!
					</label> 
					</div>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			
		  <!-- HIDDEN SESSION -->
	      <input id="dwlHospital" name="hospitalcode" type="hidden" value="VTN01"></input>
	      <input id="hidPassphrase" name="hidPassphrase" type="hidden" value="${passphrase}"/>
	      <input id="hidIv" name="hidIv" type="hidden" />
		  <input id="hidSalt" name="hidSalt" type="hidden" />
		  <input id="hidIterationCount" name="hidIterationCount" type="hidden" />
		  <input id="hidKeySize" name="hidKeySize" type="hidden" />
			
          </form>
          
        </div>
      </div>
      
    </div>
  </div> 
</div>
 
<script>
$(document).ready(function(){
        $("#myModal").modal({
        	 backdrop: 'static',
        	 keyboard: false
        });
});
</script>

</body>
</html>