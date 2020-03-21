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
public class DetalleentregablesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "entregables_identregable")
    private String entregablesIdentregable;
    @Basic(optional = false)
    @Column(name = "paquetes_idpaquete")
    private int paquetesIdpaquete;

    public DetalleentregablesPK() {
    }

    public DetalleentregablesPK(String entregablesIdentregable, int paquetesIdpaquete) {
        this.entregablesIdentregable = entregablesIdentregable;
        this.paquetesIdpaquete = paquetesIdpaquete;
    }

    public String getEntregablesIdentregable() {
        return entregablesIdentregable;
    }

    public void setEntregablesIdentregable(String entregablesIdentregable) {
        this.entregablesIdentregable = entregablesIdentregable;
    }

    public int getPaquetesIdpaquete() {
        return paquetesIdpaquete;
    }

    public void setPaquetesIdpaquete(int paquetesIdpaquete) {
        this.paquetesIdpaquete = paquetesIdpaquete;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entregablesIdentregable != null ? entregablesIdentregable.hashCode() : 0);
        hash += (int) paquetesIdpaquete;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleentregablesPK)) {
            return false;
        }
        DetalleentregablesPK other = (DetalleentregablesPK) object;
        if ((this.entregablesIdentregable == null && other.entregablesIdentregable != null) || (this.entregablesIdentregable != null && !this.entregablesIdentregable.equals(other.entregablesIdentregable))) {
            return false;
        }
        if (this.paquetesIdpaquete != other.paquetesIdpaquete) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.DetalleentregablesPK[ entregablesIdentregable=" + entregablesIdentregable + ", paquetesIdpaquete=" + paquetesIdpaquete + " ]";
    }
    
}
