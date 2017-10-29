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
			                <h2>Menú</h2>
			                
			                <div class="col-md-10">
			                
			               
			                
			                	<c:forEach items = "${listaPizzas}" var="listaPizzas">
			                	
			                	
				                	<div class="col-sm-4 col-sm-6">
		                        		<img src="images/pizzas/${listaPizzas.imagen}" alt="imagen no encontrada" class="img-rounded img-responsive">	                              
		                                 <pre><h3>${listaPizzas.nombre} $${listaPizzas.precio}</h3></pre>
		                                
		                                 
	                                      <%-- <a href="aniadirCarrito/${listaPizzas.nombre}/5" class="btn btn-warning">Agregar a mi Pedido</a>	 --%>
	                                      
	                                     
	                               
		                            
		                        	  <form:form method="POST" modelAttribute="lapizza" action="aniadirCarrito">
		                        	   <form:input path="nombre" name="nombre" type="hidden" value="${listaPizzas.nombre}" />
		                        	   <form:select path="cantidad" class="form-control">
											<form:options items="${numeros}" />
										</form:select>		                        	   
									    <c:choose>
										  <c:when test="${nombresdepizzas.nombre == listaPizzas.nombre}">
										    <button type="submit" class="btn btn-warning">Modificar mi Pedido</button>
										  </c:when>
										  <c:otherwise>									     
										      <button type="submit" class="btn btn-warning">Agregar a mi Pedido</button>
										  </c:otherwise>
										</c:choose>		   								                     	   
		                        	  </form:form>	
		                        	</div>  
                       			</c:forEach>
                       		 
                       			
                       			
			                </div>
			                
			                <div class="col-md-2">
			                	 <h2><u>Su pedido</u></h2>
			                	  <c:choose>		   		                              				                              				
								            <c:when test="${not empty pizzasdelpedidorealizado}">
								            	<div class="name">Nombre: ${pizzasdelpedidorealizado.nombre}</div>
								            </c:when>
			                	   </c:choose>
			                	 <h6><a>Cancelar pedido</a></h6>		                	 			                	 
			                	 <ul>
			                	
								   <c:choose>		   		                              				                              				
								            <c:when test="${not empty pizzaspedidas}">
								            <form action="validar-pedido" method="POST"> 	
								            
			                					 <c:forEach items = "${pizzaspedidas}" var="pizzaspedidas">
			                	 
			                	 
			                	 					<!-- Lo saque de pedidos ya, pero no puedo hacer que meta el valor el cliente 
								                	   <li id="2021416" title="Editar" class="modified seen" data-auto="shopdetails_cart_item">
												            <div class="productQuantity dropdown" title="Seleccionar cantidad">
												                <select name="qty_2021416" class="" id="qty_2021416">
																		<option value="1" selected="selected">1</option>
																		<option value="2">2</option>
																 </select> 
																 -->
																
																 															
															       	 
											                	 <div class="name">Nombre: ${pizzaspedidas.nombre}</div>
								           						 <div class="price" style="display: block;">Precio: ${pizzaspedidas.precio}</div>
								           						 <div class="price" style="display: block;">Cantidad: ${pizzaspedidas.cantidad}</div>
								           						 <div class="name"><a href="quitarDelCarrito?nombre=${pizzaspedidas.nombre}">Remover</a></div>
								           												           						 
								               			</li></br> 	 
			                	 				 </c:forEach>    
			                	 				 
			                	 				  <button type="submit" class="btn btn-warning">Confirmar Pedido</button>
			                	 				 
			                	 		   </form>	
			                	 		   </c:when>
			                	   </c:choose>
			                	   						       
								</ul>
							 </div> 
			                </div>
			                
			            </div>
		            </div>
	            </div>
           </div>
		</div>
    <jsp:include page="/includes/footer.jsp"></jsp:include>
	</body>
</html>