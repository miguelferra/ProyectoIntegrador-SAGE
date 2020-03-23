/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Datos.PaquetesJpaController;
import Entidades.Clientes;
import Entidades.Paquetes;
import java.util.List;

/**
 *
 * @author ferra
 */
public class ControlPaquete {

    PaquetesJpaController cPaq;
    
    public ControlPaquete() {
        cPaq = new PaquetesJpaController();
    }
    
    public Paquetes getPaqueteId(int id){
        return cPaq.findPaquetes(id);
    }
   
     public List<Paquetes> getPaquetes(){
        return cPaq.findPaquetesEntities();
    }
    
    
}
