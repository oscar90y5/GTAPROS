<%-- 
    Document   : informes
    Created on : 29-dic-2016, 11:11:20
    Author     : Rebeca
--%>

<%@page import="dominio.Proyecto"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=1,initial-scale=1,user-scalable=1" />
        <title>GTAPROS</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
    </head>
    <body>
        <section class="container">
            <% 
            ObjectMapper mapper = new ObjectMapper();
            String json = (String) request.getAttribute("proyecto");
            String inProject = request.getParameter("in");
            %>
            <h1>Elija el informe que desea obtener:</h1>
            <div class="columna_caja_principal">
                <form action="GenerarInforme" method="POST" style="margin-top: 20px;">
                    <% if(json==null){%>
                    <p>No hay informes que mostrar</p>
                    <%}else{
                    Proyecto p = mapper.readValue(json, Proyecto.class);
                    if(inProject.equals("true")){
                    %>
                    <input type="hidden" name="proyecto" value="<%=p.getId()%>"/>
                    <input type="submit" name="informe" value="Trabajadores/Actividades por periodo semanal" class="btn btn-lg btn-primary btn-block"/>
                    <input type="submit" name="informe" value="Trabajadores/Informes pendientes de Envio" class="btn btn-lg btn-primary btn-block"/>
                    <input type="submit" name="informe" value="Trabajadores/Informes pendientes de Aprobacion" class="btn btn-lg btn-primary btn-block"/>
                    <input type="submit" name="informe" value="Tiempo real/planificado de actividades por periodo" class="btn btn-lg btn-primary btn-block"/>
                    <input type="submit" name="informe" value="Actividades con tiempo real mayor del esperado" class="btn btn-lg btn-primary btn-block"/>
                    <input type="submit" name="informe" value="Actividades/Recursos por periodo posterior" class="btn btn-lg btn-primary btn-block"/>
                    <input type="submit" name="informe" value="Trabajadores/Actividades/Tiempo por periodo posterior" class="btn btn-lg btn-primary btn-block"/>
                    <%}if(p.getEstado().equals("Finalizado")){
                    %>
                    <input type="submit" name="informe" value="Informe general" class="btn btn-lg btn-primary btn-block"/>
                    <%}}%>
                </form>
            </div>
       </section>
    </body>
</html>
