/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Actividad;
import dominio.Informetareas;
import dominio.Proyecto;
import dominio.Tarea;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.ActividadFacadeLocal;
import persistencia.InformetareasFacadeLocal;
import persistencia.ProyectoFacadeLocal;

/**
 *
 * @author claramorrondo
 */
@WebServlet(name = "FijarFinProyecto", urlPatterns = {"/FijarFinProyecto"})
public class FijarFinActividad extends HttpServlet {

    @EJB
    private InformetareasFacadeLocal informetareasFacade;

    @EJB
    private ProyectoFacadeLocal proyectoFacade;

    @EJB
    private ActividadFacadeLocal actividadFacade;

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
          int id = Integer.valueOf(request.getParameter("idActividad"));
          Actividad a = actividadFacade.findById(id);
          Date fechaFin = new Date();
          List<Tarea> listadoTareas = a.getTareaList();
          int count =0;
          for(Tarea t : listadoTareas){
              Informetareas i = t.getInformetareas();
              String estadoInforme = i.getEstado();
              if(!estadoInforme.equals("Aceptado")){
                  break;
              } 
              else count++;
          }
          // Todos los informes de tareas tienen estado cerrado y podemos cerrar la actividad
          if(count == listadoTareas.size()){
              a.setFechaFin(fechaFin);
              a.setEstado("Cerrado");
              actividadFacade.edit(a);
               List <Actividad> sucesoras = a.getActividadList1();
                //Ponemos como fecha de inicio de todas las sucesoras la fecha fin de la actividad
                for(Actividad actividad : sucesoras){
                    actividad.setFechaInicio(fechaFin);
                }
                int idProyecto = a.getIdProyecto().getId();
                Proyecto p = proyectoFacade.findById(id);
                List <Actividad> actividadesProyecto = p.getActividadList();
                int contador = 0;
                //Recogemos todas las actividades del proyecto y vemos si todas están cerradas
                for (Actividad ac : actividadesProyecto){
                    if(ac.getEstado().equals("Cerrado")) contador++;
                }
                //Si todas las actividades están cerradas, cerramos el proyecto
                if(contador == actividadesProyecto.size()){
                    p.setFechaFin(fechaFin);
                    p.setEstado("Finalizado");
                    proyectoFacade.edit(p);
                }
                response.sendRedirect("exito.jsp"); 

          }
          //Hay informes de tareas que no tienen estado cerrado
          else{
            request.getRequestDispatcher("jefeProyecto.jsp?error=CierreInformes").forward(request, response);
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
