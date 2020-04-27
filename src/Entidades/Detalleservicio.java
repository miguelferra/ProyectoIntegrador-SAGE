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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ferra
 */
@Entity
@Table(name = "detalleservicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleservicio.findAll", query = "SELECT d FROM Detalleservicio d")
    , @NamedQuery(name = "Detalleservicio.findByTiempo", query = "SELECT d FROM Detalleservicio d WHERE d.tiempo = :tiempo")
    , @NamedQuery(name = "Detalleservicio.findByDireccion", query = "SELECT d FROM Detalleservicio d WHERE d.direccion = :direccion")
    , @NamedQuery(name = "Detalleservicio.findByFecha", query = "SELECT d FROM Detalleservicio d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "Detalleservicio.findByPedidosIdpedido", query = "SELECT d FROM Detalleservicio d WHERE d.pedidosIdpedido = :pedidosIdpedido")})
public class Detalleservicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "tiempo")
    private String tiempo;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Id
    @Basic(optional = false)
    @Column(name = "pedidos_idpedido")
    private Integer pedidosIdpedido;
    @JoinColumn(name = "pedidos_idpedido", referencedColumnName = "idpedido", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pedidos pedidos;
    @JoinColumn(name = "servicios_idservicio", referencedColumnName = "idservicio")
    @ManyToOne(optional = false)
    private Servicios serviciosIdservicio;

    public Detalleservicio() {
    }

    public Detalleservicio(Servicios servicio,String tiempo) {
        this.serviciosIdservicio = servicio;
        this.tiempo = tiempo;
    }

    public Detalleservicio(Integer pedidosIdpedido) {
        this.pedidosIdpedido = pedidosIdpedido;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    public Servicios getServiciosIdservicio() {
        return serviciosIdservicio;
    }

    public void setServiciosIdservicio(Servicios serviciosIdservicio) {
        this.serviciosIdservicio = serviciosIdservicio;
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
        if (!(object instanceof Detalleservicio)) {
            return false;
        }
        Detalleservicio other = (Detalleservicio) object;
        if ((this.pedidosIdpedido == null && other.pedidosIdpedido != null) || (this.pedidosIdpedido != null && !this.pedidosIdpedido.equals(other.pedidosIdpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Detalleservicio[ pedidosIdpedido=" + pedidosIdpedido + " ]";
    }
    
}
