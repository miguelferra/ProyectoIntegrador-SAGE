/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "detalleentregablespedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleentregablespedido.findAll", query = "SELECT d FROM Detalleentregablespedido d")
    , @NamedQuery(name = "Detalleentregablespedido.findByCantidad", query = "SELECT d FROM Detalleentregablespedido d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "Detalleentregablespedido.findByFecha", query = "SELECT d FROM Detalleentregablespedido d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "Detalleentregablespedido.findByPedidosIdpedido", query = "SELECT d FROM Detalleentregablespedido d WHERE d.pedidosIdpedido = :pedidosIdpedido")})
public class Detalleentregablespedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Id
    @Basic(optional = false)
    @Column(name = "pedidos_idpedido")
    private Integer pedidosIdpedido;
    @JoinColumn(name = "entregables_identregable", referencedColumnName = "identregable")
    @ManyToOne(optional = false)
    private Entregables entregablesIdentregable;

    public Detalleentregablespedido() {
    }
    
    public Detalleentregablespedido(Entregables idEntregable,Integer cantidad) {
        this.entregablesIdentregable = idEntregable;
        this.cantidad = cantidad;
    }

    public Detalleentregablespedido(Integer pedidosIdpedido) {
        this.pedidosIdpedido = pedidosIdpedido;
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

    public Integer getPedidosIdpedido() {
        return pedidosIdpedido;
    }

    public void setPedidosIdpedido(Integer pedidosIdpedido) {
        this.pedidosIdpedido = pedidosIdpedido;
    }

    public Entregables getEntregablesIdentregable() {
        return entregablesIdentregable;
    }

    public void setEntregablesIdentregable(Entregables entregablesIdentregable) {
        this.entregablesIdentregable = entregablesIdentregable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidosIdpedido != null ? pedidosIdpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleentregablespedido)) {
            return false;
        }
        Detalleentregablespedido other = (Detalleentregablespedido) object;
        if ((this.pedidosIdpedido == null && other.pedidosIdpedido != null) || (this.pedidosIdpedido != null && !this.pedidosIdpedido.equals(other.pedidosIdpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Detalleentregablespedido[ pedidosIdpedido=" + pedidosIdpedido + " ]";
    }
    
}
