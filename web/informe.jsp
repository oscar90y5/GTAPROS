<%@page import="dominio.Miembro"%>
<%@page import="dominio.Actividad"%>
<%@page import="java.util.HashMap"%>
<%@page import="dominio.Tarea"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@page import="com.fasterxml.jackson.core.type.TypeReference"%>
<%@page import="java.util.List"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="dominio.Proyecto"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GTAPROS</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
    </head>
    <body>
        <section class="container">
        <% 
        ObjectMapper mapper = new ObjectMapper();
        String json = (String) request.getAttribute("datos");
        String estado = request.getParameter("infor");
        %>
        <div class="caja_principal">
        <form action="jefeProyecto.jsp" method="POST">
            <%if(estado.equals("pendienteEnvio")){%>
                <h2>Relacion Trabajadores/Actividades pendientes de envio:</h2>
                <%}if(estado.equals("pendienteAprob")){%>
                <h2>Relacion Trabajadores/Actividades pendientes de aprobacion</h2>
                <%}if(estado.equals("realmayor")){%>
                <h2>Actividades con mayor tiempo real que estimado</h2>
                <%}%>
            <% if(json==null){%>
                    <%if(estado.equals("pendienteEnvio")){%>
                    <p>No existen informes pendientes de envio</p>
                    <%}if(estado.equals("pendienteAprob")){%>
                    <p>No existen informes pendientes de aprobacion</p>
                    <%}if(estado.equals("realmayor")){%>
                    <p>No existen actividades con mayor tiempo real que estimado</p>
            <%}}else{%>
                    <%if(estado.equals("pendienteEnvio") || estado.equals("pendienteAprob")){
                        List<Tarea> datos = mapper.readValue(json, new TypeReference<List<Tarea>>(){});
                    %>
                    <table class="table columna_caja_principal" >
                    <tr>
                        <td><h4>Dni</h4></td>
                        <td><h4>Id Actividad</h4></td>
                        <td><h4>Tipo Actividad</h4></td>
                    </tr>
                    <%for(Tarea t: datos){%>
                    <tr>
                        <td><%=t.getMiembro().getDni().getDni()%></td>
                        <td><%=t.getTareaPK().getIdActividad()%></td>
                        <td><%=t.getTareaPK().getTipo()%></td>
                    </tr>
                    </table>
                    <%}}if(estado.equals("realmayor")){
                        List<Actividad> datos = mapper.readValue(json, new TypeReference<List<Actividad>>(){});
                    %>
                    <table class="table columna_caja_principal" >
                    <tr>
                        <td><h4>Id Actividad</h4></td>
                        <td><h4>Nombre Actividad</h4></td>
                        <td><h4>TiempoEstimado</h4></td>
                        <td><h4>TiempoReal</h4></td>
                    </tr>
                    <%for(Actividad a: datos){
                            List<Tarea> tareas = a.getTareaList();
                            int tiempoReal = 0;
                            for(Tarea t: tareas)
                                tiempoReal += t.getEsfuerzoReal();
                        %>
                    <tr>
                        <td><%=a.getId()%></td>
                        <td><%=a.getNombre()%></td>
                        <td><%=a.getDuracion()%></td>
                        <td><%=tiempoReal%></td>
                    </tr>
                    </table>
                    <%}}if(estado.equals("general")){
                        List<Actividad> datos = mapper.readValue(json, new TypeReference<List<Actividad>>(){});
                    %>
                    <table class="table columna_caja_principal" >
                    <tr>
                        <td><h4>Id Actividad</h4></td>
                        <td><h4>Nombre Actividad</h4></td>
                        <td><h4>Predecesoras</h4></td>
                        <td><h4>Sucesoras</h4></td>
                        <td><h4>Recursos</h4></td>
                        <td><h4>TiempoReal</h4></td>
                    </tr>
                    <%for(Actividad a: datos){
                            List<Tarea> tareas = a.getTareaList();
                            List<Miembro> miembros = a.getMiembroList();
                            int tiempoReal = 0;
                            String recursos ="";
                            for(Tarea t: tareas)
                                tiempoReal += t.getEsfuerzoReal();
                            for(Miembro m: miembros)
                                recursos.concat(m.getDni().getNombreCompleto()+"\n");
                        %>
                    <tr>
                        <td><%=a.getId()%></td>
                        <td><%=a.getNombre()%></td>
                        <td><%=a.getActividadList()%></td>
                        <td><%=a.getActividadList1()%></td>
                        <td><%=recursos%></td>
                        <td><%=tiempoReal%></td>
                    </tr>
                    </table>
                    <%}}}%>
                <button type="submit" class="btn btn-primary" name="accion" value="Aceptar">Aceptar</button>   
        </form>
        </div>
        </section>
    </body>
</html>
