package com.bidanet.hibernate.lambda.core;

/**
 * Created by xuejike on 2017/3/28.
 */
public interface OrderCriteria<T> {
    LambdaCriteria<T> orderDesc(String propertyName);

    LambdaCriteria<T> orderAsc(String propertyName);
}
