<%-- 
    Document   : usuarios
    Created on : 28-dic-2016, 18:20:42
    Author     : Rebeca
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.fasterxml.jackson.core.type.TypeReference"%>
<%@page import="dominio.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        String jsonU = (String) request.getAttribute("usuarios");
        String jsonP = (String) request.getAttribute("participacion");
        %>
        <form method="post" action="AsignarAProyecto">
        <table class="table columna_caja_principal" >
            <tr><h1>Usuarios disponibles:</h1></tr>
            <tr>
                <td><h4>Asignar</h4></td>
                <td><h4>Participacion</h4></td>
                <td><h4>Dni</h4></td>
                <td><h4>Nombre</h4></td>
                <td><h4>TipoCategoria</h4></td>
            </tr>
            <p></p>
            <% if(jsonU==null){%>
            <tr>No existen usuarios disponibles</tr>
            <%}else{
                    List<Usuario> users = mapper.readValue(jsonU, new TypeReference<List<Usuario>>(){});
                    List<Integer> porcents = mapper.readValue(jsonP, new TypeReference<List<Integer>>(){});
                    for(int i=0; i<users.size();i++){
                        Usuario u = users.get(i);
                        System.out.println("className.methodName()"+porcents.get(i));
            %>
            <tr>
                <td><input type="checkbox" name="dni" value="<%= u.getDni()%>"></td>
                <td><input type="number" name="participacion" min="1" max=<%=100 - porcents.get(i)%> </td>
                <td><%=u.getDni()%></td>
                <td><%=u.getNombreCompleto()%></td>
                <td><select name="tipoCategoria">
                        <% switch(u.getTipoCategoria()){
                            //El caso 1 y 2 son igual porque no podemos asignar JefeProyecto
                            case 1: case 2:%>
                            <option selected disabled>Elija categoria</option>
                            <option value="Analista">Analista</option> 
                            <option value="Disenador">Diseñador</option> 
                            <option value="Analista-programador">Analista-programador</option>
                            <option value="Responsable equipo de pruebas">Responsable equipo de pruebas</option> 
                            <option value="Programador">Programador</option> 
                            <option value="Probador">Probador</option> 
                            <%break;
                            case 3:%>
                            <option selected disabled>Elija categoria</option>
                            <option value="Disenador">Diseñador</option> 
                            <option value="Analista-programador">Analista-programador</option>
                            <option value="Responsable equipo de pruebas">Responsable equipo de pruebas</option> 
                            <option value="Programador">Programador</option> 
                            <option value="Probador">Probador</option> 
                            <%break;
                            case 4:%>
                            <option selected disabled>Elija categoria</option>
                            <option value="Programador">Programador</option> 
                            <option value="Probador">Probador</option> 
                        <%}%> 
                     </select></td>
            </tr>
            <%}}%>
        </table>
        <button type="submit" class="btn btn-primary" name="accion" value="Asignar">Aceptar</button>
        <button type="submit" class="btn btn-danger" name="accion" value="Cancelar">Cancelar</button>
        </form>
        </section>
    </body>
</html>
