package jshan.library.connector;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnector {
	private static Connection conn = null;
	
	private MySqlConnector(){}
	
	public static Connection getConnection(){
		if(conn == null){
			String driver = "org.mariadb.jdbc.Driver";
			String url = "jdbc:mysql://192.168.2.250:3306/dreamsearch";
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
	
	private static Connection conn2 = null;
	public static Connection getConnection2(){
    if(conn2 == null){
      String driver = "org.mariadb.jdbc.Driver";
      String url = "jdbc:mysql://183.111.148.6:3306/dreamsearch";
      String id = "dreamsearch";
      String pwd = "dnjvld$%^";
      try {
        Class.forName(driver).newInstance();
        conn2 = DriverManager.getConnection(url,id,pwd);
      }
      catch (Exception e) {
        e.toString();
      }
    }
    return conn2;
  }
  
  public static void close2(){
    if(conn2== null) return;
    try {
      if(!conn2.isClosed()) conn2.close();
    }
    catch (Exception e) {
      e.toString();
    }
    conn2 = null;
  }
}
