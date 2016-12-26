<%-- 
    Document   : proyectos
    Created on : 26-dic-2016, 10:51:06
    Author     : Rebeca
--%>

<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dominio.Proyecto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proyectos</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
    </head>
    <body>
        <% 
        ObjectMapper mapper = new ObjectMapper();
        String jsonProyects = request.getParameter("proyectos");
        ArrayList<Proyecto> proyects = mapper.readValue(jsonProyects,
                 mapper.getTypeFactory().constructCollectionType(ArrayList.class, Proyecto.class));
        %>
        <h1>Proyectos disponibles:</h1>
        <table class="table" >
            <%for(Proyecto p:proyects){%>
            <tr>
                <td><%=p.getId()%></td>
                <td><%=p.getNombre()%></td>
                <td><%=p.getEstado()%></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
