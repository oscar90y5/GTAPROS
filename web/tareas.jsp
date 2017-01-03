<%@page import="java.util.ArrayList"%>
<%@page import="dominio.Informetareas"%>
<%@page import="dominio.Tarea"%>
<%@page import="dominio.Rol"%>
<%@page import="dominio.Actividad"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GTAPROS</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
        <link rel="stylesheet" type="text/css" media="all" href="bootstrap/css/calendar-estilo.css" />
        <script type="text/javascript" src="bootstrap/js/calendar.js"></script>
        <script type="text/javascript" src="bootstrap/js/calendar-es.js"></script>
        <script type="text/javascript" src="bootstrap/js/calendar-setup.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="caja_principal">
                <%
                    List<Tarea> tareas = (List<Tarea>) request.getAttribute("tareas");
                    if (tareas == null) {%>
                No existen tareas en este proyecto.
                <%} else {
                    List<Informetareas> lista = new ArrayList<Informetareas>();
                    for (Tarea t : tareas) {
                        if (!lista.contains(t.getIdInforme())) {
                            lista.add(t.getIdInforme());
                        }
                    }

                    for (Informetareas i : lista) {
                        //Mostrar info informe
%>
                <h3><i>Informe id=<%=i.getId()%> Semana: <%=i.getSemana()%></i></h3>
                Estado: <%=i.getEstado()%>  Fecha de envio: <%=i.getFechaEnvio()%> 
                <%
                    for (Tarea t : tareas) {
                        if (i.getId().equals(t.getIdInforme().getId())) {
                            //Mostrar tareas                             
%>
                <div>
                    <p><%=t.getTareaPK().getTipo()%></p>
                    <p><%=t.getEsfuerzoReal()%></p>
                </div>
                <%          }
                            }
                        }
                    }
                %>
            </div>
        </div>
    </body>
</html>
