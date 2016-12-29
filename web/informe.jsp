<%@page import="java.util.HashMap"%>
<%@page import="dominio.Tarea"%>
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
        <section class="container">
        <% 
        ObjectMapper mapper = new ObjectMapper();
        String json = (String) request.getAttribute("datos");
        String estado = request.getParameter("estado");
        %>
        <table class="table columna_caja_principal" >
            <tr><%if(estado.equals("pendienteEnvio")){%>
                <h1>Relacion Trabajadores/Actividades pendientes de envio:</h1>
                <%}if(estado.equals("pendienteAprob")){%>
                <h1>Relacion Trabajadores/Actividades pendientes de aprobacion</h1>
                <%}%>
            </tr>
            <tr>
                <td><h4>Dni</h4></td>
                <td><h4>Id Actividad</h4></td>
                <td><h4>Tipo Actividad</h4></td>
            </tr>
            <% if(json==null){
                    if(estado.equals("pendienteEnvio")){%>
                    <tr>No existen informes pendientes de envio</tr>
                    <%}if(estado.equals("pendienteAprob")){%>
                    <tr>No existen informes pendientes de aprobacion</tr>
            <%}}else{
                List<Tarea> datos = mapper.readValue(json, new TypeReference<List<Tarea>>(){});
                for(Tarea t: datos){
            %>
            <tr>
                <td><%=t.getMiembro().getDni().getDni()%></td>
                <td><%=t.getTareaPK().getIdActividad()%></td>
                <td><%=t.getTareaPK().getTipo()%></td>
            </tr>
            <%}}%>
        </table>
    </body>
</html>
