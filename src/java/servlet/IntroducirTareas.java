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
        
        if(accion.equals("Cancelar")){
                        System.out.println("Accion cancelar");

                   //Obtenemos la lista de actividades activas pertenecientes al proyecto y al usuario
                    List<Actividad> actividades = actividadFacade.findActiveActivities(proyecto);
                    List<Miembro> miembros;
                    Actividad a;
                    //si el for es de la forma: (Actividad a : actividades) falla al borrar elementos.
                    for(int i = 0; i<actividades.size();i++){
                        a = actividades.get(i);
                        if(!a.getMiembroList().contains(miembroFacade.findByIdProyectoAndDni(proyecto, user))){
                            actividades.remove(a);
                            i--;
                        }
                    }
                    if(actividades.size()==1){
                        //redirigimos a introducir tareas con el id de la actividad
                        response.sendRedirect("introducirTareas.jsp?idActividad="+actividades.get(0).getId());
                    } else {
                        request.setAttribute("actividades", actividades);
                        request.setAttribute("destino", "introducirTareas.jsp");
                        rd = "actividades.jsp";
                    }                             
        }
        
        if(accion.equals("Aceptar")){
            System.out.println("acion Aceptar");
            //AÑADIR ID AL INFORME
            Informetareas informe = new Informetareas();
            informe.setSemana(Date.valueOf(request.getParameter("semana")));
            System.out.println("setSemana");

            informe.setFechaEnvio(Date.from(Instant.now()));
            System.out.println("set fecha envio");

            informe.setEstado("PendienteAprobacion");
            System.out.println("set estado");
            

            Integer idMiembro = miembroFacade.findByIdProyectoAndDni(proyecto, user).getIdMiembro();
            Integer idActividad = Integer.valueOf((String) request.getServletContext().getAttribute("idActividad"));
            Tarea nuevaTarea;
                        System.out.println("1");
                        System.out.println("miembro: "+idMiembro);
                        System.out.println("actividad: "+idActividad);


            nuevaTarea = new Tarea("TratoConUsuarios",idMiembro, idActividad);
                        System.out.println("1.1");

            nuevaTarea.setIdInforme(informe);
                        System.out.println("1.2");

            nuevaTarea.setEsfuerzoReal(Integer.valueOf(request.getParameter("tratoUsuarios")));
                        System.out.println("1.3");

            tareaFacade.create(nuevaTarea);
                        System.out.println("2");

            nuevaTarea = new Tarea("ReunionesInternasExternas",idMiembro,idActividad);
            nuevaTarea.setIdInforme(informe);
            nuevaTarea.setEsfuerzoReal(Integer.valueOf(request.getParameter("reuniones")));
            tareaFacade.create(nuevaTarea);
                        System.out.println("3");

            nuevaTarea = new Tarea("LecturaRevisionDocumentacion",idMiembro,idActividad);
            nuevaTarea.setIdInforme(informe);
            nuevaTarea.setEsfuerzoReal(Integer.valueOf(request.getParameter("leerRevisarDocumentacion")));
            tareaFacade.create(nuevaTarea);
                        System.out.println("4");

            nuevaTarea = new Tarea("ElaboracionDocumentacion",idMiembro,idActividad);
            nuevaTarea.setIdInforme(informe);
            nuevaTarea.setEsfuerzoReal(Integer.valueOf(request.getParameter("elaborDocumentacion")));
            tareaFacade.create(nuevaTarea);
                        System.out.println("5");

            nuevaTarea = new Tarea("DesarrolloVerificacionProgramas",idMiembro,idActividad);
            nuevaTarea.setIdInforme(informe);
            nuevaTarea.setEsfuerzoReal(Integer.valueOf(request.getParameter("programar")));
            tareaFacade.create(nuevaTarea);
                        System.out.println("6");

            nuevaTarea = new Tarea("FormacionUsuariosYOtros",idMiembro,idActividad);
            nuevaTarea.setIdInforme(informe);
            nuevaTarea.setEsfuerzoReal(Integer.valueOf(request.getParameter("formar")));
            tareaFacade.create(nuevaTarea);
                        System.out.println("7");


            informetareasFacade.create(informe);

            
            request.setAttribute("informe", informe);
            
            
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
