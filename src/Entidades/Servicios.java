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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicios.findAll", query = "SELECT s FROM Servicios s")
    , @NamedQuery(name = "Servicios.findByIdservicio", query = "SELECT s FROM Servicios s WHERE s.idservicio = :idservicio")
    , @NamedQuery(name = "Servicios.findByTipo", query = "SELECT s FROM Servicios s WHERE s.tipo = :tipo")
    , @NamedQuery(name = "Servicios.findByLugar", query = "SELECT s FROM Servicios s WHERE s.lugar = :lugar")
    , @NamedQuery(name = "Servicios.findByDetalle", query = "SELECT s FROM Servicios s WHERE s.detalle = :detalle")})
public class Servicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idservicio")
    private Integer idservicio;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "lugar")
    private String lugar;
    @Column(name = "detalle")
    private String detalle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicios")
    private List<Detalleservicio> detalleservicioList;

    public Servicios() {
    }

    public Servicios(int id, String tipo, String lugar, String detalle) {
        this.idservicio = id;
        this.tipo = tipo;
        this.lugar = lugar;
        this.detalle = detalle;
    }
    
    public Servicios(Integer idservicio) {
        this.idservicio = idservicio;
    }

    public Integer getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(Integer idservicio) {
        this.idservicio = idservicio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @XmlTransient
    public List<Detalleservicio> getDetalleservicioList() {
        return detalleservicioList;
    }

    public void setDetalleservicioList(List<Detalleservicio> detalleservicioList) {
        this.detalleservicioList = detalleservicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idservicio != null ? idservicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.idservicio == null && other.idservicio != null) || (this.idservicio != null && !this.idservicio.equals(other.idservicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Servicios[ idservicio=" + idservicio + " ]";
    }
    
}
