package jshan.temp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import redis.clients.jedis.Jedis;
import jshan.library.connector.MySqlConnector;
import jshan.library.redisUtils.JedisManager;

public class AltoolbarLogWithRedis {
	
	public static void main(String[] args){
		try {
			/*JedisManager manager = JedisManager.getInstance();
			Jedis jedis = manager.getConnection(3);
			Set<String> test = jedis.keys("*");
			for(String s : test){
				//i++;
			//	map.put(s, jedis.get(s));
//				System.out.println(jedis.get(s));
				jedis.del(s);
//				i++;
			}*/
			FileReader fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\20150721_알툴바 로그 분석\\accesslog_altoolbar.txt");
			//setDataRedis(fr);
			analyzeAltoolbar(fr);
//			fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-0100");
//			setDataRedis(fr);
//			fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-0200");
//			setDataRedis(fr);
//			fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-0300");
//			setDataRedis(fr);
//			fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-0400");
//			setDataRedis(fr);
//			fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-0500");
//			setDataRedis(fr);
//			fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-0600");
//			setDataRedis(fr);
//			fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-0700");
//			setDataRedis(fr);
//			fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-0800");
//			setDataRedis(fr);
//			fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-0900");
//			setDataRedis(fr);
//			fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-1000");
//			setDataRedis(fr);
//			fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-1100");
//			setDataRedis(fr);
//			fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-1200");
//			setDataRedis(fr);
//			fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-1300");
//			setDataRedis(fr);
//			fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-1400");
//			setDataRedis(fr);
//			fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-1500");
//			setDataRedis(fr);
//			test = jedis.keys("*");
//			for(String s : test){
//				//i++;
//			//	map.put(s, jedis.get(s));
//				System.out.println(jedis.get(s));
//				System.out.println(s+":"+jedis.get(s));
//				i++;
//			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setDataRedis(FileReader fr){
		BufferedReader br = null;
		String lineStr = null;
		Jedis jedis = null;
		JedisManager manager = JedisManager.getInstance();
		jedis = manager.getConnection(3);
		Set<String> test = jedis.keys("*");
//		for(String s : test){
//			//i++;
//		//	map.put(s, jedis.get(s));
////			System.out.println(jedis.get(s));
//			jedis.del(s);
////			i++;
//		}
		try {
			//
			//fr = new FileReader("C:\\workspace\\documents\\2015\\2.외부연동\\201505011~20150531 알툴바 연동\\report\\accesslog38_0805\\www.dreamsearch.or.kr-access.log.20150805-0000");
			br = new BufferedReader(fr);
			StringBuilder sb = new StringBuilder();
			String ip = "";
			int i = 0;
			// 1줄로 읽기
			while((lineStr = br.readLine()) != null){
				// ip
				ip = lineStr.substring(0,lineStr.indexOf("-")-1);
				if(lineStr.indexOf("altoolbar")!= -1){
					jedis.incr("total");
					jedis.incr(ip);
					if(lineStr.indexOf("/altoolbar/config.txt")!= -1){
						jedis.incr("altoolbar:config");
					}
					else if(lineStr.indexOf("/servlet/altoolbarApi?type=config")!= -1){
						jedis.incr("altoolbar:configApi");
					}
					else if(lineStr.indexOf("/altoolbar/urlWithScList.txt")!= -1){
						jedis.incr("altoolbar:domainList");
					}
					else if(lineStr.indexOf("/servlet/altoolbarApi?type=urlWithScDownload")!= -1){
						jedis.incr("altoolbar:domainListApi");
					}
					else if(lineStr.indexOf("/servlet/altoolbarApi?url")!= -1){
						jedis.incr("altoolbar:callAd");
					}
				}
				if(lineStr.indexOf("drc")!= -1 && lineStr.indexOf("s=3380")!= -1){
					jedis.incr("altoolbar:drc");
					jedis.set("altoolbar:drcValue"+i++, lineStr);
				}
				if(lineStr.indexOf("NO_IP_3380_1438748776337_3921")!= -1){
					jedis.incr("altoolbar:drc_3921");
				}
			}
			System.out.println("finish>>>>>");
			
			/*test = jedis.keys("*");
			for(String s : test){
				//i++;
			//	map.put(s, jedis.get(s));
//				System.out.println(jedis.get(s));
				System.out.println(s+":"+jedis.get(s));
//				i++;
			}*/
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(lineStr);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(lineStr);
		}
		
		finally{
			try {
				fr.close();
				br.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void analyzeAltoolbar(FileReader fr){
		BufferedReader br = null;
		String lineStr = null;
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		
		br = new BufferedReader(fr);
		String ip = "";
		Map<String, Object> ipMap = new HashMap<String, Object>();
		
		try {
			while((lineStr = br.readLine()) != null){
				
				// ip
				ip = lineStr.substring(lineStr.indexOf(":")+1,lineStr.indexOf("- -")-1);
				if(ipMap.containsKey(ip)){
					@SuppressWarnings("unchecked")
					Map<String, Object> map = (Map<String, Object>) ipMap.get(ip);
					// AD
					if(lineStr.indexOf("/servlet/altoolbarApi?type=callAd&url=&sc=") != -1){
						map.put("adCnt",((Integer)map.get("adCnt"))+1);
					}
					else{
						map.put("umCnt",((Integer)map.get("umCnt"))+1);
					}
					
				}
				else{
					Map<String, Object> map = new HashMap<String, Object>();
					// AD
					if(lineStr.indexOf("/servlet/altoolbarApi?type=callAd&url=&sc=") != -1){
						map.put("adCnt", 1);
						map.put("umCnt", 0);
					}
					else{
						map.put("adCnt", 0);
						map.put("umCnt", 1);
					}
					ipMap.put(ip, map);
				}
				
			}
			System.out.println(ipMap);
			
			Connection conn =  MySqlConnector.getConnection();
			String sql = "select 1";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
