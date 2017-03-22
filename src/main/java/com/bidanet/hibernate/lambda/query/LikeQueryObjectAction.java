package com.bidanet.hibernate.lambda.query;

import com.bidanet.hibernate.lambda.exception.LambdaQueryException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * Like 查询实现
 */
public class LikeQueryObjectAction<T> extends AbsQueryObjectAction<T> {
    public LikeQueryObjectAction(Class<T> zclass) {
        super(zclass);
    }

    @Override
    protected Criterion createCriterion(String key, Object val) {
        if (val instanceof String){
            return Restrictions.like(key,val.toString(), MatchMode.EXACT);
        }else{
            throw new LambdaQueryException("Like 查询 参数 必须是String 类型");
        }

    }
}
