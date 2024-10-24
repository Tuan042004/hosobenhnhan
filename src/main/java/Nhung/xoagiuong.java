/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Nhung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class xoagiuong extends javax.swing.JFrame {
//    private JComboBox<String> ma_g; // JComboBox cho mã giường
//    private JComboBox<String> ma_p;

    //contrustor không tham số
    public xoagiuong() throws SQLException, ClassNotFoundException {
        initComponents(); 
        load_Gb();      
        loadTableData(); 

        // Lắng nghe sự kiện khi chọn mã phòng
        ma_p.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object selectedItem = ma_p.getSelectedItem();
            if (selectedItem != null) {
                String mp = selectedItem.toString().trim();
                updateGiuongByPhong(mp); // Cập nhật mã giường dựa trên mã phòng đã chọn
            }
        }
        });
    }

  // Constructor nhận tham số
    public xoagiuong(JComboBox<String> ma_g, JComboBox<String> ma_p) throws ClassNotFoundException {
        initComponents();
        this.ma_g = ma_g;
        this.ma_p = ma_p;
        setFields(ma_g, ma_g);
        load_Gb();
    }

    // Hàm tải dữ liệu bảng
    private void loadTableData() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) tbGiuong.getModel();
        model.setRowCount(0); // Xóa tất cả các hàng hiện có

        try (Connection con = BTL.Connect.KetnoiDB()) {
            String sql = "SELECT * FROM Giuong"; 
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String maGiuong = rs.getString("MaGiuong");
                String maPhong = rs.getString("MaPhong");
                model.addRow(new Object[]{maGiuong, maPhong});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi nạp dữ liệu vào bảng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(xoagiuong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Nạp danh sách mã giường và mã phòng
    private void load_Gb() throws ClassNotFoundException {
        // Làm sạch JComboBox trước khi nạp lại dữ liệu
        ma_g.removeAllItems();
        ma_p.removeAllItems();

        // Nạp lại mã giường
        try (Connection con = BTL.Connect.KetnoiDB()) {
            String sql = "SELECT MaGiuong FROM Giuong"; // Câu lệnh SQL lấy mã giường
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            ma_g.addItem("-- Nhập mã giường --"); // Thêm item mặc định
            while (rs.next()) {
                ma_g.addItem(rs.getString("MaGiuong")); // Thêm mã giường vào JComboBox
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi nạp danh sách mã giường!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        try (Connection con = BTL.Connect.KetnoiDB()) {
            String sql = "SELECT MaPhong FROM PhongBenh"; // Câu lệnh SQL lấy mã phòng
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            ma_p.addItem("-- Nhập mã phòng --"); // Thêm item mặc định
            while (rs.next()) {
                ma_p.addItem(rs.getString("MaPhong")); // Thêm mã phòng vào JComboBox
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi nạp danh sách mã phòng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Hàm chung để nạp dữ liệu vào ComboBox
    private void loadComboBoxData(JComboBox<String> comboBox, String query, String defaultItem) {
        comboBox.removeAllItems();  // Xóa các mục hiện tại
        comboBox.addItem(defaultItem); // Thêm mục mặc định

        try (Connection con = BTL.Connect.KetnoiDB()) {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String item = rs.getString(1);
                if (itemExistsInComboBox(comboBox, item)) {
                } else {
                    comboBox.addItem(item);  // Thêm mục mới vào ComboBox
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi nạp danh sách!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(xoagiuong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// Kiểm tra xem mục đã tồn tại trong ComboBox hay chưa
    private boolean itemExistsInComboBox(JComboBox<String> comboBox, String item) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (comboBox.getItemAt(i).equals(item)) {
                return true;
            }
        }
        return false;
    }
    
  public void setFields(String maGiuong, String maPhong, String trangThai) {
        setFields(maGiuong, maPhong); 
    }

    public void setFields(JComboBox<String> maGiuong, JComboBox<String> maPhong) {
//        ma_g.addItem(maGiuong);
//        ma_p.addItem(maPhong);
        ma_p.setSelectedItem(maPhong); // Đặt mã phòng đã chọn
        ma_g.setSelectedItem(maGiuong); // Đặt mã giường đã chọn
    }

    private void updateGiuongByPhong(String mp) {
            // Nếu mã phòng là null hoặc rỗng, không làm gì cả
        if (mp == null || mp.isEmpty()) {
            return;
        }

        // Xóa tất cả các mục hiện tại trong JComboBox ma_g
        //ma_g.removeAllItems();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = BTL.Connect.KetnoiDB();
            String sql = "SELECT MaGiuong FROM Giuong WHERE MaPhong = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, mp);
            rs = stmt.executeQuery();

            // Làm mới JComboBox mã giường
            ma_g.removeAllItems();
            ma_g.addItem("-- Nhập mã giường --"); // Thêm item mặc định

            while (rs.next()) {
                String maGiuong = rs.getString("MaGiuong");
                ma_g.addItem(maGiuong); // Thêm mã giường vào JComboBox
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi tải mã giường!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(xoagiuong.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng kết nối và statement
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Refresh = new javax.swing.JButton();
        xoa = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbGiuong = new javax.swing.JTable();
        thoát = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ma_p = new javax.swing.JComboBox<>();
        ma_g = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(580, 320));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("XÓA GIƯỜNG BỆNH");

        Refresh.setText("Refresh");
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshActionPerformed(evt);
            }
        });

        xoa.setText("Xóa");
        xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaActionPerformed(evt);
            }
        });

        tbGiuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã Giường", "Mã Phòng"
            }
        ));
        tbGiuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGiuongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbGiuong);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        thoát.setText("Thoát");
        thoát.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thoátActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel3.setText("Mã giường:");

        jLabel4.setText("Mã phòng:");

        ma_p.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Nhập mã phòng --", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211", "212", "213", "214", "215", "301", "302", "303", "304", "305", "306", "307", "308", "309", "310", "311", "312", "313", "314", "315", " " }));

        ma_g.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Nhập mã giường --", " " }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ma_p, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(ma_g, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ma_g, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(ma_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(xoa)
                        .addGap(46, 46, 46)
                        .addComponent(Refresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(thoát))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xoa)
                    .addComponent(thoát)
                    .addComponent(Refresh))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    Connection con;
    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
        // TODO add your handling code here:
        try {
            load_Gb();
            ma_g.setEnabled(true);  // Đảm bảo có thể nhập Mã phòng
            ma_g.setSelectedItem("-- Nhập mã giường --");
            ma_p.setSelectedItem("-- Nhập mã phòng --");
            JOptionPane.showMessageDialog(this, "Thông tin đã được làm mới!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {

        }

    }//GEN-LAST:event_RefreshActionPerformed

    private void xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaActionPerformed
        String mg = ma_g.getSelectedItem().toString().trim();  
        String mp = ma_p.getSelectedItem().toString().trim(); 
        if (mg.equals("-- Nhập mã giường --") || mg.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã giường không hợp lệ.");
            return;
        }

        if (mp.equals("-- Nhập mã phòng --") || mp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã phòng không hợp lệ.");
            return;
        }

        Connection con = null;
        PreparedStatement deleteStmt = null;

        try {
            con =BTL.Connect.KetnoiDB();

            // Câu lệnh SQL để kiểm tra sự tồn tại của giường
            String checkSQL = "SELECT COUNT(*) FROM Giuong WHERE MaGiuong = ? AND MaPhong = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSQL);
            checkStmt.setString(1, mg);
            checkStmt.setString(2, mp);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy giường với mã: " + mg + " trong phòng: " + mp, "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Xóa giường
            String sql = "DELETE FROM Giuong WHERE MaGiuong = ? AND MaPhong = ?";
            deleteStmt = con.prepareStatement(sql);
            deleteStmt.setString(1, mg);
            deleteStmt.setString(2, mp);

            int rowsDeleted = deleteStmt.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Xóa thành công mã giường: " + mg + " trong phòng: " + mp);
                load_Gb(); 
                updateGiuongByPhong(mp); // Cập nhật mã giường cho phòng đã chọn
            } else {
                JOptionPane.showMessageDialog(this, "Không có giường nào được xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
            // Reset JComboBox sau khi xóa thành công
            ma_g.setSelectedItem("-- Nhập mã giường --");
            ma_p.setSelectedItem("-- Nhập mã phòng --");
            loadTableData(); // Nạp lại dữ liệu cho bảng

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xóa giường!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(xoagiuong.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (deleteStmt != null) {
                try {
                    deleteStmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_xoaActionPerformed

    private void tbGiuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGiuongMouseClicked
        // TODO add your handling code here:
        int i = tbGiuong.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một mã giường để xem thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tbGiuong.getModel();

        Object magValue = model.getValueAt(i, 0);
        Object mapValue = model.getValueAt(i, 1);
        Object ttgValue = model.getValueAt(i, 2);

        ma_g.setSelectedItem(magValue != null ? magValue.toString() : "");
        ma_p.setSelectedItem(mapValue != null ? mapValue.toString() : "");
        ma_g.setEnabled(false); // Vô hiệu hóa JTextField nếu cần
    }//GEN-LAST:event_tbGiuongMouseClicked

    private void thoátActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoátActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this,
            "Bạn có chắc chắn muốn thoát không?",
            "Xác nhận thoát",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        // Kiểm tra kết quả xác nhận
        if (confirm == JOptionPane.OK_OPTION) {
            // Nếu người dùng chọn OK, thực hiện lệnh thoát
            this.dispose();
        } else {
            // Nếu người dùng chọn Cancel, không làm gì
            // Hộp thoại đóng và không thoát
        }
    }//GEN-LAST:event_thoátActionPerformed

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
            java.util.logging.Logger.getLogger(xoagiuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(xoagiuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(xoagiuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(xoagiuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new xoagiuong().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(xoagiuong.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(xoagiuong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Refresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> ma_g;
    private javax.swing.JComboBox<String> ma_p;
    private javax.swing.JTable tbGiuong;
    private javax.swing.JButton thoát;
    private javax.swing.JButton xoa;
    // End of variables declaration//GEN-END:variables

    private void setFields(String maGiuong, String maPhong) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  


}
