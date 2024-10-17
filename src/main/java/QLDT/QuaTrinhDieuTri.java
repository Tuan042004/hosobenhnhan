/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package QLDT;

import BTL.Connect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Admin
 */
public class QuaTrinhDieuTri extends javax.swing.JFrame {

    /**
     * Creates new form QuaTrinhDieuTri
     */
    public QuaTrinhDieuTri() throws ClassNotFoundException {
        initComponents();
        loadDanhMuc();
        load_qtdt();
    }
    
    Connection con;
    
    public void xoatrang() {
        txtmdt.setText("");
        cbmbn.setSelectedIndex(-1);  // Xóa lựa chọn trong JComboBox
        cbkhoa.setSelectedIndex(-1);
        cbbs.setSelectedIndex(-1);
        dcngay.setDate(null);  // Xóa ngày trong JDateChooser
        txtcd.setText("");
        txtpp.setText("");
        cbthuoc.setSelectedIndex(-1);  // Xóa lựa chọn trong JComboBox
    }

    private void loadDanhMuc() throws ClassNotFoundException {
    try {
        con = BTL.Connect.KetnoiDB();
        String query = "SELECT MaBenhNhan FROM BenhNhan"; // Thay đổi truy vấn để lấy mã bệnh nhân
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            cbmbn.addItem(rs.getString("MaBenhNhan")); // Thêm mã bệnh nhân vào ComboBox
        }
        
        // Lấy MaNhanVien từ bảng NhanVienYTe
    String queryNhanVien = "SELECT BacSiDieuTri FROM NhanVienYTe";
    Statement statementNhanVien = con.createStatement();
    ResultSet rsNhanVien = statementNhanVien.executeQuery(queryNhanVien);
    while (rsNhanVien.next()) {
        cbbs.addItem(rsNhanVien.getString("BacSiDieuTri"));
    }

    // Lấy MaThuoc từ bảng Thuoc
    String queryThuoc = "SELECT MaThuoc FROM Thuoc";
    Statement statementThuoc = con.createStatement();
    ResultSet rsThuoc = statementThuoc.executeQuery(queryThuoc);
    while (rsThuoc.next()) {
        cbthuoc.addItem(rsThuoc.getString("MaThuoc"));
    }

    // Lấy MaKhoa từ bảng Khoa
    String queryKhoa = "SELECT TenKhoa FROM Khoa";
    Statement statementKhoa = con.createStatement();
    ResultSet rsKhoa = statementKhoa.executeQuery(queryKhoa);
    while (rsKhoa.next()) {
        cbkhoa.addItem(rsKhoa.getString("TenKhoa"));
    }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi tải danh mục: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}

    
    private void load_qtdt(){
        try {
    // Làm sạch bảng trước khi thêm dữ liệu mới
    tbqtdt.removeAll();

    // B1: Kết nối đến DB
    con = BTL.Connect.KetnoiDB();

    // B2: Tạo đối tượng Statement để thực hiện câu lệnh truy cập
    String sql = "SELECT * FROM QuaTrinhDieuTri";
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);

    // Định nghĩa tiêu đề cho bảng
    String[] tieude = {"Mã điều trị", "Mã bệnh nhân", "Khoa", "Bác sĩ điều trị", "Ngày điều trị", "Chẩn đoán điều trị", "Phương pháp điều trị", "Thuốc"};

    // Tạo DefaultTableModel
    DefaultTableModel tb = new DefaultTableModel(tieude, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            // Chỉ cho phép cột "Mã bệnh nhân" chỉnh sửa
            return column == 1; // Cột mã bệnh nhân là cột thứ 1 (index 1)
        }
    };

    // Thêm dữ liệu vào bảng
    while (rs.next()) {
        Vector<String> v = new Vector<>();
        v.add(rs.getString("MaDieuTri"));              // Mã điều trị
        v.add(rs.getString("MaBenhNhan"));             // Mã bệnh nhân
        v.add(rs.getString("TenKhoa"));                 // Tên khoa
        v.add(rs.getString("BacSiDieuTri"));           // Bác sĩ điều trị
        v.add(rs.getDate("NgayDieuTri").toString());   // Ngày điều trị
        v.add(rs.getString("ChanDoanDieuTri"));        // Chẩn đoán điều trị
        v.add(rs.getString("PhuongPhapDieuTri"));      // Phương pháp điều trị
        v.add(rs.getString("MaThuoc"));                 // Thuốc
        tb.addRow(v);
    }  
    // Đóng kết nối
    tbqtdt.setModel(tb);
    con.close();

    } catch (SQLException e) {
        // Xử lý lỗi SQL
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi truy cập cơ sở dữ liệu: " + e.getMessage());
    } catch (Exception e) {
        // Xử lý lỗi khác
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
}

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtmdt = new javax.swing.JTextField();
        cbmbn = new javax.swing.JComboBox<>();
        cbkhoa = new javax.swing.JComboBox<>();
        cbbs = new javax.swing.JComboBox<>();
        dcngay = new com.toedter.calendar.JDateChooser();
        cbthuoc = new javax.swing.JComboBox<>();
        txtcd = new javax.swing.JTextField();
        txtpp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbqtdt = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txttk = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btthem = new javax.swing.JButton();
        btsua = new javax.swing.JButton();
        btxoa = new javax.swing.JButton();
        btnhapexcel = new javax.swing.JButton();
        btxuatexcel = new javax.swing.JButton();
        btthoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("QUÁ TRÌNH ĐIỀU TRỊ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết"));

        jLabel2.setText("Mã điều trị");

        jLabel3.setText("Mã bệnh nhân");

        jLabel4.setText("Bác sĩ điều trị");

        jLabel5.setText("Ngày điều trị ");

        jLabel6.setText("Phương pháp điều trị");

        jLabel7.setText("Tên khoa");

        jLabel8.setText("Chuẩn đoán ");

        jLabel9.setText("Thuốc");

        cbmbn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn mã bệnh nhân---" }));

        cbkhoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn khoa---" }));

        cbbs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn bác sĩ điều trị---" }));

        dcngay.setDateFormatString("yyyy-MM-dd");

        cbthuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn thuốc---" }));

        tbqtdt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã điều trị", "Mã bênh nhân", "Khoa", "Bác sĩ điều trị", "Ngày điều trị", "Chẩn đoán", "Phương pháp điều trị", "Thuốc"
            }
        ));
        tbqtdt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbqtdtMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbqtdt);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtmdt)
                    .addComponent(cbmbn, javax.swing.GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE)
                    .addComponent(cbkhoa, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbs, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(88, 88, 88)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(dcngay, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtpp, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(252, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5)
                        .addComponent(txtmdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(cbmbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbkhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel4)
                    .addComponent(cbbs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbthuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin tìm kiếm"));

        jLabel10.setText("Mã bệnh nhân");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttk)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txttk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btthem.setText("Thêm");
        btthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btthemActionPerformed(evt);
            }
        });

        btsua.setText("Sửa");
        btsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsuaActionPerformed(evt);
            }
        });

        btxoa.setText("Xóa");
        btxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxoaActionPerformed(evt);
            }
        });

        btnhapexcel.setText("Nhập Excel");

        btxuatexcel.setText("Xuất Excel");

        btthoat.setText("Thoát");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btthem)
                .addGap(18, 18, 18)
                .addComponent(btsua)
                .addGap(18, 18, 18)
                .addComponent(btxoa)
                .addGap(18, 18, 18)
                .addComponent(btnhapexcel)
                .addGap(18, 18, 18)
                .addComponent(btxuatexcel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btthoat)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btthem)
                    .addComponent(btsua)
                    .addComponent(btxoa)
                    .addComponent(btnhapexcel)
                    .addComponent(btxuatexcel)
                    .addComponent(btthoat))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbqtdtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbqtdtMouseClicked
            int i = tbqtdt.getSelectedRow();
    DefaultTableModel tb = (DefaultTableModel) tbqtdt.getModel();

    // Gán giá trị cho TextField
    txtmdt.setText(tb.getValueAt(i, 0).toString());

    // Gán giá trị cho ComboBox mã bệnh nhân
    cbmbn.setSelectedItem(tb.getValueAt(i, 1).toString());
    
        // Gán giá trị cho ComboBox tên khoa (nếu có tên khoa riêng)
    cbkhoa.setSelectedItem(tb.getValueAt(i, 2).toString());

    // Gán giá trị cho ComboBox bác sĩ điều trị
    cbbs.setSelectedItem(tb.getValueAt(i, 3).toString());

    // Gán giá trị cho JDateChooser ngày điều trị
    String ngay = tb.getValueAt(i, 4).toString();
    java.util.Date ngs;
    try {
        ngs = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
        dcngay.setDate(ngs);
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    // Gán giá trị cho các trường còn lại (TextField và ComboBox)
    txtcd.setText(tb.getValueAt(i, 5).toString());
    txtpp.setText(tb.getValueAt(i, 6).toString());

    // Gán giá trị cho ComboBox thuốc
    cbthuoc.setSelectedItem(tb.getValueAt(i, 7).toString());

    // Vô hiệu hóa việc chỉnh sửa mã bệnh nhân
    cbmbn.setEnabled(false);

    }//GEN-LAST:event_tbqtdtMouseClicked

    private void btthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthemActionPerformed
            // B1: Lấy dữ liệu từ các components và đưa vào biến
    String mdt = txtmdt.getText().trim();  // Mã điều trị
    String mbn = cbmbn.getSelectedItem().toString();  // Mã bệnh nhân (ComboBox)
    String khoa = cbkhoa.getSelectedItem().toString();  // Tên khoa (ComboBox)
    String bs = cbbs.getSelectedItem().toString();  // Bác sĩ điều trị (ComboBox)
    
    // Lấy ngày điều trị từ JDateChooser
    SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
    Date ndt = new Date(dcngay.getDate().getTime());
    
    String cd = txtcd.getText().trim();  // Chẩn đoán
    String pp = txtpp.getText().trim();  // Phương pháp điều trị
    String thuoc = cbthuoc.getSelectedItem().toString();  // Thuốc (ComboBox)

//    // Kiểm tra trùng mã bệnh nhân
//    if (Checktrungmbn(mbn)) {
//        JOptionPane.showMessageDialog(this, "Mã bệnh nhân đã tồn tại, không thể thêm.");
//        return;
//    }

    // B1.1: Kiểm tra các trường bắt buộc phải nhập
    if (mdt.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Mã điều trị không được để trống.");
        txtmdt.requestFocus();
        return;
    }

    if (mbn.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Mã bệnh nhân không được để trống.");
        cbmbn.requestFocus();
        return;
    }

    if (khoa.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Tên khoa không được để trống.");
        cbkhoa.requestFocus();
        return;
    }

    if (bs.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Bác sĩ điều trị không được để trống.");
        cbbs.requestFocus();
        return;
    }

    if (ndt == null) {
        JOptionPane.showMessageDialog(this, "Ngày điều trị không được để trống.");
        dcngay.requestFocus();
        return;
    }

    if (cd.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Chẩn đoán không được để trống.");
        txtcd.requestFocus();
        return;
    }

    if (pp.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Phương pháp điều trị không được để trống.");
        txtpp.requestFocus();
        return;
    }

    if (thuoc.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Thuốc không được để trống.");
        cbthuoc.requestFocus();
        return;
    }

    // B2: Kết nối Database
    try {
        con = BTL.Connect.KetnoiDB();

        // B3: Tạo đối tượng Statement để thực hiện lệnh truy vấn
        String sql = "INSERT INTO QuaTrinhDieuTri (MaDieuTri, MaBenhNhan, TenKhoa, BacSiDieuTri, NgayDieuTri, ChanDoanDieuTri, PhuongPhapDieuTri, MaThuoc) " +
                     "VALUES ('" + mdt + "', '" + mbn + "', N'" + khoa + "', N'" + bs + "', '" + fomat.format(ndt) + "', N'" + cd + "', N'" + pp + "', '" + thuoc + "')";
        Statement st = con.createStatement();
        st.executeUpdate(sql);

        // Đóng kết nối
            con.close();
            load_qtdt();
            JOptionPane.showMessageDialog(this, "Thêm mới thành công");    
            xoatrang();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi khi thêm dữ liệu: " + e.getMessage());
    }
    }//GEN-LAST:event_btthemActionPerformed

    private void btsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsuaActionPerformed
            try {
        // B1: Lấy dữ liệu từ các components và đưa vào biến
        String mdt = txtmdt.getText().trim();  // Mã điều trị
        String mbn = cbmbn.getSelectedItem().toString();  // Mã bệnh nhân (ComboBox)
        String khoa = cbkhoa.getSelectedItem().toString();  // Tên khoa (ComboBox)
        String bs = cbbs.getSelectedItem().toString();  // Bác sĩ điều trị (ComboBox)

        // Lấy ngày điều trị từ JDateChooser
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date ndt = new Date(dcngay.getDate().getTime());

        String cd = txtcd.getText().trim();  // Chẩn đoán
        String pp = txtpp.getText().trim();  // Phương pháp điều trị
        String thuoc = cbthuoc.getSelectedItem().toString();  // Thuốc (ComboBox)

        // Kiểm tra nếu các trường không được để trống
        if (mdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã điều trị");
            return;
        }
        if (mbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã bệnh nhân");
            return;
        }
        if (cd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống chẩn đoán điều trị");
            return;
        }
        if (pp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống phương pháp điều trị");
            return;
        }
        if (bs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống bác sĩ điều trị");
            return;
        }
        if (thuoc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống thuốc điều trị");
            return;
        }
        if (ndt == null) {
            JOptionPane.showMessageDialog(this, "Không được để trống ngày điều trị");
            return;
        }

        // Định dạng ngày điều trị thành kiểu chuỗi
        java.sql.Date sqlDate = new java.sql.Date(ndt.getTime());

        // Kết nối tới database
        con = Connect.KetnoiDB();

        // Câu lệnh SQL để cập nhật bản ghi trong bảng QuaTrinhDieuTri
        String sql = "UPDATE QuaTrinhDieuTri SET "
                   + "NgayDieuTri = ?, "
                   + "ChanDoanDieuTri = ?, "
                   + "PhuongPhapDieuTri = ?, "
                   + "BacSiDieuTri = ?, "
                   + "TenKhoa = ?, "
                   + "MaThuoc = ? "
                   + "WHERE MaDieuTri = ? AND MaBenhNhan = ?";

        // Sử dụng PreparedStatement để tránh SQL Injection
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, sqlDate);  // Gán ngày điều trị
        ps.setString(2, cd);     // Gán chẩn đoán điều trị
        ps.setString(3, pp);     // Gán phương pháp điều trị
        ps.setString(4, bs);     // Gán bác sĩ điều trị
        ps.setString(5, khoa);   // Gán tên khoa
        ps.setString(6, thuoc);  // Gán thuốc điều trị
        ps.setString(7, mdt);    // Gán mã điều trị
        ps.setString(8, mbn);    // Gán mã bệnh nhân

        // Thực hiện câu lệnh cập nhật
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            load_qtdt();  // Tải lại dữ liệu sau khi cập nhật
            xoatrang();   // Xóa các trường nhập liệu
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu để sửa");
        }

        con.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Sửa không thành công");
        e.printStackTrace();
    }

    }//GEN-LAST:event_btsuaActionPerformed

    private void btxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoaActionPerformed
        String mdt = txtmdt.getText();  // Lấy mã điều trị từ text field
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BTL.Connect.KetnoiDB();  // Kết nối đến cơ sở dữ liệu
            String sql = "DELETE FROM QuaTrinhDieuTri WHERE MaDieuTri = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, mdt);  // Đặt tham số MaDieuTri vào câu truy vấn

            int response = JOptionPane.showConfirmDialog(null, 
                "Bạn có muốn xóa?", 
                "Xác nhận", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                int rowsAffected = ps.executeUpdate();  // Thực hiện truy vấn
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không có dữ liệu để xóa");
                }
            }

            // Sau khi xóa thành công, nạp lại dữ liệu
            load_qtdt(); 
            xoatrang();

        } catch (SQLException e) {
            e.printStackTrace();  // In ra lỗi nếu có vấn đề với SQL
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }       catch (ClassNotFoundException ex) {
                    Logger.getLogger(QuaTrinhDieuTri.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
            try {
                if (ps != null) ps.close();  // Đóng PreparedStatement
                if (con != null) con.close();  // Đóng kết nối cơ sở dữ liệu
            } catch (SQLException e) {
                e.printStackTrace();  // In ra lỗi nếu có vấn đề khi đóng kết nối
            }
        }

    }//GEN-LAST:event_btxoaActionPerformed

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
            java.util.logging.Logger.getLogger(QuaTrinhDieuTri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuaTrinhDieuTri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuaTrinhDieuTri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuaTrinhDieuTri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new QuaTrinhDieuTri().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(QuaTrinhDieuTri.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnhapexcel;
    private javax.swing.JButton btsua;
    private javax.swing.JButton btthem;
    private javax.swing.JButton btthoat;
    private javax.swing.JButton btxoa;
    private javax.swing.JButton btxuatexcel;
    private javax.swing.JComboBox<String> cbbs;
    private javax.swing.JComboBox<String> cbkhoa;
    private javax.swing.JComboBox<String> cbmbn;
    private javax.swing.JComboBox<String> cbthuoc;
    private com.toedter.calendar.JDateChooser dcngay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbqtdt;
    private javax.swing.JTextField txtcd;
    private javax.swing.JTextField txtmdt;
    private javax.swing.JTextField txtpp;
    private javax.swing.JTextField txttk;
    // End of variables declaration//GEN-END:variables
}
