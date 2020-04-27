/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import Datos.DetalleservicioJpaController;
import Datos.ServiciosJpaController;
import Datos.exceptions.PreexistingEntityException;
import Entidades.Detalleservicio;
import Entidades.Pedidos;
import Entidades.Servicios;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public void registrarDetalleServicio(Detalleservicio detalleServicio){
        try {
            cDetServ.create(detalleServicio);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(ControlServicio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControlServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void asignarDetalleServiciosPedido(List<Detalleservicio> listaServiciosPedido, Pedidos pedido){
         try {         
            for (int i = 0; i < listaServiciosPedido.size(); i++) {
                Detalleservicio detalleServicio = listaServiciosPedido.get(i);
                detalleServicio.setPedidosIdpedido(pedido.getIdpedido());
                detalleServicio.setFecha(new java.sql.Date(new Date().getTime()));
                cDetServ.create(detalleServicio);
            }
        } catch (Exception ex) {
            Logger.getLogger(FachadaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
}
