<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html lang="en"> <!--<![endif]-->
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>:: Vejthani Report ::</title>
     
    
    <!-- Java Script Import -->
	<script src="resources/libraries/jquery-1.12.4.min.js" type="text/javascript"></script>
	<script src="resources/libraries/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="resources/libraries/jquery-ui-1.12.1/jquery-ui.min.js" type="text/javascript"></script>
	<script src="resources/libraries/datatables-1.10.13/js/jquery.dataTables.min.js" type="text/javascript"></script>
	<script src="resources/libraries/datatables-1.10.13/extensions/Responsive/js/dataTables.responsive.min.js" type="text/javascript"></script>
    
    
	<link rel="stylesheet" type="text/css" href="resources/css/share.css" media="all" />
	<link rel="stylesheet" type="text/css" href="resources/css/login.css" media="all" /> 
	<!-- encrypt -->
	<script type="text/javascript" src="resources/js/utils/aes.js"></script>
	<script type="text/javascript" src="resources/js/utils/pbkdf2.js"></script>
	<script type="text/javascript" src="resources/js/utils/AesUtil.js"></script>
	<script type="text/javascript" src="resources/js/pages/login/login.js"></script>
	<!-- <script type="text/javascript" src="javascript/md5.js"></script> -->
	

 
</head> 
   <body>
        <form id="frmLogin" action="/vtnreport/LoginSrvl" method="post">
            <div id="input">
                <div class="row">
                    <label for="username">Username</label>
                    <input id="username" name="username" type="text" class="text" maxlength="50"/>
                </div>
                <div class="row">
                    <label for="username">Password</label>
                    <input id="password" name="password" type="password" class="text" maxlength="50" />
                </div>
                <div class="row">
                    <label for="username">Site</label>
					    <span ><select id="dwlHospital" name="hospitalcode" class="selectHospital">
					        
					    </select></span>
		            
                </div>
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
		</div>
            <div id="submit">  
                <input type="submit" value="" class="imgClass" />
            </div>
            <div id="intro">
                <p>Please fill your username and password to login to Doctor Fee Application.</p>
                <p>If you do not have an account, please contact administrator.</p>
            </div>
            
            
            
	             <!-- HIDDEN SESSION -->
	      
	      <input id="hidPassphrase" name="hidPassphrase" type="hidden" value="${passphrase}"/>
	      <input id="hidIv" name="hidIv" type="hidden" />
		  <input id="hidSalt" name="hidSalt" type="hidden" />
		  <input id="hidIterationCount" name="hidIterationCount" type="hidden" />
		  <input id="hidKeySize" name="hidKeySize" type="hidden" />
		  <input id="hidIsLoginNull" name="hidIsLoginNull" type="hidden"  />
        </form>
        
    </body>
    <%-- <jsp:include page="../../../resources/template/modalMessage.jsp"></jsp:include>  --%>
</html>
