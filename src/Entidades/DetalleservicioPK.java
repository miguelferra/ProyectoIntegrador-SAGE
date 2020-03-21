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
public class DetalleservicioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "paquetes_idpaquete")
    private int paquetesIdpaquete;
    @Basic(optional = false)
    @Column(name = "servicios_idservicio")
    private int serviciosIdservicio;

    public DetalleservicioPK() {
    }

    public DetalleservicioPK(int paquetesIdpaquete, int serviciosIdservicio) {
        this.paquetesIdpaquete = paquetesIdpaquete;
        this.serviciosIdservicio = serviciosIdservicio;
    }

    public int getPaquetesIdpaquete() {
        return paquetesIdpaquete;
    }

    public void setPaquetesIdpaquete(int paquetesIdpaquete) {
        this.paquetesIdpaquete = paquetesIdpaquete;
    }

    public int getServiciosIdservicio() {
        return serviciosIdservicio;
    }

    public void setServiciosIdservicio(int serviciosIdservicio) {
        this.serviciosIdservicio = serviciosIdservicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) paquetesIdpaquete;
        hash += (int) serviciosIdservicio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleservicioPK)) {
            return false;
        }
        DetalleservicioPK other = (DetalleservicioPK) object;
        if (this.paquetesIdpaquete != other.paquetesIdpaquete) {
            return false;
        }
        if (this.serviciosIdservicio != other.serviciosIdservicio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.DetalleservicioPK[ paquetesIdpaquete=" + paquetesIdpaquete + ", serviciosIdservicio=" + serviciosIdservicio + " ]";
    }
    
}
