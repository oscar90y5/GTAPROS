
<%@page import="dominio.Usuario"%>
<%@page import="dominio.Proyecto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=1,initial-scale=1,user-scalable=1" />
        <title>GTAPROS</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
    </head>
    <body>
        <section class="container">
            <section class="login-form">
            <form role="form" action="AsignarResponsable" name="AsignarResponsable" value="AsignarResponsable" method="post">
            <% try{
                String nombreProyecto = (String) request.getAttribute("nombreProyecto");
                if(nombreProyecto==null){
                    try{
                     List<Proyecto> proyectos = (List<Proyecto>) request.getAttribute("proyectosSinResponsable");
                     if(proyectos==null){%>
            <p>No existen proyectos sin responsable asignado</p>
            <%}else{%>
            <h2> Asignar Responsable al Proyecto:</h2>
                <select name="proyectos" >
                    <%for(Proyecto p: proyectos){%>
                    <option><%=p.getId()%>- <%=p.getNombre()%></option>
                     <%}%>   
                </select>
            <%}}catch(NullPointerException e){}
                catch(ClassCastException e){}
            }else{%>
            <h2> Asignar Responsable al Proyecto: <%=nombreProyecto%></h2>    
            <%}}catch(NullPointerException e){ }%>
             <div class="">
                 <p>Seleccione el usuario que desea que sea jefe de proyecto:</p>
                <%
                try{
                    List<Usuario> usuariosDisponibles = (List<Usuario>) request.getAttribute("usuariosDisponibles");
                    if(usuariosDisponibles==null){
                %>
                <p>No hay usuarios disponibles con la categoria necesaria para ser Jefe de Proyecto</p>
                <%}else{%>
                <select name="usuariosDisponibles" >
                    <%for(Usuario u: usuariosDisponibles){%>
                      <option><%=u.getNombreCompleto()%></option>
                     <%}%>   
                </select>
                <%}}catch(NullPointerException e){ }
                    catch(ClassCastException e){}
                %>
             </div>
                <p>Participación:<p>
                <input type="number" name="participacion" min="1" max="100"/>
             <div>
                 
             </div>
         <div class="box-footer">
             <button type="submit" class="btn btn-primary" name="asignarJefe" value="asignarJefe">Asignar Jefe de Proyecto</button>
             <button type="submit" class="btn btn-danger" name="asignarJefe" value="cancelar">Cancelar</button>
         </div>
        </form>
           </section>
        </section>
    </body>
</html>