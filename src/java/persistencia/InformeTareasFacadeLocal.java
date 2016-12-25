/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.InformeTareas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rebeca
 */
@Local
public interface InformeTareasFacadeLocal {

    void create(InformeTareas informeTareas);

    void edit(InformeTareas informeTareas);

    void remove(InformeTareas informeTareas);

    InformeTareas find(Object id);

    List<InformeTareas> findAll();

    List<InformeTareas> findRange(int[] range);

    int count();
    
}
