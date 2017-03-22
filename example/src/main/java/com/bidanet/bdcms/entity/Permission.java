package com.bidanet.bdcms.entity;

import javax.persistence.*;

/**
 * Created by xuejike on 2017/3/6.
 */
@Entity
@Table(name = "_permission")
public class Permission {
    private Long id;
    private String name;
    private String description;
    private String group;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name = "_group")
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
