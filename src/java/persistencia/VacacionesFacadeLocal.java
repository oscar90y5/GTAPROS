/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Vacaciones;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author miki
 */
@Local
public interface VacacionesFacadeLocal {

    void create(Vacaciones vacaciones);

    void edit(Vacaciones vacaciones);

    void remove(Vacaciones vacaciones);

    Vacaciones find(Object id);

    List<Vacaciones> findAll();

    List<Vacaciones> findRange(int[] range);

    int count();
    
}
