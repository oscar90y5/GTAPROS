/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Actividad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author miki
 */
@Stateless
public class ActividadFacade extends AbstractFacade<Actividad> implements ActividadFacadeLocal {

    @PersistenceContext(unitName = "GTAPROSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActividadFacade() {
        super(Actividad.class);
    }

    @Override
    public List<Actividad> findByIdProject(Object idProyecto) {
        return em.createNamedQuery("Actividad.findByIdProyecto").setParameter("idProyecto", idProyecto).getResultList();
    }

    @Override
    public List<Actividad> findByIdProyectoAndDni(Object idProyecto, Object dni) {
        return em.createNamedQuery("Actividad.findByIdProyectoAndDni").setParameter("idProyecto", idProyecto).setParameter("dni", dni).getResultList();
    }

}
