/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ferra
 */
@Entity
@Table(name = "detalleentregablespaquete")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleentregablespaquete.findAll", query = "SELECT d FROM Detalleentregablespaquete d")
    , @NamedQuery(name = "Detalleentregablespaquete.findByTiempoServicio", query = "SELECT d FROM Detalleentregablespaquete d WHERE d.tiempoServicio = :tiempoServicio")
    , @NamedQuery(name = "Detalleentregablespaquete.findByCantidadEntregable", query = "SELECT d FROM Detalleentregablespaquete d WHERE d.cantidadEntregable = :cantidadEntregable")
    , @NamedQuery(name = "Detalleentregablespaquete.findByFecha", query = "SELECT d FROM Detalleentregablespaquete d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "Detalleentregablespaquete.findByServiciosIdservicio", query = "SELECT d FROM Detalleentregablespaquete d WHERE d.serviciosIdservicio = :serviciosIdservicio")
    , @NamedQuery(name = "Detalleentregablespaquete.findByEntregablesIdentregable", query = "SELECT d FROM Detalleentregablespaquete d WHERE d.entregablesIdentregable = :entregablesIdentregable")
    , @NamedQuery(name = "Detalleentregablespaquete.findByPrueba", query = "SELECT d FROM Detalleentregablespaquete d WHERE d.prueba = :prueba")})
public class Detalleentregablespaquete implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "tiempoServicio")
    private String tiempoServicio;
    @Column(name = "cantidadEntregable")
    private Integer cantidadEntregable;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "servicios_idservicio")
    private Integer serviciosIdservicio;
    @Column(name = "entregables_identregable")
    private Integer entregablesIdentregable;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prueba")
    private Integer prueba;
    @JoinColumn(name = "paquetes_idpaquete", referencedColumnName = "idpaquete")
    @ManyToOne(optional = false)
    private Paquetes paquetesIdpaquete;

    public Detalleentregablespaquete() {
    }

    public Detalleentregablespaquete(Integer prueba) {
        this.prueba = prueba;
    }

    public String getTiempoServicio() {
        return tiempoServicio;
    }

    public void setTiempoServicio(String tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }

    public Integer getCantidadEntregable() {
        return cantidadEntregable;
    }

    public void setCantidadEntregable(Integer cantidadEntregable) {
        this.cantidadEntregable = cantidadEntregable;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getServiciosIdservicio() {
        return serviciosIdservicio;
    }

    public void setServiciosIdservicio(Integer serviciosIdservicio) {
        this.serviciosIdservicio = serviciosIdservicio;
    }

    public Integer getEntregablesIdentregable() {
        return entregablesIdentregable;
    }

    public void setEntregablesIdentregable(Integer entregablesIdentregable) {
        this.entregablesIdentregable = entregablesIdentregable;
    }

    public Integer getPrueba() {
        return prueba;
    }

    public void setPrueba(Integer prueba) {
        this.prueba = prueba;
    }

    public Paquetes getPaquetesIdpaquete() {
        return paquetesIdpaquete;
    }

    public void setPaquetesIdpaquete(Paquetes paquetesIdpaquete) {
        this.paquetesIdpaquete = paquetesIdpaquete;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prueba != null ? prueba.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleentregablespaquete)) {
            return false;
        }
        Detalleentregablespaquete other = (Detalleentregablespaquete) object;
        if ((this.prueba == null && other.prueba != null) || (this.prueba != null && !this.prueba.equals(other.prueba))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Detalleentregablespaquete[ prueba=" + prueba + " ]";
    }
    
}
