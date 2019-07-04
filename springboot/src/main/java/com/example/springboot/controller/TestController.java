package com.example.springboot.controller;

import com.example.springboot.dao.UserDao;
import com.example.springboot.service.AsyncService;
import com.example.springboot.vo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.concurrent.Future;

/**
 * @author luowei
 * @date 2019/5/7 20:38
 */
@RestController
public class TestController {

    @Resource
    private UserDao userDao;

    @RequestMapping("/save/{id}")
    public String addUser(@PathVariable Integer id) {
        User user = new User();
        user.setId(12);
        user.setName("易磊");
        user.setAge(21);
        user.setEmail("yilei@ruiec.cn");
        userDao.add(user);
        return "success";
    }

    @Resource
    private AsyncService asyncService;

    //定义消息转换器 解决返回中文乱码 默认配置了无需配置
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @RequestMapping("/async")
    @ResponseBody
    public String asyncTest() {
        long start = System.currentTimeMillis();
        Future<String> task1 = asyncService.doTask1();
        Future<String> task2 = asyncService.doTask2();
        Future<String> task3 = asyncService.doTask3();
        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        return "全部执行完成，总耗时：" + (end - start);
    }


}
