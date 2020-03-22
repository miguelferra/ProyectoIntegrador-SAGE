/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ferra
 */
@Entity
@Table(name = "pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidos.findAll", query = "SELECT p FROM Pedidos p")
    , @NamedQuery(name = "Pedidos.findByIdpedido", query = "SELECT p FROM Pedidos p WHERE p.idpedido = :idpedido")
    , @NamedQuery(name = "Pedidos.findByFechaPedido", query = "SELECT p FROM Pedidos p WHERE p.fechaPedido = :fechaPedido")
    , @NamedQuery(name = "Pedidos.findByFechaRequerida", query = "SELECT p FROM Pedidos p WHERE p.fechaRequerida = :fechaRequerida")
    , @NamedQuery(name = "Pedidos.findByPrecio", query = "SELECT p FROM Pedidos p WHERE p.precio = :precio")
    , @NamedQuery(name = "Pedidos.findByPromocion", query = "SELECT p FROM Pedidos p WHERE p.promocion = :promocion")
    , @NamedQuery(name = "Pedidos.findByNotas", query = "SELECT p FROM Pedidos p WHERE p.notas = :notas")})
public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpedido")
    private Integer idpedido;
    @Basic(optional = false)
    @Column(name = "fechaPedido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;
    @Column(name = "fechaRequerida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRequerida;
    @Basic(optional = false)
    @Column(name = "precio")
    private double precio;
    @Basic(optional = false)
    @Column(name = "promocion")
    private String promocion;
    @Column(name = "notas")
    private String notas;
    @OneToMany(mappedBy = "pedidosIdpedido")
    private List<Detalleentregables> detalleentregablesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidos")
    private List<Detalleactividades> detalleactividadesList;
    @JoinColumn(name = "clientes_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Clientes clientesIdcliente;
    @JoinColumn(name = "paquetes_idpaquete", referencedColumnName = "idpaquete")
    @ManyToOne(optional = false)
    private Paquetes paquetesIdpaquete;
    @OneToMany(mappedBy = "pedidosIdpedido")
    private List<Detalleservicio> detalleservicioList;

    public Pedidos() {
    }

    public Pedidos(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public Pedidos(Integer idpedido, Date fechaPedido, Date fechaRequerida, double precio, String promocion, String notas) {
        this.idpedido = idpedido;
        this.fechaPedido = fechaPedido;
        this.fechaRequerida = fechaRequerida;
        this.precio = precio;
        this.promocion = promocion;
        this.notas = notas;
    }

    public Integer getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Date getFechaRequerida() {
        return fechaRequerida;
    }

    public void setFechaRequerida(Date fechaRequerida) {
        this.fechaRequerida = fechaRequerida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @XmlTransient
    public List<Detalleentregables> getDetalleentregablesList() {
        return detalleentregablesList;
    }

    public void setDetalleentregablesList(List<Detalleentregables> detalleentregablesList) {
        this.detalleentregablesList = detalleentregablesList;
    }

    @XmlTransient
    public List<Detalleactividades> getDetalleactividadesList() {
        return detalleactividadesList;
    }

    public void setDetalleactividadesList(List<Detalleactividades> detalleactividadesList) {
        this.detalleactividadesList = detalleactividadesList;
    }

    public Clientes getClientesIdcliente() {
        return clientesIdcliente;
    }

    public void setClientesIdcliente(Clientes clientesIdcliente) {
        this.clientesIdcliente = clientesIdcliente;
    }

    public Paquetes getPaquetesIdpaquete() {
        return paquetesIdpaquete;
    }

    public void setPaquetesIdpaquete(Paquetes paquetesIdpaquete) {
        this.paquetesIdpaquete = paquetesIdpaquete;
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
        hash += (idpedido != null ? idpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.idpedido == null && other.idpedido != null) || (this.idpedido != null && !this.idpedido.equals(other.idpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Pedidos[ idpedido=" + idpedido + " ]";
    }
    
}
