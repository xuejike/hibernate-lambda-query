package com.bidanet.hibernate.lambda.query;

import com.bidanet.hibernate.lambda.proxy.GeterSeterMethodInterceptor;
import com.bidanet.hibernate.lambda.proxy.MapListProxy;
import net.sf.cglib.proxy.MethodInterceptor;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.sql.JoinType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2017/3/10.
 */
public abstract class AbsQueryListAction<T> extends AbsQueryOneAction<T>  {
    protected MapListProxy mapListProxy;

    public AbsQueryListAction(Class<T> zclass) {
        super(zclass);
    }

    @Override
    protected GeterSeterMethodInterceptor getInterceptor() {
        if (mapListProxy==null){
            mapListProxy=new MapListProxy();
        }
        return mapListProxy;
    }



    @Override
    public List<Criterion> getCriterionList() {
        ArrayList<Criterion> criterionArrayList = new ArrayList<>();
        Map<String, List<Object>> mapList = mapListProxy.getMapList();
        for (String key : mapList.keySet()) {
            List<Object> list = mapList.get(key);
            List<Criterion> criterionList = createCriteron(key, list);
            criterionArrayList.addAll(criterionList);
        }
        return criterionArrayList;
    }

    protected List<Criterion> createCriteron(String key, List<Object> list) {
        ArrayList<Criterion> criterionList = new ArrayList<>();
        if (list!=null){
            for (Object val : list) {
                Criterion criterion = createCriterion(key, val);
                criterionList.add(criterion);
            }
        }
        return criterionList;
    }

    public Map<String, List<Object>> getFieldMap(){
        return mapListProxy.getMapList();
    }
    @Override
    public Map<String,JoinType> getJoinField(){
        return mapListProxy.getJoinFieldMap();
    }


}
