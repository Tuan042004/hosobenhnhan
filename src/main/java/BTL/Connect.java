/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BTL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Connect {
    public static Connection KetnoiDB() throws ClassNotFoundException, SQLException {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost\\Tuandepzai:1433;databaseName=QuanLyBenhNhan;encrypt=true;trustServerCertificate=true";
            String user = "sa";
            String pass = "123456";
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new SQLException("Lỗi kết nối cơ sở dữ liệu!", e);
        }
        return con;
    }

    public static void DongDB(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}