<%-- 
    Document   : desarrollador
    Created on : 28-dic-2016, 10:54:20
    Author     : oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=1,initial-scale=1,user-scalable=1" />
        <title>DESARROLLADOR INDEX</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
    </head>
    <body>
        <section class="container">
            <section class="login-form">
            <h1>Bienvenido <%=session.getAttribute("idUser")%></h1>
            <h2> Eliga qué desea hacer: </h2>
            <form action="Desarrollador" method="POST" style="margin-top: 20px;">
                <input type="submit" name="accion" value="Introducir datos de tareas" class="btn btn-lg btn-primary btn-block"/>
                <input type="submit" name="accion" value="Modificar datos de tareas" class="btn btn-lg btn-primary btn-block"/>
                <input type="submit" name="accion" value="Consultar datos de tareas" class="btn btn-lg btn-primary btn-block"/>
                <input type="submit" name="accion" value="Obtener informes" class="btn btn-lg btn-primary btn-block"/>
            </form>
           </section>
        </section>
    </body>
</html>

