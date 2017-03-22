package com.bidanet.hibernate.lambda.core;

/**
 * Created by xuejike on 2017/3/10.
 */
public interface FirstCriteria<T> {
    T first();

    void first(QueryOne<T> result);
}
