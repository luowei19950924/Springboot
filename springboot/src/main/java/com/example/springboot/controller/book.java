package com.example.springboot.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 类型安全的注释
 * @author luowei
 * @date 2019/4/28 19:25
 */
@ConfigurationProperties(prefix = "book") //类型安全的注释
public class book {

    private String author;

    private String name;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
