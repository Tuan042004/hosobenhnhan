/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Van;

import BTL.Connect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell V
 */
public class Sua extends javax.swing.JFrame {

    /**
     * Creates new form Sua
     */
    public Sua() throws ClassNotFoundException {
        initComponents();
        load_hsnv();
        loadcbo();
        txtmhs3.setEnabled(false);
        cbmbn3.setEnabled(false);
    }
    Connection con;
    public void load_hsnv(){
        try {
    // Làm sạch bảng trước khi thêm dữ liệu mới
    tbhs3.removeAll();

    // B1: Kết nối đến DB
    con = Connect.KetnoiDB();

    // B2: Tạo đối tượng Statement để thực hiện câu lệnh truy cập
    String sql = "SELECT * from HoSoNhapVien";
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);

    // Định nghĩa tiêu đề cho bảng
    String[] tieude = { "Mã hồ sơ", "Mã bệnh nhân", "Ngày nhập viện", "Chẩn đoán", "Mã phòng", "Mã giường", "Mã khoa", "Tên khoa" };

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
        v.add(rs.getString("MaHoSoNhapVien"));        // Mã hồ sơ
        v.add(rs.getString("MaBenhNhan"));             // Mã bệnh nhân
//        v.add(rs.getString("HoTenBenhNhan"));   // Họ Tên Bệnh Nhân
        v.add(rs.getDate("NgayNhapVien").toString()); // Ngày nhập viện        
        v.add(rs.getString("ChanDoan")); //chẩn đoán bệnh
        v.add(rs.getString("MaPhong"));                  // Mã phòng
        v.add(rs.getString("MaGiuong"));                 // mã giường
        v.add(rs.getString("MaKhoa"));            // mã khoa
        v.add(rs.getString("TenKhoa"));                // tên khoa
        tb.addRow(v);
    }  

    // Đóng kết nối
    tbhs3.setModel(tb);
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
    public void loadcbo() throws ClassNotFoundException {
    try {
        con = Connect.KetnoiDB();
        String query = "SELECT MaBenhNhan FROM BenhNhan"; // Thay đổi truy vấn để lấy mã bệnh nhân
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            cbmbn3.addItem(rs.getString("MaBenhNhan")); // Thêm mã bệnh nhân vào ComboBox
        }
        
    // Lấy TenKhoa từ bảng Khoa
    String queryKhoa = "SELECT MaKhoa FROM Khoa";
    Statement statementKhoa = con.createStatement();
    ResultSet rsKhoa = statementKhoa.executeQuery(queryKhoa);
    while (rsKhoa.next()) {
        cbmk3.addItem(rsKhoa.getString("MaKhoa")); // thêm mã khoa vào combo box
    }
        
    // Lấy TenKhoa từ bảng Khoa
    String queryTenKhoa = "SELECT TenKhoa FROM Khoa";
    Statement statementTenKhoa = con.createStatement();
    ResultSet rsTenKhoa = statementTenKhoa.executeQuery(queryTenKhoa);
    while (rsTenKhoa.next()) {
        cbtk3.addItem(rsTenKhoa.getString("TenKhoa")); //thêm tên khoa vào combo box
    }
    
            // Lấy MaPhong từ bảng PhongBenh
    String queryPhong = "SELECT MaPhong FROM PhongBenh";
    Statement statementNhanVien = con.createStatement();
    ResultSet rsPhong = statementNhanVien.executeQuery(queryPhong);
    while (rsPhong.next()) {
        cbmp3.addItem(rsPhong.getString("MaPhong")); //thêm mã phòng vào combo box
    }
    
     // Lấy MaGiuong từ bảng Giường
    String queryGiuong = "SELECT MaGiuong FROM Giuong";
    Statement statementGiuong = con.createStatement();
    ResultSet rsGiuong = statementGiuong.executeQuery(queryGiuong);
    while (rsGiuong.next()) {
        cbmg3.addItem(rsGiuong.getString("MaGiuong")); //thêm mã giư vào combo box
    }
    
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi tải danh mục: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        btluu = new javax.swing.JButton();
        btcapnhat = new javax.swing.JButton();
        btxoa = new javax.swing.JButton();
        btthoat = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtmhs3 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        dcnnv3 = new com.toedter.calendar.JDateChooser();
        jScrollPane7 = new javax.swing.JScrollPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbhs3 = new javax.swing.JTable();
        cbmp3 = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        cbmg3 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        cbmk3 = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        cbtk3 = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        txtcd3 = new javax.swing.JTextField();
        cbmbn3 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        jButton4.setText("Trở lại");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        btthoat.setText("Trở lại");
        btthoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btthoatActionPerformed(evt);
            }
        });

        jButton2.setText("Thêm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(btluu, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btcapnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btthoat)
                .addContainerGap(250, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btluu)
                    .addComponent(btcapnhat)
                    .addComponent(btxoa)
                    .addComponent(btthoat)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Cập nhật thông tin chi tiết"));

        jLabel27.setText("Mã hồ sơ:");

        jLabel28.setText("Mã bệnh nhân: ");

        jLabel29.setText("Ngày nhập viện: ");

        jLabel30.setText("Mã Phòng:");

        tbhs3.setModel(new javax.swing.table.DefaultTableModel(
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
        tbhs3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbhs3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbhs3MouseEntered(evt);
            }
        });
        jScrollPane8.setViewportView(tbhs3);

        jScrollPane7.setViewportView(jScrollPane8);

        cbmp3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Phòng" }));
        cbmp3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmp3ItemStateChanged(evt);
            }
        });

        jLabel31.setText("Mã Giường:");

        cbmg3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Giường" }));
        cbmg3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmg3ItemStateChanged(evt);
            }
        });

        jLabel32.setText("Mã Khoa:");

        cbmk3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Mã Khoa" }));
        cbmk3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmk3ItemStateChanged(evt);
            }
        });

        jLabel33.setText("Tên Khoa:");

        cbtk3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Tên Khoa" }));
        cbtk3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbtk3ItemStateChanged(evt);
            }
        });

        jLabel34.setText("chẩn đoán:");

        txtcd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcd3ActionPerformed(evt);
            }
        });

        cbmbn3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn Mã Bệnh Nhân" }));
        cbmbn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmbn3ActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane7)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel27))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbmp3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmhs3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcnnv3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel28)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbmbn3, 0, 180, Short.MAX_VALUE)
                                    .addComponent(cbmg3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel33))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbmk3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbtk3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtcd3, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(cbmbn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(cbmk3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtmhs3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(cbmp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(cbmg3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33)
                            .addComponent(cbtk3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(dcnnv3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcd3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton3.setText("Sửa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton3)
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 205, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 205, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btluuActionPerformed
        
    }//GEN-LAST:event_btluuActionPerformed

    private void btcapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcapnhatActionPerformed
        
    }//GEN-LAST:event_btcapnhatActionPerformed

    private void btxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoaActionPerformed

    }//GEN-LAST:event_btxoaActionPerformed

    private void btthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthoatActionPerformed
        dispose();
    }//GEN-LAST:event_btthoatActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        xoatrang();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbhs3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbhs3MouseClicked
        //Lấy chỉ số của dòng được click
        int i = tbhs3.getSelectedRow();
        DefaultTableModel tb = (DefaultTableModel) tbhs3.getModel();
        //gán giá trị cho textfield
        txtmhs3.setText(tb.getValueAt(i, 0).toString());
        //gán giá trị cho combobox mã bệnh nhân
        cbmbn3.setSelectedItem(tb.getValueAt(i, 1).toString());
        //gán giá trị cho datechooser ngày nhập viện
        String nnv = tb.getValueAt(i, 2).toString();
        java.util.Date ng;
        try {
            ng = new SimpleDateFormat("yyyy-MM-DD").parse(nnv);
            dcnnv3.setDate(ng);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        //gán giá trị cho tf chẩn đoán
        txtcd3.setText(tb.getValueAt(i, 3).toString());
        //gán giá trị cho cbb mã phòng
        cbmp3.setSelectedItem(tb.getValueAt(i, 4).toString());
        cbmg3.setSelectedItem(tb.getValueAt(i, 5).toString());
        cbmk3.setSelectedItem(tb.getValueAt(i, 6).toString());
        cbtk3.setSelectedItem(tb.getValueAt(i, 7).toString());
    }//GEN-LAST:event_tbhs3MouseClicked

    private void tbhs3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbhs3MouseEntered

    }//GEN-LAST:event_tbhs3MouseEntered

    private void cbmp3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmp3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmp3ItemStateChanged

    private void cbmg3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmg3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmg3ItemStateChanged

    private void cbmk3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmk3ItemStateChanged
        // TODO add your handling code here:;
    }//GEN-LAST:event_cbmk3ItemStateChanged

    private void cbtk3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbtk3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbtk3ItemStateChanged

    private void txtcd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcd3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcd3ActionPerformed

    private void cbmbn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmbn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmbn3ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            String mhs = txtmhs3.getText().trim();  // Mã hồ sơ
            String mbn = (String) cbmbn3.getSelectedItem(); // Mã bệnh nhân
            String mk = (String) cbmk3.getSelectedItem();  // Mã khoa
            String tk = (String) cbtk3.getSelectedItem();  // Tên khoa
            String mp = (String) cbmp3.getSelectedItem();  // Mã phòng
            String mg = (String) cbmg3.getSelectedItem(); // Mã giường
            String cd = txtcd3.getText().trim();  // Chẩn đoán bệnh
            Date dateValue = dcnnv3.getDate(); // Ngày nhập viện

            if (mhs.isEmpty() || mbn == null || mk == null || mp == null || tk == null || mg == null || cd.isEmpty() || dateValue == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền tất cả các trường.");
                return;
            }

            java.sql.Date sqlDateNhapVien = new java.sql.Date(dateValue.getTime());

            // Câu lệnh SQL để cập nhật bản ghi
            String sql = "UPDATE HoSoNhapVien SET NgayNhapVien = ?, ChanDoan = ?, MaPhong = ?, MaGiuong = ?, MaKhoa = ?, TenKhoa = ? WHERE MaHoSoNhapVien = ? AND MaBenhNhan = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sqlDateNhapVien);
            ps.setString(2, cd);
            ps.setString(3, mp);
            ps.setString(4, mg);
            ps.setString(5, mk);
            ps.setString(6, tk);
            ps.setString(7, mhs);
            ps.setString(8, mbn);

            // Thực hiện câu lệnh cập nhật
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                load_hsnv();  // Tải lại dữ liệu sau khi cập nhật
                xoatrang();   // Xóa các trường nhập liệu
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu để sửa");
            }

//            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi SQL: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sửa không thành công: " + e.getMessage());
            e.printStackTrace();
        }
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Sua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Sua().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Sua.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcapnhat;
    private javax.swing.JButton btluu;
    private javax.swing.JButton btthoat;
    private javax.swing.JButton btxoa;
    private javax.swing.JComboBox<String> cbmbn3;
    private javax.swing.JComboBox<String> cbmg3;
    private javax.swing.JComboBox<String> cbmk3;
    private javax.swing.JComboBox<String> cbmp3;
    private javax.swing.JComboBox<String> cbtk3;
    private com.toedter.calendar.JDateChooser dcnnv3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable tbhs3;
    private javax.swing.JTextField txtcd3;
    private javax.swing.JTextField txtmhs3;
    // End of variables declaration//GEN-END:variables

    

    private void xoatrang() {
    txtmhs3.setText("");
        cbmbn3.setSelectedItem(null);  // Xóa lựa chọn trong JComboBox
        cbmk3.setSelectedItem(null);
        cbtk3.setSelectedItem(null);
        cbmg3.setSelectedItem(null);
        cbmp3.setSelectedItem(null);
        dcnnv3.setDate(null);   
        txtcd3.setText("");
        
        // Mở khóa lại các trường txtmhs và cbmbn
    txtmhs3.setEnabled(true);  // Mở lại trường mã hồ sơ để người dùng nhập
    cbmbn3.setEnabled(true);  // Mở lại JComboBox mã bệnh nhân để người dùng chọn
    }
    
}
