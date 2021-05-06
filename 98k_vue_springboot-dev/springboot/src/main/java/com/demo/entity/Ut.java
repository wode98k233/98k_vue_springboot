package com.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
class PrimaryKey implements Serializable {

    private Integer uid;

    private Integer tid;
}

@Entity
@Data
@Table(name = "ut")
@IdClass(PrimaryKey.class)
@EqualsAndHashCode()
@DynamicUpdate
public class Ut {
    @Id
    @Column(name = "uid", nullable = false)
    private Integer uid;
    @Id
    @Column(name = "tid", nullable = false)
    private Integer tid;
    private String name;
    private String path;
    private String author;
    private Integer up;
    private Integer collection;
}
