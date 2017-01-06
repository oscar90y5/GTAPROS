<%-- 
    Document   : informeSemana
    Created on : 31-dic-2016, 10:58:06
    Author     : Rebeca
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="dominio.Tarea"%>
<%@page import="dominio.Actividad"%>
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
            <div class="caja_principal">
                <%String estado = null;
                try{
                    estado = request.getParameter("infor");
                    if(estado.equals("realplanificado")){
                %>
                <h2>Relacion Tiempo Real/Planificado de Actividades por periodo: </h2>
                <%}if(estado.equals("recursos")){%>
                <h2>Relacion Actividades/Recursos asignados por periodo: </h2>
                <%}if(estado.equals("trabajadores")){%>
                <h2>Relacion Trabajadores/Tiempo empleado en Actividad por periodo: </h2>
                <%}}catch(NullPointerException e){ }%>
                <form role="form" action="InformePeriodo?infor=<%=estado%>" name="InformePeriodo?infor=<%=estado%>" method="post">
                    <% 
                    String error = null;
                    try{
                        error = (String) request.getParameter("error");
                        if(error.equals("dias")){
                    %>
                    <p style="color:red">El dia inicial elegido es anterior a la fecha actual</p>
                    <%}
                    }catch(NullPointerException e){ }
                    try{
                            String fecha1 = (String) request.getAttribute("fecha1");
                            String fecha2 = (String) request.getAttribute("fecha2");
                            try{
                                String datos = (String) request.getAttribute("datos");
                            if(datos.equals("porBuscar")){
                        %>
                                <input type="text" id="fecha1" name="fecha1" class="form-control" required/>
                                <button id="fechaIni">Seleccione fecha inicio</button>
                                <script type="text/javascript">
                                   Calendar.setup({
                                     inputField: "fecha1",
                                     ifFormat:   "%d/%m/%Y",
                                     weekNumbers: false,
                                     displayArea: "fechaIni",
                                     daFormat:    "%A, %d de %B de %Y"
                                   });
                                </script>
                                <div class="form-group">
                                <input type="text" id="fecha2" name="fecha2" class="form-control" required/>
                                <button id="fechaFin">Seleccione fecha fin</button>
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
                            <%}}catch(ClassCastException e){
                                List<Actividad> datos = (List<Actividad>) request.getAttribute("datos");
                                if(datos==null || datos.isEmpty()){
                            %>
                            <p>No existen actividades en este periodo</p>
                            <%}else{
                                if(estado.equals("realplanificado")){
                            %>
                            <table class="table columna_caja_principal" >
                                <tr><p>Periodo: <%=fecha1%> - <%=fecha2%></p></tr>
                                <tr>
                                    <td><h4>Id Actividad</h4></td>
                                    <td><h4>Nombre Actividad</h4></td>
                                    <td><h4>Tiempo Planificado</h4></td>
                                    <td><h4>Tiempo Real</h4></td>
                                </tr>
                                <%for(Actividad a: datos){
                                    List<Tarea> tareas = a.getTareaList();
                                    int tiempoReal = 0;
                                    for(Tarea t: tareas){
                                        tiempoReal += t.getEsfuerzoReal();
                                    
                                    }
                                %>
                                <tr>
                                    <td><%=a.getId()%></td>
                                    <td><%=a.getNombre()%></td>
                                    <td><%=a.getDuracion()%></td>
                                    <td><%=tiempoReal%></td>
                                </tr>
                                <%}}if(estado.equals("recursos")){%>   
                                <table class="table columna_caja_principal" >
                                    <tr><p>Periodo: <%=fecha1%> - <%=fecha2%></p></tr>
                                    <tr>
                                        <td><h4>Id Actividad</h4></td>
                                        <td><h4>Nombre Actividad</h4></td>
                                        <td><h4>Periodo</h4></td>
                                        <td><h4>Recursos</h4></td>
                                    </tr>
                                    <%for(Actividad a: datos){
                                        List<Miembro> miembros = a.getMiembroList();
                                        String recursos = "";
                                        for(Miembro m: miembros){
                                            recursos.concat(m.getDni().getNombreCompleto()+"\n");
                                        }
                                    %>
                                    <tr>
                                        <td><%=a.getId()%></td>
                                        <td><%=a.getNombre()%></td>
                                        <td><%=a.getFechaInicioPrettyString()%> - <%=a.getFechaFinPrettyString()%></td>
                                        <td><%=recursos%></td>
                                    </tr>
                                    <%}}if(estado.equals("trabajadores")){%>   
                                <table class="table columna_caja_principal" >
                                    <tr><p>Periodo: <%=fecha1%> - <%=fecha2%></p></tr>
                                    <tr>
                                        <td><h4>Dni</h4></td>
                                        <td><h4>Nombre Completo</h4></td>
                                        <td><h4>Id Actividad</h4></td>
                                        <td><h4>Nombre Actividad</h4></td>
                                        <td><h4>Tiempo trabajado</h4></td>
                                    </tr>
                                    <%for(Actividad a: datos){
                                        List<Miembro> miembros = a.getMiembroList();
                                        for(Miembro m: miembros){
                                            List<Tarea> tareasPersonales = m.getTareaList();
                                            int tiempoTrabajado = 0;
                                            for(Tarea t: tareasPersonales){
                                                tiempoTrabajado += t.getEsfuerzoReal();
                                            }%>
                                    <tr>
                                        <td><%=m.getDni().getDni()%></td>
                                        <td><%=m.getDni().getNombreCompleto()%></td>
                                        <td><%=a.getId()%></td>
                                        <td><%=a.getNombre()%></td>
                                        <td><%=tiempoTrabajado%></td>
                                    </tr>
                                    <%}}%>
                                </table>
                        <%}}}}catch(NullPointerException e){ }%>
                        <button type="submit" class="btn btn-primary" name="accion" value="Aceptar">Aceptar</button>
                </form>
            </div>
        </div>
    </body>
</html>
