/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Miembro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rebeca
 */
@Stateless
public class MiembroFacade extends AbstractFacade<Miembro> implements MiembroFacadeLocal {

    @PersistenceContext(unitName = "GTAPROSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MiembroFacade() {
        super(Miembro.class);
    }
    
    @Override
    public List<Miembro> findByDni(Object dni){
        return em.createNamedQuery("Miembro.findByDni").setParameter("dni", dni).getResultList();
    }
    
}
