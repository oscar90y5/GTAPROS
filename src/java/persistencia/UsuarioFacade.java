    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author miki
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "GTAPROSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public List<Usuario> finByAdmin(Boolean admin){
        return em.createNamedQuery("Usuario.findByEsAdmin").setParameter("esAdmin", admin).getResultList();
    }
    
    @Override
    public Usuario findByNombreCompleto(String nombreCompleto){
        return (Usuario) em.createNamedQuery("Usuario.findByNombreCompleto").setParameter("nombreCompleto", nombreCompleto).getSingleResult();
    }
     
    @Override
    public List<Usuario> findByTipoCategoria(int tipoCategoria){
        return em.createNamedQuery("Usuario.findByTipoCategoria").setParameter("tipoCategoria", tipoCategoria).getResultList();
    }
    
}
