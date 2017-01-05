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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        
        System.out.println(accion + " " + idProject);
        Proyecto proyect = proyectoFacade.find(idProject);
        //Para despachar
        String rd = "cargarPlan.jsp";
        
        if (accion.equals("Cargar")) {
            //Obtencion Fecha de inicio
            String fecha = (String) request.getParameter("fecha");
            String[] partes = fecha.split("/");
            Date myDate = null;
            try {
                String dateString = partes[2] + "-" + partes[1] + "-" + partes[0];
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                myDate = formatter.parse(dateString);
            } catch (ParseException ex) {
                Logger.getLogger(CargarPlan.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Recogida del archivo
            Part filePart = request.getPart("file");
            InputStream fileContent = filePart.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(fileContent));
            //Tratamiento de actividades
            String line, nombre;
            String[] array, listaPres = null;
            Actividad actual;
            Rol r;
            Integer duracion;
            List<Actividad> pres;
            //Para tratamiento de predecesoras
            HashMap<String, Actividad> mapaActs = new HashMap<>();
            HashMap<String, String[]> mapaPres = new HashMap<>();
            int nextId = actividadFacade.count() + 1; //getNextId
            while ((line = br.readLine()) != null) {
                array = line.split(";");
                duracion = Integer.parseInt(array[array.length - 1]);
                nombre = array[0];
                pres = new ArrayList<>();
                actual = new Actividad(nextId, nombre, duracion, array[1], proyect);
                nextId = nextId + 1;
                actual.setEstado("Abierto");
                if (duracion != 0) {
                    r = rolFacade.findByNombreRolAndIdProyecto(array[2], proyect.getId());
                    actual.setIdRol(r);
                }
                mapaActs.put(nombre, actual);
                if (!array[array.length - 2].equals("")) {
                    listaPres = array[array.length - 2].split(",");
                }
                mapaPres.put(nombre, listaPres);
                actividadFacade.create(actual);
            }
            //Tratamiento predecesoras
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
                if (actual.getActividadList().isEmpty()) {
                    actual.setFechaInicio(myDate);
                }
                //Modificacion
                System.out.println("Actividad agregada" + actual.toString());
                actividadFacade.edit(actual);
            }

            //Cambiar proyecto de Pendiente-->EnCurso
            proyect.setEstado("EnCurso");
            proyect.setFechaInicio(myDate);
            proyectoFacade.edit(proyect);
            rd = "exito.jsp";
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
