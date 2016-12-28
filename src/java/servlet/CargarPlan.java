/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Actividad;
import dominio.Proyecto;
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

/**
 *
 * @author Rebeca
 */
@WebServlet(name = "CargarPlan", urlPatterns = {"/CargarPlan"})
@MultipartConfig
public class CargarPlan extends HttpServlet {

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
            List<Actividad> pres;
//            HashMap<String, Actividad> mapaActs = new HashMap<>();
//            HashMap<String, String[]> mapaPres = new HashMap<>();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                array = line.split(";");
                nombre = array[0];
                predecesoras = array[array.length - 2].split(",");
                pres = new ArrayList<>();
//                for (String predecesora : predecesoras) {
//                    pres.add(actividadFacade.find(predecesora));
//                }
                a = new Actividad(array[2], Integer.parseInt(array[array.length - 1]), array[1], proyect, null);
//                mapaActs.put(nombre, a);
//                mapaPres.put(nombre, predecesoras);
                actividadFacade.create(a);
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
