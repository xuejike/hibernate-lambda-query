package com.bidanet.hibernate.lambda.query;

import com.bidanet.hibernate.lambda.proxy.GeterSeterMethodInterceptor;
import net.sf.cglib.proxy.MethodInterceptor;
import org.hibernate.Criteria;

/**
 * Created by xuejike on 2017/3/10.
 */
public abstract class AbsQueryAction<T> implements QueryAction<T> {
    /**
     * 获取代理处理类
     * @return
     */
    protected abstract MethodInterceptor getMethodInterceptor();
    @Override
    public T getProxyBean() {
        return null;
    }

    @Override
    public void buildCriteria(Criteria criteria) {

    }
}
