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
                <h3>Modifica un informe de tareas de la actividad id = <%=tareas.get(0).getIdActividad().getId()%> :</h3>
                <%
                    List<Informetareas> lista = new ArrayList<Informetareas>();
                    for (Tarea t : tareas) {
                        if (!lista.contains(t.getInformetareas())) {
                            lista.add(t.getInformetareas());
                        }
                    }
                %>
                <div class="caja_small">
                    <%
                        String error = null;
                        if ((error = (String) request.getAttribute("error")) != null) {
                    %><p style="color:red"><%=error%></p><%
                                 }
                    %>
                    <form role="form" action="CargarInformeModificar" method="post">
                        <label for="informeCombo">Selecciona un informe:</label>
                        <select class="form-control" 
                                id="informeCombo" 
                                name="informeCombo">
                            <option selected></option>
                            <%                                for (Informetareas i : lista) {
                            %>
                            <option>id = <%=i.getId()%> - <%=i.getSemanaPrettyPrinter()%></option>
                            <% } %>
                        </select>
                        <div class="box-footer text-right" style="margin-top: 10px">
                            <button type="submit" class="btn btn-primary" name="accion" value="Aceptar">Aceptar</button>
                            <button type="submit" class="btn btn-danger" name="accion" value="Cancelar">Cancelar</button>
                        </div>
                    </form>
                </div>
                <%
                    }
                %>
                <form role="form" action="VolverMenu" method="POST">
                    <button type="submit" class="btn btn-primary" name="accion" value="Volver">Volver</button>
                </form>
            </div>
        </div>
    </body>
</html>
