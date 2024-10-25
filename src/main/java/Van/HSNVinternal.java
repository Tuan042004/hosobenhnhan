/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Van;


import BTL.Connect;
import Van.NewJFrame;
import Van.Sua;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
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
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
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
public class HSNVinternal extends javax.swing.JInternalFrame {
    private NewJFrame f1;
    /**
     * Creates new form HSNVinternal
     */
    public HSNVinternal() throws ClassNotFoundException {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        initComponents();
        load_disable();
        load_hsnv();
        loadcbo();
        load_bn();
    }
    public HSNVinternal(NewJFrame f1){
        this.f1 = f1;
        initComponents();
    }
    private void load_disable(){
        txtmhs.setEnabled(false);
        cbmbn.setEnabled(false);
        txtten.setEnabled(false);
        dcnnv.setEnabled(false);
        txtcd.setEnabled(false);
        cbmp.setEnabled(false);
        cbmg.setEnabled(false);
        cbmk.setEnabled(false);
        cbtk.setEnabled(false);
        
    }
    Connection con;

    void addRowToTable(Object[] rowData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public class Form2 {
    private NewJFrame form1;
    private DefaultTableModel model;

    public Form2(NewJFrame form1) {
        this.form1 = form1;  // Lưu tham chiếu đến form1
        model = (DefaultTableModel) tbhs.getModel();
    }

    public void addRowToTable(Object[] rowData) {
        model.addRow(rowData);  // Thêm dữ liệu vào bảng trong form2
    }
}

    HSNVinternal(NewJFrame.Form1 aThis) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void load_hsnv(){
        try {
    // Làm sạch bảng trước khi thêm dữ liệu mới
    tbhs.removeAll();

    // B1: Kết nối đến DB
    con = Connect.KetnoiDB();

    // B2: Tạo đối tượng Statement để thực hiện câu lệnh truy cập
    String sql = "SELECT * from HoSoNhapVien";
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);

    // Định nghĩa tiêu đề cho bảng
    String[] tieude = { "Mã hồ sơ", "Mã bệnh nhân", "Tên bệnh nhân", "Ngày nhập viện", "Chẩn đoán", "Mã phòng", "Mã giường", "Mã khoa", "Tên khoa" };

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
        v.add(rs.getString("HoTenBenhNhan"));   // Họ Tên Bệnh Nhân
        v.add(rs.getDate("NgayNhapVien").toString()); // Ngày nhập viện        
        v.add(rs.getString("ChanDoan")); //chẩn đoán bệnh
        v.add(rs.getString("MaPhong"));                  // Mã phòng
        v.add(rs.getString("MaGiuong"));                 // mã giường
        v.add(rs.getString("MaKhoa"));            // mã khoa
        v.add(rs.getString("TenKhoa"));                // tên khoa
        tb.addRow(v);
    }  

    // Đóng kết nối
    tbhs.setModel(tb);
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
     public void xoatrang() {
        txtmhs.setText("");
        cbmbn.setSelectedItem(null);  // Xóa lựa chọn trong JComboBox
        cbmk.setSelectedItem(null);
        cbtk.setSelectedItem(null);
        cbmg.setSelectedItem(null);
        cbmp.setSelectedItem(null);
        dcnnv.setDate(null);   
        txtcd.setText("");
        txtten.setText("");
        txtmhs.setEnabled(true);  // Mở lại trường mã hồ sơ để người dùng nhập
        cbmbn.setEnabled(true);  // Mở lại JComboBox mã bệnh nhân để người dùng chọn

    }
     public void Themhoso(String mhs, String mbn, String tbn, String nnv, String cd, String mp,String mg, String mk, String tk) {
        try {
            con =Connect.KetnoiDB();
            String sql = "INSERT INTO HoSoNhapVien (MaHoSoNhapVien, MaBenhNhan, HoTenBenhNhan, NgayNhapVien, ChanDoan, MaPhong, MaGiuong, MaKhoa, TenKhoa) "
                       + "VALUES ('" + mhs + "', '" + mbn + "', N'" + tbn + "', '" + nnv + "', N'" + cd + "', '" + mp + "', '" + mg + "',  '" + mk + "', N'" + tk + "')";

            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

            
         public void ReadExcel(String tenfilepath) {
   try {
    FileInputStream fis = new FileInputStream(tenfilepath);
    XSSFWorkbook wb = new XSSFWorkbook(fis);
    XSSFSheet sheet = wb.getSheetAt(0); // Lấy sheet đầu tiên
    Iterator<Row> itr = sheet.iterator();
    int row_count = 0;

    while (itr.hasNext()) {
        Row row = itr.next();
        if (row_count > 0) { // Bỏ qua dòng tiêu đề
            // Đọc mã hồ sơ
            String mhs = getCellValueAsString(row.getCell(0));
            if (mhs.isEmpty()) { 
                row_count++;
                continue; // Bỏ qua nếu mã hồ sơ rỗng
            }

            String maBenhNhan = getCellValueAsString(row.getCell(1));
            String tenBenhNhan = getCellValueAsString(row.getCell(2));

            // Đọc và định dạng ngày nhập viện
            String ngayNhapVien = "";
            Cell cellNgay = row.getCell(3);
            if (cellNgay != null) {
                if (cellNgay.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cellNgay)) {
                    // Nếu là kiểu ngày, format sang "yyyy-MM-dd"
                    ngayNhapVien = new SimpleDateFormat("yyyy-MM-dd").format(cellNgay.getDateCellValue());
                } else {
                    ngayNhapVien = cellNgay.toString().trim(); // Trường hợp khác (chuỗi)
                }
            }

            String chandoan = getCellValueAsString(row.getCell(4));
            String map = getCellValueAsString(row.getCell(5));
            String mag = getCellValueAsString(row.getCell(6));
            String maKhoa = getCellValueAsString(row.getCell(7));
            String tenKhoa = getCellValueAsString(row.getCell(8));

            // Gọi phương thức thêm hồ sơ
            Themhoso(mhs, maBenhNhan, tenBenhNhan, ngayNhapVien, chandoan, map, mag, maKhoa, tenKhoa);
        }
        row_count++;
    }

    JOptionPane.showMessageDialog(this, "Thêm hồ sơ nhập  viện bằng file thành công");
    load_hsnv(); // Tải lại dữ liệu
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Lỗi khi đọc file: " + e.getMessage());
}
         }
// Hàm tiện ích để lấy giá trị của ô dưới dạng chuỗi
         private String getCellValueAsString(Cell cell) {
    if (cell == null) {
        return "";
    }
    switch (cell.getCellType()) {
        case STRING:
            return cell.getStringCellValue().trim();
        case NUMERIC:
            // Kiểm tra nếu là số nguyên (thay vì số thực)
            if (DateUtil.isCellDateFormatted(cell)) {
                return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
            } else {
                return String.valueOf((int) cell.getNumericCellValue()).trim();
            }
        default:
            return cell.toString().trim();
    }
}

         
         Map<String,String> benhnhan = new HashMap<>();
         public void load_bn(){
             try{
            con = Connect.KetnoiDB();
            String sql = "Select * From BenhNhan";
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //Đổ dữ liệu vào combobox
            while(rs.next()){
                cbmbn.addItem(rs.getString("MaBenhNhan"));
                benhnhan.put(rs.getString("MaBenhNhan"), rs.getString("HoTenBenhNhan"));
            }
//            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
         }
         public void loadcbo() throws ClassNotFoundException {
    try {
        con = Connect.KetnoiDB();
//        String query = "SELECT MaBenhNhan FROM BenhNhan"; // Thay đổi truy vấn để lấy mã bệnh nhân
//        Statement statement = con.createStatement();
//        ResultSet rs = statement.executeQuery(query);
//        while (rs.next()) {
//            cbmbn.addItem(rs.getString("MaBenhNhan")); // Thêm mã bệnh nhân vào ComboBox
//        }
        
    // Lấy TenKhoa từ bảng Khoa
    String queryKhoa = "SELECT MaKhoa FROM Khoa";
    Statement statementKhoa = con.createStatement();
    ResultSet rsKhoa = statementKhoa.executeQuery(queryKhoa);
    while (rsKhoa.next()) {
        cbmk.addItem(rsKhoa.getString("MaKhoa")); // thêm mã khoa vào combo box
    }
        
    // Lấy TenKhoa từ bảng Khoa
    String queryTenKhoa = "SELECT TenKhoa FROM Khoa";
    Statement statementTenKhoa = con.createStatement();
    ResultSet rsTenKhoa = statementTenKhoa.executeQuery(queryTenKhoa);
    while (rsTenKhoa.next()) {
        cbtk.addItem(rsTenKhoa.getString("TenKhoa")); //thêm tên khoa vào combo box
    }
    
            // Lấy MaPhong từ bảng PhongBenh
    String queryPhong = "SELECT MaPhong FROM PhongBenh";
    Statement statementNhanVien = con.createStatement();
    ResultSet rsPhong = statementNhanVien.executeQuery(queryPhong);
    while (rsPhong.next()) {
        cbmp.addItem(rsPhong.getString("MaPhong")); //thêm mã phòng vào combo box
    }
    
     // Lấy MaGiuong từ bảng Giường
    String queryGiuong = "SELECT MaGiuong FROM Giuong";
    Statement statementGiuong = con.createStatement();
    ResultSet rsGiuong = statementGiuong.executeQuery(queryGiuong);
    while (rsGiuong.next()) {
        cbmg.addItem(rsGiuong.getString("MaGiuong")); //thêm mã giư vào combo box
    }
    
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Lỗi tải danh mục: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        jLabel11 = new javax.swing.JLabel();
        txtten = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tkmbn = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btthoat = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1010, 600));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin hồ sơ nhập viện"));

        jLabel4.setText("Mã hồ sơ:");

        jLabel3.setText("Mã bệnh nhân: ");

        txtmhs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmhsActionPerformed(evt);
            }
        });

        jLabel5.setText("Ngày nhập viện: ");

        jLabel6.setText("Mã Phòng:");

        dcnnv.setDateFormatString("yyyy-MM-dd");

        tbhs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Hồ Sơ", "Mã Bệnh Nhân", "Tên Bệnh Nhân", "Ngày Nhập Viện", "Chẩn Đoán", "Mã Phòng", "Mã Giường", "Mã Khoa", "Tên Khoa"
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

        cbmp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmpItemStateChanged(evt);
            }
        });

        jLabel7.setText("Mã Giường:");

        cbmg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmgItemStateChanged(evt);
            }
        });

        jLabel8.setText("Mã Khoa:");

        cbmk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmkItemStateChanged(evt);
            }
        });

        jLabel9.setText("Tên Khoa:");

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

        cbmbn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbmbnItemStateChanged(evt);
            }
        });
        cbmbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmbnActionPerformed(evt);
            }
        });

        jLabel11.setText("Họ Tên:");

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
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbmp, 0, 177, Short.MAX_VALUE)
                                    .addComponent(txtmhs)))
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
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbmbn, 0, 180, Short.MAX_VALUE)
                            .addComponent(cbmg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtcd))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbmk, 0, 185, Short.MAX_VALUE)
                            .addComponent(cbtk, 0, 185, Short.MAX_VALUE)
                            .addComponent(txtten))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(dcnnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtcd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11))
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(90, Short.MAX_VALUE))
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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Hồ Sơ Nhập Viện");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton5.setText("Xuất Excel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setText("Thao tác");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Xuất báo cáo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Nhập Excel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Load");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Cập nhật");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addGap(91, 91, 91)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btthoat, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton6)
                    .addComponent(btthoat))
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtmhsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmhsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmhsActionPerformed

    private void tbhsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbhsMouseClicked
        load_disable();
        int i = tbhs.getSelectedRow();
        DefaultTableModel tb = (DefaultTableModel) tbhs.getModel();
        txtmhs.setText(tb.getValueAt(i, 0).toString()); //gán giá trị cho textfield mã hồ sơ
        cbmbn.setSelectedItem(tb.getValueAt(i, 1).toString());//gán giá trị cho combobox mã bệnh nhân
        txtten.setText(tb.getValueAt(i, 2).toString());
        String nnv = tb.getValueAt(i, 3).toString();//gán giá trị cho datechooser ngày nhập viện
        java.util.Date ng;
        try {
            ng = new SimpleDateFormat("yyyy-MM-dd").parse(nnv);
            dcnnv.setDate(ng);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        txtcd.setText(tb.getValueAt(i, 4).toString());//gán giá trị cho tf chẩn đoán
        cbmp.setSelectedItem(tb.getValueAt(i, 5).toString());//gán giá trị cho cbb mã phòng
        cbmg.setSelectedItem(tb.getValueAt(i, 6).toString());//gán giá trị cho cbb mã giường
        cbmk.setSelectedItem(tb.getValueAt(i, 7).toString());//gán giá trị cho cbb mã khoa
        cbtk.setSelectedItem(tb.getValueAt(i, 8).toString());//gán giá trị cho cbb tên khoa
        // Sua s = null;
        //        try {
            //            s = new Sua();
            //        } catch (ClassNotFoundException ex) {
            //            Logger.getLogger(HSNV.class.getName()).log(Level.SEVERE, null, ex);
            //        }
        // s.setVisible(true);
    }//GEN-LAST:event_tbhsMouseClicked

    private void tbhsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbhsMouseEntered

    }//GEN-LAST:event_tbhsMouseEntered

    private void cbmpItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmpItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmpItemStateChanged

    private void cbmgItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmgItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmgItemStateChanged

    private void cbmkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmkItemStateChanged
        // TODO add your handling code here:;
    }//GEN-LAST:event_cbmkItemStateChanged

    private void cbtkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbtkItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbtkItemStateChanged

    private void txtcdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcdActionPerformed

    private void cbmbnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbmbnItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cbmbnItemStateChanged

    private void cbmbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmbnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmbnActionPerformed

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
            String[] tieude = {"Mã Hồ Sơ", "Mã Bệnh Nhân","Tên Bệnh Nhân", "Ngày Nhập Viện","Chẩn Đoán","Mã Phòng","Mã Giường","Mã Khoa","Tên Khoa"};
            DefaultTableModel tb = new DefaultTableModel(tieude, 0);
            tb.setRowCount(0);

            // Duyệt qua kết quả và thêm vào bảng
            while (rs.next()) {
                Vector<String> v = new Vector<>();
                v.add(rs.getString("MaHoSoNhapVien")); // Mã hồ sơ nhập viện
                v.add(rs.getString("MaBenhNhan"));    // Mã bệnh nhân
                v.add(rs.getString("HoTenBenhNhan"));
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
            cell.setCellValue("Tên bệnh nhân");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Ngày nhập viện");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Chẩn đoán");

            cell = row.createCell(6, CellType.STRING);
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
                cell.setCellValue(rs.getString("HoTenBenhNhan"));

                cell = row.createCell(4);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("NgayNhapVien"));

                cell = row.createCell(5);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("ChanDoan"));

                cell = row.createCell(6);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("MaPhong"));

                cell = row.createCell(7);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("MaGiuong"));

                cell = row.createCell(8);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("MaKhoa"));

                cell = row.createCell(9);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("TenKhoa"));

                i++;
            }
            //Hiệu chỉnh độ rộng của cột
            for (int col = 0; col < tongsocot; col++) {
                spreadsheet.autoSizeColumn(col);
            }

            File f = new File("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Van\\HSNV.xlsx");
            FileOutputStream out = new FileOutputStream(f);
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(this, "Xuất excel thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //               xoatrang();
        //close();
        NewJFrame an = null;
        try {
            an = new NewJFrame();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HSNVinternal.class.getName()).log(Level.SEVERE, null, ex);
        }
        an.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            String mhs = txtmhs.getText().trim();
            //String mht = txthoten.getText().trim();

            Connection con = Connect.KetnoiDB();

            JasperDesign jdesign=JRXmlLoader.load("C:\\Users\\Admin\\Documents\\NetBeansProjects\\hosobenhnhann\\src\\main\\java\\Van\\hsnv.jrxml");

            String sql = "Select * From HoSoNhapVien Where MaHoSoNhapVien like N'%"+mhs+"%'";
            JRDesignQuery updateQuery=new JRDesignQuery();
            updateQuery.setText(sql);

            jdesign.setQuery(updateQuery);
            JasperReport jreport=JasperCompileManager.compileReport(jdesign);
            JasperPrint jprint=JasperFillManager.fillReport(jreport, null,con);
            JasperViewer.viewReport(jprint);
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
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
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        load_hsnv();
        xoatrang();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        Sua s = null;
        try {
            s = new Sua();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HSNVinternal.class.getName()).log(Level.SEVERE, null, ex);
        }
        s. setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btthoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthoatActionPerformed
        dispose();
    }//GEN-LAST:event_btthoatActionPerformed
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
            java.util.logging.Logger.getLogger(HSNVinternal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HSNVinternal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HSNVinternal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HSNVinternal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
               try{ new HSNVinternal().setVisible(true);
                } catch (ClassNotFoundException ex){
                    Logger.getLogger(HSNVinternal.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btthoat;
    private javax.swing.JComboBox<String> cbmbn;
    private javax.swing.JComboBox<String> cbmg;
    private javax.swing.JComboBox<String> cbmk;
    private javax.swing.JComboBox<String> cbmp;
    private javax.swing.JComboBox<String> cbtk;
    private com.toedter.calendar.JDateChooser dcnnv;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbhs;
    private javax.swing.JTextField tkmbn;
    private javax.swing.JTextField txtcd;
    private javax.swing.JTextField txtmhs;
    private javax.swing.JTextField txtten;
    // End of variables declaration//GEN-END:variables
}
