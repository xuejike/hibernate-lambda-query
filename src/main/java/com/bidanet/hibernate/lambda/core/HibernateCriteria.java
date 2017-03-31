package com.bidanet.hibernate.lambda.core;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;

/**
 * Created by xuejike on 2017/3/31.
 */
public interface HibernateCriteria {
    LambdaCriteria addCriterion(Criterion criterion);

    LambdaCriteria addProjection(Projection projection);
}
