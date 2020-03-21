/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ferra
 */
@Entity
@Table(name = "paquetes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paquetes.findAll", query = "SELECT p FROM Paquetes p")
    , @NamedQuery(name = "Paquetes.findByIdpaquete", query = "SELECT p FROM Paquetes p WHERE p.idpaquete = :idpaquete")
    , @NamedQuery(name = "Paquetes.findByNombre", query = "SELECT p FROM Paquetes p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Paquetes.findByDescripcion", query = "SELECT p FROM Paquetes p WHERE p.descripcion = :descripcion")})
public class Paquetes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idpaquete")
    private Integer idpaquete;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paquetes")
    private List<Detalleentregables> detalleentregablesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paquetesIdpaquete")
    private List<Pedidos> pedidosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paquetes")
    private List<Detalleservicio> detalleservicioList;

    public Paquetes() {
    }

    public Paquetes(Integer idpaquete) {
        this.idpaquete = idpaquete;
    }

    public Integer getIdpaquete() {
        return idpaquete;
    }

    public void setIdpaquete(Integer idpaquete) {
        this.idpaquete = idpaquete;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Detalleentregables> getDetalleentregablesList() {
        return detalleentregablesList;
    }

    public void setDetalleentregablesList(List<Detalleentregables> detalleentregablesList) {
        this.detalleentregablesList = detalleentregablesList;
    }

    @XmlTransient
    public List<Pedidos> getPedidosList() {
        return pedidosList;
    }

    public void setPedidosList(List<Pedidos> pedidosList) {
        this.pedidosList = pedidosList;
    }

    @XmlTransient
    public List<Detalleservicio> getDetalleservicioList() {
        return detalleservicioList;
    }

    public void setDetalleservicioList(List<Detalleservicio> detalleservicioList) {
        this.detalleservicioList = detalleservicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpaquete != null ? idpaquete.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paquetes)) {
            return false;
        }
        Paquetes other = (Paquetes) object;
        if ((this.idpaquete == null && other.idpaquete != null) || (this.idpaquete != null && !this.idpaquete.equals(other.idpaquete))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Paquetes[ idpaquete=" + idpaquete + " ]";
    }
    
}
