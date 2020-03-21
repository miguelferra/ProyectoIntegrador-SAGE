/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidades.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controlador {
    
    ClientesJpaController cClientes = new ClientesJpaController();
    PaquetesJpaController cPaq = new PaquetesJpaController();
    PedidosJpaController cPed = new PedidosJpaController();
    ActividadesJpaController cAct = new ActividadesJpaController();
    DetalleactividadesJpaController cDetAc = new DetalleactividadesJpaController();
    DetalleentregablesJpaController cDetEnt = new DetalleentregablesJpaController();
    DetalleservicioJpaController cDetServ = new DetalleservicioJpaController();
    EmpleadosJpaController cEmp = new EmpleadosJpaController();
    ServiciosJpaController cServ = new ServiciosJpaController();
    EntregablesJpaController cEnt = new EntregablesJpaController();
    List <Paquetes> listPaq = new ArrayList();
    List <Clientes> listClientes = new ArrayList();
    
    public List<Clientes> getClientes()
    {
        listClientes = cClientes.findClientesEntities();
        return listClientes;
    }
    
    public List<Paquetes> getPaquetes()
    {
        listPaq = cPaq.findPaquetesEntities();
        return listPaq;
    }
    
    public void agregaCliente(String nombre, String apellido, String direccion, String numero, String correo)
    {
        try {
            cClientes.create(new Clientes(0,nombre,apellido, numero,correo,direccion));
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Clientes getClienteId(int id)
    {
        return cClientes.findClientes(id);
    }
    
    public void editarCliente(int id, String nombre, String apellido, String direccion, String numero, String correo)
    {
        Clientes c = cClientes.findClientes(id);
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setDireccion(direccion);
        c.setEmail(correo);
        try {
            cClientes.edit(c);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
