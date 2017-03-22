package com.bidanet.bdcms.entity;

import javax.persistence.*;

/**
 * Created by xuejike on 2017/3/6.
 */
@Entity
@Table(name = "_role_permission")
public class RolePermission {
    private Long id;
    private Long roleId;
    private Long premissionId;
    private Long data;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "role_id")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    @Column(name = "permission_id")
    public Long getPremissionId() {
        return premissionId;
    }

    public void setPremissionId(Long premissionId) {
        this.premissionId = premissionId;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }
}
