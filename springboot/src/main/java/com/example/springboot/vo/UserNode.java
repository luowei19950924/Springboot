package com.example.springboot.vo;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * neo4j实体
 * @author luowei
 * @date 2019/5/14 9:03
 */
@NodeEntity //标识节点类型
public class UserNode {

    @GraphId
    private Long nodeId;

    @Property
    private String userId;

    @Property
    private String name;

    @Property
    private Integer age;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
