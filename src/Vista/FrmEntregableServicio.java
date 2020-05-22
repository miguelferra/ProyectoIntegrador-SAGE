/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controladores.IFachadaControl;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author ferra
 */
public class FrmEntregableServicio extends javax.swing.JFrame {

    /**
     * Creates new form FrmEntregableServicio
     */
    IFachadaControl fachadaControl;
    
    public FrmEntregableServicio(IFachadaControl fachadaControl,String tipo) {
        initComponents();
        this.fachadaControl = fachadaControl;
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        if (tipo.equalsIgnoreCase("Entregable")) {
            textoTipo.setText("Registrar Entregable");
            texto2.setText("Tamaño");
            texto3.setText("");
            campoTexto3.setVisible(false);
        }else{
            textoTipo.setText("Registrar Servicio");
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
        textoTipo = new javax.swing.JLabel();
        texto1 = new javax.swing.JLabel();
        texto2 = new javax.swing.JLabel();
        texto3 = new javax.swing.JLabel();
        campoTexto1 = new javax.swing.JTextField();
        campoTexto2 = new javax.swing.JTextField();
        registrar = new javax.swing.JButton();
        scroll = new javax.swing.JScrollPane();
        campoTexto3 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(107, 19, 43));

        jPanel1.setBackground(new java.awt.Color(107, 19, 43));

        textoTipo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        textoTipo.setForeground(new java.awt.Color(255, 255, 255));
        textoTipo.setText("Tipo");

        texto1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        texto1.setForeground(new java.awt.Color(255, 255, 255));
        texto1.setText("Tipo:");

        texto2.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        texto2.setForeground(new java.awt.Color(255, 255, 255));
        texto2.setText("Lugar:");

        texto3.setFont(new java.awt.Font("Yu Gothic Medium", 0, 18)); // NOI18N
        texto3.setForeground(new java.awt.Color(255, 255, 255));
        texto3.setText("Detalle:");

        campoTexto1.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        campoTexto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTexto1ActionPerformed(evt);
            }
        });

        campoTexto2.setFont(new java.awt.Font("Yu Gothic Medium", 0, 14)); // NOI18N
        campoTexto2.setToolTipText("");

        registrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        registrar.setText("Registrar");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });

        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        campoTexto3.setColumns(20);
        campoTexto3.setRows(5);
        scroll.setViewportView(campoTexto3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(textoTipo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(texto3)
                                    .addComponent(texto2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(texto1)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoTexto2)
                                    .addComponent(campoTexto1)
                                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)))))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(textoTipo)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto1)
                    .addComponent(campoTexto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texto2)
                    .addComponent(campoTexto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(texto3)
                        .addGap(66, 66, 66))
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        // TODO add your handling code here:
        if (textoTipo.getText().equalsIgnoreCase("Registrar Entregable")) {
            if (campoTexto1.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "El tipo no puede ir vacío");
                return;
            }
            if (campoTexto2.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "El tamaño no puede ir vacío");
                return;
            }
            
            try {
                fachadaControl.registrarEntregable(campoTexto1.getText(), campoTexto2.getText());
                JOptionPane.showMessageDialog(this, "Entregable Agregado");
                this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "No se ha podido agregar");
            }
        }else{
             if (campoTexto1.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "El tipo no puede ir vacío");
                return;
            }
            if (campoTexto2.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "El lugar no puede ir vacío");
                return;
            }
            if (campoTexto3.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "El detalle no puede ir vacío");
                return;
            }
            try {
                fachadaControl.registrarServicio(campoTexto1.getText(), campoTexto2.getText(),campoTexto3.getText());
                JOptionPane.showMessageDialog(this, "Servicio Agregado");
                this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "No se ha podido agregar");
            }
        }
    }//GEN-LAST:event_registrarActionPerformed

    private void campoTexto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTexto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTexto1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoTexto1;
    private javax.swing.JTextField campoTexto2;
    private javax.swing.JTextArea campoTexto3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton registrar;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JLabel texto1;
    private javax.swing.JLabel texto2;
    private javax.swing.JLabel texto3;
    private javax.swing.JLabel textoTipo;
    // End of variables declaration//GEN-END:variables
}
