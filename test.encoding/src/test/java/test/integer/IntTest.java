package test.integer;

import static org.junit.Assert.assertEquals;

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
}
