/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.appausa.model;

import java.io.Serializable;
import java.time.LocalDate;



public class User {

    private String numDoc;
    private String nombre;
    private String apellido;
    private LocalDate fechaNam;
    private String correoElectronico;
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
