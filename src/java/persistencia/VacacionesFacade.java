/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Vacaciones;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author miki
 */
@Stateless
public class VacacionesFacade extends AbstractFacade<Vacaciones> implements VacacionesFacadeLocal {

    @PersistenceContext(unitName = "GTAPROSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VacacionesFacade() {
        super(Vacaciones.class);
    }
    
}
