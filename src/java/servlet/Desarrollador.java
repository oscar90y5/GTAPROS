/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Actividad;
import dominio.Miembro;
import dominio.Proyecto;
import dominio.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.ActividadFacadeLocal;
import persistencia.MiembroFacadeLocal;
import persistencia.ProyectoFacadeLocal;
import persistencia.UsuarioFacadeLocal;

/**
 *
 * @author oscar
 */
@WebServlet(name = "Desarrollador", urlPatterns = {"/Desarrollador"})
public class Desarrollador extends HttpServlet {

    @EJB
    private MiembroFacadeLocal miembroFacade;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    @EJB
    private ProyectoFacadeLocal proyectoFacade;

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
        HttpSession sesion = request.getSession();
        int idProject = (Integer) sesion.getAttribute("idProject");
        Proyecto proyecto = proyectoFacade.find(idProject);
        String dni = (String) sesion.getAttribute("idUser");
        Usuario user = usuarioFacade.find(dni);
        Miembro miembroActual = miembroFacade.findByIdProyectoAndDni(proyecto, user);
        //Proyecto proyect = proyectoFacade.find(idProject);
        System.out.println("Desarrollador: dni " + dni + " idProject " + idProject);
        String accion = request.getParameter("accion");
        String rd = "Desarrollador.jsp";
        if (accion != null) {
            if (accion.equals("Introducir tarea")) {
                //Obtenemos la lista de actividades activas pertenecientes al proyecto y al usuario
                List<Actividad> actividades = actividadFacade.findActiveActivities(proyecto);
                List<Miembro> miembros;
                Actividad a;
                System.out.println("actividades activas");
                //si el for es de la forma: (Actividad a : actividades) falla al borrar elementos.
                for (int i = 0; i < actividades.size(); i++) {
                    a = actividades.get(i);
                    if (!a.getMiembroList().contains(miembroActual)) {
                        System.out.println("borramos");
                        actividades.remove(a);
                        i--;
                        System.out.println("borrado");
                    }
                }
                System.out.println("actividades: " + actividades.size());

                if (actividades.size() == 1) {
                    //redirigimos a introducir tareas con el id de la actividad
                    response.sendRedirect("introducirTareas.jsp?idActividad=" + actividades.get(0).getId());
                } else {
                    request.setAttribute("actividades", actividades);
                    request.setAttribute("destino", "introducirTareas.jsp");
                    rd = "actividades.jsp";
                }
                System.out.println("salimos");

            }
            if (accion.equals("Modificar tareas activas")) {
                // out.print("Modificar datos de tareas en desarrollo....");
                List<Actividad> actividades = new ArrayList<>();
                for (Actividad a : miembroActual.getActividadList()) {
                    if(!a.getEstado().equalsIgnoreCase("Cerrado")){
                        actividades.add(a);
                    }
                }
                request.setAttribute("actividades", actividades);
                request.setAttribute("destino", "ModificarTarea");
                rd = "actividades.jsp";
            }
            if (accion.equals("Consultar datos de tareas")) {
                List<Actividad> actividades = new ArrayList<>();
                for (Actividad a : miembroActual.getActividadList()) {
                    if (a.getIdProyecto().getId().equals(idProject)) {
                        actividades.add(a);
                    }
                }
                System.out.println(actividades.size());
                request.setAttribute("actividades", actividades);
                request.setAttribute("destino", "ConsultarTareas");
                rd = "actividades.jsp";
            }
            if (accion.equals("Obtener informes")) {
                // out.print("Obtener informes en desarrollo....");
            }
            if (accion.equals("Fijar vacaciones")) {
                rd = "vacaciones.jsp";
            }
            if (accion.equals("Cerrar Sesion")) {
                rd = "index.jsp";
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

}
