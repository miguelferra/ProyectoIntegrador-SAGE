/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controladores.IFachadaControl;
import Entidades.Detalleentregablespaquete;
import Entidades.Detalleentregablespedido;
import Entidades.Entregables;
import Entidades.Servicios;
import java.awt.Color;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ferra
 */
public class PanelServiciosEntregables extends javax.swing.JPanel {

    private IFachadaControl fachadaControl;
    private List<Entregables> listaEntregables;
    private List<Servicios> listaServicios;
    JPanel panelPrincipal;
    int row;
    /**
     * Creates new form PanelServiciosEntregables
     */
    public PanelServiciosEntregables(IFachadaControl fachadaControl,JPanel panelPrincipal) {
        initComponents();
        this.fachadaControl = fachadaControl;
        this.panelPrincipal = panelPrincipal;
        CrearModeloEntregables();
        CrearModeloServicios();
        cargarEntregables();
        cargarServicios();
        diseñoTablas();
    }
    
    private void diseñoTablas(){
        tablaServicios.setRowHeight(30);
        tablaServicios.getTableHeader().setReorderingAllowed(false);
       
        tablaEntregables.setRowHeight(30);
//        tablaEntregables.getColumnModel().getColumn(0).setMaxWidth(400);
//        tablaEntregables.getColumnModel().getColumn(1).setMaxWidth(100);
//        tablaEntregables.getColumnModel().getColumn(2).setMaxWidth(100);
        tablaEntregables.getTableHeader().setReorderingAllowed(false);
    }

    DefaultTableModel modelo3;
    DefaultTableModel modelo4;
    
    private void CrearModeloEntregables() {
        try {
            modelo3 = (new DefaultTableModel(
                    null, new String[]{
                        "Tipo", "Tamaño"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false
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
            tablaEntregables.setModel(modelo3);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error2");
        }
    }
    
    
    private void CrearModeloServicios() {
        try {
            modelo4 = (new DefaultTableModel(
                    null, new String[]{
                        "Tipo", "Detalle"}) {
                Class[] types = new Class[]{
                    java.lang.String.class,
                    java.lang.String.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false
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
            tablaServicios.setModel(modelo4);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error2");
        }
    }
    
    private void cargarEntregables() {
        try {
            System.out.println("CargarEntregables");
            Object o[] = null;
            listaEntregables = fachadaControl.getEntregables();
            for (int i = 0; i < listaEntregables.size(); i++) {
                modelo3.addRow(o);
                modelo3.setValueAt(listaEntregables.get(i).getTipo(), i, 0);
                modelo3.setValueAt(listaEntregables.get(i).getTamaño(), i, 1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
     private void cargarServicios() {
        try {
            System.out.println("CargarServicios");
            Object o[] = null;
            listaServicios = fachadaControl.getServicios();
            for (int i = 0; i < listaEntregables.size(); i++) {
                modelo4.addRow(o);
                modelo4.setValueAt(listaServicios.get(i).getTipo(), i, 0);
                modelo4.setValueAt(listaServicios.get(i).getDetalle(), i, 1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
  /*  private void cargarListaEntregables(){
        try {
            System.out.println("CargarListaEntregables");
            DefaultListModel modelo = new DefaultListModel();
            Object o[] = null;
            listaEntregables = fachadaControl.getEntregables();
            for (int i = 0; i < listaEntregables.size(); i++) {
                modelo.addElement(listaEntregables.get(i).getTipo()+ " " + listaEntregables.get(i).getTamaño());
            }
            listE.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }*/
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entregables = new javax.swing.JScrollPane();
        tablaEntregables = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaServicios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        eliminar = new javax.swing.JButton();
        botonRegistrar = new javax.swing.JButton();

        setBackground(new java.awt.Color(107, 19, 43));

        tablaEntregables.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        tablaEntregables.setForeground(new java.awt.Color(102, 0, 0));
        tablaEntregables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaEntregables.setSelectionBackground(new java.awt.Color(107, 19, 43));
        tablaEntregables.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEntregablesMouseClicked(evt);
            }
        });
        entregables.setViewportView(tablaEntregables);

        tablaServicios.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        tablaServicios.setForeground(new java.awt.Color(102, 0, 0));
        tablaServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaServicios.setSelectionBackground(new java.awt.Color(107, 19, 43));
        tablaServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaServiciosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaServicios);

        jLabel1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Entregables");

        jLabel2.setFont(new java.awt.Font("Yu Gothic Medium", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Servicios");

        eliminar.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        botonRegistrar.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        botonRegistrar.setText("Registrar");
        botonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1023, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(entregables)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGap(70, 70, 70))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(entregables, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(59, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
        // TODO add your handling code here:
        String[] options = {"Servicio", "Entregable"};
        int x = JOptionPane.showOptionDialog(null, "¿Que deseas registrar?",
                "Elección de registro",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (x == 0) {
            new FrmEntregableServicio(fachadaControl,"Servicio").setVisible(true);
        }else{
            new FrmEntregableServicio(fachadaControl,"Entregable").setVisible(true);
        }
    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
        if (tablaEntregables.getSelectedRow() != -1) {
            try {
                fachadaControl.eliminarEntregable(listaEntregables.get(tablaEntregables.getSelectedRow()));
                JOptionPane.showMessageDialog(this, "Entregable eliminado");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "No se ha podido eliminar el entregable :(");
            }
        }else if(tablaServicios.getSelectedRow() != -1){
            try {
                fachadaControl.eliminarServicio(listaServicios.get(tablaServicios.getSelectedRow()));
                JOptionPane.showMessageDialog(this, "Servicio eliminado");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "No se ha podido eliminar el servicio :(");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Selecciona un entregable o un servicio");
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void tablaEntregablesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEntregablesMouseClicked
        // TODO add your handling code here:
        tablaServicios.clearSelection();
        row = tablaEntregables.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_tablaEntregablesMouseClicked

    private void tablaServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaServiciosMouseClicked
        // TODO add your handling code here:
        tablaEntregables.clearSelection();
         row = tablaServicios.rowAtPoint(evt.getPoint());
    }//GEN-LAST:event_tablaServiciosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JButton eliminar;
    private javax.swing.JScrollPane entregables;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaEntregables;
    private javax.swing.JTable tablaServicios;
    // End of variables declaration//GEN-END:variables
}
