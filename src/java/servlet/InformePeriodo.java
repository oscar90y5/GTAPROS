/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Actividad;
import dominio.Proyecto;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.ProyectoFacadeLocal;

/**
 *
 * @author Rebeca
 */
@WebServlet(name = "InformePeriodo", urlPatterns = {"/InformePeriodo"})
public class InformePeriodo extends HttpServlet {

    @EJB
    private ProyectoFacadeLocal proyectoFacade;

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
        String fecha1= null;
        String fecha2 = null;
        ArrayList<Actividad> actPeriodo = new ArrayList<>();
        String infor = (String) request.getParameter("infor");
        String rd = "informePeriodo.jsp";
        try{
            fecha1 = (String) request.getParameter("fecha1");
            fecha2 = (String) request.getParameter("fecha2");
        }catch (NullPointerException e){ }
        
        if(fecha1==null || fecha2==null){
            rd = "jefeProyecto.jsp";
        }else{
            Date fechaInicio = obtenerFecha(fecha1);
            Date fechaFinal =  obtenerFecha(fecha2);
            Date fechaActual = new Date();
            String stringP = (String) sesion.getAttribute("idP");
            int idP = Integer.valueOf(stringP);
            Proyecto p = proyectoFacade.find(idP);
            List<Actividad> actividades = p.getActividadList();
            
            if(infor.equals("realplanificado")){
                for(Actividad a: actividades){
                    Date fechaIniA = null;
                    Date fechaFinA = null;
                    try{
                        fechaIniA = a.getFechaInicio();
                        fechaFinA = a.getFechaFin();
                    }catch(NullPointerException e){ }
                    //Si fechaIniA==null actividad no ha empezado, imposible que entre en periodo
                    //Si fechaFinA==null aun no ha acabado, no tenemos su tiempoReal
                    if(fechaIniA!=null && fechaFinA!=null){
                        if((fechaInicio.before(fechaIniA) || fechaIniA.equals(fechaInicio))
                            && (fechaFinA.before(fechaFinal) || fechaFinal.equals(fechaFinA)))
                            actPeriodo.add(a);
                    }
                }
                
                request.setAttribute("fecha1", fecha1);
                request.setAttribute("fecha2", fecha2);
                request.setAttribute("datos", actPeriodo);
                sesion.removeAttribute("idP");
                rd = "informePeriodo.jsp?infor=realplanificado";
                
            }if(infor.equals("recursos")){
                if(fechaInicio.before(fechaActual)){
                    request.setAttribute("datos", "porBuscar");
                    rd = "informePeriodo.jsp?infor=recursos&error=dias";
                }else{   
                    for(Actividad a: actividades){
                        Date fechaIniA = null;
                        Date fechaFinA = null;
                        try{
                            fechaIniA = a.getFechaInicio();
                            fechaFinA = a.getFechaFin();
                        }catch(NullPointerException e){ }
                        /*Buscamos actividades por realizar (o en realizacion), 
                        *en presente o futuro, da igual cuando hayan empezado
                        *importa que no hayan acabado
                        */
                        if((fechaIniA==null || fechaIniA!=null) && fechaFinA==null)
                            actPeriodo.add(a);
                        if(fechaIniA!=null && fechaFinA!=null){
                            if((fechaFinal.before(fechaFinA) || fechaFinal.equals(fechaFinA)))
                                actPeriodo.add(a);
                        }                
                    }
                    
                request.setAttribute("fecha1", fecha1);
                request.setAttribute("fecha2", fecha2);
                request.setAttribute("datos", actPeriodo);
                sesion.removeAttribute("idP");
                rd = "informePeriodo.jsp?infor=recursos";
                } 
                
            }if(infor.equals("trabajadores")){
                if(fechaInicio.before(fechaActual)){
                    request.setAttribute("datos", "porBuscar");
                    rd = "informePeriodo.jsp?infor=trabajadores&error=dias";
                }else{ 
                    for(Actividad a: actividades){
                        Date fechaIniA = null;
                        Date fechaFinA = null;
                        try{
                            fechaIniA = a.getFechaInicio();
                            fechaFinA = a.getFechaFin();
                        }catch(NullPointerException e){ }
                        /*Buscamos actividades que ya tengan una tarea con
                        * un esfuerzo real dedicado por el trabajador. fechaIniA 
                        * no puede ser null porque no habria empezado la actividad
                        * (no habria tareas asignadas)
                        */
                        if((fechaIniA!=null) && fechaFinA==null)
                            actPeriodo.add(a);
                        if(fechaIniA!=null && fechaFinA!=null){
                            if((fechaFinal.before(fechaFinA) || fechaFinal.equals(fechaFinA)))
                                actPeriodo.add(a);
                        }                
                    }
                    
                request.setAttribute("fecha1", fecha1);
                request.setAttribute("fecha2", fecha2);
                request.setAttribute("datos", actPeriodo);
                sesion.removeAttribute("idP");
                rd = "informePeriodo.jsp?infor=trabajadores";
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

     public Date obtenerFecha(String fecha){
          String[] partes = fecha.split("/");
          Date myDate = null;
          try {
              String dateString = partes[2] + "-" + partes[1] + "-" + partes[0];
              DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
              myDate = formatter.parse(dateString);
          } catch (ParseException ex) {
              Logger.getLogger(CargarPlan.class.getName()).log(Level.SEVERE, null, ex);
          }
          return myDate;
      }
}
