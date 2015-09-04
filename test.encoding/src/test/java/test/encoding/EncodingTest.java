package test.encoding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.junit.Test;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;

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
	
	/**
	 * base64 테스트 
	 */
	@Test
	public void base64Test(){
		String text = "안녕하세요";
		String encodeStr = Base64.encodeBase64String(text.getBytes());
		System.out.println(encodeStr);
		
		String decodeStr = new String(Base64.decodeBase64(encodeStr));
		System.out.println(decodeStr);
		
		assertEquals(text, decodeStr);
		
		String url = "http://www.dreamsearch.or.kr/servlet/drc?no=0&kno=0&s=3380&adgubun=AD&gb=AD&sc=c43bb61d6d175a6e1d3089b1f7dbadd5&mc=3380&userid=navisu114&u=navisu114&product=nor&slink=http%3A%2F%2Fwww.navi.co.kr&viewTime=NO_IP_3380_1437113519392_5908";
		String encodeUrlStr = Base64.encodeBase64URLSafeString(url.getBytes());
		System.out.println(encodeUrlStr);
		
		boolean isBase64 = Base64.isBase64(encodeUrlStr);
		assertTrue(isBase64);
		
		String testStr = "http%3A%2F%2Fwww.naver.com";
		String testStr2 = "";
		// base64에서 사용하는 알파벳인지만 체크. ㅡㅡ;; 
		// 공백을 넣어도 true나옴 ㅡㅡ;;
		// 뭐 이따구로 만들어 놧음 ㅡㅡ;;
		isBase64 = Base64.isBase64(testStr);
		System.out.println(isBase64);
		assertFalse(isBase64);
		
		String decodeUrlStr = new String(Base64.decodeBase64(encodeUrlStr));
		System.out.println(decodeUrlStr);
		assertEquals(url, decodeUrlStr);
	}
	
	/**
	 * 어떤 인코딩인지 체크
	 */
	@Test
	public void validateEncodingTest(){
		try {
//			String test = "guide (but, yeah, itâ€™s okay to share it with â€˜em).";
//			  String test2 = "guide (but, yeah, it’s okay to share it with ‘em).";
//			  SYSTEM.OUT.PRINTLN( ISUTF8MISINTERPRETED(TEST)); //TRUE
//			  System.out.println( isUTF8MisInterpreted(test2)); //false
			String testStr = new String("fsdfsdf안fsdf녕하세요! 반갑습니다. (★".getBytes("utf-8"), "utf-8");
			String str8859 = new String(testStr.getBytes(),"ISO-8859-1");
			System.out.println(isUTF8MisInterpreted(str8859));
			byte[] b1 = str8859.getBytes();
			System.out.println(testStr);
			System.out.println(b1);
			System.out.println(str8859);
			String testStr2 = new String("테스트중입니다.. ".getBytes("euc-kr"), "euc-kr");
			
			String str88592 = new String(testStr2.getBytes(),"ISO-8859-1");
			System.out.println(isUTF8MisInterpreted(str88592));
			byte[] b2 = str88592.getBytes();
			System.out.println(testStr2);
			System.out.println(b2);
			System.out.println(str88592);
			String encodingStr = new String(str8859.getBytes("8859_1"),"utf-8");
			
//			System.out.println(encodingStr);
			/*byte[] str8859 = new String(testStr.getBytes("8859_1"),"";
			System.out.println(str8859);*/
//			UniversalDetector detector = new UniversalDetector(null);
//			byte[] byteTemp = str8859.getBytes();
//			System.out.println(byteTemp.length);
//			handleData(byteTemp, 0, byteTemp.length);
//			detector.dataEnd();
//			
//			String encoding = detector.getDetectedCharset();
//			System.out.println(encoding);
//			if (encoding != null) {
//		      System.out.println("Detected encoding = " + encoding);
//		    } else {
//		      System.out.println("No encoding detected.");
//		    }
			/*byte[] BOM = new byte[4];
			BOM = encodingStr.getBytes();
			System.out.println(BOM);
			FileInputStream fs = new FileInputStream("");
			fs.re*/
			/*// 3. 파일 인코딩 확인하기
			if( (BOM[0] & 0xFF) == 0xEF && (BOM[1] & 0xFF) == 0xBB && (BOM[2] & 0xFF) == 0xBF )
			    System.out.println("UTF-8");
			else if( (BOM[0] & 0xFF) == 0xFE && (BOM[1] & 0xFF) == 0xFF )
			    System.out.println("UTF-16BE");
			else if( (BOM[0] & 0xFF) == 0xFF && (BOM[1] & 0xFF) == 0xFE )
			    System.out.println("UTF-16LE");
			else if( (BOM[0] & 0xFF) == 0x00 && (BOM[1] & 0xFF) == 0x00 && 
			         (BOM[0] & 0xFF) == 0xFE && (BOM[1] & 0xFF) == 0xFF )
			    System.out.println("UTF-32BE");
			else if( (BOM[0] & 0xFF) == 0xFF && (BOM[1] & 0xFF) == 0xFE && 
			         (BOM[0] & 0xFF) == 0x00 && (BOM[1] & 0xFF) == 0x00 )
			    System.out.println("UTF-32LE");
			else
			    System.out.println("EUC-KR");*/
			
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error");
		}
		
	}
	
	public static boolean isUTF8MisInterpreted( String input ) {
        //convenience overload for the most common UTF-8 misinterpretation
        //which is also the case in your question
    return isUTF8MisInterpreted( input, "8859_1");  
}

	public static boolean isUTF8MisInterpreted( String input, String encoding) {

	    CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
	    CharsetEncoder encoder = Charset.forName(encoding).newEncoder();
	    ByteBuffer tmp;
	    try {
	        tmp = encoder.encode(CharBuffer.wrap(input));
	    }

	    catch(CharacterCodingException e) {
	        return false;
	    }

	    try {
	        decoder.decode(tmp);
	        return true;
	    }
	    catch(CharacterCodingException e){
	        return false;
	    }       
	}

@Test
public void tttttt(){
	String originalStr = "����몄������. ("; // 테스트 
	String [] charSet = {"utf-8","euc-kr","ksc5601","iso-8859-1","x-windows-949"};
	  
	for (int i=0; i<charSet.length; i++) {
	 for (int j=0; j<charSet.length; j++) {
	  try {
	   System.out.println("[" + charSet[i] +"," + charSet[j] +"] = " + new String(originalStr.getBytes(charSet[i]), charSet[j]));
	  } catch (UnsupportedEncodingException e) {
	   e.printStackTrace();
	  }
	 }
	}
}

@Test
public void testtttt(){
	try {
		String charset1 = "utf-8"; //자바에서는 유니코드 처리됨
		String charset2 = "utf-8";
		String str1 = new String("가".getBytes(charset1),"8859_1"); //getbytes 매개변수는 해당 캐릭터셋으로 바이트 배열로 변환하는 메서드 톰캣에서 8859_1 로 받아서 코딩을 저렇게 함
		String str2 = new String(str1.getBytes("8859_1"),charset2);
		//String str3 = new String(str2.getBytes(charset1),charset2);
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str1.getBytes()[0]);
		System.out.println(str2.getBytes()[0]);
		System.out.println(str1.getBytes()[0] & 0x80);
		System.out.println(str2.getBytes()[0] & 0x80);
		System.out.println((str1.getBytes()[0] & 0xFF) + " " + (str1.getBytes()[1] & 0xFF) + " " + (str1.getBytes()[2] & 0xFF));
		System.out.println((str2.getBytes()[0] & 0xFF) + " " + (str2.getBytes()[1] & 0xFF) + " " + (str2.getBytes()[2] & 0xFF));
		
		byte[] bt = {str1.getBytes()[0], str1.getBytes()[1], str1.getBytes()[2]};
		byte[] bt2 = {str2.getBytes()[0], str2.getBytes()[1], str2.getBytes()[2]};
		String str3 = new String(bt,"utf-8" );
		String str4 = new String(bt2,"utf-8" );
		System.out.println(str3);
		System.out.println(str4);
		
		// hex -> to int
		int hexint = Integer.parseInt("A1",16);
		System.out.println(hexint);
		System.out.println(Integer.toHexString(hexint) );
		assertTrue(isValidUTF8(str2.getBytes()) );
		
		// hex -> byte
		byte[] bytes = new BigInteger("a4a1",16).toByteArray();
		System.out.println(new String(bytes,"euc-kr"));
		
	}
	catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static boolean isValidUTF8( byte[] input ) {

    CharsetDecoder cs = Charset.forName("UTF-8").newDecoder();

    try {
        cs.decode(ByteBuffer.wrap(input));
        return true;
    }
    catch(CharacterCodingException e){
        return false;
    }       
}
}
