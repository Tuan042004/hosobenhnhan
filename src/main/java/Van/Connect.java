/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package folder;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Dell V
 */
public class Connect {
    public static Connection KetnoiDB() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = ("jdbc:sqlserver://localhost:1433;databaseName=QuanLyBenhNhan;encrypt=true;trustServerCertificate=true");
            String user = "sa";
            String pass  = "123456";
            conn = DriverManager.getConnection(url, user , pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
        
    }  
}
