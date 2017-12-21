package test.integer;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class IntTest {
	@Test
	public void intTest() {
		int num = 0;
		String numStr = "test";
		try {
			num = Integer.parseInt(numStr);
		}
		catch (Exception e) {
			System.out.println("occur exception");
			// TODO: handle exception
		}
		assertEquals(0, num);
	}
	
	@Test
	public void randomTest(){
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			System.out.println(new Random().nextInt(10));
		}
	}
	
	@Test
	public void 소수점만구하기테스트(){
	  double num1 = 12.99;
	  double result = num1-Math.floor(num1);
	  System.out.println(Double.parseDouble(String.format("%.2f",result)) );
	}

}
