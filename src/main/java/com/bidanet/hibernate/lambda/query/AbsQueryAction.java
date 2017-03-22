package com.bidanet.hibernate.lambda.query;

import com.bidanet.hibernate.lambda.proxy.GeterSeterMethodInterceptor;
import com.bidanet.hibernate.lambda.proxy.Proxy;
import net.sf.cglib.proxy.MethodInterceptor;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import java.util.List;

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
    public void buildCriteria(Criteria criteria){
        List<Criterion> criterionList = getCriterionList();
        for (Criterion criterion : criterionList) {
            criteria.add(criterion);
        }
    }

}
