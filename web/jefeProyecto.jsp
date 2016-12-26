<%-- 
    Document   : jefeProyecto
    Created on : 26-dic-2016, 9:06:50
    Author     : Rebeca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=1,initial-scale=1,user-scalable=1" />
        <title>JEFE DE PROYECTO INDEX</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
    </head>
    <body>
        <section class="col-md-10 col-md-offset-5">
            <h1>Bienvenido <%=session.getAttribute("idUser")%></h1>
            <h3>¿Qué quieres hacer?</h3>
            <section class="btn-group-vertical">
                <button type="button" name="estado" value="sinPlan" class="btn-block" onclick="/ProyectosServlet">Cargar Plan de proyecto</button>
                <button type="button" name="estado" value="enCurso" class="btn-block" onclick="/ProyectosServlet">Añadir personas a proyecto</button>
                <button type="button" name="estado" value="enCurso" class="btn-block" onclick="/ProyectosServlet">Añadir personas a actividad</button>
                <button type="button" class="btn-block" onclick="actividades.jsp">Fijar fin de actividad</button>
                <button type="button" name="estado" value="todos" class="btn-block" onclick="/ProyectosServlet">Obtener informes</button>
                <button type="button" name="estado" value="enCurso" class="btn-block" onclick="/ProyectosServlet">Consultar datos de actividad</button>
                <button type="button" class="btn-block" onclick="vacaciones.html">Fijar vacaciones</button>
            </section>
        </section>
    </body>
</html>
