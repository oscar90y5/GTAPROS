/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author miki
 */
@Embeddable
public class TareaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 31)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idInforme")
    private int idInforme;

    public TareaPK() {
    }

    public TareaPK(String tipo, int idInforme) {
        this.tipo = tipo;
        this.idInforme = idInforme;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(int idInforme) {
        this.idInforme = idInforme;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipo != null ? tipo.hashCode() : 0);
        hash += (int) idInforme;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TareaPK)) {
            return false;
        }
        TareaPK other = (TareaPK) object;
        if ((this.tipo == null && other.tipo != null) || (this.tipo != null && !this.tipo.equals(other.tipo))) {
            return false;
        }
        if (this.idInforme != other.idInforme) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.TareaPK[ tipo=" + tipo + ", idInforme=" + idInforme + " ]";
    }
    
}
