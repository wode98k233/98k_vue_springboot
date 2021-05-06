package com.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
@Entity
@Data
public class Message {
    @Id
    private Integer mid;
    private Integer fid;
    private String fname;
    private Integer sid;
    private String sname;
    private String text;
    private Timestamp time;

}
