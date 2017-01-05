<%@page import="dominio.Usuario"%>
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
                <table class="table columna_caja_principal" >
                    <tr><h1>Usuarios Disponibles:</h1></tr>
                    <tr style="align-content: center">
                        <td><h4>Nombre</h4></td>
                        <td><h4>DNI</h4></td>
                        <td><h4>Tipo Categoria</h4></td>
                    </tr>
                    <%
                        List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuariosDisponibles");
                        if (usuarios == null) {%>
                    <tr>No existen usuarios disponibles.</tr>
                    <%} else {
                        String destino = (String) request.getAttribute("destino");
                        for (Usuario u : usuarios) {
                            
                    
                    %>
                    <tr style="cursor:pointer" 
                        onclick="document.location.href ='<%= destino%>?UsuarioDni=<%=u.getDni()%>'"
                        onmouseover="this.style.color = '#2B58CC';" onmouseout="this.style.color = '#4E4E4E';">
                        <td><%=u.getNombreCompleto()%></td>
                        <td><%=u.getDni()%></td>
                        <td><%=u.getTipoCategoria()%></td>
                    </tr>
                    <%}
                        }
                    %>
                </table>
            </div>
        </div>
    </body>
</html>
