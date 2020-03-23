/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Entidades.Paquetes;
import java.util.List;

/**
 *
 * @author ferra
 */
public class FachadaDatos implements IFachadaDatos{
    
    ClientesJpaController cClientes;
    PaquetesJpaController cPaquetes;
    PedidosJpaController cPedidos;
    DetalleentregablespedidoJpaController cDetEnt;
    EntregablesJpaController cEnt;
    DetalleservicioJpaController cDetServ;
    ServiciosJpaController cServ;

    public FachadaDatos() {
        cClientes = new ClientesJpaController();
        cPaquetes = new PaquetesJpaController();
    }
    
    
   public Paquetes getPaqueteId(int id){
        return cPaquetes.findPaquetes(id);
    }
   
     public List<Paquetes> getPaquetes(){
        return cPaquetes.findPaquetesEntities();
    }
    
    
    
    
}
