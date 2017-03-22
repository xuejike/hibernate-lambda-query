package com.bidanet.hibernate.lambda.query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * In
 */
public class InQueryListAction<T> extends AbsQueryListAction<T> {
    public InQueryListAction(Class<T> zclass) {
        super(zclass);
    }

    @Override
    protected Criterion createCriterion(String key, Object val) {
        return null;
    }

    @Override
    protected List<Criterion> createCriteron(String key, List<Object> list) {
        Criterion criterion = Restrictions.in(key, list);
        ArrayList<Criterion> criteria = new ArrayList<>(1);
        criteria.add(criterion);
        return criteria;

    }
}
