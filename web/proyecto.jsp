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
        <h1>Crear nuevo Proyecto</h1>
        <form role="form" action="proyecto" name="proyecto" value="crearProyecto" method="post">
         <div class=>
             <div class="">
                 <label for="nombreProyecto">Nombre del proyecto</label>
                 <input type="text" class="form-control" id="nombreProyecto" placeholder="Introduzca el nombre del proyecto" name="nombreProyecto">
             </div>
             <div>
                 <label>Fecha del proyecto:</label>
                 <div class="input-group">
                     <div class="input-group-addon">
                         <i class="fa fa-calendar"></i>
                     </div>
                     <input type="text" class="form-control pull-right" id="reservation" placeholder="Pulse para introducir la fecha de inicio y la fecha de fin" name="fechaInicioyFin">
                 </div>
             </div>
             <div>
                 <label>Responsable de Proyecto</label>
                 <select style="width: 100%;" name="jefe-proyecto">
                     
                 </select>
             </div>
             <div class="form-group">
                 <label>Numero de participantes</label>
                 <input type="number" name="numeroParticipantes">
             </div> <!-- /.box-header -->
         </div><!-- /.box-body -->
         <div class="box-footer">
             <% session.setAttribute("actualizar", true);%>

             <button type="submit" class="btn btn-primary" name="crearProyecto" value="crearProyecto" onclick="return validar()">Crear Proyecto</button>
             <a href="vistaProyectos.jsp"><span class="btn btn-default">Cancelar</span></a>
         </div>

        </form>
    </body>
</html>
