package test.String;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringTest {

	@Test
	public void test() {
		String value = "https://site.com/subdir/test.do";
		String[] valueList = value.split("/");
		assertEquals(valueList[0], "https:");
		assertEquals(valueList[1], "");
		assertEquals(valueList[2], "site.com");
		for (String string : valueList) {
			System.out.println(string);
		}
	}
	
	@Test
	public void StringBufferTest(){
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 5000000; i++) {
			stringBuffer.append("test");
		}
	}
	
	@Test
	public void StringBuildTest(){
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 5000000; i++) {
			stringBuilder.append("test");
		}
	}

}
