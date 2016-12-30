/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author miki
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);
    
    Usuario findByNombreCompleto(Object nombreCompleto);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);
    
    List<Usuario> finByAdmin(Boolean admin);

    int count();
    
}
