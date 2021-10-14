/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.sd.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author user
 */
@SuppressWarnings("serial")
@Embeddable
public class LogPK implements Serializable{

    @Basic(optional = false)
    @Column(nullable = false,name = "fecha",columnDefinition = "DATETIME")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;
    @Basic(optional = false)
    @Column(name = "cuenta")
    private String cuenta;
    @Basic(optional = false)
    @Column(name = "accion")
    private String accion;

    public LogPK() {
    }

    public LogPK(LocalDateTime fecha, String cuenta, String accion) {
        this.fecha = fecha;
        this.cuenta = cuenta;
        this.accion = accion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fecha != null ? fecha.hashCode() : 0);
        hash += (cuenta != null ? cuenta.hashCode() : 0);
        hash += (accion != null ? accion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogPK)) {
            return false;
        }
        LogPK other = (LogPK) object;
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        if ((this.cuenta == null && other.cuenta != null) || (this.cuenta != null && !this.cuenta.equals(other.cuenta))) {
            return false;
        }
        if ((this.accion == null && other.accion != null) || (this.accion != null && !this.accion.equals(other.accion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesbanco.LogPK[ fecha=" + fecha + ", cuenta=" + cuenta + ", accion=" + accion + " ]";
    }
    
}
