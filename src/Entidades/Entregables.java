/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ferra
 */
@Entity
@Table(name = "entregables")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entregables.findAll", query = "SELECT e FROM Entregables e")
    , @NamedQuery(name = "Entregables.findByIdentregable", query = "SELECT e FROM Entregables e WHERE e.identregable = :identregable")
    , @NamedQuery(name = "Entregables.findByTipo", query = "SELECT e FROM Entregables e WHERE e.tipo = :tipo")
    , @NamedQuery(name = "Entregables.findByTama\u00f1o", query = "SELECT e FROM Entregables e WHERE e.tama\u00f1o = :tama\u00f1o")})
public class Entregables implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "identregable")
    private String identregable;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "tama\u00f1o")
    private String tamaño;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entregables")
    private List<Detalleentregables> detalleentregablesList;

    public Entregables() {
    }

    public Entregables(String identregable) {
        this.identregable = identregable;
    }

    public String getIdentregable() {
        return identregable;
    }

    public void setIdentregable(String identregable) {
        this.identregable = identregable;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    @XmlTransient
    public List<Detalleentregables> getDetalleentregablesList() {
        return detalleentregablesList;
    }

    public void setDetalleentregablesList(List<Detalleentregables> detalleentregablesList) {
        this.detalleentregablesList = detalleentregablesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (identregable != null ? identregable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entregables)) {
            return false;
        }
        Entregables other = (Entregables) object;
        if ((this.identregable == null && other.identregable != null) || (this.identregable != null && !this.identregable.equals(other.identregable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Entregables[ identregable=" + identregable + " ]";
    }
    
}
