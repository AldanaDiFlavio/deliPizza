<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/includes/head.jsp"></jsp:include>
</head>
<body class="signup-page">
	<jsp:include page="/includes/header.jsp"></jsp:include>
	<div class="wrapper">
		<div class="header header-filter"
			style="background-image: url('images/fondo.jpg');">
			<div class="container">
				<div class="row">
					<div class="card card-signup">

						<div class="header header-primary text-center">
							<h4>Cuadrilla de motos</h4>
						</div>
						<div class="content">
							<c:choose>
								<c:when test="${not empty motos}">
									<div class="table-responsive">
										<table class="table table-hover">
											<h2>Motos Ocupadas</h2>
											<thead>
												<tr>
													<th>Patente</th>
													<th>Conductor</th>
													<th>Direccion</th>
													<th>Solicitante</th>
													<th>Telefono</th>
													<th>Precio</th>	
													<th>Demora Informada</th>													
													<th>Liberar Moto</th>
												</tr>
											</thead>
											<tbody>

												<c:forEach items="${motosocupadas}" var="motosocupadas">
													<tr> 
														<th>${motosocupadas.patente}</th>
														<th>${motosocupadas.conductor}</th>
														<c:forEach items="${motosocupadas.listaPedido}"
															var="listaPedido">															
															<th>${listaPedido.direccion}</th>
															<th>${listaPedido.solicitante}</th>
															<th>${listaPedido.telefono}</th>
															<th>${listaPedido.precio}</th>
															<th>${listaPedido.demora}</th>
														</c:forEach>
														<td><a href="liberar-moto?patente=${motosocupadas.patente}"
															class="btn btn-primary">Liberar</a></td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
									</div>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${not empty pedidosenespera}">
									<div class="table-responsive">
										<h2>Pedidos En Espera</h2>
										<table class="table table-hover">
											<thead>
												<tr>
													<th>Solicitante</th>
													<th>Direccion</th>
													<th>Demora Informada</th>
													<th>Detalle</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${pedidosenespera}" var="i">
													<tr>
														<th>${i.solicitante}</th>
														<th>${i.direccion}</th>
														<th>${i.id}</th>
														<th>${i.id}</th>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${not empty motoslibres}">
											<div class="table-responsive">
												<h2>Motos Libres</h2>
												<table class="table table-hover">
													<thead>
														<tr>
															<th>Patente</th>
															<th>Conductor</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${motoslibres}" var="i">
															<tr>
																<th>${i.patente}</th>
																<th>${i.conductor}</th>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</c:when>
									</c:choose>
								</c:otherwise>
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