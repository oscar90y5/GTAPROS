/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miki
 */
@Entity
@Table(name = "rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
    , @NamedQuery(name = "Rol.findByIdRol", query = "SELECT r FROM Rol r WHERE r.idRol = :idRol")
    , @NamedQuery(name = "Rol.findByNombreRol", query = "SELECT r FROM Rol r WHERE r.nombreRol = :nombreRol")
    , @NamedQuery(name = "Rol.findByTipoCategoria", query = "SELECT r FROM Rol r WHERE r.tipoCategoria = :tipoCategoria")
    , @NamedQuery(name = "Rol.findByNombreRolAndIdProyecto", query = "SELECT r FROM Rol r WHERE r.nombreRol = :nombreRol AND r.idProyecto = :idProyecto")})
@JsonIgnoreProperties(value = {"miembroList", "actividadList"})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idRol")
    private Integer idRol;
    @Size(max = 30)
    @Column(name = "nombreRol")
    private String nombreRol;
    @Column(name = "tipoCategoria")
    private Integer tipoCategoria;
    @OneToMany(mappedBy = "idRol", fetch = FetchType.EAGER)
    private List<Miembro> miembroList;
    @JoinColumn(name = "idProyecto", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Proyecto idProyecto;
    @OneToMany(mappedBy = "idRol", fetch = FetchType.EAGER)
    private List<Actividad> actividadList;

    public Rol() {
    }

    public Rol(Integer idRol) {
        this.idRol = idRol;
    }
    
    public Rol(Integer idRol, String nombreRol){
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Integer getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(Integer tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

    @XmlTransient
    public List<Miembro> getMiembroList() {
        return miembroList;
    }

    public void setMiembroList(List<Miembro> miembroList) {
        this.miembroList = miembroList;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    @XmlTransient
    public List<Actividad> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Rol[ idRol=" + idRol + " ]";
    }
    
}
