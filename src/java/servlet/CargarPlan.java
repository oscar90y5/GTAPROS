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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        System.out.println("LLEGA 1");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        String accion = (String) request.getParameter("accion");
        int idProject = (Integer) sesion.getAttribute("idProject");
        System.out.println(accion + " " + idProject);
        Proyecto proyect = proyectoFacade.find(idProject);
        System.out.println("proyecto" + proyect.getCargado());
        String rd = "cargarPlan.jsp";
        if (accion.equals("Cargar")) {
            //Recogida del archivo
            Part filePart = request.getPart("file");
            InputStream fileContent = filePart.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(fileContent));
            System.out.println("LLEGA 4");
            //Tratamiento de actividades
            String line;
            String[] array;
            Actividad a;
            String[] predecesoras;
            String nombre;
            String rol;
            Rol r;
            List<Actividad> pres;
            HashMap<String, Actividad> mapaActs = new HashMap<>();
            HashMap<String, String[]> mapaPres = new HashMap<>();
            //Obtencion del siguiente indice
            int nextId = actividadFacade.findAll().size() + 1;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                array = line.split(";");
                nombre = array[0];
                rol = array[2];
                r = rolFacade.findByNombreRolAndIdProyecto(rol, proyect);
                System.out.println("rol " + r.getNombreRol());
                predecesoras = array[array.length - 2].split(",");
                pres = new ArrayList<>();
                a = new Actividad(nextId, r, nombre, Integer.parseInt(array[array.length - 1]), array[1], proyect);
                nextId = nextId + 1;
                a.setEstado("Abierto");
                mapaActs.put(nombre, a);
                mapaPres.put(nombre, predecesoras);
            }
            //Movida de predecesoras
            List<Actividad> pred;
            List<Actividad> suce;
            Actividad actual;
            for (String s : mapaActs.keySet()) {
                pred = new ArrayList<>();
                suce = new ArrayList<>();
                actual = mapaActs.get(s);
                for (String p : mapaPres.get(actual.getNombre())) {
                    suce.add(actual);
                    pred.add(mapaActs.get(p));
                }
                actual.setActividadList(pred);
                actual.setActividadList1(suce);
                //Creaccion
                System.out.println("Actividad " + actual.toString());
                actividadFacade.create(actual);
            }

            System.out.println("Termina");
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
