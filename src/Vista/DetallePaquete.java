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
        cargarListaEntregables();
        cargarListaServicios();
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
                        "Tipo", "Tiempo", "Paquete"}) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEntregables = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaServicios = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        listE = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        listS = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        jScrollPane3.setViewportView(listE);

        jButton1.setText("+");

        jScrollPane4.setViewportView(listS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(171, 171, 171))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaEntregablesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEntregablesMouseClicked

                                 
    }//GEN-LAST:event_tablaEntregablesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<String> listE;
    private javax.swing.JList<String> listS;
    private javax.swing.JTable tablaEntregables;
    private javax.swing.JTable tablaServicios;
    // End of variables declaration//GEN-END:variables
}
