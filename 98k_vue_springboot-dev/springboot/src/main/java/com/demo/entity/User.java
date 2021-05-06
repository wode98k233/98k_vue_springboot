package com.demo.entity;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
@Data
public class User {
    @Id
    private Integer id;
    private String name;
    private String password;
    private String sex;
    //权限，0为用户，1为管理员
    private Integer jurisdiction;
    private String email;
    private String pic;
    private Timestamp btime;
    private String phone;

}
