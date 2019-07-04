package com.luowei.springcloudeureka.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 测试文件 （断章取义）
 * @author luo_wei<br>
 * @date 2019年4月23日 下午2:29:10
 */
public class FileTest {

    public static void main(String[] args) {
        String fileName = "E:\\idea-项目空间\\springcloud-eureka\\src\\main\\resources\\595924.txt";
        sub(fileName);
    }

    /**
     * 裁剪文本
     * @author luo_wei<br>
     * @date 2019年4月23日 下午5:02:36
     */
    @SuppressWarnings("resource")
    public static void sub(String fileUrl) {
        System.out.println(fileUrl);
        InputStreamReader isr;
        try {
            int line = 60;
            String start = "第0001章 申奥成功";
            String end = "第0002章";
            isr = new InputStreamReader(new FileInputStream(fileUrl), "UTF-8");
            BufferedReader read = new BufferedReader(isr);
            String s = null;
            Boolean flag = false;
            while ((s = read.readLine()) != null) {
                if (s.contains(start)) {
                    flag = true;
                }
                if (s.contains(end)) {
                    flag = false;
                }
                if (flag) {
                    //优化换行
                    if (s.length() > line) {
                        for (int i = 0; i < s.length()/line; i++) {
                            System.out.println(s.substring(line*i,line*(i+1)));
                        }
                        System.out.println(s.substring(line*(s.length()/line), s.length()));
                    } else {
                        System.out.println(s);
                    }
                }
            }
            System.out.println("OK！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}