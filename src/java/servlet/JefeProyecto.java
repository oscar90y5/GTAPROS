/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dominio.Proyecto;
import dominio.Actividad;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.ProyectoFacadeLocal;
import com.fasterxml.jackson.databind.ObjectMapper;
import dominio.Miembro;
import dominio.Usuario;
import java.util.ArrayList;
import persistencia.MiembroFacadeLocal;
import persistencia.UsuarioFacadeLocal;

/**
 *
 * @author Rebeca
 */
@WebServlet(name = "JefeProyecto", urlPatterns = {"/JefeProyecto"})
public class JefeProyecto extends HttpServlet {

    @EJB
    private MiembroFacadeLocal miembroFacade;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    
    @EJB
    private ProyectoFacadeLocal proyectoFacade;
    
    public static final ObjectMapper mapper = new ObjectMapper();
    public static ArrayList<Integer> participacion = new ArrayList<>();

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
        String dni = (String) sesion.getAttribute("idUser");
        String accion = request.getParameter("accion");
        List<Proyecto> proyects = null;
        List<Usuario> users = null;
        Proyecto proyect = proyectoFacade.find(idProject);
        List<Actividad> activities =  proyect.getActividadList();
        String rd = "jefeProyecto.jsp";

        if (accion != null) {
            if (accion.equals("Cargar Plan de proyecto"))
                 rd = "cargarPlan.jsp";  

            if (accion.equals("Asignar personas a proyecto")){
                users = usuariosDisponibles(dni);
                rd = "usuarios.jsp";
            }
            if (accion.equals("Asignar personas a actividad")){
                for(Actividad a: activities){
                    if(!a.getEstado().equals("Abierto"))
                        activities.remove(a);
                }
                rd = "actividades.jsp";
            }
            if (accion.equals("Fijar fin de actividad")){
                for(Actividad a: activities){
                    if(!a.getEstado().equals("PendienteDeCierre"))
                        activities.remove(a);
                }
                rd = "actividades.jsp";
            }
            if (accion.equals("Obtener informes")){
                proyects = proyectoFacade.findAll();
                rd= "proyectos.jsp?accion=informes";
            }
            if (accion.equals("Consultar datos de actividad"))
               rd = "actividades.jsp";

            if (accion.equals("Fijar vacaciones")) 
                rd = "vacaciones.jsp";
        }

        if(proyects!=null){
            String json = mapper.writeValueAsString(proyects);
            request.setAttribute("proyectos", json);
        }
        if(users!=null){
            String jsonU = null;
            String jsonP = null;
            if(!users.isEmpty()){
                jsonU = mapper.writeValueAsString(users);
                jsonP = mapper.writeValueAsString(participacion);
            }
            request.setAttribute("usuarios", jsonU); 
            request.setAttribute("participacion", jsonP);
        }
        if(!activities.isEmpty()){
            String json = mapper.writeValueAsString(activities);
            request.setAttribute("actividades", json);
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

    private List<Usuario> usuariosDisponibles(String dni) {
        //Busco todos los empleados menos el administrador
        List<Usuario> users = usuarioFacade.finByAdmin(Boolean.FALSE);
        List<Usuario> aux = new ArrayList<>();
        users.stream().filter((u) -> (!u.getDni().equals(dni))).forEachOrdered((u) -> {
            int porcent = 0;
            List<Miembro> members = miembroFacade.findByDni(u);
            //Compruebo su participacion total en todos los proyectos de los que son miembros
            for(Miembro m: members)
                porcent += m.getParticipacion();
            if (porcent<100) {
                aux.add(u);
                participacion.add(porcent);
            }
        });
        return aux;
    }

}
