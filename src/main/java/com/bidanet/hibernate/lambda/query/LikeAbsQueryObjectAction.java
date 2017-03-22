package com.bidanet.hibernate.lambda.query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * Like 查询实现
 */
public class LikeAbsQueryObjectAction<T> extends AbsQueryObjectAction<T> {
    public LikeAbsQueryObjectAction(Class<T> zclass) {
        super(zclass);
    }

    @Override
    protected Criterion createCriterion(String key, Object val) {
        return Restrictions.like(key,val.toString(), MatchMode.EXACT);
    }
}
