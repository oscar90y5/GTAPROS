/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dominio.Miembro;
import dominio.Proyecto;
import dominio.Usuario;
import java.io.IOException;
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
import persistencia.UsuarioFacadeLocal;

/**
 *
 * @author Rebeca
 */
@WebServlet(name = "ObtenerInformes", urlPatterns = {"/ObtenerInformes"})
public class ObtenerInformes extends HttpServlet {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    @EJB
    private ProyectoFacadeLocal proyectoFacade;

    @EJB
    private MiembroFacadeLocal miembroFacade;

    public static final ObjectMapper mapper = new ObjectMapper();
    
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
        String url = request.getParameter("id");
        int idP = Integer.parseInt(url);
        String idUser = (String) sesion.getAttribute("idUser");
        int idProject = (Integer) sesion.getAttribute("idProject");
        Proyecto proyecto = proyectoFacade.find(idP);
        Usuario user = usuarioFacade.find(idUser);
        List<Miembro> roles = miembroFacade.findByDni(user);
        
        String rd = "proyectos.jsp";
        
        for(Miembro m: roles){
            System.out.println("servlet.ObtenerInformes.processRequest()"+ roles);
            if(m.getIdProyecto().getId().equals(idP)){
                if(m.getIdRol().getNombreRol().equals("JefeProyecto")){
                    String json = mapper.writeValueAsString(proyecto);
                    request.setAttribute("proyecto", json);
                    rd = "informes.jsp?in=true";
                }else{
                    String json = mapper.writeValueAsString(proyecto);
                    request.setAttribute("proyecto", proyecto);
                    rd = "informeDesarrollador.jsp";
                }
            }else
               rd = "informes.jsp?in=false";
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
