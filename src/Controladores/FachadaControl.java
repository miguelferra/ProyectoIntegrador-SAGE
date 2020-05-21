/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Datos.ActividadesJpaController;
import Datos.ClientesJpaController;
import Datos.DetalleactividadesJpaController;
import Datos.DetalleservicioJpaController;
import Datos.EmpleadosJpaController;
import Datos.PaquetesJpaController;
import Datos.PedidosJpaController;
import Datos.ServiciosJpaController;
import Entidades.Clientes;
import Entidades.Detalleentregablespaquete;
import Entidades.Detalleentregablespedido;
import Entidades.Detalleservicio;
import Entidades.Entregables;
import Entidades.Paquetes;
import Entidades.Pedidos;
import Entidades.Servicios;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ferra
 */
public class FachadaControl implements IFachadaControl {

    private ControlCliente controlCliente;
    private ControlEntregable controlEntregable;
    private ControlServicio controlServicio;
    private ControlPaquete controlPaquete;
    private ControlPedido controlPedido;
    
    
    

    public FachadaControl() {
        this.controlCliente = new ControlCliente();
        this.controlEntregable = new ControlEntregable();
        this.controlServicio = new ControlServicio();
        this.controlPaquete = new ControlPaquete();
        this.controlPedido = new ControlPedido();
    }

    public List<Clientes> getClientes() {
        return controlCliente.getClientes();
    }

    public Clientes getClienteNombre(String nombre, String apellido) {
        return controlCliente.getClienteNombre(nombre, apellido);
    }

    public Clientes getClienteId(int id) {
        return controlCliente.getClienteId(id);
    }

    public void editarCliente(int id, String nombre, String apellido, String direccion, String numero, String correo) {

        controlCliente.editarCliente(id, nombre, apellido, direccion, numero, correo);
    }

    public List<Paquetes> getPaquetes() {
        return controlPaquete.getPaquetes();
    }

    public Paquetes getPaqueteId(int id) {
        return controlPaquete.getPaqueteId(id);
    }

    public List<Detalleentregablespaquete> getDetallePaqueteEntregable(int idPaquete) {
        return controlPaquete.getDetalleEntregable(idPaquete);
    }

    public List<Detalleentregablespaquete> getDetallePaqueteServicio(int idPaquete) {
        return controlPaquete.getDetalleServicio(idPaquete);
    }

    public void agregaCliente(String nombre, String apellido, String direccion, String numero, String correo) {
        controlCliente.agregaCliente(nombre, apellido, direccion, numero, correo);
    }

    public void registrarPedido(float precio, Date fecha, String promocion, String notas, int idCliente, int idPaquete) {
        controlPedido.registrarPedido(precio,fecha , promocion, notas, controlCliente.getClienteId(idCliente), controlPaquete.getPaqueteId(idPaquete));
    }

    public List<Detalleentregablespedido> getDetalleEntregable(int idPaquete) {
        return controlEntregable.getDetalleEntregable(idPaquete);
    }

    public List<Entregables> getEntregables() {
        return controlEntregable.getEntregables();
    }

    public Entregables getEntregableId(int id) {
        return controlEntregable.getEntregableId(id);
    }

    public List<Detalleservicio> getDetalleServicio(int id) {
        return controlServicio.getDetalleServicio(id);
    }

    @Override
    public Servicios getServiciosId(int id) {
        return controlServicio.getServicioId(id);
    }

    public List<Servicios> getServicios() {
        return controlServicio.getServicios();
    }

    public void registrarDetalleServicio(Detalleservicio detalleServicio) {
        controlServicio.registrarDetalleServicio(detalleServicio);
    }

    public List<Pedidos> getPedidos() {
        return controlPedido.getPedidos();
    }

    public Pedidos getPedidoID(int id) {
        return controlPedido.getPedidoID(id);
    }
    
    public void asignarEntregablesPedido(List listaEntregablesPedido){
        controlPedido.asignarEntregablesPedido(listaEntregablesPedido);
    } 
    
    public void asignarServiciosPedido(List listaServiciosPedido){
        controlPedido.asignarServiciosPedido(listaServiciosPedido);
    }
    
    public void asignarDetalleEntregablesPedido(List<Detalleentregablespedido> listaEntregablesPedido){
        controlEntregable.asignarDetalleEntregablesPedido(listaEntregablesPedido,controlPedido.getUltimoPedido());
    }

    public void asignarDetalleServiciosPedido(List<Detalleservicio> listaServicciosPedido){
        controlServicio.asignarDetalleServiciosPedido(listaServicciosPedido,controlPedido.getUltimoPedido());
    }
    public List<Pedidos> getPedidosCliente(String nombre,String apellido){
        return controlPedido.getPedidosClientes(controlCliente.getClienteNombre(nombre, apellido).getIdcliente());
    }
    
    public void eliminarPedido(Pedidos pedido){
        eliminarDetalleServicio(pedido);
        eliminarDetalleEntregable(pedido);
        controlPedido.eliminarPedido(pedido);
    }
    
    public void eliminarDetalleEntregable(Pedidos pedido){
        controlEntregable.eliminarDetalleEntregable(pedido);
    }
    
    public void eliminarDetalleServicio(Pedidos pedido){
        controlServicio.eliminarDetalleServicio(pedido);
    }
    
     public void modificarPedido(int pedido,float precio, Date fecha,String promocion,String notas,int idCliente,int idPaquete,List<Detalleentregablespedido> listaEntregables ,List<Detalleservicio> listaServicios) {
        eliminarDetalleServicio(controlPedido.getPedidoID(pedido));
        eliminarDetalleEntregable(controlPedido.getPedidoID(pedido));
        controlEntregable.asignarDetalleEntregablesPedido(listaEntregables,controlPedido.getPedidoID(pedido));
        controlServicio.asignarDetalleServiciosPedido(listaServicios,controlPedido.getPedidoID(pedido));
        controlPedido.modificarPedido(pedido, precio, fecha, promocion, notas,controlCliente.getClienteId(idCliente), controlPaquete.getPaqueteId(idPaquete),controlEntregable.getDetalleEntregable(pedido),controlServicio.getDetalleServicio(pedido));
    }
}
    
