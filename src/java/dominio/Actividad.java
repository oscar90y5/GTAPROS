
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miki
 */
@Entity
@Table(name = "Actividad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a")
    , @NamedQuery(name = "Actividad.findById", query = "SELECT a FROM Actividad a WHERE a.id = :id")
    , @NamedQuery(name = "Actividad.findByIdProyecto", query = "SELECT a FROM Actividad a WHERE a.idProyecto = :idProyecto")
    , @NamedQuery(name = "Actividad.findByNombre", query = "SELECT a FROM Actividad a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Actividad.findByFechaInicio", query = "SELECT a FROM Actividad a WHERE a.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Actividad.findByFechaFin", query = "SELECT a FROM Actividad a WHERE a.fechaFin = :fechaFin")
    , @NamedQuery(name = "Actividad.findByDuracion", query = "SELECT a FROM Actividad a WHERE a.duracion = :duracion")
    , @NamedQuery(name = "Actividad.findByEstado", query = "SELECT a FROM Actividad a WHERE a.estado = :estado")
    , @NamedQuery(name = "Actividad.findByDescripcion", query = "SELECT a FROM Actividad a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Actividad.findByIdProyectoAndDni", query = "SELECT a FROM Actividad a, Miembro m WHERE a.idProyecto = :idProyecto AND a.idProyecto = m.idProyecto AND m.dni = :dni")
    , @NamedQuery(name = "Actividad.findActiveActivities", query = "SELECT a FROM Actividad a WHERE a.idProyecto = :idProyecto AND a.estado = 'Abierto'")}
    , @NamedQuery(name = "Actividad.findByIdProyectoAndDni", query = "SELECT a FROM Actividad a, Miembro m WHERE a.idProyecto = :idProyecto AND a.idProyecto = m.idProyecto AND m.dni = :dni")})
@JsonIgnoreProperties(value = {"actividadList", "actividadList1", "miembroList","tareaList"})