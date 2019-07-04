package com.example.springboot.service;

import com.example.springboot.pojo.User;

/**
 * @author luowei
 * @date 2019/5/13 8:48
 */
public interface UserServiceJpa {

    void addUser(User user);

    public String findRedis();
}
