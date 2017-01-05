/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Actividad;
import dominio.Informetareas;
import dominio.Miembro;
import dominio.Proyecto;
import dominio.Tarea;
import dominio.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.ActividadFacadeLocal;
import persistencia.InformetareasFacadeLocal;
import persistencia.MiembroFacadeLocal;
import persistencia.ProyectoFacadeLocal;
import persistencia.TareaFacadeLocal;
import persistencia.UsuarioFacadeLocal;

/**
 *
 * @author oscar
 */
@WebServlet(name = "IntroducirTareas", urlPatterns = {"/IntroducirTareas"})
public class IntroducirTareas extends HttpServlet {

    @EJB
    private TareaFacadeLocal tareaFacade;

    @EJB
    private InformetareasFacadeLocal informetareasFacade;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    @EJB
    private ProyectoFacadeLocal proyectoFacade;

    @EJB
    private MiembroFacadeLocal miembroFacade;

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
        
        HttpSession sesion = request.getSession();
        
        int idProject = (Integer) sesion.getAttribute("idProject");
        Proyecto proyecto = proyectoFacade.find(idProject);
        
        String dni = (String) sesion.getAttribute("idUser");
        Usuario user = usuarioFacade.find(dni);
        
        String accion = (String) request.getParameter("accion");
        String rd = "desarrollador.jsp";
        
        if( accion.equals("Ahora") || accion.equals("Tarde") ){
            //AÑADIR ID AL INFORME
            Informetareas informe = new Informetareas(informetareasFacade.count()+1);
            informe.setSemana(Date.valueOf(request.getParameter("semana")));
            
            if(accion.equals("Ahora")){
                informe.setFechaEnvio(Date.from(Instant.now()));
                informe.setEstado("PendienteAprobacion");
            } else {
                informe.setEstado("PendienteEnvio");
            }
            
            informetareasFacade.create(informe);

            Integer idMiembro = miembroFacade.findByIdProyectoAndDni(proyecto, user).getIdMiembro();
           
            Integer idActividad = Integer.valueOf((String) request.getServletContext().getAttribute("idActividad"));
            
            Tarea nuevaTarea;
            
            String esfuerzo;

            esfuerzo = request.getParameter("tratoUsuarios");
            if(!esfuerzo.isEmpty() && !esfuerzo.equals("0")){
                nuevaTarea = new Tarea("TratoConUsuarios",idMiembro, idActividad);
                nuevaTarea.setIdInforme(informe);
                nuevaTarea.setEsfuerzoReal(Integer.valueOf(esfuerzo));
                tareaFacade.create(nuevaTarea);
            }
            
            esfuerzo = request.getParameter("reuniones");
            if(!esfuerzo.isEmpty() && !esfuerzo.equals("0")){
                nuevaTarea = new Tarea("ReunionesInternasExternas",idMiembro,idActividad);
                nuevaTarea.setIdInforme(informe);
                nuevaTarea.setEsfuerzoReal(Integer.valueOf(esfuerzo));
                tareaFacade.create(nuevaTarea);
            }
            
            esfuerzo = request.getParameter("leerRevisarDocumentacion");
            if(!esfuerzo.isEmpty() && !esfuerzo.equals("0")){
                nuevaTarea = new Tarea("LecturaRevisionDocumentacion",idMiembro,idActividad);
                nuevaTarea.setIdInforme(informe);
                nuevaTarea.setEsfuerzoReal(Integer.valueOf(esfuerzo));
                tareaFacade.create(nuevaTarea);
            }
            
            esfuerzo = request.getParameter("elaborDocumentacion");
            if(!esfuerzo.isEmpty() && !esfuerzo.equals("0")){
                nuevaTarea = new Tarea("ElaboracionDocumentacion",idMiembro,idActividad);
                nuevaTarea.setIdInforme(informe);
                nuevaTarea.setEsfuerzoReal(Integer.valueOf(esfuerzo));
                tareaFacade.create(nuevaTarea);
            }
            
            esfuerzo = request.getParameter("programar");
            if(!esfuerzo.isEmpty() && !esfuerzo.equals("0")){
                nuevaTarea = new Tarea("DesarrolloVerificacionProgramas",idMiembro,idActividad);
                nuevaTarea.setIdInforme(informe);
                nuevaTarea.setEsfuerzoReal(Integer.valueOf(esfuerzo));
                tareaFacade.create(nuevaTarea);
            }
            
            esfuerzo = request.getParameter("formar");
            if(!esfuerzo.isEmpty() && !esfuerzo.equals("0")){
                nuevaTarea = new Tarea("FormacionUsuariosYOtros",idMiembro,idActividad);
                nuevaTarea.setIdInforme(informe);
                nuevaTarea.setEsfuerzoReal(Integer.valueOf(esfuerzo));
                tareaFacade.create(nuevaTarea);
            }
        
            //Si no se ha introducido ninguna tarea borramos el informe
            /*INFORME.GETtAREAS O TAREA.FINDbYiDiNFORME
            if(){
                informetareasFacade.remove(informe);
            }*/
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
