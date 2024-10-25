/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package QLDT;

import BTL.Menu1;
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
 * @author Admin
 */
public class Nhanvienyte extends javax.swing.JFrame {

    /**
     * Creates new form Nhanvienyte
     */
    public Nhanvienyte() {
        initComponents();
        load_nhanvien();
    }
    
    Connection con;
    
        private void Themnhanvien(String mnv, String hoten, String chucvu, String khoa, String sdt) {
    try {
        con = BTL.Connect.KetnoiDB();
        String sql = "INSERT INTO NhanVienYTe (MaNhanVien, HoTen, ChucVu, Khoa, SDT) "
                   + "VALUES (N'" + mnv + "', N'" + hoten+ "',  N'" + chucvu + "', N'" + khoa + "', '" + sdt + "')";

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
                String mnv = "";
                Cell cell1 = row.getCell(0);
                if (cell1 != null) {
                    if (cell1.getCellType() == CellType.STRING) {
                        mnv = cell1.getStringCellValue().trim();
                    } else if (cell1.getCellType() == CellType.NUMERIC) {
                        mnv = String.valueOf((int) cell1.getNumericCellValue());
                    }
                }

                // Kiểm tra xem mbn có phải là chuỗi rỗng không
                if (mnv.isEmpty()) {
                    row_count++;
                    continue; // Bỏ qua dòng này
                }

                String hoten = "";
                Cell cell2 = row.getCell(1);
                if (cell2 != null) {
                    if (cell2.getCellType() == CellType.STRING) {
                        hoten = cell2.getStringCellValue().trim();
                    } else if (cell2.getCellType() == CellType.NUMERIC) {
                        hoten = String.valueOf(cell2.getNumericCellValue()).trim();
                    }
                }

                String chucvu = "";
                Cell cell4 = row.getCell(2);
                if (cell4 != null) {
                    if (cell4.getCellType() == CellType.STRING) {
                        chucvu = cell4.getStringCellValue().trim();
                    } else if (cell4.getCellType() == CellType.NUMERIC) {
                        chucvu = String.valueOf(cell4.getNumericCellValue()).trim();
                    }
                }
                
                String khoa = "";
                Cell cell5 = row.getCell(3);
                if (cell5 != null) {
                    if (cell5.getCellType() == CellType.STRING) {
                        khoa = cell5.getStringCellValue().trim();
                    } else if (cell5.getCellType() == CellType.NUMERIC) {
                        khoa = String.valueOf(cell5.getNumericCellValue()).trim();
                    }
                }
                
                String sdt = "";
                Cell cell6 = row.getCell(4);
                if (cell6 != null) {
                    if (cell6.getCellType() == CellType.STRING) {
                        sdt = cell6.getStringCellValue().trim();
                    } else if (cell6.getCellType() == CellType.NUMERIC) {
                        sdt = String.valueOf(cell6.getNumericCellValue()).trim();
                    }
                }

                // Gọi phương thức thêm bệnh nhân
                Themnhanvien(mnv, hoten, chucvu, khoa, sdt);
            }
            row_count++;
        }
        JOptionPane.showMessageDialog(this, "Thêm nhân viên bằng file thành công");
        load_nhanvien();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    private void load_nhanvien(){
        try {
            tbnhanvien.removeAll();
            //B1: Kết nối đến DB
            con= BTL.Connect.KetnoiDB();
            //B2: Tạo đối tượng Statement để thực hiện câu lệnh truy cập
            String sql = "Select * From NhanVienYTe";
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String[] tieude={"Mã nhân viên", "Họ và tên","Chức Vụ","Khoa","SDT"};
            DefaultTableModel tb=new DefaultTableModel(tieude,0)    {           
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        // Tất cả các ô sẽ không thể chỉnh sửa
                        return false;
                    }
                    };
            
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("MaNhanVien"));
                v.add(rs.getString("HoTen"));
                v.add(rs.getString("ChucVu"));
                v.add(rs.getString("Khoa"));
                v.add(rs.getString("SDT"));
                tb.addRow(v);
            }
            tbnhanvien.setModel(tb);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txttimkiem = new javax.swing.JTextField();
        bttimkiem = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtmnv = new javax.swing.JTextField();
        txthoten = new javax.swing.JTextField();
        txtcv = new javax.swing.JTextField();
        txtkhoa = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbnhanvien = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btthem = new javax.swing.JButton();
        btsua = new javax.swing.JButton();
        btxoa = new javax.swing.JButton();
        btxuat = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btload = new javax.swing.JButton();
        btnhapexcel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nhân viên y tế");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin tìm kiếm"));

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

        bttimkiem.setText("Tìm kiếm");
        bttimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttimkiemActionPerformed(evt);
            }
        });

        jLabel8.setText("Mã nhân viên");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bttimkiem)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttimkiem)
                    .addComponent(jLabel8))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết"));

        jLabel2.setText("Họ và tên");

        jLabel3.setText("Mã nhân viên");

        jLabel5.setText("Chức vụ");

        jLabel6.setText("Khoa ");

        jLabel7.setText("SDT");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txthoten, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(txtmnv, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtsdt))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtcv, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtkhoa)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtmnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtcv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)
                    .addComponent(txtkhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        tbnhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Họ và tên", "Chức vụ", "Khoa", "SDT"
            }
        ));
        tbnhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbnhanvienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbnhanvien);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addContainerGap())
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

        btxuat.setText("Xuất Excel");
        btxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxuatActionPerformed(evt);
            }
        });

        jButton5.setText("Thoát");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btload.setText("Load");
        btload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btloadActionPerformed(evt);
            }
        });

        btnhapexcel.setText("Nhập Excel");
        btnhapexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapexcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btthem)
                .addGap(18, 18, 18)
                .addComponent(btsua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btxoa)
                .addGap(18, 18, 18)
                .addComponent(btxuat)
                .addGap(18, 18, 18)
                .addComponent(btnhapexcel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btload)
                .addGap(18, 18, 18)
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
                    .addComponent(btxuat)
                    .addComponent(jButton5)
                    .addComponent(btload)
                    .addComponent(btnhapexcel))
                .addGap(0, 0, Short.MAX_VALUE))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public void xoatrang(){
            txtmnv.setText("");
            txthoten.setText("");
            txtcv.setText("");
            txtkhoa.setText("");
            txtsdt.setText("");
    }    
    private void txttimkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimkiemMouseClicked
        xoatrang();
    }//GEN-LAST:event_txttimkiemMouseClicked

    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased
        try {
            // Lấy mã bệnh nhân từ trường nhập liệu
            tbnhanvien.removeAll();
            String mnv = txttimkiem.getText().trim();  // Mã bệnh nhân

            // Kết nối đến cơ sở dữ liệu
            con = BTL.Connect.KetnoiDB();
            Statement st = con.createStatement();

            // Xây dựng câu lệnh SQL cho tìm kiếm
            String sql = "SELECT * FROM NhanVienYTe WHERE MaNhanVien LIKE '%" + mnv + "%'";

            // Thực hiện truy vấn
            ResultSet rs = st.executeQuery(sql);
            String[] tieude = {"Mã nhân viên", "họ tên", "Chức vụ", "Khoa", "SDT"};
            DefaultTableModel tb = new DefaultTableModel(tieude, 0);
            tb.setRowCount(0);

            // Duyệt qua kết quả và thêm vào bảng
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("MaNhanVien"));          
                v.add(rs.getString("HoTen"));        
                v.add(rs.getString("ChucVu"));        
                v.add(rs.getString("Khoa"));    
                v.add(rs.getString("SDT"));  
                tb.addRow(v);
            }

            // Cập nhật bảng hiển thị
            tbnhanvien.setModel(tb);
            con.close();  // Đóng kết nối
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Tìm kiếm không thành công");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiemKeyReleased

    private void bttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttimkiemActionPerformed

    }//GEN-LAST:event_bttimkiemActionPerformed

    private void tbnhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnhanvienMouseClicked
        int i = tbnhanvien.getSelectedRow();  // Lấy chỉ số hàng được chọn trong bảng
        DefaultTableModel tb = (DefaultTableModel) tbnhanvien.getModel();  // Lấy model của bảng

        if (i >= 0) {  // Kiểm tra xem có hàng nào được chọn không
            txtmnv.setText(tb.getValueAt(i, 0).toString());  // Gán mã nhân viên
            txthoten.setText(tb.getValueAt(i, 1).toString());  // Gán họ tên
            txtcv.setText(tb.getValueAt(i, 2).toString());  // Gán chức vụ
            txtkhoa.setText(tb.getValueAt(i, 3).toString());  // Gán khoa
            txtsdt.setText(tb.getValueAt(i, 4).toString());  // Gán số điện thoại
            txtmnv.setEnabled(false);  // Vô hiệu hóa ô nhập mnv


            try {

            } catch (Exception ex) {
                ex.printStackTrace();  
                JOptionPane.showMessageDialog(this, "Lỗi khi xử lý dữ liệu: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng trong bảng.");  // Thông báo nếu không có hàng nào được chọn
        }

    }//GEN-LAST:event_tbnhanvienMouseClicked

    private void btthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthemActionPerformed
        // B1: lấy dữ liệu các compents đưa vào biến
        String mnv = txtmnv.getText().trim();
        String hoten = txthoten.getText().trim();
        String chucvu = txtcv.getText().trim();
        String khoa = txtkhoa.getText().trim();
        String sdt = txtsdt.getText().trim();

        // B1.1: Kiểm tra các trường bắt buộc phải nhập
        if (mnv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống.");
            txtmnv.requestFocus();
            return;
        }

        if (hoten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ và tên không được để trống.");
            txthoten.requestFocus();
            return;
        }


        if (chucvu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chức vụ không được để trống.");
            txtcv.requestFocus();
            return;
        }

        if (khoa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khoa không được để trống.");
            txtkhoa.requestFocus();
            return;
        }

        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống.");
            txtsdt.requestFocus();
            return;
        }

        //B2: Kết nối Database
        try {
            con = BTL.Connect.KetnoiDB();

            //B3: Tạo đối tượng Statement để thực hiện lệnh truy vấn
            String sql = "Insert INTO NhanVienYTe values('"+ mnv +"',N'"+ hoten +"', N'"+ chucvu +"', N'"+ khoa +"', '"+ sdt +"')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            con.close();
            load_nhanvien();
            JOptionPane.showMessageDialog(this, "Thêm mới thành công");
            xoatrang();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm dữ liệu: " + e.getMessage());
        }
    }//GEN-LAST:event_btthemActionPerformed

    private void btsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsuaActionPerformed
        try {
    String cd = txtcv.getText();  // Chức vụ
    String pp = txtkhoa.getText();  // Khoa
    String bs = txtsdt.getText();  // Số điện thoại

    // Kiểm tra nếu các trường không được để trống
    if (cd.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Không được để trống chức vụ");
        return;
    }
    if (pp.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Không được để trống khoa");
        return;
    }
    if (bs.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Không được để trống số điện thoại");
        return;
    }

    // Lấy mã nhân viên và tên nhân viên (giả sử bạn có trường txtmnv và txthoten để nhập)
    String mdt = txtmnv.getText();  // Mã nhân viên
    String mbn = txthoten.getText();  // Họ tên

    // Kiểm tra mã nhân viên không được trống
    if (mdt.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Không được để trống mã nhân viên");
        return;
    }

    con = BTL.Connect.KetnoiDB();  // Kết nối tới database

    // Câu lệnh SQL để cập nhật bản ghi trong bảng NhanVienYTe
    String sql = "UPDATE NhanVienYTe SET "
            + "HoTen = ?, "
            + "ChucVu = ?, "
            + "Khoa = ?, "
            + "SDT = ? "
            + "WHERE MaNhanVien = ?";

    // Sử dụng PreparedStatement để tránh SQL Injection
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1, mbn);   // Gán họ tên
    ps.setString(2, cd);    // Gán chức vụ
    ps.setString(3, pp);    // Gán khoa
    ps.setString(4, bs);    // Gán số điện thoại
    ps.setString(5, mdt);   // Gán mã nhân viên

    // Thực hiện câu lệnh cập nhật
    int rowsAffected = ps.executeUpdate();
    if (rowsAffected > 0) {
        JOptionPane.showMessageDialog(this, "Sửa thành công");
        load_nhanvien();  // Tải lại dữ liệu sau khi cập nhật
        xoatrang();   // Xóa trắng các trường nhập
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
        String mbn = txthoten.getText();  // Lấy mã bệnh nhân từ ô nhập liệu
try {
    con = BTL.Connect.KetnoiDB();  // Kết nối tới cơ sở dữ liệu
    if (con != null) {
        String sql = "DELETE FROM NhanVienYTe WHERE MaBenhNhan = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, mbn);  // Gán mã bệnh nhân vào câu lệnh SQL

        // Xác nhận từ người dùng trước khi xóa
        int response = JOptionPane.showConfirmDialog(null,
            "Bạn có muốn xóa?",
            "Xác nhận",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            int rowsAffected = ps.executeUpdate();  // Thực thi câu lệnh xóa
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Không có dữ liệu để xóa");
            }
        }

        // Tải lại dữ liệu sau khi xóa
        load_nhanvien();

        // Xóa trắng các trường nhập liệu
        txtmnv.setText("");
        txthoten.setText("");
        txtcv.setText("");
        txtkhoa.setText("");
        txtsdt.setText("");
    } else {
        JOptionPane.showMessageDialog(this, "Kết nối cơ sở dữ liệu không thành công");
    }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Xóa không thành công do lỗi hệ thống");
    } finally {
        try {
            if (con != null) {
                con.close();  // Đảm bảo đóng kết nối cơ sở dữ liệu
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    }//GEN-LAST:event_btxoaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();
        new Menu1().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btloadActionPerformed
       txtmnv.setEnabled(true);
       xoatrang();
    }//GEN-LAST:event_btloadActionPerformed
    
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
    
    private void btxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxuatActionPerformed
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
            cell.setCellValue("DANH SÁCH NHÂN VIÊN Y TẾ");

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
            cell.setCellValue("Mã Nhân Viên");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Họ Và Tên");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Chức Vụ");
            
            cell = row.createCell(4, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Khoa");
            
            cell = row.createCell(5, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("SDT");
            
             //Kết nối DB
            con = BTL.Connect.KetnoiDB();
            String sql = "Select * From NhanVienYTe";
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
                cell.setCellValue(rs.getString("MaNhanVien"));

                cell = row.createCell(2);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("HoTen"));
                
                cell = row.createCell(3);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("ChucVu"));
                
                cell = row.createCell(4);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("Khoa"));
                
                cell = row.createCell(5);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("SDT"));


                i++;
            }
            //Hiệu chỉnh độ rộng của cột
            for (int col = 0; col < tongsocot; col++) {
                spreadsheet.autoSizeColumn(col);
            }

            File f = new File("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\QLDT\\DSNVYT.xlsx");
            FileOutputStream out = new FileOutputStream(f);
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(this, "Xuất Excel thành công!!");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btxuatActionPerformed

    private void btnhapexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapexcelActionPerformed
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
    }//GEN-LAST:event_btnhapexcelActionPerformed

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
            java.util.logging.Logger.getLogger(Nhanvienyte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nhanvienyte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nhanvienyte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nhanvienyte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Nhanvienyte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btload;
    private javax.swing.JButton btnhapexcel;
    private javax.swing.JButton btsua;
    private javax.swing.JButton btthem;
    private javax.swing.JButton bttimkiem;
    private javax.swing.JButton btxoa;
    private javax.swing.JButton btxuat;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbnhanvien;
    private javax.swing.JTextField txtcv;
    private javax.swing.JTextField txthoten;
    private javax.swing.JTextField txtkhoa;
    private javax.swing.JTextField txtmnv;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
