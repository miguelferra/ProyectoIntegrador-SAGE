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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(107, 19, 43));

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
        entregables.setViewportView(tablaEntregables);

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
        jScrollPane2.setViewportView(tablaServicios);

        jLabel1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Entregables");

        jLabel2.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Servicios");

        jButton1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        jButton1.setText("Eliminar");

        jButton2.setFont(new java.awt.Font("Yu Gothic Medium", 0, 24)); // NOI18N
        jButton2.setText("Registrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(entregables, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(156, 156, 156)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(161, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jButton2)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(entregables, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(94, 94, 94))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton1)))
                .addGap(37, 37, 37))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane entregables;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaEntregables;
    private javax.swing.JTable tablaServicios;
    // End of variables declaration//GEN-END:variables
}
