/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Nhung;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ADMIN
 */
public class QuanLyGiuongBenh extends javax.swing.JFrame {

    /**
     * Creates new form QuanLyGiuongBenh
     */
    public QuanLyGiuongBenh() {
        initComponents();
        load_Gb();
    }
    Connection con;
    private void load_Gb(){
        try {
             tbGiuong.removeAll(); 
        con = BTL.Connect.KetnoiDB();
        String sql = "SELECT * FROM GiuongBenh";
        Statement st = con.createStatement(); 
        ResultSet rs = st.executeQuery(sql); 
        String[] tieude = {"Mã giường", "Mã phòng", "Trạng thái"};
        DefaultTableModel tb = new DefaultTableModel(tieude, 0);
        while (rs.next()) {
            Vector<String> v = new Vector<>();
            v.add(rs.getString("MaGiuong")); 
            v.add(rs.getString("MaPhong")); 
            v.add(rs.getString("TrangThai"));
            tb.addRow(v); 
        }
        tbGiuong.setModel(tb); 
        con.close();
        } catch (Exception e) {
            e.printStackTrace();
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tk_mg = new javax.swing.JTextField();
        timkiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbGiuong = new javax.swing.JTable();
        capnhat = new javax.swing.JButton();
        xoa = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ma_g = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ma_p = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tt = new javax.swing.JTextField();
        thoat = new javax.swing.JButton();
        xuatexcel = new javax.swing.JButton();
        nhapexcel = new javax.swing.JButton();
        them = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("QUẢN LÝ GIƯỜNG BỆNH");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel2.setText("Mã giường:");

        tk_mg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tk_mgMouseClicked(evt);
            }
        });
        tk_mg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tk_mgKeyReleased(evt);
            }
        });

        timkiem.setText("Tìm kiếm");
        timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(tk_mg, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(timkiem)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tk_mg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(timkiem))
                .addGap(0, 0, Short.MAX_VALUE))
        );

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
                {null, null, null}
            },
            new String [] {
                "Mã giường", "Mã phòng", "Trạng thái"
            }
        ));
        tbGiuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGiuongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbGiuong);

        capnhat.setText("Cập nhật");
        capnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capnhatActionPerformed(evt);
            }
        });

        xoa.setText("Xóa");
        xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel3.setText("Mã giường:");

        jLabel4.setText("Mã phòng:");

        jLabel5.setText("Trạng thái:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ma_g, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(ma_p))
                    .addComponent(tt)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ma_g, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(ma_p, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        thoat.setText("Thoát");
        thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thoatActionPerformed(evt);
            }
        });

        xuatexcel.setText("Xuất Excel");
        xuatexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xuatexcelActionPerformed(evt);
            }
        });

        nhapexcel.setText("Nhập Excel");
        nhapexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhapexcelActionPerformed(evt);
            }
        });

        them.setText("Thêm");
        them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(them)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(capnhat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(xoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(xuatexcel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nhapexcel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(thoat))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(capnhat)
                    .addComponent(xoa)
                    .addComponent(thoat)
                    .addComponent(them)
                    .addComponent(xuatexcel)
                    .addComponent(nhapexcel))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timkiemActionPerformed
        // TODO add your handling code here:
        String searchTerm = tk_mg.getText().trim(); // Lấy mã giường bệnh từ JTextField

       if (searchTerm.isEmpty()) {
           JOptionPane.showMessageDialog(this, "Vui lòng nhập mã giường bệnh để tìm kiếm!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
           return; // Dừng lại nếu mã giường bệnh trống
       }

       Connection con = null;
       PreparedStatement pst = null;
       ResultSet rs = null;

       try {
           con = BTL.Connect.KetnoiDB(); // Kết nối cơ sở dữ liệu
           String sql = "SELECT * FROM GiuongBenh WHERE MaGiuong = ?"; // Câu lệnh SQL tìm theo MaGiuong
           pst = con.prepareStatement(sql);
           pst.setString(1, searchTerm); // Đưa mã giường bệnh vào câu truy vấn

           rs = pst.executeQuery(); // Thực hiện câu truy vấn

           if (rs.next()) {
               ma_g.setText(rs.getString("MaGiuong")); // Hiển thị mã giường
               ma_p.setText(rs.getString("MaPhong"));  // Hiển thị mã phòng
               tt.setText(rs.getString("TrangThai"));  // Hiển thị trạng thái giường bệnh
           } else {
               JOptionPane.showMessageDialog(this, "Không tìm thấy hồ sơ với mã giường bệnh đã cho!", "Thông báo", JOptionPane.WARNING_MESSAGE);
           }
       } catch (SQLException e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(this, "Lỗi SQL: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
       } catch (Exception e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
       } finally {
           // Đóng các tài nguyên (ResultSet, PreparedStatement, Connection)
           try {
               if (rs != null) rs.close();
               if (pst != null) pst.close();
               if (con != null) con.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
    }//GEN-LAST:event_timkiemActionPerformed

    private void tbGiuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGiuongMouseClicked
        // TODO add your handling code here:
      int i = tbGiuong.getSelectedRow();
    if (i != -1) {
        DefaultTableModel model = (DefaultTableModel) tbGiuong.getModel();
        
        // Kiểm tra giá trị null trước khi gán
        Object maGValue = model.getValueAt(i, 0);
        Object maPValue = model.getValueAt(i, 1);
        Object ttValue = model.getValueAt(i, 2);
        
        ma_g.setText(maGValue != null ? maGValue.toString() : ""); // Gán rỗng nếu null
        ma_p.setText(maPValue != null ? maPValue.toString() : "");
        tt.setText(ttValue != null ? ttValue.toString() : "");
        
        ma_g.setEnabled(false);
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một mã giường để xem thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_tbGiuongMouseClicked

    private void capnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capnhatActionPerformed
        String mg = ma_g.getText().trim();
        String mp = ma_p.getText().trim();
        String tthai = tt.getText().toString().trim();

        if (mg.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải nhập mã giường!");
            return;
        }
        if (mp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải nhập mã phòng!");
            return;
        }
        if (tthai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải nhập trạng thái!");
            return;
        }

        try {
            con = BTL.Connect.KetnoiDB();

            String sql = "UPDATE GiuongBenh SET " +
                         "MaPhong = ?, " +
                         "TrangThai = ? " +
                         "WHERE MaGiuong = ?";

            // Sử dụng PreparedStatement để tránh SQL Injection
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, mp);
                pstmt.setString(2, tthai);
                pstmt.setString(3, mg);

                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Hồ sơ GB đã được cập nhật thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy hồ sơ với mã GB đã cho!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }

            load_Gb(); // Gọi phương thức để tải lại dữ liệu
            xoatrang();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Cập nhật không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Đảm bảo đóng kết nối nếu nó chưa được đóng
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
        String mg = ma_g.getText().trim();

        // Kiểm tra xem mã giường bệnh có được nhập hay không
        if (mg.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã giường bệnh để xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Connection con = null;
        PreparedStatement deleteStmt = null;

        try {
            con = BTL.Connect.KetnoiDB();

            // Tạo câu lệnh SQL để xóa
            String sql = "DELETE FROM GiuongBenh WHERE MaGiuong = ?";
            deleteStmt = con.prepareStatement(sql);
            deleteStmt.setString(1, mg);

            // Thực thi câu lệnh xóa
            int rowsDeleted = deleteStmt.executeUpdate();

            // Kiểm tra nếu xóa thành công
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Giường bệnh đã được xóa thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy hồ sơ với mã GB đã cho!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }

            // Tải lại dữ liệu
            load_Gb();
            ma_g.setText("");
            ma_p.setText("");
            tt.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi SQL: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (deleteStmt != null) deleteStmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_xoaActionPerformed
    public void  xoatrang(){
        ma_g.setText("");
        ma_p.setText("");
        tt.setText("");
    }
    private void themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themActionPerformed
        // TODO add your handling code here:
        String mg = ma_g.getText().trim();
        String mp = ma_p.getText().trim();
        String tthai = tt.getText().toString().trim();

        // B1.1: Kiểm tra các trường bắt buộc phải nhập
        if (mg.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã giường không được để trống.");
            ma_g.requestFocus();  
            return;
        }

        if (mp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã phòng không được để trống.");
            ma_p.requestFocus();  
            return;
        }
        if (tthai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Trạng thái không được để trống.");
            tt.requestFocus();  
            return;
        }

        PreparedStatement pst = null;
        try {
            con = BTL.Connect.KetnoiDB();

            if (con == null) {
                JOptionPane.showMessageDialog(this, "Không thể kết nối đến cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Thoát khỏi phương thức nếu không kết nối được
            }

            // Sử dụng PreparedStatement để tăng cường bảo mật
            String sql = "INSERT INTO GiuongBenh(MaGiuong, MaPhong, TrangThai) VALUES (?, ?, ?)";
            pst = con.prepareStatement(sql);

            // Thiết lập các tham số cho PreparedStatement
            pst.setString(1, mg);
            pst.setString(2, mp);
            pst.setString(3, tthai);

            // Thực hiện câu lệnh INSERT
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Hồ sơ giường bệnh đã được thêm thành công!");

            load_Gb();
            ma_g.setText("");
            ma_p.setText("");
            tt.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra trong quá trình thêm hồ sơ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Đảm bảo đóng tài nguyên
            try {
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_themActionPerformed
 private static CellStyle DinhDangHeader(XSSFSheet sheet){
     // Tạo font
    XSSFFont font = sheet.getWorkbook().createFont();
    font.setFontName("Times New Roman");
    font.setBold(true);
    font.setFontHeightInPoints((short) 12); // kích thước font
    font.setColor(IndexedColors.WHITE.getIndex()); // màu chữ

    // Tạo CellStyle
    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
    cellStyle.setFont(font);
    cellStyle.setAlignment(HorizontalAlignment.CENTER);
    cellStyle.setVerticalAlignment(VerticalAlignment.TOP);

    cellStyle.setFillForegroundColor(IndexedColors.DARK_GREEN.getIndex());
    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    cellStyle.setBorderBottom(BorderStyle.THIN);
    cellStyle.setWrapText(true);

    return cellStyle;
 }
    private void xuatexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xuatexcelActionPerformed
         // TODO add your handling code here:
    Connection con = null; // Khai báo biến kết nối
    PreparedStatement st = null; // Khai báo biến PreparedStatement
    ResultSet rs = null; // Khai báo biến ResultSet

    try {
        // Tạo workbook và sheet
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Khoa");

        // Tạo tiêu đề cho bảng
        XSSFRow row = spreadsheet.createRow(2); // Dòng thứ 3 (chỉ số 2)
        Cell cell = row.createCell(0);
        cell.setCellValue("DANH SÁCH GIƯỜNG BỆNH");

        // Tạo dòng tiêu đề cột
        CellStyle cellStyle_Head = DinhDangHeader(spreadsheet);
        row = spreadsheet.createRow(3); // Dòng thứ 4 (chỉ số 3)

        // Thiết lập các tiêu đề cột
        cell = row.createCell(0);
        cell.setCellStyle(cellStyle_Head);
        cell.setCellValue("Mã Giường");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle_Head);
        cell.setCellValue("Mã Phòng");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle_Head);
        cell.setCellValue("Trạng thái");

        // Kết nối đến cơ sở dữ liệu
        con = BTL.Connect.KetnoiDB(); // Đảm bảo kết nối không null
        if (con == null) {
            System.out.println("Kết nối đến cơ sở dữ liệu thất bại.");
            return;
        }

        String sql = "SELECT * FROM GiuongBenh";
        
        // Thay đổi PreparedStatement
        st = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = st.executeQuery();

        // Kiểm tra có dữ liệu trả về
        if (!rs.next()) {
            System.out.println("Không có dữ liệu nào để xuất.");
            return;
        }

        // Trở về đầu ResultSet
        rs.beforeFirst();

        // Đổ dữ liệu từ ResultSet vào các ô trong Excel
        ResultSetMetaData rsmd = rs.getMetaData();
        int tongsocot = rsmd.getColumnCount();

        // Định dạng ô chứa dữ liệu
        CellStyle cellStyle_data = spreadsheet.getWorkbook().createCellStyle();
        cellStyle_data.setBorderLeft(BorderStyle.THIN);
        cellStyle_data.setBorderRight(BorderStyle.THIN);
        cellStyle_data.setBorderBottom(BorderStyle.THIN);

        int i = 0;
        while (rs.next()) {
            row = spreadsheet.createRow(4 + i); // Bắt đầu từ dòng 5

            // Điền dữ liệu vào các ô
            cell = row.createCell(0);
            cell.setCellStyle(cellStyle_data);
            cell.setCellValue(rs.getString("MaGiuong")); // Mã Giường

            cell = row.createCell(1);
            cell.setCellStyle(cellStyle_data);
            cell.setCellValue(rs.getString("MaPhong")); // Mã Phòng

            cell = row.createCell(2);
            cell.setCellStyle(cellStyle_data);
            cell.setCellValue(rs.getString("TrangThai")); // Trạng thái

            i++;
        }

        // Hiệu chỉnh độ rộng của cột
        for (int col = 0; col < tongsocot; col++) {
            spreadsheet.autoSizeColumn(col);
        }

        // Xuất file Excel
        File f = new File("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Nhung\\DanhSachChanDoan.xlsx");
        FileOutputStream out = new FileOutputStream(f);
        workbook.write(out);
        out.close();
        
    } catch (Exception e) {
        e.printStackTrace(); // In ra lỗi nếu có
    } finally {
        // Đóng các đối tượng liên quan trong khối finally
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_xuatexcelActionPerformed

    private void tk_mgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tk_mgKeyReleased
        // TODO add your handling code here:
        try {
           String mxv = tk_mg.getText().trim();  // Mã xuất viện

        // Kết nối đến cơ sở dữ liệu
        con = BTL.Connect.KetnoiDB();
        Statement st = con.createStatement();

        // Xây dựng câu lệnh SQL cho tìm kiếm
        String sql = "SELECT * FROM GiuongBenh WHERE MaGiuong LIKE '%" + mxv + "%'";

        // Thực hiện truy vấn
        ResultSet rs = st.executeQuery(sql);
        String[] tieude = {"Mã giường", "Mã phòng", "Trạng thái"};
        DefaultTableModel tb = new DefaultTableModel(tieude, 0);

        // Duyệt qua kết quả và thêm vào bảng
        while (rs.next()) {
            Vector<String> v = new Vector<>();
            v.add(rs.getString("MaGiuong"));         
            v.add(rs.getString("MaPhong"));        
            v.add(rs.getString("TrangThai"));       
            tb.addRow(v);
        }

        // Gán model mới cho bảng
        tbGiuong.setModel(tb);

        // Đóng kết nối
        con.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Tìm kiếm không thành công");

        }
    }//GEN-LAST:event_tk_mgKeyReleased
    private void ReadExcel(String tenFilepath){
     try {
        // Sử dụng tenFilepath đã được truyền vào phương thức
        FileInputStream fis = new FileInputStream(tenFilepath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0); // Lấy sheet đầu tiên
        Iterator<Row> itr = sheet.iterator();
        int row_count = 0;

        while (itr.hasNext()) {
            Row row = itr.next();
            if (row_count > 0) { // Bỏ qua dòng tiêu đề
                String mg = "";
                Cell cell1 = row.getCell(0);
                if (cell1 != null) {
                    if (cell1.getCellType() == CellType.STRING) {
                        mg = cell1.getStringCellValue().trim();
                    } else if (cell1.getCellType() == CellType.NUMERIC) {
                        mg = String.valueOf((int) cell1.getNumericCellValue());
                    }
                }

                // Kiểm tra xem mg có phải là chuỗi rỗng không
                if (mg.isEmpty()) {
                    row_count++;
                    continue; // Bỏ qua dòng này
                }

                String mp = "";
                Cell cell2 = row.getCell(1);
                if (cell2 != null) {
                    if (cell2.getCellType() == CellType.STRING) {
                        mp = cell2.getStringCellValue().trim();
                    } else if (cell2.getCellType() == CellType.NUMERIC) {
                        mp = String.valueOf((int) cell2.getNumericCellValue()).trim(); // Chuyển kiểu số thành chuỗi
                    }
                }

                String tt = "";
                Cell cell3 = row.getCell(2);
                if (cell3 != null) {
                    if (cell3.getCellType() == CellType.STRING) {
                        tt = cell3.getStringCellValue().trim();
                    } else if (cell3.getCellType() == CellType.NUMERIC) {
                        tt = String.valueOf((int) cell3.getNumericCellValue()).trim(); // Chuyển kiểu số thành chuỗi
                    }
                }

                // Gọi phương thức thêm giường bệnh
                Themgiuongbenh(mg, mp, tt);
            }
            row_count++;
        }
        JOptionPane.showMessageDialog(this, "Thêm giường bệnh bằng file thành công");
        load_Gb();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm giường bệnh: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(); // Vẫn in stack trace ra console để kiểm tra
    }
    }
    private void nhapexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhapexcelActionPerformed
        // TODO add your handling code here:
         try {
        JFileChooser fc = new JFileChooser();
        int lc = fc.showOpenDialog(this);
        if (lc == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            String tenfile = file.getName();
            if (tenfile.endsWith(".xlsx")) { // Kiểm tra file có đuôi .xlsx
                ReadExcel(file.getPath());
            } else {
                JOptionPane.showMessageDialog(this, "Phải chọn file excel có định dạng .xlsx", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không có file nào được chọn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi mở file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // In stack trace ra console để kiểm tra
        }
    }//GEN-LAST:event_nhapexcelActionPerformed

    private void thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thoatActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_thoatActionPerformed

    private void tk_mgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tk_mgMouseClicked
        xoatrang();
    }//GEN-LAST:event_tk_mgMouseClicked

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
            java.util.logging.Logger.getLogger(QuanLyGiuongBenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyGiuongBenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyGiuongBenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyGiuongBenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyGiuongBenh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton capnhat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField ma_g;
    private javax.swing.JTextField ma_p;
    private javax.swing.JButton nhapexcel;
    private javax.swing.JTable tbGiuong;
    private javax.swing.JButton them;
    private javax.swing.JButton thoat;
    private javax.swing.JButton timkiem;
    private javax.swing.JTextField tk_mg;
    private javax.swing.JTextField tt;
    private javax.swing.JButton xoa;
    private javax.swing.JButton xuatexcel;
    // End of variables declaration//GEN-END:variables

    private CellStyle DinhdangHeader(XSSFSheet spreadsheet) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    private void Themgiuongbenh(String mg, String mp, String tt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
