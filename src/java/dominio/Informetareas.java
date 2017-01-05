/*
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author miki
 */
@Entity
@Table(name = "InformeTareas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Informetareas.findAll", query = "SELECT i FROM Informetareas i")
    , @NamedQuery(name = "Informetareas.findById", query = "SELECT i FROM Informetareas i WHERE i.id = :id")
    , @NamedQuery(name = "Informetareas.findByFechaEnvio", query = "SELECT i FROM Informetareas i WHERE i.fechaEnvio = :fechaEnvio")
    , @NamedQuery(name = "Informetareas.findByEstado", query = "SELECT i FROM Informetareas i WHERE i.estado = :estado")
    , @NamedQuery(name = "Informetareas.findBySemana", query = "SELECT i FROM Informetareas i WHERE i.semana = :semana")
    , @NamedQuery(name = "Informetareas.findByIdActividad", query = "SELECT i FROM Informetareas i, Tarea t WHERE i.id = t.informetareas.id AND t.idActividad.id = :idActividad")
    , @NamedQuery(name = "Informetareas.finfByProyectoAndEstadoAndDni", query = "SELECT i FROM Informetareas i, Tarea t, Miembro m WHERE i.id = t.informetareas.id AND i.estado = :estado AND t.idMiembro = m.idMiembro AND m.idProyecto = :idProyecto AND m.dni = :dni")})
@JsonIgnoreProperties(value = {"tareaList"})
public class Informetareas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "informetareas", fetch = FetchType.EAGER)
    private List<Tarea> tareaList;

    public Informetareas() {
    }

    public Informetareas(Integer id) {
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
 
     public String getSemanaPrettyPrinter() {
         if (semana != null) {
            SimpleDateFormat df = new SimpleDateFormat("'Semana' W (dd/MM/yyyy)");
            return df.format(semana);
        }
        return "";
    }

    public void setSemana(Date semana) {
        this.semana = semana;
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
        if (!(object instanceof Informetareas)) {
            return false;
        }
        Informetareas other = (Informetareas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Informetareas[ id=" + id + " ]";
    }

    public String getFechaEnvioPrettyPrinter() {
        if (fechaEnvio != null) {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            return df.format(fechaEnvio);
        }
        return " - ";
    }

}
