/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usbbog.sd.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author user
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByNumDoc", query = "SELECT u FROM User u WHERE u.numDoc = :numDoc")
    , @NamedQuery(name = "User.findByNombre", query = "SELECT u FROM User u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "User.findByApellido", query = "SELECT u FROM User u WHERE u.apellido = :apellido")
    , @NamedQuery(name = "User.findByFechaNam", query = "SELECT u FROM User u WHERE u.fechaNam = :fechaNam")
    , @NamedQuery(name = "User.findByCorreoElectronico", query = "SELECT u FROM User u WHERE u.correoElectronico = :correoElectronico")
    , @NamedQuery(name = "User.findByTelefono", query = "SELECT u FROM User u WHERE u.telefono = :telefono")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "num_doc")
    private String numDoc;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @Column(nullable = false,name = "fecha_nam",columnDefinition = "DATE")
	@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaNam;
    @Basic(optional = false)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;

    public User() {
    }

    public User(String numDoc) {
        this.numDoc = numDoc;
    }

    public User(String numDoc, String nombre, String apellido, LocalDate fechaNam, String correoElectronico, String telefono) {
        this.numDoc = numDoc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNam = fechaNam;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNam() {
        return fechaNam;
    }

    public void setFechaNam(LocalDate fechaNam) {
        this.fechaNam = fechaNam;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numDoc != null ? numDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.numDoc == null && other.numDoc != null) || (this.numDoc != null && !this.numDoc.equals(other.numDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesbanco.User[ numDoc=" + numDoc + " ]";
    }
    
}
