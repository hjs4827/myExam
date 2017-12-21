package jshan.temp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jshan.library.connector.MySqlConnector;

public class LogConvWithMysql {

	public static void main(String[] args) {
		FileReader fr = null;
		BufferedReader br = null;
		Connection conn = MySqlConnector.getConnection();
		PreparedStatement ps = null;
		String lineStr = null;
		try {
			fr = new FileReader("D:\\temp\\20171221\\convLog56.txt");
			br = new BufferedReader(fr);
			// 1줄로 읽기
			String urlStr = "";
			String ip = "";
			String date = "";

			String[] params;
			conn.setAutoCommit(false);
			int idx=0;

			ps = conn.prepareStatement("insert into conv_temp_1005 values(?,?,?,?,?,?,?,?,?)");
			while ((lineStr = br.readLine()) != null) {
				// 115.40.64.63 - - [05/Oct/2017:00:35:51 +0900] "GET
				// /servlet/conv?uid=topcomics&ordcode=1&pcode=1&qty=1&price=22000&pnm=5AD
				// HTTP/1.1" 200 - "https://toptoon.com/payment/complete"
				// "Mozilla/5.0 (Linux; Android 7.0; LGM-G600L Build/NRD90U; wv)
				// AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0
				// Chrome/61.0.3163.98 Mobile Safari/537.36" 22ms "IP_info:
				// 115.40.117.23.754752 ,fp: - ,Start_Time: 2017032316
				// ,site_code:
				// 9449_71b12c80d0b6f32178933d254fd2fc0c_RM_mbe_1_10_"

				try {
					String uid = "", ordercode = "", pcode = "", pnm = "", qty = "", price = "", sitecode = "";
					// date
					date = lineStr.substring(lineStr.indexOf("[") + 1, lineStr.indexOf("]"));
					date = "2017-12-21 " + date.substring(date.indexOf(":"), date.indexOf(" "));

					// ip
					int ipidx = lineStr.indexOf(" ,fp:");
					ipidx = ipidx == -1 ? lineStr.indexOf(" , fp:") : ipidx;
					ip = lineStr.substring(lineStr.indexOf("IP_info: ") + 9, ipidx);

					// site code
					if (lineStr.indexOf("site_code: ") != -1) {
						sitecode = lineStr.substring(lineStr.indexOf("site_code: ") + 11).replace("\"", "");
					}

					urlStr = lineStr.substring(lineStr.indexOf("/servlet/conv?") + 14, lineStr.indexOf(" HTTP"));
					params = urlStr.split("&");
					for (String param : params) {
						if (param.startsWith("uid")) {
							uid = param.split("=").length == 2 ? param.split("=")[1] : "";
						} else if (param.startsWith("ordcode")) {
							ordercode = param.split("=").length == 2 ? param.split("=")[1] : "";
						} else if (param.startsWith("pcode")) {
							pcode = param.split("=").length == 2 ? param.split("=")[1] : "";
							pcode = URLDecoder.decode(pcode, "utf-8");
							if (pcode.length() > 100) {
								pcode = pcode.substring(0, 99);
							}

						} else if (param.startsWith("qty")) {
							qty = param.split("=").length == 2 ? param.split("=")[1] : "";
						} else if (param.startsWith("price")) {
							price = param.split("=").length == 2 ? param.split("=")[1] : "";
						} else if (param.startsWith("pnm")) {
							try {
								pnm = jshan.utils.HangulCharsetDetector
										.toString(param.split("=").length == 2 ? param.split("=")[1] : "");
							} catch (Exception e) {
								pnm = "";
							}

							try {
								if (pnm.length() > 0)
									pnm = URLDecoder.decode(URLDecoder.decode(pnm, "utf-8"), "utf-8");
								if (pnm.length() > 200)
									pnm = pnm.substring(0, 199);
							} catch (Exception e) {
								pnm = "";
							}
						}
					}

					// create table conv_temp_1005(
					// ip varchar(100)
					// , uid varchar(100)
					// , ordercode varchar(100)
					// , pcode varchar(100)
					// , pnm varchar(100)
					// , qty varchar(100)
					// , price varchar(100)
					// , regdate varchar(100)
					// , site_code varchar(100)
					// );

//					System.out.println(String.format("%s, %s, %s, %s, %s, %s, %s, %s ", date, ip, uid, ordercode, pcode, qty, price, sitecode));
					ps.setString(1, ip);
					ps.setString(2, uid);
					ps.setString(3, ordercode);
					ps.setString(4, pcode);
					ps.setString(5, pnm);
					ps.setString(6, qty);
					ps.setString(7, price);
					ps.setString(8, date);
					ps.setString(9, sitecode);
					ps.executeUpdate();
					idx++;
					if(idx % 10000 == 0){
						conn.commit();
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(lineStr);
					continue;
				}
			}
			System.out.println("END");
			conn.commit();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(lineStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(lineStr);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
				fr.close();
				br.close();
				conn.close();
				ps.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// PreparedStatement ps = conn.prepareStatement("");
	}
}
