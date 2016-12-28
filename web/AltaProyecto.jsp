<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=1,initial-scale=1,user-scalable=1" />
        <title>GTAPROS</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
    </head>
    <body>
       
        <section class="container">
            <section class="login-form">
            <h2> Dar de alta nuevo Proyecto: </h2>
            <form role="form" action="AltaProyecto" name="AltaProyecto" value="AltaProyecto" method="post">
         <div class=>
             <div class="">
                 <label for="nombreProyecto">Nombre del nuevo proyecto:</label>
                 <input type="text" class="form-control" id="nombreProyecto" placeholder="Introduzca el nombre del trabajador" name="nombreProyecto">
             </div>
             <div class="">
                 <label for="nombreProyecto">Roles y categorías</label>
                 <input type="text" class="form-control" id="dniTrabajador" placeholder="Introduzca el dni del trabajador" name="dniTrabajador">
             </div>
         </div><!-- /.box-body -->
         <div class="box-footer">
             <h2>¿Desea asignar ahora un jefe de proyecto? </h2>
             <button type="submit" class="btn btn-primary" name="altaProyectoBtn" value="asignarJefe">Añadir Jefe de Proyecto</button>
             <button type="submit" class="btn btn-primary" name="altaProyectoBtn" value="asignarJefeLater">Más tarde</button>
             <button type="submit" class="btn btn-primary" name="altaProyectoBtn" value="cancelar">Cancelar</button>
         </div>
        </form>
           </section>
        </section>
    </body>
</html>
