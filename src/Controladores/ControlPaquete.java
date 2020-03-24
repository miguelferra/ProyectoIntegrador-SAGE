/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Datos.DetalleentregablespaqueteJpaController;
import Datos.PaquetesJpaController;
import Entidades.Clientes;
import Entidades.Detalleentregablespaquete;
import Entidades.Paquetes;
import java.util.List;

/**
 *
 * @author ferra
 */
public class ControlPaquete {

    DetalleentregablespaqueteJpaController controlDetallePaquete;
    PaquetesJpaController cPaq;
    
    public ControlPaquete() {
        cPaq = new PaquetesJpaController();
        controlDetallePaquete = new DetalleentregablespaqueteJpaController();
    }
    
    public List<Detalleentregablespaquete> getDetalleEntregable(int idPaquete){
        return controlDetallePaquete.buscarEntregables(idPaquete); 
    }
    
    
    public List<Detalleentregablespaquete> getDetalleServicio(int idPaquete){
        return controlDetallePaquete.buscarServicios(idPaquete); 
    }
    
    public Paquetes getPaqueteId(int id){
        return cPaq.findPaquetes(id);
    }
   
     public List<Paquetes> getPaquetes(){
        return cPaq.findPaquetesEntities();
    }
    
}
