/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Van;

import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class van extends javax.swing.JInternalFrame {

    /**
     * Creates new form van
     */
    public van() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btyt = new javax.swing.JButton();
        btdt = new javax.swing.JButton();
        btxv = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(561, 562));

        btyt.setText("Tài liệu y tế");
        btyt.setPreferredSize(new java.awt.Dimension(187, 187));
        btyt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btytActionPerformed(evt);
            }
        });

        btdt.setText("Báo cáo điều trị");
        btdt.setPreferredSize(new java.awt.Dimension(187, 187));
        btdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdtActionPerformed(evt);
            }
        });

        btxv.setText("Báo cáo xuất viện");
        btxv.setPreferredSize(new java.awt.Dimension(187, 187));
        btxv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btdt, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                    .addComponent(btxv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btyt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btdt, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btxv, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btyt, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btytActionPerformed
        // TODO add your handling code here:
        TLYT tlyt = new TLYT();
        tlyt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tlyt.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            }
        });
        tlyt.setVisible(true);
    }//GEN-LAST:event_btytActionPerformed

    private void btdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdtActionPerformed
        // TODO add your handling code here:
        BCDT dt = new BCDT();
        dt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dt.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            }
        });
        dt.setVisible(true);
    }//GEN-LAST:event_btdtActionPerformed

    private void btxvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxvActionPerformed
        // TODO add your handling code here:
        BCXV xv = new BCXV();
        xv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        xv.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            }
        });
        xv.setVisible(true);
    }//GEN-LAST:event_btxvActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btdt;
    private javax.swing.JButton btxv;
    private javax.swing.JButton btyt;
    // End of variables declaration//GEN-END:variables
}