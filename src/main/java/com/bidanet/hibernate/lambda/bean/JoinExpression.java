package com.bidanet.hibernate.lambda.bean;

import com.bidanet.hibernate.lambda.core.WhereCriteria;
import org.hibernate.sql.JoinType;

/**
 * Created by xuejike on 2017/3/22.
 */
public class JoinExpression {
    protected String property;
    protected JoinType joinType;
    protected WhereCriteria whereCriteria;

    public JoinExpression() {
    }

    public JoinExpression(String property, JoinType joinType, WhereCriteria whereCriteria) {
        this.property = property;
        this.joinType = joinType;
        this.whereCriteria = whereCriteria;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    public WhereCriteria getWhereCriteria() {
        return whereCriteria;
    }

    public void setWhereCriteria(WhereCriteria whereCriteria) {
        this.whereCriteria = whereCriteria;
    }
}
