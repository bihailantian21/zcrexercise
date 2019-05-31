package com.zcr.exercisetest.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class HelloJob1 implements Job {

    public HelloJob1() {
    }


    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println("-------start---------");
        System.out.println("Hello World! - " + new Date());
        System.out.println("-------end---------");
    }

}