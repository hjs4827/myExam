package test.quartzScheduler;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class HelloWorldJob implements Job{
	public void sayHello(){
		System.err.println("hello world~!");
	}

	public void execute(JobExecutionContext paramJobExecutionContext) throws JobExecutionException {
		// 잡 실행
		sayHello();
		
		// get job dataMap
		JobKey key = paramJobExecutionContext.getJobDetail().getKey();
		JobDataMap dataMap = paramJobExecutionContext.getJobDetail().getJobDataMap();
		String introduce = dataMap.getString("introduce");
		System.out.println("instance >>"+key);
		System.out.println("say Quartz >>"+introduce);
	}
}
