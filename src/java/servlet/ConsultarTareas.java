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
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.ActividadFacadeLocal;
import persistencia.InformetareasFacadeLocal;

/**
 *
 * @author miki
 */
@WebServlet(name = "ConsultarTareas", urlPatterns = {"/ConsultarTareas"})
public class ConsultarTareas extends HttpServlet {

    @EJB
    private ActividadFacadeLocal actividadFacade;

    @EJB
    private InformetareasFacadeLocal informetareasFacade;

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
        HttpSession sesion = request.getSession();
        String accion = (String) request.getParameter("accion");
        int idProject = (Integer) sesion.getAttribute("idProject");
        System.out.println(accion + " " + idProject);
        //Proyecto proyect = proyectoFacade.find(idProject);

        Integer idActividad = Integer.parseInt(request.getParameter("idActividad"));
        System.out.println("idActivitidad -" + idActividad + "-");
        Actividad actividad = actividadFacade.find(idActividad);
        System.out.println("actividad string " + actividad);
        List<Informetareas> informes = informetareasFacade.findByIdActividad(actividad);
        System.out.println("es null? " + informes);

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
