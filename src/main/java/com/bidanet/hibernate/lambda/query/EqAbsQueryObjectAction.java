package com.bidanet.hibernate.lambda.query;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * 等号 查询实现
 */
public class EqAbsQueryObjectAction<T> extends AbsQueryObjectAction<T> {
    public EqAbsQueryObjectAction(Class<T> zclass) {
        super(zclass);
    }

    @Override
    protected Criterion createCriterion(String key, Object val) {
        if (val==null){
            return Restrictions.isNull(key);
        }else{
            return Restrictions.eq(key,val);
        }
//        return ;
    }
}
