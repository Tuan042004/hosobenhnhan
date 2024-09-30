/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BTL;
import io.github.cdimascio.dotenv.Dotenv;
/**
 *
 * @author Admin
 */
public class config {
   public static Dotenv dotenv = Dotenv.load();
   public static String db_url = dotenv.get("URL_DB");
   public static String db_username = dotenv.get("USERNAME_DB");
   public static String db_pass = dotenv.get("PASS_DB");
}
