package com.bidanet.bdcms.entity;

import javax.persistence.*;

/**
 * Created by xuejike on 2017/3/6.
 */
@Entity
@Table(name = "_menu")
public class Menu {
    private Long id;
    private String name;
    private Long parentId;
    private String url;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
