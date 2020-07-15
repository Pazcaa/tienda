<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

	<!-- Font Awesome -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
		<!--Nuestro CSS-->
	<link rel="stylesheet" href="css/style.css">
	
    <title>Buscador Tienda</title>
  </head>
  <body>
  
  <header>
  
	  <nav class="navbar navbar-expand-lg navbar-light">
	  <a class="navbar-brand" href="#"> <i class=" logo fa fa-shopping-bag " aria-hidden="true"></i></a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    
	    <!--Formulario Completo-->
	    <form class="form-inline my-2 my-lg-0" action="inicio" method= "get">
	    <div class="input-group">
			<div class="input-group-prepend">				    
			   <i class=" logo fa fa-search input-group-text" aria-hidden="true"></i>				    
			</div>
			   <input type="text" class="form-control form-name" name="nombre" value="${formulario.nombre}" placeholder="Nombre Producto" >
		</div> 	
			
		 	<input class="form-control mr-sm-2" type="hidden" name="pmin" value ="${formulario.pmin }" placeholder="Precio Minimo">
		 	<input class="form-control mr-sm-2" type="hidden" name="pmax" value ="${formulario.pmax }" placeholder="Precio Maximo">
			 	
			 	<!-- Button trigger modal -->
				<button type="button" class="submit ${ ('0' eq formulario.pmin && '0' eq formulario.pmax) ? '' : 'active' }" data-toggle="modal" data-target="#exampleModal">
				  Precio 
				  min= ${ ('0' eq formulario.pmin ) ? '0' : formulario.pmin }€
				  max= ${ ('0' eq formulario.pmax ) ? '0' : formulario.pmax }€
				</button>
			 	
		 	<select class="form-control mr-sm-2 ${ ('0' eq formulario.idFabricante) ? '' : 'active' } custom-select" name="fabricante">
		 		<option value="0">-- Seleccion Fabricante --</option>
		 		<c:forEach items="${fabricantes }" var="fabricantes">
		 		<option value="${fabricantes.id }" ${ (fabricantes.id eq formulario.idFabricante) ? "selected" : "" }> ${fabricantes.nombre }</option>
		 		</c:forEach>
		 	</select>
		 	<br>
		 	<input class="submit ${ ('' eq formulario.nombre && '0' eq formulario.idFabricante) ? '' : 'active' }" type="submit" value="Buscar">
	 	</form>
	 	
	 	
	 	 <!--Formulario Reset-->
	 	<form class="form-inline my-2 my-lg-0" action="inicio" method= "get">
		 	<input type="hidden" name="nombre" value ="" placeholder="Nombre Producto">
		 	<br>
		 	<input type="hidden" name="pmin" value ="0" placeholder="Precio Minimo">
		 	<br>
		 	<input type="hidden" name="pmax" value ="0" placeholder="Precio Maximo">
		 	<br>
		 	<input type="hidden" name="fabricante" value ="0" >
		 	<br>
		 	<input class="submit ${ ('' eq formulario.nombre && '0' eq formulario.idFabricante && '0' eq formulario.pmin && '0' eq formulario.pmax) ? 'active' : '' }" type="submit" value="Limpiar">
		 	
	 	</form>
		 
	    
	  </div>
	</nav>
	
  </header>
  
  <main class="container">
 
  ${mensaje }
  
  <div class="card-content">
	<c:forEach items="${productos }" var = "p">
		 <div class="card">
			<img src="https://via.placeholder.com/250x250" class="card-img-top img-precio" alt="...">
			<div class="card-body">
			    <h5  class=card-title"><b>${p.precio }€</b> </h5>
			    <h5 class="card-title">${p.nombre } ${p.fabricante.nombre }</h5>
			    <p class="card-text">${p.descripcion }</p>
		 	</div>
		</div>
	</c:forEach>

  </div>

</main>



<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Precio</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
           <form class="form my-2 my-lg-0" action="inicio" method= "get">
    
		 	<input class="form-control mr-sm-2" type="hidden" name="nombre" value ="${formulario.nombre }" placeholder="Nombre Producto">
		 	<div>
			 	<label for="pmin">Mínimo: </label>
			 	<input class="form-control mr-sm-2 form-modal" type="number" id="pmin" name="pmin"  value ="${formulario.pmin }" placeholder="Precio Minimo">
		 	</div>
		 	<div>
			 	<label for="pmax">Máximo: </label>
			 	<input class="form-control mr-sm-2 form-modal" type="number" id="pmax" name="pmax"  value ="${formulario.pmax }" placeholder="Precio Maximo">
		 	</div>
		 		<input type="hidden" name="fabricante" value ="${formulario.idFabricante}" >
	 		<div>
		 		<input class="submit active" type="submit" value="Buscar">
		 	</div>
 		</form>
      </div>
      <div class="modal-footer">
       
      </div>
    </div>
  </div>
</div>


<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
  </body>
</html>