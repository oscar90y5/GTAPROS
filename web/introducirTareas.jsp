<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=1,initial-scale=1,user-scalable=1" />
        <title>GTAPROS</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
        <% String actividad = request.getParameter("idActividad");
            pageContext.setAttribute("idActividad", actividad, PageContext.APPLICATION_SCOPE);
        %>
    </head>
    <body>
        <div class="container">
            <div class="caja_principal">
                <h2> Introducir tareas de actividad <%=actividad%> :</h2>
                <% try {
                        String error = (String) request.getParameter("error");
                        if (error != null) {
                %>
                <p style="color:red">No se ha introducido una semana de Lunes a Domingo</p>
                <%}
                    } catch (NullPointerException e) {
                    }%>
                <div class="caja_small">
                    <form role="form" action='IntroducirTareas' method="post">
                        <input type="hidden" name="idActividad" id="idActividad" value="<%=actividad%>"/>
                        <div class="form">
                            <p>
                                <label for="tratoUsuarios">Trato con usuarios:</label>
                                <input type="number" min="0" name="tratoUsuarios" placeholder="Tiempo dedicado (h/hombre)" />
                            </p>
                            <p>
                                <label for="reuniones">Reuniones internas y externas:</label>
                                <input type="number" min="0" name="reuniones" placeholder="Tiempo dedicado (h/hombre)" />
                            </p>
                            <p>
                                <label for="leerRevisarDocumentacion">Lectura y revision de documentacion:</label>
                                <input type="number" min="0" name="leerRevisarDocumentacion" placeholder="Tiempo dedicado (h/hombre)" />
                            </p>
                            <p>
                                <label for="elaboracionDocumentacion">Elaboracion de documentacion</label>
                                <input type="number" min="0" name="elaborDocumentacion" placeholder="Tiempo dedicado (h/hombre)" />
                            </p>
                            <p>
                                <label for="programar">Desarrollo y verificacion de programas:</label>
                                <input type="number" min="0" name="programar" placeholder="Tiempo dedicado (h/hombre)" />
                            </p>
                            <p>
                                <label for="formar">Formacion de usuarios y otros:</label>
                                <input type="number" min="0" name="formar" placeholder="Tiempo dedicado (h/hombre)" />
                            </p>
                            <p>
                                <label for="semana">Semana(LUNES-DOMINGO):</label>
                                <input type="Date" name="fecha1" placeholder="Seleccione fecha inicio"/>
                                <input type="Date" name="fecha2" placeholder="Seleccione fecha fin"/>
                            </p>
                        </div>
                        <div class="box-footer text-right" style="margin-top: 10px">
                            <button type="submit" class="btn btn-primary" name="accion" value="Ahora">Enviar ahora</button>
                            <button type="submit" class="btn btn-primary" name="accion" value="Tarde">Enviar más tarde</button>
                            <button type="submit" class="btn btn-danger" name="accion" value="Cancelar">Cancelar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
