<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=1,initial-scale=1,user-scalable=1" />
        <title>GTAPROS</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
        <script type='text/javascript'>
            var counter = 1;
            var limit = 1000;
            var counterBefore = "roles"+(counter-1);
            function addRol(divName){
                 var newdiv;
                 var newInput;
                 
                 if (counter <= limit)  {
                      newdiv = document.createElement('div');
                      newdiv.innerHTML = "Rol " + (counter + 1) + " y Nombre Rol"+ (counter + 1)+
                              "<br>\n\
          <select class='form-group' name='roles"+counter+"'>\n\
                <option selected disabled>Elige una opción</option>\n\
                <optgroup label='Categoria 1'>\n\
                    <option value='JefeProyecto'>Jefe de proyecto</option>\n\
                </optgroup>\n\
                <optgroup label='Categoria 2'>\n\
                    <option value='Analista'>Analista</option>\n\
                </optgroup>\n\
                <optgroup label='Categoria 3'>\n\
                    <option value='Disenador'>Diseñador</option>\n\
                    <option value='AnalistaProgramador'>Analista-programador</option>\n\
                    <option value='RespEquipoPruebas'>Rsponsable equipo de pruebas</option>\n\
                </optgroup>\n\
                <optgroup label='Categoria 4'>\n\
                    <option value='Programador'>Programador</option>\n\
                    <option value='Probador'>Probador</option>\n\
                </optgroup>\n\
            </select>";
                      document.getElementById(divName).appendChild(newdiv);
                      counter++;
                 }
                
             
                 document.getElementById("cantidad").value=counter;
               
                 
            }
   </script>
    </head>
    <body>
      <section class="container">
             <div class="caja_principal"> 
             <h2> Dar de alta nuevo Proyecto: </h2>
            <form action="AltaProyecto" method="POST" style="margin-top: 20px;">
                <div class="form-group">  
                    <input type="text" class="form-control" id="nombreProyecto" placeholder="Introduzca el nombre del proyecto" name="nombreProyecto" required>
                </div>
                    <div class="form-group">
                 <label for="nombreProyecto">Roles y categorías (Al menos debe asignar el Jefe de Proyecto)</label><br />
                 <div id="dynamicInput">
                     <div class="form-group">Tipo Categoría y Nombre Rol 1<br></div>
                         <select name="roles0" class="form-group">
                            <option selected disabled> Elige una opción </option>
                            <optgroup label="Categoria 1"> 
                                <option value="JefeProyecto">JefeProyecto</option>  
                            </optgroup> 
                            <optgroup label="Categoria 2"> 
                                <option value="Analista">Analista</option>
                            </optgroup>
                            <optgroup label="Categoria 3">    
                                <option value="Disenador">Diseñador</option> 
                                <option value="AnalistaProgramador">Analista-programador</option> 
                                <option value="RespEquipoPruebas">Responsable equipo de pruebas</option> 
                            </optgroup> 
                            <optgroup label="Categoria 4">
                                <option value="Programador">Programador</option> 
                                <option value="Probador">Probador</option> 
                            </optgroup>        
                         </select>
                     </div>
                   <div class="form-group">
                       <input type="button" class="btn" style="color: #fff;" value="Añadir nuevo Rol" onClick="addRol('dynamicInput');">
                   </div></div>
               <input type="hidden" id ="cantidad" name="cantidad" value="1" />
        <div class="box-footer">
             <h4>¿Desea asignar ahora un jefe de proyecto? </h4>
             <div class="box-footer text-left">
             <button type="submit" class="btn btn-primary" name="altaProyectoBtn" value="asignarJefe">Añadir Jefe de Proyecto</button>
             <button type="submit" class="btn btn-primary" name="altaProyectoBtn" value="asignarJefeLater">Más tarde</button>
             <button type="submit" class="btn btn-danger" name="altaProyectoBtn" value="cancelar">Cancelar</button>
        </div> </div>
        </form>
             </div>
      </section>
    </body>
</html>
