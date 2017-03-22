package com.bidanet.hibernate.lambda.proxy;



import com.bidanet.hibernate.lambda.annotation.LambdaJoinQuery;
import com.bidanet.hibernate.lambda.common.PropertyNameTool;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.hibernate.sql.JoinType;

import javax.persistence.Entity;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2017/3/10.
 */
public class MapListProxy extends GeterSeterMethodInterceptor {
    protected String prefix="";
    protected Map<String,List<Object>> mapList;
    protected Map<String,MapListProxy> fieldMapListProxy=new HashMap<>(1);
    protected Map<String,JoinType> joinFieldMap=new HashMap<>(1);


    public MapListProxy(Map<String, List<Object>> mapList) {
        this.mapList = mapList;
    }

    public MapListProxy() {
        this.mapList=new HashMap<>();
    }

    public MapListProxy(String prefix, Map<String, List<Object>> mapList) {
        this.prefix = prefix;
        this.mapList = mapList;
    }

    public Map<String, List<Object>> getMapList() {
        return mapList;
    }


    @Override
    public void execSeterMethod(Object obj, Method method, String property, Object val) {
        List<Object> list = mapList.get(property);
        if (list==null){
            list= new ArrayList<Object>();
            mapList.put(prefix+property,list);
        }

        if (list.indexOf(val)<0){
            list.add(val);
        }
    }


    @Override
    public Object execGeterMethod(Object obj, Method method, String property, Object val) {
//        super.execGeterMethod(obj, method, property, val);
        if (method.getReturnType()!=List.class){
            Entity entity = method.getReturnType().getDeclaredAnnotation(Entity.class);

            if (entity!=null){
                JoinType joinType = JoinType.INNER_JOIN;
                joinFieldMap.put(property,joinType);

                MapListProxy mapListProxy = fieldMapListProxy.get(property);
                if (mapListProxy==null){
                    mapListProxy=new MapListProxy(PropertyNameTool.JOIN_ALIAS_PREFIX+property+".",mapList);
                }
                return Proxy.proxy(method.getReturnType(),mapListProxy);
            }
        }

        return val;
    }

    public Map<String, JoinType> getJoinFieldMap() {
        return joinFieldMap;
    }
}
