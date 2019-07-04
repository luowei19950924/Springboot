package com.lw.mybatisplus.pojo;

import lombok.Data;

/**
 *
 * @author luowei
 * @date 2019/5/22 15:54
 */
@Data //@Data 注解可以生成 getter / setter、equals、hashCode，以及 toString，是个总和的选项。
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
