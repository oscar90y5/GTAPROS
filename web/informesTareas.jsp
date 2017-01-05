<%@page import="dominio.Informetareas"%>
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
                    List<Informetareas> informes = (List<Informetareas>) request.getAttribute("informes");
                    if (informes == null || informes.size() == 0) {%>
                <h3>No existen informes pendientes de envio.</h3>
                <%} else {
                %>
                <table class="table columna_caja_principal" >
                    <tr><h1>Selecciona una actividad:</h1></tr>
                    <tr style="align-content: center">
                        <td><h4>Id informe</h4></td>
                        <td><h4>Semana</h4></td>
                    </tr>
                    <%
                        for (Informetareas i : informes) {
                    %>

                    <tr style="cursor:pointer" 
                        onclick="document.location.href = 'EnviarInforme?idInforme=<%=i.getId()%>'"
                        onmouseover="this.style.color = '#2B58CC';" onmouseout="this.style.color = '#4E4E4E';">
                        <td><%=i.getId()%></td>
                        <td><%=i.getSemana() %></td>
                    </tr>
                    <%}
                        }
                    %>
                </table>
                <form role="form" action="VolverMenu" method="POST">
                    <button type="submit" class="btn btn-primary" name="accion" value="Volver">Volver</button>
                </form>
            </div>
        </div>
    </body>
</html>