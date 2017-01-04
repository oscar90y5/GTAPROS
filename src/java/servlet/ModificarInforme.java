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
import java.util.Arrays;
import java.util.HashMap;
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
@WebServlet(name = "ModificarInforme", urlPatterns = {"/ModificarInforme"})
public class ModificarInforme extends HttpServlet {

    @EJB
    private TareaFacadeLocal tareaFacade;

    @EJB
    private InformetareasFacadeLocal informetareasFacade;

    @EJB
    private MiembroFacadeLocal miembroFacade;

    @EJB
    private ActividadFacadeLocal actividadFacade;

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
        System.out.println("ModificarInforme");
        String accion = request.getParameter("accion");
        String rd = "VolverMenu";
        if (accion.equals("Aceptar")) {
            HttpSession sesion = request.getSession();
            String dni = (String) sesion.getAttribute("idUser");
            int idProject = (Integer) sesion.getAttribute("idProject");
            Integer idActividad = (Integer) sesion.getAttribute("idActividad");
            sesion.removeAttribute("idActividad");
            Actividad actividad = actividadFacade.find(idActividad);
            System.out.println("idProyecto -" + idProject + "- idActividad -" + idActividad + "- dni -" + dni + "-");
            int idInforme = Integer.parseInt(request.getParameter("idInforme"));
            System.out.println("idInforme " + idInforme);
            Miembro miembro = miembroFacade.findByDniAndIdProyecto(dni, idProject);
            System.out.println("miembro " + miembro);

            Informetareas i = informetareasFacade.find(idInforme);

            //Tareas insertadas en el form
            List<String> nombres = Arrays.asList("TratoConUsuarios", "ReunionesInternasExternas",
                    "LecturaRevisionDocumentacion", "ElaboracionDocumentacion",
                    "DesarrolloVerificacionProgramas", "FormacionUsuariosYOtros");
            String s;
            int dur;
            HashMap<String, Integer> tareasModificadas = new HashMap<String, Integer>();
            for (String nombre : nombres) {
                s = request.getParameter(nombre);
                if (s != null && !s.equals("")) {
                    dur = Integer.parseInt(s);
                    if (dur > 0 && !tareasModificadas.containsKey(nombre)) {
                        tareasModificadas.put(nombre, dur);
                    }
                }
            }
            for (String una : tareasModificadas.keySet()) {
                System.out.println(una + " - " + tareasModificadas.get(una));
            }

            //Extraemos tareas de la BD
            List<Tarea> tareasInBD = new ArrayList<Tarea>();
            for (Tarea t : actividad.getTareaList()) {
                if (t.getIdInforme().getId().equals(i.getId())) {
                    tareasInBD.add(t);
                }
            }
            //Comparamos con las tareas del informe
            Tarea tar;
            for (String modif : tareasModificadas.keySet()) {
                if ((tar = estaEnBD(modif, tareasInBD)) != null) {
                    //Editar
                    tar.setEsfuerzoReal(tareasModificadas.get(tar.getTareaPK().getTipo()));
                    tareaFacade.edit(tar);
                } else {
                    //Insertar
                    tar = new Tarea(modif, miembro.getIdMiembro(), idActividad);
                    tar.setIdInforme(i);
                    tar.setEsfuerzoReal(tareasModificadas.get(tar.getTareaPK().getTipo()));
                    tareaFacade.create(tar);
                }
            }

            rd = "exito.jsp";
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

    private Tarea estaEnBD(String modif, List<Tarea> tareasInBD) {
        for (Tarea t : tareasInBD) {
            if (t.getTareaPK().getTipo().equals(modif)) {
                return t;
            }
        }
        return null;
    }

}
