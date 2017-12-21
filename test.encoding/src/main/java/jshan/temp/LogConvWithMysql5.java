package jshan.temp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jshan.library.connector.MySqlConnector;
//create table actno_temp_20171221
//(
// act_no bigint
//);
//
//
//ALTER TABLE actno_temp_20171221
//ADD CONSTRAINT PRIMARY KEY (act_no);
public class LogConvWithMysql5 {

	public static void main(String[] args) {
		Connection conn = MySqlConnector.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		String lineStr = null;
		try {
			// 1줄로 읽기
			String ip = "";
			String query = "SELECT ";
			query += "no ";
			query += "FROM ACTION_LOG a ";
			query += "WHERE a.IP= ? AND a.MEDIA_CODE=?	AND a.SITECODE=?	AND a.partdt  >=  '20171201' AND regdate < '2017-12-21 20:59:59' order by no desc ";
			query += "LIMIT 1";
			System.out.println(query);
			ps = conn.prepareStatement(query);
			ps2 = conn.prepareStatement("select * from conv_temp_compress_1221_sr");
			ResultSet rs = ps2.executeQuery();
			ResultSet rs2 = null;
			ps3 = conn.prepareStatement("insert into actno_temp_20171221 values(?)");
			while (rs.next()) {
				ip = rs.getString("ip");
				String scriptNo = rs.getString("script_no");
				String siteCode = rs.getString("sitecode");
// 				System.out.println(String.format("%s-%s-%s", ip, scriptNo, siteCode));
				
				ps.setString(1, ip);
				ps.setString(2, scriptNo);
				ps.setString(3, siteCode);
				
				rs2 = ps.executeQuery();
				while (rs2.next()) {
					try{
						ps3.setLong(1, rs2.getLong("no"));
						ps3.executeQuery();
					} catch (Exception e) {
					}
				}
			}
			System.out.println("END");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(lineStr);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// PreparedStatement ps = conn.prepareStatement("");
	}
}
