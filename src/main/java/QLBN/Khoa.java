/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
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
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author dqduc
 */
public class Khoa extends javax.swing.JFrame {

    /**
     * Creates new form Khoa
     */
    public Khoa() {
        initComponents();
        load_qtdt();
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
    Connection con;
    private void Thembenhnhan(String mk, String tk) {
    try {
        con = BTL.Connect.KetnoiDB();
        String sql = "INSERT INTO Khoa ( MaKhoa,TenKhoa) "
                   + "VALUES (N'" + mk + "', '" + tk + "')";

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
                String mk = "";
                Cell cell1 = row.getCell(0);
                if (cell1 != null) {
                    if (cell1.getCellType() == CellType.STRING) {
                        mk = cell1.getStringCellValue().trim();
                    } else if (cell1.getCellType() == CellType.NUMERIC) {
                        mk = String.valueOf((int) cell1.getNumericCellValue());
                    }
                }

                // Kiểm tra xem mbn có phải là chuỗi rỗng không
                if (mk.isEmpty()) {
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


                // Gọi phương thức thêm bệnh nhân
                Thembenhnhan(mk, tk);
            }
            row_count++;
        }
        JOptionPane.showMessageDialog(this, "Thêm bệnh nhân bằng file thành công");
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
            String sql = "Select * From Khoa";
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String[] tieude={ "Mã Khoa","Tên Khoa"};
            DefaultTableModel tb=new DefaultTableModel(tieude,0)    {           
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        // Tất cả các ô sẽ không thể chỉnh sửa
                        return false;
                    }
                    };
            
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("MaKhoa"));
                v.add(rs.getString("TenKhoa"));
                tb.addRow(v);
            }
            tbqlbn.setModel(tb);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void xoatrang() {
    txthoten.setText("");      // Xóa trường họ tên
    txtmbn.setText("");        // Xóa trường mã bệnh nhân

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
        txthoten = new javax.swing.JTextField();
        txtmbn = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbqlbn = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btthem = new javax.swing.JButton();
        btsua = new javax.swing.JButton();
        btxoa = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnhapexcel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản Lý Bệnh Nhân");

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

        jLabel8.setText("Mã Khoa");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bttimkiem)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttimkiem)
                    .addComponent(jLabel8))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chi tiết"));

        jLabel2.setText("Mã khoa");

        jLabel3.setText("Tên khoa");

        txtmbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmbnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmbn, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(176, 176, 176))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(txtmbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tbqlbn.setForeground(new java.awt.Color(0, 153, 204));
        tbqlbn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "title1", "title2"
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addContainerGap())
        );

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
                .addComponent(btthem)
                .addGap(18, 18, 18)
                .addComponent(btsua)
                .addGap(12, 12, 12)
                .addComponent(btxoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
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
                    .addComponent(btthem)
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

    private void txtTimkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimkiemMouseClicked
        xoatrang();
    }//GEN-LAST:event_txtTimkiemMouseClicked

    private void txtTimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimkiemKeyReleased

    }//GEN-LAST:event_txtTimkiemKeyReleased

    private void bttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttimkiemActionPerformed
        try{
            //lấy dữ liệu từ compoment đưa vài biến
            String mk = txtTimkiem.getText().trim();
            con = BTL.Connect.KetnoiDB();
            String sql = "Select * From Khoa Where MaKhoa like'%"+mk+"%'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String[] tieude={"Mã Khoa", "Tên Khoa"};
            DefaultTableModel tb=new DefaultTableModel(tieude,0)    {
                @Override
                public boolean isCellEditable(int row, int column) {
                    // Tất cả các ô sẽ không thể chỉnh sửa
                    return false;
                }
            };

            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("MaKhoa"));
                v.add(rs.getString("TenKhoa"));      
                tb.addRow(v);
            }
            tbqlbn.setModel(tb);
            con.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_bttimkiemActionPerformed

    private void txtmbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmbnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmbnActionPerformed

    private void tbqlbnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbqlbnMouseClicked
        int i=tbqlbn.getSelectedRow();
        DefaultTableModel tb=(DefaultTableModel)tbqlbn.getModel();
        txthoten.setText(tb.getValueAt(i, 1).toString());
        txtmbn.setText(tb.getValueAt(i, 0).toString());
        txtmbn.setEnabled(false);

    }//GEN-LAST:event_tbqlbnMouseClicked

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
            cell.setCellValue("DANH SÁCH Khoa");


            CellStyle cellStyle_Head = DinhdangHeader(spreadsheet);
            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã Khoa");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Tên Khoa");

           
            //Kết nối DB
            con = BTL.Connect.KetnoiDB();
            String sql = "Select * From Khoa";
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
                cell.setCellValue(rs.getString("MaKhoa"));

                cell = row.createCell(2);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(rs.getString("TenKhoa"));
                i++;
            }
            //Hiệu chỉnh độ rộng của cột
            for (int col = 0; col < tongsocot; col++) {
                spreadsheet.autoSizeColumn(col);
            }

            File f = new File("C:\\Users\\dqduc\\OneDrive\\Documents\\Java\\hosobenhnhan\\src\\main\\java\\QLBN\\Khoa.xlsx");
            FileOutputStream out = new FileOutputStream(f);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        xoatrang();
        txtmbn.setEnabled(true);
        load_qtdt();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btxoaActionPerformed
        try {
            String mk = txtmbn.getText().trim();

            // Xác nhận từ người dùng trước khi xóa
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                // Kết nối đến cơ sở dữ liệu
                con = BTL.Connect.KetnoiDB();

                // Kiểm tra xem bệnh nhân có bản ghi nào trong HoSoNhapVien không
                String checkSql = "SELECT COUNT(*) FROM HoSoNhapVien WHERE MaKhoa = '" + mk + "'";
                Statement checkStmt = con.createStatement();
                ResultSet rs = checkStmt.executeQuery(checkSql);

                if (rs.next() && rs.getInt(1) > 0) {
                    // Nếu có bản ghi trong HoSoNhapVien, xóa chúng trước
                    String deleteHoSoSql = "DELETE FROM HoSoNhapVien WHERE MaKhoa = '" + mk + "'";
                    checkStmt.executeUpdate(deleteHoSoSql);
                }

                // Xóa bản ghi trong bảng BenhNhan
                String deleteBenhNhanSql = "DELETE FROM Khoa WHERE MaKhoa = '" + mk + "'";
                Statement deleteStmt = con.createStatement();
                deleteStmt.executeUpdate(deleteBenhNhanSql);

                // Đóng kết nối
                con.close();

                // Thông báo thành công
                JOptionPane.showMessageDialog(this, "Xoá thành công");

                // Tải lại dữ liệu và xóa trắng các trường nhập liệu
                load_qtdt();
                xoatrang();
            } else {
                JOptionPane.showMessageDialog(this, "Không xoá nữa");
            }
        } catch (Exception ex) {
            // Hiển thị lỗi nếu có ngoại lệ xảy ra
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xoá: " + ex.getMessage());
        }
    }//GEN-LAST:event_btxoaActionPerformed

    private void btsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsuaActionPerformed
        try{
            String mk = txtmbn.getText();
            if(mk.isEmpty()){
                JOptionPane.showMessageDialog(this, "Phải nhập mã khoa!");
                return;
            }
            String tk = txthoten.getText();
            if(tk.isEmpty()){
                JOptionPane.showMessageDialog(this, "Phải nhập tên khoa!");
                return;
            }

            con = BTL.Connect.KetnoiDB();

            String sql = "UPDATE Khoa SET TenKhoa=N'" + tk + "' WHERE MaKhoa='" + mk + "'";
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

    private void btthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthemActionPerformed
        // B1: lấy dữ liệu các components đưa vào biến
        String tk = txthoten.getText().trim();
        String mk = txtmbn.getText().trim();
        // B1.1: Kiểm tra các trường bắt buộc phải nhập
        if (tk.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên khoa không được để trống.");
            txthoten.requestFocus();
            return;
        }

        if (mk.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã khoa không được để trống.");
            txtmbn.requestFocus();
            return;
        }
        // B2: Kết nối Database
        try {
            con = BTL.Connect.KetnoiDB();

            // B3: Tạo đối tượng Statement để thực hiện lệnh truy vấn
            String sql = "INSERT INTO Khoa (MaKhoa, TenKhoa) VALUES (N'" + mk + "', N'" + tk + "')";
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            con.close();

            load_qtdt();
            JOptionPane.showMessageDialog(this, "Thêm mới thành công");

            // Xóa trang
            xoatrang();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm dữ liệu: " + e.getMessage());
        }
    }//GEN-LAST:event_btthemActionPerformed

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
            java.util.logging.Logger.getLogger(Khoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Khoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Khoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Khoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Khoa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnhapexcel;
    private javax.swing.JButton btsua;
    private javax.swing.JButton btthem;
    private javax.swing.JButton bttimkiem;
    private javax.swing.JButton btxoa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbqlbn;
    private javax.swing.JTextField txtTimkiem;
    private javax.swing.JTextField txthoten;
    private javax.swing.JTextField txtmbn;
    // End of variables declaration//GEN-END:variables
}
