package com.example.springboot.dao;

import com.example.springboot.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 用户持久层操作类-JdbcTemplate
 * @author luowei
 * @date 2019/5/10 14:01
 */
@Service
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(User user){
        jdbcTemplate.update("insert into user (id,name,age,email) values (?,?,?,?)",
                new Object[]{user.getId(),user.getName(),user.getAge(),user.getEmail()});
    }
}
