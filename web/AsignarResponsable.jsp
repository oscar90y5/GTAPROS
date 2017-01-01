<%@page import="dominio.Proyecto"%>
<%@page import="java.util.List"%>
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
            <h2> Asignar Responsable: </h2>
            <form role="form" action="AsignarResponsableLater" name="AsignarResponsable" value="AsignarResponsable" method="post">
         <div class=>
             <div class="">
                 <label for="nombreProyecto">Seleccione el proyecto al que desea asignar responsable:</label>
                   
                   <input type="text" class="form-control" id="nombreProyecto" placeholder="Nombre proyecto" name="nombreProyecto">
             </div>
                      <div class="">
                 <label for="nombreProyecto">Seleccione el usuario que desea que sea jefe de proyecto:</label>
                     
                   <input type="text" class="form-control" id="jefeProyecto" placeholder="Nombre del Jefe del Proyecto" name="jefeProyecto">
             </div>
         </div><!-- /.box-body -->
         <div class="box-footer">
             <button type="submit" class="btn btn-primary" name="asignarJefe" value="asignarJefe">Asignar Jefe de Proyecto</button>
             <a href=><span class="btn btn-default">Cancelar</span></a>
         </div>
        </form>
           </section>
        </section>
    </body>
</html>