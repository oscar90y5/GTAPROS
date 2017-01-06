<%@page import="dominio.Tarea"%>
<%@page import="dominio.Informetareas"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=1,initial-scale=1,user-scalable=1" />
        <title>GTAPROS</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
        <%  List<Informetareas> informes = (List<Informetareas>) request.getAttribute("informes"); 
            List<List<Tarea>> tareasDeInformes = (List<List<Tarea>>) request.getAttribute("tareas"); %>
    </head>
    <body>
        <div class="container">
            <div class="caja_principal">
                <% if(informes == null || informes.size() == 0){ %>
                    <h3> No hay informes pendientes de revision</h3>
                    <form role="form" action='VolverMenu' method="post">
                        <div class="box-footer text-right" style="margin-top: 10px">
                            <button type="submit" class="btn btn-primary" name="accion" value="Volver">Volver</button>
                        </div>
                    </form>
                <% } else { %>
                    <h1> Informes pendientes de revision:</h1>
                    <%for(int i=0; i<informes.size(); i++){ 
                        Informetareas informe = informes.get(i); 
                        List<Tarea> tareas = tareasDeInformes.get(i); 
                    %>
                        <div class="caja_small">
                            <h2>Informe: <%=informe.getId()%></h2>
                            <h4 style="text-align:left;float:left;">Actividad: <%=tareas.get(0).getIdActividad().getNombre() %></h4><h4 style="text-align:right;float:right;">Id actividad: <%=tareas.get(0).getIdActividad().getId() %></h4>
                            <hr style="clear:both;"/>
                            <h4 style="text-align:left;float:left;"><%=informe.getSemanaPrettyPrinter() %></h4><h4 style="text-align:right;float:right;">Fecha envio: <%=informe.getFechaEnvioPrettyPrinter() %></h4>
                            <hr style="clear:both;"/>
                            <h3>Tareas:</h3>
                            <%for(int j=0; j<tareas.size(); j++){ 
                                Tarea tarea = tareas.get(j); 
                            %>
                                <h4><%=tarea.getTareaPK().getTipo()%>:  <%=tarea.getEsfuerzoReal()%> horas/hombre </h4>
                            <% } %>
                            <form role="form" action='RevisarInformes?idInforme=<%=informe.getId()%>' method="post">
                                <div class="box-footer text-right" style="margin-top: 10px">
                                    <button type="submit" class="btn btn-primary" name="accion" value="Aceptar">Aceptar</button>
                                    <button type="submit" class="btn btn-danger" name="accion" value="Rechazar">Rechazar</button>
                                </div>
                            </form>
                        </div>
                    <% } %>  
                    <form role="form" action='RevisarInformes' method="post">
                            <div class="box-footer text-right" style="margin-top: 10px">
                                <button type="submit" class="btn btn-danger" name="accion" value="Cancelar">Cancelar</button>
                            </div>
                    </form>
                <% } %>
            </div>
        </div>
    </body>
</html>