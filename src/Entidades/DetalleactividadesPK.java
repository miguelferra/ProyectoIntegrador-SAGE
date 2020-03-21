/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ferra
 */
@Embeddable
public class DetalleactividadesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "actividades_idactividad")
    private int actividadesIdactividad;
    @Basic(optional = false)
    @Column(name = "pedidos_idpedido")
    private int pedidosIdpedido;

    public DetalleactividadesPK() {
    }

    public DetalleactividadesPK(int actividadesIdactividad, int pedidosIdpedido) {
        this.actividadesIdactividad = actividadesIdactividad;
        this.pedidosIdpedido = pedidosIdpedido;
    }

    public int getActividadesIdactividad() {
        return actividadesIdactividad;
    }

    public void setActividadesIdactividad(int actividadesIdactividad) {
        this.actividadesIdactividad = actividadesIdactividad;
    }

    public int getPedidosIdpedido() {
        return pedidosIdpedido;
    }

    public void setPedidosIdpedido(int pedidosIdpedido) {
        this.pedidosIdpedido = pedidosIdpedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) actividadesIdactividad;
        hash += (int) pedidosIdpedido;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleactividadesPK)) {
            return false;
        }
        DetalleactividadesPK other = (DetalleactividadesPK) object;
        if (this.actividadesIdactividad != other.actividadesIdactividad) {
            return false;
        }
        if (this.pedidosIdpedido != other.pedidosIdpedido) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.DetalleactividadesPK[ actividadesIdactividad=" + actividadesIdactividad + ", pedidosIdpedido=" + pedidosIdpedido + " ]";
    }
    
}
