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
           <button type="submit" id="aceptar" class="btn btn-primary" name="accion" value="Cargar" disabled="disabled()">Aceptar</button>
           <button type="submit" class="btn btn-danger" name="accion" value="Cancelar">Cancelar</button>
           <script>
               function disabled(){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
                String fecha1 = document.getElementById("fecha1").value;
                String fecha2 = document.getElementById("fecha2").value;
                Date fechaInicio = sdf.parse(fecha1);
                Date fechaFinal = sdf.parse(fecha2);
                Date dias = fechaFinal - fechaInicio;

                String idUser = (String) sesion.getAttribute("idUser");
                //find Usuario y getVacaciones()
                if(!fechaInicio.before(fechaFinal)
                        alert("La fecha Inicial es posterior a la final");
                //if(vacaciones<=28) alert("Ya tienes fijadas el maximo de vacaciones/año")
                //if(dias<14) alert("No puedes fijar vacaciones de más de 2 semanas de duración)
                //if(vacaciones+dias<=28) alert("No puedes fijar tantos días, excedes el máximo de vacaciones/año")
                document.getElementById("aceptar").disabled = false;
                }
            }
           </script>
       </div>
    </body>
</html>
