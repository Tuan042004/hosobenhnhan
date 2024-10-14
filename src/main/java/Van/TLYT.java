/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Van;

import BTL.Connect;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
 * @author Dell V
 */
public class TLYT extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public TLYT() {
        initComponents();
        loadyt();
        load_disable();
    }
    private void Themtailieu(String mbn, String mtl, String ttl, String nd) {
    try {
        con = Connect.KetnoiDB();
        String sql = "INSERT INTO TaiLieuYTe (MaTaiLieu, MaBenhNhan, TenTaiLieu, NoiDungTaiLieu) "
                   + "VALUES ('" + Integer.parseInt(mtl) + "', '" + Integer.parseInt(mbn) + "', N'" + ttl + "', N'" + nd + "')";

        Statement st = con.createStatement();
        st.executeUpdate(sql);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    private void ReadExcel(String tenfilepath) {
        try {
        FileInputStream fis = new FileInputStream(tenfilepath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0); // Lấy sheet đầu tiên
        Iterator<Row> itr = sheet.iterator();
        int row_count = 0;

        while (itr.hasNext()) {
            Row row = itr.next();
            if (row_count > 0) { // Bỏ qua dòng tiêu đề
                String mbn = "";
                Cell cell1 = row.getCell(0);
                if (cell1 != null) {
                    if (cell1.getCellType() == CellType.STRING) {
                        mbn = cell1.getStringCellValue().trim();
                    } else if (cell1.getCellType() == CellType.NUMERIC) {
                        mbn = String.valueOf((int) cell1.getNumericCellValue());
                    }
                }

                // Kiểm tra xem mbn có phải là chuỗi rỗng không
                if (mbn.isEmpty()) {
                    row_count++;
                    continue; // Bỏ qua dòng này
                }

                String mtl = "";
                Cell cell2 = row.getCell(1);
                if (cell2 != null) {
                    if (cell2.getCellType() == CellType.STRING) {
                        mtl = cell2.getStringCellValue().trim();
                    } else if (cell2.getCellType() == CellType.NUMERIC) {
                        mtl = String.valueOf((int) cell2.getNumericCellValue());
                    }
                }

                // Kiểm tra xem mbn có phải là chuỗi rỗng không
                if (mtl.isEmpty()) {
                    row_count++;
                    continue; // Bỏ qua dòng này
                }

                String ttl = "";
                Cell cell4 = row.getCell(2);
                if (cell4 != null) {
                    if (cell4.getCellType() == CellType.STRING) {
                        ttl = cell4.getStringCellValue().trim();
                    } else if (cell4.getCellType() == CellType.NUMERIC) {
                        ttl = String.valueOf(cell4.getNumericCellValue()).trim();
                    }
                }
                
                String nd = "";
                Cell cell5 = row.getCell(3);
                if (cell5 != null) {
                    if (cell5.getCellType() == CellType.STRING) {
                        nd = cell5.getStringCellValue().trim();
                    } else if (cell5.getCellType() == CellType.NUMERIC) {
                        nd = String.valueOf(cell5.getNumericCellValue()).trim();
                    }
                }

                // Gọi phương thức thêm bệnh nhân
                Themtailieu(mbn, mtl, ttl,nd);
            }
            row_count++;
        }
        JOptionPane.showMessageDialog(this, "Thêm tài liệu y tế bằng file thành công");
        loadyt();
    } catch (Exception e) {
        e.printStackTrace();
    }
        

    }
    private boolean Checktrungmbn(String mbn) {
    boolean kq = false;
        try {
        Connection con = Connect.KetnoiDB();
            String sql = "Select * from TaiLieuYTe where MaBenhNhan = ?";
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
    Connection con;
    private void load_disable(){
        txtmatl.setEnabled(false);
        txtmbn.setEnabled(false);
        txtttl.setEnabled(false);
        txtndtl.setEnabled(false);
        btluu.setEnabled(false);
        btcapnhat.setEnabled(false);
    }
    private void load_enable(){
        txtmatl.setEnabled(true);
        txtmbn.setEnabled(true);
        txtttl.setEnabled(true);
        txtndtl.setEnabled(true);
        btluu.setEnabled(true);
        btcapnhat.setEnabled(true);
    }
    private void loadyt(){
        try {
            tbyt.removeAll();
            //B1: Kết nối đến DB
            con= Connect.KetnoiDB();
            //B2: Tạo đối tượng Statement để thực hiện câu lệnh truy cập
            String sql = "Select * From TaiLieuYTe";
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String[] tieude={"Mã Tài Liệu", "Mã Bệnh Nhân","Tên Tài Liệu","Nội Dung Tài Liệu"};
            DefaultTableModel tb=new DefaultTableModel(tieude,0)    { //dùng cho doubleclick          
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        // Tất cả các ô sẽ không thể chỉnh sửa
                        return false;
                    }
                    };
            
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("MaTaiLieu"));
                v.add(rs.getString("MaBenhNhan"));
                v.add(rs.getString("TenTaiLieu"));
                v.add(rs.getString("NoiDungTaiLieu"));
                tb.addRow(v);
            }
            tbyt.setModel(tb);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void xoatrang(){
            txtmatl.setText("");
            txtmbn.setText("");
            txtttl.setText("");
            txtndtl.setText("");
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
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtmatl = new javax.swing.JTextField();
        txtmbn = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtttl = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtndtl = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbyt = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tkmabn = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        btluu = new javax.swing.JButton();
        btcapnhat = new javax.swing.JButton();
        btxoa = new javax.swing.JButton();
        btthoat = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Quản Lý Tài Liệu Y Tế");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(376, 376, 376)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(352, 352, 352))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết"));

        jLabel4.setText("Mã tài liệu:");

        jLabel3.setText("Mã bệnh nhân: ");

        jLabel5.setText("Tên tài liệu: ");

        jLabel6.setText("Nội dung tài liệu: ");

        txtndtl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtndtlActionPerformed(evt);
            }
        });

        tbyt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã tài liệu", "Mã bệnh nhân", "Tên tài liệu", "Nội dung tài liệu"
            }
        ));
        tbyt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbytMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbyt);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtmatl, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtmbn, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(jLabel5)
                                .addGap(29, 29, 29)
                                .addComponent(txtttl, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtndtl))))
                .addGap(30, 30, 30))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtmatl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtmbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtttl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtndtl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin tìm kiếm"));

        jLabel2.setText("Mã bệnh nhân:");

        tkmabn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tkmabnMouseClicked(evt);
            }
        });
        tkmabn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkmabnActionPerformed(evt);
            }
        });
        tkmabn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tkmabnKeyReleased(evt);
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
                .addComponent(tkmabn, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tkmabn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jButton3.setText("Thêm mới");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

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

        btthoat.setText("Thoát");
        btthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btthoatActionPerformed(evt);
            }
        });

        jButton5.setText("Xuất Excel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setText("Nhập Excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(btluu)
                .addGap(18, 18, 18)
                .addComponent(btcapnhat)
                .addGap(18, 18, 18)
                .addComponent(btxoa)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btthoat)
                .addGap(30, 30, 30))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(btluu)
                    .addComponent(btcapnhat)
                    .addComponent(btxoa)
                    .addComponent(btthoat)
                    .addComponent(jButton5)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtndtlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtndtlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtndtlActionPerformed

    private void tkmabnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tkmabnMouseClicked
        // TODO add your handling code here:
        xoatrang();
    }//GEN-LAST:event_tkmabnMouseClicked

    private void tkmabnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkmabnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tkmabnActionPerformed

    private void tkmabnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkmabnKeyReleased
        // TODO add your handling code here:
        try {
            // Lấy mã bệnh nhân từ trường nhập liệu
            tbyt.removeAll();
            String mbn = tkmabn.getText().trim();  // Mã bệnh nhân
            // Kết nối đến cơ sở dữ liệu
            Connection con = Connect.KetnoiDB(); //dm chó tuấn
            Statement st = con.createStatement();

            // Xây dựng câu lệnh SQL cho tìm kiếm
            String sql = "SELECT * FROM TaiLieuYTe WHERE MaBenhNhan LIKE '%" + mbn + "%'";

            // Thực hiện truy vấn
            ResultSet rs = st.executeQuery(sql);
            String[] tieude = {"Mã Tài Liệu", "Mã Bệnh Nhân", "Tên Tài Liệu","Nội Dung Tài Liệu"};
            DefaultTableModel tb = new DefaultTableModel(tieude, 0);
            tb.setRowCount(0);

            // Duyệt qua kết quả và thêm vào bảng
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("MaTaiLieu"));          // Mã báo cáo
                v.add(rs.getString("MaBenhNhan"));         // Mã bệnh nhân
                v.add(rs.getString("TenTaiLieu"));        // tên tài liệu y tế
                v.add(rs.getString("NoiDungTaiLieu"));       // nội dung tài liệu y tế
                tb.addRow(v);
            }

            // Cập nhật bảng hiển thị
            tbyt.setModel(tb);
            con.close();  // Đóng kết nối
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Tìm kiếm không thành công");
        }
    }//GEN-LAST:event_tkmabnKeyReleased

    private void btluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btluuActionPerformed
        // B1: lấy dữ liệu các compents đưa vào biến
        String mtl = txtmatl.getText().trim();
        String mbn = txtmbn.getText().trim();
        String ttl = txtttl.getText().trim();
        String nd = txtndtl.getText().trim();
           //Kiểm tra trùng mã bệnh nhân
        if (Checktrungmbn(mbn)) {
        JOptionPane.showMessageDialog(this, "Mã bệnh nhân đã tồn tại, không thể thêm.");
        return;
    }
        // B1.1: Kiểm tra các trường bắt buộc phải nhập
        if (mtl.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã tài liệu không được để trống.");
            txtmatl.requestFocus();
            return;
        }

        if (mbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã bệnh nhân không được để trống.");
            txtmbn.requestFocus();
            return;
        }

        if (ttl == null) {
            JOptionPane.showMessageDialog(this, "Tên tài liệu không được để trống.");
            txtttl.requestFocus();
            return;
        }

        if (nd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nội dung tài liệu không được để trống.");
            txtndtl.requestFocus();
            return;
        }

        //B2: Kết nối Database
        try {
            Connection con = Connect.KetnoiDB();

            //B3: Tạo đối tượng Statement để thực hiện lệnh truy vấn
            String sql = "Insert INTO TaiLieuYTe values('"+ mtl +"','"+ mbn +"', N'"+ ttl +"', N'"+ nd +"')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            con.close();
            loadyt();
            JOptionPane.showMessageDialog(this, "Thêm mới thành công");
            xoatrang();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm dữ liệu: " + e.getMessage());
        }
    }//GEN-LAST:event_btluuActionPerformed

    private void btcapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcapnhatActionPerformed
        try {
            String ttl = txtttl.getText();  // tên tài liệu y tế
            String nd = txtndtl.getText();  // nội dung tài liệu y tế

            // Kiểm tra nếu các trường không được để trống
            if (ttl.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống tên tài liệu của bệnh nhân");
                return;
            }
            if (nd.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để nội dung tài liệu y tế của bệnh nhân");
                return;
            }// Lấy mã điều trị và mã bệnh nhân (giả sử bạn có trường txtmdt và txtmbn để nhập mã)
            String mtl = txtmatl.getText();  // Mã tài liệu
            String mbn = txtmbn.getText();  // Mã bệnh nhân

            // Kiểm tra mã điều trị không được trống
            if (mtl.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống mã tài liệu");
                return;
            }

            Connection con = Connect.KetnoiDB(); // Kết nối tới database

            // Câu lệnh SQL để cập nhật bản ghi trong bảng QuaTrinhDieuTri
           String sql = "UPDATE TaiLieuYTe SET "
            + "TenTaiLieu = ?, "
            + "NoiDungTaiLieu = ? "
            + "WHERE MaTaiLieu = ? AND MaBenhNhan = ?";


            // Sử dụng PreparedStatement để tránh SQL Injection
            PreparedStatement ps = con.prepareStatement(sql);
            //        ps.setDate(1, sqlDate);  // Gán ngày điều trị
            ps.setString(1, ttl);     // Gán tên tài liệu
            ps.setString(2, nd);     // gán nội dung tài tài liệu
            ps.setString(3, mtl);    // Gán mã tài liệu
            ps.setString(4, mbn);    // Gán mã bệnh nhân

            // Thực hiện câu lệnh cập nhật
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                loadyt();  // Tải lại dữ liệu sau khi cập nhật
                xoatrang();
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu để sửa");
            }

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sửa không thành công");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btcapnhatActionPerformed

    private void btxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoaActionPerformed
        try{
             String mtl = txtmatl.getText().trim();
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                con = Connect.KetnoiDB();
                String sql = "Delete From TaiLieuYTe Where MaTaiLieu='"+mtl+"'";
                Statement st = con.createStatement();
                st.executeUpdate(sql);
                con.close();
                JOptionPane.showMessageDialog(this, "Xoá thành công");
                loadyt();
                xoatrang();
            } else {
                JOptionPane.showMessageDialog(this, "Không xoá nữa thì thôi");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btxoaActionPerformed

    private void btthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthoatActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btthoatActionPerformed
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
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
//         TODO add your handling code here:
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
            cell.setCellValue("DANH SÁCH TÀI LIỆU Y TẾ");

            //Tạo dòng tiêu đều của bảng
            // create CellStyle Họ và tên", "Mã bệnh nhân","Ngày sinh","Giới tính","Địa chỉ","Số điện thoại
            CellStyle cellStyle_Head = DinhdangHeader(spreadsheet);
            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã tài liệu y tế");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã bệnh nhân");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Tên tài liệu y tế");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Nội dung tài liệu y tế");

            //Kết nối DB
            con = Connect.KetnoiDB();
            String sql = "Select * From TaiLieuYTe";
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
                cell.setCellValue(rs.getString("MaTaiLieu"));

                cell = row.createCell(2);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("MaBenhNhan"));

                cell = row.createCell(3);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("TenTaiLieu"));

                cell = row.createCell(4);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("NoiDungTaiLieu"));

                i++;
            }
            //Hiệu chỉnh độ rộng của cột
            for (int col = 0; col < tongsocot; col++) {
                spreadsheet.autoSizeColumn(col);
            }

            File f = new File("D:\\Java-Netbeans\\mavenproject1\\src\\main\\java\\folder\\TLYT.xlsx");
            FileOutputStream out = new FileOutputStream(f);
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(this, "Xuất excel thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tbytMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbytMouseClicked
        // TODO add your handling code here:
        int i=tbyt.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel)tbyt.getModel();
        txtmatl.setText(tb.getValueAt(i, 0).toString());
        txtmbn.setText(tb.getValueAt(i, 1).toString());
      
        txtttl.setText(tb.getValueAt(i, 2).toString());
        txtndtl.setText(tb.getValueAt(i, 3).toString());
        txtmbn.setEnabled(false);
        
    }//GEN-LAST:event_tbytMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        load_enable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         try {
            JFileChooser fc = new JFileChooser();
            int lc = fc.showOpenDialog(this);
            if (lc == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();

                String tenfile = file.getName();
                if (tenfile.endsWith(".xlsx")) {    //endsWith chọn file có phần kết thúc ...
                    ReadExcel(file.getPath());
                } else {
                    JOptionPane.showMessageDialog(this, "Phải chọn file excel");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TLYT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TLYT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TLYT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TLYT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TLYT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcapnhat;
    private javax.swing.JButton btluu;
    private javax.swing.JButton btthoat;
    private javax.swing.JButton btxoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbyt;
    private javax.swing.JTextField tkmabn;
    private javax.swing.JTextField txtmatl;
    private javax.swing.JTextField txtmbn;
    private javax.swing.JTextField txtndtl;
    private javax.swing.JTextField txtttl;
    // End of variables declaration//GEN-END:variables

    
}
