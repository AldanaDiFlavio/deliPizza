<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>deli-PIZZA</title>
		<jsp:include page="/includes/head.jsp"></jsp:include>
	</head>
	<body class="index-page">
		<jsp:include page="/includes/header.jsp"></jsp:include>
		
		<div class="wrapper">
			<div class="header header-filter" style="background-image: url('images/fondo.jpg');">
				<div class="container">
					<div class="row">
						<div class="col-md-8 col-md-offset-2">
							<div class="brand">
								<img src="images/logos/logo-deli-pizza.png">
								<h3>¡Pedí tu pizza online ahora!</h3>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="main main-raised">
				<div class="section section-basic">
	    			<div class="container">
			            <div class="title">
			                <h2>Pedidos</h2>
			                
			               			                
			                <div class="col-md-12">
			                	 <h2><u>Su pedido</u></h2>
			                	 <h6><a>Cancelar pedido</a></h6>		                	 			                	 
			                	
			                	
								   <c:choose>		   		                              				                              				
								            <c:when test="${not empty pedidos}">
								            
								            
			                					 <c:forEach items = "${pedidos}" var="pedidos">
			                	 
			                	
																 															
															       	 
											                	 <div class="name">Nombre: ${pedidos.nombre}</div>
								           						 <div class="price" style="display: block;">Precio: </div>
								           						 
								           						
								           												           						 
								               			 
			                	 				 </c:forEach>    
			                	 				 
		
			                	 		   </c:when>
			                	   </c:choose>
			                	   						       
								
							 </div> 
			                </div>
			                
			            </div>
		            </div>
	            </div>
           </div>
		
    <jsp:include page="/includes/footer.jsp"></jsp:include>
	</body>
</html>