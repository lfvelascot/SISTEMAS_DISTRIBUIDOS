/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.appausa.model;


import java.time.LocalDateTime;



public class CuentaApp {

    private String userNumDoc;
    private String contrasena;
    private LocalDateTime ultimoIngreso;
    private EstadoCuenta estadoCuentaNombre;
    private Rol rolNombreRom;
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
