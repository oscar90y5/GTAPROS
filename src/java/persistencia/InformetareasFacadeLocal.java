/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Informetareas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author miki
 */
@Local
public interface InformetareasFacadeLocal {

    void create(Informetareas informetareas);

    void edit(Informetareas informetareas);

    void remove(Informetareas informetareas);

    Informetareas find(Object id);

    List<Informetareas> findAll();

    List<Informetareas> findRange(int[] range);

    int count();

    List<Informetareas> findByIdActividad(Object idActividad);
    
    List<Informetareas> findByIdProyectoAndEstadoAndDni(Object idProyecto, Object idEstado, Object dni);

    List<Informetareas> findByEstado(Object estado);
}
