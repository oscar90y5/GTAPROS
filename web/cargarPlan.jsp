<%-- 
    Document   : cargarPlan
    Created on : 28-dic-2016, 9:30:22
    Author     : Rebeca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GTAPROS</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
    </head>
    <body>
        <section class="container">
            <section class="login-form">
            <h2> Cargar plan de proyecto: </h2>
            <div class="columna_caja_principal"> 
            <form role="form" action="CargarPlan" name="CargarPlan" method="post">
            <div class=>
                <div class="">
                    <input type="file" accept="text/plain" class="btn btn-lg btn-primary btn-block" id="plan" name="Plan de Proyecto">
                </div>
                <div class="box-footer">
                    <button type="submit" class="btn btn-primary" name="accion" value="Cargar">Aceptar</button>
                    <button type="submit" class="btn btn-danger" name="accion" value="Cancelar">Cancelar</button>
                </div>
            </div>
           </form>
            </div>
           </section>
        </section>
    </body>
</html>
