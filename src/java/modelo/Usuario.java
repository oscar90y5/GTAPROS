package modelo;

import java.io.Serializable;


public class Usuario implements Serializable {
    
    
    private String dni;
    private String nombreCompleto;
    private String clave;
    private boolean vacacionesFijadas;
    private TipoCategoria tipoCategoria;

    public Usuario() {
    }

    public Usuario(String dni, String nombreCompleto, String clave, boolean vacacionesFijadas, TipoCategoria tipoCategoria) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.clave = clave;
        this.vacacionesFijadas = vacacionesFijadas;
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

    public boolean isVacacionesFijadas() {
        return vacacionesFijadas;
    }

    public void setVacacionesFijadas(boolean vacacionesFijadas) {
        this.vacacionesFijadas = vacacionesFijadas;
    }

    public TipoCategoria getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(TipoCategoria tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }
    
    
}
