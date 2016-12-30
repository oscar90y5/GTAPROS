<%@page import="com.fasterxml.jackson.core.type.TypeReference"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="dominio.Miembro"%>
<%@page import="java.util.List"%>

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
        <section class="container">
            <section class="login-form">
            <h1>Bienvenido <%=session.getAttribute("idUser")%></h1>
            <h2> Elija con qué rol desea trabajar: </h2>
            <form action="MisProyectos" method="POST" style="margin-top: 20px;">
                <% 
                ObjectMapper mapper = new ObjectMapper();
                String json = (String) request.getAttribute("misProjects");
                List<Miembro> misProyects = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, Miembro.class));
                for(Miembro m: misProyects){
                %>
                <input type="submit" name="accion" value="<%=m.getIdRol().getNombreRol()%>, Proyecto:<%=m.getIdProyecto().getId()%>" class="btn btn-lg btn-primary btn-block" />
                <%}%>
            </form>
           </section>
        </section>
    </body>
</html>