package test.String;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void duplicationTest(){
		String[] arr1 = {"key1","key2","key3","key3"};
		String[] arr2 = {"1","2","3","4"};
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		// 중복 제거
		for(int i=0; i<arr1.length; i++){
			if(!list1.contains(arr1[i])){
				list1.add(arr1[i]);
				list2.add(arr2[i]);
			}
		}
		System.out.println(list2.toString());
	}

}
