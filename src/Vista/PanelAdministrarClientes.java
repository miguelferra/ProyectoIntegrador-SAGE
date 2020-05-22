/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controladores.IFachadaControl;
import Entidades.Clientes;
import Entidades.Pedidos;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ferra
 */
public class PanelAdministrarClientes extends javax.swing.JPanel {

    /**
     * Creates new form PanelAdministrarClientes
     */
    IFachadaControl fachadaControl;
    List<Clientes> clientes;
    JPanel panelPrincipal;
    DefaultTableModel modelo2;
    int row;
    
    public PanelAdministrarClientes(IFachadaControl fachadaControl,JPanel panelPrincipal) {
        initComponents();
        this.fachadaControl = fachadaControl;
        this.panelPrincipal = panelPrincipal;
        this.clientes = new LinkedList<>();
        CrearModelo2();
        cargarClientes();
        diseñoTabla();
    }
    
    private void diseñoTabla(){    
        tablaClientes.setRowHeight(30);
        tablaClientes.getTableHeader().setReorderingAllowed(false);
    }
    
     private void CrearModelo2() {
        try {
            modelo2 = (new DefaultTableModel(
                    null, new String[]{
                        "Nombre","Apellido",
                        "Telefono","Email","Direccion"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false, false,false,false
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
            tablaClientes.setModel(modelo2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error2");
        }
    }

     private void cargarClientes(){
        try{
            System.out.println("CargarClientes");
            Object o[] = null;
            List<Clientes> listaClientes = fachadaControl.getClientes();
            for (int i = 0; i < listaClientes.size(); i++) {
                modelo2.addRow(o);
                modelo2.setValueAt(listaClientes.get(i).getNombre(), i, 0);
                modelo2.setValueAt(listaClientes.get(i).getApellido(), i, 1);
                modelo2.setValueAt(listaClientes.get(i).getTelefono(), i, 2);
                modelo2.setValueAt(listaClientes.get(i).getEmail(), i, 3);
                modelo2.setValueAt(listaClientes.get(i).getDireccion(), i, 4);
                clientes.add(listaClientes.get(i));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        modificarCliente = new javax.swing.JButton();
        eliminarCliente = new javax.swing.JButton();

        setBackground(new java.awt.Color(107, 19, 43));

        tablaClientes.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        tablaClientes.setForeground(new java.awt.Color(102, 0, 0));
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaClientes.setSelectionBackground(new java.awt.Color(107, 19, 43));
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

        jLabel1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Clientes");

        jButton1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        modificarCliente.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        modificarCliente.setText("Actualizar");
        modificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarClienteActionPerformed(evt);
            }
        });

        eliminarCliente.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        eliminarCliente.setText("Eliminar");
        eliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1062, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(195, 195, 195)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(eliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(modificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(158, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(128, 128, 128))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(eliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(modificarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new FrmNuevoCliente(fachadaControl).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void modificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarClienteActionPerformed
        // TODO add your handling code here:
        try {
            new FrmNuevoCliente(fachadaControl,clientes.get(row).getIdcliente()).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente");
        }
    }//GEN-LAST:event_modificarClienteActionPerformed

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        // TODO add your handling code here:
        row = tablaClientes.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void eliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarClienteActionPerformed
        // TODO add your handling code here:
        try {
            fachadaControl.eliminarCliente(clientes.get(row));
        JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se ha podido eliminar el cliente :(");
        }
    }//GEN-LAST:event_eliminarClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton eliminarCliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificarCliente;
    private javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables
}
