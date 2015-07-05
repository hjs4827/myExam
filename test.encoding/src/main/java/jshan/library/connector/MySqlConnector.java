package jshan.library.connector;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnector {
	private static Connection conn = null;
	private MySqlConnector(){}
	
	public static Connection getConnection(){
		if(conn == null){
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://119.205.215.106:3306/dreamsearch";
			String id = "dreamsearch";
			String pwd = "dnjvld$%^";
			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url,id,pwd);
			}
			catch (Exception e) {
				e.toString();
			}
		}
		return conn;
	}
	
	public static void close(){
		if(conn== null) return;
		try {
			if(!conn.isClosed()) conn.close();
		}
		catch (Exception e) {
			e.toString();
		}
		conn = null;
	}
}
