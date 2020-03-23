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
import java.util.List;

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
    public List<Detalleentregablespedido> getDetalleEntregable(int idPaquete){
        return cDetEnt.buscarEntregables(idPaquete); 
    }
    
}
