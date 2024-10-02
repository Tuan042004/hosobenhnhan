package BTL;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


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
            String url = config.db_url;
            String user = config.db_username;
            String pass = config.db_pass;
            con = (Connection) DriverManager.getConnection(url, user, pass);
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