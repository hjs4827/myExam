package test.quartzScheduler;

/* static import */
import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.CalendarIntervalScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.DateBuilder.*;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;


/**
 * QuartzScheduler 샘플
 * 1. 공식 사이트 http://quartz-scheduler.org/
 * 2. 문서 2.2.1버전 기준 http://quartz-scheduler.org/generated/2.2.1/html/qs-all/
 * 3. import에서 static 되어 있는 부분 주의
 * 4. job돌리는 클래스에는 Job 클래스를 implements 해야됨.
 * @author hjs4827
 *
 */
public class QuartzScheduler {
	
	public static void main(String[] args){
		startScheduler();
	}
	
	public static void startScheduler(){
		try {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();
			// Define the job
			JobDetail job = newJob(HelloWorldJob.class).withIdentity("myFirstJob", "group1").build();
			 
			// Trigger the job to run now, and repeat every 40 seconds
			Trigger trigger = newTrigger()
				.withIdentity("myFirstTrigger","group1")
				.startNow()
				.usingJobData("introduce","hello i'm Quartz")
				.withSchedule(
				              simpleSchedule()
				              .withIntervalInSeconds(10)
				              .repeatForever()
				              )
				.build();
			 
			// Tell quartz to schedule the job using our trigger
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		}
		catch (SchedulerException e) {
			// 
			e.printStackTrace();
		}
	}
}
