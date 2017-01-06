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
import persistencia.UsuarioFacadeLocal;

/**
 *
 * @author claramorrondo
 */
@WebServlet(name = "AsignarAActividad", urlPatterns = {"/AsignarAActividad"})
public class AsignarAActividad extends HttpServlet {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

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
        HttpSession sesion = request.getSession();
        String dniUsuario = request.getParameter("UsuarioDni");
        int id = (int) sesion.getAttribute("idActividad");
        Actividad a = actividadFacade.findById(id);
        Proyecto proyecto = a.getIdProyecto();
      
        Miembro m = miembroFacade.findByDniAndIdProyecto(dniUsuario, proyecto.getId());
        List<Actividad> actividades = m.getActividadList();
        actividades.add(a);
        m.setActividadList(actividades);
        List<Miembro> miembros = a.getMiembroList();
        miembros.add(m);
        a.setMiembroList(miembros);
        miembroFacade.edit(m);
        actividadFacade.edit(a);

        request.getRequestDispatcher("exito.jsp").forward(request, response);
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
