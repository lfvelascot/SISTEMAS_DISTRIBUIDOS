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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "cuenta_bancaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaBancaria.findAll", query = "SELECT c FROM CuentaBancaria c")
    , @NamedQuery(name = "CuentaBancaria.findByNum", query = "SELECT c FROM CuentaBancaria c WHERE c.num = :num")
    , @NamedQuery(name = "CuentaBancaria.findByClave", query = "SELECT c FROM CuentaBancaria c WHERE c.clave = :clave")
    , @NamedQuery(name = "CuentaBancaria.findBySaldo", query = "SELECT c FROM CuentaBancaria c WHERE c.saldo = :saldo")})
public class CuentaBancaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "num")
    private String num;
    @Basic(optional = false)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @Column(name = "saldo")
    private double saldo;
    @JoinColumn(name = "estado_cuenta", referencedColumnName = "nombre")
    @ManyToOne(optional = false)
    private EstadoCuenta estadoCuenta;
    @JoinColumn(name = "tipo_cuenta", referencedColumnName = "nombre")
    @ManyToOne(optional = false)
    private TipoCuenta tipoCuenta;
    @JoinColumn(name = "usuario", referencedColumnName = "num_doc")
    @OneToOne(optional = false)
    private User usuario;

    public CuentaBancaria() {
    }

    public CuentaBancaria(String num) {
        this.num = num;
    }

    public CuentaBancaria(String num, String clave, String fechaInicio, double saldo) {
        this.num = num;
        this.clave = clave;
        this.saldo = saldo;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }



    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }



    public EstadoCuenta getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(EstadoCuenta estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (num != null ? num.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaBancaria)) {
            return false;
        }
        CuentaBancaria other = (CuentaBancaria) object;
        if ((this.num == null && other.num != null) || (this.num != null && !this.num.equals(other.num))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesbanco.CuentaBancaria[ num=" + num + " ]";
    }
    
}
