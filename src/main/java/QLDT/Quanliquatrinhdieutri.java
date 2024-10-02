/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package QLDT;

import BTL.Connect;
import BTL.Menu;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Quanliquatrinhdieutri extends javax.swing.JFrame {

    /**
     * Creates new form Quanliquatrinhdieutri
     */
    public Quanliquatrinhdieutri() {
        initComponents();
        load_qtdt();
 
    }
    
    Connection con;
    private void load_qtdt(){
        try {
            tbqtdt.removeAll();
            //B1: Kết nối đến DB
            con= BTL.Connect.KetnoiDB();
            //B2: Tạo đối tượng Statement để thực hiện câu lệnh truy cập
            String sql = "Select * From QuaTrinhDieuTri";
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String[] tieude={"Mã điều trị", "Mã bệnh nhân","Ngày điều trị","Chẩn đoán điều trị","Phương pháp điều trị","Bác sĩ điều trị"};
            DefaultTableModel tb=new DefaultTableModel(tieude,0)    {           
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        // Tất cả các ô sẽ không thể chỉnh sửa
                        return false;
                    }
                    };
            
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("MaDieuTri"));
                v.add(rs.getString("MaBenhNhan"));
                v.add(rs.getString("NgayDieuTri"));
                v.add(rs.getString("ChanDoanDieuTri"));
                v.add(rs.getString("PhuongPhapDieuTri"));
                v.add(rs.getString("BacSiDieuTri"));
                tb.addRow(v);
            }
            tbqtdt.setModel(tb);
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtmdt = new javax.swing.JTextField();
        txtmbn = new javax.swing.JTextField();
        dcndt = new com.toedter.calendar.JDateChooser();
        txtcd = new javax.swing.JTextField();
        txtpp = new javax.swing.JTextField();
        txtbs = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbqtdt = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btthem = new javax.swing.JButton();
        btsua = new javax.swing.JButton();
        btxoa = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(880, 600));
        setMinimumSize(new java.awt.Dimension(880, 600));
        setPreferredSize(new java.awt.Dimension(880, 700));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản lý quá trình điều trị");

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

        jLabel8.setText("Mã bệnh nhân");

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
                .addContainerGap(114, Short.MAX_VALUE))
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

        jLabel2.setText("Mã bệnh nhân");

        jLabel3.setText("Mã điều trị");

        jLabel4.setText("Ngày điều trị");

        jLabel5.setText("Chẩn đoán điều trị");

        jLabel6.setText("Phương pháp điều trị");

        jLabel7.setText("Bác sĩ điều trị");

        dcndt.setDateFormatString("yyyy-MM-dd");

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
                    .addComponent(dcndt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(txtmbn, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmdt, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtbs))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtpp)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtmdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtmbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)
                    .addComponent(txtpp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtbs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(dcndt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        tbqtdt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã điều trị", "Mã bệnh nhân", "Ngày điều trị", "Chuẩn đoán điều trị", "Phương pháp điều trị", "Bác sĩ điều trị"
            }
        ));
        tbqtdt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbqtdtMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbqtdt);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
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

        jButton4.setText("jButton4");

        jButton5.setText("Thoát");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
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
                    .addComponent(jButton5))
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
    }// </editor-fold>//GEN-END:initComponents

    private void tbqtdtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbqtdtMouseClicked
        int i=tbqtdt.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel)tbqtdt.getModel();
        txtmdt.setText(tb.getValueAt(i, 0).toString());
        txtmbn.setText(tb.getValueAt(i, 1).toString());
        String ngay=tb.getValueAt(i, 2).toString();
        txtcd.setText(tb.getValueAt(i, 3).toString());
        txtpp.setText(tb.getValueAt(i, 4).toString());
        txtbs.setText(tb.getValueAt(i, 5).toString());
        txtmbn.setEnabled(false);
        java.util.Date ngs;
        try {
            ngs = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            dcndt.setDate(ngs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_tbqtdtMouseClicked
    
    public void xoatrang(){
            txtmdt.setText("");
            txtmbn.setText("");
            dcndt.setDate(null);
            txtcd.setText("");
            txtpp.setText("");
            txtbs.setText("");
    }
    private void btthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthemActionPerformed
        // B1: lấy dữ liệu các compents đưa vào biến
        String mdt = txtmdt.getText().trim();
        String mbn = txtmbn.getText().trim();

        SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
        Date ndt = new Date(dcndt.getDate().getTime());       
        String cd = txtcd.getText().trim();
        String pp = txtpp.getText().trim();
        String bs = txtbs.getText().trim();
       

        // B1.1: Kiểm tra các trường bắt buộc phải nhập
        if (mdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã quá trình không được để trống.");
            txtmdt.requestFocus();
            return;
        }

        if (mbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã bệnh nhân không được để trống.");
            txtmbn.requestFocus();
            return;
        }

        if (ndt == null) {
            JOptionPane.showMessageDialog(this, "Ngày điều trị không được để trống.");
            dcndt.requestFocus();
            return;
        }

        if (cd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chẩn đoán không được để trống.");
            txtcd.requestFocus();
            return;
        }

        if (pp.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Quá trình điều trị không được để trống.");
            txtpp.requestFocus();
            return;
        }

        if (bs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Điều trị không được để trống.");
            txtbs.requestFocus();
            return;
        }

        //B2: Kết nối Database
        try {
            con = BTL.Connect.KetnoiDB();  

            //B3: Tạo đối tượng Statement để thực hiện lệnh truy vấn 
            String sql = "Insert INTO QuaTrinhDieuTri values('"+ mdt +"','"+ mbn +"', '"+ ndt +"', N'"+ cd +"', N'"+ pp +"', N'"+ bs +"')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            con.close();
            load_qtdt();
            JOptionPane.showMessageDialog(this, "Thêm mới thành công");    
            txtmdt.setText("");
            txtmbn.setText("");
            dcndt.setDate(null);
            txtcd.setText("");
            txtpp.setText("");
            txtbs.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm dữ liệu: " + e.getMessage());
        }
    }//GEN-LAST:event_btthemActionPerformed

    private void btxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoaActionPerformed
    String mbn = txtmbn.getText();
    try {
        con = Connect.KetnoiDB();
        String sql = "DELETE FROM QuaTrinhDieuTri WHERE MaBenhNhan = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, mbn);

        int response = JOptionPane.showConfirmDialog(null, 
            "Bạn có muốn xóa?", 
            "Xác nhận", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Không có dữ liệu để xóa");
            }
        }

        con.close();
        load_qtdt();  // reload the table to reflect changes
        // Clear the input fields
        txtmdt.setText("");
        txtmbn.setText("");
        dcndt.setDate(null);
        txtcd.setText("");
        txtpp.setText("");
        txtbs.setText("");
    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_btxoaActionPerformed

//    private void load_sua(){
//          try {
//            tbqtdt.removeAll();
//            con= BTL.Connect.KetnoiDB();
//            //B2: Tạo đối tượng Statement để thực hiện câu lệnh truy cập
//            String sql = "Select * From QuaTrinhDieuTri";
//            Statement st=con.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            String[] tieude={"Mã điều trị", "Mã bệnh nhân","Ngày điều trị","Chẩn đoán điều trị","Phương pháp điều trị","Bác sĩ điều trị"};
//            DefaultTableModel tb=new DefaultTableModel(tieude,0);
//            while(rs.next()){
//                Vector v = new Vector();
//                v.add(rs.getString("MaDieuTri"));
//                v.add(rs.getString("MaBenhNhan"));
//                v.add(rs.getString("NgayDieuTri"));
//                v.add(rs.getString("ChanDoanDieuTri"));
//                v.add(rs.getString("PhuongPhapDieuTri"));
//                v.add(rs.getString("BacSiDieuTri"));
//                tb.addRow(v);
//            }
//            tbqtdt.setModel(tb);
//            con.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    private void btsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsuaActionPerformed
            try {
        String cd = txtcd.getText();  // Chẩn đoán điều trị
        String pp = txtpp.getText();  // Phương pháp điều trị
        String bs = txtbs.getText();  // Bác sĩ điều trị

        // Kiểm tra nếu các trường không được để trống
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

        // Lấy ngày điều trị từ DateChooser
        Date ndt = new Date (dcndt.getDate().getTime());
        if (ndt == null) {
            JOptionPane.showMessageDialog(this, "Không được để trống ngày điều trị");
            return;
        }

        // Định dạng ngày điều trị thành kiểu chuỗi
        java.sql.Date sqlDate = new java.sql.Date(ndt.getTime());

        // Lấy mã điều trị và mã bệnh nhân (giả sử bạn có trường txtmdt và txtmbn để nhập mã)
        String mdt = txtmdt.getText();  // Mã điều trị
        String mbn = txtmbn.getText();  // Mã bệnh nhân

        // Kiểm tra mã điều trị không được trống
        if (mdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã điều trị");
            return;
        }

        con = Connect.KetnoiDB();  // Kết nối tới database

        // Câu lệnh SQL để cập nhật bản ghi trong bảng QuaTrinhDieuTri
        String sql = "UPDATE QuaTrinhDieuTri SET "
                   + "NgayDieuTri = ?, "
                   + "ChanDoanDieuTri = ?, "
                   + "PhuongPhapDieuTri = ?, "
                   + "BacSiDieuTri = ? "
                   + "WHERE MaDieuTri = ? AND MaBenhNhan = ?";

        // Sử dụng PreparedStatement để tránh SQL Injection
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, sqlDate);  // Gán ngày điều trị
        ps.setString(2, cd);     // Gán chẩn đoán điều trị
        ps.setString(3, pp);     // Gán phương pháp điều trị
        ps.setString(4, bs);     // Gán bác sĩ điều trị
        ps.setString(5, mdt);    // Gán mã điều trị
        ps.setString(6, mbn);    // Gán mã bệnh nhân

        // Thực hiện câu lệnh cập nhật
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            load_qtdt();  // Tải lại dữ liệu sau khi cập nhật
            xoatrang();  
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu để sửa");
        }
      
        con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sửa không thành công");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btsuaActionPerformed

    private void bttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttimkiemActionPerformed

    }//GEN-LAST:event_bttimkiemActionPerformed

    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased
                    try {
           // Lấy mã bệnh nhân từ trường nhập liệu
           tbqtdt.removeAll();
           String mbn = txttimkiem.getText().trim();  // Mã bệnh nhân

           // Kết nối đến cơ sở dữ liệu
           con = BTL.Connect.KetnoiDB();
           Statement st = con.createStatement();

           // Xây dựng câu lệnh SQL cho tìm kiếm
           String sql = "SELECT * FROM QuaTrinhDieuTri WHERE MaBenhNhan LIKE '%" + mbn + "%'";

           // Thực hiện truy vấn
           ResultSet rs = st.executeQuery(sql);
           String[] tieude = {"Mã điều trị", "Mã bệnh nhân", "Ngày điều trị", "Chẩn đoán điều trị", "Phương pháp điều trị", "Bác sĩ điều trị"};
           DefaultTableModel tb = new DefaultTableModel(tieude, 0);
           tb.setRowCount(0);

           // Duyệt qua kết quả và thêm vào bảng
           while (rs.next()) {
               Vector<String> v = new Vector<>();
               v.add(rs.getString("MaDieuTri"));          // Mã điều trị
               v.add(rs.getString("MaBenhNhan"));         // Mã bệnh nhân
               v.add(rs.getString("NgayDieuTri"));        // Ngày điều trị
               v.add(rs.getString("ChanDoanDieuTri"));    // Chẩn đoán điều trị
               v.add(rs.getString("PhuongPhapDieuTri"));  // Phương pháp điều trị
               v.add(rs.getString("BacSiDieuTri"));       // Bác sĩ điều trị
               tb.addRow(v);
           }

           // Cập nhật bảng hiển thị
           tbqtdt.setModel(tb);
           con.close();  // Đóng kết nối
       } catch (Exception e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(this, "Tìm kiếm không thành công");
       }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiemKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       dispose();  
        new Menu().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txttimkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimkiemMouseClicked
        xoatrang();
    }//GEN-LAST:event_txttimkiemMouseClicked

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
            java.util.logging.Logger.getLogger(Quanliquatrinhdieutri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quanliquatrinhdieutri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quanliquatrinhdieutri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quanliquatrinhdieutri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Quanliquatrinhdieutri().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btsua;
    private javax.swing.JButton btthem;
    private javax.swing.JButton bttimkiem;
    private javax.swing.JButton btxoa;
    private com.toedter.calendar.JDateChooser dcndt;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbqtdt;
    private javax.swing.JTextField txtbs;
    private javax.swing.JTextField txtcd;
    private javax.swing.JTextField txtmbn;
    private javax.swing.JTextField txtmdt;
    private javax.swing.JTextField txtpp;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
