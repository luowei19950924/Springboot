package com.example.springboot.mapper;

import com.example.springboot.vo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author luowei
 * @date 2019/5/10 14:42
 */
/*@Qualifier("db1SqlSessionFactory")*/
public interface UserMapper {

    @Select("select * from user where name=#{name}")
    User findUserByName(@Param("name") String name);
}
