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
import persistencia.MiembroFacadeLocal;
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
    private MiembroFacadeLocal miembroFacade;

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
        HttpSession sesion = request.getSession();
        String user = (String) sesion.getAttribute("user");
        String accion = request.getParameter("accion");
        String rd = "administrador.jsp";
        if (accion != null) {
              if (accion.equals("Dar de Alta Trabajador")) rd = "altaTrabajador.jsp";     
              if (accion.equals("Dar de Alta Proyecto")) rd = "altaProyecto.jsp";

              if (accion.equals("Asignar Responsable")){
                  //Buscamos proyectos sin miembro con rol jefe de proyecto
                  List<Proyecto> proyectos = proyectoFacade.findAll();
                  List<Proyecto> proyectosSinResponsable = proyectoFacade.findAll();
                  for(Proyecto p: proyectos){
                    List<Miembro> miembros = miembroFacade.findByIdProyecto(p);
                    for(Miembro m: miembros)
                        if(m.getIdRol().getNombreRol().equals("JefeProyecto"))
                            proyectosSinResponsable.remove(p);
                  }
                    //Buscamos usuarios disponibles para ese rol 
                    List<Usuario> usuariosDisponibles = new ArrayList<>();
                    //Recogemos todos los usuarios con categoria 1 y miembros almacenados en la BD
                   List<Usuario> usuarios = usuarioFacade.findByTipoCategoria(1);
                   for(Usuario u: usuarios){
                        List<Miembro> miembros = miembroFacade.findByDni(u);
                        //Si no está en la tabla de miembros es porque está disponible
                       if(!u.getEsAdmin() && (miembros==null || miembros.isEmpty()))
                            usuariosDisponibles.add(u); 
                        /*Si está en la tabla de mimebros, ver que sea miembro
                        * de 1 solo proyecto (maximo posible es 2) y si es miembro de 1 que no 
                        * sea ya jefe
                        */
                       else{
                            if(miembros.size()==1){ 
                               if(!miembros.get(0).getIdRol().getNombreRol().equals("JefeProyecto"))
                                   usuariosDisponibles.add(u); 
                            }
                       }
                  }
                   
                request.setAttribute("proyectosSinResponsable", proyectosSinResponsable);
                request.setAttribute("usuariosDisponibles", usuariosDisponibles);
                rd= "asignarResponsable.jsp";
              }
              if (accion.equals("Fijar vacaciones")) rd = "vacaciones.jsp";
              if(accion.equals("Cerrar Sesion")) rd = "index.jsp";
        }
        request.getRequestDispatcher(rd).forward(request, response);
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
