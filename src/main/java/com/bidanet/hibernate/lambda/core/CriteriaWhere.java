package com.bidanet.hibernate.lambda.core;

/**
 * Created by xuejike on 2017/3/10.
 */
public interface CriteriaWhere<T> {
    CriteriaList<T> eq(QueryOne<T> queryOne);

    CriteriaList<T> like(QueryOne<T> queryOne);

    CriteriaList<T> ne(QueryOne<T> queryOne);
}
