/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Hosoxuatvien;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class HoSoXuatVien extends javax.swing.JInternalFrame {

    /**
     * Creates new form HoSoXuatVien
     */
    public HoSoXuatVien() throws ClassNotFoundException {
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        initComponents();
        initComponents();
        load_hsxv();
        loadcbo();
    }
    
    Connection con;  
       
    public void xoatrang() {
        txtmhs.setText("");
        cbmbn.setSelectedItem("---Chọn mã bệnh nhân---"); // Xóa lựa chọn trong JComboBox
        cbmk.setSelectedItem("---Chọn mã khoa---");
        cbkhoa.setSelectedItem("---Khoa---");
        cbbs.setSelectedItem("---Bác sĩ điều trị---");
        dcngaynv.setDate(null);  
        dcngayxv.setDate(null); 
        txtld.setText("");
        
        // Mở khóa lại các trường txtmhs và cbmbn
    txtmhs.setEnabled(true);  // Mở lại trường mã hồ sơ để người dùng nhập
    cbmbn.setEnabled(true);  // Mở lại JComboBox mã bệnh nhân để người dùng chọn
    txttenbn.setEnabled(true);
    cbkhoa.setEnabled(true);
    }
    
private boolean Checktrungmbn(String mbn) {
    boolean kq = false;
    try {
        con = BTL.Connect.KetnoiDB();
        // Thay HoSoXuatVien bằng HoSoNhapVien nếu bạn muốn kiểm tra mã bệnh nhân khi nhập viện
        String sql = "SELECT * FROM HoSoXuatVien WHERE MaBenhNhan = '" + mbn + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) { // Nếu tìm thấy mã bệnh nhân trong cơ sở dữ liệu
            kq = true; // Mã bệnh nhân đã tồn tại
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return kq;
}
    
        private void loadcbo() throws ClassNotFoundException {
    try {
        con = BTL.Connect.KetnoiDB();
        String query = "SELECT MaBenhNhan FROM BenhNhan"; // Thay đổi truy vấn để lấy mã bệnh nhân
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            cbmbn.addItem(rs.getString("MaBenhNhan")); // Thêm mã bệnh nhân vào ComboBox
        }
        
            // Lấy TenKhoa từ bảng Khoa
    String queryKhoa = "SELECT MaKhoa FROM Khoa";
    Statement statementKhoa = con.createStatement();
    ResultSet rsKhoa = statementKhoa.executeQuery(queryKhoa);
    while (rsKhoa.next()) {
        cbmk.addItem(rsKhoa.getString("MaKhoa"));
    }
        
    // Lấy TenKhoa từ bảng Khoa
    String queryTenKhoa = "SELECT TenKhoa FROM Khoa";
    Statement statementTenKhoa = con.createStatement();
    ResultSet rsTenKhoa = statementTenKhoa.executeQuery(queryTenKhoa);
    while (rsTenKhoa.next()) {
        cbkhoa.addItem(rsTenKhoa.getString("TenKhoa"));
    }
    
            // Lấy MaNhanVien từ bảng NhanVienYTe
    String queryNhanVien = "SELECT BacSiDieuTri FROM NhanVienYTe";
    Statement statementNhanVien = con.createStatement();
    ResultSet rsNhanVien = statementNhanVien.executeQuery(queryNhanVien);
    while (rsNhanVien.next()) {
        cbbs.addItem(rsNhanVien.getString("BacSiDieuTri"));
    }
    
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi tải danh mục: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void load_hsxv(){
        try {
    // Làm sạch bảng trước khi thêm dữ liệu mới
    tbxv.removeAll();

    // B1: Kết nối đến DB
    con = BTL.Connect.KetnoiDB();

    // B2: Tạo đối tượng Statement để thực hiện câu lệnh truy cập
    String sql = "SELECT * FROM HoSoXuatVien";
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);

    // Định nghĩa tiêu đề cho bảng
    String[] tieude = {"Mã hồ sơ", "Mã bệnh nhân"," Ngày nhập viện"," Ngày xuất viện","Mã khoa","Khoa", "Bác sĩ điều trị", "Lý do xuất viện"};
    // Tạo DefaultTableModel
    DefaultTableModel tb=new DefaultTableModel(tieude,0)    {           
        @Override
        public boolean isCellEditable(int row, int column) {
        // Tất cả các ô sẽ không thể chỉnh sửa
        return false;
        }
    };

    // Thêm dữ liệu vào bảng
    while (rs.next()) {
        Vector<String> v = new Vector<>();
        v.add(rs.getString("MaHoSoXuatVien"));        // Mã hồ sơ
        v.add(rs.getString("MaBenhNhan"));             // Mã bệnh nhân
        v.add(rs.getDate("NgayNhapVien").toString());   // Ngày điều trị
        v.add(rs.getDate("NgayXuat").toString()); // Ngày xuất viện        
        v.add(rs.getString("MaKhoa"));                  // Mã khoa
        v.add(rs.getString("TenKhoa"));                 // Tên khoa
        v.add(rs.getString("BacSiDieuTri"));            // Bác sĩ điều trị
        v.add(rs.getString("LyDoXuat"));                // Lý do xuất viện
        tb.addRow(v);
    }  

    // Đóng kết nối
    tbxv.setModel(tb);
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
    
    private String[] getBenhNhanInfo(String maBenhNhan) throws ClassNotFoundException, SQLException {
    String[] benhNhanInfo = new String[2]; // Mảng chứa tên và ngày nhập viện (String[0] = tên, String[1] = ngày nhập viện)
    
    // Kết nối đến cơ sở dữ liệu
    String query = "SELECT b.HoTenBenhNhan, h.NgayNhapVien FROM BenhNhan b JOIN HoSoNhapVien h ON b.MaBenhNhan = h.MaBenhNhan WHERE b.MaBenhNhan = ?";
    con = BTL.Connect.KetnoiDB(); 
    try (
         PreparedStatement pstmt = con.prepareStatement(query)) {
        pstmt.setString(1, maBenhNhan); // Gán mã bệnh nhân vào truy vấn
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            benhNhanInfo[0] = rs.getString("HoTenBenhNhan"); // Lấy tên bệnh nhân
            benhNhanInfo[1] = rs.getString("NgayNhapVien");  // Lấy ngày nhập viện
        }
    }

    return benhNhanInfo; // Trả về mảng thông tin bệnh nhân
    }

//    private String getTenKhoa(String maKhoa) throws ClassNotFoundException, SQLException {
//        String tenKhoa = "";
//
//        // Kết nối đến cơ sở dữ liệu
//        String query = "SELECT TenKhoa FROM Khoa WHERE MaKhoa = ?";
//        con = BTL.Connect.KetnoiDB();
//        try (
//             PreparedStatement pstmt = con.prepareStatement(query)) {
//            pstmt.setString(1, maKhoa); // Gán mã khoa vào truy vấn
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                tenKhoa = rs.getString("TenKhoa"); // Lấy tên khoa từ kết quả truy vấn
//            }
//        }
//
//        return tenKhoa; // Trả về tên khoa
//    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtmhs = new javax.swing.JTextField();
        cbmbn = new javax.swing.JComboBox<>();
        dcngaynv = new com.toedter.calendar.JDateChooser();
        dcngayxv = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbmk = new javax.swing.JComboBox<>();
        cbkhoa = new javax.swing.JComboBox<>();
        cbbs = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtld = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbxv = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txttenbn = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btthem = new javax.swing.JButton();
        btsua = new javax.swing.JButton();
        btxoa = new javax.swing.JButton();
        btxuatexcel = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1010, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hồ sơ xuất viện ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin tìm kiếm"));

        jLabel2.setText("Mã bệnh nhân");

        txttimkiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txttimkiemMouseClicked(evt);
            }
        });
        txttimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txttimkiem)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết"));
        jPanel3.setPreferredSize(new java.awt.Dimension(910, 743));

        jLabel3.setText("Mã hồ sơ");

        jLabel4.setText("Mã bệnh nhân");

        jLabel5.setText("Ngày nhập viện");

        jLabel6.setText("Ngày xuất viện");

        cbmbn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn mã bệnh nhân---" }));
        cbmbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmbnActionPerformed(evt);
            }
        });

        dcngaynv.setDateFormatString("yyyy-MM-dd");

        dcngayxv.setDateFormatString("yyyy-MM-dd");

        jLabel7.setText("Mã khoa");

        jLabel8.setText("Tên khoa");

        jLabel9.setText("Bác sĩ điều trị");

        cbmk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn mã khoa---" }));
        cbmk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmkActionPerformed(evt);
            }
        });

        cbkhoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Khoa---" }));

        cbbs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Bác sĩ điều trị---" }));

        jLabel11.setText("Lý do xuất viện");

        tbxv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hồ sơ", "Mã bệnh nhân", "Ngày nhập viện", "Ngày xuất viện", "Mã khoa", "Khoa", "Bác sĩ điều trị", "Lý do xuất viện"
            }
        ));
        tbxv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbxvMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbxv);

        jLabel10.setText("Họ và tên");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel10)))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbmbn, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtmhs, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txttenbn, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcngayxv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcngaynv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(126, 126, 126)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbkhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbmk, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbs, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtld, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtmhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(cbmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbmbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbkhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(txtld, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txttenbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(dcngaynv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dcngayxv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addContainerGap())
        );

        btthem.setText("Thêm");
        btthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btthemActionPerformed(evt);
            }
        });

        btsua.setText("Sửa ");
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

        btxuatexcel.setText("Xuất Excel");
        btxuatexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxuatexcelActionPerformed(evt);
            }
        });

        jButton6.setText("Thoát");

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
                .addComponent(btxuatexcel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(btthem)
                    .addComponent(btsua)
                    .addComponent(btxoa)
                    .addComponent(btxuatexcel))
                .addContainerGap(15, Short.MAX_VALUE))
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
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txttimkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimkiemMouseClicked
        xoatrang();
    }//GEN-LAST:event_txttimkiemMouseClicked

    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased
        try {
            // Lấy mã bệnh nhân từ trường nhập liệu
            tbxv.removeAll();
            String mbn = txttimkiem.getText().trim();  // Mã bệnh nhân

            // Kết nối đến cơ sở dữ liệu
            con = BTL.Connect.KetnoiDB(); //dm chó tuấn
            Statement st = con.createStatement();

            // Xây dựng câu lệnh SQL cho tìm kiếm
            String sql = "SELECT * FROM HoSoXuatVien WHERE MaBenhNhan LIKE '%" + mbn + "%'";

            // Thực hiện truy vấn
            ResultSet rs = st.executeQuery(sql);
            String[] tieude = {"Mã hồ sơ", "Mã bệnh nhân"," Ngày nhập viện"," Ngày xuất viện","Mã khoa","Khoa", "Bác sĩ điều trị", "Lý do xuất viện"};
            DefaultTableModel tb = new DefaultTableModel(tieude, 0);
            tb.setRowCount(0);

            // Thêm dữ liệu vào bảng
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("MaHoSoXuatVien"));        // Mã hồ sơ
                v.add(rs.getString("MaBenhNhan"));             // Mã bệnh nhân
                v.add(rs.getDate("NgayNhapVien").toString());   // Ngày điều trị
                v.add(rs.getDate("NgayXuat").toString()); // Ngày xuất viện
                v.add(rs.getString("MaKhoa"));                  // Mã khoa
                v.add(rs.getString("TenKhoa"));                 // Tên khoa
                v.add(rs.getString("BacSiDieuTri"));            // Bác sĩ điều trị
                v.add(rs.getString("LyDoXuat"));                // Lý do xuất viện
                tb.addRow(v);
            }

            // Cập nhật bảng hiển thị
            tbxv.setModel(tb);
            con.close();  // Đóng kết nối
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Tìm kiếm không thành công");
        }
    }//GEN-LAST:event_txttimkiemKeyReleased

    private void tbxvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbxvMouseClicked
        int i = tbxv.getSelectedRow();
        DefaultTableModel tb = (DefaultTableModel) tbxv.getModel();

        // Gán giá trị cho TextField
        txtmhs.setText(tb.getValueAt(i, 0).toString());

        // Gán giá trị cho ComboBox mã bệnh nhân
        cbmbn.setSelectedItem(tb.getValueAt(i, 1).toString());

        // Gán giá trị cho JDateChooser ngày điều trị
        String ngaynv = tb.getValueAt(i, 2).toString();
        java.util.Date ngs;
        try {
            ngs = new SimpleDateFormat("yyyy-MM-dd").parse(ngaynv);
            dcngaynv.setDate(ngs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String ngayxv = tb.getValueAt(i, 3).toString();
        try {
            ngs = new SimpleDateFormat("yyyy-MM-dd").parse(ngayxv);
            dcngayxv.setDate(ngs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        cbmk.setSelectedItem(tb.getValueAt(i, 4).toString());

        // Gán giá trị cho ComboBox tên khoa (nếu có tên khoa riêng)
        cbkhoa.setSelectedItem(tb.getValueAt(i, 5).toString());

        // Gán giá trị cho ComboBox bác sĩ điều trị
        cbbs.setSelectedItem(tb.getValueAt(i, 6).toString());

        txtld.setText(tb.getValueAt(i, 7).toString());

        // Vô hiệu hóa việc chỉnh sửa mã bệnh nhân
        cbmbn.setEnabled(false);
        txtmhs.setEnabled(false);
        txttenbn.setEnabled(false);
        cbkhoa.setEnabled(false);

    }//GEN-LAST:event_tbxvMouseClicked

    private void btthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthemActionPerformed
        // B1: Lấy dữ liệu từ các components và đưa vào biến
        String maHoSo = txtmhs.getText().trim();  // Mã hồ sơ xuất viện
        String mbn = cbmbn.getSelectedItem().toString();  // Mã bệnh nhân (ComboBox)
        String maKhoa = cbmk.getSelectedItem().toString();  // Mã khoa (ComboBox)
        String tenKhoa = cbkhoa.getSelectedItem().toString();  // Tên khoa (ComboBox)
        String bs = cbbs.getSelectedItem().toString();  // Bác sĩ điều trị (ComboBox)

        // Lấy ngày nhập viện và ngày xuất viện từ JDateChooser
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date ngayNhapVien = new Date(dcngaynv.getDate().getTime()); // JDateChooser cho ngày nhập viện
        Date ngayXuat = new Date(dcngayxv.getDate().getTime()); // JDateChooser cho ngày xuất viện

        String lyDoXuat = txtld.getText().trim();  // Lý do xuất viện
        
                //Kiểm tra trùng mã bệnh nhân
        if(Checktrungmbn(mbn)){
            JOptionPane.showMessageDialog(this, "Trùng mã bệnh ");
            return;
        }

        // B1.1: Kiểm tra các trường bắt buộc phải nhập
        if (maHoSo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã hồ sơ không được để trống.");
            txtmhs.requestFocus();
            return;
        }

        if (mbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã bệnh nhân không được để trống.");
            cbmbn.requestFocus();
            return;
        }

        if (maKhoa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã khoa không được để trống.");
            cbmk.requestFocus();
            return;
        }

        if (tenKhoa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên khoa không được để trống.");
            cbkhoa.requestFocus();
            return;
        }

        if (bs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bác sĩ điều trị không được để trống.");
            cbbs.requestFocus();
            return;
        }

        if (ngayNhapVien == null) {
            JOptionPane.showMessageDialog(this, "Ngày nhập viện không được để trống.");
            dcngaynv.requestFocus();
            return;
        }

        if (ngayXuat == null) {
            JOptionPane.showMessageDialog(this, "Ngày xuất viện không được để trống.");
            dcngayxv.requestFocus();
            return;
        }

        if (lyDoXuat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lý do xuất viện không được để trống.");
            txtld.requestFocus();
            return;
        }

        // B2: Kết nối Database
        try {
            con = BTL.Connect.KetnoiDB();

            // B3: Tạo đối tượng Statement để thực hiện lệnh truy vấn
            String sql = "INSERT INTO HoSoXuatVien (MaHoSoXuatVien, MaBenhNhan, NgayNhapVien, NgayXuat, MaKhoa, TenKhoa, BacSiDieuTri, LyDoXuat) " +
            "VALUES ('" + maHoSo + "', '" + mbn + "', '" + format.format(ngayNhapVien) + "', '" + format.format(ngayXuat) + "', '" + maKhoa + "', N'" + tenKhoa + "', N'" + bs + "', N'" + lyDoXuat + "')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);

            // Đóng kết nối
            con.close();
            load_hsxv(); // Cập nhật bảng hiển thị hồ sơ xuất viện
            JOptionPane.showMessageDialog(this, "Thêm mới thành công");
            xoatrang(); // Xóa trắng các trường nhập
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm dữ liệu: " + e.getMessage());
        }
    }//GEN-LAST:event_btthemActionPerformed

    private void btsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsuaActionPerformed
        try {
            // B1: Lấy dữ liệu từ các components và đưa vào biến
            String mhs = txtmhs.getText().trim();  // Mã điều hồ sơ
            String mbn = cbmbn.getSelectedItem().toString();  // Mã bệnh nhân (ComboBox)

            // Lấy ngày điều trị từ JDateChooser
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date nnv = new Date(dcngaynv.getDate().getTime());
            Date nxv = new Date(dcngayxv.getDate().getTime());

            String mk = cbmk.getSelectedItem().toString();  // Mã khoa (ComboBox)
            String khoa = cbkhoa.getSelectedItem().toString();  // Tên khoa (ComboBox)
            String bs = cbbs.getSelectedItem().toString();  // Bác sĩ điều trị (ComboBox)
            String ld = txtld.getText().trim();  // Lý do xuất viện

            // Kiểm tra nếu các trường không được để trống
            if (mhs.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống mã hồ sơ");
                return;
            }
            if (mbn.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống mã bệnh nhân");
                return;
            }
            if (mk.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống mã khoa");
                return;
            }
            if (khoa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống khoa");
                return;
            }
            if (bs.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống bác sĩ điều trị");
                return;
            }
            if (ld.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống lý do xuất viện");
                return;
            }
            if (nnv == null) {
                JOptionPane.showMessageDialog(this, "Không được để trống ngày nhập viện");
                return;
            }

            if (nxv == null) {
                JOptionPane.showMessageDialog(this, "Không được để trống ngày xuất viện");
                return;
            }

            // Định dạng ngày điều trị thành kiểu chuỗi
            java.sql.Date sqlDateNhapVien = new java.sql.Date(nnv.getTime());
            java.sql.Date sqlDateXuatVien = new java.sql.Date(nxv.getTime());

            // Kết nối tới database
            con = BTL.Connect.KetnoiDB();

            // Câu lệnh SQL để cập nhật bản ghi trong bảng QuaTrinhDieuTri
            String sql = "UPDATE HoSoXuatVien SET "
            + "NgayNhapVien = ?, "
            + "NgayXuat = ?, "
            + "MaKhoa = ?, "
            + "TenKhoa = ?, "
            + "BacSiDieuTri = ?, "
            + "LyDoXuat = ? "
            + "WHERE MaHoSoXuatVien = ? AND MaBenhNhan = ?";

            // Sử dụng PreparedStatement để tránh SQL Injection
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sqlDateNhapVien);  // Gán ngày nhập viện
            ps.setDate(2, sqlDateXuatVien);  // Gán ngày xuất viện
            ps.setString(3, mk);
            ps.setString(4, khoa);
            ps.setString(5, bs);
            ps.setString(6, ld);
            ps.setString(7, mhs);
            ps.setString(8, mbn);    // Gán mã bệnh nhân

            // Thực hiện câu lệnh cập nhật
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                load_hsxv();  // Tải lại dữ liệu sau khi cập nhật
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
        String mdt = txtmhs.getText();  // Lấy mã điều trị từ text field
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BTL.Connect.KetnoiDB();  // Kết nối đến cơ sở dữ liệu
            String sql = "DELETE FROM HoSoXuatVien WHERE MaHoSoXuatVien = ?";
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
            load_hsxv();
            xoatrang();

        } catch (SQLException e) {
            e.printStackTrace();  // In ra lỗi nếu có vấn đề với SQL
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }       catch (ClassNotFoundException ex) {
            Logger.getLogger(HSXV.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) ps.close();  // Đóng PreparedStatement
                if (con != null) con.close();  // Đóng kết nối cơ sở dữ liệu
            } catch (SQLException e) {
                e.printStackTrace();  // In ra lỗi nếu có vấn đề khi đóng kết nối
            }
        }
    }//GEN-LAST:event_btxoaActionPerformed

    private static CellStyle DinhdangHeader(XSSFSheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 12); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
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
    
    private void btxuatexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxuatexcelActionPerformed
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Khoa");
            // register the columns you wish to track and compute the column width

            CreationHelper createHelper = workbook.getCreationHelper();

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH XUẤT VIỆN");

            //Tạo dòng tiêu đều của bảng
            // create CellStyle
            CellStyle cellStyle_Head = DinhdangHeader(spreadsheet);
            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã hồ sơ");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã Bệnh Nhân");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Ngày nhập viện");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Ngày xuất viện");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã khoa");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Khoa");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Bác sĩ điều trị");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Lý do xuất viện");

            //Kết nối DB
            con = BTL.Connect.KetnoiDB();
            String sql = "Select * From HoSoXuatVien";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            //Đổ dữ liệu từ rs vào các ô trong excel
            ResultSetMetaData rsmd = rs.getMetaData();
            int tongsocot = rsmd.getColumnCount();

            //Đinh dạng Tạo đường kẻ cho ô chứa dữ liệu
            CellStyle cellStyle_data = spreadsheet.getWorkbook().createCellStyle();
            cellStyle_data.setBorderLeft(BorderStyle.THIN);
            cellStyle_data.setBorderRight(BorderStyle.THIN);
            cellStyle_data.setBorderBottom(BorderStyle.THIN);

            int i = 0;
            while (rs.next()) {
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);

                cell = row.createCell(0);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(i + 1);

                cell = row.createCell(1);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("MaHoSoXuatVien"));  // Mã hồ sơ

                cell = row.createCell(2);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("MaBenhNhan"));  // Mã bệnh nhân

                // Định dạng ngày nhập viện
                java.util.Date ngayNhapVien = new java.util.Date(rs.getDate("NgayNhapVien").getTime());
                CellStyle cellStyleNgay = workbook.createCellStyle();
                cellStyleNgay.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
                cellStyleNgay.setBorderLeft(BorderStyle.THIN);
                cellStyleNgay.setBorderRight(BorderStyle.THIN);
                cellStyleNgay.setBorderBottom(BorderStyle.THIN);
                cell = row.createCell(3);
                cell.setCellValue(ngayNhapVien);
                cell.setCellStyle(cellStyleNgay);

                // Định dạng ngày xuất viện
                java.util.Date ngayXuatVien = new java.util.Date(rs.getDate("NgayXuat").getTime());
                cell = row.createCell(4);
                cell.setCellValue(ngayXuatVien);
                cell.setCellStyle(cellStyleNgay);

                cell = row.createCell(5);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("MaKhoa"));  // Mã khoa

                cell = row.createCell(6);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("TenKhoa"));  // Khoa

                cell = row.createCell(7);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("BacSiDieuTri"));  // Bác sĩ điều trị

                cell = row.createCell(8);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("LyDoXuat"));  // Lý do xuất viện

                i++;
            }
            //Hiệu chỉnh độ rộng của cột
            for (int col = 0; col < tongsocot; col++) {
                spreadsheet.autoSizeColumn(col);
            }

            File f = new File("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Hosoxuatvien\\HSXV.xlsx");
            FileOutputStream out = new FileOutputStream(f);
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(this, "Xuất Excel thành công!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btxuatexcelActionPerformed

    private void cbmbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmbnActionPerformed
    // Lấy mã bệnh nhân được chọn từ ComboBox
    String selectedMaBenhNhan = (String) cbmbn.getSelectedItem();
    
    if (selectedMaBenhNhan != null && !selectedMaBenhNhan.isEmpty()) {
        try {
            // Gọi phương thức getBenhNhanInfo để lấy tên và ngày nhập viện
            String[] benhNhanInfo = getBenhNhanInfo(selectedMaBenhNhan);
            
            // Cập nhật tên bệnh nhân vào txttenbenhnhan
            txttenbn.setText(benhNhanInfo[0]);

            // Chuyển đổi ngày nhập viện từ chuỗi sang java.sql.Date và đặt vào JDateChooser (dcngaynv)
            if (benhNhanInfo[1] != null) {
                java.sql.Date ngayNhapVien = java.sql.Date.valueOf(benhNhanInfo[1]); 
                dcngaynv.setDate(new java.util.Date(ngayNhapVien.getTime())); // Chuyển thành java.util.Date và đặt vào dcngaynv
            }            
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(); // Xử lý ngoại lệ nếu có lỗi kết nối hoặc truy vấn
            }
        }   
    }//GEN-LAST:event_cbmbnActionPerformed

    private void cbmkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmkActionPerformed
    // Lấy mã khoa đã chọn
    String maKhoa = (String) cbmk.getSelectedItem();
    
    // Kiểm tra nếu mã khoa không null
    if (maKhoa != null) {
        try {
            // Tạo kết nối với cơ sở dữ liệu
            con = BTL.Connect.KetnoiDB();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HoSoXuatVien.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HoSoXuatVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        try  {
            // Lấy tên khoa tương ứng với mã khoa
            String sqlKhoa = "SELECT TenKhoa FROM Khoa WHERE MaKhoa = ?";
            try (PreparedStatement pstmtKhoa = con.prepareStatement(sqlKhoa)) {
                pstmtKhoa.setString(1, maKhoa);
                ResultSet rsKhoa = pstmtKhoa.executeQuery();
                if (rsKhoa.next()) {
                    String tenKhoa = rsKhoa.getString("TenKhoa");
                    cbkhoa.setSelectedItem(tenKhoa);
                }
            }

            // Lấy bác sĩ điều trị tương ứng với tên khoa
            String sqlNVYT = "SELECT BacSiDieuTri FROM NhanVienYTe WHERE TenKhoa = ?";
            try (PreparedStatement pstmtNVYT = con.prepareStatement(sqlNVYT)) {
                pstmtNVYT.setString(1, cbkhoa.getSelectedItem().toString());
                ResultSet rsNVYT = pstmtNVYT.executeQuery();
                
                // Xóa các mục cũ trong cbbs
                cbbs.removeAllItems();
                
                while (rsNVYT.next()) {
                    String bacSiDieuTri = rsNVYT.getString("BacSiDieuTri");
                    cbbs.addItem(bacSiDieuTri);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_cbmkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btsua;
    private javax.swing.JButton btthem;
    private javax.swing.JButton btxoa;
    private javax.swing.JButton btxuatexcel;
    private javax.swing.JComboBox<String> cbbs;
    private javax.swing.JComboBox<String> cbkhoa;
    private javax.swing.JComboBox<String> cbmbn;
    private javax.swing.JComboBox<String> cbmk;
    private com.toedter.calendar.JDateChooser dcngaynv;
    private com.toedter.calendar.JDateChooser dcngayxv;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTable tbxv;
    private javax.swing.JTextField txtld;
    private javax.swing.JTextField txtmhs;
    private javax.swing.JTextField txttenbn;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
