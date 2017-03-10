package com.bidanet.hibernate.lambda.query;

import org.hibernate.Criteria;

/**
 * Created by xuejike on 2017/3/10.
 */
public interface QueryAction<T> {
    T getProxyBean();
    void buildCriteria(Criteria criteria);
}
