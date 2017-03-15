package com.bidanet.hibernate.lambda.query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * In
 */
public class InQueryListAction<T> extends QueryListAction<T> {
    public InQueryListAction(Class<T> zclass) {
        super(zclass);
    }

    @Override
    protected void createCriteron(Criteria criteria, String key, List<Object> list) {
        Criterion criterion = Restrictions.in(key, list);
        criteria.add(criterion);

    }
}
