package com.bidanet.bdcms.entity;

import javax.persistence.*;

/**
 * Created by xuejike on 2017/3/6.
 */
@Entity
@Table(name = "_user_role")
public class UserRole {
    private Long id;
    private Long userId;
    private Long roleId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @Column(name = "role_id")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
