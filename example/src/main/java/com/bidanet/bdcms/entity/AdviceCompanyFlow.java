package com.bidanet.bdcms.entity;

import com.bidanet.bdcms.entity.enumType.AdviceFlow;
import com.bidanet.bdcms.entity.enumType.FlowNextUserDpt;
import com.bidanet.bdcms.entity.enumType.FlowNextUserType;

import javax.persistence.*;

/**
 * Created by xuejike on 2017/3/6.
 */
@Entity
@Table(name = "_advice_company_flow")
public class AdviceCompanyFlow {
    private Long id;

    private Long adviceTypeId;
    private Long companyId;
    private Long departmentId;

    private AdviceFlow nowFlow;
    private AdviceFlow nextFlow;
    private FlowNextUserType flowNextUserType;
    private FlowNextUserDpt flowNextUserDpt;

    private Long nextUid;
    private Long nexDepartmentId;
    private Long nextRoleId;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "advice_type_id")
    public Long getAdviceTypeId() {
        return adviceTypeId;
    }

    public void setAdviceTypeId(Long adviceTypeId) {
        this.adviceTypeId = adviceTypeId;
    }

    @Column(name = "company_id")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Column(name = "department_id")
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name = "now_flow")
    @Enumerated(value = EnumType.ORDINAL)
    public AdviceFlow getNowFlow() {
        return nowFlow;
    }

    public void setNowFlow(AdviceFlow nowFlow) {
        this.nowFlow = nowFlow;
    }
    @Column(name = "next_flow")
    @Enumerated(value = EnumType.ORDINAL)
    public AdviceFlow getNextFlow() {
        return nextFlow;
    }

    public void setNextFlow(AdviceFlow nextFlow) {
        this.nextFlow = nextFlow;
    }
    @Column(name = "flow_next_user_type")
    @Enumerated(value = EnumType.ORDINAL)
    public FlowNextUserType getFlowNextUserType() {
        return flowNextUserType;
    }

    public void setFlowNextUserType(FlowNextUserType flowNextUserType) {
        this.flowNextUserType = flowNextUserType;
    }
    @Column(name = "flow_next_user_dpt")
    @Enumerated(value = EnumType.ORDINAL)
    public FlowNextUserDpt getFlowNextUserDpt() {
        return flowNextUserDpt;
    }

    public void setFlowNextUserDpt(FlowNextUserDpt flowNextUserDpt) {
        this.flowNextUserDpt = flowNextUserDpt;
    }
    @Column(name = "next_uid")
    public Long getNextUid() {
        return nextUid;
    }

    public void setNextUid(Long nextUid) {
        this.nextUid = nextUid;
    }
    @Column(name = "next_department_id")
    public Long getNexDepartmentId() {
        return nexDepartmentId;
    }

    public void setNexDepartmentId(Long nexDepartmentId) {
        this.nexDepartmentId = nexDepartmentId;
    }

    public Long getNextRoleId() {
        return nextRoleId;
    }

    public void setNextRoleId(Long nextRoleId) {
        this.nextRoleId = nextRoleId;
    }




}
