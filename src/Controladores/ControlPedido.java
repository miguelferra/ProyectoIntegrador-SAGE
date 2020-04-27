/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Datos.PaquetesJpaController;
import Datos.PedidosJpaController;
import Entidades.Clientes;
import Entidades.Detalleentregablespedido;
import Entidades.Detalleservicio;
import Entidades.Pedidos;
import Entidades.Paquetes;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ferra
 */
public class ControlPedido {

    private PedidosJpaController cPedido;
    
    private List<Detalleentregablespedido> listaEntregablesPedido;
    private List<Detalleservicio> listaServiciosesPedido;
    


    public ControlPedido() {
        cPedido = new PedidosJpaController();
        this.listaEntregablesPedido = new LinkedList<Detalleentregablespedido>();
        this.listaServiciosesPedido = new LinkedList<Detalleservicio>();
    }

    public List<Pedidos> getPedidos() {
        return cPedido.findPedidosEntities();
    }
    
    public List<Pedidos> getPedidosClientes(Integer id) {
        return cPedido.findPedidosCliente(id);
    }

    public Pedidos getPedidoID(int id){
        return cPedido.findPedidos(id);
    }
    
    public void registrarPedido(float precio,Date fecha, String promocion, String notas, Clientes cliente, Paquetes paquete) {
        Pedidos ped = new Pedidos(0, new Date(),fecha, precio, promocion, notas);
        ped.setClientesIdcliente(cliente);
        ped.setPaquetesIdpaquete(paquete);
        try {
            cPedido.create(ped);
        } catch (Exception ex) {
            Logger.getLogger(FachadaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Pedidos getUltimoPedido(){
        return cPedido.getUltimoPedido();
    }
    
    public void asignarEntregablesPedido(List listaEntregablesPedido){
        this.listaEntregablesPedido = listaEntregablesPedido;
    }
    
    public void asignarServiciosPedido(List listaServiciosPedido){
        this .listaServiciosesPedido = listaServiciosPedido;
    }

    

}
