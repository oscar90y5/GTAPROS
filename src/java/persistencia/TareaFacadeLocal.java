/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Tarea;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rebeca
 */
@Local
public interface TareaFacadeLocal {

    void create(Tarea tarea);

    void edit(Tarea tarea);

    void remove(Tarea tarea);

    Tarea find(Object id);

    List<Tarea> findAll();

    List<Tarea> findRange(int[] range);

    int count();
    
}
