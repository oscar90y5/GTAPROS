
<%@page import="dominio.Proyecto"%>
<%@page import="java.util.ArrayList"%>
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
        <section class="caja_principal container">
            <div class="columna_caja_principal col-md-3">
                <h2><i>Opciones:</i></h2>
                <form action="Desarrollador" method="POST" style="margin-top: 20px;">
                    <input type="submit" name="accion" value="Introducir tarea" class="btn btn-lg btn-primary btn-block"/>
                    <input type="submit" name="accion" value="Modificar tareas activas" class="btn btn-lg btn-primary btn-block"/>
                    <input type="submit" name="accion" value="Consultar datos de tareas" class="btn btn-lg btn-primary btn-block"/>
                    <input type="submit" name="accion" value="Obtener informes" class="btn btn-lg btn-primary btn-block"/>
                </form>
                
            </div>
        </section>
    </body>
</html>

