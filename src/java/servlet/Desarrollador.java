/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dominio.Actividad;
import dominio.Proyecto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.ActividadFacadeLocal;
import persistencia.ProyectoFacadeLocal;

/**
 *
 * @author oscar
 */
@WebServlet(name = "Desarrollador", urlPatterns = {"/Desarrollador"})
public class Desarrollador extends HttpServlet {

    @EJB
    private ActividadFacadeLocal actividadFacade;

    @EJB
    private ProyectoFacadeLocal proyectoFacade;
    
    public static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesion = request.getSession();
            
            //String idProject = (String) sesion.getAttribute("idProject");
            //BORRAR
            int idProject = 1;
            
            String accion = request.getParameter("accion");
            System.out.print("request "+accion+"\n");
            if(accion!=null){
                System.out.print("not null\n");
                if(accion.equals("Introducir tarea")){
                    System.out.print("introducir tarea\n");
                    Proyecto proyecto = proyectoFacade.find(Integer.valueOf(idProject));
                    List<Actividad> actividades = proyecto.getActividadList();
                    //HACER NUEVA CONSULTA QUE DEVUELVA LAS ACTIVIDADES ACTIVAS DE EL USUARIO Y EL PROYECTO
                    if(actividades.size()==1){
                        System.out.print("size 1\n");
                        //redirigimos a introducir tareas con el id de la actividad
                        response.sendRedirect("introducirTareas.jsp?id="+actividades.get(0).getId());
                    } else {
                        System.out.print("size * "+actividades.size()+"\n");
                        //Añadimos las actividades a un json
                        String json = mapper.writeValueAsString(actividades);
                        System.out.print("bieeeen\n");
                        request.setAttribute("actividades", json);
                        System.out.print("bieeeeeeeeeeeeeeeeeeeeen\n");
                        response.sendRedirect("actividades.jsp");
                        System.out.print("mal\n");
                    }

                    
                }
                if(accion.equals("Modificar tareas activas")){
                    out.print("Modificar datos de tareas en desarrollo....");
                }
                if(accion.equals("Consultar datos de tareas")){
                    out.print("Consultar datos de tareas en desarrollo....");
                }
                if(accion.equals("Obtener informes")){
                    out.print("Obtener informes en desarrollo....");
                }
                
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
