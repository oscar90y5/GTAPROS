/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dominio.Informetareas;
import dominio.Miembro;
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
import persistencia.InformetareasFacadeLocal;
import persistencia.MiembroFacadeLocal;

/**
 *
 * @author Rebeca
 */
@WebServlet(name = "GenerarInforme", urlPatterns = {"/GenerarInforme"})
public class GenerarInforme extends HttpServlet {

    @EJB
    private MiembroFacadeLocal miembroFacade;

    @EJB
    private InformetareasFacadeLocal informetareasFacade;

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
        String informe = request.getParameter("informe");
        String json = (String) request.getAttribute("proyecto");
        Proyecto p = mapper.readValue(json, Proyecto.class);
        
        if(informe.equals("Trabajadores/Actividades por periodo semanal")){
            //Primero selecciona semana, luego genera informe
        }
        if(informe.equals("Trabajadores/Informes pendientes de Envio")){
            List<Miembro> trabajadores = miembroFacade.findByIdProyecto(p);
            List<Informetareas> pendientesEnvio = informetareasFacade.findByEstado("PendienteEnvio");
        }
        if(informe.equals("Trabajadores/Informes pendientes de Aprobación")){
             List<Informetareas> pendientesAprob = informetareasFacade.findByEstado("PendienteAprobacion");
        }
        if(informe.equals("Tiempo real/planificado de actividades por periodo")){
            //Primero mostrar calendario (similar vacaciones.jsp), luego generar informe
        }
        if(informe.equals("Actividades con tiempo real mayor del esperado")){
            //Generar informe
        }
        if(informe.equals("Actividades/Recursos por periodo posterior")){
            //Primero pedir días despues de fecha actual, luego genera informe
        }
        if(informe.equals("Trabajadores/Actividades/Tiempo por periodo posterior")){
            //Primero pedir días despues de fecha actual, luego genera informe
        }
        if(informe.equals("Informe general")){
            //Generar informe
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
