<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<!-- Navbar -->
	<nav class="navbar navbar-transparent navbar-fixed-top navbar-color-on-scroll">
		<div class="container">
        	<div class="navbar-header">
		    	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-index">
		        	<span class="sr-only">Toggle navigation</span>
		        	<span class="icon-bar"></span>
		        	<span class="icon-bar"></span>
		        	<span class="icon-bar"></span>
		    	</button>
	        	<div class="logo-container">
	                <div class="logo">
	                    <a href="home"><img src="images/logos/deli-pizza.png"></a>
	                </div>
				</div>
	    	</div>
		    <div class="collapse navbar-collapse" id="navigation-index">
		    	<ul class="nav navbar-nav navbar-right">
		    		<c:choose>
	            		<c:when test="${usuario.user != null}">
		                	<li><a href="#"style="color: white">${usuario.user}</a></li>
		                    <li><a href="administrar">Administrar</a></li>
		                    <li><a href="cerrarSesion">Salir</a></li>
	            		</c:when>
	            		<c:otherwise>
		                	<li>
								<a href="login">Login</a>
							</li>
	            		</c:otherwise>
            		</c:choose>
<!-- 					<li> -->
<!-- 						<a rel="tooltip" title="Follow us on Twitter" data-placement="bottom" href="#" target="_blank" class="btn btn-white btn-simple btn-just-icon"> -->
<!-- 							<i class="fa fa-twitter"></i> -->
<!-- 						</a> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<a rel="tooltip" title="Like us on Facebook" data-placement="bottom" href="#" target="_blank" class="btn btn-white btn-simple btn-just-icon"> -->
<!-- 							<i class="fa fa-facebook-square"></i> -->
<!-- 						</a> -->
<!-- 					</li> -->
<!-- 					<li> -->
<!-- 						<a rel="tooltip" title="Follow us on Instagram" data-placement="bottom" href="#" target="_blank" class="btn btn-white btn-simple btn-just-icon"> -->
<!-- 							<i class="fa fa-instagram"></i> -->
<!-- 						</a> -->
<!-- 					</li> -->
		    	</ul>
		    </div>
		</div>
	</nav>
	<!-- End Navbar -->