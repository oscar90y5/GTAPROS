/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dominio.Miembro;
import dominio.Usuario;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.MiembroFacadeLocal;
import persistencia.RolFacadeLocal;
import persistencia.UsuarioFacadeLocal;

public class LoginServlet extends HttpServlet {

    @EJB
    private RolFacadeLocal rolFacade;
    public static final ObjectMapper mapper = new ObjectMapper();
    
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
          response.setContentType("text/html");
            HttpSession sesion = request.getSession();
            String id= request.getParameter("id");
            String password=request.getParameter("password");
            String rd = "index.jsp";
            
            Usuario user = usuarioFacade.find(id);
 
            if(user!=null) {
                if(password.equals(user.getClave())){
                    sesion.setAttribute("idUser", id);
                    if(user.getEsAdmin())
                       rd = "administrador.jsp";
                    else{
                        List<Miembro> miembros = miembroFacade.findByDni(user);
                        if(miembros.size()==1){
                            int idProject =  miembros.get(0).getIdProyecto().getId();
                            sesion.setAttribute("idProject", idProject);
                            if(miembros.get(0).getIdRol().getNombreRol().equals("JefeProyecto")) 
                                rd = "jefeProyecto.jsp";
                            else
                                rd = "desarrollador.jsp";
                        }else{
                            String json = mapper.writeValueAsString(miembros);
                            System.out.println("servlet.LoginServlet.processRequest()" + json);
                            request.setAttribute("misProjects", json);
                            rd = "misProyectos.jsp";
                        }
                    }     
                }
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
