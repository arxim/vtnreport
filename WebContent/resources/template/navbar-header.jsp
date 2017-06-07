<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default no-margin">
	<div class="container-fluid" style="background-color: #294783">
		<!-- navbar-header-->
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="form-group"></div>
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#divNavBar">
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" id="menu-toggle" style="color: #ffffff;">
				<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>
			</button>
			<a class="navbar-brand" href="javascript:void(0);" style="color: #ffffff">
			</a>  
		</div> 	
		<div class="collapse navbar-collapse navbar-right" id="divNavBar">
			<div class="container text-center">				
		        <a href="#" class="btn btn-default" onclick="getPaymnet()">
		        	<span class="btn-lg glyphicon glyphicon-list-alt"></span>   	
		        </a>&nbsp;
		        <a href="#" class="btn btn-default" onclick="getTax()">
		            <span class="btn-lg glyphicon glyphicon-usd"></span>
		        </a>&nbsp;
		        <a href="#" class="btn btn-default" onclick="sendEmail()">
		            <span class="btn-lg glyphicon glyphicon-send"></span>
		        </a>&nbsp;
	 			<ul class="nav navbar-nav navbar-right">
					<li style="color: #FFFFFF">
						<br/>
						<a href="javascript:void(0);" style="color: #ede3e3">FirstName : &nbsp; LastName</a>
					</li>
					<li>
						<div class="btn btn-lg">
							<a href="${pageContext.request.contextPath}/LogoutSrvl" style="color: #ffffff">
						 	<span class="glyphicon glyphicon-log-out"></span><br/>
						 	 Logout 
						 </a>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="navbar-header" ></div>
	</div>
</nav>