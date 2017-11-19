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
			style="background-image: url('images/fondo.jpg'); background-size: cover; background-position: top center;">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
						<div class="card card-signup">
							<form:form class="form" action="validar-login" method="POST"
								modelAttribute="usuario">
								<div class="header header-primary text-center">
									<h4>Ingreso administraci�n</h4>
								</div>
								<%--Bloque que es visible si el elemento error no está vacío	--%>
								<c:if test="${not empty error}">
									<h4 class="text-divider">
										<span>${error}</span>
									</h4>
								</c:if>
								<div class="content">
									<div class="input-group">
										<span class="input-group-addon"> <i
											class="material-icons">email</i>
										</span>
										<form:input path="email" id="email" type="email"
											class="form-control" />
									</div>
									<div class="input-group">
										<span class="input-group-addon"> <i
											class="material-icons">lock_outline</i>
										</span>

										<form:input path="password" type="password" id="password"
											class="form-control" />
									</div>
								</div>
								<div class="footer text-center">
									<button class="btn btn-simple btn-primary btn-lg" Type="Submit">Ingresar</button>

								</div>


							</form:form>


						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<jsp:include page="/includes/footer.jsp"></jsp:include>
</body>
</html>
