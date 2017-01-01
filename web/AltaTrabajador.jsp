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
            <h2> Dar de alta nuevo Trabajador</h2>
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
                 <label for="nombreProyecto">Clave del trabajador</label>
                 <input type="password" class="form-control" id="claveTrabajador" placeholder="Introduzca la clave del trabajador" name="claveTrabajador">
             </div>
             <div class="">
                 <label for="nombreProyecto">Categoría trabajador</label>
                 <select class="" id="categoriaTrabajador" name="categoriaTrabajador">
                     <option>1-JefeProyecto</option>
                     <option>2-Analista</option>
                     <option>3-Diseñador</option>
                     <option>3-AnalistaProgramador</option>
                     <option>3-ResponsablePruebas</option>
                     <option>4-Programador</option>
                     <option>4-Probrador</option>
                 </select>
             </div>
         </div><!-- /.box-body -->
         <div class="box-footer">
             <button type="submit" class="btn btn-primary" name="altaTrabajadorBtn" value="addTrabajador">Añadir Trabajador</button>
             <button type="submit" class="btn btn-primary" name="altaTrabajadorBtn" value="cancelAddTrabajador">Cancelar</button>
         </div>
        </form>
           </section>
        </section>
    </body>
</html>
