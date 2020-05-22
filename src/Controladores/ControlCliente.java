/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Datos.ClientesJpaController;
import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import Entidades.Clientes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ferra
 */
public class ControlCliente {

    ClientesJpaController cClientes;
    
    public ControlCliente() {
        cClientes = new ClientesJpaController();
    }
    
    public List<Clientes> getClientes(){
        
        return cClientes.findClientesEntities();
    }
    
    public Clientes getClienteId(int id){
        
        return cClientes.findClientes(id);
    }
    
    public Clientes getClienteNombre(String nombre, String apellido){
        return cClientes.buscarClienteNombre(nombre, apellido);
    }
    
    public void agregaCliente(String nombre, String apellido, String direccion, String numero, String correo)
    {
        try {
            cClientes.create(new Clientes(0,nombre,apellido, numero,correo,direccion));
        } catch (Exception ex) {
            Logger.getLogger(FachadaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editarCliente(int id, String nombre, String apellido, String direccion, String numero, String correo){
        
        Clientes c = cClientes.findClientes(id);
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setDireccion(direccion);
        c.setEmail(correo);
        try {
            cClientes.edit(c);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FachadaControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FachadaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarCliente(Clientes cliente){
        try {
            cClientes.destroy(cliente.getIdcliente());
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(ControlCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
