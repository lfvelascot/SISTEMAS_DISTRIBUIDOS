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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * @author user
 */
@Entity
@Table(name = "transferencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transferencia.findAll", query = "SELECT t FROM Transferencia t")
    , @NamedQuery(name = "Transferencia.findByIdTrans", query = "SELECT t FROM Transferencia t WHERE t.idTrans = :idTrans")
    , @NamedQuery(name = "Transferencia.findByValor", query = "SELECT t FROM Transferencia t WHERE t.valor = :valor")
    , @NamedQuery(name = "Transferencia.findByFecha", query = "SELECT t FROM Transferencia t WHERE t.fecha = :fecha")})
public class Transferencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_trans")
    private String idTrans;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @Basic(optional = false)
    @Column(nullable = false,name = "fecha",columnDefinition = "DATETIME")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;
    @JoinColumn(name = "cuenta_origen", referencedColumnName = "num")
    @ManyToOne(optional = false)
    private CuentaBancaria cuentaOrigen;
    @JoinColumn(name = "cuenta_destino", referencedColumnName = "num")
    @ManyToOne(optional = false)
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
