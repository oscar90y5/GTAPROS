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
                    if (tareas == null || tareas.size() == 0) {
                %>
                No existe el informe de tareas seleccionado.
                <%} else {
                    Informetareas informe = tareas.get(0).getInformetareas();
                %>
                <h3>Informe de la actividad id = <%=tareas.get(0).getIdActividad().getId()%></h3>
                <form role="form" action='ModificarInforme' method="post">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Informe id = <%=informe.getId()%>
                                <div class="right" style="text-align: right"><%=informe.getSemanaEnvioPrettyPrinter()%></div></h3>
                        </div>
                        <input type="hidden" name="idInforme" id="idInforme" value="<%=informe.getId()%>"/>
                               <div class="panel-body">                           
                               <table class="table table-rol">
                            <tr style="align-content: center" class="text-center">
                                <td><b>Tipo de tarea personal</b></td>
                                <td><b>Duracion (Horas Hombre)</b></td>
                            </tr>
                            <%
                                String[] nombres = {"TratoConUsuarios", "ReunionesInternasExternas",
                                    "LecturaRevisionDocumentacion", "ElaboracionDocumentacion",
                                    "DesarrolloVerificacionProgramas", "FormacionUsuariosYOtros"};
                                Integer[] duracion = {0, 0, 0, 0, 0, 0};
                                for (int i = 0; i < nombres.length; i++) {
                                    for (Tarea t : tareas) {
                                        if (nombres[i].equalsIgnoreCase(t.getTareaPK().getTipo())) {
                                            duracion[i] = t.getEsfuerzoReal();
                                        }
                                    }
                                }
                                for (int i = 0; i < nombres.length; i++) {//Mostrar tareas
                            %>
                            <tr>
                                <td><%=nombres[i]%></td>
                                <td class="text-center"><input name="<%=nombres[i]%>" id="<%=nombres[i]%>" type="number" size="5" value="<%=duracion[i]%>" min="0"/></td>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </div>
                    <div class="panel-footer">
                        Estado: <%=informe.getEstado()%>   <BR/>
                        Fecha de envio: <%=informe.getFechaEnvioPrettyPrinter()%>
                    </div>
            </div>
            <div class="box-footer text-right" style="margin-top: 10px">
                <button type="submit" class="btn btn-primary" name="accion" value="Aceptar">Aceptar</button>
                <button type="submit" class="btn btn-danger" name="accion" value="Cancelar">Cancelar</button>
            </div>
        </form>
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
