package com.bidanet.bdcms.entity;

import com.bidanet.bdcms.entity.enumType.Sex;
import com.bidanet.hibernate.lambda.annotation.LambdaJoinQuery;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * Created by xuejike on 2017/2/26.
 */
@Entity
@Table(name = "_user")
public class User {
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     *
     */
    private String pwd;
    private String pwdSale;
    private String nickname;
    private String name;
    private String mobile;
    private Sex sex;
    private Long companyId;


    //关联属性
    private Company company;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Column(name = "pwd_sale")
    public String getPwdSale() {
        return pwdSale;
    }

    public void setPwdSale(String pwdSale) {
        this.pwdSale = pwdSale;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Column(name = "_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Enumerated(EnumType.ORDINAL)
    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
    @Column(name = "company_id")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id",insertable = false,updatable = false)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
