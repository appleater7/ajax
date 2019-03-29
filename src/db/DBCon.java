package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBCon {

	private static final String URL;
	private static final String ID;
	private static final String PASSWORD;
	private static final String DRIVER;
	private static Connection con;
	static {
		InputStream is = DBCon.class.getResourceAsStream("/config/db.properties");
		Properties prop = new Properties();
		try {
			prop.load(is);			
		} catch (IOException e) {
			e.printStackTrace();
		}
		URL = prop.getProperty("url");
		ID = prop.getProperty("id");
		PASSWORD = prop.getProperty("password");
		DRIVER = prop.getProperty("driver");
	}
	public static Connection getCon() {
		if (con == null) {
			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, ID, PASSWORD);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	
	public static void close() {
		if (con != null) {
			try {
				if (!con.isClosed()) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		con = null;
	}
}
