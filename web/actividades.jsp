<%@page import="dominio.Tarea"%>
<%@page import="dominio.Rol"%>
<%@page import="dominio.Actividad"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GTAPROS</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
        <link rel="stylesheet" type="text/css" media="all" href="bootstrap/css/calendar-estilo.css" />
        <script type="text/javascript" src="bootstrap/js/calendar.js"></script>
        <script type="text/javascript" src="bootstrap/js/calendar-es.js"></script>
        <script type="text/javascript" src="bootstrap/js/calendar-setup.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="caja_principal">
                <%
                List<Actividad> actividades = (List<Actividad>) request.getAttribute("actividades");
                if (actividades == null || actividades.isEmpty()) {%>
                <h3>No existen actividades relevantes para la accion que usted quiere realizar.</h3>
                <%} else {
                %>
                <table class="table columna_caja_principal" >
                    <tr><h1>Selecciona una actividad:</h1></tr>
                    <tr style="align-content: center">
                        <td><h4>Id</h4></td>
                        <td><h4>Nombre</h4></td>
                        <td><h4>Rol</h4></td>
                        <td><h4>Estado</h4></td>
                        <td><h4>Duracion (horas/hombre)</h4></td>
                        <td><h4>Fecha de inicio</h4></td>
                        <td><h4>Fecha de fin</h4></td>
                        <td><h4>Descripcion</h4></td>
                    </tr>
                    <%
                        String destino = (String) request.getAttribute("destino");
                        for (Actividad a : actividades) {
                    %>

                    <tr style="cursor:pointer" 
                        onclick="document.location.href = '<%= destino%>?idActividad=<%=a.getId()%>'"
                        onmouseover="this.style.color = '#2B58CC';" onmouseout="this.style.color = '#4E4E4E';">
                        <td><%=a.getId()%></td>
                        <td><%=a.getNombre()%></td>
                        <td><%=a.getIdRol().getNombreRol()%></td>
                        <td><%=a.getEstado()%></td>
                        <td><%=a.getDuracion()%></td>
                        <td><%=a.getFechaInicioPrettyString()%></td>
                        <td><%=a.getFechaFinPrettyString()%></td>
                        <td><%=a.getDescripcion()%></td>
                    </tr>
                    <%}}%>
                </table>
                <form role="form" action="VolverMenu" method="POST">
                    <button type="submit" class="btn btn-primary" name="accion" value="Volver">Volver</button>
                </form>
            </div>
        </div>
    </body>
</html>
