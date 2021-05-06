package com.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
class PrimaryKey2 implements Serializable {

    private Integer uid;

    private Integer vipuid;
}
@Entity
@Data
@Table(name = "uu")
@IdClass(PrimaryKey2.class)
@EqualsAndHashCode()
@DynamicUpdate
public class Uu {
    @Id
    @Column(name = "uid", nullable = false)
    private Integer uid;
    @Id
    @Column(name = "vipuid", nullable = false)
    private Integer vipuid;
    private String name;
    private String vipname;
    private Integer vip;
}
