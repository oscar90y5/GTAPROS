/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dominio.Actividad;
import dominio.Informetareas;
import dominio.Miembro;
import dominio.Proyecto;
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
import persistencia.ProyectoFacadeLocal;

/**
 *
 * @author Rebeca
 */
@WebServlet(name = "GenerarInforme", urlPatterns = {"/GenerarInforme"})
public class GenerarInforme extends HttpServlet {

    @EJB
    private InformetareasFacadeLocal informetareasFacade;

    @EJB
    private ActividadFacadeLocal actividadFacade;

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
        String informe = request.getParameter("informe");
        String stringP = request.getParameter("proyecto");
        int idP = Integer.parseInt(stringP);
        Proyecto p = proyectoFacade.find(idP);
        List<Actividad> datosActividad = new ArrayList<>();
        List<Informetareas> datosInforme = new ArrayList<>();
        String rd = "informes.jsp";

        if (informe.equals("Trabajadores/Actividades por periodo semanal")) {
            sesion.setAttribute("idP", stringP);
            request.setAttribute("datos", "porBuscar");
            rd = "informeSemana.jsp";
        }
        if (informe.equals("Trabajadores/Informes pendientes de Envio")) {
            List<Informetareas> allInfor = informetareasFacade.findAll();
            for(Informetareas i: allInfor){
                if(i.getEstado().equals("PendienteEnvio")){
                    List<Tarea> t = i.getTareaList();
                    //Cojo la primera tarea porque todas son del mismo miembro, mismo proyecto
                    if(t.get(0).getIdMiembro().getIdProyecto().getId()==idP)
                        datosInforme.add(i);
                }        
            }
            rd = "informe.jsp?infor=pendienteEnvio";
        }
        if (informe.equals("Trabajadores/Informes pendientes de Aprobacion")) {
            List<Informetareas> allInfor = informetareasFacade.findAll();
            for(Informetareas i: allInfor){
                if(i.getEstado().equals("PendienteAprobacion")){
                    List<Tarea> t = i.getTareaList();
                    //Cojo la primera tarea porque todas son del mismo miembro, mismo proyecto
                    if(t.get(0).getIdMiembro().getIdProyecto().getId()==idP)
                        datosInforme.add(i);
                }        
            }
            rd = "informe.jsp?infor=pendienteAprob";
        }
        
        if(informe.equals("Tiempo real/planificado de actividades por periodo")){
            sesion.setAttribute("idP", stringP);
            request.setAttribute("datos", "porBuscar");
            rd = "informePeriodo.jsp?infor=realplanificado";
        }
        if (informe.equals("Actividades con tiempo real mayor del esperado")) {
            List<Actividad> actividades = actividadFacade.findByIdProject(p);
            for (Actividad a : actividades) {
                int tiempoEstimado = a.getDuracion();
                List<Tarea> tareas = a.getTareaList();
                int tiempoReal = 0;
                for (Tarea t : tareas) {
                    tiempoReal += t.getEsfuerzoReal();
                }
                if (tiempoReal > tiempoEstimado) {
                    datosActividad.add(a);
                }
            }
            rd = "informe.jsp?infor=realmayor";
        }
        if(informe.equals("Actividades/Recursos por periodo posterior")){
            sesion.setAttribute("idP", stringP);
            request.setAttribute("datos", "porBuscar");
            rd = "informePeriodo.jsp?infor=recursos";
        }
        if(informe.equals("Trabajadores/Actividades/Tiempo por periodo posterior")){
            sesion.setAttribute("idP", stringP);
            request.setAttribute("datos", "porBuscar");
            rd = "informePeriodo.jsp?infor=trabajadores";
        }
        if(informe.equals("Informe general")){
             datosActividad = actividadFacade.findByIdProject(p);
             rd = "informe.jsp?infor=general";
        }
        
        if(informe.equals("Datos Actividad por periodo semanal")){
            sesion.setAttribute("idP", stringP);
            request.setAttribute("datos", "porBuscar");
            rd = "informeSemana.jsp?infor=actividades";
        }
        
        if(!datosActividad.isEmpty()) 
            request.setAttribute("datos", datosActividad);
        
        if(!datosInforme.isEmpty())
            request.setAttribute("datos", datosInforme);

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
