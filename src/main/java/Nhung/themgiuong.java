/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Nhung;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class themgiuong extends javax.swing.JFrame {

    /**
     * Creates new form themgiuong
     */
    public themgiuong() {
        initComponents();
        load_Gb();
        load_Combobox();
    }
    private void load_Gb() {
        String sql = "SELECT * FROM Giuong";
        String[] tieude = {"Mã giường", "Mã phòng", "Trạng thái"};
    DefaultTableModel tb=new DefaultTableModel(tieude,0)    {           
        @Override
        public boolean isCellEditable(int row, int column) {
        // Tất cả các ô sẽ không thể chỉnh sửa
        return false;
        }
    };

        try (Connection con = BTL.Connect.KetnoiDB();

             Statement st = con.createStatement(); 
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("MaGiuong")); 
                v.add(rs.getString("MaPhong")); 
                v.add(rs.getString("TrangThaiGiuong"));
                tb.addRow(v); 
            }
            tbGiuong.setModel(tb); 
            tbGiuong.repaint();  // Thêm lệnh này để làm mới bảng
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private  void load_Combobox(){
        String sql ="select MaPhong From PhongBenh";
        try {
            con =BTL.Connect.KetnoiDB();

            Statement st = con.createStatement();
            ResultSet rs= st.executeQuery(sql);
            while (rs.next()) {
            ma_p.addItem(rs.getString("MaPhong"));
            
        } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //cập nhật ds mã phòng trong combobox
    private void updateMaPhongComboBox(String maGiuong) {
        ma_p.removeAllItems(); // Xóa tất cả các mục hiện có trong JComboBox
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = BTL.Connect.KetnoiDB();
            String sql = "SELECT MaPhong FROM Giuong WHERE MaGiuong = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, maGiuong);
            rs = pst.executeQuery();
            // Thêm mãphòng vào JComboBox
            while (rs.next()) {
                ma_p.addItem(rs.getString("MaPhong"));
            }
            // Nếu không có mã phòng nào, thêm lựa chọn mặc định
            if (ma_p.getItemCount() == 0) {
                ma_p.addItem("-- Không có phòng nào --");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi tải danh sách phòng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }   catch (ClassNotFoundException ex) {
                Logger.getLogger(themgiuong.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
            // Đảm bảo đóng tài nguyên
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        thoát = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ma_p = new javax.swing.JComboBox<>();
        ma_g = new javax.swing.JTextField();
        tt = new javax.swing.JTextField();
        Refresh = new javax.swing.JButton();
        xoa = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbGiuong = new javax.swing.JTable();
        capnhat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        theM = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(680, 360));

        thoát.setText("Thoát");
        thoát.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thoátActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel3.setText("Mã giường:");

        jLabel4.setText("Mã phòng:");

        jLabel5.setText("Trạng thái:");

        ma_p.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Nhập mã phòng --" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ma_g)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ma_p, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(tt)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(ma_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ma_g, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

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
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Giường", "Mã Phòng", "Trạng Thái"
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        capnhat.setText("Cập nhật");
        capnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capnhatActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("THÔNG TIN GIƯỜNG BỆNH");

        theM.setText("Thêm ");
        theM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                theMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(theM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(capnhat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(xoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Refresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(thoát))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(228, 228, 228)
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
                    .addComponent(theM)
                    .addComponent(capnhat)
                    .addComponent(xoa)
                    .addComponent(thoát)
                    .addComponent(Refresh))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
            Connection con;
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

    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
        // TODO add your handling code here:
        try {
        load_Gb();
        ma_g.setEnabled(true);  // Đảm bảo có thể nhập Mã phòng
        ma_g.setText("");
        ma_p.setSelectedItem("-- Nhập mã phòng --");
        tt.setText("");
        JOptionPane.showMessageDialog(this, "Thông tin đã được làm mới!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            
        }       
    }//GEN-LAST:event_RefreshActionPerformed

    private void xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaActionPerformed
        String map = ma_p.getSelectedItem().toString(); 
        String mg = ma_g.getText(); 
        // Khởi tạo frame xoagiuong và truyền dữ liệu từ form chính
        xoagiuong xoag = null;
        try {
            xoag = new xoagiuong(ma_p, ma_p);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(themgiuong.class.getName()).log(Level.SEVERE, null, ex);
        }
        xoag.setFields(ma_p, ma_p);
        xoag.setVisible(true);
//        String mg = ma_g.getText().toString().trim();
//
//        if (mg.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Bạn chưa chọn mã giường để xóa.");
//            return;
//        }
//
//        Connection con = null;
//        PreparedStatement deleteStmt = null;
//
//        try {
//            con = conDB.ketnoidb(); // Kết nối tới cơ sở dữ liệu
//
//            // Câu lệnh SQL để kiểm tra sự tồn tại của giường
//            String checkSQL = "SELECT COUNT(*) FROM Giuong WHERE MaGiuong = ?";
//            PreparedStatement checkStmt = con.prepareStatement(checkSQL);
//            checkStmt.setString(1, mg);
//            ResultSet rs = checkStmt.executeQuery();
//
//            if (rs.next() && rs.getInt(1) == 0) {
//                JOptionPane.showMessageDialog(this, "Không tìm thấy giường với mã: " + mg, "Thông báo", JOptionPane.WARNING_MESSAGE);
//                return;
//            }
//
//            // Câu lệnh SQL để xóa giường
//            String sql = "DELETE FROM Giuong WHERE MaGiuong = ?";
//            deleteStmt = con.prepareStatement(sql);
//            deleteStmt.setString(1, mg);
//
//            int rowsDeleted = deleteStmt.executeUpdate();
//
//            if (rowsDeleted > 0) {
//                JOptionPane.showMessageDialog(this, "Xóa thành công mã giường: " + mg);
//            } else {
//                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xóa giường!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            }
//
//            load_Gb(); // Cập nhật lại danh sách sau khi xóa
//            load_Combobox();
//            // Xóa trắng các trường
//            ma_g.setText("");
//            ma_p.setSelectedItem("-- Nhập mã phòng --");
//            tt.setText("");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xóa giường!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        } finally {
//            if (deleteStmt != null) {
//                try {
//                    deleteStmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }//GEN-LAST:event_xoaActionPerformed

    private void tbGiuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGiuongMouseClicked
int i = tbGiuong.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một mã phòng để xem thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tbGiuong.getModel();
        ma_p.setSelectedItem(getValueOrEmpty(model.getValueAt(i, 1)));
        ma_g.setText(getValueOrEmpty(model.getValueAt(i, 0)));
        tt.setText(getValueOrEmpty(model.getValueAt(i, 2)));
        ma_g.setEnabled(false); // Vô hiệu hóa JTextField nếu cần
        }
        private String getValueOrEmpty(Object value) {
            return value != null ? value.toString() : "";
    }//GEN-LAST:event_tbGiuongMouseClicked

    private void capnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capnhatActionPerformed
        String mg = ma_g.getText().trim();
        String mp = ma_p.getSelectedItem().toString().trim(); 
        String tthai = tt.getText().trim(); 
        if (mp.equals("-- Nhập mã phòng --")) {
            JOptionPane.showMessageDialog(this, "Mã phòng không hợp lệ!");
            ma_p.requestFocus();
            return;
        }
        if (tthai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải nhập trạng thái!");
            return;
        }

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = BTL.Connect.KetnoiDB();
            if (con == null) {
                JOptionPane.showMessageDialog(this, "Không thể kết nối đến cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String checkSql = "SELECT * FROM Giuong WHERE MaGiuong = ? AND MaPhong = ?";
            pst = con.prepareStatement(checkSql);
            pst.setString(1, mg); 
            pst.setString(2, mp); 
            rs = pst.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy giường bệnh với mã: " + mg + " trong phòng: " + mp, "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Cập nhật mã phòng và trạng thái
            String updateSql = "UPDATE Giuong SET MaPhong = ?, TrangThaiGiuong = ? WHERE MaGiuong = ? AND MaPhong = ?";
            pst = con.prepareStatement(updateSql);
            pst.setString(1, mp); 
            pst.setString(2, tthai); 
            pst.setString(3, mg); 
            pst.setString(4, rs.getString("MaPhong")); 

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Hồ sơ giường bệnh đã được cập nhật thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy giường bệnh với mã: " + mg + " trong phòng: " + mp, "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
            // Tải lại dữ liệu sau khi cập nhật
            load_Combobox();
            load_Gb();
            ma_g.setText(""); 
            ma_p.setSelectedItem("-- Nhập mã phòng --");
            tt.setText(""); 
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình cập nhật hồ sơ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_capnhatActionPerformed

    private void theMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_theMActionPerformed
        String mg = ma_g.getText().trim();
        String mp = ma_p.getSelectedItem().toString().trim(); 
        String tthai = tt.getText().trim();
        if (mg.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã giường không được để trống!");
            return;
        }
        if (mp.equals("-- Nhập mã phòng --")) {
            JOptionPane.showMessageDialog(this, "Mã phòng không hợp lệ!");
            ma_p.requestFocus();
            return;
        }
        if (tthai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Trạng thái không được để trống!");
            return;
        }
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = BTL.Connect.KetnoiDB();

            if (con == null) {
                JOptionPane.showMessageDialog(this, "Không thể kết nối đến cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Kiểm tra xem mã giường có trùng trong cùng một phòng không
            String checkDuplicateSql = "SELECT * FROM Giuong WHERE MaGiuong = ? AND MaPhong = ?";
            pst = con.prepareStatement(checkDuplicateSql);
            pst.setString(1, mg); 
            pst.setString(2, mp);
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Giường đã tồn tại trong phòng này!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Kiểm tra số lượng giường hiện tại trong phòng
            String checkSql = "SELECT COUNT(*) AS SoGiuongHienTai FROM Giuong WHERE MaPhong = ?";
            pst = con.prepareStatement(checkSql);
            pst.setString(1, mp);
            rs = pst.executeQuery();

            int soGiuongHienTai = 0;
            if (rs.next()) {
                soGiuongHienTai = rs.getInt("SoGiuongHienTai");
            }
            // Lấy số giường tối đa từ bảng PhongBenh
            String maxGiuongSql = "SELECT SoGiuong FROM PhongBenh WHERE MaPhong = ?";
            pst = con.prepareStatement(maxGiuongSql);
            pst.setString(1, mp);
            rs = pst.executeQuery();
            int soGiuongToiDa = 0;
            if (rs.next()) {
                soGiuongToiDa = rs.getInt("SoGiuong");
            }
            // Kiểm tra xem phòng đã đầy hay chưa
            if (soGiuongHienTai >= soGiuongToiDa) {
                JOptionPane.showMessageDialog(this, "Giường đã đầy trong phòng này! Không thể thêm giường mới.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Không thêm giường mới nếu phòng đã đầy
            }
            // Thêm giường mới
            String sql = "INSERT INTO Giuong(MaGiuong, MaPhong, TrangThaiGiuong) VALUES (?, ?, ?)";
            pst = con.prepareStatement(sql);

            // Thiết lập các tham số cho PreparedStatement
            pst.setString(1, mg);
            pst.setString(2, mp);
            pst.setString(3, tthai);

            // Thực hiện câu lệnh INSERT
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Hồ sơ giường bệnh đã được thêm thành công!");

            // Tải lại dữ liệu sau khi thêm
            load_Gb();
            load_Combobox();
            ma_g.setText("");
            ma_p.setSelectedItem("-- Nhập mã phòng --");
            tt.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình thêm hồ sơ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_theMActionPerformed

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
            java.util.logging.Logger.getLogger(themgiuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(themgiuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(themgiuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(themgiuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new themgiuong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Refresh;
    private javax.swing.JButton capnhat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField ma_g;
    private javax.swing.JComboBox<String> ma_p;
    private javax.swing.JTable tbGiuong;
    private javax.swing.JButton theM;
    private javax.swing.JButton thoát;
    private javax.swing.JTextField tt;
    private javax.swing.JButton xoa;
    // End of variables declaration//GEN-END:variables
}
