<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Login Form</title>
  <link rel="stylesheet" href="resources/css/style.css">
 
 <!-- Java Script Import -->
 <script src="${pageContext.request.contextPath}/resources/libraries/jquery-1.12.4.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/libraries/bootstrap-3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/libraries/jquery-ui-1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/libraries/datatables-1.10.13/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/libraries/datatables-1.10.13/extensions/Responsive/js/dataTables.responsive.min.js" type="text/javascript"></script>

  <!-- encrypt -->
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/aes.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/pbkdf2.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/AesUtil.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pages/login/login.js"></script>
  <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>
  <section class="container">
    <div class="login">
      <h1>Login to Web App</h1>
      
      <form id="frmLogin" action="/vtnreport/LoginSrvl" method="post">
        <p><input type="text" id="username" name="username" value="" placeholder="Username"></p>
        <p><input type="password"  id="password" name="password" value="" placeholder="Password"></p>
        <p><span class="styled-select" >
		     <select id="dwlHospital" name="hospitalcode">
		        <option value="test1">Option 1</option>
		        <option value="test2">Option 2</option>
		        <option value="test3">Option 3</option>
		        <option value="test4">Option 4</option>
		        <option value="test5">Option 5</option>
		    </select>
		</span></p>
        <p class="remember_me">
          <label>
            <input type="checkbox" name="remember_me" id="remember_me">
            Remember me on this computer
          </label>
        </p>
        <p class="submit"><input type="submit" name="commit" value="Login"></p>
    
       <!-- HIDDEN SESSION -->
        
      <input id="hidPassphrase" name="hidPassphrase" type="hidden" value="${passphrase}"/>
      <input id="hidIv" name="hidIv" type="hidden" />
	  <input id="hidSalt" name="hidSalt" type="hidden" />
	  <input id="hidIterationCount" name="hidIterationCount" type="hidden" />
	  <input id="hidKeySize" name="hidKeySize" type="hidden" />
      </form>
    </div>

    <div class="login-help">
      <p>Forgot your password? <a href="index.html">Click here to reset it</a>.</p>
    </div>
    
    
  </section>

  
</body>
</html>