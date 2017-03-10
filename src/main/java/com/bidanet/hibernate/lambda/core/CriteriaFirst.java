package com.bidanet.hibernate.lambda.core;

/**
 * Created by xuejike on 2017/3/10.
 */
public interface CriteriaFirst<T> {
    T first();

    void first(QueryOne<T> result);
}
