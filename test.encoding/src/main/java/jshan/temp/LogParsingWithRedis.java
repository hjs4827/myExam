package jshan.temp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
			fr = new FileReader("C:\\tempfile\\accesslogSample.txt");
			br = new BufferedReader(fr);
			
			// 1줄로 읽기
			String urlStr = "";
			String paramStr = "";
			String ip = "";
			String date = "";
			String full_url = "";
			String s = "";
			String us = "";
			
			while((lineStr = br.readLine()) != null){
				
				// ip
				ip = lineStr.substring(0,lineStr.indexOf("-")-1);
				//System.out.println(ip);
				// date
				date = lineStr.substring(lineStr.indexOf("[")+1,lineStr.indexOf("]"));
				//System.out.println(date);
				// url
				try{
					urlStr = lineStr.substring(lineStr.indexOf("/servlet"));
					full_url = urlStr.substring(0,urlStr.indexOf("\""));
				}catch(Exception e){
					full_url = "";
				}
				//System.out.println(full_url);
				// s
				try{
					paramStr = urlStr.substring(urlStr.indexOf("&s="));
					s = paramStr.substring(3,paramStr.indexOf("&",2));
				}catch(Exception e){
					s = "";
				}
				//System.out.println(s);
				// us
				try{
					paramStr = urlStr.substring(urlStr.indexOf("&us="));
					us = paramStr.substring(4,paramStr.indexOf("&",2));
				}catch(Exception e){
					us = "";
				}
				//System.out.println(us);
				jedis.incr("mediacnt17:"+s+"-"+us);
				jedis.set("mediaurl17:"+s+"-"+us,full_url);
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
	
}
