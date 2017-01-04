/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Tarea;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author miki
 */
@Stateless
public class TareaFacade extends AbstractFacade<Tarea> implements TareaFacadeLocal {

    @PersistenceContext(unitName = "GTAPROSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TareaFacade() {
        super(Tarea.class);
    }

    @Override
    public List<Tarea> findByIdActividadAndMiembro(Object idActividad, Object idMiembro) {
        return em.createNamedQuery("Tarea.findByIdActividadAndMiembro").setParameter("idActividad", idActividad).setParameter("idMiembro", idMiembro).getResultList();
    }
    
    @Override
    public List<Tarea> findByIdInforme(Object idInforme){
        return em.createNamedQuery("Tarea.findByIdInforme").setParameter("idInforme", idInforme).getResultList();
    }
    
     @Override
    public List<Tarea> findByIdMiembro(Object idMiembro){
        return em.createNamedQuery("Tarea.findByIdMiembro").setParameter("idMiembro", idMiembro).getResultList();
    }

}
