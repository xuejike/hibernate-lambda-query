package com.bidanet.hibernate.lambda.core;

import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * Created by xuejike on 2017/3/10.
 */
public interface WhereCriteria<T> {
    LambdaCriteria<T> eq(QueryOne<T> queryOne);

    LambdaCriteria<T> eqExample(T example);

    LambdaCriteria<T> like(QueryOne<T> queryOne);

    LambdaCriteria<T> ne(QueryOne<T> queryOne);

    LambdaCriteria<T> gte(QueryOne<T> queryOne);

    LambdaCriteria<T> gt(QueryOne<T> queryOne);

    LambdaCriteria<T> lte(QueryOne<T> queryOne);

    LambdaCriteria<T> lt(QueryOne<T> queryOne);

    LambdaCriteria<T> in(QueryOne<T> queryOne);
    List<Criterion> getCriterionList();
}
