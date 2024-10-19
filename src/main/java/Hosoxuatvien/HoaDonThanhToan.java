/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Hosoxuatvien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Admin
 */
public class HoaDonThanhToan extends javax.swing.JInternalFrame {

    private ArrayList<String> selectedMedicines; // Danh sách các thuốc đã chọn
    /**
     * Creates new form HoaDonThanhToan
     */
    public HoaDonThanhToan() throws ClassNotFoundException {                       
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        
        initComponents();
        loadcbo();
        load_hd();
        
        selectedMedicines = new ArrayList<>();

        // Thêm ActionListener cho JComboBox
        cbmt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbmtActionPerformed(e);
            }
        });
    }
    
    Connection con;
   
   
   public void xoatrang() {
        cbmhs.setSelectedItem("---Chọn mã hồ sơ---");
        cbmt.setSelectedItem("---Chọn mã thuốc---");  // Xóa lựa chọn trong JComboBox
        cbbhyt.setSelectedItem("---Loại bảo hiểm y tế---");
        txtll.setText("");
        txtthanhtoan.setText("");
        dcngay.setDate(null);  

        // Mở khóa lại các trường txtmhs và cbmbn
        cbmhs.setEnabled(true);  
        cbbhyt.setEnabled(true);
    }
   
    private void loadcbo() throws ClassNotFoundException {
    try {
        con = BTL.Connect.KetnoiDB();
        
        String queryhs = "SELECT MaHoSoXuatVien FROM HoSoXuatVien"; 
        Statement statemenths = con.createStatement();
        ResultSet rshs = statemenths.executeQuery(queryhs);
        while (rshs.next()) {
            cbmhs.addItem(rshs.getString("MaHoSoXuatVien"));
            }
        
        String query = "SELECT MaThuoc FROM Thuoc"; // Thay đổi truy vấn để lấy mã bệnh nhân
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            cbmt.addItem(rs.getString("MaThuoc")); // Thêm mã bệnh nhân vào ComboBox
            }
        } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi tải danh mục: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
   
    
    private void load_hd(){
        try {
    // Làm sạch bảng trước khi thêm dữ liệu mới
    tbhd.removeAll();

    // B1: Kết nối đến DB
    con = BTL.Connect.KetnoiDB();

    // B2: Tạo đối tượng Statement để thực hiện câu lệnh truy cập
    String sql = "SELECT * FROM DieuTriThanhToan";
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);

    // Định nghĩa tiêu đề cho bảng
    String[] tieude = {"Mã hồ sơ", "Mã thuốc"," Liều lượng","Thanh toán"," Ngày thanh toán"};
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
        v.add(rs.getString("MaHoSoXuatVien"));       
        v.add(rs.getString("MaThuoc"));
        v.add(rs.getString("LieuLuong"));
        v.add(rs.getString("SoTien")); 
        v.add(rs.getDate("NgayThanhToan").toString());       
        tb.addRow(v);
    }  

    // Đóng kết nối
    tbhd.setModel(tb);
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
    
    
   private double getDonGia(String maThuoc) throws ClassNotFoundException, SQLException {
    double donGia = 0.0;
    String sql = "SELECT DonGia FROM Thuoc WHERE MaThuoc = ?";
    con = BTL.Connect.KetnoiDB();
    try (
        PreparedStatement pstmt = con.prepareStatement(sql)) {
        
        pstmt.setString(1, maThuoc);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            donGia = rs.getDouble("DonGia"); // Lấy giá từ kết quả truy vấn
        }
    } catch (Exception e) {
        e.printStackTrace(); // Xử lý lỗi nếu cần
    }
    
    return donGia;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbmhs = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtll = new javax.swing.JTextField();
        txtthanhtoan = new javax.swing.JTextField();
        dcngay = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbhd = new javax.swing.JTable();
        cbmt = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbbhyt = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btthem = new javax.swing.JButton();
        btthoat = new javax.swing.JButton();
        btsua = new javax.swing.JButton();
        btxoa = new javax.swing.JButton();
        btxuathd = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(935, 600));

        jLabel1.setText("Hóa đơn thanh toán");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết"));

        jLabel2.setText("Thuốc");

        jLabel3.setText("Liều lượng");

        jLabel4.setText("Thanh toán");

        jLabel5.setText("Ngày thanh toán");

        cbmhs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn mã hồ sơ---" }));

        jLabel7.setText("Mã hồ sơ");

        dcngay.setDateFormatString("yyyy-MM-dd");

        tbhd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hồ sơ", "Mã thuốc", "Liều lượng", "Thanh toán", "Ngày thanh toán"
            }
        ));
        tbhd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbhdMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbhd);

        cbmt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn mã thuốc---" }));
        cbmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmtActionPerformed(evt);
            }
        });

        jLabel6.setText("BHYT");

        cbbhyt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Loại bảo hiểm y tế---", "90%", "85%", "70%" }));
        cbbhyt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbhytActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbmhs, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtthanhtoan)
                                    .addComponent(cbbhyt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(cbmt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtll, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(dcngay, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbmhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2)
                    .addComponent(cbmt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbbhyt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addComponent(txtthanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin tìm kiếm"));

        jLabel8.setText("Mã hồ sơ");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        btthem.setText("Thêm ");
        btthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btthemActionPerformed(evt);
            }
        });

        btthoat.setText("Thoát");

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

        btxuathd.setText("Xuất hóa đơn");
        btxuathd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btxuathdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btthem)
                .addGap(18, 18, 18)
                .addComponent(btsua)
                .addGap(18, 18, 18)
                .addComponent(btxoa)
                .addGap(18, 18, 18)
                .addComponent(btxuathd)
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
                    .addComponent(btthoat)
                    .addComponent(btxuathd))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbhdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbhdMouseClicked
        int i = tbhd.getSelectedRow();
        DefaultTableModel tb = (DefaultTableModel) tbhd.getModel();

        cbmhs.setSelectedItem(tb.getValueAt(i, 0).toString());

        cbmt.setSelectedItem(tb.getValueAt(i, 1).toString());

        txtll.setText(tb.getValueAt(i, 2).toString());

        txtthanhtoan.setText(tb.getValueAt(i, 3).toString());
        // Gán giá trị cho JDateChooser ngày điều trị
        String ngay = tb.getValueAt(i, 4).toString();
        java.util.Date ngs;
        try {
            ngs = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            dcngay.setDate(ngs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Vô hiệu hóa việc chỉnh sửa mã bệnh nhân
        cbmhs.setEnabled(false);
        cbbhyt.setEnabled(false);
    }//GEN-LAST:event_tbhdMouseClicked

    private void cbmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmtActionPerformed
        // Lấy mã thuốc đã chọn từ JComboBox
        String selectedMaThuoc = (String) cbmt.getSelectedItem();

        if (selectedMaThuoc == null) {
            System.out.println("Chưa chọn mã thuốc!");
            return; // Thoát nếu không có thuốc nào được chọn
        }

        // Gọi phương thức để lấy giá tiền từ mã thuốc
        double giaTien = 0;
        try {
            giaTien = getDonGia(selectedMaThuoc);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Thanhtoan.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Cập nhật giá tiền vào JTextField
        double currentTotal = 0;
        try {
            currentTotal = Double.parseDouble(txtthanhtoan.getText());
        } catch (NumberFormatException e) {
            currentTotal = 0; // Nếu có lỗi, coi như giá tiền hiện tại là 0
        }

        // Cộng thêm giá tiền thuốc vào tổng
        currentTotal += giaTien;

        // Cập nhật lại JTextField
        txtthanhtoan.setText(String.valueOf(currentTotal));
    }//GEN-LAST:event_cbmtActionPerformed

    private void cbbhytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbhytActionPerformed

        BigDecimal tong = BigDecimal.ZERO;//khia báo BigDecima để chính xác hơn khi dùng double hay float vì fungf nó sẽ ko tính đc 70%, 85%
        try {
            tong = new BigDecimal(txtthanhtoan.getText());
        } catch (NumberFormatException e) {
            tong = BigDecimal.ZERO; // Nếu có lỗi, coi như giá tiền hiện tại là 0
        }

        String chonbhyt = (String) cbbhyt.getSelectedItem();
        BigDecimal giamgia = BigDecimal.ZERO; // Khởi tạo giamgia bằng 0

        if (chonbhyt != null) {
            switch (chonbhyt) {
                case "90%":
                giamgia = tong.multiply(BigDecimal.valueOf(0.9)); // Giảm 90%
                break;
                case "85%":
                giamgia = tong.multiply(BigDecimal.valueOf(0.85)); // Giảm 85%
                break;
                case "70%":
                giamgia = tong.multiply(BigDecimal.valueOf(0.7)); // Giảm 70%
                break;
            }
        }

        // Cập nhật lại JTextField với số tiền phải thanh toán sau khi giảm giá
        BigDecimal soTienPhaiTra = tong.subtract(giamgia); // Tính số tiền cuối cùng
        txtthanhtoan.setText(soTienPhaiTra.setScale(2, RoundingMode.HALF_UP).toString()); // Cập nhật số tiền cần thanh toán ,RoundingMode.HALF_UP để làm tròn
    }//GEN-LAST:event_cbbhytActionPerformed

    private void txttimkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttimkiemMouseClicked
        xoatrang();
    }//GEN-LAST:event_txttimkiemMouseClicked

    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased
        try {
            // Lấy mã bệnh nhân từ trường nhập liệu
            tbhd.removeAll();
            String mhs = txttimkiem.getText().trim();  // Mã bệnh nhân

            // Kết nối đến cơ sở dữ liệu
            con = BTL.Connect.KetnoiDB(); //dm chó tuấn
            Statement st = con.createStatement();

            // Xây dựng câu lệnh SQL cho tìm kiếm
            String sql = "SELECT * FROM DieuTriThanhToan WHERE MaHoSoXuatVien LIKE '%" + mhs + "%'";

            // Thực hiện truy vấn
            ResultSet rs = st.executeQuery(sql);
            String[] tieude = {"Mã hồ sơ", "Mã thuốc"," Liều lượng","Thanh toán"," Ngày thanh toán"};           DefaultTableModel tb = new DefaultTableModel(tieude, 0);
            tb.setRowCount(0);

            // Thêm dữ liệu vào bảng
            // Thêm dữ liệu vào bảng
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("MaHoSoXuatVien"));
                v.add(rs.getString("MaThuoc"));
                v.add(rs.getString("LieuLuong"));
                v.add(rs.getString("SoTien"));
                v.add(rs.getDate("NgayThanhToan").toString());
                tb.addRow(v);
            }

            // Cập nhật bảng hiển thị
            tbhd.setModel(tb);
            con.close();  // Đóng kết nối
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Tìm kiếm không thành công");
        }
    }//GEN-LAST:event_txttimkiemKeyReleased

    private void btthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthemActionPerformed
        // B1: Lấy dữ liệu từ các components và đưa vào biến
        String mhs = cbmhs.getSelectedItem().toString();
        String mt = cbmt.getSelectedItem().toString();
        String ll = txtll.getText().trim();
        String tt = txtthanhtoan.getText().trim();

        // Lấy ngày nhập viện và ngày xuất viện từ JDateChooser
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date ngay = new Date(dcngay.getDate().getTime());

        // B1.1: Kiểm tra các trường bắt buộc phải nhập
        if (mhs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã hồ sơ không được để trống.");
            cbmhs.requestFocus();
            return;
        }

        if (mt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã thuốc không được để trống.");
            cbmt.requestFocus();
            return;
        }

        if (ll.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Liều lượng không được để trống.");
            txtll.requestFocus();
            return;
        }

        if (tt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thanh toán không được để trống.");
            txtthanhtoan.requestFocus();
            return;
        }

        if (ngay == null) {
            JOptionPane.showMessageDialog(this, "Ngày thanh toán không được để trống.");
            dcngay.requestFocus();
            return;
        }

        // B2: Kết nối Database
        try {
            con = BTL.Connect.KetnoiDB();

            // B3: Tạo đối tượng Statement để thực hiện lệnh truy vấn
            String sql = "INSERT INTO DieuTriThanhToan (MaHoSoXuatVien, MaThuoc, LieuLuong, SoTien, NgayThanhToan) " +
            "VALUES ('" + mhs + "', '" + mt + "', N'" + ll + "', '" + tt + "', '" + format.format(ngay) + "')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);

            // Đóng kết nối
            con.close();
            load_hd(); // Cập nhật bảng hiển thị hồ sơ xuất viện
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
            String mhs = cbmhs.getSelectedItem().toString();  // Mã hồ sơ
            String mt = cbmt.getSelectedItem().toString();
            String ll = txtll.getText().trim();
            String tt = txtthanhtoan.getText().trim();

            // Lấy ngày điều trị từ JDateChooser
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date ngay = new Date(dcngay.getDate().getTime());

            // Kiểm tra nếu các trường không được để trống
            if (mhs.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống mã hồ sơ");
                return;
            }
            if (mt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống mã thuốc");
                return;
            }
            if (tt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống số tiền thanh toán");
                return;
            }
            if (ll.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống liều lượng");
                return;
            }
            if (ngay == null) {
                JOptionPane.showMessageDialog(this, "Không được để trống ngày thanh toán");
                return;
            }

            // Định dạng ngày điều trị thành kiểu chuỗi
            java.sql.Date sqlDate = new java.sql.Date(ngay.getTime());

            // Kết nối tới database
            con = BTL.Connect.KetnoiDB();

            // Câu lệnh SQL để cập nhật bản ghi trong bảng DieuTriThanhToan
            String sql = "UPDATE DieuTriThanhToan SET "
            + "MaThuoc = ?, "
            + "LieuLuong = ?, "
            + "SoTien = ?, "
            + "NgayThanhToan = ? "
            + "WHERE MaHoSoXuatVien = ?";

            // Sử dụng PreparedStatement để tránh SQL Injection
            PreparedStatement ps = con.prepareStatement(sql);

            // Gán các tham số
            ps.setString(1, mt);     // Gán mã thuốc
            ps.setString(2, ll);     // Gán liều lượng
            ps.setString(3, tt);     // Gán số tiền thanh toán
            ps.setDate(4, sqlDate);   // Gán ngày thanh toán
            ps.setString(5, mhs);     // Gán mã hồ sơ

            // Thực hiện câu lệnh cập nhật
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                load_hd();  // Tải lại dữ liệu sau khi cập nhật
                xoatrang();   // Xóa các trường nhập liệu
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu để sửa");
            }

            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Sửa không thành công: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btsuaActionPerformed

    private void btxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoaActionPerformed
        String mhs = (String) cbmhs.getSelectedItem(); // Lấy mã điều trị từ JComboBox
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BTL.Connect.KetnoiDB();  // Kết nối đến cơ sở dữ liệu
            String sql = "DELETE FROM DieuTriThanhToan WHERE MaHoSoXuatVien= ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, mhs);  // Đặt tham số MaDieuTri vào câu truy vấn

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
            load_hd();
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

    private void btxuathdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxuathdActionPerformed
        try {
            String mhs = cbmhs.getSelectedItem().toString().trim();
            //String mht = txthoten.getText().trim();

            Connection con = BTL.Connect.KetnoiDB();

            JasperDesign jdesign=JRXmlLoader.load("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Hosoxuatvien\\HoaDon.jrxml");

            String sql = "Select * From DieuTriThanhToan Where MaHoSoXuatVien like N'%"+mhs+"%'";
            JRDesignQuery updateQuery=new JRDesignQuery();
            updateQuery.setText(sql);

            jdesign.setQuery(updateQuery);
            JasperReport jreport=JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint=JasperFillManager.fillReport(jreport, null,con);
            JasperViewer.viewReport(jprint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btxuathdActionPerformed

//        public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Thanhtoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Thanhtoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Thanhtoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Thanhtoan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    new Thanhtoan().setVisible(true);
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(Thanhtoan.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btsua;
    private javax.swing.JButton btthem;
    private javax.swing.JButton btthoat;
    private javax.swing.JButton btxoa;
    private javax.swing.JButton btxuathd;
    private javax.swing.JComboBox<String> cbbhyt;
    private javax.swing.JComboBox<String> cbmhs;
    private javax.swing.JComboBox<String> cbmt;
    private com.toedter.calendar.JDateChooser dcngay;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbhd;
    private javax.swing.JTextField txtll;
    private javax.swing.JTextField txtthanhtoan;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
