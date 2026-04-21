package findmyroomie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
   private static final String URL = "jdbc:mysql://localhost:3306/findmyroomie";
   private static final String USER = "root";
   private static final String PASSWORD = "Fortknox123$";

   public DBConnection() {
   }

   public static Connection getConnection() throws SQLException {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
      } catch (ClassNotFoundException var1) {
         var1.printStackTrace();
         System.out.println("MySQL Driver not found!");
      }

      return DriverManager.getConnection(URL, USER, PASSWORD);
   }
   public static void main(String[] args) {
    try {
        Connection conn = getConnection();
        System.out.println("Connection successful!");
        conn.close();
    } catch (SQLException e) {
        System.out.println("Connection failed: " + e.getMessage());
    }
}
}





