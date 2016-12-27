/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
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
    , @NamedQuery(name = "Actividad.findByFechaInicio", query = "SELECT a FROM Actividad a WHERE a.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Actividad.findByFechaFin", query = "SELECT a FROM Actividad a WHERE a.fechaFin = :fechaFin")
    , @NamedQuery(name = "Actividad.findByDuracion", query = "SELECT a FROM Actividad a WHERE a.duracion = :duracion")
    , @NamedQuery(name = "Actividad.findByEstado", query = "SELECT a FROM Actividad a WHERE a.estado = :estado")
    , @NamedQuery(name = "Actividad.findByRol", query = "SELECT a FROM Actividad a WHERE a.rol = :rol")
    , @NamedQuery(name = "Actividad.findByDescripcion", query = "SELECT a FROM Actividad a WHERE a.descripcion = :descripcion")})
public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "duracion")
    private Integer duracion;
    @Size(max = 17)
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 19)
    @Column(name = "rol")
    private String rol;
    @Size(max = 300)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinTable(name = "AsignacionActividad", joinColumns = {
        @JoinColumn(name = "idActividad", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "idMiembro", referencedColumnName = "idMiembro")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Miembro> miembroList;
    @JoinTable(name = "Predecesora", joinColumns = {
        @JoinColumn(name = "idPredecedora", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "idSucesora", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Actividad> actividadList;
    @ManyToMany(mappedBy = "actividadList", fetch = FetchType.EAGER)
    private List<Actividad> actividadList1;
    @JoinColumn(name = "idProyecto", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Proyecto idProyecto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actividad", fetch = FetchType.EAGER)
    private List<Tarea> tareaList;

    public Actividad() {
    }

    public Actividad(Integer id) {
        this.id = id;
    }

    public Actividad(Integer id, String rol) {
        this.id = id;
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Miembro> getMiembroList() {
        return miembroList;
    }

    public void setMiembroList(List<Miembro> miembroList) {
        this.miembroList = miembroList;
    }

    @XmlTransient
    public List<Actividad> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }

    @XmlTransient
    public List<Actividad> getActividadList1() {
        return actividadList1;
    }

    public void setActividadList1(List<Actividad> actividadList1) {
        this.actividadList1 = actividadList1;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    @XmlTransient
    public List<Tarea> getTareaList() {
        return tareaList;
    }

    public void setTareaList(List<Tarea> tareaList) {
        this.tareaList = tareaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Actividad[ id=" + id + " ]";
    }
    
}
