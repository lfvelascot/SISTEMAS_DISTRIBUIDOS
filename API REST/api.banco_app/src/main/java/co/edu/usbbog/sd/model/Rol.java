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
@Table(name = "rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
    , @NamedQuery(name = "Rol.findByNombreRom", query = "SELECT r FROM Rol r WHERE r.nombreRom = :nombreRom")
    , @NamedQuery(name = "Rol.findByDescrip", query = "SELECT r FROM Rol r WHERE r.descrip = :descrip")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nombre_rom")
    private String nombreRom;
    @Basic(optional = false)
    @Column(name = "descrip")
    private String descrip;
    
    public Rol() {
    }

    public Rol(String nombreRom) {
        this.nombreRom = nombreRom;
    }

    public Rol(String nombreRom, String descrip) {
        this.nombreRom = nombreRom;
        this.descrip = descrip;
    }

    public String getNombreRom() {
        return nombreRom;
    }

    public void setNombreRom(String nombreRom) {
        this.nombreRom = nombreRom;
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
        hash += (nombreRom != null ? nombreRom.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.nombreRom == null && other.nombreRom != null) || (this.nombreRom != null && !this.nombreRom.equals(other.nombreRom))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesbanco.Rol[ nombreRom=" + nombreRom + " ]";
    }
    
}
