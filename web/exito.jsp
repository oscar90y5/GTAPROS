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
            <h2>La operación se ha realizado con éxito</h2>
            <div class="columna_caja_principal"> 
            <form role="form" action="index.jsp" method="post">
                <button type="submit" class="btn btn-primary" name="accion" value="Aceptar">Aceptar</button>
           </form>
            </div>
           </section>
        </section>
    </body>
</html>
