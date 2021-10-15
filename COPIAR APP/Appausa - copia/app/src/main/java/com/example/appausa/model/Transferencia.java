/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.appausa.model;

import java.io.Serializable;
import java.time.LocalDateTime;



public class Transferencia{

    private static final long serialVersionUID = 1L;

    private String idTrans;

    private double valor;

    private LocalDateTime fecha;

    private CuentaBancaria cuentaOrigen;

    private CuentaBancaria cuentaDestino;

    public Transferencia() {
    }

    public Transferencia(String idTrans) {
        this.idTrans = idTrans;
    }

    public Transferencia(String idTrans, double valor, LocalDateTime fecha) {
        this.idTrans = idTrans;
        this.valor = valor;
        this.fecha = fecha;
    }

    public String getIdTrans() {
        return idTrans;
    }

    public void setIdTrans(String idTrans) {
        this.idTrans = idTrans;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public CuentaBancaria getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(CuentaBancaria cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public CuentaBancaria getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(CuentaBancaria cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrans != null ? idTrans.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transferencia)) {
            return false;
        }
        Transferencia other = (Transferencia) object;
        if ((this.idTrans == null && other.idTrans != null) || (this.idTrans != null && !this.idTrans.equals(other.idTrans))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesbanco.Transferencia[ idTrans=" + idTrans + " ]";
    }
    
}
