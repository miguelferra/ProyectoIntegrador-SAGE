/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sage;

import Datos.ClientesJpaController;
import Datos.PaquetesJpaController;
import Datos.PedidosJpaController;
import Entidades.Clientes;
import Entidades.Paquetes;
import Entidades.Pedidos;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauriciowi100
 */
public class Conexion {

//    
//        static ClientesJpaController cClientes = new ClientesJpaController();
//        static PaquetesJpaController cPaq = new PaquetesJpaController();
//        static PedidosJpaController cPed = new PedidosJpaController();
//        static Scanner sc = new Scanner(System.in);
//        static List<Clientes> listClientes;
//        static List<Paquetes> listPaquetes;
//    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here    
//        int opcion = 1;
//        while(opcion!=0)
//        {
//            System.out.println("1.- Agregar Cliente");
//            System.out.println("2.- Actualizar Cliente");
//            System.out.println("3.- Eliminar Cliente");
//            System.out.println("4.- Listar Clientes");
//            System.out.println("5.- Registrar pedido");
//            System.out.println("10.- Salir");
//            System.out.println("Elige opcion: ");
//            opcion = sc.nextInt();
//            switch(opcion)
//            {
//                case 1: agregaCliente();
//                        break;
//                        
//                case 2: actualizaCliente(); break;
//                case 3: eliminaCliente(); break;
//                case 4: listaCliente(); break;
//                case 5: registrarPedido(); break;
//                case 10: System.exit(0);break;
//                default: System.out.println("Eliga una opcion numerica");
//                
//            }
//        }
//        
//    }
//    
//    public static void registrarPedido()
//    {
//        Pedidos ped = new Pedidos();
//        String str;
//        listaCliente();
//        sc.nextLine();
//        System.out.println("id cliente:");
//        str = sc.nextLine();
//        ped.setClientesIdcliente(cClientes.findClientes(str));
//        listaPaquetes();
//        System.out.println("id paquete");
//        str = sc.nextLine();
//        ped.setPaquetesIdpaquete(cPaq.findPaquetes(Integer.parseInt(str)));
//        System.out.println("promocion");
//        str = sc.nextLine();
//        ped.setPromocion(str);
//        ped.setFechaPedido(new java.sql.Date(new Date().getTime()));
//        ped.setFechaRequerida(new java.sql.Date(new Date().getTime()));
//        System.out.println("Precio: ");
//        ped.setPrecio(sc.nextDouble());
//        cPed.create(ped);
//        
//    }
//    
//    static void listaPaquetes()
//    {
//        
//        listPaquetes = cPaq.findPaquetesEntities();
//        
//        for (int i = 0; i < listPaquetes.size(); i++) {
//            
//            System.out.println("----------------------------------------------------------------------");
//            System.out.println("ID: "+listPaquetes.get(i).getIdpaquete());
//            System.out.println("Nombre Paquete: "+listPaquetes.get(i).getNombre());
//            System.out.println("Descripción paquete: "+listPaquetes.get(i).getDescripcion());
//        }
//    }
//    static void listaEntregablesPaquete()
//    {
//        
//        listPaquetes = cPaq.findPaquetesEntities();
//        
//        for (int i = 0; i < listPaquetes.size(); i++) {
//            
//            System.out.println("----------------------------------------------------------------------");
//            System.out.println("ID: "+listPaquetes.get(i).getIdpaquete());
//            System.out.println("Nombre Paquete: "+listPaquetes.get(i).getNombre());
//            System.out.println("Descripción paquete: "+listPaquetes.get(i).getDescripcion());
//        }
//    }
//    
//    public static void agregaCliente()
//    {
//        System.out.println("Insertar cliente");
//        Clientes c = new Clientes();
//        Scanner sc = new Scanner(System.in);
//        System.out.println("ID::");
//        c.setIdcliente(sc.nextLine());
//        System.out.println("Nombre:");
//        c.setNombre(sc.nextLine());
//        System.out.println("Apellido:");
//        c.setApellido(sc.nextLine());
//        System.out.println("Número tel:");
//        c.setTelefono(sc.nextLine());
//        System.out.println("Email:");
//        c.setEmail(sc.nextLine());
//        System.out.println("Direccion:");
//        c.setDireccion(sc.nextLine());
//        try {
//            cClientes.create(c);
//        } catch (Exception ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    
//    public static void listaCliente()
//    {
//        System.out.println("Listar cientes");
//        listClientes = cClientes.findClientesEntities();
//        for (int i = 0; i < listClientes.size(); i++) {
//            
//            System.out.println("----------------------------------------------------------------------");
//            System.out.println("ID: "+listClientes.get(i).getIdcliente());
//            System.out.println("Nombre: "+listClientes.get(i).getNombre());
//            System.out.println("Apellido: "+listClientes.get(i).getApellido());
//            System.out.println("Email: "+listClientes.get(i).getEmail());
//            System.out.println("Teléfono: "+listClientes.get(i).getTelefono());
//            System.out.println("Dirección: "+listClientes.get(i).getDireccion());
//        }
//    }
//    
//    public static void eliminaCliente()
//    {
//        System.out.println("Eliminar cliente");
//        System.out.println("ID cliente:");
//        try {
//            cClientes.destroy(sc.nextLine());
//                    
//                    } catch (IllegalOrphanException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NonexistentEntityException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
//    
//    public static void actualizaCliente()
//    {
//        System.out.println("Editar cliente");
//        sc.nextLine();
//        System.out.println("ID cliente:");
//        String id = sc.nextLine();
//        listClientes = cClientes.findClientesEntities();
//        try {
//            Clientes cEdit = cClientes.findClientes(id);
//            System.out.println("Nombre: " + cEdit.getNombre()+"--> Nuevo nombre: ");
//            cEdit.setNombre(sc.nextLine());
//            System.out.println("Apellido: " + cEdit.getApellido()+"--> Nuevo Apellido: ");
//            cEdit.setApellido(sc.nextLine());
//            System.out.println("Teléfono: " + cEdit.getTelefono()+"--> Nuevo  Teléfono: ");
//            cEdit.setTelefono(sc.nextLine());
//            System.out.println("Email: " + cEdit.getEmail()+"--> Nuevo Email: ");
//            cEdit.setEmail(sc.nextLine());
//            System.out.println("Dirección: " + cEdit.getDireccion()+"--> Nueva dirección: ");
//            cEdit.setDireccion(sc.nextLine());
//            
//            cClientes.edit(cEdit);
//            
//                    
//                    } catch (IllegalOrphanException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NonexistentEntityException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
    
}
