package jshan.temp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jshan.library.connector.MySqlConnector;

/**
 * 아이피당 하나씩만 인정한다.
 * @date 2017. 12. 22.
 * @param 
 * @exception
 * @see
 */
public class LogConvWithMysql2 {
	
	public static void main(String[] args){
		Connection conn = null;
		PreparedStatement ps = null;
		String lineStr = null;
		try {
			conn = MySqlConnector.getConnection();
			ps = conn.prepareStatement("select * from conv_temp_compress_1221_sr_2");
			
			ResultSet rs = ps.executeQuery();
			List<String> iplist = new ArrayList<String>();
			while(rs.next()){
			  String no = rs.getString("idx");
			  String ip = rs.getString("ip");
			  if(iplist.contains(ip)) {
			    System.out.println(String.format("skip>>>>>%s, %s", no, ip));
			    continue;
			  }
			  System.out.println(String.format("insert>>>>>%s, %s", no, ip));
			  iplist.add(ip);
			  ps = conn.prepareStatement("insert into conv_temp_compress_1221_sr_2_nodupl select * from conv_temp_compress_1221_sr_2 where idx=?");
			  ps.setString(1, no);
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
