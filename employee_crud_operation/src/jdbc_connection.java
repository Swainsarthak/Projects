import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbc_connection {
    static Connection con=null;
    static String url="jdbc:mysql://localhost:3306/Sarthak_project";
    static String un="root";
    static String password="Sarthak@123";
    public static Connection request_connection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection(url,un,password);
        return con;
    }

}
