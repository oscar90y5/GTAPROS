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
import persistencia.ProyectoFacadeLocal;
import persistencia.UsuarioFacadeLocal;

/**
 *
 * @author claramorrondo
 */
@WebServlet(name = "UsuariosDisponibles", urlPatterns = {"/UsuariosDisponibles"})
public class UsuariosDisponibles extends HttpServlet {

    @EJB
    private MiembroFacadeLocal miembroFacade;

    @EJB
    private ProyectoFacadeLocal proyectoFacade;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

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
            String dniUsuario = (String) sesion.getAttribute("idUsuario");
            int id = Integer.valueOf(request.getParameter("idActividad"));              
            Actividad a = actividadFacade.findById(id);
            //ROL NECESARIO PARA LA ACTIVIDAD
            int idRolActividad = a.getIdRol().getIdRol();  
            //RECOGEMOS TODOS LOS USUARIOS ALMACENADOS
            List<Usuario> usuarios =usuarioFacade.findAll();
            List<Usuario> usuariosDisponibles = new ArrayList<>();
            Proyecto p = a.getIdProyecto();      
            //PARA CADA USUARIO ALMACENADO MIRAMOS SU ROL
            for(Usuario u : usuarios){
                int tipoCategoriaUsuario = u.getTipoCategoria();
                String dni = u.getDni();
                //SI EL ROL DEL USUARIO ES IGUAL O MENOR QUE EL DE LA ACTIVIDAD LA PUEDE ASIGNAR
               if(tipoCategoriaUsuario<=idRolActividad 
                       && u.getEsAdmin().equals(false) &&
                       !u.getDni().equals(dniUsuario)) {
                //SI EL USUARIO NO ESTA EN LA TABLA MIEMBRO SE AÑADE
                    List<Miembro> miembros = miembroFacade.findByDni(u);
                    if(miembros.isEmpty()) usuariosDisponibles.add(u);
                    
                    //SI EL USUARIO ESTA EN LA TABLA MIEMBRO
                    else{
                        //RECOGEMOS TODAS LAS VECES QUE ESTA EN LA TABLA MIEMBRO
                        for(Miembro m : miembros){
                            //SI SE QUIERE AÑADIR A UNA ACTIVIDAD DEL MISMO PROYECTO
                            //TIENE QUE SER EL JEFE DE PROYECTO
                            if(m.getIdProyecto().equals(a.getIdProyecto()) && 
                              (m.getIdRol().getIdRol()!=1)){
                                usuariosDisponibles.add(u);
                                break;
                            }else{
                                if(!m.getIdProyecto().equals(a.getIdProyecto())){
                                    usuariosDisponibles.add(u);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            String destino = "AsignarUsuarioActividad";
            int idActividad = a.getId();
            request.setAttribute("destino", destino);
            sesion.setAttribute("idRolActividad", idRolActividad);
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
