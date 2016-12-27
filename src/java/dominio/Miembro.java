/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miki
 */
@Entity
@Table(name = "Miembro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Miembro.findAll", query = "SELECT m FROM Miembro m")
    , @NamedQuery(name = "Miembro.findByIdMiembro", query = "SELECT m FROM Miembro m WHERE m.idMiembro = :idMiembro")
    , @NamedQuery(name = "Miembro.findByTipoRol", query = "SELECT m FROM Miembro m WHERE m.tipoRol = :tipoRol")
    , @NamedQuery(name = "Miembro.findByParticipacion", query = "SELECT m FROM Miembro m WHERE m.participacion = :participacion")})
public class Miembro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMiembro")
    private Integer idMiembro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 19)
    @Column(name = "tipoRol")
    private String tipoRol;
    @Column(name = "participacion")
    private Integer participacion;
    @ManyToMany(mappedBy = "miembroList", fetch = FetchType.EAGER)
    private List<Actividad> actividadList;
    @JoinColumn(name = "dni", referencedColumnName = "dni")
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario dni;
    @JoinColumn(name = "idProyecto", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Proyecto idProyecto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miembro", fetch = FetchType.EAGER)
    private List<Tarea> tareaList;

    public Miembro() {
    }

    public Miembro(Integer idMiembro) {
        this.idMiembro = idMiembro;
    }

    public Miembro(Integer idMiembro, String tipoRol) {
        this.idMiembro = idMiembro;
        this.tipoRol = tipoRol;
    }

    public Integer getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(Integer idMiembro) {
        this.idMiembro = idMiembro;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }

    public Integer getParticipacion() {
        return participacion;
    }

    public void setParticipacion(Integer participacion) {
        this.participacion = participacion;
    }

    @XmlTransient
    public List<Actividad> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }

    public Usuario getDni() {
        return dni;
    }

    public void setDni(Usuario dni) {
        this.dni = dni;
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
        hash += (idMiembro != null ? idMiembro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Miembro)) {
            return false;
        }
        Miembro other = (Miembro) object;
        if ((this.idMiembro == null && other.idMiembro != null) || (this.idMiembro != null && !this.idMiembro.equals(other.idMiembro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Miembro[ idMiembro=" + idMiembro + " ]";
    }
    
}
