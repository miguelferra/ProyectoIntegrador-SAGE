/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ferra
 */
@Entity
@Table(name = "detalleentregables")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleentregables.findAll", query = "SELECT d FROM Detalleentregables d")
    , @NamedQuery(name = "Detalleentregables.findByCantidad", query = "SELECT d FROM Detalleentregables d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "Detalleentregables.findByFecha", query = "SELECT d FROM Detalleentregables d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "Detalleentregables.findByEntregablesIdentregable", query = "SELECT d FROM Detalleentregables d WHERE d.detalleentregablesPK.entregablesIdentregable = :entregablesIdentregable")
    , @NamedQuery(name = "Detalleentregables.findByPaquetesIdpaquete", query = "SELECT d FROM Detalleentregables d WHERE d.detalleentregablesPK.paquetesIdpaquete = :paquetesIdpaquete")})
public class Detalleentregables implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleentregablesPK detalleentregablesPK;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "entregables_identregable", referencedColumnName = "identregable", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Entregables entregables;
    @JoinColumn(name = "paquetes_idpaquete", referencedColumnName = "idpaquete", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paquetes paquetes;
    @JoinColumn(name = "pedidos_idpedido", referencedColumnName = "idpedido")
    @ManyToOne
    private Pedidos pedidosIdpedido;

    public Detalleentregables() {
    }

    public Detalleentregables(DetalleentregablesPK detalleentregablesPK) {
        this.detalleentregablesPK = detalleentregablesPK;
    }

    public Detalleentregables(String entregablesIdentregable, int paquetesIdpaquete) {
        this.detalleentregablesPK = new DetalleentregablesPK(entregablesIdentregable, paquetesIdpaquete);
    }

    public DetalleentregablesPK getDetalleentregablesPK() {
        return detalleentregablesPK;
    }

    public void setDetalleentregablesPK(DetalleentregablesPK detalleentregablesPK) {
        this.detalleentregablesPK = detalleentregablesPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Entregables getEntregables() {
        return entregables;
    }

    public void setEntregables(Entregables entregables) {
        this.entregables = entregables;
    }

    public Paquetes getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(Paquetes paquetes) {
        this.paquetes = paquetes;
    }

    public Pedidos getPedidosIdpedido() {
        return pedidosIdpedido;
    }

    public void setPedidosIdpedido(Pedidos pedidosIdpedido) {
        this.pedidosIdpedido = pedidosIdpedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleentregablesPK != null ? detalleentregablesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleentregables)) {
            return false;
        }
        Detalleentregables other = (Detalleentregables) object;
        if ((this.detalleentregablesPK == null && other.detalleentregablesPK != null) || (this.detalleentregablesPK != null && !this.detalleentregablesPK.equals(other.detalleentregablesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Detalleentregables[ detalleentregablesPK=" + detalleentregablesPK + " ]";
    }
    
}
