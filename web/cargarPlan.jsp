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
                <h2> Cargar plan de proyecto: </h2>
                <div class="caja_small">
                    <%
                        String error = null;
                        if ((error = (String) request.getAttribute("error")) != null) {
                    %><p style="color:red"><%=error%></p><%
                    } else {
                    %>
                    <form role="form" action="CargarPlan" name="CargarPlan" enctype="multipart/form-data" method="post">
                        <div class="form-group">
                            <label for="fecha">Fecha de inicio:</label>
                            <input type="Date" name="fecha"/>
                        </div>
                        <div class="form-group">
                            <label for="file">Seleccionar archivo de carga de proyecto:</label>
                            <input id="file" name="file" type="file" accept="text/plain" 
                                   class="form-control-file" />
                            <small id="fileHelp" class="form-text text-muted">El archivo debe ser un fichero de texto plano.</small>
                        </div>
                        <div class="box-footer text-right" style="margin-top: 10px">
                            <button type="submit" class="btn btn-primary" name="accion" value="Aceptar">Aceptar</button>
                            <button type="submit" class="btn btn-danger" name="accion" value="Cancelar">Cancelar</button>
                        </div>
                    </form>
                    <%}
                    %>
                </div>
                <form role="form" action="VolverMenu" method="POST">
                    <button type="submit" class="btn btn-primary" name="accion" value="Volver">Volver</button>
                </form>
            </div>
        </div>
    </body>
</html>
