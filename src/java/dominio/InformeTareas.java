/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author Rebeca
 */
@Entity
@Table(name = "InformeTareas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformeTareas.findAll", query = "SELECT i FROM InformeTareas i")
    , @NamedQuery(name = "InformeTareas.findById", query = "SELECT i FROM InformeTareas i WHERE i.id = :id")
    , @NamedQuery(name = "InformeTareas.findByFechaEnvio", query = "SELECT i FROM InformeTareas i WHERE i.fechaEnvio = :fechaEnvio")
    , @NamedQuery(name = "InformeTareas.findByEstado", query = "SELECT i FROM InformeTareas i WHERE i.estado = :estado")
    , @NamedQuery(name = "InformeTareas.findBySemana", query = "SELECT i FROM InformeTareas i WHERE i.semana = :semana")})
public class InformeTareas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "fechaEnvio")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @Size(max = 19)
    @Column(name = "estado")
    private String estado;
    @Column(name = "semana")
    @Temporal(TemporalType.DATE)
    private Date semana;
    @OneToMany(mappedBy = "idInforme")
    private Collection<Tarea> tareaCollection;

    public InformeTareas() {
    }

    public InformeTareas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getSemana() {
        return semana;
    }

    public void setSemana(Date semana) {
        this.semana = semana;
    }

    @XmlTransient
    public Collection<Tarea> getTareaCollection() {
        return tareaCollection;
    }

    public void setTareaCollection(Collection<Tarea> tareaCollection) {
        this.tareaCollection = tareaCollection;
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
        if (!(object instanceof InformeTareas)) {
            return false;
        }
        InformeTareas other = (InformeTareas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.InformeTareas[ id=" + id + " ]";
    }
    
}
