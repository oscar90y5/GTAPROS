/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Proyecto;
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

/**
 *
 * @author claramorrondo
 */
@WebServlet(name = "AltaProyecto", urlPatterns = {"/AltaProyecto"})
public class AltaProyecto extends HttpServlet {

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
            String nombreProyecto = request.getParameter("nombreProyecto");
            String [] roles = (String[]) request.getAttribute("roles[]");
            List<Rol> listaRol;
            for(int i =0;i<roles.length;i++){
                
            }
            if(request.getParameter("altaProyectoBtn").equals("asignarJefe")){ 
                Proyecto p = new Proyecto();
                listaRol = request.getAttribute("listaRoles");
                p.setNombre(nombreProyecto);
                p.setRolList(listaRol);
                proyectoFacade.create(p);
            }
            else{
                if(request.getParameter("altaProyectoBtn").equals("asignarJefeLater")){
                    Proyecto p = new Proyecto();
                listaRol = request.getAttribute("listaRoles");
                    p.setNombre(nombreProyecto);
                    p.setRolList(listaRol);
                    proyectoFacade.create(p);
                }
                response.sendRedirect("Administrador.jsp"); 
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
