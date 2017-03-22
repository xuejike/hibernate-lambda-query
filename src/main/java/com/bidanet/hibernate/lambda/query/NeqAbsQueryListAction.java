package com.bidanet.hibernate.lambda.query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * 不等于
 */
public class NeqAbsQueryListAction<T> extends AbsQueryListAction<T> {
    public NeqAbsQueryListAction(Class<T> zclass) {
        super(zclass);
    }

    @Override
    protected Criterion createCriterion(String key, Object val) {
        if (val==null){
            return Restrictions.isNotNull(key);
        }else{
            return Restrictions.ne(key, val);
        }
    }
}
