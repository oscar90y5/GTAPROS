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
            <div class="caja_principal">            
            <h2> Dar de alta nuevo Trabajador</h2>
            <form role="form" action="AltaTrabajador" name="AltaTrabajador" value="AltraTrabajador" method="post">
         <div class=>
             <div class="form-group">
                 <label for="nombreProyecto">Nombre del trabajador</label>
                 <input type="text" class="form-control input-lg" id="nombreTrabajador" placeholder="Introduzca el nombre del trabajador" name="nombreTrabajador">
             </div>
             <div class="form-group">
                 <label for="nombreProyecto">DNI del trabajador</label>
                 <input type="text" class="form-control input-lg" id="dniTrabajador" placeholder="Introduzca el dni del trabajador" name="dniTrabajador">
             </div>
             <div class="form-group">
                 <label for="nombreProyecto">Clave del trabajador</label>
                 <input type="password" class="form-control input-lg" id="claveTrabajador" placeholder="Introduzca la clave del trabajador" name="claveTrabajador">
             </div>
             <div class="form-group">
                 <label for="nombreProyecto">Categoría trabajador</label>
                 <select class="" id="categoriaTrabajador" name="categoriaTrabajador">
                     <option>1</option>
                     <option>2</option>
                     <option>3</option>
                     <option>4</option>
                 </select>
             </div>
         </div><!-- /.box-body -->
         <div class="box-footer text-right">
             <button type="submit"  class="btn btn-primary" name="altaTrabajadorBtn" value="addTrabajador">Añadir Trabajador</button>
             <button type="submit"  class="btn btn-danger" name="altaTrabajadorBtn" value="cancelAddTrabajador">Cancelar</button>
         </div>
        </form>
            </div>
        </section>
    </body>
</html>
