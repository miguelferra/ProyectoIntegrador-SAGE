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
@Table(name = "detalleservicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleservicio.findAll", query = "SELECT d FROM Detalleservicio d")
    , @NamedQuery(name = "Detalleservicio.findByDireccion", query = "SELECT d FROM Detalleservicio d WHERE d.direccion = :direccion")
    , @NamedQuery(name = "Detalleservicio.findByFecha", query = "SELECT d FROM Detalleservicio d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "Detalleservicio.findByPaquetesIdpaquete", query = "SELECT d FROM Detalleservicio d WHERE d.detalleservicioPK.paquetesIdpaquete = :paquetesIdpaquete")
    , @NamedQuery(name = "Detalleservicio.findByServiciosIdservicio", query = "SELECT d FROM Detalleservicio d WHERE d.detalleservicioPK.serviciosIdservicio = :serviciosIdservicio")})
public class Detalleservicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleservicioPK detalleservicioPK;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "paquetes_idpaquete", referencedColumnName = "idpaquete", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paquetes paquetes;
    @JoinColumn(name = "pedidos_idpedido", referencedColumnName = "idpedido")
    @ManyToOne
    private Pedidos pedidosIdpedido;
    @JoinColumn(name = "servicios_idservicio", referencedColumnName = "idservicio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Servicios servicios;

    public Detalleservicio() {
    }

    public Detalleservicio(DetalleservicioPK detalleservicioPK) {
        this.detalleservicioPK = detalleservicioPK;
    }

    public Detalleservicio(int paquetesIdpaquete, int serviciosIdservicio) {
        this.detalleservicioPK = new DetalleservicioPK(paquetesIdpaquete, serviciosIdservicio);
    }

    public DetalleservicioPK getDetalleservicioPK() {
        return detalleservicioPK;
    }

    public void setDetalleservicioPK(DetalleservicioPK detalleservicioPK) {
        this.detalleservicioPK = detalleservicioPK;
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

    public Servicios getServicios() {
        return servicios;
    }

    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleservicioPK != null ? detalleservicioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleservicio)) {
            return false;
        }
        Detalleservicio other = (Detalleservicio) object;
        if ((this.detalleservicioPK == null && other.detalleservicioPK != null) || (this.detalleservicioPK != null && !this.detalleservicioPK.equals(other.detalleservicioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Detalleservicio[ detalleservicioPK=" + detalleservicioPK + " ]";
    }
    
}
