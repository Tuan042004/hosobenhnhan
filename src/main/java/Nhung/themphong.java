/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Nhung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class themphong extends javax.swing.JFrame {

    /**
     * Creates new form themphong
     */
    public themphong() {
        initComponents();
        load_Pb();
        load_CCB();
        
    }
    private void load_Pb(){
         try {
             TbPhong.removeAll(); 
        Connection con = BTL.Connect.KetnoiDB();
        String sql = "SELECT * FROM PhongBenh";
        Statement st = con.createStatement(); 
        ResultSet rs = st.executeQuery(sql); 
        String[] tieude = {"Mã phòng","Tên khoa","Loại Phòng","Số giường", "Trạng thái"};
        DefaultTableModel tb = new DefaultTableModel(tieude, 0);
        while (rs.next()) {
            Vector<String> v = new Vector<>();
            v.add(rs.getString("MaPhong")); 
            v.add(rs.getString("TenKhoa")); 
            v.add(rs.getString("LoaiPhong")); 
            v.add(rs.getString("SoGiuong")); 
            v.add(rs.getString("TrangThai"));
            tb.addRow(v); 
        }
        TbPhong.setModel(tb); 
        con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
private void load_CCB() {
    try {
        con = BTL.Connect.KetnoiDB();
        String query = "SELECT TenKhoa FROM Khoa"; // Thay đổi truy vấn để lấy mã bệnh nhân
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            tenk.addItem(rs.getString("TenKhoa")); // Thêm mã bệnh nhân vào ComboBox
        }
} catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi tải danh mục: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(themphong.class.getName()).log(Level.SEVERE, null, ex);
        }
}




    private boolean checkTrungMaPhong(String maPhong) {
    String sql = "SELECT COUNT(*) FROM PhongBenh WHERE MaPhong = ?";
    
    try (Connection con = BTL.Connect.KetnoiDB();
         PreparedStatement pst = con.prepareStatement(sql)) {
        
        pst.setString(1, maPhong);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            return rs.getInt(1) > 0; // Trả về true nếu có ít nhất 1 bản ghi
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false; // Trả về false nếu không có bản ghi nào
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        them = new javax.swing.JButton();
        capnhat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        xoa = new javax.swing.JButton();
        thoát = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tenk = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        map = new javax.swing.JTextField();
        loaip = new javax.swing.JTextField();
        ttp = new javax.swing.JTextField();
        soGiuong = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TbPhong = new javax.swing.JTable();
        Refresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(680, 430));

        them.setText("Thêm ");
        them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themActionPerformed(evt);
            }
        });

        capnhat.setText("Cập nhật");
        capnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capnhatActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("THÔNG TIN PHÒNG BỆNH");

        xoa.setText("Xóa");
        xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaActionPerformed(evt);
            }
        });

        thoát.setText("Thoát");
        thoát.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thoátActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel3.setText("Mã phòng:");

        jLabel4.setText("Tên Khoa:");

        jLabel5.setText("Loại phòng:");

        tenk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Nhập tên Khoa --" }));
        tenk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenkActionPerformed(evt);
            }
        });

        jLabel6.setText("Số giường:");

        jLabel7.setText("Trạng thái:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(ttp))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(map, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(loaip))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tenk, 0, 183, Short.MAX_VALUE)
                            .addComponent(soGiuong))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(tenk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(map, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(loaip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soGiuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(ttp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TbPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã phòng", "Tên khoa", "Loại phòng", "Số giường", "Trạng thái"
            }
        ));
        TbPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbPhongMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TbPhong);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Refresh.setText("Refresh");
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(them)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(capnhat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(xoa)
                                .addGap(18, 18, 18)
                                .addComponent(Refresh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(thoát))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(them)
                    .addComponent(capnhat)
                    .addComponent(xoa)
                    .addComponent(thoát)
                    .addComponent(Refresh))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    Connection con;
    private void themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themActionPerformed
        String mp = map.getText().trim();
        String khoa = tenk.getSelectedItem().toString().trim();
        String lp = loaip.getText().trim();
        String sg = soGiuong.getText().trim();
        String tt = ttp.getText().trim();

        // B1.1: Kiểm tra các trường bắt buộc phải nhập
        if (mp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã phòng không được để trống!");
            map.requestFocus();
            return;
        }

        if (khoa.equals("-- Nhập tên Khoa --")) {
            JOptionPane.showMessageDialog(this, "Tên Khoa không không hợp lệ!");
            tenk.requestFocus();
            return;
        }

        if (lp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Loại phòng không được để trống!");
            loaip.requestFocus();
            return;
        }

        if (sg.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số giường kkhông được để trống!");
            soGiuong.requestFocus();
            return;
        }

        if (tt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Trạng thái phòng không được để trống!");
            ttp.requestFocus();
            return;
        }
        
        // Kiểm tra trùng mã phòng
        if (checkTrungMaPhong(mp)) {
            JOptionPane.showMessageDialog(this, "Mã phòng đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            map.requestFocus();
            return;
        }
        PreparedStatement pst = null;
        Connection con = null;
        try {
            // B1.2: Kết nối với cơ sở dữ liệu
            con = BTL.Connect.KetnoiDB();

            // Sử dụng PreparedStatement để tăng cường bảo mật
            String sql = "INSERT INTO PhongBenh(MaPhong, TenKhoa,"
            + " LoaiPhong, SoGiuong, TrangThai) VALUES (?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);

            // Thiết lập các tham số cho PreparedStatement
            pst.setString(1, mp);  
            pst.setString(2, khoa);  
            pst.setString(3, lp); 
            pst.setString(4, sg); 
            pst.setString(5, tt);  

            // Thực hiện câu lệnh INSERT
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Hồ sơ giường bệnh đã được thêm thành công!");

            load_Pb();
            //xoa trắng
            map.setText("");
            tenk.setSelectedIndex(0);
            loaip.setText("");
            soGiuong.setText("");
            ttp.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình thêm hồ sơ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_themActionPerformed

    private void capnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capnhatActionPerformed
        String mp = map.getText().trim();
        String khoa = tenk.getSelectedItem().toString().trim();
        String lp = loaip.getText().trim();
        String sg = soGiuong.getText().trim();
        String tt = ttp.getText().trim();

        // B1.1: Kiểm tra các trường bắt buộc phải nhập
        if (mp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã phòng không được để trống!");
            map.requestFocus();
            return;
        }

        if (khoa.equals("-- Nhập tên Khoa --")) {
            JOptionPane.showMessageDialog(this, "Tên Khoa không hợp lệ!");
            tenk.requestFocus();
            return;
        }

        if (lp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Loại phòng không được để trống!");
            loaip.requestFocus();
            return;
        }

        if (sg.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số giường không được để trống!");
            soGiuong.requestFocus();
            return;
        }

        if (tt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Trạng thái phòng không được để trống!");
            ttp.requestFocus();
            return;
        }
        
        Connection con = null;
        try {
            con = BTL.Connect.KetnoiDB();

            String sql = "UPDATE PhongBenh SET TenKhoa = ?, LoaiPhong = ?," +
            " SoGiuong = ?, TrangThai = ? WHERE MaPhong = ?";

            // Sử dụng PreparedStatement để tránh SQL Injection
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, khoa);
                pst.setString(2, lp);
                pst.setString(3, sg);
                pst.setString(4, tt);
                pst.setString(5, mp);

                int rowsUpdated = pst.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Hồ sơ phòng bệnh đã được cập nhật thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy hồ sơ với mã phòng đã cho!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
            load_Pb();
            // Xóa trắng các trường
            map.setText("");
            tenk.setSelectedIndex(0);
            loaip.setText("");
            soGiuong.setText("");
            ttp.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Cập nhật không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_capnhatActionPerformed

    private void xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaActionPerformed
        String mp = map.getText().trim();

        if (mp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn mã phòng để xóa.");
            return;
        }

        Connection con = null;
        PreparedStatement deleteStmt = null;

        try {
            con = BTL.Connect.KetnoiDB();

            String sql = "DELETE FROM PhongBenh WHERE MaPhong = ?";
            deleteStmt = con.prepareStatement(sql);
            deleteStmt.setString(1, mp);

            int rowsDeleted = deleteStmt.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Xóa thành công mã phòng: " + mp);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy phòng với mã: " + mp, "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
            load_Pb();
            map.setText("");
            tenk.setSelectedIndex(0);
            loaip.setText("");
            soGiuong.setText("");
            ttp.setText("");

        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this, "Không thể xóa phòng vì nó đang được tham chiếu bởi giường!", "Lỗi khóa ngoại", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xóa phòng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

    private void thoátActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoátActionPerformed
         // Hiển thị hộp thoại xác nhận
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

    private void TbPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbPhongMouseClicked
        int i = TbPhong.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một mã phòng để xem thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) TbPhong.getModel();

        // Cập nhật các trường nhập liệu
        tenk.setSelectedItem(getValueOrEmpty(model.getValueAt(i, 1)));
        loaip.setText(getValueOrEmpty(model.getValueAt(i, 2)));
        soGiuong.setText(getValueOrEmpty(model.getValueAt(i, 3)));
        map.setText(getValueOrEmpty(model.getValueAt(i, 0)));
        ttp.setText(getValueOrEmpty(model.getValueAt(i, 4)));
        map.setEnabled(false); // Vô hiệu hóa JTextField nếu cần
        }

        private String getValueOrEmpty(Object value) {
            return value != null ? value.toString() : "";
    }//GEN-LAST:event_TbPhongMouseClicked

    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
        try {
            load_Pb(); 
            map.setEnabled(true);  // Đảm bảo có thể nhập Mã phòng
            map.setText("");
            tenk.setSelectedItem("-- Nhập tên Khoa --"); 
            loaip.setText("");
            soGiuong.setText("");
            ttp.setText("");

            // Có thể thêm thông báo cho người dùng
            JOptionPane.showMessageDialog(this, "Thông tin đã được làm mới!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi làm mới thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_RefreshActionPerformed

    private void tenkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenkActionPerformed
     // Lấy tên khoa đã chọn
    String tenKhoa = (String) tenk.getSelectedItem();
    
    // Kiểm tra nếu tên khoa không phải là giá trị mặc định
    if (tenKhoa == null || tenKhoa.equals("-- Nhập mã khoa --")) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một tên khoa hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return; // Kết thúc phương thức nếu chọn giá trị không hợp lệ
    }

        try {
            // Tạo kết nối với cơ sở dữ liệu
            con = BTL.Connect.KetnoiDB();
            // Nếu bạn cần xử lý thêm thông tin nào đó với tên khoa ở đây, bạn có thể làm điều đó
            // Hiện tại không cần thêm truy vấn nào khác, chỉ đơn giản là bạn đã có tên khoa
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(themphong.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(themphong.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }//GEN-LAST:event_tenkActionPerformed

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
            java.util.logging.Logger.getLogger(themphong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(themphong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(themphong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(themphong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new themphong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Refresh;
    private javax.swing.JTable TbPhong;
    private javax.swing.JButton capnhat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField loaip;
    private javax.swing.JTextField map;
    private javax.swing.JTextField soGiuong;
    private javax.swing.JComboBox<String> tenk;
    private javax.swing.JButton them;
    private javax.swing.JButton thoát;
    private javax.swing.JTextField ttp;
    private javax.swing.JButton xoa;
    // End of variables declaration//GEN-END:variables
}
