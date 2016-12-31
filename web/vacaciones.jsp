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
        <div class="container">
            <div class="caja_principal">
            <h2>Elija la fecha de vacaciones:</h2>
            <div class="caja_small">
                <form role="form" action="FijarVacaciones" name="FijarVacaciones" method="POST">
                    <%
                    try{
                        String error = request.getParameter("error");
                        if(error.equals("fechaPasada")){%>
                        <p style="color:red">La fecha Inicial ya ha pasado</p> 
                        <%}if(error.equals("fechaAnterior")){%>
                        <p style="color:red">La fecha Inicial es posterior a la final</p>  
                        <%}if(error.equals("maxFijadas")){%>
                        <p style="color:red">Ya tienes fijadas el maximo de vacaciones/año</p>  
                        <%}if(error.equals("maxDias")){%>
                        <p style="color:red">No puedes fijar vacaciones de más de 2 semanas de duración</p> 
                        <%}if(error.equals("excedeDias")){%>
                        <p style="color:red">No puedes fijar tantos días, excedes el máximo de vacaciones/año</p> 
                        <%}
                    }catch(NullPointerException e){ }
                    %>
                    <div class="form-group">
                        <input type="text" id="fecha1" name="fecha1" class="form-control"/>
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
                    </div>
                     <div class="form-group">
                        <input type="text" id="fecha2" name="fecha2" class="form-control"/>
                        <button id="fechaFin"> Seleccione fecha fin </button>
                        <script type="text/javascript">
                           Calendar.setup({
                             inputField: "fecha2",
                             ifFormat:   "%d/%m/%Y",
                             weekNumbers: false,
                             displayArea: "fechaFin",
                             daFormat:    "%A, %d de %B de %Y"
                           });
                        </script>
                    </div>
                    <button type="submit" class="btn btn-primary" name="accion" value="Aceptar">Aceptar</button>
                    <button type="submit" class="btn btn-danger" name="accion" value="Cancelar">Cancelar</button>
                </form>
            </div>
       </div>
    </body>
</html>
