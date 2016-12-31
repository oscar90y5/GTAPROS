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
@WebServlet(name = "AltaProyecto", urlPatterns = {"/AltaProyecto"})
public class AltaProyecto extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
           HttpSession sesion = request.getSession();
            
       if(!request.getParameter("altaProyectoBtn").equals("cancelar")){ 
           
            String nombreProyecto = request.getParameter("nombreProyecto");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            String[] nombreRol;
            Integer [] ids;
            ids = new Integer[cantidad];
            nombreRol = new String[cantidad];
            for(int i =0;i<cantidad;i++){
                ids[i] = Integer.parseInt(request.getParameter("roles"+i));
                nombreRol[i] = request.getParameter("nombre"+i);
            }
           
            int id = (proyectoFacade.count()+1);
            List<Rol> listaRol = new ArrayList<>();
            
            Proyecto p = new Proyecto(id,nombreProyecto);
            proyectoFacade.create(p);
            for(int j = 0; j<ids.length;j++){
                    Rol r = new Rol(ids[j],nombreRol[j]);
                    r.setIdProyecto(p);
                    rolFacade.create(r);
                    listaRol.add(r);
            }

            p.setRolList(listaRol);            
            if(request.getParameter("altaProyectoBtn").equals("asignarJefe")){ 
                ArrayList<String> usuariosDisponibles = new ArrayList<>();
                //Recogemos todos los usuarios y miembros almacenados en la BD
                List<Usuario> usuarios =usuarioFacade.findAll();
                List<Miembro> miembros = miembroFacade.findAll();
                
                //Recorremos todos los usuarios
                for(int i =0;i<usuarios.size();i++){
                   //Si tiene categoria 1 es porque puede ser jefe de proyecto
                   if(usuarios.get(i).getTipoCategoria()==1){
                    String dniUsuario = usuarios.get(i).getDni();
                    //Si no está en la tabla de miembros es porque está disponible
                    if(!miembros.contains(usuarios.get(i))){
                        usuariosDisponibles.add(usuarios.get(i).getNombreCompleto()); 
                    }
                    //Si está en la tabla de mimebros, ver que no sea ya jefe de otro proyecto
                    else{
                        if(miembroFacade.findByDni(dniUsuario).get(0).getIdRol().getTipoCategoria()!=1){
                          usuariosDisponibles.add(usuarios.get(i).getNombreCompleto()); 
                        }
                    }
                   }    
                }
                sesion.setAttribute("usuariosDisponibles", usuariosDisponibles);
                sesion.setAttribute("nombreProyecto", nombreProyecto);
                response.sendRedirect("AsignarResponsableSesion.jsp"); 
            }
            else{
                if(request.getParameter("altaProyectoBtn").equals("asignarJefeLater")){
                    response.sendRedirect("administrador.jsp"); 
                }
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
