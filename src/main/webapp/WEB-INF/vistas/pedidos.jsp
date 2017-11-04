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
			                <h1>CONFIRMAR PEDIDO</h1>
							   <hr>
							  <div class="row">
						        <div class="col-lg-5">
						        <p class="lead">SU PEDIDO:</p>
			                        			                        
			                        <hr>
			                         <%--    --%>   <p class="lead"> Precio Total: $ ${preciototalportodaslaspizzas}</p> 
			                       	
			                         	<p class="lead"> Pago efectivo en puerta </p> 
			                         	
			                       
						        
						        </div>
						        <div class="col-lg-7" class="lead">
						         <p class="lead">SUS DATOS:</p>
			                        			                        
			                        <hr>
						        	<form:form action="validar-pedido" method="POST" modelAttribute="pedido">
						        	  <div class="col-xs-3">
						        	    <p class="lead">Nombre:</p>
									    <form:input type="text" path="solicitante" class="form-control" placeholder="Juan"/>
									  </div>
									  <div class="col-xs-4">
									   <p class="lead">Direccion:</p>
									    <form:input id="direccion" type="text" path="direccion" class="form-control" placeholder="AV Corrientes 2740"/>
									  </div>
									  <div class="col-xs-5">
									   <p class="lead">Telefono:</p>
									    <form:input type="text" path="telefono" class="form-control" placeholder="1162343596"/>
									  </div>
						        
						        </div>
						        </div>
			    <%--  --%> 
			      			<div class="hidden" id="mapa"><p class="lead"> Demora delivey: </p><iframe width="100%" height="300" frameborder="0" style="border:0" allowfullscreen></iframe></div>
						     <div class="col-md-12" id="customer-orders">	
						     <a href="home" class="btn btn-warning">Modificar</a>				  	
			                	<div class="box">		                          	
			                       <c:choose>		   		                              				                              				
									<c:when test="${not empty pedidos}">						
			                        <div class="table-responsive">
			                            <table class="table table-hover">
			                                <thead>
			                                    <tr>
			                                        <th>Nombre</th>
			                                        <th>Precio Unitario</th>	                                        
			                                        <th>Cantidad</th>
			                                        <th>Precio por Cantidad</th>
			                                    </tr>
			                                </thead>
			                                <tbody>
			                                	
			                                	<c:forEach items = "${pedidos}" var="pedidos">                               	
			                                    <tr>                                   	
			                                        <th>${pedidos.nombre}</th>
			                                        <td>${pedidos.precio}</td>
			                                        <td>${pedidos.cantidad}</td>	
			                                        <td>${pedidos.preciototal}</td>		                                        		                                                                     
			                                    </tr>                                                                                                   
			                                    </c:forEach> 
			                                                                                                                                 
			                                </tbody>                             			                            			                              
			                            </table>
			                           
			                            
			                        </div>	
			                         <button type="submit" class="btn btn-lg btn-primary btn-block">Enviar Pedido</button>	 		
						          </c:when>
						         </c:choose>  
						       </form:form>                                         
			                    </div>
						      </div>         
			    
			                </div>
			                
			            </div>
			            
		            </div>
	            </div>
           </div>
		
    <jsp:include page="/includes/footer.jsp"></jsp:include>
	</body>
	<script>
	$( "#direccion" ).blur(function() {
		  var inputDireccion = $("#direccion").val();
		  var url = 'https://www.google.com/maps/embed/v1/directions?key=AIzaSyA2mfgpfvZWDfry05R1F52Gj8V_zBGeYbo&origin=San+Justo,La+Matanza&destination='+inputDireccion;//'&avoid=tolls|highways';
		  $("#mapa iframe").attr('src', url);
		  $('#mapa').removeClass('hidden');
		  //AIzaSyA2mfgpfvZWDfry05R1F52Gj8V_zBGeYbo
		});
	</script>
</html>