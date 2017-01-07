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
        String accion = request.getParameter("accion");
        String rd = "VolverMenu";
        if (accion.equals("Aceptar")) {
            HttpSession sesion = request.getSession();
            String dni = (String) sesion.getAttribute("idUser");
            int idProject = (Integer) sesion.getAttribute("idProject");
            Integer idActividad = (Integer) sesion.getAttribute("idActividad");
            sesion.removeAttribute("idActividad");
            Actividad actividad = actividadFacade.find(idActividad);
            int idInforme = Integer.parseInt(request.getParameter("idInforme"));
            Miembro miembro = miembroFacade.findByDniAndIdProyecto(dni, idProject);

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
                    if (!tareasModificadas.containsKey(nombre)) {
                        tareasModificadas.put(nombre, dur);
                    }
                }
            }
            //Extraemos tareas de la BD
            List<Tarea> tareasInBD = new ArrayList<Tarea>();
            for (Tarea t : tareaFacade.findAll()) {
                if (t.getInformetareas().getId().equals(i.getId())) {
                    tareasInBD.add(t);
                }
            }
            //Comparamos con las tareas del informe
            Tarea tar;
            for (String modif : tareasModificadas.keySet()) {
                if ((tar = estaEnBD(modif, tareasInBD)) != null) {
                    //Editar o Borrar
                    if (tareasModificadas.get(tar.getTareaPK().getTipo()) > 0) {
                        tar.setEsfuerzoReal(tareasModificadas.get(tar.getTareaPK().getTipo()));
                        tareaFacade.edit(tar);
                    } else {
                        tareaFacade.remove(tar);
                    }
                } else {
                    if (tareasModificadas.get(modif) > 0) {
                        //Insertar
                        tar = new Tarea(modif, i.getId());
                        tar.setInformetareas(i);
                        tar.setIdActividad(actividad);
                        tar.setIdMiembro(miembro);
                        tar.setEsfuerzoReal(tareasModificadas.get(tar.getTareaPK().getTipo()));
                        tareaFacade.create(tar);
                    }
                }
            }

            //Si es rechazado se pone como pendiente de envio
            if (i.getEstado().equals("Rechazado")) {
                i.setEstado("PendienteEnvio");
                informetareasFacade.edit(i);
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
