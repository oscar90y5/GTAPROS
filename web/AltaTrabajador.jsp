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
            <h2> Dar de alta nuevo Trabajador: </h2>
            <form role="form" action="AltaTrabajador" name="AltaTrabajador" value="AltraTrabajador" method="post">
         <div class=>
             <div class="">
                 <label for="nombreProyecto">Nombre del trabajador</label>
                 <input type="text" class="form-control" id="nombreTrabajador" placeholder="Introduzca el nombre del trabajador" name="nombreTrabajador">
             </div>
             <div class="">
                 <label for="nombreProyecto">DNI del trabajador</label>
                 <input type="text" class="form-control" id="dniTrabajador" placeholder="Introduzca el dni del trabajador" name="dniTrabajador">
             </div>
             <div class="">
                 <label for="nombreProyecto">Categoría trabajador</label>
                 <input type="text" class="form-control" id="categoriaTrabajador" placeholder="Introduzca la categoría del trabajador en la empresa" name="categoriaTrabajador">
             </div>
         </div><!-- /.box-body -->
         <div class="box-footer">
             <button type="submit" class="btn btn-primary" name="altaTrabajadorBtn" value="addTrabajador">Añadir Trabajador</button>
             <button type="submit" class="btn btn-primary" name="altaTrabajadorBtn" value="addTrabajadorCancel">Cancelar</button>
         </div>
        </form>
           </section>
        </section>
    </body>
</html>
