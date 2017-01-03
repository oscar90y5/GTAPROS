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
                    if (tareas == null || tareas.size() == 0) {%>
                No existen tareas de la actividad seleccionada.
                <%} else {

                %>
                <h3>Modifica un informe de tareas de la actividad id = <%=tareas.get(0).getActividad().getId()%> :</h3>
                <%
                    List<Informetareas> lista = new ArrayList<Informetareas>();
                    for (Tarea t : tareas) {
                        if (!lista.contains(t.getIdInforme())) {
                            lista.add(t.getIdInforme());
                        }
                    }
                %>
                <div class="caja_small">
                    <form role="form" action="ModificarInforme" enctype="multipart/form-data" method="post">
                        <label for="informeCombo">Selecciona un informe:</label>
                        <select class="form-control" 
                                id="informeCombo" 
                                name="informeCombo">
                            <option selected></option>
                            <%
                                for (Informetareas i : lista) {
                            %>
                            <option>id = <%=i.getId()%> - <%=i.getSemanaEnvioPrettyPrinter()%></option>
                            <% } %>
                        </select>
                        <input type="hidden" value="2" name="oculto" id="oculto"/>
                        <BR>
                        <button type="submit" class="btn btn-primary" name="accion" value="Cargar">Aceptar</button>
                        <button type="submit" class="btn btn-danger" name="accion" value="Cancelar">Cancelar</button>
                    </form>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </body>
</html>
