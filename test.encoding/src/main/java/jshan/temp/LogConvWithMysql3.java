package jshan.temp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jshan.library.connector.MySqlConnector;

public class LogConvWithMysql3 {
	
	public static void main(String[] args){
		Connection conn = null;
		PreparedStatement ps = null;
		String lineStr = null;
		try {
			conn = MySqlConnector.getConnection();
			ps = conn.prepareStatement("SELECT * FROM conv_actlog_noduplication WHERE (ordercode IS NULL OR ordercode = '')");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
			  String no = rs.getString("NO");
			  String time = rs.getString("userregdate");
			  System.out.println(time);
			  String minute = time.substring(time.indexOf(":")+1, time.lastIndexOf(":"));
			  String hour = time.substring(time.indexOf(" ")+1, time.indexOf(":"));
			  String orderCode = "OrdNo_" + "20171005"+hour+minute;
			  System.out.println(String.format("insert>>>>>%s %s", no, time));
			  ps = conn.prepareStatement("update conv_actlog_noduplication set ordercode = ? where no = ?");
			  ps.setString(1, orderCode);
			  ps.setString(2, no);
			  ps.executeUpdate();
			}
			
	  } catch(Exception e){
	    e.printStackTrace();
	    System.out.println(lineStr);
	  } finally{
      try {
        conn.close();
        ps.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
		
	}
}
