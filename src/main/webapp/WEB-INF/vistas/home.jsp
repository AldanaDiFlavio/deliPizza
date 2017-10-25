<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			                
			                <div class="row">
			                	<c:forEach items = "${pizzas}" var="pizzas">
			                		<div class="col-sm-2 col-sm-offset-1">
	                        			<img src="images/pizzas/${pizzas.imagen}" alt="imagen no encontrada" class="img-circle img-raised img-responsive">
	                    			</div>
	                   				<div class="text">
	                                	<h3>${pizzas.nombre}</h3>
	                                    <p class="buttons">	                                        
	                                    	<a href="agregaramipedido?id=${pizzas.id}" class="btn btn-default">Agregar a mi Pedido</a>         
	                                    </p>
		                            </div>
                       			</c:forEach>
			                </div>

			            </div>
		            </div>
	            </div>
           </div>
		</div>
    <jsp:include page="/includes/footer.jsp"></jsp:include>
	</body>
</html>