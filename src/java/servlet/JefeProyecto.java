/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Proyecto;
import dominio.Actividad;
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
import persistencia.ProyectoFacadeLocal;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Rebeca
 */
@WebServlet(name = "JefeProyecto", urlPatterns = {"/JefeProyecto"})
public class JefeProyecto extends HttpServlet {

    @EJB
    private ActividadFacadeLocal actividadFacade;

    @EJB
    private ProyectoFacadeLocal proyectoFacade;
    
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
         try (PrintWriter out = response.getWriter()) {
            HttpSession sesion = request.getSession();
            String id = (String) sesion.getAttribute("idUser");
            String accion = request.getParameter("accion");
            System.out.println(accion);
            List<Proyecto> allProyects = proyectoFacade.findAll();
            ArrayList<Proyecto> proyects = new ArrayList<>();
            List<Actividad> allActivities =  actividadFacade.findAll();
            ArrayList<Actividad> activities = new ArrayList<>();
            String rd = "jefeProyecto.jsp";
            
            if (accion != null) {
                if (accion.equals("Cargar Plan de proyecto")){
                     for(Proyecto p: allProyects){
                         //if(p.getPlan()==null && p.getJefe().getDni().equals(id))
                         proyects.add(p);
                    }
                    rd = "proyectos.jsp?accion=cargar";
                }     
                if (accion.equals("AÃ±adir personas a proyecto")){
                    for(Proyecto p: allProyects){
                        if(p.getEstado().equals("EnCurso") /*&& p.getJefe().getDni().equals(id)*/)
                             proyects.add(p);
                    }
                    rd = "proyectos.jsp?accion=asignarProyecto";
                }
                if (accion.equals("AÃ±adir personas a actividad")){
                    for(Proyecto p: allProyects){
                        if(p.getEstado().equals("EnCurso") /*&& p.getJefe().getDni().equals(id)*/)
                             proyects.add(p);
                    }
                    rd = "proyectos.jsp?accion=asignarActividad";
                }
                if (accion.equals("Fijar fin de actividad")){
                    for(Actividad a: allActivities){
                        if(a.getEstado().equals("PendienteDeCierre"))
                            activities.add(a);
                    }
                    rd = "actividades.jsp";
                }
                if (accion.equals("Obtener informes")){
                    for(Proyecto p: allProyects) proyects.add(p);
                    rd= "proyectos.jsp?accion=informes";
                }
                if (accion.equals("Consultar datos de actividad")){
                    for(Proyecto p: allProyects){
                        if(p.getEstado().equals("EnCurso") /*&& p.getJefe().getDni().equals(id)*/)
                             proyects.add(p);
                    }
                    rd = "proyectos.jsp?accion=consultar";
                }
                if (accion.equals("Fijar vacaciones")) rd = "vacaciones.jsp";
            }
            
            if(!proyects.isEmpty()){
                String json = mapper.writeValueAsString(proyects);
                request.setAttribute("proyectos", json);
            }
            if(!activities.isEmpty()){
                String json = mapper.writeValueAsString(activities);
                request.setAttribute("actividades", json);
            }
            
            response.sendRedirect(rd);
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
