<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>deli-PIZZA</title>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body class="index-page">
	<jsp:include page="/includes/header.jsp"></jsp:include>

	<div class="wrapper">
		<div class="header header-filter"
			style="background-image: url('images/fondo.jpg');">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<div class="brand">
							<img src="images/logos/logo-deli-pizza.png">
							<h3 id="menu">¡Pedí tu pizza online ahora!</h3>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="main main-raised">
			<div class="section section-basic">
				<div class="container">
					<div class="title">
						<h1>Menú</h1>
						<hr>

						<div class="col-md-10">



							<c:forEach items="${listaPizzas}" var="listaPizzas">


								<div class="col-sm-4 col-sm-6" style="margin-bottom: 5%">
									<span>
										<h3>${listaPizzas.nombre}</h3>
									</span> <img src="images/pizzas/${listaPizzas.imagen}"
										alt="imagen no encontrada" class="img-rounded img-responsive">
									<span>
										<h3>${listaPizzas.precio}</h3>
									</span>


									<form:form method="POST" modelAttribute="lapizza"
										action="aniadirCarrito">
										<form:input path="id" name="id" type="hidden"
											value="${listaPizzas.id} " />
										<form:select path="cantidad" class="form-control">
											<c:forEach var="cantidadPizzas" begin="1" end="20">
												<form:option value="${cantidadPizzas}">${cantidadPizzas}</form:option>
											</c:forEach>
										</form:select>

										<button type="submit" class="btn btn-warning">Agregar
											a mi Pedido</button>

									</form:form>
								</div>
							</c:forEach>



						</div>

						<div class="col-md-2">
							<c:choose>
								<c:when test="${not empty pizzaspedidas}">
									<h2>Su pedido</h2>
									<hr>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${not empty pedidorealizado}">

									<button type="button" class="btn btn-simple"
										data-toggle="modal" data-target="#ModalCrear">
										<a data-toggle="tooltip" data-placement="top"
											title="PEDIDO EN CURSO"><i class="material-icons"
											style="font-size: 40px">motorcycle</i></a>
									</button>


									<hr>
								</c:when>
							</c:choose>

							<h5>
								<c:choose>
									<c:when test="${not empty pizzaspedidas}">

										<button type="button" class="btn btn-simple">
											<a data-toggle="tooltip" data-placement="top"
												title="Eliminar pedido" href="cancelarCarrito"><i
												class="material-icons" style="font-size: 40px">delete</i></a>
										</button>
										<hr>
										<h5>TOTAL: ${preciototalportodaslaspizzas}</h5>
										<hr>
									</c:when>
								</c:choose>
							</h5>


							<ul>

								<c:choose>
									<c:when test="${not empty pizzaspedidas}">
										<form action="mostrar-pedido" method="POST">

											<c:forEach items="${pizzaspedidas}" var="pizzaspedidas">

												<div class="name">Nombre: ${pizzaspedidas.nombre}</div>
												<div class="price" style="display: block;">Precio:
													$${pizzaspedidas.preciototal}</div>
												<div class="price" style="display: block;">Cantidad:
													${pizzaspedidas.cantidad}</div>
												<div class="name">
													<a href="quitarDelCarrito?id=${pizzaspedidas.id}">REMOVER</a>
												</div>
												<hr>

											</c:forEach>

											<button type="submit" class="btn btn-primary">Reservar
												Pedido</button>

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

	<div id="ModalCrear" class="modal fade" role="dialog"
		style="overflow-y: scroll;">
		<div class="modal-dialog">
			<div class="modal-content">

				<form class="form-horizontal" role="form" id="form-crear">
					<div class="modal-body">
						<c:choose>
							<c:when test="${not empty pedidorealizado}">



								<c:forEach items="${pedidorealizado}" var="pedidorealizado">
									<h2>Datos del pedido</h2>
									<c:forEach items="${pedidorealizado.listaPizzas}"
										var="listaPizzas">
										<div class="name">Contiene: ${listaPizzas.nombre} X
											${listaPizzas.cantidad}</div>
										<hr>
									</c:forEach>
									<div>Total a pagar: $${pedidorealizado.precio}</div>
									<hr>
									<div>Pedido por: ${pedidorealizado.solicitante}</div>
									<hr>
									<div>Direccion: ${pedidorealizado.direccion}</div>
									<hr>
									<div>Telefono: ${pedidorealizado.telefono}</div>
									<hr>
									<div>Estado del pedido: ${pedidorealizado.estado}</div>
									<hr>
									<div>Demora aproximada: ${pedidorealizado.demora}</div>
									<hr>


								</c:forEach>

							</c:when>
						</c:choose>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span> <span
								class="hidden-xs">Cerrar</span>
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="/includes/footer.jsp"></jsp:include>

</body>
</html>