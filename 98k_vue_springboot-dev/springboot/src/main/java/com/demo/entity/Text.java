package com.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class Text {
    @Id
    private Integer tid;
    private String author;
    private String name;
    private String path;
    private String tag;
    private String picture;
    private Timestamp time;
    private String size;
    private Integer up;
    private Integer collection;
}
