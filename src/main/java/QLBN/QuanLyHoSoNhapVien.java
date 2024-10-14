/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package QLBN;
import BTL.Connect;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author dqduc
 */
public class QuanLyHoSoNhapVien extends javax.swing.JFrame {
    Map<String,String> benhnhan = new HashMap<>();
    private void load_cbombn(){
        try{
            con = Connect.KetnoiDB();
            String sql = "Select * From BenhNhan";
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //Đổ dữ liệu vào combobox
            while(rs.next()){
                cbombn.addItem(rs.getString("MaBenhNhan"));
                benhnhan.put(rs.getString("MaBenhNhan"), rs.getString("HoTen"));
            }
            con.close();;
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Creates new form QuanLyHoSoNhapVien
     */
    public QuanLyHoSoNhapVien() {
        initComponents();
        load_qtdt();
        load_cbombn();
    }
    Connection con;
    private void load_qtdt(){
        try {
            tbqlbn.removeAll();
            //B1: Kết nối đến DB
            con= BTL.Connect.KetnoiDB();
            //B2: Tạo đối tượng Statement để thực hiện câu lệnh truy cập
            String sql = "Select * From HoSoNhapVien";
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String[] tieude={"Mã hồ sơ nhập viện", "Mã bệnh nhân","Ngày nhập viện","Chuẩn đoán","Khoa điều trị"};
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
                v.add(rs.getString("KhoaDieuTri"));
                tb.addRow(v);
            }
            tbqlbn.setModel(tb);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void xoatrang() {
        txtmhs.setText("");      // Xóa trường họ tên
        cbombn.setSelectedItem("Chọn Mã Bệnh Nhân");        // Xóa trường mã bệnh nhân
        dcngaynhapvien.setDate(null);  // Xóa trường ngày sinh
        txtchuandoan.setText("");     // Xóa trường địa chỉ
        txtkhoadieutri.setText("");        // Xóa trường số điện thoại
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btthem = new javax.swing.JButton();
        btsua = new javax.swing.JButton();
        btxoa = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btn_xuatbc1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtTimkiem = new javax.swing.JTextField();
        bttimkiem = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtmhs = new javax.swing.JTextField();
        dcngaynhapvien = new com.toedter.calendar.JDateChooser();
        txtchuandoan = new javax.swing.JTextField();
        txtkhoadieutri = new javax.swing.JTextField();
        cbombn = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbqlbn = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btthem.setText("Lưu");
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

        jButton4.setText("Thêm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Thoát");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setText("Xuất excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_xuatbc1.setText("Xuất báo cáo");
        btn_xuatbc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuatbc1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(15, 15, 15)
                .addComponent(btthem)
                .addGap(18, 18, 18)
                .addComponent(btsua)
                .addGap(18, 18, 18)
                .addComponent(btxoa)
                .addGap(68, 68, 68)
                .addComponent(jButton1)
                .addGap(34, 34, 34)
                .addComponent(btn_xuatbc1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btthem)
                    .addComponent(btsua)
                    .addComponent(btxoa)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton1)
                    .addComponent(btn_xuatbc1))
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Hồ Sơ Bệnh Nhân");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin tìm kiếm"));

        txtTimkiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimkiemMouseClicked(evt);
            }
        });
        txtTimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimkiemKeyReleased(evt);
            }
        });

        bttimkiem.setText("Tìm kiếm");
        bttimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttimkiemActionPerformed(evt);
            }
        });

        jLabel8.setText("Mã bệnh nhân");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bttimkiem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttimkiem)
                    .addComponent(jLabel8))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết"));

        jLabel2.setText("Mã bệnh nhân");

        jLabel3.setText("Mã hồ sơ nhập viện");

        jLabel4.setText("Ngày nhập viện");

        jLabel6.setText("Chuẩn đoán");

        jLabel7.setText("Khoa điều trị");

        dcngaynhapvien.setDateFormatString("yyyy-MM-dd");

        cbombn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Mã Bệnh Nhân" }));
        cbombn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbombnItemStateChanged(evt);
            }
        });
        cbombn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbombnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dcngaynhapvien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(txtmhs, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbombn, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtkhoadieutri))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtchuandoan, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtchuandoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtkhoadieutri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtmhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbombn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(dcngaynhapvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        tbqlbn.setForeground(new java.awt.Color(0, 153, 204));
        tbqlbn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hồ sơ nhập viện", "Mã bệnh nhân", "Ngày nhập viện", "Chuẩn đoán", "Khoa điều trị"
            }
        ));
        tbqlbn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbqlbnMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbqlbn);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthemActionPerformed
        // B1: lấy dữ liệu các components đưa vào biến
        String mhs = txtmhs.getText().trim();
        String mbn = cbombn.getSelectedItem().toString();
        String cd = txtchuandoan.getText().trim();
        String dt = txtkhoadieutri.getText().trim();

        // B1.1: Kiểm tra các trường bắt buộc phải nhập
        if (mhs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã hồ sơ nhập viện không được để trống.");
            txtmhs.requestFocus();
            return;
        }
        if(mbn.equals("Chọn Mã Bệnh Nhân")){
                mbn="";
            }
        if (mbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã bệnh nhân không được để trống.");
            cbombn.requestFocus();
            return;
        }

        SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
        Date ngaynhapvien = null;
        try {
            ngaynhapvien = new Date(dcngaynhapvien.getDate().getTime());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Phải nhập ngày nhập viện hợp lệ!");
            dcngaynhapvien.requestFocus();
            return;
        }
        if (ngaynhapvien == null) {
            JOptionPane.showMessageDialog(this, "Ngày nhập viện không được để trống.");
            dcngaynhapvien.requestFocus();
            return;
        }

        if (cd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chuẩn đoán không được để trống.");
            txtchuandoan.requestFocus();
            return;
        }

        if (dt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điều trị không được để trống.");
            txtkhoadieutri.requestFocus();
            return;
        }

        // B2: Kết nối Database
        try {
            con = BTL.Connect.KetnoiDB();

            // B3: Tạo đối tượng Statement để thực hiện lệnh truy vấn
            String sql = "INSERT INTO HoSoNhapVien (MaHoSoNhapVien, MaBenhNhan, NgayNhapVien, ChanDoan, KhoaDieuTri) VALUES (N'"+ mhs +"', '"+ mbn +"', '"+ fomat.format(ngaynhapvien) +"', N'"+ cd +"', N'"+ dt +"')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            con.close();

            load_qtdt(); // Tải lại dữ liệu (nếu có)
            JOptionPane.showMessageDialog(this, "Thêm mới thành công");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm dữ liệu: " + e.getMessage());
        }
    }//GEN-LAST:event_btthemActionPerformed

    private void btsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsuaActionPerformed
        try {
       String mhs = txtmhs.getText();  // Mã hồ sơ nhập viện
       if (mhs.isEmpty()) {
           JOptionPane.showMessageDialog(this, "Phải nhập mã hồ sơ nhập viện!");
           return;
       }

       String mbn = cbombn.getSelectedItem().toString();  // Mã bệnh nhân
       if (mbn.isEmpty()) {
           JOptionPane.showMessageDialog(this, "Phải nhập mã bệnh nhân!");
           return;
       }

       Date ngaynhapvien = new Date(dcngaynhapvien.getDate().getTime());  // Ngày nhập viện
       if (ngaynhapvien == null) {
           JOptionPane.showMessageDialog(this, "Phải nhập ngày nhập viện!");
           return;
       }

       String cd = txtchuandoan.getText();  // Chẩn đoán
       if (cd.isEmpty()) {
           JOptionPane.showMessageDialog(this, "Phải nhập chẩn đoán bệnh!");
           return;
       }

       String dt = txtkhoadieutri.getText();  // Khoa điều trị
       if (dt.isEmpty()) {
           JOptionPane.showMessageDialog(this, "Phải nhập khoa điều trị!");
           return;
       }

       // Kết nối tới database
       con = BTL.Connect.KetnoiDB();

       // Định dạng ngày theo chuẩn yyyy-MM-dd
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

       // Câu lệnh SQL cập nhật thông tin hồ sơ nhập viện
       String sql = "UPDATE HoSoNhapVien SET MaHoSoNhapVien=N'" + mhs + 
                    "', NgayNhapVien='" + format.format(ngaynhapvien) + 
                    "', ChanDoan=N'" + cd + "', KhoaDieuTri=N'" + dt + 
                    "' WHERE MaBenhNhan='" + mbn + "'";

       // Tạo đối tượng Statement và thực hiện truy vấn
       Statement st = con.createStatement();
       st.executeUpdate(sql);

       // Đóng kết nối
       con.close();

       // Thông báo thành công
       JOptionPane.showMessageDialog(this, "Sửa thành công");

       // Tải lại dữ liệu (nếu có)
       load_qtdt();

   } catch (Exception ex) {
       ex.printStackTrace();
       JOptionPane.showMessageDialog(this, "Sửa không thành công");
   }

    }//GEN-LAST:event_btsuaActionPerformed

    private void btxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoaActionPerformed
      try{
             String mhs = txtmhs.getText().trim();
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                con = BTL.Connect.KetnoiDB();
                String sql = "Delete From HoSoNhapVien Where MaHoSoNhapVien='"+mhs+"'";
                Statement st = con.createStatement();
                st.executeUpdate(sql);
                con.close();
                JOptionPane.showMessageDialog(this, "Xoá thành công");
                load_qtdt();
                xoatrang();
            } else {
                JOptionPane.showMessageDialog(this, "Không xoá nữa thì thôi");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btxoaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        xoatrang();
        cbombn.setEnabled(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
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
            cell.setCellValue("DANH SÁCH HỒ SƠ NHẬP VIỆN");

            //Tạo dòng tiêu đều của bảng
            // create CellStyle Họ và tên", "Mã bệnh nhân","Ngày sinh","Giới tính","Địa chỉ","Số điện thoại
            CellStyle cellStyle_Head = DinhdangHeader(spreadsheet);
            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã hồ sơ nhập viện");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã bệnh nhân");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Ngày nhập viện");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Chuẩn đoán");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Khoa điều trị");
            //Kết nối DB
            con = BTL.Connect.KetnoiDB();
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
                cell.setCellValue(rs.getString("KhoaDieuTri"));

                i++;
            }
            //Hiệu chỉnh độ rộng của cột
            for (int col = 0; col < tongsocot; col++) {
                spreadsheet.autoSizeColumn(col);
            }

            File f = new File("C:\\Users\\dqduc\\OneDrive\\Documents\\Java\\hosobenhnhan\\src\\main\\java\\QLBN\\DanhsachHoSoNhapVien.xlsx");
            FileOutputStream out = new FileOutputStream(f);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTimkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimkiemMouseClicked
        xoatrang();
    }//GEN-LAST:event_txtTimkiemMouseClicked

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased

    }//GEN-LAST:event_txtTimkiemKeyReleased

    private void bttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttimkiemActionPerformed
        try{
            //lấy dữ liệu từ compoment đưa vài biến
            String mbn = txtTimkiem.getText().trim();
            con = BTL.Connect.KetnoiDB();
            String sql = "Select * From HoSoNhapVien Where MaBenhNhan like'%"+mbn+"%'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String[] tieude={"Mã hồ sơ nhập viện", "Mã bệnh nhân","Ngày nhập viện","Chuẩn đoán","Khoa điều trị"};
            DefaultTableModel tb = new DefaultTableModel(tieude,0);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("MaHoSoNhapVien"));
                v.add(rs.getString("MaBenhNhan"));
                v.add(rs.getString("NgayNhapVien"));
                v.add(rs.getString("ChanDoan"));
                v.add(rs.getString("KhoaDieuTri"));
                tb.addRow(v);
            }
            tbqlbn.setModel(tb);
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_bttimkiemActionPerformed

    private void tbqlbnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbqlbnMouseClicked
        int i=tbqlbn.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel)tbqlbn.getModel();
        txtmhs.setText(tb.getValueAt(i, 0).toString());
        String mbn = cbombn.getSelectedItem().toString();
        String ngay=tb.getValueAt(i, 2).toString();
        txtchuandoan.setText(tb.getValueAt(i, 3).toString());
        txtkhoadieutri.setText(tb.getValueAt(i, 4).toString());
        cbombn.setEnabled(false);
        java.util.Date ngs;
        try {
            ngs = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            dcngaynhapvien.setDate(ngs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_tbqlbnMouseClicked

    private void cbombnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbombnItemStateChanged
        // TODO add your handling code here:
         String mbn = cbombn.getSelectedItem().toString();
    }//GEN-LAST:event_cbombnItemStateChanged

    private void btn_xuatbc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuatbc1ActionPerformed
        // TODO add your handling code here:
        try {
             //String mbn = cbombn.getSelectedItem().toString();
             String mhs = txtmhs.getText().trim();
 
            Connection con = BTL.Connect.KetnoiDB();

            JasperDesign jdesign=JRXmlLoader.load("C:\\Users\\dqduc\\OneDrive\\Documents\\Java\\hosobenhnhan\\src\\main\\java\\QLBN\\hosonhapvien.jrxml");

            String sql = "Select * From HoSoNhapVien Where MaHoSoNhapVien like N'%"+mhs+"%'";
            JRDesignQuery updateQuery=new JRDesignQuery();
            updateQuery.setText(sql);

            jdesign.setQuery(updateQuery);
            JasperReport jreport=JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint=JasperFillManager.fillReport(jreport, null,con);
            JasperViewer.viewReport(jprint);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_xuatbc1ActionPerformed

    private void cbombnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbombnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbombnActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyHoSoNhapVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoSoNhapVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoSoNhapVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoSoNhapVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyHoSoNhapVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_xuatbc1;
    private javax.swing.JButton btsua;
    private javax.swing.JButton btthem;
    private javax.swing.JButton bttimkiem;
    private javax.swing.JButton btxoa;
    private javax.swing.JComboBox<String> cbombn;
    private com.toedter.calendar.JDateChooser dcngaynhapvien;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbqlbn;
    private javax.swing.JTextField txtTimkiem;
    private javax.swing.JTextField txtchuandoan;
    private javax.swing.JTextField txtkhoadieutri;
    private javax.swing.JTextField txtmhs;
    // End of variables declaration//GEN-END:variables
}
