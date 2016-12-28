<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@page import="com.fasterxml.jackson.core.type.TypeReference"%>
<%@page import="java.util.List"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="dominio.Proyecto"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GTAPROS</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
    </head>
    <body>
        <% 
        ObjectMapper mapper = new ObjectMapper();
        String json = request.getParameter("proyectos");
        List<Proyecto> proyects = mapper.readValue(json, new TypeReference<List<Proyecto>>(){});
        %>
        <h1>Proyectos disponibles:</h1>
        <table class="table" >
            <% for(Proyecto p:proyects){%>
            <tr>
            <a href="informe.jsp">
                <td><%=p.getId()%></td>
                <td><%=p.getNombre()%></td>
                <td><%=p.getEstado()%></td>
            </a>
            </tr>
            <%}%>
        </table>
    </body>
</html>
