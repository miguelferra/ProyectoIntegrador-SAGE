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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
    , @NamedQuery(name = "Detalleentregablespedido.findByIdDetalleEntregable", query = "SELECT d FROM Detalleentregablespedido d WHERE d.idDetalleEntregable = :idDetalleEntregable")})
public class Detalleentregablespedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalleEntregable")
    private Integer idDetalleEntregable;
    @JoinColumns({
        @JoinColumn(name = "entregables_identregable", referencedColumnName = "identregable")
        , @JoinColumn(name = "entregables_identregable", referencedColumnName = "identregable")})
    @ManyToOne(optional = false)
    private Entregables entregables;
    @JoinColumns({
        @JoinColumn(name = "pedidos_idpedido", referencedColumnName = "idpedido")
        , @JoinColumn(name = "pedidos_idpedido", referencedColumnName = "idpedido")})
    @ManyToOne(optional = false)
    private Pedidos pedidos;

    public Detalleentregablespedido() {
    }
    
    public Detalleentregablespedido(Entregables idEntregable,Integer cantidad) {
        this.idDetalleEntregable = 0;
        this.entregables = idEntregable;
        this.cantidad = cantidad;
    }

    public Detalleentregablespedido(Integer idDetalleEntregable) {
        this.idDetalleEntregable = idDetalleEntregable;
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

    public Integer getIdDetalleEntregable() {
        return idDetalleEntregable;
    }

    public void setIdDetalleEntregable(Integer idDetalleEntregable) {
        this.idDetalleEntregable = idDetalleEntregable;
    }

    public Entregables getEntregables() {
        return entregables;
    }

    public void setEntregables(Entregables entregables) {
        this.entregables = entregables;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleEntregable != null ? idDetalleEntregable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleentregablespedido)) {
            return false;
        }
        Detalleentregablespedido other = (Detalleentregablespedido) object;
        if ((this.idDetalleEntregable == null && other.idDetalleEntregable != null) || (this.idDetalleEntregable != null && !this.idDetalleEntregable.equals(other.idDetalleEntregable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Detalleentregablespedido[ idDetalleEntregable=" + idDetalleEntregable + " ]";
    }
    
}
