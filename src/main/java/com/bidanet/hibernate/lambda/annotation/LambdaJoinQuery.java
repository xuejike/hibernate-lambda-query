package com.bidanet.hibernate.lambda.annotation;

import org.hibernate.sql.JoinType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by xuejike on 2017/3/22.
 */
@Target(ElementType.METHOD)
public @interface LambdaJoinQuery {
    public JoinType joinType() default JoinType.INNER_JOIN;
}
