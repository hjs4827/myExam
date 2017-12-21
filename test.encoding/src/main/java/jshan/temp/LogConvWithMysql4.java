package jshan.temp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import jshan.library.connector.MySqlConnector;

public class LogConvWithMysql4 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		String lineStr = null;
		
		// =====================================================================
		// =====================================================================
		// =====================================================================
		// =====================================================================
		// 날짜 체크해라!!!!!
		String date = "20171221";
		// =====================================================================
		// =====================================================================
		// =====================================================================
		try {
			conn = MySqlConnector.getConnection();
			conn.setAutoCommit(false);
			String query = "INSERT INTO CONVERSION_LOG"
					+ "(PARTDT,IP,USERID,ORDCODE,ORDRFURL,ORDPCODE,ORDQTY,ORDPRICE,ACTION_LOG_NO,PNM,UNAME,USEX,UPNO, IN_HOUR, DIRECT, REGDATE, LAST_CLICK_TIME, MOBON_YN, INFLOW_ROUTE) "
					+ "VALUES('"+date+"',?,?,?,?,?,?,?,?,?,?,?,?, ?, ?,?,?,?,?)";

			String query2 = "INSERT INTO CONVACT_LOG(ADACTLOG_NO,PARTDT,IP,PCODE,SHOPLOG_NO,SITECODE,MCODE,MEDIA_CODE,MEDIA_ID,K_NO,VCNT,VCNT2"
					+ ",CCNT,POINT,ACTGUBUN,ADGUBUN,ADPRODUCT,REGDATE,MCGB) "
					+ "SELECT NO ADACTLOG_NO,PARTDT,IP,PCODE,SHOPLOG_NO,SITECODE,MCODE,MEDIA_CODE,MEDIA_ID,K_NO,VCNT,VCNT2"
					+ ",CCNT,POINT,ACTGUBUN,ADGUBUN,ADPRODUCT,REGDATE,MCGB " + "FROM ACTION_LOG " + "WHERE NO=? "
					+ "ON DUPLICATE KEY " + "UPDATE CONVACTCNT=CONVACTCNT+1 ; ";

			String query3 = "INSERT INTO status_conversion(sdate,ordcode,userid,product,gubun,sitecode,media_id,media_code,convtype,ordcnt,ordpcnt,ordprice"
					+ ", mcgb, in_hour, DIRECT, REGDATE,MOBON_YN) "
					+ "VALUES('"+date+"',?,?,?,?,?,?,?,?,1,?,?, ?, ?, ?, ?,?) " + "ON DUPLICATE KEY "
					+ "UPDATE ordcnt = 1;";
			ps = conn.prepareStatement("select * from conv_recv_20171221_final_2");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				try{
				String ip = rs.getString("IP");
				String userId = rs.getString("MCODE");
				String ordCode = rs.getString("ordercode");
				String rfUrl = "";
				String ordPcode = "";
				int ordQty = Integer.parseInt(!StringUtils.isNumeric(rs.getString("qty")) ? "1" : rs.getString("qty"));
				String price = rs.getString("price");
				String ano = rs.getString("NO");
				String pnm = rs.getString("pnm");
				String uname = "0";
				String usex = "0";
				String upno = "0";
				String in_hour = rs.getString("in_hour");
				String direct = rs.getString("direct");
				String regDate = rs.getString("user_regdate");
				String clkDate = rs.getString("REGDATE").replaceAll("-", "").replaceAll(":", "").trim().substring(0,
						13);
				String inflow_route = "";
				String mobonYn = "N";

				String adproduct = rs.getString("ADPRODUCT");
				String adGubun = rs.getString("ADGUBUN");
				String sitecode = rs.getString("SITECODE");
				String mediaId = rs.getString("MEDIA_ID");
				String mediaCode = rs.getString("MEDIA_CODE");
				String actGubun = rs.getString("ACTGUBUN");
				String mcgb = rs.getString("MCGB");
				ps = conn.prepareStatement(query);
				ps.setString(1, ip);
				ps.setString(2, userId);
				ps.setString(3, ordCode);
				ps.setString(4, rfUrl);
				ps.setString(5, ordPcode);
				ps.setInt(6, ordQty);
				ps.setString(7, price);
				ps.setString(8, ano);
				ps.setString(9, pnm);
				ps.setString(10, uname);
				ps.setString(11, usex);
				ps.setString(12, upno);
				ps.setString(13, in_hour);
				ps.setString(14, direct);
				ps.setString(15, regDate);
				ps.setString(16, clkDate);
				ps.setString(17, mobonYn);
				ps.setString(18, inflow_route);
				ps.executeUpdate();

				ps = conn.prepareStatement(query2);
				ps.setString(1, ano);
				ps.executeUpdate();

				ps = conn.prepareStatement(query3);
				ps.setString(1, ordCode);
				ps.setString(2, userId);
				ps.setString(3, adproduct);
				ps.setString(4, adGubun);
				ps.setString(5, sitecode);
				ps.setString(6, mediaId);
				ps.setString(7, mediaCode);
				ps.setString(8, actGubun);
				ps.setInt(9, ordQty);
				ps.setString(10, price);
				ps.setString(11, mcgb);
				ps.setString(12, in_hour);
				ps.setString(13, direct);
				ps.setString(14, regDate);
				ps.setString(15, mobonYn);
				ps.executeUpdate();
				System.out.println(String.format("%s-%s-%s-%s-%s", ordCode, sitecode, price, pnm, userId));
				} catch (Exception e) {
					
				}
			}
			conn.commit();

		} catch (Exception e) {
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

	}
}
