/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controladores.IFachadaControl;
import Entidades.Detalleentregablespaquete;
import Entidades.Detalleservicio;
import Entidades.Entregables;
import Entidades.Servicios;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ferra
 */
public class DetallePaquete extends javax.swing.JFrame {

    IFachadaControl fachadaControl;
    /**
     * Creates new form DetallePaquete
     */
    public DetallePaquete(IFachadaControl fachadaControl,int id) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.fachadaControl = fachadaControl;
        
        CrearModeloEntregables();
        CrearModeloServicios();
        cargarEntregables(id);
        cargarServicios(id);
        tituloPaquete.setText(fachadaControl.getPaqueteId(id).getNombre().toString());
        cargarListaEntregables();
        cargarListaServicios();
        diseñoTablas();
    }
    
    public void diseñoTablas(){
        tablaEntregables.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD, 18));
        tablaEntregables.getColumnModel().getColumn(0).setMaxWidth(50);
        tablaEntregables.getColumnModel().getColumn(1).setMaxWidth(250);
        tablaEntregables.getColumnModel().getColumn(2).setMaxWidth(250);
        tablaEntregables.getTableHeader().setReorderingAllowed(false);
        
        tablaServicios.getColumnModel().getColumn(0).setMaxWidth(185);
        tablaServicios.getColumnModel().getColumn(1).setMaxWidth(185);
        tablaServicios.getColumnModel().getColumn(2).setMaxWidth(180);
        tablaServicios.getTableHeader().setReorderingAllowed(false);
    
    }

    
         DefaultTableModel modelo3;
         DefaultTableModel modelo4;
    private void CrearModeloEntregables() {
        try {
            modelo3 = (new DefaultTableModel(
                    null, new String[]{
                        "Cantidad", "Entregable", "Paquete"}) {
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
            tablaEntregables.setModel(modelo3);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error2");
        }
    }
    
    private void CrearModeloServicios() {
        try {
            modelo4 = (new DefaultTableModel(
                    null, new String[]{
                        "Tiempo", "Tipo", "Paquete"}) {
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
            tablaServicios.setModel(modelo4);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error2");
        }
    }
    
    private void cargarEntregables(int id) {
        try {
            System.out.println("CargarEntregables");
            Object o[] = null;
            List<Detalleentregablespaquete> listaEntregables = fachadaControl.getDetallePaqueteEntregable(id);
            for (int i = 0; i < listaEntregables.size(); i++) {
                modelo3.addRow(o);
                modelo3.setValueAt(listaEntregables.get(i).getCantidadEntregable(), i, 0);
                modelo3.setValueAt(fachadaControl.getEntregableId(listaEntregables.get(i).getEntregablesIdentregable()).getTipo(), i, 1);
                modelo3.setValueAt(listaEntregables.get(i).getPaquetesIdpaquete().getNombre(), i, 2);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void cargarServicios(int id){
        try {
            System.out.println("CargarServicios");
            Object o[] = null;
            List<Detalleentregablespaquete> listaEntregables = fachadaControl.getDetallePaqueteServicio(id);
            for (int i = 0; i < listaEntregables.size(); i++) {
                modelo4.addRow(o);
                modelo4.setValueAt(listaEntregables.get(i).getTiempoServicio(), i, 0);
                modelo4.setValueAt(fachadaControl.getServiciosId(listaEntregables.get(i).getServiciosIdservicio()).getTipo(), i, 1);
                modelo4.setValueAt(listaEntregables.get(i).getPaquetesIdpaquete().getNombre(), i, 2);

            } 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void cargarListaEntregables(){
        try {
            System.out.println("CargarListaEntregables");
            DefaultListModel modelo = new DefaultListModel();
            Object o[] = null;
            List<Entregables> listaEntregables = fachadaControl.getEntregables();
            for (int i = 0; i < listaEntregables.size(); i++) {
                modelo.addElement(listaEntregables.get(i).getTipo());
            }
            listE.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void cargarListaServicios(){
        try {
            System.out.println("CargarListaEntregables");
            DefaultListModel modelo = new DefaultListModel();
            Object o[] = null;
            List<Servicios> listaServicios = fachadaControl.getServicios();
            for (int i = 0; i < listaServicios.size(); i++) {
                modelo.addElement(listaServicios.get(i).getTipo());
            }
            listS.setModel(modelo);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaServicios = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEntregables = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        listS = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        listE = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        tituloPaquete = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(107, 19, 43));

        tablaServicios.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
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
        jScrollPane2.setViewportView(tablaServicios);

        tablaEntregables.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
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
        tablaEntregables.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEntregablesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaEntregables);

        jButton1.setText("+");

        listS.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jScrollPane4.setViewportView(listS);

        listE.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane3.setViewportView(listE);

        jButton2.setText("-");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("+");

        jButton4.setText("-");

        tituloPaquete.setFont(new java.awt.Font("Trebuchet MS", 0, 36)); // NOI18N
        tituloPaquete.setForeground(new java.awt.Color(255, 255, 255));
        tituloPaquete.setText("Titulo Paquete");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tituloPaquete)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(tituloPaquete)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jButton3)
                                .addGap(29, 29, 29)
                                .addComponent(jButton4)
                                .addGap(54, 54, 54))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(149, 149, 149)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(63, 63, 63))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(79, 79, 79))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaEntregablesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEntregablesMouseClicked

                                 
    }//GEN-LAST:event_tablaEntregablesMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> listE;
    private javax.swing.JList<String> listS;
    private javax.swing.JTable tablaEntregables;
    private javax.swing.JTable tablaServicios;
    private javax.swing.JLabel tituloPaquete;
    // End of variables declaration//GEN-END:variables
}
