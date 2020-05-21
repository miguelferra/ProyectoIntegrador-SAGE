/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import Datos.DetalleentregablespedidoJpaController;
import Datos.EntregablesJpaController;
import Entidades.Detalleentregablespedido;
import Entidades.Entregables;
import Entidades.Pedidos;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ferra
 */
public class ControlEntregable {

     DetalleentregablespedidoJpaController cDetEnt;
     EntregablesJpaController cEnt;
     
    public ControlEntregable() {
        cDetEnt = new DetalleentregablespedidoJpaController();
        cEnt = new EntregablesJpaController();
    }
    
    public Entregables getEntregableId(int id){
        return cEnt.findEntregables(id);
    }
    
    public List<Entregables> getEntregables(){
        return cEnt.findEntregablesEntities();
    }
    public List<Detalleentregablespedido> getDetalleEntregable(int idPedido){
        return cDetEnt.buscarEntregables(idPedido); 
    }
    
    public void asignarDetalleEntregablesPedido(List<Detalleentregablespedido> listaEntregablesPedido, Pedidos pedido){
        
        try {         
            for (int i = 0; i < listaEntregablesPedido.size(); i++) {
                Detalleentregablespedido detalleEntregable = listaEntregablesPedido.get(i);
                detalleEntregable.setPedidos(pedido);
                detalleEntregable.setFecha(new java.sql.Date(new Date().getTime()));
                cDetEnt.create(detalleEntregable);
            }
        } catch (Exception ex) {
            Logger.getLogger(FachadaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarDetalleEntregable(Pedidos pedido){
       cDetEnt.eliminarDetalleEntregable(pedido.getIdpedido());
    }
    
    
}
