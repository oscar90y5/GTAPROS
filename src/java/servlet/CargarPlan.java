/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Actividad;
import dominio.Proyecto;
import dominio.Rol;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import persistencia.ActividadFacadeLocal;
import persistencia.ProyectoFacadeLocal;
import persistencia.RolFacadeLocal;

/**
 *
 * @author Rebeca
 */
@WebServlet(name = "CargarPlan", urlPatterns = {"/CargarPlan"})
@MultipartConfig
public class CargarPlan extends HttpServlet {

    @EJB
    private RolFacadeLocal rolFacade;

    @EJB
    private ActividadFacadeLocal actividadFacade;

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
        String accion = (String) request.getParameter("accion");
        int idProject = (Integer) sesion.getAttribute("idProject");
        String fecha = (String) request.getParameter("fecha");
        System.out.println(accion + " " + idProject);
        Proyecto proyect = proyectoFacade.find(idProject);
        System.out.println("proyecto" + proyect.getCargado());
        String rd = "cargarPlan.jsp";
        if (accion.equals("Cargar")) {
            //Recogida del archivo
            Part filePart = request.getPart("file");
            InputStream fileContent = filePart.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(fileContent));
            //Tratamiento de actividades
            String line, nombre;
            String[] array, listaPres;
            Actividad actual;
            Rol r;
            Integer duracion;
            List<Actividad> pres;
            //Para tratamiento de predecesoras
            HashMap<String, Actividad> mapaActs = new HashMap<>();
            HashMap<String, String[]> mapaPres = new HashMap<>();
            int nextId = actividadFacade.findAll().size() + 1; //getNextId
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                array = line.split(";");
                duracion = Integer.parseInt(array[array.length - 1]);
                nombre = array[0];
                pres = new ArrayList<>();
                actual = new Actividad(nextId, nombre, duracion, array[1], proyect);
                nextId = nextId + 1;
                actual.setEstado("Abierto");
                if (duracion != 0) {
                    r = rolFacade.findByNombreRolAndIdProyecto(array[2], proyect);
                    actual.setIdRol(r);
                }
                mapaActs.put(nombre, actual);
                System.out.println("predecesoras -" + array[3].split(",")[0] + "-");
                listaPres = array[array.length - 2].split(",");
                if (listaPres.length == 1 && listaPres[0].equals("")) {
                    listaPres = null;
                }
                mapaPres.put(nombre, listaPres);
                actividadFacade.create(actual);
            }
            //Predecesoras y fecha de inicio
            String[] partes = fecha.split("/");
//            int dia, mes, anyo;
//            dia = Integer.parseInt(partes[0]);
//            mes = Integer.parseInt(partes[1]);
//            anyo = Integer.parseInt(partes[2]);
//            GregorianCalendar c = new GregorianCalendar(anyo, mes, dia);
            java.util.Date myDate = null;
            try {

                String dateString = partes[2] + "-" + partes[1] + "-" + partes[0];
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println("dateString " + dateString);
                myDate = formatter.parse(dateString);
                System.out.println("myDate " + myDate.toString());
            } catch (ParseException ex) {
                Logger.getLogger(CargarPlan.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Actividad> pred, suce;
            for (String s : mapaActs.keySet()) {
                pred = new ArrayList<>();
                suce = new ArrayList<>();
                actual = mapaActs.get(s);
                if (mapaPres.get(actual.getNombre()) != null) {
                    for (String p : mapaPres.get(actual.getNombre())) {
                        suce.add(actual);
                        pred.add(mapaActs.get(p));
                    }
                }
                actual.setActividadList(pred);
                actual.setActividadList1(suce);
                System.out.println("act " + actual.getId() + "pred " + actual.getActividadList().size());
                System.out.println(actual.getActividadList().isEmpty());
                System.out.println(actual.getActividadList().isEmpty() ? "null" : actual.getActividadList());

                if (actual.getActividadList().isEmpty()) {
                    actual.setFechaInicio(myDate);
                }
                //Modificacion
                System.out.println("Actividad " + actual.toString());
                actividadFacade.edit(actual);
            }
            System.out.println("Fin");
        }
        if (accion.equals("Cancelar")) {
            rd = "jefeProyecto.jsp";
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
