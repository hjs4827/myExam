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
}
