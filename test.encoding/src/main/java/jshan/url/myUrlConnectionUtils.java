package jshan.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class myUrlConnectionUtils {

	/**
	 * url connection setup
	 * @param urlStr
	 */
	private static URLConnection setup(String urlStr){
		URL url = null;
		URLConnection uc = null;
		try {
			url = new URL(urlStr);
			uc = url.openConnection();
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return uc;
	}
	
	/**
	 * 해당 url content 값을 String 값으로 리턴 해준다.
	 * @param urlStr
	 * @return
	 * 성공 : web content
	 * 실패 : ""
	 */
	public static String getUrlContent(String urlStr) {
		URLConnection uc = setup(urlStr);
		if(uc == null){
			return "";
		}
		StringBuilder sb = new StringBuilder("");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String line;
			while((line = reader.readLine())!= null){
				sb.append(line+"\r\n");
			}
			reader.close();
			
		}
		catch (IOException e) {
			// 
			e.printStackTrace();
		}
		return sb.toString();
	}
}
