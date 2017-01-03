/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Usuario;
import dominio.Vacaciones;
import dominio.VacacionesPK;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import persistencia.UsuarioFacadeLocal;
import persistencia.VacacionesFacadeLocal;

/**
 *
 * @author Rebeca
 */
@WebServlet(name = "FijarVacaciones", urlPatterns = {"/FijarVacaciones"})
public class FijarVacaciones extends HttpServlet {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    @EJB
    private VacacionesFacadeLocal vacacionesFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        String idUser = (String) sesion.getAttribute("idUser");
        Usuario user = usuarioFacade.find("idUser");
        String accion = (String) request.getParameter("accion");
        String rd = "exito.jsp";
        
        if(accion.equals("Cancelar")){
            //TO DO ALL
            rd = "desarrollador.jsp";
            rd = "administrador.jsp";
            rd = "jefeProyecto.jsp";
        
        }if(accion.equals("Aceptar")){
            String fecha1 = (String) request.getParameter("fecha1");
            String fecha2 = (String) request.getParameter("fecha2");  
            Date fechaInicio = obtenerFecha(fecha1);
            Date fechaFinal =  obtenerFecha(fecha2);
            Date fechaActual = new Date();
            long diferencia = fechaFinal.getTime() - fechaInicio.getTime();
            long dias = diferencia / (1000 * 60 * 60 * 24);
            List<Vacaciones> vacaciones = vacacionesFacade.findByUser(idUser);
            int diasFijados =0;


            for(Vacaciones v: vacaciones){
                Date fechaIni = v.getVacacionesPK().getFechaInicio();
                Date fechaFin = v.getFechaFin();
                diferencia = fechaFin.getTime() - fechaIni.getTime();
                diasFijados += diferencia / (1000 * 60 * 60 * 24);
                }

            if(fechaInicio.before(fechaActual))
                rd = "vacaciones.jsp?error=fechaPasada";
            if(fechaFinal.before(fechaInicio))
                rd = "vacaciones.jsp?error=fechaAnterior";
            if(diasFijados==28)
                rd = "vacaciones.jsp?error=maxFijadas";
            if(dias>14)
                rd = "vacaciones.jsp?error=maxDias";
            if(diasFijados+dias>=28)
                rd = "vacaciones.jsp?error=excedeDias";
            //NO SE PUEDE FIJAR EN EPOCA DE PROYECTO

            if(rd.equals("exito.jsp")){
                Vacaciones v = new Vacaciones();
                v.setUsuario(user);
                v.setFechaFin(fechaFinal);
                v.setVacacionesPK(new VacacionesPK(fechaInicio, idUser));
                vacacionesFacade.create(v);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(FijarVacaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(FijarVacaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
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
