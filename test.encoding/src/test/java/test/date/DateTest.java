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
	
	@Test
	public void CalendarTest(){
		Calendar today = Calendar.getInstance();    
        System.out.println("이 해의 년도 : " + today.get(Calendar.YEAR));
        System.out.println("월(0~11, 0:1월): " + today.get(Calendar.MONTH));
        // (today.get(Calendar.MONTH) + 1)) 이런 형식으로 하면 다음월을 받아 올 수 
        // 있다. today.get(Calendar.MONTH) + 1로 하면 이상한 값이 나온다. (괄호유무)
        System.out.println("월(0~11, 0:1월): " + (today.get(Calendar.MONTH) + 1));
        System.out.println("이 해의 몇 째 주: " + today.get(Calendar.WEEK_OF_YEAR));
        System.out.println("이 달의 몇 째 주: " + today.get(Calendar.WEEK_OF_MONTH));
        // DATE와 DAY_OF_MONTH는 같다.
        System.out.println("이 달의 몇 일: " + today.get(Calendar.DATE));
        System.out.println("이 달의 몇 일: " + today.get(Calendar.DAY_OF_MONTH));
        System.out.println("이 해의 몇 일: " + today.get(Calendar.DAY_OF_YEAR));
        // 1:일요일, 2:월요일, ... 7:토요일
        System.out.println("요일(1~7, 1:일요일): " + today.get(Calendar.DAY_OF_WEEK)); 
        System.out.println("이 달의 몇 째 요일: " + today.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("오전_오후(0:오전, 1:오후): " + today.get(Calendar.AM_PM));
        System.out.println("시간(0~11): " + today.get(Calendar.HOUR));
        System.out.println("시간(0~23): " + today.get(Calendar.HOUR_OF_DAY));
        System.out.println("분(0~59): " + today.get(Calendar.MINUTE));
        System.out.println("초(0~59): " + today.get(Calendar.SECOND));
        System.out.println("1000분의 1초(0~999): " + today.get(Calendar.MILLISECOND));
        // 천분의 1초를 시간으로 표시하기 위해 3600000으로 나누었다.(1시간 = 60 * 60초)
        System.out.println("TimeZone(-12~+12): " + 
        (today.get(Calendar.ZONE_OFFSET)/(60*60*1000))); 
        // 이 달의 마지막 일을 찾는다.
        System.out.println("이 달의 마지막 날: " + today.getActualMaximum(Calendar.DATE) );
	}
	@Test
	public void dayTest(){
		Calendar cal = Calendar.getInstance();
		int date = cal.get(Calendar.DATE);
		System.out.println("date>>>>"+ date);
		date = cal.get(Calendar.DATE);
		System.out.println("date>>>>"+ date);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println("day>>>>"+day);
	}
	
	@Test
	public void CalendarTest2(){
		 // 요일은 1부터 시작하기 때문에, DAY_OF_WEEK[0]은 비워두었다.
        final String[] DAY_OF_WEEK = {"", "일", "월", "화", "수", "목", "금", "토"};
 
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
 
        // month의 경우 0부터 시작하기 때문에 8월인 경우, 7로 지정해야한다.
            // date1.set(2005, Calendar.AUGUST, 15);와 같이 할 수도 있다.
        date1.set(2005, 7, 15); // 2005년 8월 15일로 날짜를 설정한다. 
        System.out.println("date1은 "+ calendarToString(date1) + 
                DAY_OF_WEEK[date1.get(Calendar.DAY_OF_WEEK)]+"요일이고,");
        System.out.println("오늘(date2)은 " + calendarToString(date2) + 
                DAY_OF_WEEK[date2.get(Calendar.DAY_OF_WEEK)]+"요일입니다.");
 
        // 두 날짜간의 차이를 얻으려면, getTimeInMillis()를 이용해서 
        // 천분의 일초 단위로 변환해야한다.
        long difference = (date2.getTimeInMillis() - date1.getTimeInMillis())/1000;
        System.out.println("그 날(date1)부터 지금(date2)까지 " + difference +"초가 지났습니다");
        // 1일 = 24 * 60 * 60
        System.out.println("일(day)로 계산하면 " + difference/(24*60*60) +"일입니다.");
	}
	
	public String calendarToString(Calendar date){
        return date.get(Calendar.YEAR)+"년 "
                + (date.get(Calendar.MONTH)+1) + "월 "
                + date.get(Calendar.DATE) + "일 ";
	}
	
	@Test
	public void CalendarSetTest(){
		final int[] TIME_UNIT = {3600, 60, 1}; // 큰 단위를 앞에 놓는다.
        final String[] TIME_UNIT_NAME = {"시간 ", "분 ", "초"};
 
        Calendar time1 = Calendar.getInstance();
        Calendar time2 = Calendar.getInstance();
 
        // time1의 시간을 10시 20분 30초로 설정한다.
        time1.set(Calendar.HOUR_OF_DAY, 10);
        time1.set(Calendar.MINUTE, 20);
        time1.set(Calendar.SECOND, 30);
 
        // time2의 시간을 20시 30분 10초로 설정한다.
        time2.set(Calendar.HOUR_OF_DAY, 20);
        time2.set(Calendar.MINUTE, 30);
        time2.set(Calendar.SECOND, 10);
 
        System.out.println("time1 :"+time1.get(Calendar.HOUR_OF_DAY)+"시 " +
        time1.get(Calendar.MINUTE) +"분 " + time1.get(Calendar.SECOND) + "초");
        System.out.println("time2 :"+time2.get(Calendar.HOUR_OF_DAY)+"시 " +
        time2.get(Calendar.MINUTE) +"분 " + time2.get(Calendar.SECOND) + "초");
 
        long difference = Math.abs(time2.getTimeInMillis() - time1.getTimeInMillis())/1000;
        System.out.println("time1과 time2의 차이는 "+ difference +"초 입니다.");
 
        String tmp = "";
        for(int i=0; i < TIME_UNIT.length;i++) { 
              tmp += difference/TIME_UNIT[i]+ TIME_UNIT_NAME[i]; 
              difference %= TIME_UNIT[i];
        } 
 
        System.out.println("시분초로 변환하면 " + tmp + "입니다.");
	}

}
