package com.bidanet.hibernate.lambda.query;

import com.bidanet.hibernate.lambda.proxy.GeterSeterMethodInterceptor;
import com.bidanet.hibernate.lambda.proxy.MapListProxy;
import net.sf.cglib.proxy.MethodInterceptor;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2017/3/10.
 */
public abstract class QueryListAction<T> extends AbsQueryOneAction<T>  {
    protected MapListProxy mapListProxy=new MapListProxy();

    public QueryListAction(Class<T> zclass) {
        super(zclass);
    }

    @Override
    protected GeterSeterMethodInterceptor getInterceptor() {
        return mapListProxy;
    }

    @Override
    public void buildCriteria(Criteria criteria) {
        Map<String, List<Object>> mapList = mapListProxy.getMapList();
        for (String key : mapList.keySet()) {
            List<Object> list = mapList.get(key);
            createCriteron(criteria, key, list);
        }
    }

    protected void createCriteron(Criteria criteria, String key, List<Object> list) {
        if (list!=null){
            for (Object val : list) {
                criteria.add(createCriterion(key,val));
            }
        }
    }

    @Override
    protected Criterion createCriterion(String key, Object val) {
        return null;
    }
}
