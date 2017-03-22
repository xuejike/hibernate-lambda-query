package com.bidanet.bdcms.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * Created by xuejike on 2017/3/5.
 */
@Entity
@Table(name = "_department")
public class Department {
    private Long id;
    /**
     * 公司ID
     */
    private Long companyId;
    /**
     * 父节点ID
     */
    private Long parentId;
    /**
     * 部门名称
     */
    private String name;

    private Company company;
    private Department department;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "company_id")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Company getCompany() {
        return company;
    }


    public void setCompany(Company company) {
        this.company = company;
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
}
