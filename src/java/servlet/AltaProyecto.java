/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Proyecto;
import dominio.Rol;
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

/**
 *
 * @author claramorrondo
 */
@WebServlet(name = "AltaProyecto", urlPatterns = {"/AltaProyecto"})
public class AltaProyecto extends HttpServlet {

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
             System.out.println("ENTRA");
           HttpSession sesion = request.getSession();
            String nombreProyecto = request.getParameter("nombreProyecto");
           int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            System.out.println("Counter"+cantidad);
            String[] nombreRol;
            Integer [] ids;
            ids = new Integer[cantidad];
            nombreRol = new String[cantidad];
            for(int i =0;i<cantidad;i++){
                System.out.println("  "+ request.getParameter("roles"+i));

                ids[i] = Integer.parseInt(request.getParameter("roles"+i));
                nombreRol[i] = request.getParameter("nombre"+i);
                System.out.println("IDS"+ ids[i]);
                 System.out.println("nombres"+nombreRol[i]);
            }
           // String [] nombreRoles = (String[]) request.getAttribute("rolesNombre");
            List<Rol> listaRol = new ArrayList<>();
            if(request.getParameter("altaProyectoBtn").equals("asignarJefe")){ 
                 System.out.println("ENTRA ASIGNAR");
                //for(int i =0; i<roles.length;i++){
                  //Rol r = new Rol(roles[i], nombreRoles[i]);
                  //rolFacade.create(r);
                  //listaRol.add(r);
                //}
                int id = proyectoFacade.count();
                System.out.println("ID proyecto:" + id);
                System.out.println("Nombre del proyecto"+ nombreProyecto);
                Proyecto p = new Proyecto(id,nombreProyecto);
                proyectoFacade.create(p);
                response.sendRedirect("AsignarResponsable.jsp"); 
            }
            else{
                if(request.getParameter("altaProyectoBtn").equals("asignarJefeLater")){
                System.out.println("ENTRA ASIGNAR LUEGO");
                
                int id = proyectoFacade.count();
                Proyecto p = new Proyecto(id,nombreProyecto);
                proyectoFacade.create(p);
                response.sendRedirect("administrador.jsp"); 
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
