/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controladores.IFachadaControl;
import Entidades.Clientes;
import Entidades.Paquetes;
import Entidades.Pedidos;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author ferra
 */
public class PanelPedidos extends javax.swing.JPanel {

    /**
     * Creates new form PanelPedidos
     */
    IFachadaControl fachadaControl;
    List<Pedidos> pedidos;
    JPanel panelPrincipal;
    int row;
    public PanelPedidos(IFachadaControl fachadaControl,JPanel panelPrincipal) {
        initComponents();
        this.fachadaControl = fachadaControl;
        this.panelPrincipal = panelPrincipal;
        this.pedidos = new LinkedList<>();
        CrearModelo2();
        cargarPedidos();
        diseñoTabla();
    }

    DefaultTableModel modelo2;
    
    private void diseñoTabla(){    
        tablaPedidos.setRowHeight(30);
        tablaPedidos.getColumnModel().getColumn(0).setMaxWidth(450);
        tablaPedidos.getColumnModel().getColumn(1).setMaxWidth(450);
        tablaPedidos.getColumnModel().getColumn(2).setMaxWidth(450);
        tablaPedidos.getColumnModel().getColumn(3).setMaxWidth(450);
        tablaPedidos.getTableHeader().setReorderingAllowed(false);
    }
    
    private void CrearModelo2() {
        try {
            modelo2 = (new DefaultTableModel(
                    null, new String[]{
                        "Cliente","Paquete",
                        "Fecha Requerida","Precio"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false, false,false
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
            tablaPedidos.setModel(modelo2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error2");
        }
    }
   
    private void cargarPedidos(){
        try{
            System.out.println("CargarPedidos");
            Object o[] = null;
            List<Pedidos> listaPedidos = fachadaControl.getPedidos();
            for (int i = 0; i < listaPedidos.size(); i++) {
                modelo2.addRow(o);
                modelo2.setValueAt(listaPedidos.get(i).getClientes().getNombre()+" "+listaPedidos.get(i).getClientes().getApellido(), i, 0);
                modelo2.setValueAt(listaPedidos.get(i).getPaquetes().getNombre(), i, 1);
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                modelo2.setValueAt(formato.format(listaPedidos.get(i).getFechaRequerida()), i, 2);
                modelo2.setValueAt("$" + listaPedidos.get(i).getPrecio(), i, 3);
                pedidos.add(listaPedidos.get(i));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void cargarPedidosCliente() {
        try {
            Object o[] = null;
            String[] splited = textoBuscarCliente.getText().split(" ");
            List<Pedidos> listaPedidos = fachadaControl.getPedidosCliente(splited[0],splited[1]);
            for (int i = 0; i < listaPedidos.size(); i++) {
                modelo2.addRow(o);
                modelo2.setValueAt(listaPedidos.get(i).getClientes().getNombre() + " " + listaPedidos.get(i).getClientes().getApellido(), i, 0);
                modelo2.setValueAt(listaPedidos.get(i).getPaquetes().getNombre(), i, 1);
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                modelo2.setValueAt(formato.format(listaPedidos.get(i).getFechaRequerida()), i, 2);
                modelo2.setValueAt("$"+listaPedidos.get(i).getPrecio(), i, 3);
                
            }
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        botonElimiinarPedido = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        textoBuscarCliente = new javax.swing.JTextField();

        setBackground(new java.awt.Color(107, 19, 43));

        tablaPedidos.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        tablaPedidos.setForeground(new java.awt.Color(102, 0, 0));
        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaPedidos.setSelectionBackground(new java.awt.Color(107, 19, 43));
        tablaPedidos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPedidosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPedidos);

        jButton1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        botonElimiinarPedido.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        botonElimiinarPedido.setText("Eliminar");
        botonElimiinarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonElimiinarPedidoActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jButton3.setText("Modificar");

        jComboBox1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Por cliente", "Por fecha", "Por paquete", "Por Precio", " " }));
        jComboBox1.setToolTipText("");

        textoBuscarCliente.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        textoBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoBuscarClienteKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonElimiinarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)))
                .addGap(68, 68, 68))
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(textoBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonElimiinarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGap(47, 47, 47))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        PanelRegistrarPedido registrarPedido = new PanelRegistrarPedido(fachadaControl);
        panelPrincipal.removeAll();
        panelPrincipal.add(registrarPedido, "Registrar Pedido");
        panelPrincipal.repaint();
        panelPrincipal.revalidate();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textoBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoBuscarClienteKeyReleased
        // TODO add your handling code here:
        try {
            cargarPedidosCliente();
            System.out.println("Encontrado");  
        } catch (Exception e) {
            System.out.println("No existe el cliente");
        }
    }//GEN-LAST:event_textoBuscarClienteKeyReleased

    private void botonElimiinarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonElimiinarPedidoActionPerformed
        // TODO add your handling code here:
        fachadaControl.eliminarPedido(pedidos.get(row));
        try {
            
            JOptionPane.showMessageDialog(this, "Pedido eliminado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el pedido");
        }
    }//GEN-LAST:event_botonElimiinarPedidoActionPerformed

    private void tablaPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPedidosMouseClicked
        // TODO add your handling code here:
        row = tablaPedidos.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_tablaPedidosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonElimiinarPedido;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JTextField textoBuscarCliente;
    // End of variables declaration//GEN-END:variables
}
