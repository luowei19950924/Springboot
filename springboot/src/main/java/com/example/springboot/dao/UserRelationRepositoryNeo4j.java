package com.example.springboot.dao;

import com.example.springboot.vo.UserRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * 用戶联系持久层操作
 * @author luowei
 * @date 2019/5/14 10:11
 */
public interface UserRelationRepositoryNeo4j extends Neo4jRepository<UserRelation,Long> {

    @Query("match p=(n:User)<-[r:UserRelation]->(n1:User) where n.userId={firstUserId} and n1.userId={secondUserId} return p")
    List<UserRelation> findUserRelationByEachId(@Param("firstUserId") String firstUserId,@Param("secondUserId") String secondUserId);

    @Query("match (fn:User),(su:User) where fn.userId={firstUserId} and su.userId={secondUserId}" +
            "create p=(fu)-[r:UserRelation]->(su) return p")
    List<UserRelation> findUserRelation(@Param("firstUserId") String firstUserId,@Param("secondUserId") String secondUserId);

}
