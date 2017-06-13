<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default no-margin">
	<div class="container-fluid" style="background-color: #c9dce5 ">
<!-- 	294783 -->
		<!-- navbar-header-->
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="form-group">
			<div class="navbar-header">
		<!-- 			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" id="menu-toggle" style="color: #ffffff;"> -->
		<!-- 				<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span> -->
		<!-- 			</button> -->
				<a class="navbar-brand visible-xs" href="javascript:void(0);" style="color: #ffffff">	
					<img src="resources/images/logo-vejthani.png" class="img-responsive" alt="logo vejthani" width="185" height="138">
				</a>
				<br>
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#divNavBar" style="background-color: #ffffff;">
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
			</div>  
		</div> 	
		<div class="collapse navbar-collapse" id="divNavBar">
			<div class="col hidden-xs col-sm-4 text-left">
	        	<div id="logo">
					<img src="resources/images/logo-vejthani.png" class="img-responsive" alt="logo vejthani" width="220" height="170">
				</div>
	      	</div>
			<div class="col-xs-12 col-sm-4 text-center">
				<c:choose>
			     <c:when test="${sessionScope.menuitem!= null}"> 
			         ${sessionScope.menuitem}
			     </c:when>
			     	<c:otherwise>
			
			     	</c:otherwise>
			    </c:choose>
			</div>
			<div class="col-xs-12 col-sm-4 text-center">
	 			<ul class="nav navbar-nav navbar-right">
					<li style="color: #19067f">
						<br>
						<a href="javascript:void(0);" style="color: #19067f">
						<c:choose>
				       <c:when test="${sessionScope.name!= null}"> 
				          ${sessionScope.name}
				      		</c:when>
				       		<c:otherwise> 
				       		</c:otherwise>
				      		</c:choose>
				       </a>
					</li>
					<li>				
						<div class="btn btn-lg">
							<a href="${pageContext.request.contextPath}/LogoutSrvl" style="color: #19067f">
						 	<span class="glyphicon glyphicon-log-out"></span><br>
						 	 Logout 
						 	</a>
						</div>
					</li>
				</ul>
			</div>
		<div class="navbar-header"></div>
	</div>
</nav>
