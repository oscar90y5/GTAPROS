/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.Actividad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author miki
 */
@Local
public interface ActividadFacadeLocal {

    void create(Actividad actividad);

    void edit(Actividad actividad);

    void remove(Actividad actividad);

    Actividad find(Object id);

    Actividad findById(int id);

    List<Actividad> findByIdProject(Object idProyecto);

    List<Actividad> findAll();

    List<Actividad> findRange(int[] range);

    int count();

    List<Actividad> findByIdProyectoAndDni(Object idProyecto, Object dni);

    List<Actividad> findActiveActivities(Object idProyecto);

    Actividad findById(Object id);

}
