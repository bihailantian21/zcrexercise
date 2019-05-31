package com.zcr.exercisetest.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

//2.创建一个类HelloScheduler.java，这个是具体触发我们的任务
public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException {
        //创建一个jobDetail的实例，将该实例与HelloJob Class绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myJob").build();
        //创建一个Trigger触发器的实例，定义该job立即执行，并且每2秒执行一次，一直执行
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();
        //创建schedule实例
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);

    }
    //3.执行main方法，Run ‘HelloScheduler.main()’，可以看见如下效果，表明任务执行成功了
}
