/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
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
 * @author ferra
 */
@Entity
@Table(name = "detalleactividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalleactividades.findAll", query = "SELECT d FROM Detalleactividades d")
    , @NamedQuery(name = "Detalleactividades.findByFecha", query = "SELECT d FROM Detalleactividades d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "Detalleactividades.findByActividadesIdactividad", query = "SELECT d FROM Detalleactividades d WHERE d.detalleactividadesPK.actividadesIdactividad = :actividadesIdactividad")
    , @NamedQuery(name = "Detalleactividades.findByPedidosIdpedido", query = "SELECT d FROM Detalleactividades d WHERE d.detalleactividadesPK.pedidosIdpedido = :pedidosIdpedido")})
public class Detalleactividades implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleactividadesPK detalleactividadesPK;
    @Basic(optional = false)
    @Column(name = "fecha")
    private int fecha;
    @JoinColumn(name = "actividades_idactividad", referencedColumnName = "idactividad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Actividades actividades;
    @JoinColumn(name = "empleados_RFC", referencedColumnName = "RFC")
    @ManyToOne
    private Empleados empleadosRFC;
    @JoinColumn(name = "pedidos_idpedido", referencedColumnName = "idpedido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedidos pedidos;

    public Detalleactividades() {
    }

    public Detalleactividades(DetalleactividadesPK detalleactividadesPK) {
        this.detalleactividadesPK = detalleactividadesPK;
    }

    public Detalleactividades(DetalleactividadesPK detalleactividadesPK, int fecha) {
        this.detalleactividadesPK = detalleactividadesPK;
        this.fecha = fecha;
    }

    public Detalleactividades(int actividadesIdactividad, int pedidosIdpedido) {
        this.detalleactividadesPK = new DetalleactividadesPK(actividadesIdactividad, pedidosIdpedido);
    }

    public DetalleactividadesPK getDetalleactividadesPK() {
        return detalleactividadesPK;
    }

    public void setDetalleactividadesPK(DetalleactividadesPK detalleactividadesPK) {
        this.detalleactividadesPK = detalleactividadesPK;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public Actividades getActividades() {
        return actividades;
    }

    public void setActividades(Actividades actividades) {
        this.actividades = actividades;
    }

    public Empleados getEmpleadosRFC() {
        return empleadosRFC;
    }

    public void setEmpleadosRFC(Empleados empleadosRFC) {
        this.empleadosRFC = empleadosRFC;
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
        hash += (detalleactividadesPK != null ? detalleactividadesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleactividades)) {
            return false;
        }
        Detalleactividades other = (Detalleactividades) object;
        if ((this.detalleactividadesPK == null && other.detalleactividadesPK != null) || (this.detalleactividadesPK != null && !this.detalleactividadesPK.equals(other.detalleactividadesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Detalleactividades[ detalleactividadesPK=" + detalleactividadesPK + " ]";
    }
    
}
