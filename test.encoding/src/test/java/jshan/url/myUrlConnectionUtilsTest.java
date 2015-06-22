package jshan.url;

import org.junit.Test;


public class myUrlConnectionUtilsTest {
	
	@Test
	public void getUrlContentTest(){
		String contents = myUrlConnectionUtils.getUrlContent("http://localhost/servlet/altoolbarApi?type=urlWithScDownload");
		System.out.println(contents);
	}
}
