/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Datos.DetalleservicioJpaController;
import Datos.ServiciosJpaController;
import Entidades.Detalleservicio;
import Entidades.Servicios;
import java.util.List;

/**
 *
 * @author ferra
 */
public class ControlServicio {

    DetalleservicioJpaController cDetServ;
    ServiciosJpaController cServ;
    
    public ControlServicio() {
        cServ = new ServiciosJpaController();
        cDetServ = new DetalleservicioJpaController();
    }
    
    public List<Detalleservicio> getDetalleServicio(int id){
        return cDetServ.buscarServicios(id);
    }
    
    public Servicios getServicioId(int id){
        return cServ.findServicios(id);
    }
    
    public List<Servicios> getServicios(){
        return cServ.findServiciosEntities();
    }
    
}
