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
import dominio.Usuario;
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
import persistencia.InformetareasFacadeLocal;
import persistencia.MiembroFacadeLocal;
import persistencia.ProyectoFacadeLocal;
import persistencia.TareaFacadeLocal;
import persistencia.UsuarioFacadeLocal;

/**
 *
 * @author Rebeca
 */
@WebServlet(name = "InformeSemana", urlPatterns = {"/InformeSemana"})
public class InformeSemana extends HttpServlet {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    @EJB
    private TareaFacadeLocal tareaFacade;

    @EJB
    private InformetareasFacadeLocal informetareasFacade;

    @EJB
    private MiembroFacadeLocal miembroFacade;

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
        HttpSession sesion = request.getSession();
        String vista = (String) sesion.getAttribute("vista");
        String fecha1= null;
        String fecha2 = null;
        String rd;
        try{
            fecha1 = (String) request.getParameter("fecha1");
            fecha2 = (String) request.getParameter("fecha2");
        }catch (NullPointerException e){ }
        
        if(fecha1==null || fecha2==null){
            rd = vista;
        }else{
            System.out.println("**********"+fecha1+"-"+fecha2);
            Date fechaInicio = obtenerFecha(fecha1);
            Date fechaFinal =  obtenerFecha(fecha2);
            System.out.println("servlet.InformeSemana.processRequest()"+fechaInicio+"-"+fechaFinal);
            long diferencia = fechaFinal.getTime() - fechaInicio.getTime();
            long dias = diferencia / (1000 * 60 * 60 * 24);
            System.out.println("servlet.InformeSemana.processRequest()"+dias);
            String stringP = (String) sesion.getAttribute("idP");
            String idUser = (String) sesion.getAttribute("idUser");
            Usuario user = usuarioFacade.find(idUser);
            int idP = Integer.parseInt(stringP);
            Proyecto p = proyectoFacade.find(idP);

            if(dias%7!=0){
                request.setAttribute("datos", "porBuscar");
                rd = "informeSemana.jsp?error=dias";
            }else{
                if(vista.equals("desarrollador.jsp")){
                    List<Miembro> miembros = miembroFacade.findByDni(user);
                    int idM = 0;
                    for(Miembro m: miembros){
                        if(m.getIdProyecto().getId().equals(idP))
                            idM = m.getIdMiembro();
                    }
                    /*Nunca deberia darse idM=0 en este punto porque ya se ha comprobado
                    * antes de llegar a generar el informe que el usuario pertecene a el
                    */
                    List<Tarea> tareas = tareaFacade.findByIdMiembro(idM);
                    List<Tarea> tarPeriodo = new ArrayList<>();
                    
                    for(Tarea t: tareas){
                        if(!tarPeriodo.isEmpty()){
                            for(Tarea tP: tarPeriodo)
                                if(t.getTareaPK().getIdMiembro()!=(tP.getTareaPK().getIdMiembro()) &&
                                        t.getTareaPK().getIdActividad()!=(tP.getTareaPK().getIdActividad()))
                                    tarPeriodo.add(t);  
                        }else
                            tarPeriodo.add(t);
                    }
                    for(Tarea t: tarPeriodo){
                        tareas = new ArrayList<>();
                        Date semana = t.getIdInforme().getSemana();
                        /*Como seleccionamos semanas enteras si la fecha del
                        * informe se encuentra comprendida entre la inicial y 
                        *la final lo mostramos
                        */
                        if((fechaInicio.before(semana) || fechaInicio.equals(semana)) && semana.before(fechaFinal))
                            tareas.add(t);
                                    
                    }
                    
                    request.setAttribute("fecha1", fecha1);
                    request.setAttribute("fecha2", fecha2);
                    request.setAttribute("datos", tareas);
                    sesion.removeAttribute("idP");
                    
                }if(vista.equals("jefeProyecto.jsp")){
                    List<Actividad> actividades = p.getActividadList();
                    ArrayList<Actividad> actPeriodo = new ArrayList<>();
                    for(Actividad a: actividades){
                        Date fechaIniA = null;
                        Date fechaFinA = null;
                        try{
                            fechaIniA = a.getFechaInicio();
                            fechaFinA = a.getFechaFin();
                        }catch(NullPointerException e){ }
                        //Si fechaIniA==null actividad no ha empezado, imposible que entre en periodo
                        if(fechaIniA!=null && fechaFinA==null){
                            if(fechaIniA.before(fechaInicio) || a.getFechaInicio().equals(fechaInicio))
                                actPeriodo.add(a);
                        }if(fechaIniA!=null && fechaFinA!=null){
                            if((fechaIniA.before(fechaInicio) || fechaIniA.equals(fechaInicio))
                                && (fechaFinal.before(fechaFinA) || fechaFinal.equals(fechaFinA)))
                                actPeriodo.add(a);
                        }
                    }

                    request.setAttribute("fecha1", fecha1);
                    request.setAttribute("fecha2", fecha2);
                    request.setAttribute("datos", actPeriodo);
                    sesion.removeAttribute("idP");
                }

                rd = "informeSemana.jsp";
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
        String[] partes = fecha.split("-");
        Date myDate = null;
        try {
            String dateString = partes[2] + "-" + partes[1] + "-" + partes[0];
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            myDate = formatter.parse(dateString);
        } catch (ParseException ex) { }
        return myDate;
    }
}
