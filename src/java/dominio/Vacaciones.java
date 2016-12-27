/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rebeca
 */
@Entity
@Table(name = "Vacaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vacaciones.findAll", query = "SELECT v FROM Vacaciones v")
    , @NamedQuery(name = "Vacaciones.findByFechaInicio", query = "SELECT v FROM Vacaciones v WHERE v.vacacionesPK.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Vacaciones.findByFechaFin", query = "SELECT v FROM Vacaciones v WHERE v.fechaFin = :fechaFin")
    , @NamedQuery(name = "Vacaciones.findByDni", query = "SELECT v FROM Vacaciones v WHERE v.vacacionesPK.dni = :dni")})
public class Vacaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VacacionesPK vacacionesPK;
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "dni", referencedColumnName = "dni", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Vacaciones() {
    }

    public Vacaciones(VacacionesPK vacacionesPK) {
        this.vacacionesPK = vacacionesPK;
    }

    public Vacaciones(Date fechaInicio, String dni) {
        this.vacacionesPK = new VacacionesPK(fechaInicio, dni);
    }

    public VacacionesPK getVacacionesPK() {
        return vacacionesPK;
    }

    public void setVacacionesPK(VacacionesPK vacacionesPK) {
        this.vacacionesPK = vacacionesPK;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vacacionesPK != null ? vacacionesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacaciones)) {
            return false;
        }
        Vacaciones other = (Vacaciones) object;
        if ((this.vacacionesPK == null && other.vacacionesPK != null) || (this.vacacionesPK != null && !this.vacacionesPK.equals(other.vacacionesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Vacaciones[ vacacionesPK=" + vacacionesPK + " ]";
    }
    
}
