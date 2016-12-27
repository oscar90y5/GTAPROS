<%-- 
    Document   : vacaciones
    Created on : 26-dic-2016, 12:40:52
    Author     : Rebeca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fijar vacaciones</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
        <link rel="stylesheet" type="text/css" media="all" href="bootstrap/css/calendar-estilo.css" />
        <script type="text/javascript" src="bootstrap/js/calendar.js"></script>
        <script type="text/javascript" src="bootstrap/js/calendar-es.js"></script>
        <script type="text/javascript" src="bootstrap/js/calendar-setup.js"></script>
    </head>

    <body>
        <div>
            <h1>Elija la fecha de vacaciones:</h1>
            <input type="hidden" name="fecha1" id="fecha1" />
            <input type="hidden" name="fecha2" id="fecha2" />
            <button id="fechaIni"> Seleccione fecha inicio </button>
            <script type="text/javascript">
               Calendar.setup({
                 inputField: "fecha1",
                 ifFormat:   "%d/%m/%Y",
                 weekNumbers: false,
                 displayArea: "fechaIni",
                 daFormat:    "%A, %d de %B de %Y"
               });
            </script>
            <p><button id="fechaFin"> Seleccione fecha fin </button>
               <script type="text/javascript">
                   Calendar.setup({
                     inputField: "fecha2",
                     ifFormat:   "%d/%m/%Y",
                     weekNumbers: false,
                     displayArea: "fechaFin",
                     daFormat:    "%A, %d de %B de %Y"
                   });
               </script>
           </p>
           <p><button type="button" id="aceptar" disabled="true" >Aceptar</button></p>
       </div>
    </body>
</html>
