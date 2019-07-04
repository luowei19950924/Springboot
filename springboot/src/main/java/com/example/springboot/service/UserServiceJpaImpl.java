package com.example.springboot.service;

import com.example.springboot.dao.UserDaoJpa;
import com.example.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.List;

/**
 * @author luowei
 * @date 2019/5/13 8:49
 */
@Service
public class UserServiceJpaImpl implements UserServiceJpa{

    @Resource
    private UserDaoJpa userDaoJpa;
    @Resource
    private JedisCluster jedis;
    @Autowired
    private MongoTemplate mongoTemplate;


    //@Cacheable(value = "myname")
    public void addUser(User user){
        System.out.println("从数据库中查询。。。");
        userDaoJpa.save(user);
    }

    public String findRedis(){
        jedis.set("haha","kele");
        return jedis.get("haha");
    }

    public List<User> findUser(){
        return mongoTemplate.findAll(User.class);
    }

}
