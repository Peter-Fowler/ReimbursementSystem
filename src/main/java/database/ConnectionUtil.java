package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	
	public static Connection getConnection() {
		Properties props = new Properties();
		FileInputStream fis = null;
		Connection con = null;
		try {
			//fis = new FileInputStream("db.properties");
			//props.load(fis);

			// load the Driver Class
			//Class.forName(props.getProperty("DB_DRIVER_CLASS"));
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// create the connection now
			/*
			 * con = DriverManager.getConnection(props.getProperty("DB_URL"),
			 * props.getProperty("DB_USERNAME"), props.getProperty("DB_PASSWORD"));
			 */
			
			  con = DriverManager.getConnection(
			  "jdbc:oracle:thin:@id.ciukb8h6e6pf.us-east-1.rds.amazonaws.com:1521:MONEYDB",
			  "admin", "admin123");
			 
					
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
}