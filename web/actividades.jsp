<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
<%@page import="com.fasterxml.jackson.core.type.TypeReference"%>
<%@page import="java.util.List"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="dominio.Actividad"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GTAPROS</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
    </head>
    <body>
        <section class="container">
        <% 
        ObjectMapper mapper = new ObjectMapper();
        String json = (String) request.getAttribute("actividades");
        %>
        <table class="table columna_caja_principal" >
            <tr><h1>Actividades disponibles:</h1></tr>
            <tr>
                <td><h4>Id</h4></td>
                <td><h4>Descripcion</h4></td>
            </tr>
            <% if(json==null){%>
            <tr>No existen proyectos disponibles</tr>
            <%}else{
                List<Actividad> actividades = mapper.readValue(json, new TypeReference<List<Actividad>>(){});
                for(Actividad a : actividades){
            %>
            <tr style="cursor:pointer" onclick="document.location.href='introducirTareas.jsp?id=<%=a.getId()%>'"
                onmouseover="this.style.color='#2B58CC';" onmouseout="this.style.color='#4E4E4E';">
                    <td><%=a.getId()%></td>
                    <td><%=a.getDescripcion()%></td>
            </tr>
            <%}}%>
        </table>
    </body>
</html>