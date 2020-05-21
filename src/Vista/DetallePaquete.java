/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controladores.IFachadaControl;
import Entidades.Detalleentregablespaquete;
import Entidades.Detalleentregablespedido;
import Entidades.Detalleservicio;
import Entidades.Entregables;
import Entidades.Servicios;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ferra
 */
public class DetallePaquete extends javax.swing.JDialog {

    private IFachadaControl fachadaControl;
    private List<Entregables> listaEntregables;
    private List<Servicios> listaServicios;
    private List<Detalleentregablespedido> listaEntregablesPedido;
    private List<Detalleservicio> listaServiciosesPedido;
    private boolean seleccionado = false;
    

      
    public  DetallePaquete(IFachadaControl fachadaControl,int id) {
        initComponents();
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.fachadaControl = fachadaControl;
        listaEntregablesPedido = new LinkedList<Detalleentregablespedido>();
        listaServiciosesPedido = new LinkedList<Detalleservicio>();
        CrearModeloEntregables();
        CrearModeloServicios();
        cargarEntregables(id);
        cargarServicios(id);
        tituloPaquete.setText(fachadaControl.getPaqueteId(id).getNombre().toString());
        cargarListaEntregables();
        cargarListaServicios();
        diseñoTablas();
        
    }
    
     private void diseñoTablas(){
        tablaServicios.getTableHeader().setOpaque(false);
        tablaServicios.getTableHeader().setForeground(Color.white);
        tablaServicios.setRowHeight(30);
        tablaServicios.getColumnModel().getColumn(0).setMaxWidth(200);
        tablaServicios.getColumnModel().getColumn(1).setMaxWidth(100);
        tablaServicios.getColumnModel().getColumn(2).setMaxWidth(350);
        tablaServicios.getTableHeader().setReorderingAllowed(false);
        
        tablaEntregables.getTableHeader().setOpaque(false);
        tablaEntregables.getTableHeader().setForeground(Color.white);
        tablaEntregables.setRowHeight(30);
        tablaEntregables.getColumnModel().getColumn(0).setMaxWidth(400);
        tablaEntregables.getColumnModel().getColumn(1).setMaxWidth(100);
        tablaEntregables.getColumnModel().getColumn(2).setMaxWidth(100);
        tablaEntregables.getTableHeader().setReorderingAllowed(false);
    }


    
         DefaultTableModel modelo3;
         DefaultTableModel modelo4;
    private void CrearModeloEntregables() {
        try {
            modelo3 = (new DefaultTableModel(
                    null, new String[]{
                        "Tipo", "Tamaño", "Cantidad"}) {
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
                        "Tipo", "Tiempo", "Detalle"}) {
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
                modelo3.setValueAt(fachadaControl.getEntregableId(listaEntregables.get(i).getEntregablesIdentregable()).getTipo(), i, 0);
                modelo3.setValueAt(fachadaControl.getEntregableId(listaEntregables.get(i).getEntregablesIdentregable()).getTamaño(), i, 1);
                modelo3.setValueAt(listaEntregables.get(i).getCantidadEntregable(), i, 2);
                listaEntregablesPedido.add(new Detalleentregablespedido(fachadaControl.getEntregableId(listaEntregables.get(i).getEntregablesIdentregable()),listaEntregables.get(i).getCantidadEntregable()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void cargarServicios(int id){
        try {
            System.out.println("CargarServicios");
            Object o[] = null;
            List<Detalleentregablespaquete> listaServicios = fachadaControl.getDetallePaqueteServicio(id);
            for (int i = 0; i < listaServicios.size(); i++) {
                modelo4.addRow(o);
                modelo4.setValueAt(fachadaControl.getServiciosId(listaServicios.get(i).getServiciosIdservicio()).getTipo(), i, 0);
                modelo4.setValueAt(listaServicios.get(i).getTiempoServicio(), i, 1);
                modelo4.setValueAt(fachadaControl.getServiciosId(listaServicios.get(i).getServiciosIdservicio()).getDetalle(), i, 2);
                listaServiciosesPedido.add(new Detalleservicio(fachadaControl.getServiciosId(listaServicios.get(i).getServiciosIdservicio()),listaServicios.get(i).getTiempoServicio()));
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
            listaEntregables = fachadaControl.getEntregables();
            for (int i = 0; i < listaEntregables.size(); i++) {
                modelo.addElement(listaEntregables.get(i).getTipo()+ " " + listaEntregables.get(i).getTamaño());
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
            listaServicios = fachadaControl.getServicios();
            for (int i = 0; i < listaServicios.size(); i++) {
                modelo.addElement(listaServicios.get(i).getTipo()+ " " + listaServicios.get(i).getLugar());
            }
            listS.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Detalleservicio> getServiciosPedido() {
        return listaServiciosesPedido;
    }

    public List<Detalleentregablespedido> getEntregablesPedido() {
        return listaEntregablesPedido;
    }

    public boolean seleccionado() {
        return seleccionado;
    }

    public void setSeleccionado() {
        this.seleccionado = true;
    }

    public void cambiarPaquete() {
        this.seleccionado = false;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listE = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        listS = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEntregables = new javax.swing.JTable();
        tituloPaquete = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaServicios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSeleccionarPaquete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(107, 19, 43));
        setForeground(new java.awt.Color(255, 51, 51));

        jPanel1.setBackground(new java.awt.Color(107, 19, 43));

        listE.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        listE.setForeground(new java.awt.Color(102, 0, 0));
        listE.setSelectionBackground(new java.awt.Color(107, 19, 43));
        listE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listEMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(listE);

        listS.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        listS.setForeground(new java.awt.Color(102, 0, 0));
        listS.setSelectionBackground(new java.awt.Color(107, 19, 43));
        listS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listSMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(listS);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(700, 300));

        tablaEntregables.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
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
        tablaEntregables.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaEntregables.setGridColor(new java.awt.Color(204, 204, 204));
        tablaEntregables.setSelectionBackground(new java.awt.Color(107, 19, 43));
        tablaEntregables.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEntregablesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaEntregables);

        tituloPaquete.setFont(new java.awt.Font("Trebuchet MS", 0, 36)); // NOI18N
        tituloPaquete.setForeground(new java.awt.Color(255, 255, 255));
        tituloPaquete.setText("Titulo Paquete");

        tablaServicios.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
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

        jLabel1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Servicios");

        jLabel2.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Entregables");

        btnSeleccionarPaquete.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        btnSeleccionarPaquete.setText("Seleccionar Paquete");
        btnSeleccionarPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarPaqueteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))
                        .addGap(90, 90, 90))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(tituloPaquete)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSeleccionarPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(474, 474, 474))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(tituloPaquete)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addGap(35, 35, 35)
                .addComponent(btnSeleccionarPaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaEntregablesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEntregablesMouseClicked
        tablaServicios.clearSelection();
        listE.clearSelection();
        listS.clearSelection();
        if (evt.getClickCount() == 2) {
            int row = tablaEntregables.rowAtPoint(evt.getPoint());
             String tipo = tablaEntregables.getValueAt(row, 0).toString();
            for (int i = 0; i < listaEntregablesPedido.size(); i++) {
                String tipoT = fachadaControl.getEntregableId(listaEntregablesPedido.get(i).getEntregables().getIdentregable()).getTipo();
                if (tipo.equals(tipoT)) {
                    modelo3.removeRow(row);
                    listaEntregablesPedido.remove(row);
                }
            }
        }
    }//GEN-LAST:event_tablaEntregablesMouseClicked

    public static final String[] duraciones = {"30 min", "1 hora", "1 hr 30 min", "2 horas", "3 horas", "4 horas", "5 horas"};
    private void listSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listSMouseClicked
        // TODO add your handling code here:
        tablaEntregables.clearSelection();
        listE.clearSelection();
        tablaServicios.clearSelection();
        if (evt.getClickCount() == 2) {
            Servicios servicioSeleccionado = listaServicios.get(listS.getSelectedIndex());
            String duracion = (String) JOptionPane.showInputDialog(this,
                    "Duracion servicio",
                    "Elige la duracion del servicio:",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    duraciones,
                    duraciones[0]);
            if (duracion != null) {
                modelo4.addRow(new Object[]{servicioSeleccionado.getTipo(), duracion, servicioSeleccionado.getDetalle()});
                listaServiciosesPedido.add(new Detalleservicio(fachadaControl.getServiciosId(servicioSeleccionado.getIdservicio()),duracion));
            }
        }
    }//GEN-LAST:event_listSMouseClicked

    private void listEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listEMouseClicked
        // TODO add your handling code here:
        tablaEntregables.clearSelection();
        tablaServicios.clearSelection();
        listS.clearSelection();
        if (evt.getClickCount() == 2) {
            Entregables entregableSeleccionado = listaEntregables.get(listE.getSelectedIndex());
            String cantidad = JOptionPane.showInputDialog(this, "Cantidad de entregables", "Cantidad",JOptionPane.QUESTION_MESSAGE);
            if (cantidad != null) {
                modelo3.addRow(new Object[]{entregableSeleccionado.getTipo(),entregableSeleccionado.getTamaño(),cantidad});
                listaEntregablesPedido.add(new Detalleentregablespedido(fachadaControl.getEntregableId(entregableSeleccionado.getIdentregable()),Integer.parseInt(cantidad)));
            }
        }
    }//GEN-LAST:event_listEMouseClicked

    private void tablaServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaServiciosMouseClicked

        tablaEntregables.clearSelection();
        listE.clearSelection();
        listS.clearSelection();
        if (evt.getClickCount() == 2) {
            int row = tablaServicios.rowAtPoint(evt.getPoint());
            String tipo = tablaServicios.getValueAt(row, 0).toString();
            for (int i = 0; i < listaServiciosesPedido.size(); i++) {
                String tipoT = fachadaControl.getServiciosId(listaServiciosesPedido.get(i).getServicios().getIdservicio()).getTipo();
                if (tipo.equals(tipoT)) {
                    modelo4.removeRow(row);
                    listaServiciosesPedido.remove(row);
                }
            }

        }
    }//GEN-LAST:event_tablaServiciosMouseClicked

    private void btnSeleccionarPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarPaqueteActionPerformed
//        fachadaControl.asignarServiciosPedido(listaServiciosesPedido);
//        fachadaControl.asignarEntregablesPedido(listaEntregablesPedido);
        if (listaServiciosesPedido.size() != 0 && listaEntregablesPedido.size() != 0) {
            seleccionado = true;
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese al menos un servicio y un entregable");
        }

    }//GEN-LAST:event_btnSeleccionarPaqueteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeleccionarPaquete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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

