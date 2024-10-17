/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Van;

import BTL.Connect;
//import folder.Connect;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
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
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;/**
 *
 * @author Dell V
 */
public class HSNV extends javax.swing.JFrame {

    /**
     * Creates new form HSXV
     */
    public HSNV() {
        initComponents();
        loadyt();
        load_cbmg();
        load_cbmk();
        load_cbphong();
        load_disable();
        load_cbbn();
    }
    Map<String, String> mabn = new HashMap<>();
    private void load_cbbn() {
        try {
            Connection con = Connect.KetnoiDB();
            String sql = "Select * From BenhNhan";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //Đổ dữ liệu vào combobox
            while (rs.next()) {
                cbmbn.addItem(rs.getString("MaBenhNhan"));
                mabn.put(rs.getString("MaBenhNhan"), rs.getString("HoTenBenhNhan"));
            }
            con.close();;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     private void load_disable(){
        txtmhs.setEnabled(false);
        cbmbn.setEnabled(false);
        cbmp.setEnabled(false);
        txtcd.setEnabled(false);
        cbmg.setEnabled(false);
        cbmk.setEnabled(false);
        cbtk.setEnabled(false);
        btluu.setEnabled(false);
        btcapnhat.setEnabled(false);
    }
    private void load_enable(){
        txtmhs.setEnabled(true);
        cbmbn.setEnabled(true);
        cbmp.setEnabled(true);
        txtcd.setEnabled(true);
        cbmg.setEnabled(true);
        cbmk.setEnabled(true);
        cbtk.setEnabled(true);
        btluu.setEnabled(true);
        btcapnhat.setEnabled(true);
    }
    Map<String, String> phong = new HashMap<>();
    private void load_cbphong() {
        try {
            Connection con = Connect.KetnoiDB();
            String sql = "Select * From PhongBenh";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //Đổ dữ liệu vào combobox
            while (rs.next()) {
                cbmp.addItem(rs.getString("MaPhong"));
//                cbmg.addItem(rs.getString("MaGiuong"));
                phong.put(rs.getString("MaPhong"), rs.getString("TenKhoa"));
            }
            con.close();;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    Map<String, String> khoa = new HashMap<>();
    private void load_cbmk() {
        try {
            Connection con = Connect.KetnoiDB();
            String sql = "Select * From Khoa";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //Đổ dữ liệu vào combobox
            while (rs.next()) {
                cbmk.addItem(rs.getString("MaKhoa"));
                cbtk.addItem(rs.getString("TenKhoa"));
                khoa.put(rs.getString("MaKhoa"), rs.getString("TenKhoa"));
            }
            con.close();;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    Map<String, String> giuong = new HashMap<>();
    private void load_cbmg() {
        try {
            Connection con = Connect.KetnoiDB();
            String sql = "Select * From Giuong";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //Đổ dữ liệu vào combobox
            while (rs.next()) {
                cbmg.addItem(rs.getString("MaGiuong"));
//                cbtk.addItem(rs.getString("TenKhoa"));
                khoa.put(rs.getString("MaGiuong"), rs.getString("MaPhong"));
            }
            con.close();;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private boolean Checktrungmbn(String mbn) {
    boolean kq = false;
        try {
        Connection con = Connect.KetnoiDB();
            String sql = "Select * from HoSoNhapVien where MaBenhNhan = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Nếu có bản ghi với MaBenhNhan thì trả về true (nghĩa là đã trùng mã)
                kq = true;
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
            return kq;
    }
    private void loadyt(){
        try {
            tbhs.removeAll();
            //B1: Kết nối đến DB
            Connection con = Connect.KetnoiDB();
            //B2: Tạo đối tượng Statement để thực hiện câu lệnh truy cập
            String sql = "Select * From HoSoNhapVien";
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String[] tieude={"Mã Hồ Sơ", "Mã Bệnh Nhân","Ngày Nhập Viện","Chẩn Đoán","Mã Phòng","Mã Giường","Mã Khoa","Tên Khoa"};
            DefaultTableModel tb=new DefaultTableModel(tieude,0)    {           
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        // Tất cả các ô sẽ không thể chỉnh sửa
                        return false;
                    }
                    };
            
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("MaHoSoNhapVien"));
                v.add(rs.getString("MaBenhNhan"));
                v.add(rs.getString("NgayNhapVien"));
                v.add(rs.getString("ChanDoan"));
                v.add(rs.getString("MaPhong"));
                v.add(rs.getString("MaGiuong"));
                v.add(rs.getString("MaKhoa"));
                v.add(rs.getString("TenKhoa"));
                tb.addRow(v);
            }
            tbhs.setModel(tb);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void xoatrang(){
            txtmhs.setText(""); //xóa trường mã báo cáo bệnh nhân nhập viện
            cbmbn.setSelectedItem("chọn mã bệnh nhân"); //xóa trường mã bệnh nhân chọn mã bệnh nhân
            dcnnv.setDate(null); //xóa trường ngày xuất viện
            txtcd.setText(""); //xóa trường chẩn đoán bệnh của bệnh nhân
            cbmp.setSelectedItem("chọn mã phòng"); // xóa trường mã phòng 
            cbmg.setSelectedItem("chọn mã giường"); //xóa trường mã giường
            cbmk.setSelectedItem("chọn mã khoa"); //xóa trường mã khoa
            cbtk.setSelectedItem("chọn tên khoa"); //xóa trường tên khoa
    }
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtmhs = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dcnnv = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbhs = new javax.swing.JTable();
        cbmp = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbmg = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbmk = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbtk = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtcd = new javax.swing.JTextField();
        cbmbn = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        btluu = new javax.swing.JButton();
        btcapnhat = new javax.swing.JButton();
        btxoa = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btthoat = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tkmbn = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Hồ Sơ Nhập Viện");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết"));

        jLabel4.setText("Mã hồ sơ:");

        jLabel3.setText("Mã bệnh nhân: ");

        jLabel5.setText("Ngày nhập viện: ");

        jLabel6.setText("Mã Phòng:");

        tbhs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hồ Sơ", "Mã Bệnh Nhân", "Ngày Nhập Viện", "Chẩn Đoán", "Mã Phòng", "Mã Giường", "Mã Khoa", "Tên Khoa", "Tên Bệnh Nhân"
            }
        ));
        tbhs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbhsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbhsMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tbhs);

        jScrollPane2.setViewportView(jScrollPane1);

        cbmp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Phòng" }));
        cbmp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmpItemStateChanged(evt);
            }
        });

        jLabel7.setText("Mã Giường:");

        cbmg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Giường" }));
        cbmg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmgItemStateChanged(evt);
            }
        });

        jLabel8.setText("Mã Khoa:");

        cbmk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Mã Khoa" }));
        cbmk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmkItemStateChanged(evt);
            }
        });

        jLabel9.setText("Tên Khoa:");

        cbtk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Tên Khoa" }));
        cbtk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbtkItemStateChanged(evt);
            }
        });

        jLabel10.setText("chẩn đoán:");

        txtcd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcdActionPerformed(evt);
            }
        });

        cbmbn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Mã Bệnh Nhân" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbmp, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmhs, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcnnv, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbmbn, 0, 180, Short.MAX_VALUE)
                                    .addComponent(cbmg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbmk, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbtk, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(cbmbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cbmk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtmhs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbmg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(cbtk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(dcnnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btluu.setText("Lưu");
        btluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btluuActionPerformed(evt);
            }
        });

        btcapnhat.setText("Sửa");
        btcapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcapnhatActionPerformed(evt);
            }
        });

        btxoa.setText("Xóa");
        btxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxoaActionPerformed(evt);
            }
        });

        jButton5.setText("Xuất Excel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setText("Thêm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btthoat.setText("Thoát");
        btthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btthoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(btluu, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btcapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btthoat)
                .addGap(16, 16, 16))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btluu)
                    .addComponent(btcapnhat)
                    .addComponent(btxoa)
                    .addComponent(jButton5)
                    .addComponent(jButton1)
                    .addComponent(btthoat))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin tìm kiếm"));

        jLabel2.setText("Mã bệnh nhân:");

        tkmbn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tkmbnMouseClicked(evt);
            }
        });
        tkmbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkmbnActionPerformed(evt);
            }
        });
        tkmbn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tkmbnKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel2)
                .addGap(34, 34, 34)
                .addComponent(tkmbn, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tkmbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthoatActionPerformed
        dispose();
    }//GEN-LAST:event_btthoatActionPerformed

    private void tbhsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbhsMouseClicked
        cbmk.setEnabled(false);
        cbmp.setEnabled(true);
        cbmg.setEnabled(true);
        cbmk.setEnabled(true);
        cbtk.setEnabled(true);
        cbmbn.setEnabled(false);
        txtmhs.setEnabled(false);
          int i = tbhs.getSelectedRow();
          DefaultTableModel tb = (DefaultTableModel) tbhs.getModel();
          //gán giá trị cho textfield
          txtmhs.setText(tb.getValueAt(i, 0).toString());
          //gán giá trị cho combobox mã bệnh nhân
          cbmbn.setSelectedItem(tb.getValueAt(i, 1).toString());
          //gán giá trị cho datechooser ngày nhập viện
          String nnv = tb.getValueAt(i, 2).toString();
          java.util.Date ng;
          try {
              ng = new SimpleDateFormat("yyyy-MM-DD").parse(nnv);
              dcnnv.setDate(ng);
          } catch(Exception ex) {
              ex.printStackTrace();
          }
          //gán giá trị cho tf chẩn đoán
          txtcd.setText(tb.getValueAt(i, 3).toString());
          //gán giá trị cho cbb mã phòng
          cbmp.setSelectedItem(tb.getValueAt(i, 4).toString());
          cbmg.setSelectedItem(tb.getValueAt(i, 5).toString());
          cbmk.setSelectedItem(tb.getValueAt(i, 6).toString());
          cbtk.setSelectedItem(tb.getValueAt(i, 7).toString());
    }//GEN-LAST:event_tbhsMouseClicked

    private void tbhsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbhsMouseEntered
        
    }//GEN-LAST:event_tbhsMouseEntered

    private void btluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btluuActionPerformed
        // B1: lấy dữ liệu các compents đưa vào biến
//        cbmk.setEnabled(false);
        String hs = txtmhs.getText().trim();
        String bn = cbmbn.getSelectedItem().toString();
        String cd = txtcd.getText().trim();
        String p = cbmp.getSelectedItem().toString();
        String g = cbmg.getSelectedItem().toString();
        String mk = cbmk.getSelectedItem().toString();
        String tk = cbtk.getSelectedItem().toString();
        //Kiểm tra trùng mã bệnh nhân
//        if (Checktrungmbn(mbn)) {
//            JOptionPane.showMessageDialog(this, "Mã bệnh nhân đã tồn tại, không thể thêm.");
//            return;
//        }
        // B1.1: Kiểm tra các trường bắt buộc phải nhập
        if (hs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã hồ sơ không được để trống.");
            txtmhs.requestFocus();
            return;
        }

        SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
        Date nxv = null;
        try {
            nxv = new Date(dcnnv.getDate().getTime());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Phải nhập ngày nhập viện hợp lệ!");
            dcnnv.requestFocus();
            return;
        }
        if (nxv == null) {
            JOptionPane.showMessageDialog(this, "Ngày nhập viện không được để trống!");
            dcnnv.requestFocus();
            return;
        }

        if (cd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải đưa ra chẩn đoán cho bệnh nhân!");
            txtcd.requestFocus();
            return;
        }

        //B2: Kết nối Database
        try {
            Connection con = Connect.KetnoiDB();

            //B3: Tạo đối tượng Statement để thực hiện lệnh truy vấn
            String sql = "Insert INTO HoSoNhapVien values('"+ hs +"','"+ bn +"', '"+ nxv +"', N'"+ cd +"','"+ p +"','"+ g +"','"+ mk +"','"+ tk +"')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            con.close();
            loadyt();
            JOptionPane.showMessageDialog(this, "Thêm mới thành công!");
            xoatrang();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm dữ liệu: " + e.getMessage());
        }
    }//GEN-LAST:event_btluuActionPerformed

    private void btcapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcapnhatActionPerformed
        try {
            String hs = txtmhs.getText();  // Mã hồ sơ
            if (hs.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phải nhập mã hồ sơ !");
                return;
            }
            String bn = cbmbn.getSelectedItem().toString();  // mã bệnh nhân
            if (bn.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phải nhập mã bệnh nhân !");
                return;
            }

            Date nxv = new Date(dcnnv.getDate().getTime());  // Ngày nhập viện
            if (nxv == null) {
                JOptionPane.showMessageDialog(this, "Phải nhập ngày nhập viện!");
                return;
            }

            String cd = txtcd.getText();  // Kết quả chẩn đoán
            if (cd.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phải nhập chẩn đoán của bệnh nhân!");
                return;
            }
            String p = cbmp.getSelectedItem().toString();  // mã phòng
            if (p.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phải nhập mã phòng bệnh !");
                return;
            }
            String g = cbmg.getSelectedItem().toString();  // mã bệnh giường bệnh
            if (g.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phải nhập mã giường bệnh!");
                return;
            }
            String mk = cbmk.getSelectedItem().toString();  // mã khoa
            if (mk.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phải nhập mã khoa!");
                return;
            }
            String tk = cbtk.getSelectedItem().toString();  // tên khoa bệnh
            if (tk.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phải nhập tên khoa!");
                return;
            }
            // Kết nối tới database
            Connection con = Connect.KetnoiDB();

            // Định dạng ngày theo chuẩn yyyy-MM-dd
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            // Câu lệnh SQL cập nhật thông tin hồ sơ nhập viện
            String sql = "UPDATE HoSoNhapVien SET MaHoSoNhapVien='" + hs +
            "', NgayNhapVien='" + format.format(nxv) +
            "', ChanDoan=N'" + cd + "', MaPhong='"+p+"', MaGiuong='"+g+"', MaKhoa='"+mk+"', TenKhoa='"+tk+"' WHERE MaBenhNhan='" + bn + "'";

            // Tạo đối tượng Statement và thực hiện truy vấn
            Statement st = con.createStatement();
            st.executeUpdate(sql);

            // Đóng kết nối
            con.close();

            // Thông báo thành công
            JOptionPane.showMessageDialog(this, "Sửa thành công");

            // Tải lại dữ liệu (nếu có)
            loadyt();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Sửa không thành công");
        }
    }//GEN-LAST:event_btcapnhatActionPerformed

    private void btxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoaActionPerformed
//        try{
//            String bn = cbmbn.getSelectedItem().toString();
////            String bn =txtmbn.getText().trim();
//            int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//            if (choice == JOptionPane.YES_OPTION) {
//                Connection con = Connect.KetnoiDB();
//                String sql = "Delete From HoSoNhapVien Where MaBenhNhan='"+bn+"'";
//                Statement st = con.createStatement();
//                st.executeUpdate(sql);
//                con.close();
//                JOptionPane.showMessageDialog(this, "Xoá thành công");
//                loadyt();
//                xoatrang();
//            } else {
//                JOptionPane.showMessageDialog(this, "Không xoá nữa thì thôi");
//            }
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
         
try {
    String bn = cbmbn.getSelectedItem().toString();
    int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (choice == JOptionPane.YES_OPTION) {
        Connection con = Connect.KetnoiDB();
        String sql = "DELETE FROM HoSoNhapVien WHERE MaBenhNhan = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, bn);
        int rowsDeleted = ps.executeUpdate();
        con.close();

        if (rowsDeleted > 0) {
            JOptionPane.showMessageDialog(this, "Xoá thành công");
            loadyt();  // Hàm này có thể là để tải lại danh sách?
            xoatrang();  // Hàm này để xóa thông tin hiện tại?
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy bệnh nhân để xoá");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Không xoá nữa thì thôi");
    }
} catch (Exception ex) {
    ex.printStackTrace();
}

    }//GEN-LAST:event_btxoaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //         TODO add your handling code here:
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Hồ Sơ Nhập Viện");
            // register the columns you wish to track and compute the column width

            CreationHelper createHelper = workbook.getCreationHelper();

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH HỒ SƠ BỆNH NHÂN NHẬP VIỆN");

            //Tạo dòng tiêu đều của bảng
            // create CellStyle "Mã hồ sơ", "Mã bệnh nhân","Ngày nhập viện","chẩn đoán","mã phòng","mã giường","mã khoa","tên khoa"
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
            cell.setCellValue("Mã bệnh nhân");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Ngày nhập viện");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Chẩn đoán");
            
            cell = row.createCell(5, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã phòng");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã giường");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã khoa");

            cell = row.createCell(9, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Tên khoa");

            //Kết nối DB
            Connection con = Connect.KetnoiDB();
            String sql = "Select * From HoSoNhapVien";
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
                cell.setCellValue(rs.getString("MaHoSoNhapVien"));

                cell = row.createCell(2);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("MaBenhNhan"));

                cell = row.createCell(3);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("NgayNhapVien"));

                cell = row.createCell(4);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("ChanDoan"));
                
                cell = row.createCell(5);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("MaPhong"));

                cell = row.createCell(6);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("MaGiuong"));

                cell = row.createCell(7);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("MaKhoa"));

                cell = row.createCell(8);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("TenKhoa"));

                i++;
            }
            //Hiệu chỉnh độ rộng của cột
            for (int col = 0; col < tongsocot; col++) {
                spreadsheet.autoSizeColumn(col);
            }

            File f = new File("D:\\Java-Netbeans\\mavenproject1\\src\\main\\java\\Van\\HSNV.xlsx");
            FileOutputStream out = new FileOutputStream(f);
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(this, "Xuất excel thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tkmbnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tkmbnMouseClicked
        // TODO add your handling code here:
        xoatrang();
    }//GEN-LAST:event_tkmbnMouseClicked

    private void tkmbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkmbnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tkmbnActionPerformed

    private void tkmbnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkmbnKeyReleased
        // TODO add your handling code here:
        try {
            // Lấy mã bệnh nhân từ trường nhập liệu
            tbhs.removeAll();
            String mbn = tkmbn.getText().trim();  // Mã bệnh nhân nhập vào để tìm kiếm
            // Kết nối đến cơ sở dữ liệu
            Connection con = Connect.KetnoiDB();
            Statement st = con.createStatement();

            // Xây dựng câu lệnh SQL cho tìm kiếm
            String sql = "SELECT * FROM HoSoNhapVien WHERE MaBenhNhan LIKE '%" + mbn + "%'";

            // Thực hiện truy vấn
            ResultSet rs = st.executeQuery(sql);
            String[] tieude = {"Mã Hồ Sơ", "Mã Bệnh Nhân", "Ngày Nhập Viện","Chẩn Đoán","Mã Phòng","Mã Giường","Mã Khoa","Tên Khoa"};
            DefaultTableModel tb = new DefaultTableModel(tieude, 0);
            tb.setRowCount(0);

            // Duyệt qua kết quả và thêm vào bảng
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("MaHoSoNhapVien")); // Mã hồ sơ nhập viện
                v.add(rs.getString("MaBenhNhan"));    // Mã bệnh nhân
                v.add(rs.getString("NgayNhapVien"));   // ngày nhập viện
                v.add(rs.getString("ChanDoan"));       // Chẩn đoán bệnh
                v.add(rs.getString("MaPhong")); //mã phòng bệnh
                v.add(rs.getString("MaGiuong")); //mã giường bệnh
                v.add(rs.getString("MaKhoa")); //mã khoa bệnh
                v.add(rs.getString("TenKhoa")); // tên khoa bệnh
                tb.addRow(v);
            }

            // Cập nhật bảng hiển thị
            tbhs.setModel(tb);
            con.close();  // Đóng kết nối
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Tìm kiếm không thành công !");
        }
    }//GEN-LAST:event_tkmbnKeyReleased

    private void txtcdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcdActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        load_enable();
        xoatrang();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbmgItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmgItemStateChanged
        // TODO add your handling code here:
        String g = cbmg.getSelectedItem().toString();
    }//GEN-LAST:event_cbmgItemStateChanged

    private void cbmkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmkItemStateChanged
        // TODO add your handling code here:
        String mk = cbmk.getSelectedItem().toString();
    }//GEN-LAST:event_cbmkItemStateChanged

    private void cbtkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbtkItemStateChanged
        // TODO add your handling code here:
        String tk = cbtk.getSelectedItem().toString();
    }//GEN-LAST:event_cbtkItemStateChanged

    private void cbmpItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmpItemStateChanged
        // TODO add your handling code here:
        String mp = cbmp.getSelectedItem().toString();
    }//GEN-LAST:event_cbmpItemStateChanged

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
            java.util.logging.Logger.getLogger(HSNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HSNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HSNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HSNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HSNV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcapnhat;
    private javax.swing.JButton btluu;
    private javax.swing.JButton btthoat;
    private javax.swing.JButton btxoa;
    private javax.swing.JComboBox<String> cbmbn;
    private javax.swing.JComboBox<String> cbmg;
    private javax.swing.JComboBox<String> cbmk;
    private javax.swing.JComboBox<String> cbmp;
    private javax.swing.JComboBox<String> cbtk;
    private com.toedter.calendar.JDateChooser dcnnv;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbhs;
    private javax.swing.JTextField tkmbn;
    private javax.swing.JTextField txtcd;
    private javax.swing.JTextField txtmhs;
    // End of variables declaration//GEN-END:variables
}
