/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.sd.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author user
 */
@Entity
@Table(name = "estado_cuenta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCuenta.findAll", query = "SELECT e FROM EstadoCuenta e")
    , @NamedQuery(name = "EstadoCuenta.findByNombre", query = "SELECT e FROM EstadoCuenta e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "EstadoCuenta.findByDescrip", query = "SELECT e FROM EstadoCuenta e WHERE e.descrip = :descrip")})
public class EstadoCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "descrip")
    private String descrip;

    public EstadoCuenta() {
    }

    public EstadoCuenta(String nombre) {
        this.nombre = nombre;
    }

    public EstadoCuenta(String nombre, String descrip) {
        this.nombre = nombre;
        this.descrip = descrip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCuenta)) {
            return false;
        }
        EstadoCuenta other = (EstadoCuenta) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesbanco.EstadoCuenta[ nombre=" + nombre + " ]";
    }
    
}
