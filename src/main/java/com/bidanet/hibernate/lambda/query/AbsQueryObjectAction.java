package com.bidanet.hibernate.lambda.query;

import com.bidanet.hibernate.lambda.core.QueryOne;
import com.bidanet.hibernate.lambda.proxy.GeterSeterMethodInterceptor;
import com.bidanet.hibernate.lambda.proxy.MapObjectProxy;
import net.sf.cglib.proxy.MethodInterceptor;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2017/3/10.
 */
public abstract class AbsQueryObjectAction<T> extends AbsQueryOneAction<T> {
    protected MapObjectProxy mapObjectProxy;

    public AbsQueryObjectAction(Class<T> zclass) {
        super(zclass);
    }


    @Override
    protected GeterSeterMethodInterceptor getInterceptor() {
        if (mapObjectProxy==null){
            mapObjectProxy=new MapObjectProxy();
        }
        return mapObjectProxy;
    }


    public Map<String,Object> getMap(){
        return mapObjectProxy.getMap();
    }


    @Override
    public List<Criterion> getCriterionList() {
        ArrayList<Criterion> criteria = new ArrayList<>();
        Map<String, Object> map = mapObjectProxy.getMap();
        for (String key : map.keySet()) {
            Criterion criterion = createCriterion(key, map.get(key));
            criteria.add(criterion);
        }
        return criteria;
    }
}
