package com.bidanet.hibernate.lambda.query;

import com.bidanet.hibernate.lambda.core.QueryOne;
import com.bidanet.hibernate.lambda.proxy.GeterSeterMethodInterceptor;
import org.hibernate.criterion.Criterion;

/**
 * Created by xuejike on 2017/3/10.
 */
public abstract class AbsQueryOneAction<T> extends AbsQueryAction<T> {


    public AbsQueryOneAction(Class<T> zclass) {
        super(zclass);
    }

    public void query(QueryOne<T> queryOne){
        T proxyBean = getProxyBean();
        queryOne.one(proxyBean);
    }
    protected abstract Criterion createCriterion(String key, Object val);
}
