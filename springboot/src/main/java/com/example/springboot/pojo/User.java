package com.example.springboot.pojo;

import javax.persistence.*;

/**
 * 用户实体
 * @author luowei
 * @date 2019/5/8 19:01
 */
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    private Integer age;
    private String email;
    /**
     * fast格式化日期
     */
    //@JSONField(format = "yyyy-MM-dd HH:mm:ss")
    //private Date date;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
