/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Miembro;
import dominio.Proyecto;
import dominio.Rol;
import dominio.Usuario;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.MiembroFacadeLocal;
import persistencia.ProyectoFacadeLocal;
import persistencia.RolFacadeLocal;
import persistencia.UsuarioFacadeLocal;

/**
 *
 * @author claramorrondo
 */
@WebServlet(name = "AsignarResponsable", urlPatterns = {"/AsignarResponsable"})
public class AsignarResponsable extends HttpServlet {

    @EJB
    private RolFacadeLocal rolFacade;

    @EJB
    private MiembroFacadeLocal miembroFacade;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    @EJB
    private ProyectoFacadeLocal proyectoFacade;

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
        String accion = (String) request.getParameter("asignarJefe");
        String rd = "asignarResponsableSesion.jsp";
        
        if(accion.equals("cancelar")) rd = "administrador.jsp";
        if(accion.equals("asignarJefe")){
            int idProyecto;
            try{
                idProyecto = (Integer) sesion.getAttribute("idNuevoProyecto");
            }catch(NullPointerException e){ 
                String stringp = (String) request.getParameter("proyectos");
                String[] partes = stringp.split("-");
                idProyecto = Integer.valueOf(partes[0]);
            }
            
            String nombreJefe = (String) request.getParameter("usuariosDisponibles");
            Usuario u = usuarioFacade.findByNombreCompleto(nombreJefe);
            Proyecto p = proyectoFacade.find(idProyecto);
            Miembro m = new Miembro();
            m.setIdMiembro(miembroFacade.count()+1);
            m.setDni(u);
            m.setIdProyecto(p);
            m.setIdRol(rolFacade.findByNombreRolAndIdProyecto("JefeProyecto", p));
            miembroFacade.create(m);
            rd = "exito.jsp";

        }
         response.sendRedirect(rd);
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
