/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Datos.DetalleentregablesJpaController;
import Datos.EntregablesJpaController;
import Entidades.Detalleentregables;
import java.util.List;

/**
 *
 * @author ferra
 */
public class ControlEntregable {

     DetalleentregablesJpaController cDetEnt;
     EntregablesJpaController cEnt;
     
    public ControlEntregable() {
        cDetEnt = new DetalleentregablesJpaController();
        cEnt = new EntregablesJpaController();
    }
    
    public List<Detalleentregables> getDetalleEntregable(int idPaquete){
        return cDetEnt.buscarEntregables(idPaquete); 
    }
    
}
