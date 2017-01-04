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
                <h3>Tareas de la actividad id = <%=tareas.get(0).getIdActividad().getId()%></h3>
                <%
                    List<Informetareas> lista = new ArrayList<Informetareas>();
                    for (Tarea t : tareas) {
                        if (!lista.contains(t.getInformetareas())) {
                            lista.add(t.getInformetareas());
                        }
                    }

                    for (Informetareas i : lista) {
                        //Mostrar info informe
                %>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">Tareas del informe  id = <%=i.getId()%>
                            <div class="right" style="text-align: right"><%=i.getSemanaEnvioPrettyPrinter()%></div></h3>
                    </div>
                    <div class="panel-body">                           
                        <table class="table table-rol">
                            <tr style="align-content: center" class="text-center">
                                <td><b>Tipo de tarea personal</b></td>
                                <td><b>Duracion (Horas Hombre)</b></td>
                            </tr>
                            <%
                                for (Tarea t : tareas) {
                                    if (i.getId().equals(t.getInformetareas().getId())) {
                                        //Mostrar tareas                             
%>
                            <tr>
                                <td><%=t.getTareaPK().getTipo()%></td>
                                <td class="text-center"><%=t.getEsfuerzoReal()%></td>
                            </tr>
                            <%          }
                                }
                            %>
                        </table>
                    </div>
                    <div class="panel-footer">
                        Estado: <%=i.getEstado()%>   <BR/>
                        Fecha de envio: <%=i.getFechaEnvioPrettyPrinter()%>
                    </div>
                </div>
                <%
                        }
                    }
                %>
                <form role="form" action="VolverMenu" method="POST">
                    <button type="submit" class="btn btn-primary" name="accion" value="Volver">Volver</button>
                </form>
            </div>
        </div>
    </body>
</html>
