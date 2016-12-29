/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    private ProyectoFacadeLocal proyectoFacade;

    @EJB
    private MiembroFacadeLocal miembroFacade;

    @EJB
    private InformetareasFacadeLocal informetareasFacade;

    
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
        String informe = request.getParameter("informe");
        String stringP = request.getParameter("proyecto");
        int idP = Integer.parseInt(stringP);
        System.out.println("servlet.GenerarInforme.processRequest()"+informe);
        System.out.println("servlet.GenerarInforme.processRequest()"+idP);
        Proyecto p = proyectoFacade.find(idP);
        List<Tarea> trabInfor = new ArrayList<>();
        String rd = "informes.jsp";
        
        if(informe.equals("Trabajadores/Actividades por periodo semanal")){
            //Primero selecciona semana, luego genera informe
        }
        if(informe.equals("Trabajadores/Informes pendientes de Envio")){
            List<Miembro> trabajadores = miembroFacade.findByIdProyecto(p);
            for(Miembro m: trabajadores){
                List<Tarea> tareas = m.getTareaList();
                for(Tarea t: tareas){
                    Informetareas inf = t.getIdInforme();
                    if(inf.getEstado().equals("PendienteEnvio"))
                        trabInfor.add(t);
                }
            }
            rd = "informe.jsp?estado=pendienteEnvio";
        }
        if(informe.equals("Trabajadores/Informes pendientes de Aprobacion")){
            List<Miembro> trabajadores = miembroFacade.findByIdProyecto(p);
            for(Miembro m: trabajadores){
                List<Tarea> tareas = m.getTareaList();
                for(Tarea t: tareas){
                    Informetareas inf = t.getIdInforme();
                    if(inf.getEstado().equals("PendienteAprobacion"))
                        trabInfor.add(t);
                }
            }
            rd = "informe.jsp?estado=pendienteAprob";
        }
        if(informe.equals("Tiempo real/planificado de actividades por periodo")){
            //Primero mostrar calendario (similar vacaciones.jsp), luego generar informe
        }
        if(informe.equals("Actividades con tiempo real mayor del esperado")){
            //Generar informe
        }
        if(informe.equals("Actividades/Recursos por periodo posterior")){
            //Primero pedir días despues de fecha actual, luego genera informe
        }
        if(informe.equals("Trabajadores/Actividades/Tiempo por periodo posterior")){
            //Primero pedir días despues de fecha actual, luego genera informe
        }
        if(informe.equals("Informe general")){
            //Generar informe
        }
        
        if(!trabInfor.isEmpty()){
            String json = mapper.writeValueAsString(trabInfor);
            request.setAttribute("datos", json);
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
