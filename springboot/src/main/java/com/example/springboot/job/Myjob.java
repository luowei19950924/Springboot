package com.example.springboot.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author luowei
 * @date 2019/5/10 9:48
 */
/*@Component*/
public class Myjob {

    @Scheduled(fixedRate = 1000)
    public void run(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
