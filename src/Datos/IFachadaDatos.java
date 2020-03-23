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
public interface IFachadaDatos {
    
    public Paquetes getPaqueteId(int id);
   
     public List<Paquetes> getPaquetes();
    
    
}
