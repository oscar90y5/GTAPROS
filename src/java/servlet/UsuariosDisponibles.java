/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Actividad;
import dominio.Miembro;
import dominio.Proyecto;
import dominio.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.ActividadFacadeLocal;
import persistencia.MiembroFacadeLocal;

/**
 *
 * @author claramorrondo
 */
@WebServlet(name = "UsuariosDisponibles", urlPatterns = {"/UsuariosDisponibles"})
public class UsuariosDisponibles extends HttpServlet {

    @EJB
    private MiembroFacadeLocal miembroFacade;

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
            HttpSession sesion = request.getSession();
            int id = Integer.valueOf(request.getParameter("idActividad"));              
            Actividad a = actividadFacade.findById(id);
            //ROL NECESARIO PARA LA ACTIVIDAD
            String rolActividad = a.getIdRol().getNombreRol();
            //Recogemos todos los miembros del proyecto
            List<Miembro> miembros = miembroFacade.findByIdProyecto(a.getIdProyecto());
            List<Usuario> usuariosDisponibles = new ArrayList<>();
            Proyecto p = a.getIdProyecto();      
            //Para cada miembro comprobamos su rol
            for(Miembro m : miembros){
                String rolMiembro = m.getIdRol().getNombreRol();
                Usuario dni = m.getDni();
                /*Si el miembro esta asignado al proyecto con el rol necesario
                *para realizar la actividad
                */
               if(rolMiembro.equals(rolActividad) && !a.getMiembroList().contains(m)){
                   usuariosDisponibles.add(dni);
               }
            }
            String destino = "AsignarAActividad";
            int idActividad = a.getId();
            request.setAttribute("destino", destino);
            sesion.setAttribute("idRolActividad", rolActividad);
            sesion.setAttribute("idActividad", idActividad);
            sesion.setAttribute("idProyecto", p.getId());
            request.setAttribute("usuariosDisponibles", usuariosDisponibles);
            request.getRequestDispatcher("usuariosDisponibles.jsp").forward(request, response);
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
