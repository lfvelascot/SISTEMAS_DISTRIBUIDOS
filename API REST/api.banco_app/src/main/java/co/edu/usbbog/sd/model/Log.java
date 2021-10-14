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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l")
    , @NamedQuery(name = "Log.findByFecha", query = "SELECT l FROM Log l WHERE l.logPK.fecha = :fecha")
    , @NamedQuery(name = "Log.findByDescrip", query = "SELECT l FROM Log l WHERE l.descrip = :descrip")
    , @NamedQuery(name = "Log.findByCuenta", query = "SELECT l FROM Log l WHERE l.logPK.cuenta = :cuenta")
    , @NamedQuery(name = "Log.findByAccion", query = "SELECT l FROM Log l WHERE l.logPK.accion = :accion")})
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LogPK logPK;
    @Basic(optional = false)
    @Column(name = "descrip")
    private String descrip;
    @JoinColumn(name = "accion", referencedColumnName = "nombre", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Accion accion1;
    @JoinColumn(name = "cuenta", referencedColumnName = "user_num_doc", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CuentaApp cuentaApp;

    public Log() {
    }

    public Log(LogPK logPK) {
        this.logPK = logPK;
    }

    public Log(LogPK logPK, String descrip) {
        this.logPK = logPK;
        this.descrip = descrip;
    }

    public Log(LocalDateTime fecha, String cuenta, String accion) {
        this.logPK = new LogPK(fecha, cuenta, accion);
    }

    public LogPK getLogPK() {
        return logPK;
    }

    public void setLogPK(LogPK logPK) {
        this.logPK = logPK;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public Accion getAccion1() {
        return accion1;
    }

    public void setAccion1(Accion accion1) {
        this.accion1 = accion1;
    }

    public CuentaApp getCuentaApp() {
        return cuentaApp;
    }

    public void setCuentaApp(CuentaApp cuentaApp) {
        this.cuentaApp = cuentaApp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logPK != null ? logPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.logPK == null && other.logPK != null) || (this.logPK != null && !this.logPK.equals(other.logPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesbanco.Log[ logPK=" + logPK + " ]";
    }
    
}
