package com.bidanet.hibernate.lambda.query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * Created by xuejike on 2017/3/10.
 */
public interface QueryAction<T> {
    T getProxyBean();

    void buildCriteria(Criteria criteria);

    List<Criterion> getCriterionList();
}
