/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e")
    , @NamedQuery(name = "Empleados.findByRfc", query = "SELECT e FROM Empleados e WHERE e.rfc = :rfc")
    , @NamedQuery(name = "Empleados.findByApellido", query = "SELECT e FROM Empleados e WHERE e.apellido = :apellido")
    , @NamedQuery(name = "Empleados.findByNombre", query = "SELECT e FROM Empleados e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empleados.findByPassword", query = "SELECT e FROM Empleados e WHERE e.password = :password")
    , @NamedQuery(name = "Empleados.findByEmail", query = "SELECT e FROM Empleados e WHERE e.email = :email")
    , @NamedQuery(name = "Empleados.findByRol", query = "SELECT e FROM Empleados e WHERE e.rol = :rol")
    , @NamedQuery(name = "Empleados.findByTelefono", query = "SELECT e FROM Empleados e WHERE e.telefono = :telefono")})
public class Empleados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RFC")
    private String rfc;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "rol")
    private String rol;
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(mappedBy = "empleadosRFC")
    private List<Detalleactividades> detalleactividadesList;

    public Empleados() {
    }

    public Empleados(String rfc) {
        this.rfc = rfc;
    }

    public Empleados(String rfc, String apellido, String nombre, String password, String email, String rol) {
        this.rfc = rfc;
        this.apellido = apellido;
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.rol = rol;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<Detalleactividades> getDetalleactividadesList() {
        return detalleactividadesList;
    }

    public void setDetalleactividadesList(List<Detalleactividades> detalleactividadesList) {
        this.detalleactividadesList = detalleactividadesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rfc != null ? rfc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.rfc == null && other.rfc != null) || (this.rfc != null && !this.rfc.equals(other.rfc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Empleados[ rfc=" + rfc + " ]";
    }
    
}
