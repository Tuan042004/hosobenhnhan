/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Nhung;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
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
public class giuongbenh extends javax.swing.JInternalFrame {

    private Object magValue;


    public giuongbenh() {
        initComponents();
        load_Gb();
        load_Combobox();
    //sự kiện nút combobox mp trong tìm kiếm
    tk_mg.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            updateRoomComboBox(tk_mg.getText().trim());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(giuongbenh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void removeUpdate(DocumentEvent e) {
        try {
            updateRoomComboBox(tk_mg.getText().trim());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(giuongbenh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void changedUpdate(DocumentEvent e) {
        try {
            updateRoomComboBox(tk_mg.getText().trim());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(giuongbenh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    });
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
    
    //hiển thị duy nhất 1 thông tin vừa tìm kiếm ở table
    private void load_tt(String maGiuong) {
        if (maGiuong == null || maGiuong.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã giường để xem thông tin.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return; 
        }
        String sql = "SELECT * FROM Giuong WHERE MaGiuong = ?"; 
        String[] tieude = {"Mã giường", "Mã phòng", "Trạng thái"};
        DefaultTableModel tb = new DefaultTableModel(tieude, 0);
        try (Connection con = BTL.Connect.KetnoiDB();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, maGiuong);
            ResultSet rs = pst.executeQuery();
            // Kiểm tra xem có kết quả trả về hay không
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy giường với mã đã nhập!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return; 
            }
            // Thêm dữ liệu vào bảng
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("MaGiuong")); 
                v.add(rs.getString("MaPhong")); 
                v.add(rs.getString("TrangThaiGiuong"));
                tb.addRow(v); 
            }
            tbGiuong.setModel(tb); 
            tbGiuong.repaint();  // Làm mới bảng
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải thông tin giường.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    //tải lại dữ liệu trong table
    private void load_Gb() {
        String sql = "SELECT * FROM Giuong";
        String[] tieude = {"Mã giường", "Mã phòng", "Trạng thái"};
        DefaultTableModel tb = new DefaultTableModel(tieude, 0);

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
            tbGiuong.repaint();  //  làm mới bảng
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //cập nhật ds mã phòng dựa trên mã giường
    private void updateRoomComboBox(String maGiuong) throws ClassNotFoundException {
        tk_mp.removeAllItems(); 
        // Thêm giá trị mặc định vào JComboBox
        tk_mp.addItem("-- Nhập mã phòng --");

        try (Connection con = BTL.Connect.KetnoiDB()) {
            String sql = "SELECT DISTINCT MaPhong FROM Giuong WHERE MaGiuong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maGiuong);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tk_mp.addItem(rs.getString("MaPhong"));
            }

            if (tk_mp.getItemCount() > 1) { // Nếu có mã phòng được thêm
                tk_mp.setSelectedIndex(0); // Chọn mục "--nhập mã phòng--"
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi truy vấn mã phòng: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    //kiểm tra xem người dùng đã chọn một mã phòng hợp lệ 
    private void checkSelectedRoom() {
        String selectedRoom = (String) tk_mp.getSelectedItem();
        if ("-- Nhập mã phòng --".equals(selectedRoom)) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một mã phòng hợp lệ!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            tk_mp.requestFocus(); // Đưa con trỏ về JComboBox
        } else {
             // Thực hiện các xử lý khác khi mã phòng được chọn hợp lệ
        }
    }
    // thiết lập các giá trị cho các trường trong giao diện người dùng
    public void setFields(String maGiuong, String maPhong, String trangThai) {
        ma_g.setText(maGiuong);
        ma_p.setSelectedItem(maPhong);
        tt.setText(trangThai);
    }

//lấy ds mp tương ứng mg
    private List<String> getMaPhongsByMaGiuong(String maGiuong) throws ClassNotFoundException {
        List<String> maPhongs = new ArrayList<>();

        try (Connection con = BTL.Connect.KetnoiDB()) {
            String sql = "SELECT MaPhong FROM Giuong WHERE MaGiuong = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maGiuong);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                maPhongs.add(rs.getString("MaPhong"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maPhongs;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tk_mg = new javax.swing.JTextField();
        timkiem = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        tk_mp = new javax.swing.JComboBox<>();
        xuatex = new javax.swing.JButton();
        nhapex = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ma_p = new javax.swing.JComboBox<>();
        ma_g = new javax.swing.JTextField();
        tt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbGiuong = new javax.swing.JTable();
        thoát = new javax.swing.JButton();
        themmoi = new javax.swing.JButton();
        ref = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(980, 600));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel2.setText("Mã giường:");

        tk_mg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tk_mgKeyReleased(evt);
            }
        });

        timkiem.setText("Tìm Kiếm");
        timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timkiemActionPerformed(evt);
            }
        });

        jLabel6.setText("Mã phòng:");

        tk_mp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Nhập mã phòng --" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tk_mg, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tk_mp, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(timkiem)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tk_mg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timkiem)
                    .addComponent(jLabel6)
                    .addComponent(tk_mp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        xuatex.setText("Xuất Excel");
        xuatex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xuatexActionPerformed(evt);
            }
        });

        nhapex.setText("Nhập Excel");
        nhapex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhapexActionPerformed(evt);
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
                        .addComponent(ma_g, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ma_p, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))
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
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("QUẢN LÝ GIƯỜNG BỆNH");

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
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Giường", "Mã Phòng", "Trạng thái"
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
                .addComponent(jScrollPane2)
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

        themmoi.setText("Thêm mới");
        themmoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themmoiActionPerformed(evt);
            }
        });

        ref.setText("Refresh");
        ref.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(themmoi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(xuatex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nhapex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ref)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(thoát))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themmoi)
                    .addComponent(xuatex)
                    .addComponent(nhapex)
                    .addComponent(thoát)
                    .addComponent(ref))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    Connection con;
    private void tk_mgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tk_mgKeyReleased
        try {
            String mg = tk_mg.getText().trim();  
            con = BTL.Connect.KetnoiDB();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM Giuong WHERE MaGiuong LIKE '%" + mg + "%'";
            // Thực hiện truy vấn
            ResultSet rs = st.executeQuery(sql);
            String[] tieude = {"Mã giường", "Mã phòng", "Trạng thái"};
            DefaultTableModel tb = new DefaultTableModel(tieude, 0);
            // Duyệt qua kết quả và thêm vào bảng
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("MaGiuong"));
                v.add(rs.getString("MaPhong"));
                v.add(rs.getString("TrangThaiGiuong"));
                tb.addRow(v);
            }
            tbGiuong.setModel(tb);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Tìm kiếm không thành công");
        }
    }//GEN-LAST:event_tk_mgKeyReleased

    private void timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timkiemActionPerformed
        String maGiuong = tk_mg.getText().trim();
            String maPhong = tk_mp.getSelectedItem() != null ? tk_mp.getSelectedItem().toString() : "";

        if (maGiuong.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã giường!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if ("-- Nhập mã phòng --".equals(maPhong)) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một mã phòng hợp lệ!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = BTL.Connect.KetnoiDB();
            String sqlCheckMaGiuongVaPhong = "SELECT * FROM Giuong WHERE MaGiuong = ? AND MaPhong = ?";
            pst = con.prepareStatement(sqlCheckMaGiuongVaPhong);
            pst.setString(1, maGiuong);
            pst.setString(2, maPhong);
            rs = pst.executeQuery();
            if (rs.next()) {
                // Hiển thị thông tin giường và phòng
                ma_g.setText(rs.getString("MaGiuong"));
                ma_p.removeAllItems(); // Xóa các mục cũ trong JComboBox
                ma_p.addItem(rs.getString("MaPhong"));
                ma_p.setSelectedItem(rs.getString("MaPhong"));
                tt.setText(rs.getString("TrangThaiGiuong")); 
                load_Gb(); // Tải lại dữ liệu bảng

            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy giường với mã và phòng đã nhập!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
           JOptionPane.showMessageDialog(this, "Lỗi SQL: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
           e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
              if (rs != null) rs.close();
               if (pst != null) pst.close();
              if (con != null) con.close();
           } catch (SQLException e) {
               e.printStackTrace();
            }
            }
    }//GEN-LAST:event_timkiemActionPerformed
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
    private void xuatexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xuatexActionPerformed
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
            cell.setCellValue("Trạng Thái Giường");

            // Kết nối đến cơ sở dữ liệu
            con = BTL.Connect.KetnoiDB();
            if (con == null) {
                System.out.println("Kết nối đến cơ sở dữ liệu thất bại.");
                return;
            }

            String sql = "SELECT * FROM Giuong";

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
                cell.setCellValue(rs.getString("MaGiuong"));

                cell = row.createCell(1);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("MaPhong"));

                cell = row.createCell(2);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("TrangThaiGiuong"));

                i++;
            }

            // Hiệu chỉnh độ rộng của cột
            for (int col = 0; col < tongsocot; col++) {
                spreadsheet.autoSizeColumn(col);
            }

            // Xuất file Excel
            File f = new File("D:\\BTVN JAVA\\BTL\\src\\main\\java\\PhongVaGiuong\\DSGiuongBenh.xlsx");
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
    }//GEN-LAST:event_xuatexActionPerformed
    private void Themgb(String mg, String mp, String tt) {
        try {
            con = BTL.Connect.KetnoiDB();
            String sql = "INSERT INTO Giuong ( MaGiuong, MaPhong, TrangThaiGiuong) "
                       + "VALUES (N'" + mg + "', N'" + mp + "', N'" + tt + "')";

            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
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
                    Themgb(mg, mp, tt);
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
    private void nhapexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhapexActionPerformed
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
    }//GEN-LAST:event_nhapexActionPerformed

    private void tbGiuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGiuongMouseClicked
        
        int i = tbGiuong.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một mã phòng để xem thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tbGiuong.getModel();

        // Cập nhật các trường nhập liệu
        ma_p.setSelectedItem(getValueOrEmpty(model.getValueAt(i, 1)));
        ma_g.setText(getValueOrEmpty(model.getValueAt(i, 0)));
        tt.setText(getValueOrEmpty(model.getValueAt(i, 2)));
        ma_g.setEnabled(false); // Vô hiệu hóa JTextField nếu cần
        }

        private String getValueOrEmpty(Object value) {
            return value != null ? value.toString() : "";
    }//GEN-LAST:event_tbGiuongMouseClicked
    private String getVlaueOrEmptyString(Object value){
        return value != null ? value.toString() : "";
    }

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

    private void themmoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themmoiActionPerformed
        // TODO add your handling code here:
        themgiuong themg = new themgiuong();
        themg.setVisible(true);
    }//GEN-LAST:event_themmoiActionPerformed

    private void refActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refActionPerformed
        // TODO add your handling code here:
        load_Gb();
        ma_g.setEnabled(true);  // Đảm bảo có thể nhập Mã phòng
        ma_g.setText("");
        ma_p.setSelectedItem("-- Nhập mã phòng --");
        tt.setText("");
    }//GEN-LAST:event_refActionPerformed
    
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
            java.util.logging.Logger.getLogger(giuongbenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(giuongbenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(giuongbenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(giuongbenh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new giuongbenh().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField ma_g;
    private javax.swing.JComboBox<String> ma_p;
    private javax.swing.JButton nhapex;
    private javax.swing.JButton ref;
    private javax.swing.JTable tbGiuong;
    private javax.swing.JButton themmoi;
    private javax.swing.JButton thoát;
    private javax.swing.JButton timkiem;
    private javax.swing.JTextField tk_mg;
    private javax.swing.JComboBox<String> tk_mp;
    private javax.swing.JTextField tt;
    private javax.swing.JButton xuatex;
    // End of variables declaration//GEN-END:variables

    private void themg(String mg, String mp, String tt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
