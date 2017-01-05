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
         <% 
                String error = null;
                try{
                    error = (String) request.getParameter("error");
                    if(error.equals("CierreInformes")){
                %>
                <p style="color:red">Debe cerrar todos los informes de las tareas para poder cerrar la actividad</p>
                <%}}catch(NullPointerException e){ }
                %>
        <section class="container">
            <div class="caja_principal2">
                <div class="caja_small2">
                    <h1>Bienvenido <%=session.getAttribute("idUser")%></h1>
                    <div class="columna_caja_principal">  
                        <h2> Elija qu� desea hacer: </h2>
                        <form action="Administrador" method="POST" style="margin-top: 20px;">
                            <input type="submit" name="accion" value="Dar de Alta Trabajador" class="btn btn-lg btn-primary btn-block"/>
                            <input type="submit" name="accion" value="Dar de Alta Proyecto" class="btn btn-lg btn-primary btn-block"/>
                            <input type="submit" name="accion" value="Asignar Responsable" class="btn btn-lg btn-primary btn-block"/>
                            <input type="submit" name="accion" value="Fijar vacaciones" class="btn btn-lg btn-primary btn-block"/>
                            <input type="submit" name="accion" value="Cerrar Sesion" class="btn btn-lg btn-warning btn-block"/>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
