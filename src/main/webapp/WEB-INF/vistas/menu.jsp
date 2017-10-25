<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	</head>
	<body>
		<div class="container">
                    <div class="product-slider">
                    	<c:forEach items = "${pizzas}" var="pizzas">
		                   	<div class="col-md-4 col-sm-6">

		                                        	<img src="images/pizzas/${pizzas.imagen}" alt="imagen no encontrada" class="img-responsive">
		                                    	
		                                    	
			                        	<div class="text">
		                                	<h3>${pizzas.nombre}</h3>
		                                    <p class="buttons">	                                        
		                                    	<a href="agregaramipedido?id=${pizzas.id}" class="btn btn-default">Agregar a mi Pedido</a>         
		                                    </p>
		                                </div>
		                            </div>
                         
                       	</c:forEach>
					</div>
            	</div>

		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>
