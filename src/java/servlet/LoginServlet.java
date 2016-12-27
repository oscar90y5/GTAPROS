/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.UsuarioFacadeLocal;

/**
 *
 * @author miki
 */
public class LoginServlet extends HttpServlet {

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
          response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            HttpSession sesion = request.getSession();

            String id=request.getParameter("id");
            String password=request.getParameter("password");

            ArrayList<Usuario> usuarios = (ArrayList<Usuario>) usuarioFacade.findAll();
            for(Usuario user: usuarios){
                if(id.equals(user.getDni()) && password.equals(user.getClave())){
                    sesion.setAttribute("idUser", id);
                    switch (user.getTipoCategoria()) {
                        case 0:
                            {
                                RequestDispatcher rd = request.getRequestDispatcher("jefeProyecto.jsp");
                                rd.forward(request,response);
                                break;
                            }
                        case 1:
                            {
                                RequestDispatcher rd = request.getRequestDispatcher("desarrollador.jsp");
                                rd.forward(request,response);
                                break;
                            }
                        case 2:
                            {
                                RequestDispatcher rd = request.getRequestDispatcher("administrador.jsp");
                                rd.forward(request,response);
                                break;
                            }
                        default:
                            break;
                    }
                }else{
                    out.print("<p style=\"color:red\">Sorry username or password error</p>");
                    RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
                    rd.include(request,response);
                }
                    
            }
            out.close();
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
