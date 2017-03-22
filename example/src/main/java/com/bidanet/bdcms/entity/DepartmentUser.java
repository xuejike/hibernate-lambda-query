package com.bidanet.bdcms.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * Created by xuejike on 2017/3/5.
 */
@Entity
@Table(name = "_department_user")
public class DepartmentUser {
    private Long id;
    private Long departmentId;
    private Long uid;


    private Department department;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "department_id")
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
