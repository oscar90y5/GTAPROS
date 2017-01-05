/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Actividad;
import dominio.Informetareas;
import dominio.Miembro;
import dominio.Tarea;
import java.io.IOException;
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
import persistencia.InformetareasFacadeLocal;
import persistencia.MiembroFacadeLocal;
import persistencia.TareaFacadeLocal;

/**
 *
 * @author miki
 */
@WebServlet(name = "ConsultarTareas", urlPatterns = {"/ConsultarTareas"})
public class ConsultarTareas extends HttpServlet {

    @EJB
    private MiembroFacadeLocal miembroFacade;

    @EJB
    private TareaFacadeLocal tareaFacade;

    @EJB
    private ActividadFacadeLocal actividadFacade;

    @EJB
    private InformetareasFacadeLocal informetareasFacade;

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
        String dni = (String) sesion.getAttribute("idUser");
        int idProject = (Integer) sesion.getAttribute("idProject");
        Integer idActividad = Integer.parseInt(request.getParameter("idActividad"));
        Actividad actividad = actividadFacade.find(idActividad);
        System.out.println("idProyecto -" + idProject + "- idActividad -" + idActividad + "- dni -" + dni + "-");
        System.out.println("actividad string " + actividad);
        Miembro miembro = miembroFacade.findByDniAndIdProyecto(dni, idProject);
        System.out.println("miembro " + miembro);

        List<Tarea> tareas;
        if (miembro.getIdRol().getNombreRol().equals("JefeProyecto")) {
            //Todas las tareas del proyecto
            tareas = new ArrayList<Tarea>();
            for (Tarea t : tareaFacade.findAll()) {
                //Mostrar tarea
                if (t.getIdActividad().getId().equals(idActividad)) {
                    tareas.add(t);
                    System.out.println("tarea " + t.getTareaPK().getTipo() + " = "
                            + t.getEsfuerzoReal()
                            + " idInforme=" + t.getInformetareas()
                            + " idActividad="
                            + t.getIdActividad().getId() + " idMiembro=" + t.getIdMiembro().getIdMiembro());
                }
            }
        } else {
            System.out.println("ei");
            for (Tarea t : actividad.getTareaList()) {
                //Mostrar tarea
                if (t.getIdMiembro().getIdMiembro().equals(miembro.getIdMiembro())) {
                    System.out.println("tarea " + t.getTareaPK().getTipo() + " = "
                            + t.getEsfuerzoReal() + " idInforme=" + t.getInformetareas().getId() + " idActividad="
                            + t.getIdActividad().getId() + " idMiembro=" + t.getIdMiembro().getIdMiembro());
                }
            }
            System.out.println("ei");
            System.out.println("empieza");
            tareas = new ArrayList<Tarea>();
            for (Tarea t : tareaFacade.findAll()) {
                //Mostrar tarea
                if (t.getIdActividad().getId().equals(idActividad)
                        && t.getIdMiembro().getIdMiembro().equals(miembro.getIdMiembro())) {
                    tareas.add(t);
                    System.out.println("tarea " + t.getTareaPK().getTipo() + " = "
                            + t.getEsfuerzoReal()
                            + " idInforme=" + t.getInformetareas()
                            + " idActividad="
                            + t.getIdActividad().getId() + " idMiembro=" + t.getIdMiembro().getIdMiembro());
                }
            }
            System.out.println("no llega");
        }

        request.setAttribute("tareas", tareas);
        String rd = "tareas.jsp";
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
