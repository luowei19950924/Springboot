package com.example.springboot.dao;

import com.example.springboot.vo.UserNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用戶持久层操作
 * @author luowei
 * @date 2019/5/14 9:00
 */
@Component
public interface UserRepositoryNeo4j extends Neo4jRepository<UserNode,Long> {

    @Query("MATCH (n:User) RETURB n")
    List<UserNode> getUserNodeList();
    @Query("create (n:User{age:{age},name:{name}}) RETURN n")
    List<UserNode> addUserNodeList(@Param("name") String name,@Param("age") Integer age);

}
