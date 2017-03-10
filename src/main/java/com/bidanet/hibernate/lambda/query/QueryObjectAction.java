package com.bidanet.hibernate.lambda.query;

import com.bidanet.hibernate.lambda.core.QueryOne;
import com.bidanet.hibernate.lambda.proxy.GeterSeterMethodInterceptor;
import com.bidanet.hibernate.lambda.proxy.MapObjectProxy;
import net.sf.cglib.proxy.MethodInterceptor;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import java.util.Map;

/**
 * Created by xuejike on 2017/3/10.
 */
public abstract class QueryObjectAction<T> extends AbsQueryOneAction<T> {
     protected MapObjectProxy mapObjectProxy=new MapObjectProxy();

    public QueryObjectAction(Class<T> zclass) {
        super(zclass);
    }


    @Override
    protected GeterSeterMethodInterceptor getInterceptor() {
        return mapObjectProxy;
    }

    @Override
    public void buildCriteria(Criteria criteria) {
        Map<String, Object> map = mapObjectProxy.getMap();
        for (String key : map.keySet()) {
            criteria.add(createCriterion(key,map.get(key)));
        }
    }
    public Map<String,Object> getMap(){
        return mapObjectProxy.getMap();
    }




}
