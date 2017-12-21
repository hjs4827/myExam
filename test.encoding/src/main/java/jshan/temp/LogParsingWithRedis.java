package jshan.temp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import redis.clients.jedis.Jedis;
import jshan.library.connector.MySqlConnector;
import jshan.library.redisUtils.JedisManager;

public class LogParsingWithRedis {
	
	public static void main(String[] args){
		FileReader fr = null;
		BufferedReader br = null;
		String lineStr = null;
		Jedis jedis = null;
		JedisManager manager = JedisManager.getInstance();
		jedis = manager.getConnection(0);
		try {
			fr = new FileReader("C:\\ggg\\log32.dreamsearch.or.kr-access.log-20170613-17");
			br = new BufferedReader(fr);
			
			// 1줄로 읽기
			String urlStr = "";
			String paramStr = "";
			String ip = "";
			String date = "";
			String full_url = "";
			String s = "";
			String us = "";
			String key ="";
			while((lineStr = br.readLine()) != null){
				if(lineStr.indexOf("servlet/rf?") != -1){
				  try{
            paramStr = lineStr.substring(lineStr.indexOf("&url="));
            urlStr = paramStr.substring(5,paramStr.indexOf("HTTP/1.1"));
            urlStr = URLDecoder.decode(urlStr);
            urlStr = replaceUrlPattern(urlStr, false);
            urlStr  = "RF:"+urlStr;
          }catch(Exception e){
            urlStr = "";
          }
				}
				else if(lineStr.indexOf("servlet/rd?form") != -1){
				  try{
            paramStr = lineStr.substring(lineStr.indexOf("&url="));
            urlStr = paramStr.substring(5,paramStr.indexOf("HTTP/1.1"));
            urlStr = URLDecoder.decode(urlStr);
            urlStr = replaceUrlPattern(urlStr, false);
            urlStr  = "RF:"+urlStr;
          }catch(Exception e){
            urlStr = "";
          }
				}
				else if(lineStr.indexOf("servlet/rfshop?") != -1){
				  try{
            paramStr = lineStr.substring(lineStr.indexOf("&userid="));
            urlStr = paramStr.substring(8,paramStr.indexOf("&pcode"));
            urlStr  = "SHOP:"+urlStr;
          }catch(Exception e){
            urlStr = "";
          }
				}
				else if(lineStr.indexOf("servlet/rd?sc?") != -1){
          try{
            paramStr = lineStr.substring(lineStr.indexOf("&userid="));
            urlStr = paramStr.substring(8,paramStr.indexOf("&pcode")+1);
            urlStr  = "SHOP:"+urlStr;
          }catch(Exception e){
            urlStr = "";
          }
        }
				System.out.println(urlStr);
//				// ip
//				ip = lineStr.substring(0,lineStr.indexOf("-")-1);
//				//System.out.println(ip);
//				// date
//				date = lineStr.substring(lineStr.indexOf("[")+1,lineStr.indexOf("]"));
//				//System.out.println(date);
//				// url
//				try{
//					urlStr = lineStr.substring(lineStr.indexOf("/servlet"));
//					full_url = urlStr.substring(0,urlStr.indexOf("\""));
//				}catch(Exception e){
//					full_url = "";
//				}
//				//System.out.println(full_url);
//				// s
//				try{
//					paramStr = urlStr.substring(urlStr.indexOf("&s="));
//					s = paramStr.substring(3,paramStr.indexOf("&",2));
//				}catch(Exception e){
//					s = "";
//				}
//				//System.out.println(s);
//				// us
//				try{
//					paramStr = urlStr.substring(urlStr.indexOf("&us="));
//					us = paramStr.substring(4,paramStr.indexOf("&",2));
//				}catch(Exception e){
//					us = "";
//				}
//				//System.out.println(us);
				jedis.incr("ETC-17:"+urlStr);
//				jedis.set("mediaurl17:"+s+"-"+us,full_url);
			}
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
		
//		PreparedStatement ps = conn.prepareStatement("");
	}
	
	public static String replaceUrlPattern(String url, boolean isSkipSubDomain){
    String replaceUrl = url;
    // 모든 케이스를 잡을순 없다.
    String[] replacePattern = {"www.",".com",".co.kr",".biz","http://","https://",".net",".ac.kr"};
    for (String pattern : replacePattern) {
      replaceUrl = replaceUrl.replace(pattern, "");
    }
    // 서브 도메인 삭제
    if(!isSkipSubDomain && replaceUrl.indexOf("/")!= -1)
      replaceUrl = replaceUrl.substring(0, replaceUrl.indexOf("/"));
    return replaceUrl;
  }
}


