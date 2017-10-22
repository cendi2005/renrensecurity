package io.renren.utils;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 简单的定时任务，使用注解
 */
public class ScheduledTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelayString = "5000")
    public void getTask1() {
        System.out.println("任务1,从配置文件加载任务信息，当前时间：" + dateFormat.format(new Date()));
    }

    @Scheduled(fixedDelayString = "5000")
    public void getTask2() {
        System.out.println("任务2,从配置文件加载任务信息，当前时间：" + dateFormat.format(new Date()));
    }
}