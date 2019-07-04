package com.example.springboot.dao;

import com.example.springboot.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author luowei
 * @date 2019/5/13 8:46
 */
@Service
public interface UserDaoJpa extends JpaRepository<User,Integer> {

}
