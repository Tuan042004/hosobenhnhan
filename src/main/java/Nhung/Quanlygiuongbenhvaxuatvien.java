/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Nhung;

import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class Quanlygiuongbenhvaxuatvien extends javax.swing.JInternalFrame {

    /**
     * Creates new form Quanlygiuongbenhvaxuatvien
     */
    public Quanlygiuongbenhvaxuatvien() {
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

        btxv = new javax.swing.JButton();
        btcd = new javax.swing.JButton();
        btgb = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(561, 562));

        btxv.setText("Quản lý lịch sử xuất viện");
        btxv.setPreferredSize(new java.awt.Dimension(187, 187));
        btxv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxvActionPerformed(evt);
            }
        });

        btcd.setText("Chẩn đoán");
        btcd.setPreferredSize(new java.awt.Dimension(187, 187));
        btcd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcdActionPerformed(evt);
            }
        });

        btgb.setText("Quản lý quá giường bệnh");
        btgb.setPreferredSize(new java.awt.Dimension(187, 187));
        btgb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btgbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btcd, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                    .addComponent(btgb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btxv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btcd, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btgb, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btxv, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btxvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxvActionPerformed
        // TODO add your handling code here:
        QuanLyChanDoan cd = new QuanLyChanDoan();
        cd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cd.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            }
        });
        cd.setVisible(true);
    }//GEN-LAST:event_btxvActionPerformed

    private void btcdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcdActionPerformed
        // TODO add your handling code here:
        QuanLyGiuongBenh qlgb = new QuanLyGiuongBenh();
        qlgb.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        qlgb.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            }
        });
        qlgb.setVisible(true);
    }//GEN-LAST:event_btcdActionPerformed

    private void btgbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btgbActionPerformed
        // TODO add your handling code here:
        QuanLyLsXuatVien qlxv = new QuanLyLsXuatVien();
        qlxv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        qlxv.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            }
        });
        qlxv.setVisible(true);
    }//GEN-LAST:event_btgbActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcd;
    private javax.swing.JButton btgb;
    private javax.swing.JButton btxv;
    // End of variables declaration//GEN-END:variables
}
