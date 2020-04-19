/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controladores.IFachadaControl;
import Entidades.Clientes;
import Entidades.Paquetes;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ferra
 */
public class PanelRegistrarPedido extends javax.swing.JPanel {

    /**
     * Creates new form PanelPedidos
     */
    IFachadaControl fachadaControl;
    Clientes cliente;
    int row;
    int num = 0;
    
    public PanelRegistrarPedido(IFachadaControl fachadaControl) {
        initComponents();
        this.fachadaControl = fachadaControl;
        CrearModelo2();
        cargarPaquetes();
        comboClientes.removeAllItems();
        diseñoTabla();
        
    }
    DefaultTableModel modelo2;
    
    private void diseñoTabla(){
        tablaPaquetes.setSize(900, 400);
        tablaPaquetes.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD, 18));
        tablaPaquetes.getTableHeader().setOpaque(false);
        tablaPaquetes.getTableHeader().setBackground(new Color(0).black);
        tablaPaquetes.getTableHeader().setForeground(Color.white);
        tablaPaquetes.setRowHeight(30);
        tablaPaquetes.getColumnModel().getColumn(0).setMaxWidth(400);
        tablaPaquetes.getColumnModel().getColumn(1).setMaxWidth(400);
        tablaPaquetes.getColumnModel().getColumn(2).setMaxWidth(100);
        tablaPaquetes.getTableHeader().setReorderingAllowed(false);
    }
    
    private void CrearModelo2() {
        try {
            modelo2 = (new DefaultTableModel(
                    null, new String[]{
                        "Nombres",
                        "descripción","Precio"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false, false
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return canEdit[colIndex];
                }
            });
            tablaPaquetes.setModel(modelo2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error2");
        }
    }
   
    private void cargarPaquetes(){
        try{
            System.out.println("CargarInformacion");
            Object o[] = null;
            List<Paquetes> listClientes = fachadaControl.getPaquetes();
            for (int i = 0; i < listClientes.size(); i++) {
                modelo2.addRow(o);
                modelo2.setValueAt(listClientes.get(i).getNombre(), i, 0);
                modelo2.setValueAt(listClientes.get(i).getDescripcion(), i, 1);
                modelo2.setValueAt("$"+listClientes.get(i).getPrecio(), i, 2);
                
            }
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        textoCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        registrarPedido = new javax.swing.JButton();
        campoPrecio = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        comboClientes = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        nuevoCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPaquetes = new javax.swing.JTable();
        actualizarCliente = new javax.swing.JButton();

        setBackground(new java.awt.Color(107, 19, 43));

        jLabel2.setFont(new java.awt.Font("Yu Gothic Medium", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Paquetes");

        jLabel1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cliente");

        textoCliente.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        textoCliente.setToolTipText("");
        textoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoClienteKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Promocion:");

        jLabel4.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Precio:");

        registrarPedido.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        registrarPedido.setText("Registrar Pedido");
        registrarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarPedidoActionPerformed(evt);
            }
        });

        campoPrecio.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N

        comboClientes.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        comboClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        jButton2.setText("⟳");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        nuevoCliente.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        nuevoCliente.setText("Nuevo Cliente");
        nuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoClienteActionPerformed(evt);
            }
        });

        tablaPaquetes.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        tablaPaquetes.setForeground(new java.awt.Color(102, 0, 0));
        tablaPaquetes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaPaquetes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaPaquetes.setGridColor(new java.awt.Color(204, 204, 204));
        tablaPaquetes.setSelectionBackground(new java.awt.Color(107, 19, 43));
        tablaPaquetes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPaquetesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPaquetes);

        actualizarCliente.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        actualizarCliente.setText("Actualizar Cliente");
        actualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(nuevoCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(actualizarCliente))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(223, 223, 223)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(registrarPedido)
                    .addComponent(campoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nuevoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(actualizarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(registrarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(113, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablaPaquetesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPaquetesMouseClicked
        if (evt.getClickCount() == 2) {
            row = tablaPaquetes.rowAtPoint(evt.getPoint());
            new DetallePaquete(fachadaControl,row+1).setVisible(true);
            campoPrecio.setText(tablaPaquetes.getValueAt(row, 2).toString());
        }
    }//GEN-LAST:event_tablaPaquetesMouseClicked

    private void nuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoClienteActionPerformed
        // TODO add your handling code here:
        new FrmNuevoCliente(fachadaControl).setVisible(true);
    }//GEN-LAST:event_nuevoClienteActionPerformed

    private void textoClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoClienteKeyReleased
        // TODO add your handling code here:
        String[] splited = textoCliente.getText().split(" ");
        try {
            cliente = new Clientes();
            cliente = fachadaControl.getClienteNombre(splited[0], splited[1]);
            comboClientes.addItem(cliente.getNombre()+" "+cliente.getApellido());
            System.out.println("Encontrado");  
        } catch (Exception e) {
            comboClientes.removeAllItems();
            System.out.println("No existe el cliente");
        }
        
    }//GEN-LAST:event_textoClienteKeyReleased

    private void registrarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarPedidoActionPerformed
        // TODO add your handling code here:
        try {
            fachadaControl.registrarPedido(fachadaControl.getPaqueteId(row+1).getPrecio(), "", "",cliente.getIdcliente(),fachadaControl.getPaqueteId(row+1).getIdpaquete());
            JOptionPane.showMessageDialog(this, "Pedido Registrado");
        } catch (Exception e) {
            System.out.println("Seleccione un cliente y un paquete");
        }
    }//GEN-LAST:event_registrarPedidoActionPerformed

    private void actualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarClienteActionPerformed
        // TODO add your handling code here:
        try {
            new FrmNuevoCliente(fachadaControl,cliente.getIdcliente()).setVisible(true); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Seleccione un cliente");
        }  
    }//GEN-LAST:event_actualizarClienteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        comboClientes.removeAllItems();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizarCliente;
    private javax.swing.JTextField campoPrecio;
    private javax.swing.JComboBox<String> comboClientes;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton nuevoCliente;
    private javax.swing.JButton registrarPedido;
    private javax.swing.JTable tablaPaquetes;
    private javax.swing.JTextField textoCliente;
    // End of variables declaration//GEN-END:variables
}
