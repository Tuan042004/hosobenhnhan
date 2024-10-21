/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BTL;

import Hosoxuatvien.HoSoXuatVien;
import Hosoxuatvien.HoaDonThanhToan;
import Kien.Thuoc;
import Kien.DieuTri;
import Kien.ThuocThang;
import QLBN.QuanLyBenhNhan1;
import QLBN.QuanLyKhoa;
import QLBN.QuanLyNhanVien;
import Van.IFhsnv;
import com.formdev.flatlaf.FlatLightLaf;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author Admin
 */
public class Trangchu extends javax.swing.JFrame {

    /**
     * Creates new form Trangchu
     */
    public Trangchu() {
        
        try {
            UIManager.setLookAndFeel(new FlatLightLaf()); // Hoặc FlatDarkLaf
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btbn = new javax.swing.JButton();
        btkhoa = new javax.swing.JButton();
        btnv = new javax.swing.JButton();
        btt = new javax.swing.JButton();
        btpb = new javax.swing.JButton();
        btg = new javax.swing.JButton();
        bthsnv = new javax.swing.JButton();
        btdt = new javax.swing.JButton();
        bthsxv = new javax.swing.JButton();
        bttt = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        dp = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 800));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("HỆ THỐNG QUẢN LÝ DANH SÁCH BỆNH NHÂN NỘI TRÚ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(170, 170, 170))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jToolBar1.setRollover(true);

        btbn.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Anh\\Benhnhan.png")); // NOI18N
        btbn.setText("Thông tin bệnh nhân");
        btbn.setFocusable(false);
        btbn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btbn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btbn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btbnMouseClicked(evt);
            }
        });
        jToolBar1.add(btbn);

        btkhoa.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Anh\\khoa.png")); // NOI18N
        btkhoa.setText("Khoa");
        btkhoa.setFocusable(false);
        btkhoa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btkhoa.setMargin(new java.awt.Insets(2, 15, 3, 15));
        btkhoa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btkhoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btkhoaMouseClicked(evt);
            }
        });
        jToolBar1.add(btkhoa);

        btnv.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Anh\\bacsi.png")); // NOI18N
        btnv.setFocusable(false);
        btnv.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnv.setLabel("Nhân viên y tế");
        btnv.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnvMouseClicked(evt);
            }
        });
        jToolBar1.add(btnv);

        btt.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Anh\\thuoc.png")); // NOI18N
        btt.setText("Thuốc");
        btt.setFocusable(false);
        btt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btt.setMargin(new java.awt.Insets(2, 15, 3, 15));
        btt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttMouseClicked(evt);
            }
        });
        jToolBar1.add(btt);

        btpb.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Anh\\phong.png")); // NOI18N
        btpb.setText("Phòng bệnh");
        btpb.setFocusable(false);
        btpb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btpb.setMargin(new java.awt.Insets(2, 15, 3, 15));
        btpb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btpb);

        btg.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Anh\\giuong.png")); // NOI18N
        btg.setText("Giường bệnh");
        btg.setFocusable(false);
        btg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btg.setMargin(new java.awt.Insets(2, 15, 3, 15));
        btg.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btg);

        bthsnv.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Anh\\nhapvien.png")); // NOI18N
        bthsnv.setText("Hồ sơ nhập viện");
        bthsnv.setFocusable(false);
        bthsnv.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bthsnv.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bthsnv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bthsnvMouseClicked(evt);
            }
        });
        jToolBar1.add(bthsnv);

        btdt.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Anh\\quatrinhdieutri.png")); // NOI18N
        btdt.setText("Quá trình điều trị");
        btdt.setFocusable(false);
        btdt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btdt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btdt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btdtMouseClicked(evt);
            }
        });
        jToolBar1.add(btdt);

        bthsxv.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Anh\\xuatvien.png")); // NOI18N
        bthsxv.setText("Hồ sơ xuất viện");
        bthsxv.setFocusable(false);
        bthsxv.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bthsxv.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bthsxv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bthsxvMouseClicked(evt);
            }
        });
        jToolBar1.add(bthsxv);

        bttt.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Anh\\thanhtoan.png")); // NOI18N
        bttt.setText("Thanh toán");
        bttt.setFocusable(false);
        bttt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bttt.setMargin(new java.awt.Insets(2, 15, 3, 15));
        bttt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bttt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btttMouseClicked(evt);
            }
        });
        jToolBar1.add(bttt);

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Anh\\nutthoat.png")); // NOI18N
        jButton1.setText("Thoát");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jToolBar1.add(jButton1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        dp.setPreferredSize(new java.awt.Dimension(935, 820));

        javax.swing.GroupLayout dpLayout = new javax.swing.GroupLayout(dp);
        dp.setLayout(dpLayout);
        dpLayout.setHorizontalGroup(
            dpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dpLayout.setVerticalGroup(
            dpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(dp, javax.swing.GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dp, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btbnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btbnMouseClicked
        dp.removeAll();
        QuanLyBenhNhan1 bn = null;
         bn= new QuanLyBenhNhan1();
        dp.add(bn ).setVisible(true);
    }//GEN-LAST:event_btbnMouseClicked

    private void btttMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btttMouseClicked
        dp.removeAll();
        HoaDonThanhToan hdtt = null;
        try {
            hdtt = new HoaDonThanhToan();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
        dp.add(hdtt).setVisible(true);
    }//GEN-LAST:event_btttMouseClicked

    private void bthsxvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bthsxvMouseClicked
        dp.removeAll();
        HoSoXuatVien hsxv = null;
        try {
            hsxv = new HoSoXuatVien();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
        dp.add(hsxv ).setVisible(true);
    }//GEN-LAST:event_bthsxvMouseClicked

    private void bttMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttMouseClicked
        dp.removeAll();
        ThuocThang thuoc = null;
        thuoc = new ThuocThang();
        dp.add(thuoc).setVisible(true);
    }//GEN-LAST:event_bttMouseClicked

    private void btkhoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btkhoaMouseClicked
        dp.removeAll();
        QuanLyKhoa bn = null;
        bn= new QuanLyKhoa();
        dp.add(bn ).setVisible(true);
    }//GEN-LAST:event_btkhoaMouseClicked

    private void btnvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnvMouseClicked
        dp.removeAll();
        QuanLyNhanVien bn = null;
        bn= new QuanLyNhanVien();
        dp.add(bn ).setVisible(true);
    }//GEN-LAST:event_btnvMouseClicked

    private void btdtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btdtMouseClicked
        dp.removeAll();
        DieuTri bn = null;
        bn= new DieuTri();
        dp.add(bn ).setVisible(true);
    }//GEN-LAST:event_btdtMouseClicked

    private void bthsnvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bthsnvMouseClicked
        dp.removeAll();
        IFhsnv bn = null;
        try {
            bn= new IFhsnv();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Trangchu.class.getName()).log(Level.SEVERE, null, ex);
        }
        dp.add(bn ).setVisible(true);        
    }//GEN-LAST:event_bthsnvMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

    this.dispose();
    Login login = new Login();
    login.setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Trangchu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbn;
    private javax.swing.JButton btdt;
    private javax.swing.JButton btg;
    private javax.swing.JButton bthsnv;
    private javax.swing.JButton bthsxv;
    private javax.swing.JButton btkhoa;
    private javax.swing.JButton btnv;
    private javax.swing.JButton btpb;
    private javax.swing.JButton btt;
    private javax.swing.JButton bttt;
    private javax.swing.JDesktopPane dp;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
