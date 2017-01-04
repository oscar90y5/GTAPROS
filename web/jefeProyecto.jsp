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
                    if(error.equals("NoActividades")){
                %>
                <p style="color:red">No hay actividades para cerrar</p>
                <%}
                    if(error.equals("NoActividadesAbiertas")){ %>
                <p style="color:red">No hay actividades sin asignar</p>
                <%}}catch(NullPointerException e){ }
                %>
        <section class="container">
            <section class="login-form">
                <h1>Bienvenido <%=session.getAttribute("idUser")%></h1>
                <h2> Elija qué desea hacer: </h2>
                <div class="columna_caja_principal"> 
                    <form action="JefeProyecto" method="POST" style="margin-top: 20px;">
                        <input type="submit" name="accion" value="Cargar Plan de proyecto" class="btn btn-lg btn-primary btn-block"/>
                        <input type="submit" name="accion" value="Asignar personas a proyecto" class="btn btn-lg btn-primary btn-block"/>
                        <input type="submit" name="accion" value="Asignar personas a actividad" class="btn btn-lg btn-primary btn-block"/>
                        <input type="submit" name="accion" value="Fijar fin de actividad" class="btn btn-lg btn-primary btn-block"/>
                        <input type="submit" name="accion" value="Obtener informes" class="btn btn-lg btn-primary btn-block"/>
                        <input type="submit" name="accion" value="Consultar datos de actividad" class="btn btn-lg btn-primary btn-block"/>
                        <input type="submit" name="accion" value="Fijar vacaciones" class="btn btn-lg btn-primary btn-block"/>
                        <input type="submit" name="accion" value="Cerrar Sesion" class="btn btn-lg btn-warning btn-block"/>
                    </form>
                </div>
           </section>
        </section>
    </body>
</html>
