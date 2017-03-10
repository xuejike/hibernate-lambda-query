package com.bidanet.hibernate.lambda.query;

import com.bidanet.hibernate.lambda.proxy.GeterSeterMethodInterceptor;
import com.bidanet.hibernate.lambda.proxy.Proxy;
import net.sf.cglib.proxy.MethodInterceptor;
import org.hibernate.Criteria;

/**
 * Created by xuejike on 2017/3/10.
 */
public abstract class AbsQueryAction<T> implements QueryAction<T> {
    protected Class<T> zclass;
    protected T proxyBean;

    public AbsQueryAction(Class<T> zclass) {
        this.zclass = zclass;
        proxyBean= Proxy.proxy(zclass,getInterceptor());
    }

    public AbsQueryAction(T proxyBean) {
        this.proxyBean = proxyBean;
    }

    protected abstract GeterSeterMethodInterceptor getInterceptor();

    @Override
    public T getProxyBean() {
        return proxyBean;
    }

    @Override
    public abstract void buildCriteria(Criteria criteria);

}
