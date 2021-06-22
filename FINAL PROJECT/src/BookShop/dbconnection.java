package BookShop;
import java.sql.*;
import javax.swing.*;
public class dbconnection {
	Connection conn = null;
    public static Connection dbconnector() {
    	try {
    		Class.forName("org.sqlite.JDBC");
    		Connection conn = DriverManager.getConnection("jdbc:sqlite:BookShop.db");
    		return conn;
    		
    	}catch(Exception e) {
    		return null;
    	}
    }
}



