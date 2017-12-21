package jshan.temp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jshan.library.connector.MySqlConnector;

public class LogParsingWithMysql2 {
	
	public static void main(String[] args){
	  Connection conn = MySqlConnector.getConnection();
	  try{
	    String[] fileName = new String[]{
	          "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-00.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-01.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-02.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-03.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-04.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-05.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-06.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-07.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-08.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-09.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-10.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-11.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-12.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-13.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-14.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-15.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-16.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-17.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-18.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-19.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-20.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-21.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-22.bak"
	        , "C:\\ggg\\0227\\www34.dreamsearch.or.kr-access.log-20170227-23.bak"
	    };
	    int idx = 0;
	    for (String file : fileName) {
	      upload(conn, file, "0227", idx+"" );
	      idx++;
	    }
	  } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    finally{
      try {
        conn.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
	  System.out.println("end!!!");
	  
	}
	
	
	public static void upload(Connection conn, String file, String date, String time) throws Exception{
	  FileReader fr = null;
    BufferedReader br = null;
    PreparedStatement ps = null;
    String lineStr = null;
    try {
      fr = new FileReader(file);
      br = new BufferedReader(fr);
      // 1줄로 읽기
      String urlStr = "";
      String ip = "";
      String full_url = "";
      String refer_url = "";
      
      ps = conn.prepareStatement("insert into temp_tomcat_log_access values(?,?,?,?,?)");
      while((lineStr = br.readLine()) != null){
        if(lineStr.indexOf("&s=6074&") == -1 ) continue;
        // 클릭 체크시
        //if(lineStr.indexOf("drc?") == -1 ) continue;
        if(lineStr.indexOf("drc?") != -1 ) continue;
        System.out.println(lineStr);
        // ip
        ip = lineStr.substring(0,lineStr.indexOf("-")-1);
        //System.out.println(ip);
      // url
        try{
          urlStr = lineStr.substring(lineStr.indexOf("/servlet"));
          full_url = urlStr.substring(0,urlStr.indexOf("\""));
        }catch(Exception e){
          full_url = "";
        }
        // url
        try{
          urlStr = lineStr.substring(lineStr.indexOf("http:"));
          refer_url = urlStr.substring(0,urlStr.indexOf("\""));
          System.out.println(refer_url);
        }catch(Exception e){
          refer_url = "";
        }
  
        
        ps.setString(1, ip);
        ps.setString(2, full_url);
        ps.setString(3, refer_url);
        ps.setString(4, date);
        ps.setString(5, time);
        ps.executeUpdate();
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
    catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.out.println(lineStr);
    }
    finally{
      try {
        fr.close();
        br.close();
        ps.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
	}
}
