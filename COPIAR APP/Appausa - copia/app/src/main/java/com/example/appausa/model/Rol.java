/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.appausa.model;

public class Rol{



    private String nombreRom;

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
