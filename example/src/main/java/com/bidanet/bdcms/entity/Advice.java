package com.bidanet.bdcms.entity;

import com.bidanet.bdcms.entity.enumType.AdviceFlow;
import com.bidanet.bdcms.entity.enumType.AdviceLevel;
import com.bidanet.bdcms.entity.enumType.AdviceStatus;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 *  建议基本信息
 */
@Entity
@Table(name = "_advice")
public class Advice {
    protected Long id;
    /**
     * 标题
     */
    protected String title;
    /**
     * 问题描述
     */
    protected String questionDescribe;
    /**
     * 发起者部门ID
     */
    protected Long departmentId;
    /**
     * 改善前图片
     */
    protected String beforeImages;
    /**
     * 发起者UID
     */
    protected Long uid;
    /**
     * 创建时间
     */
    protected Long createTime;
    /**
     * 建议类型
     */
    protected Long adviceTypeId;
    /**
     * 改善后的图片
     */
    protected String afterImages;
    /**
     * 建议状态
     */
    protected AdviceStatus adviceStatus;
    /**
     * 处理者UID
     */
    protected Long dealUid;
    /**
     * 处理者角色ID
     */
    protected Long dealRoleId;
    /**
     * 处理者部门ID
     */
    protected Long dealDepartmentId;
    /**
     *处理用户姓名
     */
    protected String dealUserName;
    /**
     *建议级别
     */
    protected AdviceLevel level;
    /**
     *处理时间
     */
    protected Long dealTime;
    /**
     *处理结果
     */
    protected Boolean dealResult;
    /**
     * 处理结果原因
     */
    protected String dealReason;
    /**
     * 流程状态
     */
    protected AdviceFlow adviceFlow;
    /**
     * 流程进度 每一次流转将递增
     */
    protected Integer flowIndex;

    /**
     * 是否是最新流转
     */
    protected Boolean lastFlow;
    /**
     * 评分
     */
    protected Float score;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "question_describe")
    public String getQuestionDescribe() {
        return questionDescribe;
    }

    public void setQuestionDescribe(String questionDescribe) {
        this.questionDescribe = questionDescribe;
    }

    @Column(name = "department_id")
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    @Column(name = "before_images")
    @Type(type = "text")
    public String getBeforeImages() {
        return beforeImages;
    }

    public void setBeforeImages(String beforeImages) {
        this.beforeImages = beforeImages;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    @Column(name = "advice_type_id")
    public Long getAdviceTypeId() {
        return adviceTypeId;
    }

    public void setAdviceTypeId(Long adviceTypeId) {
        this.adviceTypeId = adviceTypeId;
    }
    @Column(name = "after_images")
    @Type(type = "text")
    public String getAfterImages() {
        return afterImages;
    }

    public void setAfterImages(String afterImages) {
        this.afterImages = afterImages;
    }
    @Column(name = "after_status")
    public AdviceStatus getAdviceStatus() {
        return adviceStatus;
    }

    public void setAdviceStatus(AdviceStatus adviceStatus) {
        this.adviceStatus = adviceStatus;
    }

    @Column(name = "deal_uid")
    public Long getDealUid() {
        return dealUid;
    }

    public void setDealUid(Long dealUid) {
        this.dealUid = dealUid;
    }
    @Column(name = "deal_role_id")
    public Long getDealRoleId() {
        return dealRoleId;
    }

    public void setDealRoleId(Long dealRoleId) {
        this.dealRoleId = dealRoleId;
    }

    @Column(name = "deal_department_id")
    public Long getDealDepartmentId() {
        return dealDepartmentId;
    }

    public void setDealDepartmentId(Long dealDepartmentId) {
        this.dealDepartmentId = dealDepartmentId;
    }
    @Column(name = "deal_user_name")
    public String getDealUserName() {
        return dealUserName;
    }

    public void setDealUserName(String dealUserName) {
        this.dealUserName = dealUserName;
    }

    @Enumerated(EnumType.ORDINAL)
    public AdviceLevel getLevel() {
        return level;
    }

    public void setLevel(AdviceLevel level) {
        this.level = level;
    }

    @Column(name = "deal_time")
    public Long getDealTime() {
        return dealTime;
    }

    public void setDealTime(Long dealTime) {
        this.dealTime = dealTime;
    }
    @Column(name = "deal_result")
    public Boolean getDealResult() {
        return dealResult;
    }

    public void setDealResult(Boolean dealResult) {
        this.dealResult = dealResult;
    }
    @Column(name = "deal_reason")
    public String getDealReason() {
        return dealReason;
    }

    public void setDealReason(String dealReason) {
        this.dealReason = dealReason;
    }
    @Column(name = "advice_flow")
    public AdviceFlow getAdviceFlow() {
        return adviceFlow;
    }

    public void setAdviceFlow(AdviceFlow adviceFlow) {
        this.adviceFlow = adviceFlow;
    }
    @Column(name = "flow_index")
    public Integer getFlowIndex() {
        return flowIndex;
    }

    public void setFlowIndex(Integer flowIndex) {
        this.flowIndex = flowIndex;
    }

    @Column(name = "last_flow")
    public Boolean getLastFlow() {
        return lastFlow;
    }

    public void setLastFlow(Boolean lastFlow) {
        this.lastFlow = lastFlow;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
