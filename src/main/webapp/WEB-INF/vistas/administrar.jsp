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
								<c:when test="${not empty motosocupadas}">
									<div class="table-responsive">
										<h2>Motos Ocupadas</h2>
										<table class="table table-hover">
											<thead>
												<tr>
													<th><span class="lead">Patente</span></th>
													<th><span class="lead">Conductor</span></th>
													<th><span class="lead">Direccion</span></th>
													<th><span class="lead">Solicitante</span></th>
													<th><span class="lead">Telefono</span></th>
													<th><span class="lead">Demora</span></th>
													<th><span class="lead">Precio</span></th>
													<th><span class="lead">Liberar Moto</span></th>
												</tr>
											</thead>
											<tbody>

												<c:forEach items="${motosocupadas}" var="motosocupadas">
													<tr align="left">
														<th><span class="lead bg-danger">${motosocupadas.patente}</span></th>
														<th><span class="lead bg-danger">${motosocupadas.conductor}</span></th>
														<c:forEach items="${motosocupadas.listaPedido}"
															var="listaPedido">
															<th><span class="lead bg-info">${listaPedido.direccion}</span></th>
															<th><span class="lead bg-info">${listaPedido.solicitante}</span></th>
															<th><span class="lead bg-info">${listaPedido.telefono}</span></th>
															<th><span class="lead bg-info">${listaPedido.demora}</span></th>
															<th><span class="lead bg-info">${listaPedido.precio}</span></th>
														</c:forEach>
														<td><a
															href="liberar-moto?patente=${motosocupadas.patente}"
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
													<th><span class="lead">Solicitante</span></th>
													<th><span class="lead">Dirrecion</span></th>
													<th><span class="lead">Telefono</span></th>
													<th><span class="lead">Demora</span></th>
													<th><span class="lead">Direccion</span></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${pedidosenespera}" var="i">
													<tr>
														<th><span class="lead bg-danger">${i.solicitante}</span></th>
														<th><span class="lead bg-danger">${i.direccion}</span></th>
														<th><span class="lead bg-danger">${i.telefono}</span></th>
														<th><span class="lead bg-danger">${i.demora}</span></th>
														<th><span class="lead bg-danger">${i.precio}</span></th>
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
															<th><span class="lead">Patente</span></th>
															<th><span class="lead">Conductor</span></th>
															<th><span class="lead">Pedidos Entregados</span></th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${motoslibres}" var="motoslibres">
															<tr>
																<th><span class="lead bg-danger">${motoslibres.patente}</span></th>
																<th><span class="lead bg-danger">${motoslibres.conductor}</span></th>
																<c:choose>
																	<c:when test="${not empty motoslibres.listaPedido}">

																		<th>

																			<button type="button" class="btn btn-simple"
																				data-toggle="modal"
																				data-target="#${motoslibres.patente}">
																				<a data-toggle="tooltip" data-placement="top"
																					title="PEDIDOS ENTREGADOS"><i
																					class="material-icons" style="font-size: 20px">motorcycle</i></a>
																			</button>
																		</th>


																	</c:when>
																	<c:otherwise>
																		<th><p class="lead bg-info">No tuvo pedidos
																				aún</p></th>
																	</c:otherwise>
																</c:choose>
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

	<c:choose>
		<c:when test="${not empty motoslibres}">
			<c:forEach items="${motoslibres}" var="motoslibres">

				<div id="${motoslibres.patente}" class="modal fade" role="dialog"
					style="overflow-y: scroll;">
					<div class="modal-dialog">
						<div class="modal-content">

							<form class="form-horizontal" role="form" id="form-crear">
								<div class="modal-body">
									<c:choose>
										<c:when test="${not empty motoslibres.listaPedido}">
											<h2>Conductor ${motoslibres.conductor}</h2>
											<hr>
											<c:forEach items="${motoslibres.listaPedido}"
												var="listaPedido">
												<h3>Pedido ${listaPedido.estado}</h3>
												<span class="lead bg-info">${listaPedido.solicitante}
													/</span>
												<span class="lead bg-success">
													${listaPedido.direccion} /</span>
												<span class="lead bg-warning">
													${listaPedido.telefono} /</span>
												<span class="lead bg-primary"> $${listaPedido.precio}</span>
												<hr>
											</c:forEach>
										</c:when>
									</c:choose>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">
										<span class="glyphicon glyphicon-remove"></span> <span
											class="hidden-xs">Cerrar</span>
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:when>
	</c:choose>

	<jsp:include page="/includes/footer.jsp"></jsp:include>
</body>
</html>