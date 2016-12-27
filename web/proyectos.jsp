<%-- 
    Document   : proyectos
    Created on : 26-dic-2016, 10:51:06
    Author     : Rebeca
--%>

<%@page import="com.fasterxml.jackson.core.type.TypeReference"%>
<%@page import="java.util.List"%>
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
        String json = request.getParameter("proyectos");
        List<Proyecto> proyects = mapper.readValue(json, new TypeReference<List<Proyecto>>(){});
        String accion = (String) request.getAttribute("accion");
        %>
        <h1>Proyectos disponibles:</h1>
        <table class="table" >
            <% for(Proyecto p:proyects){%>
            <tr>
            <a href="activities.jsp">
                <td><%=p.getId()%></td>
                <td><%=p.getNombre()%></td>
                <td><%=p.getEstado()%></td>
            </a>
            </tr>
            <%}%>
        </table>
    </body>
</html>
