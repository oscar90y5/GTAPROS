<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=1,initial-scale=1,user-scalable=1" />
        <title>GTAPROS</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
        <script type='text/javascript'>
            var counter = 1;
            var limit = 1000;

            function addRol(divName){
                 var newdiv;
                 var newInput;
                 if (counter <= limit)  {
                      newdiv = document.createElement('div');
                      newdiv.innerHTML = "Rol " + (counter + 1) + " y Nombre Rol"+ (counter + 1)+"   <br><input type='text' name='roles"+counter+"'><input type='text' name='nombre"+counter+"'>";
                      document.getElementById(divName).appendChild(newdiv);
                      counter++;
                 }
                
             
                 document.getElementById("cantidad").value=counter;
               
                 
            }
   </script>
    </head>
    <body>
      <section class="container">
            <section class="login-form">
             <h2> Dar de alta nuevo Proyecto: </h2>
            <form action="AltaProyecto" method="POST" style="margin-top: 20px;">
         
                 <input type="text" class="form-control" id="nombreProyecto" placeholder="Introduzca el nombre del proyecto" name="nombreProyecto">
             </div>
             <div class="">
                 <label for="nombreProyecto">Roles y categorías (Al menos debe asignar el Jefe de Proyecto)</label><br />
                 <div id="dynamicInput"><div>Rol 1 y Nombre Rol 1<br><input type="text" name="roles0"><input type="text" name="nombre0"></div></div>
                <input type="button" value="Añadir nuevo Rol" onClick="addRol('dynamicInput');">
             
             </div>
               <input type="hidden" id ="cantidad" name="cantidad" value="1" />
         </div><!-- /.box-body -->
 <div class="box-footer">
             <h2>¿Desea asignar ahora un jefe de proyecto? </h2>
             <button type="submit" class="btn btn-primary" name="altaProyectoBtn" value="asignarJefe">Añadir Jefe de Proyecto</button>
             <button type="submit" class="btn btn-primary" name="altaProyectoBtn" value="asignarJefeLater">Más tarde</button>
             <button type="submit" class="btn btn-primary" name="altaProyectoBtn" value="cancelar">Cancelar</button>
         </div>
            </form>
    </body>
</html>
