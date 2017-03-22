package com.bidanet.hibernate.lambda.core;

/**
 * Created by xuejike on 2017/3/10.
 */
public interface CountCriteria {
    Long count();

    Long count(String property);
}
