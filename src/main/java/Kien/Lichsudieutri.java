/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Kien;

import BTL.Menu;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;
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
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class Lichsudieutri extends javax.swing.JFrame {

    /**
     * Creates new form Lichsudieutri
     */
    public Lichsudieutri() {
        initComponents();
        load_qtdt();
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
        btnLoad = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtTimkiem = new javax.swing.JTextField();
        bttimkiem = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMalich = new javax.swing.JTextField();
        txtManv = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtKq = new javax.swing.JTextField();
        dcngaysinh = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        txtBacsi = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKhoa = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jButton4.setText("Xuất Excel");
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

        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
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
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLoad)
                .addGap(27, 27, 27)
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
                    .addComponent(btnLoad))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản lý Lịch Sử Điều Trị");

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

        jLabel8.setText("Thông tin Lịch Sử Điều Trị");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        jLabel2.setText("Mã Bệnh Nhân");

        jLabel3.setText("Mã Lịch Sử");

        jLabel5.setText("Ngày Điều Trị");

        jLabel6.setText("Kết Quả Điều Trị");

        dcngaysinh.setDateFormatString("yyyy-MM-dd");

        jLabel7.setText("Bác Sĩ Điều Trị");

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
                    .addComponent(txtManv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(txtMalich, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBacsi, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(dcngaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtKq, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtMalich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(dcngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtManv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtKq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtBacsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        tbKhoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tbKhoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhoaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKhoa);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
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

    Connection con;
    private void load_qtdt(){
        try {
            tbKhoa.removeAll();
            //B1: Kết nối đến DB
            con= BTL.Connect.KetnoiDB();
            //B2: Tạo đối tượng Statement để thực hiện câu lệnh truy cập
            String sql = "Select * From LichSuDieuTri";
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String[] tieude={"Mã Lịch Sử", "Mã Bệnh Nhân","Ngày Điều Trị","Kết Quả Điều Trị","Bác Sĩ Điều Trị"};
            DefaultTableModel tb=new DefaultTableModel(tieude,0)    {           
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        // Tất cả các ô sẽ không thể chỉnh sửa
                        return false;
                    }
                    };
            
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("MaLichSu"));
                v.add(rs.getString("MaBenhNhan"));
                v.add(rs.getString("NgayDieuTri"));
                v.add(rs.getString("KetQuaDieuTri"));
                v.add(rs.getString("BacSiDieuTri"));
                tb.addRow(v);
            }
            tbKhoa.setModel(tb);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void xoatrang(){
            txtMalich.setText("");
            txtManv.setText("");
            dcngaysinh.setDate(null);
            txtKq.setText("");
            txtBacsi.setText("");
    }
    private void btthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthemActionPerformed
        // B1: lấy dữ liệu các compents đưa vào biến
        String ma = txtMalich.getText().trim();
        String ten = txtManv.getText().trim();
        SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
        Date ndt = new Date(dcngaysinh.getDate().getTime());
        String tk = txtKq.getText().trim();
        String bs = txtBacsi.getText().trim();
        // B1.1: Kiểm tra các trường bắt buộc phải nhập
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã lịch sử không được để trống.");
            txtMalich.requestFocus();
            return;
        }

        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã bệnh nhân không được để trống.");
            txtManv.requestFocus();
            return;
        }

        if (ndt == null) {
            JOptionPane.showMessageDialog(this, "Ngày điều trị không được để trống.");
            dcngaysinh.requestFocus();
            return;
        }

        if (tk.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kết quả không được để trống.");
            txtKq.requestFocus();
            return;
        }
        
        if (bs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bác sĩ không được để trống.");
            txtKq.requestFocus();
            return;
        }

        //B2: Kết nối Database
        try {
            Connection con = BTL.Connect.KetnoiDB();

            //B3: Tạo đối tượng Statement để thực hiện lệnh truy vấn
            String sql = "Insert INTO LichSuDieuTri values('"+ ma +"', N'"+ ten +"', '"+ ndt +"', N'"+ tk +"', N'"+ bs +"')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
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
        try{
            String mk = txtMalich.getText();
            if(mk.isEmpty()){
                JOptionPane.showMessageDialog(this, "Phải nhập mã lịch sử!");
                return;
            }
            String ten = txtManv.getText();
            if(ten.isEmpty()){
                JOptionPane.showMessageDialog(this, "Phải nhập mã bn!");
                return;
            }

            Date ndt = new Date (dcngaysinh.getDate().getTime());
            if (ndt == null) {
                JOptionPane.showMessageDialog(this, "Không được để trống ngày điều trị");
                return;
            }

            // Định dạng ngày điều trị thành kiểu chuỗi
            java.sql.Date sqlDate = new java.sql.Date(ndt.getTime());

            String tk = txtKq.getText();
            if(tk.isEmpty()){
                JOptionPane.showMessageDialog(this, "Phải nhập kết quả điều trị!");
                return;
            }
            
            String bs = txtBacsi.getText();
            if(tk.isEmpty()){
                JOptionPane.showMessageDialog(this, "Phải nhập bác sĩ điều trị!");
                return;
            }

            con = BTL.Connect.KetnoiDB();
            String sql = "Update LichSuDieuTri Set MaBenhNhan=N'"+ten+"',NgayDieuTri='"+ndt+"',KetQuaDieuTri=N'"+tk+"',BacSiDieuTri=N'"+bs+"' Where MaLichSu='"+mk+"'";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            con.close();
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            load_qtdt();
        }catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Sửa ko thành công");
        }
    }//GEN-LAST:event_btsuaActionPerformed

    private void btxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoaActionPerformed
        try{
            String mk = txtMalich.getText();
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                con = BTL.Connect.KetnoiDB();
                String sql = "Delete From LichSuDieuTri Where MaLichSu='"+mk+"'";
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
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Lịch Làm Việc");
            // register the columns you wish to track and compute the column width

            CreationHelper createHelper = workbook.getCreationHelper();

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH LỊCH SỬ ĐIỀU TRỊ");

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
            cell.setCellValue("Mã Lịch Sử");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã Bệnh Nhân");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Ngày Điều Trị");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Kết Quả Điều Trị");
            
            cell = row.createCell(5, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Bác Sĩ Điều Trị");

            //Kết nối DB
            con = BTL.Connect.KetnoiDB();
            String sql = "Select * From LichSuDieuTri";
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
                cell.setCellValue(rs.getString("MaLichSu"));

                cell = row.createCell(2);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("MaBenhNhan"));

                //Định dạng ngày tháng trong excel
                java.util.Date ngay = new java.util.Date(rs.getDate("NgayDieuTri").getTime());
                CellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
                cellStyle.setBorderLeft(BorderStyle.THIN);
                cellStyle.setBorderRight(BorderStyle.THIN);
                cellStyle.setBorderBottom(BorderStyle.THIN);
                cell = row.createCell(3);
                cell.setCellValue(ngay);
                cell.setCellStyle(cellStyle);

                cell = row.createCell(4);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("KetQuaDieuTri"));
                
                cell = row.createCell(5);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("BacSiDieuTri"));

                i++;
            }
            //Hiệu chỉnh độ rộng của cột
            for (int col = 0; col < tongsocot; col++) {
                spreadsheet.autoSizeColumn(col);
            }

            File f = new File("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhan\\src\\main\\java\\Kien\\Lichsudieutri.xlsx");
            FileOutputStream out = new FileOutputStream(f);
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(this, "Xuất Excel thành công!!");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();
        new Menu().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        // TODO add your handling code here:
        txtMalich.setEnabled(true);
    }//GEN-LAST:event_btnLoadActionPerformed

    private void txtTimkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimkiemMouseClicked
        xoatrang();
    }//GEN-LAST:event_txtTimkiemMouseClicked

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased
        //        try {
            //            // Lấy mã bệnh nhân từ trường nhập liệu
            //            tbKhoa.removeAll();
            //            String mbn = txtTimkiem.getText().trim();  // Mã bệnh nhân
            //
            //            // Kết nối đến cơ sở dữ liệu
            //            con = BTL.Connect.KetnoiDB(); //dm chó tuấn
            //            Statement st = con.createStatement();
            //
            //            // Xây dựng câu lệnh SQL cho tìm kiếm
            //            String sql = "SELECT * FROM QuaTrinhDieuTri WHERE MaBenhNhan LIKE '%" + mbn + "%'";
            //
            //            // Thực hiện truy vấn
            //            ResultSet rs = st.executeQuery(sql);
            //            String[] tieude = {"Mã điều trị", "Mã bệnh nhân", "Ngày điều trị", "Chẩn đoán điều trị", "Phương pháp điều trị", "Bác sĩ điều trị"};
            //            DefaultTableModel tb = new DefaultTableModel(tieude, 0);
            //            tb.setRowCount(0);
            //
            //            // Duyệt qua kết quả và thêm vào bảng
            //            while (rs.next()) {
                //                Vector<String> v = new Vector<>();
                //                v.add(rs.getString("MaDieuTri"));          // Mã điều trị
                //                v.add(rs.getString("MaBenhNhan"));         // Mã bệnh nhân
                //                v.add(rs.getString("NgayDieuTri"));        // Ngày điều trị
                //                v.add(rs.getString("ChanDoanDieuTri"));    // Chẩn đoán điều trị
                //                v.add(rs.getString("PhuongPhapDieuTri"));  // Phương pháp điều trị
                //                v.add(rs.getString("BacSiDieuTri"));       // Bác sĩ điều trị
                //                tb.addRow(v);
                //            }
            //
            //            // Cập nhật bảng hiển thị
            //            tbKhoa.setModel(tb);
            //            con.close();  // Đóng kết nối
            //        } catch (Exception e) {
            //            e.printStackTrace();
            //            JOptionPane.showMessageDialog(this, "Tìm kiếm không thành công");
            //        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimkiemKeyReleased

    private void bttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttimkiemActionPerformed
        try{
            //lấy dữ liệu từ compoment đưa vài biến
            String mk = txtTimkiem.getText().trim();
            //            String ten = txtTimkiem.getText().trim();
            //            String tk = txtTimkiem.getText().trim();
            con = BTL.Connect.KetnoiDB();
            String sql = "Select * From LichSuDieuTri Where MaLichSu like'%"+mk+"%'"; // and TenKhoa like N'%"+ten+"%' and TruongKhoa like N'%"+tk+"%'
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String[] tieude = {"Mã Lịch Sử", "Mã Bệnh Nhân","Ngày Điều Trị","Kết Quả Điều Trị","Bác Sĩ Điều Trị"};
            DefaultTableModel tb = new DefaultTableModel(tieude,0);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("MaLichSu"));
                v.add(rs.getString("MaBenhNhan"));
                v.add(rs.getString("NgayDieuTri"));
                v.add(rs.getString("KetQuaDieuTri"));
                v.add(rs.getString("BacSiDieuTri"));
                tb.addRow(v);
            }
            tbKhoa.setModel(tb);
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_bttimkiemActionPerformed

    private void tbKhoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhoaMouseClicked
        int i = tbKhoa.getSelectedRow();
        DefaultTableModel tb = (DefaultTableModel)tbKhoa.getModel();
        txtMalich.setText(tb.getValueAt(i, 0).toString());
        txtManv.setText(tb.getValueAt(i, 1).toString());
        String ngay = tb.getValueAt(i, 2).toString();
        java.util.Date ngs;
        try{
            ngs = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            dcngaysinh.setDate(ngs);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        txtKq.setText(tb.getValueAt(i, 3).toString());
        txtBacsi.setText(tb.getValueAt(i, 4).toString());
        txtMalich.setEnabled(false);
    }//GEN-LAST:event_tbKhoaMouseClicked

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
            java.util.logging.Logger.getLogger(Lichsudieutri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lichsudieutri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lichsudieutri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lichsudieutri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lichsudieutri().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btsua;
    private javax.swing.JButton btthem;
    private javax.swing.JButton bttimkiem;
    private javax.swing.JButton btxoa;
    private com.toedter.calendar.JDateChooser dcngaysinh;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JTable tbKhoa;
    private javax.swing.JTextField txtBacsi;
    private javax.swing.JTextField txtKq;
    private javax.swing.JTextField txtMalich;
    private javax.swing.JTextField txtManv;
    private javax.swing.JTextField txtTimkiem;
    // End of variables declaration//GEN-END:variables
}
