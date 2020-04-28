/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ferra
 */
@Entity
@Table(name = "actividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividades.findAll", query = "SELECT a FROM Actividades a")
    , @NamedQuery(name = "Actividades.findByIdactividad", query = "SELECT a FROM Actividades a WHERE a.idactividad = :idactividad")
    , @NamedQuery(name = "Actividades.findByDetalle", query = "SELECT a FROM Actividades a WHERE a.detalle = :detalle")
    , @NamedQuery(name = "Actividades.findByEstado", query = "SELECT a FROM Actividades a WHERE a.estado = :estado")})
public class Actividades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idactividad")
    private Integer idactividad;
    @Column(name = "detalle")
    private String detalle;
    @Column(name = "estado")
    private String estado;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "actividades")
    private Detalleactividades detalleactividades;

    public Actividades() {
    }

    public Actividades(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public Integer getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Detalleactividades getDetalleactividades() {
        return detalleactividades;
    }

    public void setDetalleactividades(Detalleactividades detalleactividades) {
        this.detalleactividades = detalleactividades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactividad != null ? idactividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividades)) {
            return false;
        }
        Actividades other = (Actividades) object;
        if ((this.idactividad == null && other.idactividad != null) || (this.idactividad != null && !this.idactividad.equals(other.idactividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Actividades[ idactividad=" + idactividad + " ]";
    }
    
}
