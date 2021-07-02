package com.lqm.springboot.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class User  {
    private Integer id;

    private String name;

    private Integer age;

    private Long wealth;

    private Date createTime;

    private Integer version;

}