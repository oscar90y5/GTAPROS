<%-- 
    Document   : informeSemana
    Created on : 31-dic-2016, 10:58:06
    Author     : Rebeca
--%>

<%@page import="dominio.Tarea"%>
<%@page import="dominio.Informetareas"%>
<%@page import="dominio.Actividad"%>
<%@page import="com.fasterxml.jackson.core.type.TypeReference"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.util.List"%>
<%@page import="dominio.Miembro"%>
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
            <%HttpSession sesion = request.getSession();
            String vista = (String) sesion.getAttribute("vista");%>
            <div class="caja_principal">
                <%if(vista.equals("jefeProyecto.jsp")){%>
                <h2>Relacion Trabajadores/Actividades por periodo semanal: </h2>
                <%}if(vista.equals("desarrollador.jsp")){%>
                <h2>Datos Actividad por periodo semanal: </h2>
                <% 
                }String error = null;
                try{
                    error = (String) request.getParameter("error");
                    if(error.equals("dias")){
                %>
                <p style="color:red">El numero de dias entre las fechas elegidas no corresponde a semanas completas (7días)</p>
                <%}}catch(NullPointerException e){ }
                %>
                <form role="form" action="InformeSemana" name="Informesemana" method="post">
                        <%
                        try{
                            String fecha1 = (String) request.getAttribute("fecha1");
                            String fecha2 = (String) request.getAttribute("fecha2");
                            try{
                            String datos = (String) request.getAttribute("datos");   
                            if(datos.equals("porBuscar")){
                        %> 
                        <div class="caja_small">
                                <input type="Date" name="fecha1" class="form-control"/>
                                <input type="Date" name="fecha2" class="form-control"/>
                        </div>
                            <%}}catch(ClassCastException e){
                                if(vista.equals("desarrollador.jsp")){
                                    List<Informetareas> datos = (List<Informetareas>) request.getAttribute("datos");
                                    if(datos==null){
                            %>
                            <p>No tienes informes de tarea en este periodo</p>
                            <%}else{
                            %>
                            <table class="table columna_caja_principal" >
                                <tr><p>Periodo: <%=fecha1%> - <%=fecha2%></p></tr>
                                <tr>
                                    <td><h4>Id Informe</h4></td>
                                    <td><h4>Nombre Actividad</h4></td>
                                    <td><h4>Semana</h4></td>
                                    <td><h4>Estado</h4></td>
                                </tr>
                                <%for(Informetareas i: datos){
                                    Tarea t = i.getTareaList().get(0);
                                    
                                %>
                                <tr>
                                    <td><%=i.getId()%></td>
                                    <td><%=t.getIdActividad().getNombre()%></td>
                                    <td><%=i.getSemanaPrettyPrinter()%></td>
                                    <td><%=i.getEstado()%></td>
                                </tr>
                                <%}}%>
                            </table>
                            <%}if(vista.equals("jefeProyecto.jsp")){
                                List<Actividad> datos = (List<Actividad>) request.getAttribute("datos");
                                if(datos==null){
                            %>
                            <p>No existen trabajadores asignados a actividades en este periodo</p>
                            <%}else{
                            %>
                            <table class="table columna_caja_principal" >
                                <tr><p>Periodo: <%=fecha1%> - <%=fecha2%></p></tr>
                                <tr>
                                    <td><h4>Dni</h4></td>
                                    <td><h4>Id Actividad</h4></td>
                                    <td><h4>Nombre Actividad</h4></td>
                                    <td><h4>Periodo Actividad</h4></td>
                                </tr>
                                <%for(Actividad a: datos){
                                    List<Miembro> miembros = a.getMiembroList();
                                    for(Miembro m: miembros){
                                %>
                                <tr>
                                    <td><%=m.getDni().getDni()%></td>
                                    <td><%=a.getId()%></td>
                                    <td><%=a.getNombre()%></td>
                                    <td><%=a.getFechaInicioPrettyString()%> - <%=a.getFechaFinPrettyString()%></td>
                                </tr>
                                <%}}%>
                            </table>
                        <%}}}}catch(NullPointerException e){ }%>
                        <div class="caja_small">
                        <div class="box-footer text-right" style="margin-top: 10px">
                            <button type="submit" class="btn btn-primary" name="accion" value="Aceptar">Aceptar</button>
                        </div>
                        </div>
                </form>
            </div>
        </div>
    </body>
</html>
