/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Proyecto;
import dominio.Rol;
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
import persistencia.ProyectoFacadeLocal;
import persistencia.RolFacadeLocal;
import persistencia.UsuarioFacadeLocal;

/**
 *
 * @author claramorrondo
 */
@WebServlet(name = "Administrador", urlPatterns = {"/Administrador"})
public class Administrador extends HttpServlet {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    @EJB
    private RolFacadeLocal rolFacade;

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
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesion = request.getSession();
            String user = (String) sesion.getAttribute("user");
            String accion = request.getParameter("accion");
            if (accion != null) {
                  if (accion.equals("Dar de Alta Trabajador")) response.sendRedirect("AltaTrabajador.jsp");     
                  if (accion.equals("Dar de Alta Proyecto")) response.sendRedirect("AltaProyecto.jsp");
                  if (accion.equals("Asignar Responsable")){
                      List<Proyecto> proyectosSinResponsable = proyectoFacade.findAll();
                      for(int i =0;i<proyectosSinResponsable.size();i++){
                          int idProyecto = proyectosSinResponsable.get(i).getId();
                          String nombreProyecto = proyectosSinResponsable.get(i).getNombre();
                          //BUSCAMOS LOS USUARIOS DISPONIBLES 
                          List<Usuario> usuariosDisponibles = usuarioFacade.findAll();
                          ArrayList<String> nombreUsuariosDisponibles = new ArrayList<>();
                          for(int j =0;j<usuariosDisponibles.size();j++){
                              if(usuariosDisponibles.get(j).getTipoCategoria()==1) nombreUsuariosDisponibles.add(usuariosDisponibles.get(j).getNombreCompleto());   
                          }
                      }
                      response.sendRedirect("AsignarResponsable.jsp");
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
