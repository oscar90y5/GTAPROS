/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rebeca
 */
@Entity
@Table(name = "Usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByDni", query = "SELECT u FROM Usuario u WHERE u.dni = :dni")
    , @NamedQuery(name = "Usuario.findByNombreCompleto", query = "SELECT u FROM Usuario u WHERE u.nombreCompleto = :nombreCompleto")
    , @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave")
    , @NamedQuery(name = "Usuario.findByVacacionesFijadas", query = "SELECT u FROM Usuario u WHERE u.vacacionesFijadas = :vacacionesFijadas")
    , @NamedQuery(name = "Usuario.findByTipoCategoria", query = "SELECT u FROM Usuario u WHERE u.tipoCategoria = :tipoCategoria")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "dni")
    private String dni;
    @Size(max = 40)
    @Column(name = "nombreCompleto")
    private String nombreCompleto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "clave")
    private String clave;
    @Column(name = "vacacionesFijadas")
    private Integer vacacionesFijadas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipoCategoria")
    private int tipoCategoria;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Miembro miembro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Collection<Vacaciones> vacacionesCollection;

    public Usuario() {
    }

    public Usuario(String dni) {
        this.dni = dni;
    }

    public Usuario(String dni, String clave, int tipoCategoria) {
        this.dni = dni;
        this.clave = clave;
        this.tipoCategoria = tipoCategoria;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getVacacionesFijadas() {
        return vacacionesFijadas;
    }

    public void setVacacionesFijadas(Integer vacacionesFijadas) {
        this.vacacionesFijadas = vacacionesFijadas;
    }

    public int getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(int tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }

    @XmlTransient
    public Collection<Vacaciones> getVacacionesCollection() {
        return vacacionesCollection;
    }

    public void setVacacionesCollection(Collection<Vacaciones> vacacionesCollection) {
        this.vacacionesCollection = vacacionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dni != null ? dni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.dni == null && other.dni != null) || (this.dni != null && !this.dni.equals(other.dni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Usuario[ dni=" + dni + " ]";
    }
    
}
