/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Miembro;
import dominio.Proyecto;
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
import persistencia.UsuarioFacadeLocal;

/**
 *
 * @author Rebeca
 */
@WebServlet(name = "AsignarAProyecto", urlPatterns = {"/AsignarAProyecto"})
public class AsignarAProyecto extends HttpServlet {

    @EJB
    private ProyectoFacadeLocal proyectoFacade;

    @EJB
    private MiembroFacadeLocal miembroFacade;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    

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
       // Se obtienen los usuario
        String [] dnis = request.getParameterValues("dni");
        String [] categorias = request.getParameterValues("tipoCategoria");
        System.out.println("servlet.AsignarAProyecto.processRequest()*************************"+ categorias);
         System.out.println("servlet.AsignarAProyecto.processRequest()"+ categorias.length);
        String[] participacion = request.getParameterValues("participacion");
        HttpSession sesion = request.getSession();
        Integer idProject = (Integer) sesion.getAttribute("idProject");
        for(int i=0; i< dnis.length; i++){
            Miembro m = new Miembro();
            Usuario u = usuarioFacade.find(dnis[i]);
            m.setDni(u);
            Proyecto p = proyectoFacade.find(idProject);
            m.setIdProyecto(p);
            m.setTipoRol(categorias[i]);
            m.setParticipacion(Integer.parseInt(participacion[i]));
            int id = miembroFacade.findAll().size() +1;
            m.setIdMiembro(id);
            miembroFacade.create(m);
        }
        response.sendRedirect("exito.jsp");

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
