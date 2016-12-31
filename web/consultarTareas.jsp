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
                <table class="table columna_caja_principal" >
                    <tr><h1>Proyectos disponibles:</h1></tr>
                    <tr>
                        <td><h4>Id</h4></td>
                        <td><h4>Nombre</h4></td>
                        <td><h4>Estado</h4></td>
                    </tr>
                    <% 
                        List<String> tareas= (List<String>) request.getAttribute("tareas");
                        if (tareas == null) {%>
                    <tr>No existen proyectos disponibles</tr>
                    <%} else {
                     
                    %>
                    <tr style="cursor:pointer" onclick="document.location.href = 'ObtenerInformes?id='"
                        onmouseover="this.style.color = '#2B58CC';" onmouseout="this.style.color = '#4E4E4E';">
                        <td>hola1</td>
                        <td>hola2</td>
                        <td>hola3</td>
                    </tr>
                    <%}
                %>
                </table>
            </div>
        </div>
    </body>
</html>
