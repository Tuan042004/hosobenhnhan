/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLyBenhNhan;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ADMIN
 */
public class conDB {
     public static Connection ketnoidb() {
        Connection con = null;
        try {
            // B1: Nạp trình điều khiển
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // B2: Tạo chuỗi kết nối đến DB
            String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyBenhNhan;encrypt=true;trustServerCertificate=true";
            String user = "sa";
            String pass = "123456";

            // B3: Thiết lập kết nối
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
