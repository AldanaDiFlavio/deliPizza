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
   			
			            <div class="title" id="conf">
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
						        	<form:form name="confirform" action="validar-pedido" method="POST" modelAttribute="pedido">
						        	  <div class="col-xs-3">
						        	    <p class="lead">Nombre:</p>
									    <form:input id="nombre" type="text" path="solicitante" class="form-control" placeholder="Juan"/>
									  </div>
									  <div class="col-xs-4">
									   <p class="lead">Direccion:</p>
									    <form:input id="direccion" type="text" path="direccion" class="form-control" placeholder="AV Corrientes 2740"/><span id="direerror"></span>								   
									  </div>
									  <div class="col-xs-5">
									   <p class="lead">Telefono:</p>
									    <form:input id="telefono" type="text" path="telefono" class="form-control" placeholder="0111562343596" value="" onKeyPress="return validar(event)" maxlength="20"/>
									  </div>
						        
						        </div>
						        </div>

			  			  <%--  <div id="map">MAP</div> <div class="hidden" id="duration"> --%> 
		
							<div class="hidden" id="map">MAP</div>		
			      			
			      			<div  style="visibility:hidden;" id="mapa"><div class="lead"> Demora delivey: <span  id="duration"></span></div><div class="lead">¿Su dirección?</div><iframe id="ifra" width="100%" height="0" frameborder="0" style="border:0" allowfullscreen></iframe></div>
						     <div class="col-md-12" id="customer-orders">	
						     <a href="home#menu" class="btn btn-warning">MODIFICAR PEDIDO</a>				  	
			                	<div class="box">		                          	
			                       <c:choose>		   		                              				                              				
									<c:when test="${not empty pedidos}">						
			                        <div class="table-responsive" >
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
			                          <div id="dspbtn" ></div>
			                         <button id="tresp" type="button" class="btn btn-lg btn-primary btn-block"  onclick="valida_envia()">Enviar Pedido</button>	
		
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
           
           			                	    <div id="ModalCrear" class="modal fade" role="dialog" style="overflow-y: scroll;">
					                    <div class="modal-dialog">
					                        <div class="modal-content">
					
					                            <form class="form-horizontal" role="form" id="form-crear">
					                                <div class="modal-body">
					                      				<h2>¿Estas seguro de enviar el pedido?</h2>
			                	   
					                                </div>
					                                <div class="modal-footer">
					                                
					                                 <button type="button" class="btn btn-primary" data-dismiss="modal" onClick="javascript: document.confirform.submit();" >
					                                        <span class="hidden-xs">Confirmar</span>
					                                    </button>
					                                    <button type="button" class="btn btn-warning" data-dismiss="modal" onclick="valida_noenvia()">
					                                        <span class="glyphicon glyphicon-remove"></span>
					                                        <span class="hidden-xs">Cancelar</span>
					                                    </button>
					                                </div>
					                            </form>
					                        </div>
					                    </div>
					                </div>	
					                
					                
		
    <jsp:include page="/includes/footer.jsp"></jsp:include>
	</body>
	
		
	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBCGMlKnUq66Cb1jaJwUHBqqUurzJPJ7s8"></script>
  
  
<script type="text/javascript">//<![CDATA[
	
var vnombre = false;
var vdireccion = false;
var vtelefono = false;
	
$( "#nombre" ).blur(function() {
var nombre = $("#nombre").val();		



	 $(".error").remove();
	if(nombre == '' || nombre.length < 4){
	    $("#nombre").focus().after("<span class='error'>Ingrese su nombre</span>");
	    vnombre = false;
	}else{
		vnombre = true;
	};

});

$( "#direccion" ).blur(function() {
var inputDireccion = $("#direccion").val();	


$(".error").remove();
if(inputDireccion == ''){
    $("#direccion").focus().after("<span class='error'>Ingrese su direccion</span>");
};

if(inputDireccion!='' && inputDireccion!=' ' && inputDireccion!='  ' && inputDireccion!='   '){
	
	 var url = 'https://www.google.com/maps/embed/v1/place?key=AIzaSyA2mfgpfvZWDfry05R1F52Gj8V_zBGeYbo&q='+inputDireccion;',Buenos+Aires'
     $("#mapa iframe").attr('src', url);
     $('#mapa').css('visibility', 'visible');
     $('#ifra').css({'height':'300'});
// $('#ifra').css({'width':'100%','height':'300'});
// Instantiate a directions service.
var directionsService = new google.maps.DirectionsService;

// Create a map and center it on Manhattan.
var map = new google.maps.Map(document.getElementById('map'), {
  zoom: 13,
  center: {lat: 40.771, lng: -73.974}
});

// Create a renderer for directions and bind it to the map.
var directionsDisplay = new google.maps.DirectionsRenderer({map: map});

// Request route directions
directionsService.route({
  origin: inputDireccion + ', Buenos Aires',
  destination: 'Florencio Varela 1903, San Justo',
  travelMode: google.maps.TravelMode.DRIVING
}, function(response, status) {
  if (status === google.maps.DirectionsStatus.OK) {
    
    // Get first route duration
    var route = response.routes[0];
    var duration = 0;
    
    route.legs.forEach(function (leg) {
      // The leg duration in seconds.
      duration += leg.duration.value;
    });
    
    var horas = Math.floor(duration/3600);
    var minutos = Math.floor((duration-(horas*3600))/60);
    var segundos = Math.round(duration-(horas*3600)-(minutos*60)); 
    
    function conversor_horas(duration) {
        var horas = Math.floor(duration/3600);
        var minutos = Math.floor((duration-(horas*3600))/60);

     }
    if (horas > 0){
	    directionsDisplay.setDirections(response);
	    document.getElementById('duration').innerHTML = horas + ' horas';
	    document.getElementById('direerror').style.padding='0px 0px';
	    document.getElementById('direerror').innerHTML = '';
	    vdireccion = true;
    }else {
    	directionsDisplay.setDirections(response);
        document.getElementById('duration').innerHTML = minutos + ' minutos'; 	
        document.getElementById('direerror').style.padding='0px 0px';
        document.getElementById('direerror').innerHTML = '';
        vdireccion = true;
    }
  } else {
	  directionsDisplay.setDirections(response);
      document.getElementById('mapa').style.visibility='hidden';
      document.getElementById('ifra').style.height='0';
  	  document.getElementById('direerror').style.padding='6px 12px';
      document.getElementById('direerror').innerHTML = 'Direccion no valida';
      document.getElementById('duration').innerHTML = ''; 	
      vdireccion = false;
  }
});
}else{
	document.getElementById('duration').innerHTML = '';
	 document.getElementById('direerror').style.padding='0px 0px';
	document.getElementById('direerror').innerHTML = '';
	document.getElementById('mapa').style.visibility='hidden';
	document.getElementById('ifra').style.height='0';
	 vdireccion = false;
	}
});//]]> 


$( "#telefono" ).blur(function() {
var telefono = $("#telefono").val();		



	 $(".error").remove();
	if(telefono == '' || telefono.length < 9){
	    $("#telefono").focus().after("<span class='error'>Ingrese su telefono</span>");
	    vtelefono = false;
	}else{
		vtelefono = true;
	};

});

function validar(e) {
    
    
    
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla==8) return true; //Tecla de retroceso (para poder borrar)
    if (tecla==44) return true; //Coma ( En este caso para diferenciar los decimales )
    if (tecla==48) return true;
    if (tecla==49) return true;
    if (tecla==50) return true;
    if (tecla==51) return true;
    if (tecla==52) return true;
    if (tecla==53) return true;
    if (tecla==54) return true;
    if (tecla==55) return true;
    if (tecla==56) return true;
    if (tecla==57) return true;
    patron = /1/; //ver nota
    te = String.fromCharCode(tecla);
    return patron.test(te); 
} 

function valida_envia() {
    
	if (vnombre == true && vdireccion == true  && vtelefono == true){ 
		
		$("#tresp").remove();
		$("#dspbtn").focus().after('<button  id="trespue" type="button" class="btn btn-lg btn-primary btn-block" data-toggle="modal" data-target="#ModalCrear">Enviar Pedido</button>');
		$('#ModalCrear').modal('show');

   	}else{
   	 $(".error").remove();
   		$("#tresp").focus().after("<span class='error'>Primero complete los campos</span>");
   	} 
} 

function valida_noenvia() {
		
	$("#trespue").remove();
	$("#dspbtn").focus().after('<button id="tresp" type="button" class="btn btn-lg btn-primary btn-block"  onclick="valida_envia()">Enviar Pedido</button>');
	
} 


</script>  

</html>