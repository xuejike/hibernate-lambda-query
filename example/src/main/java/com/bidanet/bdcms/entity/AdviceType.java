package com.bidanet.bdcms.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * Created by xuejike on 2017/3/6.
 */
@Entity
@Table(name = "_advice_type")
public class AdviceType {
    private Long id;
    private String name;
    private Long departmentId;
    private Long companyId;

    private Department department;
    private Company company;


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

    @Column(name = "department_id")
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name = "company_id")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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
    @JoinColumn(name = "company_id",insertable = false,updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
