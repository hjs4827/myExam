package test.encoding;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

public class EncodingTest {
	String url = "http://google.com";
	@Test
	public void test() throws UnsupportedEncodingException {
		String eUrl = URLEncoder.encode(url, "euc-kr");
		System.out.println(eUrl);
		assertTrue(eUrl.startsWith("http%3A%2F%2F"));
		
		String utfUrl = URLEncoder.encode(url, "utf-8");
		System.out.println(utfUrl);
		assertTrue(utfUrl.startsWith("http%3A%2F%2F"));
		
		// 한글일때는 인코딩 방식이 다르다.
		url = "안녕하세요";
		eUrl = URLEncoder.encode(url, "euc-kr");
		utfUrl = URLEncoder.encode(url, "utf-8");
		System.out.println(eUrl);
		System.out.println(utfUrl);
		assertTrue(eUrl.equals("%BE%C8%B3%E7%C7%CF%BC%BC%BF%E4"));
		assertTrue(utfUrl.equals("%EC%95%88%EB%85%95%ED%95%98%EC%84%B8%EC%9A%94"));
	}
	
	@Test
	public void encodingTest() throws UnsupportedEncodingException{
		String charset[] = {"euc-kr", "ksc5601", "iso-8859-1", "8859_1", "ascii", "UTF-8"};
		String name = "한글";
		for(int i=0; i<charset.length ; i++){
			for(int j=0 ; j<charset.length ; j++){
				if(i==j) continue;
				System.out.println(charset[i]+" : "+charset[j]+" :"+new String(name.getBytes(charset[i]),charset[j]));
			}
		}
	}
}
