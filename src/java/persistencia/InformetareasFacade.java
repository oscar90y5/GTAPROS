/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Informetareas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author miki
 */
@Stateless
public class InformetareasFacade extends AbstractFacade<Informetareas> implements InformetareasFacadeLocal {

    @PersistenceContext(unitName = "GTAPROSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InformetareasFacade() {
        super(Informetareas.class);
    }

    @Override
    public List<Informetareas> findByIdActividad(Object idActividad) {
        return em.createNamedQuery("Informetareas.findByIdActividad").setParameter("idActividad", idActividad).getResultList();
    }

    @Override
    public List<Informetareas> findByIdProyectoAndEstadoAndDni(Object idProyecto, Object estado, Object dni) {
        return em.createNamedQuery("Informetareas.finfByProyectoAndEstadoAndDni").setParameter("idProyecto", idProyecto).setParameter("estado", estado).setParameter("dni", dni).getResultList();
    }
}
