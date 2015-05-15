package test.date;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.junit.Test;

public class DateTest {

	@Test
	public void test() {
		// 현제 시간 오후4시 10분 -> 1610 
		SimpleDateFormat hhmi = new SimpleDateFormat("kkmm");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,16);
		cal.set(Calendar.MINUTE,10);
		String hm=hhmi.format(cal.getTime());
		assertEquals(hm, "1610");
		 
	}

}
