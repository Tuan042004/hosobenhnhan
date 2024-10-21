/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package QLBN;
import BTL.Connect;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
 * @author dqduc
 */
public class QuanLyNhanVien extends javax.swing.JInternalFrame {

    /**
     * Creates new form QuanLyNhanVien
     */
    public QuanLyNhanVien() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        btluu.setEnabled(false);
        btsua.setEnabled(false);
        btxoa.setEnabled(false);
        initComponents();
        load_qtdt();
        load_cboTacgia();
    }
     Connection con;
    private void Thembenhnhan(String mnv, String tk, String ht, String ns, String gt, String dc, String sdt, String cccd) {
    try {
        con = BTL.Connect.KetnoiDB();
        String sql = "INSERT INTO NhanVienYTe ( MaNhanVien, TenKhoa,BacSiDieuTri ,NgaySinh,GioiTinh ,DiaChi ,SDT , CCCD) "
                   + "VALUES (N'" + mnv + "', N'" + tk + "', N'" + ht + "', '" + ns + "', N'" + gt + "', '" + dc + "', '" + sdt + "', '" + cccd + "')";

        Statement st = con.createStatement();
        st.executeUpdate(sql);
    } catch (Exception e) {
        e.printStackTrace();
    }
}   Map<String,String> tacgia = new HashMap<>();
    private void load_cboTacgia(){
        try{
            con = Connect.KetnoiDB();
            String sql = "Select * From Khoa";
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            //Đổ dữ liệu vào combobox
            while(rs.next()){
                cbokhoa.addItem(rs.getString("TenKhoa"));
                tacgia.put(rs.getString("TenKhoa"), rs.getString("MaKhoa"));
            }
            con.close();;
        }catch(Exception ex){
            ex.printStackTrace();
        }
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

                String tk = "";
                Cell cell2 = row.getCell(1);
                if (cell2 != null) {
                    if (cell2.getCellType() == CellType.STRING) {
                        tk = cell2.getStringCellValue().trim();
                    } else if (cell2.getCellType() == CellType.NUMERIC) {
                        tk = String.valueOf(cell2.getNumericCellValue()).trim();
                    }
                }

                String ht = "";
                Cell cell3 = row.getCell(2);
                if (cell3 != null) {
                    if (cell3.getCellType() == CellType.STRING) {
                        ht = cell3.getStringCellValue().trim();
                    } else if (cell3.getCellType() == CellType.NUMERIC) {
                        ht = new SimpleDateFormat("yyyy-MM-dd").format(cell3.getDateCellValue());
                    }
                }

                String ns = "";
                Cell cell4 = row.getCell(3);
                if (cell4 != null) {
                    if (cell4.getCellType() == CellType.STRING) {
                        ns = cell4.getStringCellValue().trim();
                    } else if (cell4.getCellType() == CellType.NUMERIC) {
                        ns = String.valueOf(cell4.getNumericCellValue()).trim();
                    }
                }
                
                String gt = "";
                Cell cell5 = row.getCell(4);
                if (cell5 != null) {
                    if (cell5.getCellType() == CellType.STRING) {
                        gt = cell5.getStringCellValue().trim();
                    } else if (cell5.getCellType() == CellType.NUMERIC) {
                        gt = String.valueOf(cell5.getNumericCellValue()).trim();
                    }
                }
                
                String dc = "";
                Cell cell6 = row.getCell(5);
                if (cell6 != null) {
                    if (cell6.getCellType() == CellType.STRING) {
                        dc = cell6.getStringCellValue().trim();
                    } else if (cell6.getCellType() == CellType.NUMERIC) {
                        dc = String.valueOf(cell6.getNumericCellValue()).trim();
                    }
                }
                String sdt = "";
                Cell cell7 = row.getCell(6);
                if (cell7 != null) {
                    if (cell7.getCellType() == CellType.STRING) {
                        sdt = cell7.getStringCellValue().trim();
                    } else if (cell7.getCellType() == CellType.NUMERIC) {
                        sdt = String.valueOf(cell7.getNumericCellValue()).trim();
                    }
                }
                String cccd = "";
                Cell cell8 = row.getCell(7);
                if (cell8 != null) {
                    if (cell8.getCellType() == CellType.STRING) {
                        cccd = cell8.getStringCellValue().trim();
                    } else if (cell8.getCellType() == CellType.NUMERIC) {
                        cccd = String.valueOf(cell8.getNumericCellValue()).trim();
                    }
                }
                Thembenhnhan(mnv,tk,  ht,  ns,  gt,  dc,  sdt,  cccd);
            }
            row_count++;
        }
        JOptionPane.showMessageDialog(this, "Thêm nhân viên bằng file thành công");
        load_qtdt();
    } catch (Exception e) {
        e.printStackTrace();
    }
        

    }
    private void load_qtdt(){
        try {
            tbqlbn.removeAll();
            //B1: Kết nối đến DB
            con= BTL.Connect.KetnoiDB();
            //B2: Tạo đối tượng Statement để thực hiện câu lệnh truy cập
            String sql = "Select * From NhanVienYTe";
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String[] tieude={ "Mã nhân viên","Họ và tên", "Khoa","Ngày sinh","Giới tính","Địa chỉ","Số điện thoại", "CCCD",};
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
                v.add(rs.getString("BacSiDieuTri"));
                 v.add(rs.getString("TenKhoa"));
                v.add(rs.getString("NgaySinh"));
                v.add(rs.getString("GioiTinh"));
                v.add(rs.getString("DiaChi"));
                v.add(rs.getString("SDT"));
                v.add(rs.getString("CCCD"));
               
                tb.addRow(v);
            }
            tbqlbn.setModel(tb);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void xoatrang() {
    txthoten.setText("");     
    txtmnv.setText("");       
    dcngaysinh.setDate(null); 
    cbogioitinh.setSelectedIndex(0); 
    txtdiachi.setText("");    
    txtsdt.setText("");      
    txtcccd.setText("");       
    cbokhoa.setSelectedIndex(0);      
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
        txtTimkiem = new javax.swing.JTextField();
        bttimkiem = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txthoten = new javax.swing.JTextField();
        txtmnv = new javax.swing.JTextField();
        dcngaysinh = new com.toedter.calendar.JDateChooser();
        txtdiachi = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        cbogioitinh = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtcccd = new javax.swing.JTextField();
        cbokhoa = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbqlbn = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btluu = new javax.swing.JButton();
        btsua = new javax.swing.JButton();
        btxoa = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnhapexcel = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(980, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Nhân Viên");

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

        jLabel8.setText("Mã Nhân Viên");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bttimkiem)
                .addGap(38, 38, 38))
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

        jLabel2.setText("Mã Nhân Viên");

        jLabel3.setText("Họ và tên");

        jLabel4.setText("Ngày sinh");

        jLabel5.setText("Giới tính");

        jLabel6.setText("Địa chỉ");

        jLabel7.setText("Số điện thoại");

        txtmnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmnvActionPerformed(evt);
            }
        });

        dcngaysinh.setDateFormatString("yyyy-MM-dd");

        cbogioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn giới tính", "Nam", "Nữ", "Khác" }));
        cbogioitinh.setPreferredSize(new java.awt.Dimension(64, 22));
        cbogioitinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbogioitinhActionPerformed(evt);
            }
        });

        jLabel9.setText("CCCD");

        jLabel10.setText("Tên Khoa");

        cbokhoa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn khoa" }));
        cbokhoa.setPreferredSize(new java.awt.Dimension(64, 22));
        cbokhoa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbokhoaItemStateChanged(evt);
            }
        });
        cbokhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbokhoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtmnv, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txthoten, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcngaysinh, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtdiachi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(cbogioitinh, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtsdt))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtcccd, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(cbokhoa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtcccd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cbokhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbogioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtmnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(dcngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tbqlbn.setForeground(new java.awt.Color(0, 153, 204));
        tbqlbn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "title1", "title2", "title3", "title4", "title5", "title6", "Title 7", "Title 8"
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
        );

        btluu.setText("Lưu");
        btluu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btluuActionPerformed(evt);
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

        btnhapexcel.setText("Nhập excel");
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
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(15, 15, 15)
                .addComponent(btluu)
                .addGap(18, 18, 18)
                .addComponent(btsua)
                .addGap(12, 12, 12)
                .addComponent(btxoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(btnhapexcel)
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addGap(167, 167, 167)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btluu)
                    .addComponent(btsua)
                    .addComponent(btxoa)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton1)
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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

    private void txtTimkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimkiemMouseClicked
        xoatrang();
    }//GEN-LAST:event_txtTimkiemMouseClicked

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased

    }//GEN-LAST:event_txtTimkiemKeyReleased

    private void bttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttimkiemActionPerformed
        try{

            String mbn = txtTimkiem.getText().trim();
            con = BTL.Connect.KetnoiDB();
            String sql = "Select * From NhanVienYTe Where MaNhanVien like'%"+mbn+"%'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String[] tieude={ "Mã nhân viên","Họ và tên", "Khoa","Ngày sinh","Giới tính","Địa chỉ","Số điện thoại", "CCCD",};
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
                v.add(rs.getString("BacSiDieuTri"));
                v.add(rs.getString("TenKhoa"));
                v.add(rs.getString("NgaySinh"));
                v.add(rs.getString("GioiTinh"));
                v.add(rs.getString("DiaChi"));
                v.add(rs.getString("SDT"));
                v.add(rs.getString("CCCD"));

                tb.addRow(v);
            }
            tbqlbn.setModel(tb);
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_bttimkiemActionPerformed

    private void txtmnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmnvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmnvActionPerformed

    private void cbogioitinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbogioitinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbogioitinhActionPerformed

    private void cbokhoaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbokhoaItemStateChanged
        // TODO add your handling code here:
        String khoa = cbokhoa.getSelectedItem().toString();
    }//GEN-LAST:event_cbokhoaItemStateChanged

    private void cbokhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbokhoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbokhoaActionPerformed

    private void tbqlbnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbqlbnMouseClicked
        
        btsua.setEnabled(true);
        btxoa.setEnabled(true);
        int i=tbqlbn.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel)tbqlbn.getModel();
        txthoten.setText(tb.getValueAt(i, 1).toString());
        txtmnv.setText(tb.getValueAt(i, 0).toString());
        String ngay=tb.getValueAt(i, 3).toString();
        txtdiachi.setText(tb.getValueAt(i, 5).toString());
        txtsdt.setText(tb.getValueAt(i, 6).toString());
        txtcccd.setText(tb.getValueAt(i, 7).toString());
        cbokhoa.setSelectedItem(tb.getValueAt(i, 2).toString());
        cbogioitinh.setSelectedItem(tb.getValueAt(i, 4).toString());
        txtmnv.setEnabled(false);
        java.util.Date ngs;
        try {
            ngs = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            dcngaysinh.setDate(ngs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_tbqlbnMouseClicked

    private void btluuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btluuActionPerformed
        // B1: lấy dữ liệu các components đưa vào biến
        
        String mht = txthoten.getText().trim();
        String mnv = txtmnv.getText().trim();
        String gioitinh = cbogioitinh.getSelectedItem().toString().trim();
        String dc = txtdiachi.getText().trim();
        String sdt = txtsdt.getText().trim();
        String cccd = txtcccd.getText().trim();
        String khoa = cbokhoa.getSelectedItem().toString().trim();

        // B1.1: Kiểm tra các trường bắt buộc phải nhập

        if (mnv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống.");
            txtmnv.requestFocus();
            return;
        }
        if (mht.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống.");
            txthoten.requestFocus();
            return;
        }
        SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
        Date ns = null;
        try {
            ns = new Date(dcngaysinh.getDate().getTime());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Phải nhập ngày sinh hợp lệ!");
            dcngaysinh.requestFocus();
            return;
        }
        if (ns == null) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống.");
            dcngaysinh.requestFocus();
            return;
        }

        String gt = cbogioitinh.getSelectedItem().toString();
        if (gt.equals("Chọn giới tính")) {
            gt = "";
        }
        if (gt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải nhập giới tính!");
            return;
        }

        if (dc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống.");
            txtdiachi.requestFocus();
            return;
        }

        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống.");
            txtsdt.requestFocus();
            return;
        }
        if (cccd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số CCCD không được để trống.");
            txtcccd.requestFocus();
            return;
        }

        if (khoa.equals("Chọn khoa")) {
            khoa = "";
        }
        if (khoa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phải nhập khoa!");
            return;
        }

        // Kiểm tra định dạng số điện thoại
        String regex_dt = "(84|0[3|5|7|8|9])+([0-9]{8})";
        Pattern pattern = Pattern.compile(regex_dt);
        Matcher matcher = pattern.matcher(sdt);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Nhập sai số điện thoại!");
            txtsdt.requestFocus();
            return;
        }

        // B2: Kết nối Database
        try {
            con = BTL.Connect.KetnoiDB();
            // B3: Tạo đối tượng Statement để thực hiện lệnh truy vấn
            String sql = "INSERT INTO NhanVienYTe ( MaNhanVien, TenKhoa,BacSiDieuTri ,NgaySinh,GioiTinh ,DiaChi ,SDT , CCCD) "
            + "VALUES (N'" + mnv + "', N'" + khoa + "', N'" + mht + "', '" + ns + "', N'" + gt + "', '" + dc + "', '" + sdt + "', '" + cccd + "')";
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
    }//GEN-LAST:event_btluuActionPerformed

    private void btsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsuaActionPerformed
        try {
            String mht = txthoten.getText(); // Get BacSiDieuTri (Họ tên)
            if (mht.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phải nhập họ và tên!");
                return;
            }

            String mbn = txtmnv.getText(); // Get MaNhanVien
            if (mbn.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phải nhập mã nhân viên!");
                return;
            }

            Date ns = new Date(dcngaysinh.getDate().getTime()); // Get NgaySinh
            if (ns == null) {
                JOptionPane.showMessageDialog(this, "Phải nhập ngày sinh!");
                return;
            }

            String gioitinh = cbogioitinh.getSelectedItem().toString(); // Get GioiTinh
            if (gioitinh.equals("Chọn giới tính")) {
                gioitinh = "";
            }
            if (gioitinh.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phải nhập giới tính!");
                return;
            }

            String dc = txtdiachi.getText(); // Get DiaChi
            if (dc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phải nhập địa chỉ!");
                return;
            }

            String sdt = txtsdt.getText(); // Get SDT
            if (sdt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phải nhập số điện thoại!");
                return;
            }

            String khoa = cbokhoa.getSelectedItem().toString(); // Get TenKhoa
            if (khoa.equals("Chọn khoa")) {
                khoa = "";
            }
            if (khoa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Phải nhập khoa!");
                return;
            }

            // Connect to the database
            con = BTL.Connect.KetnoiDB();

            // Format the date
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            // Update query to modify NhanVienYTe record
            String sql = "UPDATE NhanVienYTe SET TenKhoa=N'" + khoa +
            "', BacSiDieuTri=N'" + mht + "', NgaySinh='" + format.format(ns) +
            "', GioiTinh=N'" + gioitinh + "', DiaChi=N'" + dc +
            "', SDT='" + sdt + "' WHERE MaNhanVien='" + mbn + "'";

            // Execute the update
            Statement st = con.createStatement();
            st.executeUpdate(sql);

            // Close the connection
            con.close();
            load_qtdt();
            // Notify the user of success
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin nhân viên y tế thành công!");

            // Clear input fields or refresh data
            xoatrang();

        } catch (Exception e) {
            // Handle any errors during the process
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật dữ liệu: " + e.getMessage());
        }
    }//GEN-LAST:event_btsuaActionPerformed

    private void btxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoaActionPerformed
        try {
            String mnv = txtmnv.getText().trim(); // Get MaNhanVien from input
            // Confirm deletion
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                // Connect to the database
                con = BTL.Connect.KetnoiDB();

                // Now, delete the record from NhanVienYTe
                String deleteNhanVienSql = "DELETE FROM NhanVienYTe WHERE MaNhanVien = '" + mnv + "'";
                Statement deleteStmt = con.createStatement();
                deleteStmt.executeUpdate(deleteNhanVienSql);

                // Close the connection
                con.close();

                // Notify user of successful deletion
                JOptionPane.showMessageDialog(this, "Xoá thành công");

                // Reload the data and clear input fields
                load_qtdt();
                xoatrang();
            } else {
                JOptionPane.showMessageDialog(this, "Không xoá nữa");
            }
        } catch (Exception ex) {
            // Handle any exceptions that occur during the process
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xoá: " + ex.getMessage());
        }
    }//GEN-LAST:event_btxoaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        xoatrang();
        txtmnv.setEnabled(true);
        btluu.setEnabled(true);
        btsua.setEnabled(false);
        btxoa.setEnabled(false);
        load_qtdt();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

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
            cell.setCellValue("DANH SÁCH NHÂN VIÊN");

            CellStyle cellStyle_Head = DinhdangHeader(spreadsheet);
            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã nhân viên");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Họ và tên");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Khoa");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Ngày sinh");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Giới tính");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Địa chỉ");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Số điện thoại");

            cell = row.createCell(8, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("CCCD");
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
                cell.setCellValue(rs.getString("BacSiDieuTri"));

                cell = row.createCell(3);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("TenKhoa"));

                cell = row.createCell(4);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("NgaySinh"));

                cell = row.createCell(5);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("GioiTinh"));

                cell = row.createCell(6);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("DiaChi"));

                cell = row.createCell(7);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("SDT"));

                cell = row.createCell(8);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("CCCD"));

                i++;
            }
            //Hiệu chỉnh độ rộng của cột
            for (int col = 0; col < tongsocot; col++) {
                spreadsheet.autoSizeColumn(col);
            }

            File f = new File("C:\\Users\\dqduc\\OneDrive\\Documents\\Java\\hosobenhnhan\\src\\main\\java\\QLBN\\NhanVien.xlsx");
            FileOutputStream out = new FileOutputStream(f);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnhapexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapexcelActionPerformed
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
    }//GEN-LAST:event_btnhapexcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btluu;
    private javax.swing.JButton btnhapexcel;
    private javax.swing.JButton btsua;
    private javax.swing.JButton bttimkiem;
    private javax.swing.JButton btxoa;
    private javax.swing.JComboBox<String> cbogioitinh;
    private javax.swing.JComboBox<String> cbokhoa;
    private com.toedter.calendar.JDateChooser dcngaysinh;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbqlbn;
    private javax.swing.JTextField txtTimkiem;
    private javax.swing.JTextField txtcccd;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txthoten;
    private javax.swing.JTextField txtmnv;
    private javax.swing.JTextField txtsdt;
    // End of variables declaration//GEN-END:variables
}
