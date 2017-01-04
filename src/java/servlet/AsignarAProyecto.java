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
 * @author Rebeca
 */
@WebServlet(name = "AsignarAProyecto", urlPatterns = {"/AsignarAProyecto"})
public class AsignarAProyecto extends HttpServlet {

    @EJB
    private RolFacadeLocal rolFacade;

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
        HttpSession sesion = request.getSession();
        String accion = request.getParameter("accion");
        String [] dnis = request.getParameterValues("dni");
        String [] categorias = request.getParameterValues("tipoCategoria");
        String[] participacion = request.getParameterValues("participacion");
        Integer idProject = (Integer) sesion.getAttribute("idProject");
        String rd = "exito.jsp";
        
        if(accion.equals("Cancelar"))
            rd = "jefeProyecto.jsp";
        if(accion.equals("Aceptar")){
            if(dnis==null)
                rd = "usuarios.jsp?error=dni";
            else{
                for(int i=0; i< dnis.length; i++){
                    System.out.println("servlet.AsignarAProyecto.processRequest()"+dnis.length+"*"+categorias.length+"*"+participacion.length);
                    //Comprobacion: estoy tomando un usuario checked
                    if(!dnis[i].equals("0")){
                        Miembro m = new Miembro();
                        Usuario u = usuarioFacade.find(dnis[i]);
                        m.setDni(u);
                        Proyecto p = proyectoFacade.find(idProject);
                        m.setIdProyecto(p);
                        Rol r = rolFacade.findByNombreRolAndIdProyecto(categorias[i], p);
                        m.setIdRol(r);
                        m.setParticipacion(Integer.parseInt(participacion[i]));
                        int id = miembroFacade.count()+1;
                        m.setIdMiembro(id);
                        miembroFacade.create(m);
                    }
                        //Limpio sesion
                        sesion.removeAttribute("usuarios");
                        sesion.removeAttribute("participacion");
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
