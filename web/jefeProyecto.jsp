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
        <section class="container">
            <section class="login-form">
            <h1>Bienvenido <%=session.getAttribute("idUser")%></h1>
            <h2> Eliga qué desea hacer: </h2>
            <form action="JefeProyecto" method="POST" style="margin-top: 20px;">
                <input type="submit" name="accion" value="Cargar Plan de proyecto" class="btn btn-lg btn-primary btn-block"/>
                <input type="submit" name="accion" value="Añadir personas a proyecto" class="btn btn-lg btn-primary btn-block"/>
                <input type="submit" name="accion" value="Añadir personas a actividad" class="btn btn-lg btn-primary btn-block"/>
                <input type="submit" name="accion" value="Fijar fin de actividad" class="btn btn-lg btn-primary btn-block"/>
                <input type="submit" name="accion" value="Obtener informes" class="btn btn-lg btn-primary btn-block"/>
                <input type="submit" name="accion" value="Consultar datos de actividad" class="btn btn-lg btn-primary btn-block"/>
                <input type="submit" name="accion" value="Fijar vacaciones" class="btn btn-lg btn-primary btn-block"/>
            </form>
           </section>
        </section>
    </body>
</html>
