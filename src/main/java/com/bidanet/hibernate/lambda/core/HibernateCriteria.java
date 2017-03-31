package com.bidanet.hibernate.lambda.core;

import org.hibernate.criterion.Criterion;

/**
 * Created by xuejike on 2017/3/31.
 */
public interface HibernateCriteria {
    LambdaCriteria addProjection(Criterion criterion);
}
