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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author user
 */
@Entity
@Table(name = "cuenta_app")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaApp.findAll", query = "SELECT c FROM CuentaApp c")
    , @NamedQuery(name = "CuentaApp.findByUserNumDoc", query = "SELECT c FROM CuentaApp c WHERE c.userNumDoc = :userNumDoc")
    , @NamedQuery(name = "CuentaApp.findByContrasena", query = "SELECT c FROM CuentaApp c WHERE c.contrasena = :contrasena")
    , @NamedQuery(name = "CuentaApp.findByUltimoIngreso", query = "SELECT c FROM CuentaApp c WHERE c.ultimoIngreso = :ultimoIngreso")})
public class CuentaApp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "user_num_doc")
    private String userNumDoc;
    @Basic(optional = false)
    @Column(name = "contrasena")
    private String contrasena;
    @Basic(optional = false)
    @Column(nullable = false,name = "ultimo_ingreso",columnDefinition = "DATETIME")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ultimoIngreso;
    @JoinColumn(name = "estado_cuenta_nombre", referencedColumnName = "nombre")
    @ManyToOne(optional = false)
    private EstadoCuenta estadoCuentaNombre;
    @JoinColumn(name = "rol_nombre_rom", referencedColumnName = "nombre_rom")
    @ManyToOne(optional = false)
    private Rol rolNombreRom;
    @JoinColumn(name = "user_num_doc", referencedColumnName = "num_doc", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public CuentaApp() {
    }

    public CuentaApp(String userNumDoc) {
        this.userNumDoc = userNumDoc;
    }

    public CuentaApp(String userNumDoc, String contrasena, LocalDateTime ultimoIngreso) {
        this.userNumDoc = userNumDoc;
        this.contrasena = contrasena;
        this.ultimoIngreso = ultimoIngreso;
    }

    public String getUserNumDoc() {
        return userNumDoc;
    }

    public void setUserNumDoc(String userNumDoc) {
        this.userNumDoc = userNumDoc;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public LocalDateTime getUltimoIngreso() {
        return ultimoIngreso;
    }

    public void setUltimoIngreso(LocalDateTime ultimoIngreso) {
        this.ultimoIngreso = ultimoIngreso;
    }


    public EstadoCuenta getEstadoCuentaNombre() {
        return estadoCuentaNombre;
    }

    public void setEstadoCuentaNombre(EstadoCuenta estadoCuentaNombre) {
        this.estadoCuentaNombre = estadoCuentaNombre;
    }

    public Rol getRolNombreRom() {
        return rolNombreRom;
    }

    public void setRolNombreRom(Rol rolNombreRom) {
        this.rolNombreRom = rolNombreRom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userNumDoc != null ? userNumDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaApp)) {
            return false;
        }
        CuentaApp other = (CuentaApp) object;
        if ((this.userNumDoc == null && other.userNumDoc != null) || (this.userNumDoc != null && !this.userNumDoc.equals(other.userNumDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesbanco.CuentaApp[ userNumDoc=" + userNumDoc + " ]";
    }
    
}
