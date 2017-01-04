/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miki
 */
@Entity
@Table(name = "tarea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarea.findAll", query = "SELECT t FROM Tarea t")
    , @NamedQuery(name = "Tarea.findByEsfuerzoReal", query = "SELECT t FROM Tarea t WHERE t.esfuerzoReal = :esfuerzoReal")
    , @NamedQuery(name = "Tarea.findByTipo", query = "SELECT t FROM Tarea t WHERE t.tareaPK.tipo = :tipo")
    , @NamedQuery(name = "Tarea.findByIdInforme", query = "SELECT t FROM Tarea t WHERE t.tareaPK.idInforme = :idInforme")
    , @NamedQuery(name = "Tarea.findByIdMiembro", query = "SELECT t FROM Tarea t WHERE t.idMiembro.idMiembro = :idMiembro")
    , @NamedQuery(name = "Tarea.findByIdActividadAndMiembro", query = "SELECT t FROM Tarea t WHERE t.idActividad.id = :idActividad AND t.idMiembro.idMiembro = :idMiembro")})
public class Tarea implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TareaPK tareaPK;
    @Column(name = "esfuerzoReal")
    private Integer esfuerzoReal;
    @JoinColumn(name = "idActividad", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Actividad idActividad;
    @JoinColumn(name = "idInforme", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Informetareas informetareas;
    @JoinColumn(name = "idMiembro", referencedColumnName = "idMiembro")
    @ManyToOne(fetch = FetchType.EAGER)
    private Miembro idMiembro;

    public Tarea() {
    }

    public Tarea(TareaPK tareaPK) {
        this.tareaPK = tareaPK;
    }

    public Tarea(String tipo, int idInforme) {
        this.tareaPK = new TareaPK(tipo, idInforme);
    }

    public TareaPK getTareaPK() {
        return tareaPK;
    }

    public void setTareaPK(TareaPK tareaPK) {
        this.tareaPK = tareaPK;
    }

    public Integer getEsfuerzoReal() {
        return esfuerzoReal;
    }

    public void setEsfuerzoReal(Integer esfuerzoReal) {
        this.esfuerzoReal = esfuerzoReal;
    }

    public Actividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Actividad idActividad) {
        this.idActividad = idActividad;
    }

    public Informetareas getInformetareas() {
        return informetareas;
    }

    public void setInformetareas(Informetareas informetareas) {
        this.informetareas = informetareas;
    }

    public Miembro getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(Miembro idMiembro) {
        this.idMiembro = idMiembro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tareaPK != null ? tareaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarea)) {
            return false;
        }
        Tarea other = (Tarea) object;
        if ((this.tareaPK == null && other.tareaPK != null) || (this.tareaPK != null && !this.tareaPK.equals(other.tareaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Tarea[ tareaPK=" + tareaPK + " ]";
    }

}
