/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Clientes;
import Entidades.Detalleentregablespaquete;
import Entidades.Detalleentregablespedido;
import Entidades.Detalleservicio;
import Entidades.Entregables;
import Entidades.Paquetes;
import Entidades.Pedidos;
import Entidades.Servicios;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ferra
 */
public interface IFachadaControl {
    
    
    public List<Clientes> getClientes();
    
    public List<Paquetes> getPaquetes();
    
    public void agregaCliente(String nombre, String apellido, String direccion, String numero, String correo);

    public void registrarPedido(float precio, Date fecha,String promocion,String notas,int idCliente,int idPaquete);
    
    public void registrarDetalleServicio(Detalleservicio detalleServicio);
    
    public Clientes getClienteId(int id);

    public List<Detalleentregablespedido> getDetalleEntregable(int id);

    public List<Detalleservicio> getDetalleServicio(int id);
    
    public Clientes getClienteNombre(String nombre, String apellido);
    
    public void editarCliente(int id, String nombre, String apellido, String direccion, String numero, String correo);
    
    public List<Detalleentregablespaquete> getDetallePaqueteEntregable(int idPaquete);
    
    public List<Detalleentregablespaquete> getDetallePaqueteServicio(int idPaquete);
    
    public Entregables getEntregableId(int id);
    
    public List<Entregables> getEntregables();
    
    public List<Servicios> getServicios();
    
    public Servicios getServiciosId(int id);
    
    public Paquetes getPaqueteId(int id);
    
    public List<Pedidos> getPedidos();

    public Pedidos getPedidoID(int id);
    
    public void asignarEntregablesPedido(List listaEntregablesPedido);
    
    public void asignarServiciosPedido(List listaServiciosPedido);
    
    public void asignarDetalleEntregablesPedido(List<Detalleentregablespedido> listaEntregablesPedido);
    
    public void asignarDetalleServiciosPedido(List<Detalleservicio> listaServicciosPedido);
    
    public List<Pedidos> getPedidosCliente(String nombre, String apellido);
    
    public void eliminarPedido(Pedidos pedido);
    
    
}
