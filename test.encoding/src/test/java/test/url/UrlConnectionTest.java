package test.url;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import jshan.url.myUrlConnectionUtils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

public class UrlConnectionTest {
	URL url;
	URLConnection uc;
	HttpURLConnection huc;
	private static final String urlStr = "http://www039.dreamsearch.or.kr/servlet/altoolbarApi?type=urlDownload";
	private static final String MAIN_PATH = "C:\\workspace\\tempFile\\";
	
	@Before
	public void init(){
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
		
	}
	
	@Test
	public void urlOutputTest() {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String line;
			while((line = reader.readLine())!= null){
				sb.append(line+"\r\n");
			}
			reader.close();
			System.out.println(sb.toString());
		}
		catch (IOException e) {
			// 
			e.printStackTrace();
		}
	}
	
	@Test
	public void urlOutPutToFile() throws IOException{
		String content = myUrlConnectionUtils.getUrlContent(urlStr);
		if(StringUtils.isEmpty(content)){
			fail("url 커넥션 에러");
			return;
		}
		
		// generate file
		File urlFile = FileUtils.getFile(MAIN_PATH+"urlList.txt");
		FileUtils.write(urlFile, content);
	}
	
	@Test
	public void serverConnectionTest(){
		try {
			url = new URL("http://naver.com");
			huc = (HttpURLConnection) url.openConnection();
			
			System.out.println(huc.getResponseCode());
			assertEquals(HttpURLConnection.HTTP_OK, huc.getResponseCode());
			// 입력 받을수 있는 상태인지?
			System.out.println(uc.getAllowUserInteraction()); //false
			assertFalse(uc.getAllowUserInteraction());
			
			// 찾을수 없음.
			url = new URL("http://www38.dreamsearch.or.kr/altoolbar/config2.txt");
			huc = (HttpURLConnection) url.openConnection();
			assertEquals(HttpURLConnection.HTTP_NOT_FOUND ,huc.getResponseCode());
		}
		catch (MalformedURLException e) {
			// 
			e.printStackTrace();
			fail(e.toString());
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.toString());
		}
	}
}
