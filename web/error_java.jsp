<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>GTAPROS</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">


        <%@include file="WEB-INF/jspf/includes.jspf" %>
    </head>

    <body>
        <%@ page isErrorPage="true" %>
        <div class="container">
            <h1>Java Error</h1>
            <p>Sorry, Java has thrown an exception.</p>
            <p>To continue, click the Back button.</p>
            <br>
            <h2>Details</h2>
            <p>
                Type: <%= exception.getClass()%><br>
                Message: <%= exception.getMessage()%><br>
            </p>
        </div>
    </body>
</html>