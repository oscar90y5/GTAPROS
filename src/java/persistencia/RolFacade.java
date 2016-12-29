/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Rol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author miki
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> implements RolFacadeLocal {

    @PersistenceContext(unitName = "GTAPROSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }

    @Override
    public Rol findByNombreRolAndIdProyecto(Object nombreRol, Object idProyecto) {
        return (Rol) em.createNamedQuery("Rol.findByNombreRolAndIdProyecto").setParameter("nombreRol", nombreRol).setParameter("idProyecto", idProyecto).getSingleResult();
    }

}
