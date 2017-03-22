package com.bidanet.bdcms.entity;

import javax.persistence.*;

/**
 * Created by xuejike on 2017/3/5.
 */
@Entity
@Table(name = "_company")
public class Company {
    private Long id;
    /**
     * 公司名称
     */
    private String name;
    private String description;
    /**
     * 公司最大允许的员工数
     */
    private Long maxUserNum;
    /**
     * 公司已有用户数
     */
    private Long haveUserNum;

    /**
     * 乐观锁 版本号
     */
    private Long version;

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

    @Column(name = "max_user_num")
    public Long getMaxUserNum() {
        return maxUserNum;
    }

    public void setMaxUserNum(Long maxUserNum) {
        this.maxUserNum = maxUserNum;
    }
    @Column(name = "have_user_num")
    public Long getHaveUserNum() {
        return haveUserNum;
    }

    public void setHaveUserNum(Long haveUserNum) {
        this.haveUserNum = haveUserNum;
    }

    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
