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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    , @NamedQuery(name = "Paquetes.findByDescripcion", query = "SELECT p FROM Paquetes p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Paquetes.findByPrecio", query = "SELECT p FROM Paquetes p WHERE p.precio = :precio")})
public class Paquetes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpaquete")
    private Integer idpaquete;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Float precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paquetes")
    private List<Pedidos> pedidosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paquetes")
    private List<Detalleentregablespaquete> detalleentregablespaqueteList;

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

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    @XmlTransient
    public List<Pedidos> getPedidosList() {
        return pedidosList;
    }

    public void setPedidosList(List<Pedidos> pedidosList) {
        this.pedidosList = pedidosList;
    }

    @XmlTransient
    public List<Detalleentregablespaquete> getDetalleentregablespaqueteList() {
        return detalleentregablespaqueteList;
    }

    public void setDetalleentregablespaqueteList(List<Detalleentregablespaquete> detalleentregablespaqueteList) {
        this.detalleentregablespaqueteList = detalleentregablespaqueteList;
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
